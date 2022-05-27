package com.rundering.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rundering.dto.NoticeVO;

public class NoticeRegistCommand {
	private String title;       //제목
	private String content;     //내용
	private String employeeId;  //작성자
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
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(List<MultipartFile> uploadFile) {
		this.uploadFile = uploadFile;
	}

	public NoticeVO toNoticeVO(){
		NoticeVO notice = new NoticeVO();
		notice.setTitle(this.title);
		notice.setContent(this.content);  
		notice.setEmployeeId(this.employeeId);
		
		return notice;
	}
	
}
