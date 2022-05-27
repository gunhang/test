package com.rundering.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.AcoCriteria;
import com.rundering.command.AcoPageMaker;
import com.rundering.command.FAQCriteria;
import com.rundering.command.FAQPageMaker;
import com.rundering.dao.AttachDAO;
import com.rundering.dao.FAQDAO;
import com.rundering.dao.MemberDAO;
import com.rundering.dto.AttachVO;
import com.rundering.dto.FAQVO;
import com.rundering.dto.MemberVO;
import com.rundering.util.SensSms;

public class FAQServiceImpl implements FAQService {
	
	private FAQDAO faqDAO;
	
	public void setFaqDAO(FAQDAO faqDAO) {
		this.faqDAO = faqDAO;
	}
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	private SensSms sensSms;
	public void setSensSms(SensSms sensSms) {
		this.sensSms = sensSms;
	}
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}

	@Override
	public Map<String, Object> getFAQList(FAQCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 현재 page 번호에 맞게 리스트를 가져오기
		List<FAQVO> faqList = faqDAO.selectSearchFAQList(cri);

		// 전체 board 개수
		int totalCount = faqDAO.selectSearchFAQListCount(cri);

		// PageMaker 생성.
		FAQPageMaker pageMaker = new FAQPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("faqList", faqList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	
	@Override
	public Map<String, Object> getFAQReply(int faqno) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		FAQVO faq = faqDAO.selectFAQByFaqno(faqno);
		if(faq != null && faq.getAtchFileNo() != null) {
			List<AttachVO> attachList = attachDAO.selectAttachVOByFileNo(faq.getAtchFileNo());
			dataMap.put("attachList", attachList);
		}
		
		dataMap.put("faq", faq);
		
		return dataMap;
	}
	
	@Override
	public void reply(FAQVO faq) throws Exception {
		faqDAO.replyFAQ(faq);
		
		FAQVO faqVO = faqDAO.selectOriginalFAQByFaqno(faq.getFaqno());
		MemberVO memberVO = memberDAO.selectMemberByMemberNo(faqVO.getWriter());
		//고객 문자알림
		try {
		sensSms.sendSMS(memberVO.getPhone().trim(), "[Rundering]\n고객님의 문의에 답변이 등록되었습니다.\n문의내역에서 확인해주세요.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* 아코디언 */	
	@Override
	public Map<String, Object> getFAQFrequentlyList(AcoCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		// 현재 page 번호에 맞게 리스트를 가져오기
		List<FAQVO> frequentlyList = faqDAO.selectFrequentlyList(cri);
		
		// 전체 board 개수
		int totalCount = faqDAO.selectFrequentlyListCount(cri);
		
		// PageMaker 생성.
		AcoPageMaker pageMaker = new AcoPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("frequentlyList", frequentlyList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	
	@Override
	public void modify(FAQVO faq) throws SQLException {
		faqDAO.updateFrequently(faq);
	}

	@Override
	public void remove(int faqno) throws SQLException {
		faqDAO.deleteFrequently(faqno);
	}
	
	// 자주묻는질문 등록(아코디언)
	@Override
	public void insertQnA(FAQVO faq) throws Exception {
		faqDAO.insertQnA(faq);
	}
	
}
