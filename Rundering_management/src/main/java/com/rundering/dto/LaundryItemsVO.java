package com.rundering.dto;

import java.util.Date;

public class LaundryItemsVO {
	private String laundryItemsCode;  	//세탁품목코드
	private int price;  				//가격
	private String itemsName; 			//품목명
	private Date registDate;  			//등록일
	private Date modifyDate;  			//수정일
	private String deleteYn; 			//삭제여부
	private String charger;  			//담당자
	private String laundryCategory; 	//세탁품목구분
	
	
	public LaundryItemsVO() {
		super();
	}

	
	public String getLaundryItemsCode() {
		return laundryItemsCode;
	}
	public void setLaundryItemsCode(String laundryItemsCode) {
		this.laundryItemsCode = laundryItemsCode;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
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
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getCharger() {
		return charger;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public String getLaundryCategory() {
		return laundryCategory;
	}
	public void setLaundryCategory(String laundryCategory) {
		this.laundryCategory = laundryCategory;
	}
	
	
}
