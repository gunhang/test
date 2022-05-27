package com.rundering.dao;

import java.util.List;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.ItemInsertVO;
import com.rundering.dto.ItemVO;

public interface ItemInsertDAO {
	public void insertItemIsert(ItemInsertVO itemInsert) throws Exception;
	public ItemVO selectDayItemByItem(ItemVO item) throws Exception;
	public List<ItemInsertVO> selectItemInsertList(BranchCriteria cri) throws Exception; 
	public int selectItemInsertCount(BranchCriteria cri) throws Exception;
}
