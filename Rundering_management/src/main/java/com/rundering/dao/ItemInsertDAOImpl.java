package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.ItemInsertVO;
import com.rundering.dto.ItemVO;

public class ItemInsertDAOImpl implements ItemInsertDAO {
	SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public void insertItemIsert(ItemInsertVO itemInsert) throws Exception {
		session.insert("ItemInsert-Mapper.insertItemIsert", itemInsert);
	}

	@Override
	public ItemVO selectDayItemByItem(ItemVO item) throws Exception {
		return session.selectOne("ItemInsert-Mapper.selectDayItemByItem",item);
	}

	@Override
	public List<ItemInsertVO> selectItemInsertList(BranchCriteria cri) throws Exception {
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);	
		
		return session.selectList("ItemInsert-Mapper.selectItemInsertList",cri,rowBounds);
	}
	@Override
	public int selectItemInsertCount(BranchCriteria cri) throws Exception {
		return session.selectOne("ItemInsert-Mapper.selectItemInsertCount",cri);
	}


	
}
