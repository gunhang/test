package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.command.Criteria;
import com.rundering.dto.LaundryArticlesVO;

public interface LaundryArticlesDAO {
	List<LaundryArticlesVO> LaundryArticlesList(Criteria cri) throws SQLException;
	
	int selectLaundryArticlesCriteriaTotalCount(Criteria cri) throws SQLException;
	
	LaundryArticlesVO selectLaundryArticlesListByArticlesCode(String articlesCode) throws SQLException;

	void insertLaundryArticles(LaundryArticlesVO laundryArticles) throws SQLException;

	void updateLaundryArticles(LaundryArticlesVO laundryArticles) throws SQLException;

	void deleteLaundryArticles(String articlesCode) throws SQLException;
	
	List<LaundryArticlesVO>	NotALaundryArticlesList(Criteria cri) throws SQLException;
	List<LaundryArticlesVO> selectLandryArticlesStock() throws Exception;
	
	String selectLaundryArticles(String articlesCode) throws Exception;
}
