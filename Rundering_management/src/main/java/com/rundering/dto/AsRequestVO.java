package com.rundering.dto;

import java.sql.Date;

public class AsRequestVO {
	private int asno; // AS번호
	private String fixturesCode; // 비품코드
	private String title; // 제목
	private String ascontent; // 내용
	private Date registDate; // 등록일
	private String checkyn; // 확인여부
	private Date modifyDate; // 수정일
	private String deleteyn; // 삭제여부
	private String employeeId; // 담당자
	private String branchCode; // 지점코드
	private String articlesCode; // 세탁물품코드
	private String objectname; // 물품명
	private String requestDate; // 고장발생일
	private String fixturesName; // 물품호칭

	public AsRequestVO() {
		super();
	}

	public String getObjectname() {
		return objectname;
	}

	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	public int getAsno() {
		return asno;
	}

	public void setAsno(int asno) {
		this.asno = asno;
	}

	public String getFixturesCode() {
		return fixturesCode;
	}

	public void setFixturesCode(String fixturesCode) {
		this.fixturesCode = fixturesCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAscontent() {
		return ascontent;
	}

	public void setAscontent(String ascontent) {
		this.ascontent = ascontent;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getCheckyn() {
		return checkyn;
	}

	public void setCheckyn(String checkyn) {
		this.checkyn = checkyn;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getDeleteyn() {
		return deleteyn;
	}

	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getFixturesName() {
		return fixturesName;
	}

	public void setFixturesName(String fixturesName) {
		this.fixturesName = fixturesName;
	}

}
