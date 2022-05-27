package com.rundering.dto;

import java.util.Date;

public class AttachVO {

	private String  atchFileNo;
	private int  atchFileSeq;
	private String  bizType;
	private String  filePath;
	private String  fileNm;
	private String  saveFileNm;
	private String  fileContType;
	private Long  fileSize;
	private int  fileDownCnt;
	private Date  regDate;
	private String  regUserId;
	private Date  updDate;
	private String  updUserId;

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
