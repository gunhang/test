package com.rundering.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rundering.dto.FAQVO;

public class FAQRegistCommand {
	private String setbukdoorclcode;// 질문분류코드
	private String question; // 질문
	private String fcontent; // 질문내용
	private String writer; // 작성자
	private String orderno; //주문번호
	private String secretyn; // 비밀글여부
	private List<MultipartFile> uploadFile; //첨부파일
	public String getSetbukdoorclcode() {
		return setbukdoorclcode;
	}
	public void setSetbukdoorclcode(String setbukdoorclcode) {
		this.setbukdoorclcode = setbukdoorclcode;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getFcontent() {
		return fcontent;
	}
	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getSecretyn() {
		return secretyn;
	}
	public void setSecretyn(String secretyn) {
		this.secretyn = secretyn;
	}
	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(List<MultipartFile> uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public FAQVO toFAQVO() {
		FAQVO faqvo = new FAQVO();
		faqvo.setSetbukdoorclcode(this.setbukdoorclcode);
		faqvo.setQuestion(this.question);
		faqvo.setFcontent(this.fcontent);
		faqvo.setWriter(this.writer);
		faqvo.setOrderno(this.orderno);
		faqvo.setSecretyn(this.secretyn);
		
		return faqvo;
	}
}
