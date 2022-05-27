package com.rundering.command;

import java.util.List;

public class AdminLaundryOrderListCriteria extends Criteria{
	
	private String orderStatus;
	private List<String> listOrderStatus;
	private String pickupRequestDate;
	private String branchCode;
	private String area;
	private String orderNo;
	private String selectOrderNo;
	private List<String> listSelectOrderNo;
	private String selectAllOrderNo;
	
	private String startDate;
	private String endDate;

	public String getPickupRequestDate() {
		return pickupRequestDate;
	}
	public void setPickupRequestDate(String pickupRequestDate) {
		this.pickupRequestDate = pickupRequestDate;
	}
	
	private void setDate() {
		if(this.pickupRequestDate != null && !this.pickupRequestDate.isEmpty()) {
			String[] split = this.pickupRequestDate.split("~");
			if(split[0].equals(split[1])) {
				this.startDate = split[0];
				this.endDate = split[0];
			}else {
				this.startDate = split[0];
				this.endDate = split[1];
			}
		}
	}
	
	public String getStartDate() {
		setDate();
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<String> getListOrderStatus() {
		return listOrderStatus;
	}
	public void setListOrderStatus(List<String> listOrderStatus) {
		this.listOrderStatus = listOrderStatus;
	}
	public String getSelectOrderNo() {
		return selectOrderNo;
	}
	public void setSelectOrderNo(String selectOrderNo) {
		this.selectOrderNo = selectOrderNo;
	}
	public List<String> getListSelectOrderNo() {
		return listSelectOrderNo;
	}
	public void setListSelectOrderNo(List<String> listSelectOrderNo) {
		this.listSelectOrderNo = listSelectOrderNo;
	}
	public String getSelectAllOrderNo() {
		return selectAllOrderNo;
	}
	public void setSelectAllOrderNo(String selectAllOrderNo) {
		this.selectAllOrderNo = selectAllOrderNo;
	}
	
	
	
	
	
	
	
}






