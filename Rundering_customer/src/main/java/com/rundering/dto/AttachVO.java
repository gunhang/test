package com.rundering.dto;

import java.util.Date;

public class AttachVO {

	private String  atchFileNo;    //통합첨부파일번호
	private int  atchFileSeq;      //첨부파일순번
	private String  bizType;       //업무구분   
	private String  filePath;      //파일경로  
	private String  fileNm;        //파일명   
	private String  saveFileNm;    //저장파일명   
	private String  fileContType;  //파일유형   
	private Long  fileSize;        //파일크기   
	private int  fileDownCnt;      //다운횟수  
	private Date  regDate;         //등록일  
	private String  regUserId;     //등록자ID  
	private Date  updDate;         //수정일
	private String  updUserId;     //수정자ID

	public AttachVO() {}
	
	public String getAtchFileNo() {
		return atchFileNo;
	}
	public void setAtchFileNo(String atchFileNo) {
		this.atchFileNo = atchFileNo;
	}
	public int getAtchFileSeq() {
		return atchFileSeq;
	}
	public void setAtchFileSeq(int atchFileSeq) {
		this.atchFileSeq = atchFileSeq;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getSaveFileNm() {
		return saveFileNm;
	}
	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}
	public String getFileContType() {
		return fileContType;
	}
	public void setFileContType(String fileContType) {
		this.fileContType = fileContType;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public int getFileDownCnt() {
		return fileDownCnt;
	}
	public void setFileDownCnt(int fileDownCnt) {
		this.fileDownCnt = fileDownCnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getRegUserId() {
		return regUserId;
	}
	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	public String getUpdUserId() {
		return updUserId;
	}
	public void setUpdUserId(String updUserId) {
		this.updUserId = updUserId;
	}
}
