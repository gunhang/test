package com.rundering.customer;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.command.LaundryOrderReceiveCommand;
import com.rundering.dto.AttachVO;
import com.rundering.dto.LaundryOrderDetailVO;
import com.rundering.dto.LaundryOrderVO;
import com.rundering.dto.MemberAddressVO;
import com.rundering.dto.MemberVO;
import com.rundering.service.AttachService;
import com.rundering.service.LaundryItemsService;
import com.rundering.service.LaundryOrderService;
import com.rundering.service.MemberAddressService;
import com.rundering.util.FormatUtil;
import com.rundering.util.SensSms;

@Controller
@RequestMapping("/order")
public class LaundryOrderController {
	
	@Resource(name = "laundryOrderService")
	private LaundryOrderService laundryOrderService;
	@Resource(name = "laundryItemsService")
	private LaundryItemsService laundryItemsService;
	@Resource(name="memberAddressService")
	private MemberAddressService memberAddressService;
	@Resource(name = "attachService")
	private AttachService attachService;
	@Resource(name = "sensSms")
	private SensSms sensSms;
	
	@Resource(name = "laundryorderpicturePath")
	private String picturePath;
	
	
	@RequestMapping("")
	public ModelAndView checkInformation(HttpServletRequest request, ModelAndView mnv) throws Exception {
		String url="/order/order_essential";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		String hyphenationPhoneNum = FormatUtil.hyphenationPhoneNum(loginUser.getPhone());
		mnv.addObject("phone",hyphenationPhoneNum);
		
		MemberAddressVO defaultMemberAddress = memberAddressService.getDefaultMemberAddress(loginUser.getMemberNo());
		mnv.addObject("defaultMemberAddress",defaultMemberAddress);
		
		List<MemberAddressVO> memberAddressList = memberAddressService.getMemberAddressList(loginUser.getMemberNo());
		mnv.addObject("memberAddressList",memberAddressList);
		
		mnv.setViewName(url);
		
		return mnv;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ModelAndView order(LaundryOrderReceiveCommand command, ModelAndView mnv) throws Exception {
		String url="/order/order";

		Map<String, Object> dataMap = laundryItemsService.getlaundryItemsList();
		
		mnv.addObject("command",command);
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/comfirm", method = RequestMethod.POST)
	public ModelAndView comfirm(LaundryOrderReceiveCommand command, HttpServletRequest request, ModelAndView mnv) throws Exception {
		String url="/order/order_confirm";
		
		List<LaundryOrderDetailVO> laundryOrderDetailVOList = command.toLaundryOrderDetailVOList();
		Map<String, Object> dataMap = laundryOrderService.checkOrder(laundryOrderDetailVOList);
		
		mnv.setViewName(url);
		mnv.addObject("command",command);
		mnv.addObject("dataMap", dataMap);
		
		return mnv;
	}
	
	@RequestMapping(value = "/completed", method = RequestMethod.POST)
	public ModelAndView completed(@RequestParam(defaultValue = "") String from,LaundryOrderReceiveCommand command, HttpServletRequest request, ModelAndView mnv) throws Exception {
		String url="/order/order_completed";
		
//		System.out.println(from);
//		if(!from.equals("confirm")) {
//			//insert??? ??? ??? ???????????? ???
//			LaundryOrderVO registeredLaundryOrder = laundryOrderService.getLaundryOrderByPaymentNo(command.getPaymentNo());
//		}else {
//			//insert ??? ?????? ???
//			//?????? insert???????????????????????????
//			url="redirect:/order/completed";
//		}
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		MemberAddressVO memberAddress = command.toMemberAddressVO();
		//????????? ???????????? ?????? ????????? ??????
		if(command.getAddressNo().equals("0")) {
			memberAddress.setMemberNo(loginUser.getMemberNo());
			memberAddressService.memberAddressRegist(memberAddress);
		}else {
			//????????? ????????? ???????????? ??????????????? ?????????????????????
			memberAddress = memberAddressService.getMemberAddress(command.getAddressNo());
			command.setAddress(memberAddress);
		}
		
		//???????????? ?????? ??????
		List<AttachVO> attachVOList = command.toAttachVOList(picturePath);
		int atchFileNo = attachService.insertFile(attachVOList);
		
		LaundryOrderVO laundryOrder = command.toLaundryOrderVO();
		laundryOrder.setMemberNo(loginUser.getMemberNo());
		laundryOrder.setAtchFileNo(String.valueOf(atchFileNo));
		memberAddress = memberAddressService.getAreaCode(memberAddress);
		laundryOrder.setArea(memberAddress.getArea());
		
		List<LaundryOrderDetailVO> laundryOrderDetailVOList = command.toLaundryOrderDetailVOList();
 		String orderNo = laundryOrderService.orderReceive(laundryOrder, laundryOrderDetailVOList);
 		
 		LaundryOrderVO registeredLaundryOrder = laundryOrderService.getLaundryOrder(orderNo);
 		
 		SimpleDateFormat dateHourFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 		

 		StringBuilder sb = new StringBuilder();
 		sb.append("[Rundering]\n???????????? ??????????????? ???????????????????????????.");
 		sb.append("\n???????????????: ");
 		sb.append(registeredLaundryOrder.getOrderNo());
 		
 		String smsStr = sb.toString();
		
 		//???????????? ?????? ????????? ?????? ??????~
// 		try {
//			SendSmsResponse sendSmsResponse = sensSms.sendSMS(registeredLaundryOrder.getContactNumber().trim(), smsStr);
//			if(sendSmsResponse.getStatusCode().equals("202") || sendSmsResponse.getStatusCode().equals("200")) {
//				System.out.println(sendSmsResponse);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
 		
 		mnv.addObject("registeredLaundryOrder", registeredLaundryOrder);
		mnv.setViewName(url);
		return mnv;
		
		
	}

}
