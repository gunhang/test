package com.rundering.manage.delivery;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.dto.AttachVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.LaundryOrderVO;
import com.rundering.scheduler.OrderTaskScheduler;
import com.rundering.service.AttachService;
import com.rundering.service.DeliveryService;
import com.rundering.util.FileUtil;
import com.rundering.util.MakeFileName;

@Controller
@RequestMapping("/fordelivery")
public class ForDeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	@Resource(name = "deliveryPath")
	private String deliveryPath;
	
	@Autowired
	private AttachService attachService;

	@RequestMapping("/login")
	public String login() {
		String url = "/delivery/login";
		return url;
	}

	@RequestMapping("/main")
	public ModelAndView main(ModelAndView mnv, String branchCode, HttpServletRequest request) throws Exception {
		String url = "/delivery/main";
		
		HttpSession session = request.getSession();
		EmployeesVO emp = (EmployeesVO) session.getAttribute("loginEmployee");
		branchCode = emp.getBranchCode();
		
		Map<String, Object> dataMap = deliveryService.getOrderCount(branchCode);
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/getPicture", produces = "text/plain;charset=utf-8")
	public ResponseEntity<byte[]> getPicture(AttachVO atch) throws Exception {
		
		FileUtil fileUtil = new FileUtil();
		ResponseEntity<List<byte[]>> en = fileUtil.getPicture(atch, attachService);
		List<byte[]> bs =en.getBody();
		byte[] file = bs.get(0);
		
		ResponseEntity<byte[]> entity = null;
		entity = new ResponseEntity<byte[]>(file, HttpStatus.CREATED);
		return entity;
	}
	

	@RequestMapping(value ="pictureupload",method = RequestMethod.POST,produces ="application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, String>> pictureUpload(MultipartFile multi){
		
		Map<String, String> fileMap = null;
		FileUtil fileUtil = new FileUtil();
		fileMap= fileUtil.saveFile(multi, deliveryPath);
		ResponseEntity<Map<String, String>> resp = new ResponseEntity<Map<String,String>>(fileMap, HttpStatus.OK);
//		{
//			fileName:  ,
//			originame : 
//		}
//		var Filenam
//		success : function(result){
//			Filenam = result.fileName
//		}
		return resp;
		
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(LaundryOrderVO laundryOrder,AttachVO attach, RedirectAttributes rttr) throws Exception {
		String url = null;
		
		if(laundryOrder.getOrderStatus().equals("03")) {
			url = "redirect:/fordelivery/pickup";
		}else if(laundryOrder.getOrderStatus().equals("08") || laundryOrder.getOrderStatus().equals("09")) {
			url = "redirect:/fordelivery/delivery";
		}
		
		String fileName = laundryOrder.getPicture();
		
		File file = new File(deliveryPath + fileName);
		String orginalFileName = MakeFileName.parseFileNameFromUUID(fileName, "\\$\\$");
		long fileSize = file.length() / 1024;
		String type = fileName.substring(fileName.lastIndexOf('.') + 1);
		attach.setFileContType(type);
		attach.setFileNm(orginalFileName);
		attach.setSaveFileNm(fileName);
		attach.setFileSize(fileSize);
		attach.setFilePath(deliveryPath);
		
		deliveryService.regist(laundryOrder, attach);
		rttr.addFlashAttribute("from", "regist");
		
		// ????????? ?????????
		String oPath = deliveryPath + attach.getSaveFileNm(); // ?????? ??????
		File oFile = new File(oPath);

		int index = oPath.lastIndexOf(".");
		String ext = oPath.substring(index + 1); // ?????? ?????????

		String tPath = oFile.getParent() + File.separator + "t-" + oFile.getName(); // ??????????????? ??????
		File tFile = new File(tPath);

		double ratio = 2; // ????????? ?????? ??????

		try {
			BufferedImage oImage = ImageIO.read(oFile); // ???????????????
			int tWidth = (int) (oImage.getWidth() / ratio); // ????????? ????????????????????? ??????    200 ?????? ??????
			int tHeight = (int) (oImage.getHeight() / ratio); // ????????? ????????????????????? ?????? 200 ?????? ??????
			
			BufferedImage tImage = new BufferedImage(tWidth, tHeight, BufferedImage.TYPE_3BYTE_BGR); // ??????????????????
			Graphics2D graphic = tImage.createGraphics();
			Image image = oImage.getScaledInstance(tWidth, tHeight, Image.SCALE_SMOOTH);
			graphic.drawImage(image, 0, 0, tWidth, tHeight, null);
			graphic.dispose(); // ???????????? ?????? ??????

			ImageIO.write(tImage, ext, tFile);
		} catch (IOException e) {
			e.printStackTrace();
		}


		return url;
	}
	
	// ?????? ?????????
	@RequestMapping("/pickup")
	public ModelAndView pickUp(ModelAndView mnv, HttpServletRequest request, LaundryOrderVO laundryOrder)
			throws Exception {
		String url = "/delivery/pickup";

		HttpSession session = request.getSession();
		EmployeesVO emp = (EmployeesVO) session.getAttribute("loginEmployee");

		laundryOrder.setBranchCode(emp.getBranchCode());
		laundryOrder.setPickupEmployeeId(emp.getEmployeeId());

		Map<String, Object> dataMap = deliveryService.getPickupWaitList(laundryOrder);

		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);

		return mnv;
	}
	
	@RequestMapping("/pickupdetailform")
	public String pickUpDetailForm() {
		String url="";
				
		return url;
	}
	
	// ?????? ????????????
	@RequestMapping("/pickupdetail")
	public ModelAndView pickUpDetail(ModelAndView mnv, String orderNo,String orderStatus, AttachVO attach) throws Exception {
		String url = null;
		if (orderStatus.equals("02")) {
			url = "/delivery/pickUp_detail";
		} else if (orderStatus.equals("03")) {
			url = "/delivery/pickUpCom_detail";
		}
		Map<String, Object> dataMap = deliveryService.getOrderDetailByOrderNo(orderNo, attach);
			
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
			
		return mnv;
	}
	
	// ?????? ??????????????? ????????????
	@RequestMapping("/updatePickUpCom")
	public ResponseEntity<String> updatePickUpCom(LaundryOrderVO laundryOrder)throws Exception {
		ResponseEntity<String> ok = null;
		
		try {
			deliveryService.updatePickUpCom(laundryOrder);
			
			ok = new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ok;
		
	}
	
	// ?????? ?????? ?????? ??????(?????? ?????? ???)
	@RequestMapping("/arrive/tobranch")
	public ResponseEntity<String> arriveTobranch(LaundryOrderVO laundryOrder, HttpServletRequest request)throws Exception {
		ResponseEntity<String> ok = null;
		
		HttpSession session = request.getSession();
		EmployeesVO ev = (EmployeesVO) session.getAttribute("loginEmployee");
		
		laundryOrder.setBranchCode(ev.getBranchCode());
		
		
		try {
			deliveryService.updateToBranch(laundryOrder);
			
			ok = new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ok;
	}

	// ?????? ?????????
	@RequestMapping("/delivery")
	public ModelAndView deliveryList( HttpServletRequest request, LaundryOrderVO laundryOrder, ModelAndView mnv) throws Exception {
		String url = "/delivery/delivery";

		HttpSession session = request.getSession();
		EmployeesVO emp = (EmployeesVO) session.getAttribute("loginEmployee");

		laundryOrder.setBranchCode(emp.getBranchCode());
		laundryOrder.setPickupEmployeeId(emp.getEmployeeId());
		
		Map<String, Object> dataMap = deliveryService.getDeliveryList(laundryOrder);

		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);

		return mnv;
	}
	//?????? ????????????
	@RequestMapping(value="/deliverydetail")
	public ModelAndView deliveryDetail(String orderNo,String orderStatus, ModelAndView mnv, AttachVO attach) throws Exception{
		String url=null;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		if(orderStatus.equals("07")) {
			url="/delivery/delivery_detail";
		}else if((orderStatus.equals("08"))||(orderStatus.equals("09"))){
			url="/delivery/deliveryCom_detail";
		}
		dataMap = deliveryService.getOrderDetailByOrderNo(orderNo, attach);

		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	//?????? ??????
	@RequestMapping("/sortAddress")
	public ResponseEntity<List<LaundryOrderVO>> sortAddress(String sortValue, LaundryOrderVO laundryOrder) throws Exception{
		ResponseEntity<List<LaundryOrderVO>> entity = null;
		List<LaundryOrderVO> sort = null;
		
		if(sortValue.equals("0")) {
			sort = deliveryService.sortAddressAsc(laundryOrder);
		}else if(sortValue.equals("1")) {
			sort = deliveryService.sortAddressDesc(laundryOrder);
		}
		entity = new ResponseEntity<List<LaundryOrderVO>>(sort,HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping("/playScheduler")
	public void playScheduler() throws Exception{
		new OrderTaskScheduler().assignPickupEmployee(); 
		new OrderTaskScheduler().assignDeliveryEmployee();
	}
	
	/** ????????? ????????? ????????????
     */
//    @GetMapping("/display")
//    public ResponseEntity<byte[]> getFile(String fileName){

       /* ResponseEntity<byte[]> result = null;

        try{
            String srcFileName = URLDecoder.decode(fileName,"UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            HttpHeaders header = new HttpHeaders();

            //MIME ?????? ??????
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            //File????????? Path??? ???????????? MIME ????????? ???????????? HTTPHeaders??? Content-Type???  ????????? ???????????????.

            //?????? ????????? ?????? *FileCopyUtils.copy ????????? ??????
            //new ResponseEntity(body,header,status)
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
            
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
	*/
	
	
	
	// ?????? ????????????
//	@RequestMapping(value="/deliverydetail",method = RequestMethod.POST,produces ="application/json;charset=utf-8")
//	@ResponseBody
//	public ResponseEntity<Map<String, Object>> deliverydetail(String orderNo, ModelAndView mnv, String orderStatus) throws Exception {
//		Map<String, Object> dataMap = null;
//		ResponseEntity<Map<String, Object>> ok = null;
//		
//		String url = null;
//		if (orderStatus.equals("06")||orderStatus.equals("07")) {
//			url = "/delivery/delivery_detail";
//			dataMap = deliveryService.getOrderDetailByOrderNo(orderNo);
//			dataMap.put("list", "OK");
//			ok = new ResponseEntity<Map<String, Object>>(dataMap,HttpStatus.OK);
//		} else if ((orderStatus.equals("08"))||(orderStatus.equals("09"))) {
//			url = "/delivery/deliveryCom_detail";
//			ok = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
//			dataMap = deliveryService.getOrderDetailByOrderNo(orderNo);
//			dataMap.put("COM", "COM");
//			
//		}
//
//
//		return ok;
	//}
}
