package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.Criteria;
import com.rundering.dto.SuggestVO;

public class SuggestDAOImpl implements SuggestDAO {
	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<SuggestVO> selectSearchSuggestList(Criteria cri) throws SQLException {

		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<SuggestVO> suggestList = session.selectList("Suggest-mapper.selectSearchSuggestList", cri, rowBounds);
		return suggestList;
	}

	@Override
	public int selectSearchSuggestListCount(Criteria cri) throws SQLException {
		return session.selectOne("Suggest-mapper.selectSearchSuggestListCount", cri);
	}

	@Override
	public SuggestVO selectSuggestBySno(int sno) throws SQLException {
		return session.selectOne("Suggest-mapper.selectSuggestBySno", sno);
	}

	@Override
	public void increaseViewCount(int sno) throws SQLException {
		session.update("Suggest-mapper.increaseViewCount", sno);
	}

	@Override
	public void checkSuggest(int sno) throws SQLException {
		session.update("Suggest-mapper.checkSuggest", sno);
	}

	@Override
	public int selectSuggestSequenceNextValue() throws SQLException {
		return session.selectOne("Suggest-mapper.selectSuggestSequenceNextValue");
	}

	@Override
	public void insertSuggest(SuggestVO Suggest) throws SQLException {
		session.update("Suggest-mapper.insertSuggest", Suggest);
	}

	@Override
	public void updateSuggest(SuggestVO Suggest) throws SQLException {
		session.update("Suggest-mapper.updateSuggest", Suggest);
	}

	@Override
	public void deleteSuggest(int sno) throws SQLException {
		session.update("Suggest-mapper.deleteSuggest", sno);
	}

}
