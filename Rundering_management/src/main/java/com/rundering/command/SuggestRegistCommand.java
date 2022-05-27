package com.rundering.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rundering.dto.SuggestVO;

public class SuggestRegistCommand {
	private String title; // 제목
	private String content; // 내용
	private String writer; // 작성자
	private String branchName; // 지점명
	private List<MultipartFile> uploadFile; //첨부파일
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(List<MultipartFile> uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public SuggestVO toSuggestVO() {
		SuggestVO suggest = new SuggestVO();
		suggest.setTitle(this.title);
		suggest.setContent(this.content);
		suggest.setWriter(this.writer);
		suggest.setBranchName(this.branchName);
		
		return suggest;
	}
	
}
