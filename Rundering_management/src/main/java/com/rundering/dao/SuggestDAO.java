package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.command.Criteria;
import com.rundering.dto.SuggestVO;

public interface SuggestDAO {

	List<SuggestVO> selectSearchSuggestList(Criteria cri) throws SQLException;

	int selectSearchSuggestListCount(Criteria cri) throws SQLException;

	SuggestVO selectSuggestBySno(int sno) throws SQLException;

	void increaseViewCount(int sno) throws SQLException;
	
	void checkSuggest(int sno) throws SQLException;

	int selectSuggestSequenceNextValue() throws SQLException;

	void insertSuggest(SuggestVO Suggest) throws SQLException;

	void updateSuggest(SuggestVO Suggest) throws SQLException;

	void deleteSuggest(int sno) throws SQLException;
}
