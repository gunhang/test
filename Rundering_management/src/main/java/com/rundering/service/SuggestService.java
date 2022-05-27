package com.rundering.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rundering.command.Criteria;
import com.rundering.command.SuggestModifyCommand;
import com.rundering.command.SuggestRegistCommand;
import com.rundering.dto.AttachVO;
import com.rundering.dto.SuggestVO;

public interface SuggestService {

	// 목록조회
	public Map<String, Object> getSuggestList(Criteria cri) throws SQLException;

	// 상세보기
	Map<String, Object> getSuggest(int sno) throws Exception;
	
	// 확인미확인
	SuggestVO getCheck(int sno) throws SQLException;

	// 수정화면 상세
	Map<String, Object> getSuggestModify(int sno) throws Exception;

	// 등록
	void regist(SuggestRegistCommand suggestcmd, List<AttachVO> attachList) throws Exception;

	// 수정
	void modify(SuggestModifyCommand suggestcmd, List<AttachVO> attachList) throws Exception;

	// 삭제
	void remove(int sno) throws Exception;

}
