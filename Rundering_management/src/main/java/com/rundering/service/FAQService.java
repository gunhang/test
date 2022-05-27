package com.rundering.service;

import java.sql.SQLException;
import java.util.Map;

import com.rundering.command.AcoCriteria;
import com.rundering.command.FAQCriteria;
import com.rundering.dto.FAQVO;

public interface FAQService {
	
	// 목록조회
	public Map<String, Object> getFAQList(FAQCriteria cri) throws SQLException;
	
	// 답글화면 상세
	Map<String, Object> getFAQReply(int faqno) throws Exception;
	
	// 답글
	void reply(FAQVO faq) throws Exception;
	
	/* 아코디언 */
	public Map<String, Object> getFAQFrequentlyList(AcoCriteria cri) throws SQLException;
	
	// 아코디언 수정
	void modify(FAQVO faq) throws SQLException;
	
	// 아코디언 삭제
	void remove(int faqno) throws SQLException;
	
	// 자주묻는질문 등록(아코디언)
	public void insertQnA(FAQVO faq) throws Exception;
		
}