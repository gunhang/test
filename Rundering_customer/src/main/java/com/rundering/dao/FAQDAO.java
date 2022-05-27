package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.command.MyOrderCriteria;
import com.rundering.dto.FAQVO;

public interface FAQDAO {
	

	/* 일반 문의 */
	List<FAQVO> selectSearchFAQList(MyOrderCriteria cri) throws SQLException;

	int selectSearchFAQListCount(MyOrderCriteria cri) throws SQLException;
	
	int selectFAQSequenceNextValue() throws SQLException;
	
	void insertFAQ(FAQVO FAQ) throws SQLException;
	
	FAQVO selectFAQByFaqno(int faqno) throws SQLException;
	
	void updateFAQ(FAQVO FAQ) throws SQLException;
	//첨부파일번호 업데이트
	void updateFAQAtchFileNo(FAQVO FAQ) throws SQLException;
	//주문번호 업데이트
	void updateFAQOrderNo(FAQVO FAQ) throws SQLException;
	
	void deleteFAQ(int faqno) throws SQLException;
	
	/* 주문번호 */
	List<FAQVO> selectOrderno(String memberNo) throws SQLException;
	
	/* 아코디언 */
	List<FAQVO> selectFrequentlyList(MyOrderCriteria cri) throws SQLException;
	
	int selectFrequentlyListCount(MyOrderCriteria cri) throws SQLException;

	/* 마이페이지 문의 */
	//내 문의 리스트 가져오기
	List<FAQVO> MypageFAQList(MyOrderCriteria cri) throws SQLException;
	//페이징 처리
	int MypageFAQListCount(MyOrderCriteria cri) throws SQLException;
}
