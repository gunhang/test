package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.ItemVO;
import com.rundering.dto.LaundryGoodsStockVO;

public class LaundryGoodsStockDAOImpl implements LaundryGoodsStockDAO{
	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public void insertLaundryGoodsStock(LaundryGoodsStockVO laundryGoodsStock) throws Exception {
		session.insert("LaundryGoodsStock-Mapper.insertLaundryGoodsStock",laundryGoodsStock);
	}

	@Override
	public void updateLaundryGoodsStockCountByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception {
		session.update("LaundryGoodsStock-Mapper.updateLaundryGoodsStockCountByVO", laundryGoodsStock);
	}

	@Override
	public List<ItemVO> selectItemListByBranchCode(BranchCriteria cri) throws Exception {
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		
		return session.selectList("LaundryGoodsStock-Mapper.selectItemListByBranchCode", cri,rowBounds);
	}

	@Override
	public int selectItemCountByBranchCode(BranchCriteria cri) throws Exception {
		return session.selectOne("LaundryGoodsStock-Mapper.selectItemCountByBranchCode",cri );
	}

	@Override
	public void updateLaundryGoodsStockAutoOrderByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception {
		 session.update("LaundryGoodsStock-Mapper.updateLaundryGoodsStockAutoOrderByVO", laundryGoodsStock);
	}

	@Override
	public void updateLaundryGoodsStockAutoOrderPointByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception {
		session.update("LaundryGoodsStock-Mapper.updateLaundryGoodsStockAutoOrderPointByVO", laundryGoodsStock);
		
	}

	@Override
	public void updateLaundryGoodsStockAutoOrderCountByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception {
		session.update("LaundryGoodsStock-Mapper.updateLaundryGoodsStockAutoOrderCountByVO", laundryGoodsStock);
		
	}
	@Override
	public void updateLaundryGoodsStockSupplyCountByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception {
		session.update("LaundryGoodsStock-Mapper.updateLaundryGoodsStockSupplyCountByVO", laundryGoodsStock);
		
	}

	@Override
	public int selectSupplyCountByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception {
		return session.selectOne("LaundryGoodsStock-Mapper.selectSupplyCountByVO",laundryGoodsStock);
	}
	@Override 
	public void updateLaundryGoodsStockSupplyCountPlusByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception{
		session.selectOne("LaundryGoodsStock-Mapper.updateLaundryGoodsStockSupplyCountPlusByVO", laundryGoodsStock);
	}
	@Override
	public List<LaundryGoodsStockVO> selectLaundryGoodsStock() throws Exception{
		return session.selectList("LaundryGoodsStock-Mapper.selectLaundryGoodsStock");
	}
	@Override
	public void updateLaundryGoodsStockLastDateAuto(LaundryGoodsStockVO laundryGoodsStock) throws Exception{
		session.update("LaundryGoodsStock-Mapper.updateLaundryGoodsStockLastDateAuto",laundryGoodsStock);
	}
	@Override
	public List<LaundryGoodsStockVO> selectLaundryGoodsStockByBranchCode(String branchCode) throws Exception{
		return session.selectList("LaundryGoodsStock-Mapper.selectLaundryGoodsStockByBranchCode", branchCode);
	}
	@Override
	public void updateAdminGoodsStockSupplyByLaundryGoodsStock(LaundryGoodsStockVO laundryGoodsStockVO) throws Exception{
		session.update("LaundryGoodsStock-Mapper.updateAdminGoodsStockSupplyByLaundryGoodsStock", laundryGoodsStockVO);
	}


}
