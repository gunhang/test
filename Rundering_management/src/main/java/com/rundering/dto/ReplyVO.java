package com.rundering.dto;

import java.util.Date;

public class ReplyVO {
	private int replyno;           //댓글번호
	private String replyContent;   //댓글내용
	private String memberno;       //작성자
	private Date registDate;       //등록일
	private Date modifyDate;       //수정일
	private int replynoSeq;  	   //댓글순번
	private String atchFileNo;     //통합첨부파일번호
	private String deleteYn;    	//삭제여부
	
	
	private int count = 0;  
	public ReplyVO() {
		super();
	}
	public int getReplyno() {
		return replyno;
	}
	public void setReplyno(int replyno) {
		this.replyno = replyno;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getMemberno() {
		return memberno;
	}
	public void setMemberno(String memberno) {
		this.memberno = memberno;
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
	public int getReplynoSeq() {
		return replynoSeq;
	}
	public void setReplynoSeq(int replynoSeq) {
		this.replynoSeq = replynoSeq;
	}
	public String getAtchFileNo() {
		return atchFileNo;
	}
	public void setAtchFileNo(String atchFileNo) {
		this.atchFileNo = atchFileNo;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
