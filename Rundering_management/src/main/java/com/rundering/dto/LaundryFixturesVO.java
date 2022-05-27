package com.rundering.dto;

import java.util.Date;

public class LaundryFixturesVO {

	private String fixturesCode; // 비품코드
	private Date registDate; // 등록일
	private String status; // 상태
	private Date dispositionDate; // 처분일
	private String branchCode; // 지점코드
	private String articlesCode; // 세탁물품코드
	private String fixturesName; // 물품호칭

	public LaundryFixturesVO() {
		super();
	}

	public String getFixturesCode() {
		return fixturesCode;
	}

	public void setFixturesCode(String fixturesCode) {
		this.fixturesCode = fixturesCode;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDispositionDate() {
		return dispositionDate;
	}

	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
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

	public String getFixturesName() {
		return fixturesName;
	}

	public void setFixturesName(String fixturesName) {
		this.fixturesName = fixturesName;
	}

}
