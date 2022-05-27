package com.rundering.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.rundering.dto.EmployeesVO;
import com.rundering.dto.MemberVO;


public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		User user = (User)authentication.getDetails();	
		
		MemberVO loginMember = user.getMemberVO();
		String memberno = loginMember.getMemberNo();
		
		EmployeesVO loginEmployee = user.getEmployeesVO(memberno);
		String branchName = user.getBranchName(loginEmployee.getBranchCode());
 		
		HttpSession session = request.getSession();		
		session.setAttribute("loginMember", loginMember);
		session.setAttribute("loginEmployee", loginEmployee);
		session.setAttribute("branchName", branchName);
		session.setMaxInactiveInterval(60*240);
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
