package com.rundering.dto;

public class ItemOrderDetailVO {
	private String ordercode;       //발주코드
	private String articlesName;
	private String articlesCode;    //세탁물품코드
	private int seq; 				//순번
	private int orderCount;         //발주개수
	private int price;
	
	public ItemOrderDetailVO() {
		super();
	}
	

	public ItemOrderDetailVO(String ordercode, String articlesCode, int seq, int orderCount, int price) {
		super();
		this.ordercode = ordercode;
		this.articlesCode = articlesCode;
		this.seq = seq;
		this.orderCount = orderCount;
		this.price = price;
	}


	public String getOrdercode() {
		return ordercode;
	}


	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}


	public String getArticlesCode() {
		return articlesCode;
	}


	public void setArticlesCode(String articlesCode) {
		this.articlesCode = articlesCode;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public int getOrderCount() {
		return orderCount;
	}


	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getArticlesName() {
		return articlesName;
	}


	public void setArticlesName(String articlesName) {
		this.articlesName = articlesName;
	}
	
	
	
}
