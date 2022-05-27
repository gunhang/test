package com.rundering.dto;

import java.util.Date;

public class MemberAddressVO {
	private int addressNo; 		//주소번호
	private String add1;  		//주소
	private String add2;  		//상세주소
	private String zip; 		//우편번호
	private Date registDate;  	//등록일
	private Date modifyDate;  	//수정일
	private String defaultYn; 	//기본주소지여부
	private String memberNo; 	//회원고유번호
	private String area;	 	//지역분류코드
	private String toparea;  	//상위지역분류
	
	
	public MemberAddressVO() {
		super();
	}


	public int getAddressNo() {
		return addressNo;
	}
	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
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
	public String getDefaultYn() {
		return defaultYn;
	}
	public void setDefaultYn(String defaultYn) {
		this.defaultYn = defaultYn;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getToparea() {
		return toparea;
	}
	public void setToparea(String toparea) {
		this.toparea = toparea;
	}
	
}
