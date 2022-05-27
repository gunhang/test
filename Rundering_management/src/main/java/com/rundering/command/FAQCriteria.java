package com.rundering.command;

public class FAQCriteria {
	
	private int page=1;
	private int perPageNumQuestion = 10;
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

	public int getPerPageNumQuestion() {
		return perPageNumQuestion;
	}

	public void setPerPageNumQuestion(int perPageNumQuestion) {
		if(perPageNumQuestion < 1) {
			this.perPageNumQuestion = 10;
		}else {
			this.perPageNumQuestion = perPageNumQuestion;
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
		this.startRowNum=(this.page-1)*perPageNumQuestion;		
	}
	public int getStartRowNum() {
		return this.startRowNum;
	}
	
}
