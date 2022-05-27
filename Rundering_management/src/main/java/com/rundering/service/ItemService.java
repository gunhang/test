package com.rundering.service;

import java.util.List;
import java.util.Map;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.ItemVO;
import com.rundering.dto.LaundryGoodsStockVO;

public interface ItemService {


	Map<String, Object> selectItemVOList(BranchCriteria cri) throws Exception;
	List<ItemVO> selectDDItemByItem(ItemVO item) throws Exception;
	List<ItemVO> selectMMItemByItem(ItemVO item) throws Exception;
	List<ItemVO> selectYYItemByItem(ItemVO item) throws Exception;
	List<ItemVO> select3MItemByItem(ItemVO item) throws Exception;
	
	List<ItemVO> selectDDItemOutByItem(ItemVO item) throws Exception;
	List<ItemVO> selectMMItemOutByItem(ItemVO item) throws Exception;
	List<ItemVO> selectYYItemOutByItem(ItemVO item) throws Exception;
	List<ItemVO> select3MItemOutByItem(ItemVO item) throws Exception;
	
	void useAutoYn(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	
	void updatePoint(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	void updateCount(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	Map<String, Object> itemOutList (BranchCriteria cri,String articlesCode) throws Exception;
	Map<String, Object> itemInsertList(BranchCriteria cri, String articlesCode) throws Exception;
	void updateSupllyCount(LaundryGoodsStockVO laundryGoodsStock) throws Exception;
	void updateSupplyCountCancel(  String itemOutCode) throws Exception;
	void branchAutoOrder(String branchCode) throws Exception;
	
	
};
