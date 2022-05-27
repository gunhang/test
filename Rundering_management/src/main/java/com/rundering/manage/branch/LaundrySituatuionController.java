package com.rundering.manage.branch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.LaundryOrderVO;
import com.rundering.service.AttachService;
import com.rundering.service.LaundryOrderService;
import com.rundering.util.FileUtil;

@Controller
@RequestMapping("/branch/laundrysituatuion")
public class LaundrySituatuionController {
	@Autowired
	LaundryOrderService laundryOrderService;
	@Autowired
	AttachService attachService;
	
	@RequestMapping("/list")
	private String situatuionList(Model model,BranchCriteria cri,HttpSession session) throws Exception {
		Map<String, Object> dataMap=null;
		cri.setPerPageNum(5);
		String url = "branch/laundrysituatuion/situatuion_list";
		EmployeesVO emp = (EmployeesVO)session.getAttribute("loginEmployee");
		cri.setBranchCode(emp.getBranchCode());
		dataMap= laundryOrderService.laundryOrderList(cri);
		model.addAttribute("dataMap", dataMap);
		return url;
	}
	

	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	private String situatuonModify(String[] orderNo ,String orderStatus) throws Exception{
		String url = "redirect:/branch/laundrysituatuion/list";
		List<LaundryOrderVO> laundryOrderList = new ArrayList<LaundryOrderVO>();
		for (String no : orderNo) {
			LaundryOrderVO laundryOrder = new LaundryOrderVO();
			laundryOrder.setOrderNo(no);
			laundryOrder.setOrderStatus(orderStatus);
			laundryOrderList.add(laundryOrder);
		}
		laundryOrderService.updateStatus(laundryOrderList);
		return url;
	}
	
	@RequestMapping("/orderdetail")
	private String situatuonDetail(HttpSession session ,String orderNo, Model model) throws Exception{
		String url = "branch/laundrysituatuion/laundry_order_detail";
		Map<String, Object> dataMap;
		String empBranchName = null;
		
		dataMap = laundryOrderService.getlaundryOrderByOrderNo(orderNo);
		if(dataMap==null) {
			return "branch/laundrysituatuion/window_close";
		}
		EmployeesVO emp = (EmployeesVO)session.getAttribute("loginEmployee");
		LaundryOrderVO laundryOrder=(LaundryOrderVO) dataMap.get("laundryOrder");
		empBranchName  =  laundryOrderService.selectGetBranchCode(emp.getBranchCode());
		if(!laundryOrder.getBranchCode().equals(empBranchName)) {
			return "branch/laundrysituatuion/window_close";
		}
		model.addAllAttributes(dataMap);
		return url; 
	}
	@RequestMapping(value =  "/getimgs",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody
	private  ResponseEntity<List<byte[]>> situationGetImage(String atchFileNo){
		FileUtil fileUtil = new FileUtil();
		 
		ResponseEntity<List<byte[]>> resp=null;;
		try {
			resp = fileUtil.getPicture(atchFileNo, attachService);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
	}
	
}
