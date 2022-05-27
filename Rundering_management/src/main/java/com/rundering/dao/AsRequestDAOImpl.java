package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.BranchCriteria;
import com.rundering.command.Criteria;
import com.rundering.dto.AsRequestVO;

public class AsRequestDAOImpl implements AsRequestDAO {
	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<AsRequestVO> selectSearchAsRequestList(Criteria cri) throws SQLException {

		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<AsRequestVO> asRequestList = session.selectList("AsRequest-mapper.selectSearchAsRequestList", cri,
				rowBounds);
		return asRequestList;
	}
	@Override
	public List<AsRequestVO> selectBranchSearchAsRequestList(BranchCriteria cri) throws SQLException {

		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<AsRequestVO> asRequestList = session.selectList("AsRequest-mapper.selectBranchSearchAsRequestList", cri,
				rowBounds);
		return asRequestList;
	}
	@Override
	public int selectSearchAsRequestListCount(Criteria cri) throws SQLException {
		return session.selectOne("AsRequest-mapper.selectSearchAsRequestListCount", cri);
	}
	
	@Override
	public int selectBranchSearchAsRequestListCount(BranchCriteria cri) throws SQLException {
		return session.selectOne("AsRequest-mapper.selectBranchSearchAsRequestListCount", cri);
	}
	
	@Override
	public AsRequestVO selectAsRequestByAsno(int asno) throws SQLException {
		return session.selectOne("AsRequest-mapper.selectAsRequestByAsno", asno);
	}
	
	/*
	 * @Override public void increaseViewCount(int asno) throws SQLException {
	 * session.update("AsRequest-mapper.increaseViewCount", asno); }
	 */

	@Override
	public void checkAsRequest(int asno) throws SQLException {
		session.update("AsRequest-mapper.checkAsRequest", asno);
	}

	@Override
	public void okAsRequest(int asno) throws SQLException {
		session.update("AsRequest-mapper.okAsRequest", asno);
	}

	@Override
	public int selectAsRequestSequenceNextValue() throws SQLException {
		return session.selectOne("AsRequest-mapper.selectAsRequestSequenceNextValue");
	}

	@Override
	public void insertAsRequest(AsRequestVO AsRequest) throws SQLException {
		session.update("AsRequest-mapper.insertAsRequest", AsRequest);
	}
	
	@Override
	public void selectItemList(AsRequestVO AsRequest) throws SQLException {
		session.update("AsRequest-mapper.selectItemList", AsRequest);
	}

	@Override
	public void updateAsRequest(AsRequestVO AsRequest) throws SQLException {
		session.update("AsRequest-mapper.updateAsRequest", AsRequest);
	}

	@Override
	public void deleteAsRequest(int asno) throws SQLException {
		session.update("AsRequest-mapper.deleteAsRequest", asno);
	}

}
