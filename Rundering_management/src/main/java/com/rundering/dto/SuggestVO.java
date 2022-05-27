package com.rundering.dto;

import java.sql.Date;

public class SuggestVO {
	private int sno; // 글번호
	private String title; // 제목
	private String content; // 내용
	private Date registDate; // 등록일
	private Date modifyDate; // 수정일
	private String deleteyn; // 삭제키(N유지/Y삭제)
	private int viewcnt; // 조회수
	private String writer; // 작성자
	private String branchName; // 지점명
	private String checkyn; // 확인여부
	private String atchFileNo;			//통합첨부파일번호

	public SuggestVO() {
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getDeleteyn() {
		return deleteyn;
	}

	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCheckyn() {
		return checkyn;
	}

	public void setCheckyn(String checkyn) {
		this.checkyn = checkyn;
	}
	

	public String getAtchFileNo() {
		return atchFileNo;
	}

	public void setAtchFileNo(String atchFileNo) {
		this.atchFileNo = atchFileNo;
	}

	@Override
	public String toString() {
		return "SuggestVO [sno=" + sno + ", title=" + title + ", content=" + content + ", registDate=" + registDate
				+ ", modifyDate=" + modifyDate + ", deleteyn=" + deleteyn + ", viewcnt=" + viewcnt + ", writer="
				+ writer + ", branchName=" + branchName + ", checkyn=" + checkyn + "]";
	}

}
