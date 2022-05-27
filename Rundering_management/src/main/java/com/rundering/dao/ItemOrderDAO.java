package com.rundering.dao;

import java.util.List;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.ItemOrderDetailVO;
import com.rundering.dto.ItemOrderVO;

public interface ItemOrderDAO {
	public void insertItemOrderByItmeOrder(ItemOrderVO itemOrder) throws Exception;
	public void insertItemOrderDetailByItmeOrderDetail(ItemOrderDetailVO itemOrderDetail) throws Exception;
	public String seq() throws Exception;
	public void itemOrderRemove(String seq) throws Exception;
	public void itemOrderDetailRemove(String seq) throws Exception;
	public List<ItemOrderVO> selectItemOrderList(BranchCriteria cri) throws Exception;
	public int selectCount(BranchCriteria cri) throws Exception;
	public int selectTotalPriceByOrderCode(String ordercode) throws Exception;
	public List<ItemOrderDetailVO> selectItemOrderDetailByOrdercode(String ordercode) throws Exception;
	public String selectItemOrderBranchCodeByOrdercode(String ordercode) throws Exception;
	public ItemOrderVO selectItemOrderByOrdercode(String ordercode) throws Exception;
	public void updateItemOrderStatusByOrderCode(ItemOrderVO itemOrder) throws Exception;
	public void deleteItemOrderRemoveByOrdercode(String ordercode) throws Exception;
	public void deleteItemOrderDetailRemove(String ordercode) throws Exception;
	List<ItemOrderVO> selectAdminItemOrderList(BranchCriteria cri) throws Exception;
	int selectAdminItemOrderCount() throws Exception;
	int selectItemOrder01() throws Exception;
	int selectItemOrder02() throws Exception;
	int selectItemOrder03ByBranchCode(BranchCriteria cri) throws Exception;
	int selectItemOrder04ByBranchCode(BranchCriteria cri) throws Exception;
	int selectItemOrder02ByBranchCode(BranchCriteria cri) throws Exception;
 
} 