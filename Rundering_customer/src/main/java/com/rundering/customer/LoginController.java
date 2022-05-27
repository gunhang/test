package com.rundering.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.dto.MemberVO;
import com.rundering.service.MailSendService;
import com.rundering.service.MemberService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	MemberService memberService;
	
	@Autowired
	private MailSendService mss;
	
	@RequestMapping("form")
	public String login() {
		String url="/login/member_login";
		return url;
	}
	@RequestMapping("failcount")
	public String loginfailCount() {
		String url="/login/member_login_count";
		return url;
	}
	
	@RequestMapping("/findaccount")
	public String forgot_account() {
		String url="/login/member_forgot_account";
		return url;
	}
	
	@RequestMapping("/findpassword")
	public String forgot_password() {
		String url="/login/member_forgot_password";
		return url;
	}
	
	@RequestMapping(value ="/idfindmailcheck",method = RequestMethod.POST,produces = "application/json;charset=utf-8") 
	@ResponseBody
	public ResponseEntity<Map<String, String>> idFindMailCheck(MemberVO member) {
		ResponseEntity<Map<String, String>> entity = null;
		Map<String, String> resultMap = new HashMap<String, String>();
		int check =0;
		try {
			check = memberService.idFind(member);
		} catch (Exception e) {
			entity = new ResponseEntity<Map<String, String>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			return entity;
		}
		if(check==0) resultMap.put("check", "false"); 
		if(check==1) {
			String authKey = mss.sendAuthMail(member.getEmail());
			resultMap.put("check", "true"); 
			resultMap.put("authKey",authKey);
			resultMap.put("name",member.getName());
			resultMap.put("email", member.getEmail());
		}
		entity = new ResponseEntity<Map<String, String>>(resultMap,HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value ="/passwordfindmailcheck",method = RequestMethod.POST,produces = "application/json;charset=utf-8") 
	@ResponseBody
	public ResponseEntity<Map<String, String>> passwordFindMailCheck(MemberVO member) {
		ResponseEntity<Map<String, String>> entity = null;
		Map<String, String> resultMap = new HashMap<String, String>();
		int check=0;
		try {
			check = memberService.passwordFind(member);
		} catch (Exception e) {
			entity = new ResponseEntity<Map<String, String>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			return entity;
		}
		if(check==0) resultMap.put("check", "false"); 
		if(check==1) {
			String authKey = mss.sendAuthMail(member.getEmail());
			resultMap.put("check", "true"); 
			resultMap.put("authKey",authKey);
			resultMap.put("id",member.getId());
		}
		entity = new ResponseEntity<Map<String, String>>(resultMap,HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value ="/idgetemail",method = RequestMethod.POST) 
	@ResponseBody
	public ResponseEntity<String> idGetEmail(MemberVO member) {
		ResponseEntity<String> entity = null;
	 	System.out.println(member.getName());
	 	System.out.println(member.getEmail());
		String id = null;
		try {
			id = memberService.getFindId(member);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			return entity;
		}
		if(id ==null || id=="") {
			entity = new ResponseEntity<String>("false",HttpStatus.OK);
		}
		if(id !=null && id!="") {
			mss.sendIdMail(member.getEmail(),id);
			entity = new ResponseEntity<String>("true",HttpStatus.OK);
		}
		return entity;
	}

	@RequestMapping(value ="passwordmodifyform",method = RequestMethod.POST )
	public ModelAndView passwordModifyForm(String id,ModelAndView modelAndView) {
		String url="/login/member_forgot_password_modify";
		modelAndView.addObject("id", id);
		modelAndView.setViewName(url);
		return modelAndView;
	}
	@RequestMapping(value ="passwordModify",method = RequestMethod.POST)
	public String passwordModify(MemberVO member,Model model) throws Exception {
		String url="/login/member_login";
		System.out.println(member.getId());
		System.out.println(member.getPassword());
		memberService.updatePassword(member);
		model.addAttribute("from", "passwordModify");
		
		return url;
	}
	
}
