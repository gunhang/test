package com.rundering.dao;

import java.util.List;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.ItemVO;
import com.rundering.dto.LaundryGoodsStockVO;

public interface LaundryGoodsStockDAO {
	public void insertLaundryGoodsStock(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	public void updateLaundryGoodsStockCountByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	public List<ItemVO> selectItemListByBranchCode(BranchCriteria cri) throws Exception;
	public int selectItemCountByBranchCode(BranchCriteria cri) throws Exception;
	public void updateLaundryGoodsStockAutoOrderByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	public void updateLaundryGoodsStockAutoOrderPointByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	public void updateLaundryGoodsStockAutoOrderCountByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	void updateLaundryGoodsStockSupplyCountByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	public int selectSupplyCountByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	void updateLaundryGoodsStockSupplyCountPlusByVO(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	List<LaundryGoodsStockVO> selectLaundryGoodsStock() throws Exception;
	void updateLaundryGoodsStockLastDateAuto(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	List<LaundryGoodsStockVO> selectLaundryGoodsStockByBranchCode(String branchCode) throws Exception;
	void updateAdminGoodsStockSupplyByLaundryGoodsStock(LaundryGoodsStockVO laundryGoodsStockVO) throws Exception;

}
 