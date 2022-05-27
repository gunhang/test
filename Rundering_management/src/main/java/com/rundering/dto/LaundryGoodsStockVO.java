package com.rundering.dto;

import java.util.Date;

public class LaundryGoodsStockVO {
	private String branchCode; 		//지점코드
	private String articlesCode; 	//세탁물품코드
	private int supplyCount;  		//용품개수
	private int	autoOrderPoint;
	private int	autoOrderCount;
	private String autoOrderYn;
	private Date autoOrderLastDate;
	
	public LaundryGoodsStockVO() {
		super();
	}
	
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getArticlesCode() {
		return articlesCode;
	}
	public void setArticlesCode(String articlesCode) {
		this.articlesCode = articlesCode;
	}
	public int getSupplyCount() {
		return supplyCount;
	}
	public void setSupplyCount(int supplyCount) {
		this.supplyCount = supplyCount;
	}


	public int getAutoOrderPoint() {
		return autoOrderPoint;
	}


	public void setAutoOrderPoint(int autoOrderPoint) {
		this.autoOrderPoint = autoOrderPoint;
	}


	public int getAutoOrderCount() {
		return autoOrderCount;
	}

	public void setAutoOrderCount(int autoOrderCount) {
		this.autoOrderCount = autoOrderCount;
	}


	public String getAutoOrderYn() {
		return autoOrderYn;
	}


	public void setAutoOrderYn(String autoOrderYn) {
		this.autoOrderYn = autoOrderYn;
	}


	public Date getAutoOrderLastDate() {
		return autoOrderLastDate;
	}


	public void setAutoOrderLastDate(Date autoOrderLastDate) {
		this.autoOrderLastDate = autoOrderLastDate;
	}
	
	

	
	
}
