package com.rundering.command;

import java.util.List;

import com.rundering.dto.FAQVO;

public class FAQModifyCommand extends FAQRegistCommand{
	private String faqno;
	private List<String> deleteFile;
	
	public String getFaqno() {
		return faqno;
	}
	public void setFaqno(String faqno) {
		this.faqno = faqno;
	}
	public List<String> getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(List<String> deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	@Override
	public FAQVO toFAQVO() {
		FAQVO faqvo = super.toFAQVO();
		faqvo.setFaqno(Integer.parseInt(this.faqno));
		return faqvo;
	}
	
	
	
}
