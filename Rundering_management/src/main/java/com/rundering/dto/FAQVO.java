package com.rundering.dto;

import java.sql.Date;

public class FAQVO {
	private int faqno; // 질문번호
	private String setbukdoorclcode;// 질문분류코드
	private String question; // 질문
	private String answer; // 답변
	private Date registDate; // 등록일
	private Date modifyDate; // 수정일
	private String writer; // 작성자
	private String secretyn; // 비밀글여부
	private String faqyn; // FAQ여부
	private String fcontent; // 질문내용
	private String orderno; //주문번호
	private String atchFileNo; //통합첨부파일번호

	

	public String getAtchFileNo() {
		return atchFileNo;
	}

	public void setAtchFileNo(String atchFileNo) {
		this.atchFileNo = atchFileNo;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public int getFaqno() {
		return faqno;
	}

	public void setFaqno(int faqno) {
		this.faqno = faqno;
	}

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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSecretyn() {
		return secretyn;
	}

	public void setSecretyn(String secretyn) {
		this.secretyn = secretyn;
	}

	public String getFaqyn() {
		return faqyn;
	}

	public void setFaqyn(String faqyn) {
		this.faqyn = faqyn;
	}

	public String getFcontent() {
		return fcontent;
	}

	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}

}
