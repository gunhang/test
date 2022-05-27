package com.rundering.customer;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.command.MemberAddCommand;
import com.rundering.dto.MemberAddressVO;
import com.rundering.dto.MemberVO;
import com.rundering.service.MemberAddressService;
import com.rundering.service.MemberService;
import com.rundering.util.UserSha256;

@Controller
public class MainController {

	@Autowired
	private MemberService memberService;
	
	@Resource(name="memberAddressService")
	private MemberAddressService memberAddressService;
	
	@RequestMapping("/home")
	public ModelAndView main(HttpServletRequest request,ModelAndView mnv) {
		String url = "/main";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		mnv.addObject("loginUser", loginUser);
		mnv.setViewName(url);
		
		return mnv;
	}

	@RequestMapping("/joinform")
	public String joinForm() {
		String url = "/login/member_join";
		return url;
	}	
	
	@RequestMapping("/join")
	@ResponseBody
	public ResponseEntity<String> join(MemberAddCommand mac) throws Exception, IOException {
		ResponseEntity<String> entity = null;
			
		MemberVO member = mac.toMember();
		
		MemberAddressVO memberAdd = null;
		
		String pw = UserSha256.encrypt(member.getPassword());
		member.setPassword(pw);
		try {
			
			memberService.memberJoin(member);
			
			member = memberService.getMember(member.getId());
			
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
	
	
	@RequestMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<String> idCheck(String id) throws Exception {
		ResponseEntity<String> entity = null;
		System.out.println(id);
		try {
			MemberVO member = memberService.checkId(id);
			if (member != null) {
				entity = new ResponseEntity<String>("duplicated", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("", HttpStatus.OK);
			}
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	// 이메일 중복 체크
	@RequestMapping("/emailCheck")
	@ResponseBody
	public ResponseEntity<String> emailCheck(String email) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			MemberAddCommand mac = memberService.checkEmail(email);
			if (mac != null) {
				entity = new ResponseEntity<String>("OK", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("", HttpStatus.OK);
			}
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	
	
	@RequestMapping("/introduce")
	public String introduce() {
		String url = "/introduce/company_introduce";
		return url;
	}
	
	
	
	
}
