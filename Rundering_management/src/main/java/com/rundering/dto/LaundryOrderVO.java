package com.rundering.dto;

import java.util.Date;

public class LaundryOrderVO {
   
	private String orderNo;             //주문번호
	private String requestDetails;      //요청사항
	private Date orderDate;             //주문일시
	private String contactNumber;       //배송연락처
	private Date deliveryRequestDate;   //배송요청일
	private int totalPrice;             //주문총가격
	private Date pickupRequestDate;     //수거요청일
	private Date pickupDate;            //실제수거날짜
	private Date branchArrivalDate;     //지점도착날짜
	private String area;                //지역분류코드
	private Date deliveryDate;          //배송완료일자
	private String orderStatus;         //주문상태코드
	private String cancelYn;            //취소여부
	private String paymentNo;           //결제번호
	private String branchCode;          //지점코드
	private String memberNo;            //회원고유번호
	private String deliveryEmployeeId;  //배송담당기사
	private String pickupEmployeeId;    //수거담당기사
	private String zip;					//우편번호
	private String add1;				//주소
	private String add2;				//상세주소
	private String atchFileNo;			//통합첨부파일번호
	private int replyNo;				//댓글번호
	private String pickupDate1;			//Date 타입 변환
	
	private String phone;				//받아올 고객 연락처
	private String picture;				//사진이름 받아오는 변수
	
	public LaundryOrderVO() {
		super();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRequestDetails() {
		return requestDetails;
	}
	public void setRequestDetails(String requestDetails) {
		this.requestDetails = requestDetails;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Date getDeliveryRequestDate() {
		return deliveryRequestDate;
	}
	public void setDeliveryRequestDate(Date deliveryRequestDate) {
		this.deliveryRequestDate = deliveryRequestDate;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getPickupRequestDate() {
		return pickupRequestDate;
	}
	public void setPickupRequestDate(Date pickupRequestDate) {
		this.pickupRequestDate = pickupRequestDate;
	}
	public Date getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}
	public Date getBranchArrivalDate() {
		return branchArrivalDate;
	}
	public void setBranchArrivalDate(Date branchArrivalDate) {
		this.branchArrivalDate = branchArrivalDate;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getCancelYn() {
		return cancelYn;
	}
	public void setCancelYn(String cancelYn) {
		this.cancelYn = cancelYn;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getDeliveryEmployeeId() {
		return deliveryEmployeeId;
	}
	public void setDeliveryEmployeeId(String deliveryEmployeeId) {
		this.deliveryEmployeeId = deliveryEmployeeId;
	}
	public String getPickupEmployeeId() {
		return pickupEmployeeId;
	}
	public void setPickupEmployeeId(String pickupEmployeeId) {
		this.pickupEmployeeId = pickupEmployeeId;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getAtchFileNo() {
		return atchFileNo;
	}
	public void setAtchFileNo(String atchFileNo) {
		this.atchFileNo = atchFileNo;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getPickupDate1() {
		return pickupDate1;
	}

	public void setPickupDate1(String pickupDate1) {
		this.pickupDate1 = pickupDate1;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "LaundryOrderVO [orderNo=" + orderNo + ", requestDetails=" + requestDetails + ", orderDate=" + orderDate
				+ ", contactNumber=" + contactNumber +", pickupRequestDate=" + pickupRequestDate + ", deliveryRequestDate=" + deliveryRequestDate + ", totalPrice="
				+ totalPrice + ", area=" + area + ", orderStatus=" + orderStatus + ", memberNo=" + memberNo + ", zip=" + zip + ", add1=" + add1 + ", add2=" + add2
				;
	}
	
	
	
}
