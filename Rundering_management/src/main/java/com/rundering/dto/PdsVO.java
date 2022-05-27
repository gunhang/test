package com.rundering.dto;

import java.util.Date;

public class PdsVO {
	private int pdsnno;        //글번호
	private String title;      //제목
	private String wrter;      //작성자
	private String content;    //내용
	private int views;         //조회수
	private Date registDate;   //등록일
	private Date modifyDate;   //수정일
	private String deleteYn;   //삭제여부
	private String atchFileno; //통합첨부파일번호
	
	
	public PdsVO() {
		super();
	}
	
	
	public int getPdsnno() {
		return pdsnno;
	}
	public void setPdsnno(int pdsnno) {
		this.pdsnno = pdsnno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWrter() {
		return wrter;
	}
	public void setWrter(String wrter) {
		this.wrter = wrter;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
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
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getAtchFileno() {
		return atchFileno;
	}
	public void setAtchFileno(String atchFileno) {
		this.atchFileno = atchFileno;
	}
	
}
