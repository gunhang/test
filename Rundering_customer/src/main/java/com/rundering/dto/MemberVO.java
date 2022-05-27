package com.rundering.dto;

import java.util.Date;

public class MemberVO {
	private String memberNo;			//회원고유번호
	private String id;					//아이디
	private String password;			//비밀번호
	private String name;				//이름
	private String phone;				//연락처
	private String email;				//이메일
	private Date registDate;			//등록일자
	private Date modifyDate;			//수정일자
	private int enableWhether;			//활성화여부
	private String deleteYn;			//탈퇴여부
    private Date lastLogpsnHourLiver;	//마지막로그인
    private int loginfailCount;			//로그인실패횟수
    private String employeeIdYn;		//사원여부
    
    
	public MemberVO() {
		super();
	}


	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getEnableWhether() {
		return enableWhether;
	}
	public void setEnableWhether(int enableWhether) {
		this.enableWhether = enableWhether;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public Date getLastLogpsnHourLiver() {
		return lastLogpsnHourLiver;
	}
	public void setLastLogpsnHourLiver(Date lastLogpsnHourLiver) {
		this.lastLogpsnHourLiver = lastLogpsnHourLiver;
	}
	public int getLoginfailCount() {
		return loginfailCount;
	}
	public void setLoginfailCount(int loginfailCount) {
		this.loginfailCount = loginfailCount;
	}
	public String getEmployeeIdYn() {
		return employeeIdYn;
	}
	public void setEmployeeIdYn(String employeeIdYn) {
		this.employeeIdYn = employeeIdYn;
	}
	
}