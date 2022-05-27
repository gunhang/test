package com.rundering.command;

import java.util.List;

import com.rundering.dto.NoticeVO;

public class NoticeModifyCommand extends NoticeRegistCommand{
	private String noticeno;
	private List<String> deleteFile;
	public String getNoticeno() {
		return noticeno;
	}
	public void setNoticeno(String noticeno) {
		this.noticeno = noticeno;
	}
	public List<String> getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(List<String> deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	@Override
	public NoticeVO toNoticeVO() {
		NoticeVO noticeVO = super.toNoticeVO();
		noticeVO.setNoticeno(Integer.parseInt(this.noticeno));
		return noticeVO;
	}

}
