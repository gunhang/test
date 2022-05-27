package com.rundering.command;

import java.util.List;

import com.rundering.dto.SuggestVO;

public class SuggestModifyCommand extends SuggestRegistCommand {
	private String sno;
	private List<String> deleteFile;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public List<String> getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(List<String> deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	@Override
	public SuggestVO toSuggestVO() {
		SuggestVO suggestVO = super.toSuggestVO();
		suggestVO.setSno(Integer.parseInt(sno));
		return suggestVO;
	}

}
