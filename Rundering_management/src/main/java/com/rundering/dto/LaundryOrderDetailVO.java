package com.rundering.dto;

public class LaundryOrderDetailVO {
	private String detailOrderno; 		//상세주문번호
	private String orderNo;  			//주문번호
	private int price;    				//가격
	private String laundryItemsCode;  	//세탁품목코드
	private int quantity; 				//개수
	private String itemsName;			//세탁품목명
	
	
	public LaundryOrderDetailVO() {
		super();
	}


	public String getDetailOrderno() {
		return detailOrderno;
	}
	public void setDetailOrderno(String detailOrderno) {
		this.detailOrderno = detailOrderno;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getLaundryItemsCode() {
		return laundryItemsCode;
	}
	public void setLaundryItemsCode(String laundryItemsCode) {
		this.laundryItemsCode = laundryItemsCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	
}
