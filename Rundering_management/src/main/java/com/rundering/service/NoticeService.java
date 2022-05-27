package com.rundering.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rundering.command.Criteria;
import com.rundering.command.NoticeModifyCommand;
import com.rundering.command.NoticeRegistCommand;
import com.rundering.dto.AttachVO;
import com.rundering.dto.NoticeVO;

public interface NoticeService {

	// 목록조회
	public Map<String, Object> getNoticeList(Criteria cri) throws SQLException;
	
	// 상세보기
	Map<String, Object> getNotice(int noticeno) throws Exception;
	
	// 수정화면 상세
	Map<String, Object> getNoticeForModify(int noticeno) throws Exception;

	// 등록
	void regist(NoticeRegistCommand notice, List<AttachVO> attachList)throws Exception;
	
	// 수정
	void modify(NoticeModifyCommand notice, List<AttachVO> attachList) throws Exception;

	// 삭제
	void remove(int noticeno) throws Exception;

	Map<String, Object> getNoticeReplyList(String replyno, Criteria cri) throws Exception;
	

}
