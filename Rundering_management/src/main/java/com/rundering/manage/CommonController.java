package com.rundering.manage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.command.MemberAddCommand;
import com.rundering.dto.BranchVO;
import com.rundering.dto.MemberAddressVO;
import com.rundering.dto.MemberVO;
import com.rundering.service.BranchService;
import com.rundering.service.MemberAddressService;
import com.rundering.service.MemberService;
import com.rundering.util.FormatUtil;
import com.rundering.util.PhoneResDTO.SendSmsResponse;
import com.rundering.util.SensSms;

@Controller
public class CommonController {
	
	@Autowired
	private SensSms sensSms;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BranchService branchService;
	
	@Resource(name="memberAddressService")
	private MemberAddressService memberAddressService;
	
	
	@RequestMapping("/common/application")
	public ModelAndView application(ModelAndView mnv) throws Exception {
		String url = "common/worker_register";
		
		List<BranchVO> branchList = branchService.getBranchList();
		
		mnv.addObject("branchList", branchList);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/common/application_success")
	@ResponseBody
	public ResponseEntity<String> application_success(MemberAddCommand mac) throws Exception, IOException {
		ResponseEntity<String> entity = null;
			
		MemberVO member = mac.toMember();
		
		MemberAddressVO memberAdd;
		
		try {
			
			memberService.applicationEmployee(member);
			
			member = memberService.getMemberNo(member.getPhone());
			
			mac.setMemberNo(member.getMemberNo());
			
			memberAdd = mac.toAddr();
			
			memberAddressService.memberAddressRegist(memberAdd);
			
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
			
		} catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	
	//문자인증
	@ResponseBody
	@RequestMapping(value = "/common/certifyPhoneNum", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> certifyPhoneNum(String phoneNumber) throws Exception {
		
		ResponseEntity<Map<String, Object>> result = null;
		Map<String, Object> dataMap = new HashMap<>();
		
		String randomNum = String.format("[%06d]", (int)(Math.random() * 999999));
		
		try {
			SendSmsResponse sendSmsResponse = sensSms.sendSMS(phoneNumber, randomNum+" Rundering에서 보낸 인증번호 입니다.");
			if(sendSmsResponse.getStatusCode().equals("202") || sendSmsResponse.getStatusCode().equals("200")) {
				dataMap.put("randomNum", FormatUtil.RemoveSpecialChar(randomNum));
				result = new ResponseEntity<Map<String, Object>>(dataMap,HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/common/loginform", method = RequestMethod.GET)
	public String loginForm(@RequestParam(defaultValue="")String error,
			HttpServletResponse response) {
		String url = "common/worker_login";
		
		if(error.equals("1")) {
			response.setStatus(302);
		}
		
		return url;
	}
	
	@RequestMapping("/common/findpassword")
	public String findPassword() {
		String url = "common/worker_forgot_password";
		
		
		
		return url;
	}
	
	@RequestMapping("/common/change/newpasswordform")
	public ModelAndView changePasswordForm(ModelAndView mnv,String id,String password) {
		String url = "common/worker_change_new_password";
		
		mnv.addObject("id" , id);
		mnv.addObject("password" , password);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/common/change/newpassword")
	public ResponseEntity<String> changePassword(MemberVO mv) {
		ResponseEntity<String> entity = null;

		try {
			memberService.modifyPwById(mv);
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	
	@RequestMapping("/security/accessDenied")
	public void accessDenied() {}
	
	@RequestMapping("/common/loginTimeOut")
	public String loginTimeOut(Model model)throws Exception {
		String url="security/sessionOut";
		model.addAttribute("message","세션이 만료되었습니다.");
		return url;
	}
}
