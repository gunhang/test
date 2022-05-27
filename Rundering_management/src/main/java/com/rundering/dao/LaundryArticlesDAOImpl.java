package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.Criteria;
import com.rundering.dto.LaundryArticlesVO;

public class LaundryArticlesDAOImpl implements LaundryArticlesDAO{
	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}
	@Override
	public List<LaundryArticlesVO> LaundryArticlesList(Criteria cri) throws SQLException {
		
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);		
		
		List<LaundryArticlesVO> laundryArticlesList=
				session.selectList("LaundryArticles-Mapper.laundryArticlesList",cri,rowBounds);
		
		return laundryArticlesList;
	}
	@Override
	public int selectLaundryArticlesCriteriaTotalCount(Criteria cri) throws SQLException {
		int count=session.selectOne("LaundryArticles-Mapper.selectSearchLaundryArticlesListCount",cri);
		return count;
	}
	
	@Override
	public LaundryArticlesVO selectLaundryArticlesListByArticlesCode(String articlesCode) throws SQLException {
		LaundryArticlesVO ordergoods=
				session.selectOne("LaundryArticles-Mapper.selectLaundryArticlesByArticlesCode", articlesCode);
		return ordergoods;
	}

	@Override
	public void insertLaundryArticles(LaundryArticlesVO laundryArticles) throws SQLException {
		session.update("LaundryArticles-Mapper.insertLaundryArticles",laundryArticles);
	}

	@Override
	public void updateLaundryArticles(LaundryArticlesVO laundryArticles) throws SQLException {
		session.update("LaundryArticles-Mapper.updateLaundryArticles", laundryArticles);
	}

	@Override
	public void deleteLaundryArticles(String articlesCode) throws SQLException {
		session.update("LaundryArticles-Mapper.deleteLaundryArticles",articlesCode);
	}
	@Override
	public List<LaundryArticlesVO> NotALaundryArticlesList(Criteria cri) throws SQLException {
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);		
		
		List<LaundryArticlesVO> laundryArticlesList=
				session.selectList("LaundryArticles-Mapper.NotALaundryArticlesList",cri,rowBounds);
		
		return laundryArticlesList;
	}
	@Override
	public List<LaundryArticlesVO> selectLandryArticlesStock() throws Exception {
		return session.selectList("LaundryArticles-Mapper.NotALaundryArticlesList");
	}
	@Override
	public String selectLaundryArticles(String articlesCode) throws Exception {
		
		return session.selectOne("LaundryArticles-Mapper.selectLaundryArticles",articlesCode);
	}

	
	
}
