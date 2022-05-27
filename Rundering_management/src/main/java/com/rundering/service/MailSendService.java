package com.rundering.service;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.rundering.dao.MemberDAO;
import com.rundering.dto.MemberVO;
import com.rundering.util.MailUtils;

public class MailSendService {
	
	private JavaMailSenderImpl mailSender;

	MemberDAO memberDAO;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}
	

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	// 사원등록 후 아이디 및 비밀번호 이메일로 발송
	public void sendIdPwMail(String memberNo) {
		
		MemberVO mv;
		try {
			mv = memberDAO.getEmpAppinfo(memberNo);
			
			// 인증메일 보내기
			MailUtils sendMail = new MailUtils(mailSender);
			sendMail.setSubject("사원 등록이 정상적으로 완료 되었습니다.");
			sendMail.setText(new StringBuffer().append("<h1>[사원 아이디]</h1><br/>")
					.append("<h3>"+mv.getId()+"<h3>")
					.append("<br/><h1>[사원 비밀번호]</h1><br/>")
					.append("<h3>"+mv.getId()+"<h3><br/>")
					.append("<br/>비밀번호는 사원번호와 일치하여 보안에 취약하니 로그인시 비밀번호 변경을 권고 드립니다.<br/>")
					.append("<br/><a href='http://localhost/runderingmanage/common/change/newpasswordform?id="+mv.getId()+"&password="+mv.getPassword()+"'>비밀번호 변경</a>")
					.toString());
			sendMail.setTo(mv.getEmail());
			sendMail.send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
}
