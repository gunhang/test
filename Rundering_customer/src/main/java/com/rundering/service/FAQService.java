package com.rundering.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rundering.command.FAQModifyCommand;
import com.rundering.command.FAQRegistCommand;
import com.rundering.command.MyOrderCriteria;
import com.rundering.dto.AttachVO;
import com.rundering.dto.FAQVO;

public interface FAQService {
	
	// 목록조회
	public Map<String, Object> getFAQList(MyOrderCriteria cri) throws SQLException;

	// 등록
	void regist(FAQRegistCommand faqcmd, List<AttachVO> attachList) throws Exception;
	
	// 수정화면 상세
	Map<String, Object> getFAQModify(int faqno) throws Exception;
	
	// 주문번호
	public Map<String, Object> getOrderList(String memberNo) throws SQLException;
	
	// 수정
	void modify(FAQModifyCommand faqcmd, List<AttachVO> attachList) throws Exception;
	
	// 삭제
	void remove(int faqno) throws Exception;
	
	/* 아코디언 */
	public Map<String, Object> getFAQFrequentlyList(MyOrderCriteria cri) throws SQLException;
	
	// 내 목록조회
	public Map<String, Object> getMyFAQList(MyOrderCriteria cri) throws SQLException;
}
