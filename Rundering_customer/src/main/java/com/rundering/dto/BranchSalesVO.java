package com.rundering.dto;

import java.util.Date;

public class BranchSalesVO {	
	private String branchCode;	 //지점코드
	private Date date;			 //날짜
	private int salesAmount; 	//매출액
	
	
	public BranchSalesVO() {
		super();
	}
	
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
	}
	
	
}
