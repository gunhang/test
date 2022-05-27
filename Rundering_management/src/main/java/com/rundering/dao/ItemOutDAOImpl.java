package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.ItemOutVO;
import com.rundering.dto.ItemVO;

public class ItemOutDAOImpl implements ItemOutDAO{
	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	
	@Override
	public List<ItemOutVO> selectItemOutList(BranchCriteria cri) throws Exception {
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);	
		
		return session.selectList("ItemOut-Mapper.selectItemOutList",cri, rowBounds) ;
	}

	@Override
	public void insertItemOut(ItemOutVO itemOut) throws Exception {
		session.insert("ItemOut-Mapper.insertItemOut", itemOut);
	}

	@Override
	public void deleteItemOut(ItemOutVO itemOut) throws Exception {
		session.delete("ItemOut-Mapper.deleteItemOut", itemOut);
		
	}
 
	@Override
	public int selectItemOutCount(BranchCriteria cri) throws Exception {
		return session.selectOne("ItemOut-Mapper.selectItemOutCount", cri);
	}

 
	@Override
	public ItemOutVO selectItemOutByOutItemCode(String itemOutCode) throws Exception {
		return session.selectOne("ItemOut-Mapper.selectItemOutByOutItemCode",itemOutCode);
	}


	@Override
	public ItemVO selectDayItemOutByItem(ItemVO itemVO) throws Exception {
		
		return session.selectOne("ItemOut-Mapper.selectDayItemOutByItem",itemVO);
	}

	


}
