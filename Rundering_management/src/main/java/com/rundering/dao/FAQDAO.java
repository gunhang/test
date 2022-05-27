package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.command.FAQCriteria;
import com.rundering.command.AcoCriteria;
import com.rundering.dto.FAQVO;

public interface FAQDAO {

	List<FAQVO> selectSearchFAQList(FAQCriteria cri) throws SQLException;

	int selectSearchFAQListCount(FAQCriteria cri) throws SQLException;
	
	int selectFAQSequenceNextValue() throws SQLException;
	
	FAQVO selectFAQByFaqno(int faqno) throws SQLException;
	FAQVO selectOriginalFAQByFaqno(int faqno) throws SQLException;
	
	void replyFAQ(FAQVO FAQ) throws SQLException;
	
	/* 아코디언 */
	List<FAQVO> selectFrequentlyList(AcoCriteria cri) throws SQLException;
	
	int selectFrequentlyListCount(AcoCriteria cri) throws SQLException;
	
	void updateFrequently(FAQVO FAQ) throws SQLException;
	
	void deleteFrequently(int faqno) throws SQLException;
	
	// 자주묻는질문 등록(아코디언)
	public void insertQnA(FAQVO faq)throws Exception;
	
	//회원의 문의목록가져오기
	public List<FAQVO> selectFAQListByMemberNo(String memberNo) throws Exception;
	//세탁주문의 문의 가져오기
	public List<FAQVO> selectFAQListByOrderNo(String orderNo);
}
