package com.rundering.command;

public class AcoCriteria {
	
	private int page=1;
	private int perPageNumFAQ = 5;
	private String searchType="";
	private String keyword="";
	private String memberNo;
	
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	private int startRowNum=0;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page<1) {
			this.page=1;
		}else {
			this.page = page;
		}
		setStartRowNum();
	}

	public int getPerPageNumFAQ() {
		return perPageNumFAQ;
	}

	public void setPerPageNumFAQ(int perPageNumFAQ) {
		if(perPageNumFAQ < 1) {
			this.perPageNumFAQ = 5;
		}else {
			this.perPageNumFAQ = perPageNumFAQ;
		}
		setStartRowNum();
	}

	public String getSearchType() {
		return searchType;
	}
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	private void setStartRowNum() {
		this.startRowNum=(this.page-1)*perPageNumFAQ;		
	}
	public int getStartRowNum() {
		return this.startRowNum;
	}
	
}
