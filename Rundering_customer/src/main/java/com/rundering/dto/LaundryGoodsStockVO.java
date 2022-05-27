package com.rundering.dto;

public class LaundryGoodsStockVO {
	private String branchCode; 		//지점코드
	private String articlesCode; 	//세탁물품코드
	private int supplyCount;  		//용품개수
	
	
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

	
	
}
