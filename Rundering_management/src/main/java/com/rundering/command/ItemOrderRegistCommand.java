package com.rundering.command;

import java.util.ArrayList;
import java.util.List;

import com.rundering.dto.ItemOrderDetailVO;
import com.rundering.dto.ItemOrderVO;

public class ItemOrderRegistCommand {
	private String[] code; 		   	
	private String totalprice; 	
	private String[] price;    	
	private String[] quantity;
	public String[] getCode() {
		return code;
	}
	public void setCode(String[] code) {
		this.code = code;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String[] getPrice() {
		return price;
	}
	public void setPrice(String[] price) {
		this.price = price;
	}
	 		
	public String[] getQuantity() {
		return quantity;
	}
	public void setQuantity(String[] quantity) {
		this.quantity = quantity;
	}
	public ItemOrderVO itemOrderVO() {
		ItemOrderVO itemOrder=new ItemOrderVO();
		
		itemOrder.setItemOrderPaymentPrice(Integer.parseInt(totalprice));
		
		return itemOrder;
		
	}
	public List<ItemOrderDetailVO> itemOrderDetail() {
		List<ItemOrderDetailVO> itemOrderDetailList= new ArrayList<ItemOrderDetailVO>();
		for(int i = 0 ; i < code.length ; i++) {
			ItemOrderDetailVO itemOrderDetail = new ItemOrderDetailVO();
			itemOrderDetail.setPrice(Integer.parseInt(price[i])); 
			itemOrderDetail.setOrderCount(Integer.parseInt(quantity[i]));
			itemOrderDetail.setArticlesCode(code[i]);
			itemOrderDetailList.add(itemOrderDetail);
		}
		return itemOrderDetailList;
	}
	
	
}
