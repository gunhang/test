package com.rundering.service;

import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.rundering.util.MailUtils;

public class MailSendService {
	
	private JavaMailSenderImpl mailSender;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}
	
	// 인증키 생성
	private String getKey() {
		return getAuthCode();
	}

	// 인증코드 난수 발생
	private String getAuthCode() {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		int num = 0;

		while (buffer.length() < 4) {
			num = random.nextInt(10);
			buffer.append(num);
		}

		return buffer.toString();
	}

	// 인증메일 보내기
	public String sendAuthMail(String email) {
		// 6자리 난수 인증번호 생성
		String authKey = getKey();

		// 인증메일 보내기
		try {
			MailUtils sendMail = new MailUtils(mailSender);
			sendMail.setSubject("rundering 인증키 이메일 인증");
			sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
					.append("&authKey=").append(authKey).toString());
			sendMail.setTo(email);
			sendMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} 

		return authKey;
	}
	public void sendIdMail(String email,String id) {

		// 인증메일 보내기
		try {
			MailUtils sendMail = new MailUtils(mailSender);
			sendMail.setSubject("아이디 찾기");
			sendMail.setText(new StringBuffer().append("<h1>[회원 아이디]</h1>")
					.append("&id=").append(id).toString());
			sendMail.setTo(email);
			sendMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} 

	}
}
