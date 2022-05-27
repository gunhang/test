package com.rundering.dto;

import java.util.Date;

public class ItemVO {
	private String branchCode; 		//지점코드
	private String articlesCode; 	//세탁물품코드
	private int supplyCount;  		//용품개수
	private String articlesName; 	// 물품명
	private String clcode; 			// 분류코드
	private String each; 			// 단위
	private int	autoOrderPoint;		//자동발주 포인트
	private int	autoOrderCount;     //자동발주 개수
	private int number;
	private int number2;
	private int sum;
	private String day; 
	private String autoOrderYn;
	private Date autoOrderLastDate;
	
	
	public int getNumber2() {
		return number2;
	}
	public void setNumber2(int number2) {
		this.number2 = number2;
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
	public String getArticlesName() {
		return articlesName;
	}
	public void setArticlesName(String articlesName) {
		this.articlesName = articlesName;
	}
	public String getClcode() {
		return clcode;
	}
	public void setClcode(String clcode) {
		this.clcode = clcode;
	}
	public String getEach() {
		return each;
	}
	public void setEach(String each) {
		this.each = each;
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
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
