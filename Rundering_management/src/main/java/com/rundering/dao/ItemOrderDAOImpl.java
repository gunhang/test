package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.ItemOrderDetailVO;
import com.rundering.dto.ItemOrderVO;

public class ItemOrderDAOImpl implements ItemOrderDAO{

	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	} 
	
	@Override 
	public void insertItemOrderByItmeOrder(ItemOrderVO itemOrder) throws Exception {
		
		 session.insert("ItemOrder-Mapper.insertItemOrderByItmeOrder", itemOrder);
		
	}

	@Override
	public void insertItemOrderDetailByItmeOrderDetail(ItemOrderDetailVO itemOrderDetail) throws Exception {
		session.insert("ItemOrder-Mapper.insertItemOrderDetailByDetail", itemOrderDetail);
		
	}
	@Override
	public String seq() throws Exception{
		String seq = session.selectOne("ItemOrder-Mapper.seq");
		return seq;
	}
	@Override
	public void itemOrderRemove(String seq) throws Exception{
		session.delete("ItemOrder-Mapper.itemOrderRemove",seq);
	}
	@Override
	public void itemOrderDetailRemove(String seq) throws Exception {
		session.delete("ItemOrder-Mapper.itemOrderDetailRemove",seq);
	}
	@Override
	public List<ItemOrderVO> selectItemOrderList(BranchCriteria cri) throws Exception{
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<ItemOrderVO> itemOrderList = null;
	 	itemOrderList = session.selectList("ItemOrder-Mapper.selectItemOrderList",cri,rowBounds);
	 	return itemOrderList;
	}
	@Override
	public List<ItemOrderVO> selectAdminItemOrderList(BranchCriteria cri) throws Exception{
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);
		List<ItemOrderVO> itemOrderList = null;
	 	itemOrderList = session.selectList("ItemOrder-Mapper.selectAdminItemOrderList",cri,rowBounds);
	 	return itemOrderList;
	}
	
	
	@Override
	public int selectCount(BranchCriteria cri) throws Exception{
		int count = session.selectOne("ItemOrder-Mapper.selectCount",cri);
		return count;
	}
	
	@Override
	public int selectAdminItemOrderCount() throws Exception{
		int count = session.selectOne("ItemOrder-Mapper.selectAdminItemOrderCount");
		return count;
	}
	
	@Override
	public int selectTotalPriceByOrderCode(String ordercode) throws Exception{
		int result =0;
		String totalPrice = session.selectOne("ItemOrder-Mapper.selectTotalPriceByOrderCode",ordercode);
		
		if (totalPrice==null) {
			return result;
		}
		int intTotalPrice=Integer.parseInt(totalPrice);
		return intTotalPrice;
	}
	
	@Override
	public List<ItemOrderDetailVO> selectItemOrderDetailByOrdercode(String ordercode) throws Exception{
		return session.selectList("ItemOrder-Mapper.selectItemOrderDetailByOrdercode", ordercode);
	}
	@Override
	public String selectItemOrderBranchCodeByOrdercode(String ordercode) throws Exception{
		return session.selectOne("ItemOrder-Mapper.selectItemOrderBranchCodeByOrdercode",ordercode);
	}
	@Override 
	public ItemOrderVO selectItemOrderByOrdercode(String ordercode) throws Exception{
		return session.selectOne("ItemOrder-Mapper.selectItemOrderByOrdercode",ordercode);
	} 
	@Override
	public void updateItemOrderStatusByOrderCode(ItemOrderVO itemOrder) throws Exception{
		session.update("ItemOrder-Mapper.updateItemOrderStatusByOrderCode", itemOrder);
	}
	@Override
	public void deleteItemOrderRemoveByOrdercode(String ordercode) throws Exception{
		session.delete("ItemOrder-Mapper.deleteItemOrderRemoveByOrdercode", ordercode);
	}
	@Override
	public void deleteItemOrderDetailRemove(String ordercode) throws Exception{
		session.delete("ItemOrder-Mapper.deleteItemOrderDetailRemove",ordercode);
	}
	@Override
	public int selectItemOrder01() throws Exception{
		return session.selectOne("ItemOrder-Mapper.selectItemOrder01");
	}
	@Override
	public int selectItemOrder02() throws Exception{
		return session.selectOne("ItemOrder-Mapper.selectItemOrder02");
	}
	@Override
	public int selectItemOrder02ByBranchCode(BranchCriteria cri) throws Exception{
		return session.selectOne("ItemOrder-Mapper.selectItemOrder02ByBranchCode",cri);
	}
	@Override
	public int selectItemOrder03ByBranchCode(BranchCriteria cri) throws Exception{
		return session.selectOne("ItemOrder-Mapper.selectItemOrder03ByBranchCode",cri);
	}
	@Override
	public int selectItemOrder04ByBranchCode(BranchCriteria cri) throws Exception{
		return session.selectOne("ItemOrder-Mapper.selectItemOrder04ByBranchCode",cri);
	}
  
}
