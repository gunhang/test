package com.rundering.dto;

import java.util.Date;

public class NotificationVO {
	private String ntcnId;           //알림아이디
	private String cnfirmat;         //확인여부
	private String ntcnknd;          //알림종류
	private String ntcncn;           //알림내용
	private String ntcnclickhourUrl; //알림클릭시URL
	private Date occrrncdehour;   	 //발생일시
	private Date cnfirmdehour;  	 //확인일시
	private String employeeId;       //알림받은사원

	
	public NotificationVO() {
		super();
	}

	
	public String getNtcnId() {
		return ntcnId;
	}
	public void setNtcnId(String ntcnId) {
		this.ntcnId = ntcnId;
	}
	public String getCnfirmat() {
		return cnfirmat;
	}
	public void setCnfirmat(String cnfirmat) {
		this.cnfirmat = cnfirmat;
	}
	public String getNtcnknd() {
		return ntcnknd;
	}
	public void setNtcnknd(String ntcnknd) {
		this.ntcnknd = ntcnknd;
	}
	public String getNtcncn() {
		return ntcncn;
	}
	public void setNtcncn(String ntcncn) {
		this.ntcncn = ntcncn;
	}
	public String getNtcnclickhourUrl() {
		return ntcnclickhourUrl;
	}
	public void setNtcnclickhourUrl(String ntcnclickhourUrl) {
		this.ntcnclickhourUrl = ntcnclickhourUrl;
	}
	public Date getOccrrncdehour() {
		return occrrncdehour;
	}
	public void setOccrrncdehour(Date occrrncdehour) {
		this.occrrncdehour = occrrncdehour;
	}
	public Date getCnfirmdehour() {
		return cnfirmdehour;
	}
	public void setCnfirmdehour(Date cnfirmdehour) {
		this.cnfirmdehour = cnfirmdehour;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
}
