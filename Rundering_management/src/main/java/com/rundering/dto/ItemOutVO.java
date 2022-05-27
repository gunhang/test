package com.rundering.dto;

import java.util.Date;

public class ItemOutVO {
	
	private String articlesCode;
	private String outItemCode;
	private Date outDate;
	private String branchCode;
	private int itemcount;
	
	
	
	public ItemOutVO() {
	}
	public String getArticlesCode() {
		return articlesCode;
	}
	public void setArticlesCode(String articlesCode) {
		this.articlesCode = articlesCode;
	}
	public String getOutItemCode() {
		return outItemCode;
	}
	public void setOutItemCode(String outItemCode) {
		this.outItemCode = outItemCode;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public int getItemcount() {
		return itemcount;
	}
	public void setItemcount(int itemcount) {
		this.itemcount = itemcount;
	}
	
	
}
