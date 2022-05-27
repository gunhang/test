package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.AcoCriteria;
import com.rundering.command.FAQCriteria;
import com.rundering.dto.FAQVO;

public class FAQDAOImpl implements FAQDAO {
	SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<FAQVO> selectSearchFAQList(FAQCriteria cri) throws SQLException {

		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNumQuestion();
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<FAQVO> faqList = session.selectList("FAQ-mapper.selectSearchFAQList", cri, rowBounds);
		return faqList;
	}

	@Override
	public int selectSearchFAQListCount(FAQCriteria cri) throws SQLException {
		return session.selectOne("FAQ-mapper.selectSearchFAQListCount", cri);
	}

	@Override
	public int selectFAQSequenceNextValue() throws SQLException {
		return session.selectOne("FAQ-mapper.selecFAQSequenceNextValue");
	}
	
	@Override
	public FAQVO selectFAQByFaqno(int faqno) throws SQLException {
		return session.selectOne("FAQ-mapper.selectFAQByFaqno", faqno);
	}
	
	@Override
	public void replyFAQ(FAQVO FAQ) throws SQLException {
		session.update("FAQ-mapper.replyFAQ", FAQ);
	}
	
	/* 아코디언 */	
	@Override
	public List<FAQVO> selectFrequentlyList(AcoCriteria cri) throws SQLException {

		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNumFAQ();
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<FAQVO> frequentlyList = session.selectList("FAQ-mapper.selectFrequentlyList", cri, rowBounds);
		return frequentlyList;
	}
	
	@Override
	public int selectFrequentlyListCount(AcoCriteria cri) throws SQLException {
		return session.selectOne("FAQ-mapper.selectFrequentlyListCount", cri);
	}

	@Override
	public void updateFrequently(FAQVO FAQ) throws SQLException {
		session.update("FAQ-mapper.updateFrequently", FAQ);
	}	
	
	@Override
	public void deleteFrequently(int faqno) throws SQLException {
		session.update("FAQ-mapper.deleteFrequently", faqno);
	}
	
	// 자주묻는질문 등록(아코디언)
	@Override
	public void insertQnA(FAQVO faq) throws Exception {
		session.update("FAQ-mapper.insertQnA", faq);
	}

	@Override
	public FAQVO selectOriginalFAQByFaqno(int faqno) throws SQLException {
		return session.selectOne("FAQ-mapper.selectOriginalFAQByFaqno", faqno);
	}

	@Override
	public List<FAQVO> selectFAQListByMemberNo(String memberNo) throws Exception {
		return session.selectList("FAQ-mapper.selectFAQListByMemberNo", memberNo);
	}

	@Override
	public List<FAQVO> selectFAQListByOrderNo(String orderNo) {
		return session.selectList("FAQ-mapper.selectFAQListByOrderNo", orderNo);
	}

}
