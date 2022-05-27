package com.rundering.command;

import java.util.Date;

public class EmployeesCommand {
	private String employeeId;   //사원번호
	private String branchCode;   //지점코드
	private String department;   //부서
	private String position;     //직책
	private Date joiningCompanyDate;  //입사일
	
	private String name;  //이름
	private String phone;  //연락처
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	public Date getJoiningCompanyDate() {
		return joiningCompanyDate;
	}
	public void setJoiningCompanyDate(Date joiningCompanyDate) {
		this.joiningCompanyDate = joiningCompanyDate;
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
	
}
