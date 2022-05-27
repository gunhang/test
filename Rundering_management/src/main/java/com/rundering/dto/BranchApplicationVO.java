package com.rundering.dto;

import java.util.Date;

public class BranchApplicationVO {
	
	private int applicationNo;               //지점신청번호
	private Date applicationDate;            //신청날짜
	private String applicateName;            //신청자명
	private String phone;                    //연락처
	private String email;                    //이메일
	private String leasecontractFile;        //임대차계약서 첨부파일번호
	private String approvalreturnYn;         //승인반려여부
	private String approvalreturnContents;   //승인반려내용
	private String examinationYn;            //심사통과여부
	private String examinationDetails;       //심사내용
	private String privatecontractFile;      //수의계약서 첨부파일번호
	private String charger;                  //담당자
	private String progressStatusCode;		 //진행상태코드
	
	
	
	public BranchApplicationVO() {
		super();
	}
	



	public int getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(int applicationNo) {
		this.applicationNo = applicationNo;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getApplicateName() {
		return applicateName;
	}
	public void setApplicateName(String applicateName) {
		this.applicateName = applicateName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLeasecontractFile() {
		return leasecontractFile;
	}
	public void setLeasecontractFile(String leasecontractFile) {
		this.leasecontractFile = leasecontractFile;
	}
	public String getApprovalreturnYn() {
		return approvalreturnYn;
	}
	public void setApprovalreturnYn(String approvalreturnYn) {
		this.approvalreturnYn = approvalreturnYn;
	}
	public String getApprovalreturnContents() {
		return approvalreturnContents;
	}
	public void setApprovalreturnContents(String approvalreturnContents) {
		this.approvalreturnContents = approvalreturnContents;
	}
	public String getExaminationYn() {
		return examinationYn;
	}
	public void setExaminationYn(String examinationYn) {
		this.examinationYn = examinationYn;
	}
	public String getExaminationDetails() {
		return examinationDetails;
	}
	public void setExaminationDetails(String examinationDetails) {
		this.examinationDetails = examinationDetails;
	}
	public String getPrivatecontractFile() {
		return privatecontractFile;
	}
	public void setPrivatecontractFile(String privatecontractFile) {
		this.privatecontractFile = privatecontractFile;
	}
	public String getCharger() {
		return charger;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public String getProgressStatusCode() {
		return progressStatusCode;
	}
	public void setProgressStatusCode(String progressStatusCode) {
		this.progressStatusCode = progressStatusCode;
	}
	
	
	
}
