package com.rundering.dto;

import java.util.Date;

public class BranchVO {
	private String branchCode; 			//지점코드
	private String branchName;			//지점명
	private String zip; 				//우편번호
	private String add1;				//주소
	private String add2;				//상세주소
	private int branchLndrpcrymslmcoqy; //세탁가능수량
	private Date branchRegistDate; 		//등록일
	private String branchContact; 		//연락처
	private Date branchModifyDate; 		//수정일
	private String deleteYN; 			//삭제여부
	private String toparea; 			//상위지역분류
	private String area;				//지역분류
	
	public BranchVO() {
		super();
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public int getBranchLndrpcrymslmcoqy() {
		return branchLndrpcrymslmcoqy;
	}

	public void setBranchLndrpcrymslmcoqy(int branchLndrpcrymslmcoqy) {
		this.branchLndrpcrymslmcoqy = branchLndrpcrymslmcoqy;
	}

	public Date getBranchRegistDate() {
		return branchRegistDate;
	}

	public void setBranchRegistDate(Date branchRegistDate) {
		this.branchRegistDate = branchRegistDate;
	}

	public String getBranchContact() {
		return branchContact;
	}

	public void setBranchContact(String branchContact) {
		this.branchContact = branchContact;
	}

	public Date getBranchModifyDate() {
		return branchModifyDate;
	}

	public void setBranchModifyDate(Date branchModifyDate) {
		this.branchModifyDate = branchModifyDate;
	}

	public String getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}

	public String getToparea() {
		return toparea;
	}

	public void setToparea(String toparea) {
		this.toparea = toparea;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "BranchVO [branchCode=" + branchCode + ", branchName=" + branchName + ", zip=" + zip + ", add1=" + add1
				+ ", add2=" + add2 + ", branchLndrpcrymslmcoqy=" + branchLndrpcrymslmcoqy + ", branchRegistDate="
				+ branchRegistDate + ", branchContact=" + branchContact + ", branchModifyDate=" + branchModifyDate
				+ ", deleteYN=" + deleteYN + ", toparea=" + toparea + ", area=" + area + "]";
	}

}
