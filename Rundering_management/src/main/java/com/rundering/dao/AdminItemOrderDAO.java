package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.command.Criteria;
import com.rundering.dto.ItemOrderDetailVO;
import com.rundering.dto.ItemOrderVO;

public interface AdminItemOrderDAO {
	
	public List<ItemOrderVO> selectSearchItemOrderList(Criteria cri) throws SQLException;
	
	public int selectSearchItemOrderListCount(Criteria cri) throws SQLException;
	
	public ItemOrderVO selectItemOrderDetail(String ordercode)throws SQLException;		
	
	public List<ItemOrderDetailVO> selectItemOrderDetailList(String ordercode)throws SQLException;
	
	public void modifyStatus (ItemOrderVO itemOrder)throws SQLException;
}
