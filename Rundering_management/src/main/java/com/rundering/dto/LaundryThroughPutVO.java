package com.rundering.dto;

import java.util.Date;

public class LaundryThroughPutVO {
	private String throughputNo;   	//처리번호
	private int laundryQuota;      	//할당세탁량
	private int totalThroughput;   	//총세탁처리량
	private int overshoot;         	//초과량
	private String area;			//지역분류코드
	private Date date;             	//날짜
	private String branchCode;     	//지점코드
	private String quotaPercent;			//  할당세탁량/총세탁처리량=?%
	private String branchName;				//지점명
	private String name;
	
	public LaundryThroughPutVO() {
		super();
	}


	public String getThroughputNo() {
		return throughputNo;
	}
	public void setThroughputNo(String throughputNo) {
		this.throughputNo = throughputNo;
	}
	public int getLaundryQuota() {
		return laundryQuota;
	}
	public void setLaundryQuota(int laundryQuota) {
		this.laundryQuota = laundryQuota;
	}
	public int getTotalThroughput() {
		return totalThroughput;
	}
	public void setTotalThroughput(int totalThroughput) {
		this.totalThroughput = totalThroughput;
	}
	public int getOvershoot() {
		return overshoot;
	}
	public void setOvershoot(int overshoot) {
		this.overshoot = overshoot;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getQuotaPercent() {
		return quotaPercent;
	}
	public void setQuotaPercent(String quotaPercent) {
		this.quotaPercent = quotaPercent;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
