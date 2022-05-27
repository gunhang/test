package com.rundering.dao;

import java.util.List;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.ItemOutVO;
import com.rundering.dto.ItemVO;

public interface ItemOutDAO {
	public List<ItemOutVO> selectItemOutList(BranchCriteria cri) throws Exception;
	public void insertItemOut(ItemOutVO itemOut) throws Exception;
	public void deleteItemOut(ItemOutVO itemOut) throws Exception;
	public int selectItemOutCount(BranchCriteria cri) throws Exception;
	ItemOutVO selectItemOutByOutItemCode(String itemOutCode) throws Exception;
	ItemVO selectDayItemOutByItem(ItemVO itemVO) throws Exception;
 } 
 