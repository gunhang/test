package com.rundering.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.ItemOrderDetailVO;
import com.rundering.dto.ItemOrderVO;
import com.rundering.dto.LaundryArticlesVO;

public interface ItemOrderService {


	public void insertItemOrder(ItemOrderVO itemOrder, List<ItemOrderDetailVO> itemOrderDetailList) throws Exception;


	public List<ItemOrderDetailVO> getItemOrdeDetail(String ordercode) throws Exception;


	public String getBranchCode(String ordercode) throws Exception;


	public ItemOrderVO getItemOrder(String ordercode) throws Exception;


	public Map<String, String> comCode() throws Exception;


	public void updateState(ItemOrderVO itemOrder) throws Exception;


	public void deleteItemorder(String ordercode) throws Exception;


	public Map<String, Object> itemOrdeList(BranchCriteria cri, HttpSession session) throws Exception;


	Map<String, Object> adminItemOrdeList(BranchCriteria cri) throws Exception;


	void updateStateNotRecive(ItemOrderVO itemOrder) throws Exception;


	List<LaundryArticlesVO> getLaundryArticles() throws Exception;


	Map<String, Object> selectItemOrderList() throws Exception;
	
}
