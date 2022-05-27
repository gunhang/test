package com.rundering.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.rundering.command.BranchCriteria;
import com.rundering.command.BranchPageMaker;
import com.rundering.dao.BranchDAO;
import com.rundering.dao.ComCodeDAO;
import com.rundering.dao.ItemInsertDAO;
import com.rundering.dao.ItemOrderDAO;
import com.rundering.dao.LaundryArticlesDAO;
import com.rundering.dao.LaundryGoodsStockDAO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.ComCodeVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.ItemInsertVO;
import com.rundering.dto.ItemOrderDetailVO;
import com.rundering.dto.ItemOrderVO;
import com.rundering.dto.LaundryArticlesVO;
import com.rundering.dto.LaundryGoodsStockVO;

public class ItemOrderServiceImpl implements ItemOrderService {
		
	ItemOrderDAO itemOrderDAO;
	
	ComCodeDAO comCodeDAO;
	
	ItemInsertDAO itemInsertDAO;
	
	LaundryGoodsStockDAO laundryGoodsStockDAO;
	
	BranchDAO branchDAO;
	
	LaundryArticlesDAO laundryArticlesDAO;
	
	
	public void setItemOrderDAO(ItemOrderDAO itemOrderDAO) {
		this.itemOrderDAO = itemOrderDAO;
	}
	public void setComCodeDAO(ComCodeDAO comCodeDAO) {
		this.comCodeDAO = comCodeDAO;
	}
	public void setLaundryGoodsStockDAO(LaundryGoodsStockDAO laundryGoodsStockDAO) {
		this.laundryGoodsStockDAO = laundryGoodsStockDAO;
	}
	public void setItemInsertDAO(ItemInsertDAO itemInsertDAO) {
		this.itemInsertDAO = itemInsertDAO;
	}
	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}
	
	public void setLaundryArticlesDAO(LaundryArticlesDAO laundryArticlesDAO) {
		this.laundryArticlesDAO = laundryArticlesDAO;
	}
	
	
	@Override
	public void insertItemOrder(ItemOrderVO itemOrder,List<ItemOrderDetailVO> itemOrderDetailList) throws Exception {
		String seq = itemOrderDAO.seq();
		itemOrder.setOrdercode(seq);
		itemOrderDAO.insertItemOrderByItmeOrder(itemOrder);
		int i = 1;
		try {
			for (ItemOrderDetailVO itemOrderDetail : itemOrderDetailList) {
			itemOrderDetail.setSeq(i);	
			itemOrderDetail.setOrdercode(seq);
			itemOrderDAO.insertItemOrderDetailByItmeOrderDetail(itemOrderDetail);
			i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, Object> itemOrdeList(BranchCriteria cri, HttpSession session) throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String,String> comCodeMap = new HashMap<String, String>();		
		Map<String,Integer> totalPriceMap = new HashMap<String,Integer>();
		List<ItemOrderVO> list = null; 
		List<ItemOrderVO> itemOrderList = new ArrayList<ItemOrderVO>();
		
		int totalPrice=0;
		
		EmployeesVO emp = (EmployeesVO)session.getAttribute("loginEmployee");
		cri.setBranchCode(emp.getBranchCode());
		 
		String empBranch= emp.getBranchCode();
		List<ComCodeVO> itemOrderComCode = comCodeDAO.selectItemOrderCode();
		list = itemOrderDAO.selectItemOrderList(cri);
		for (ItemOrderVO itemOrder : list) {
			if( empBranch.equals(itemOrder.getBranchCode())) {
				itemOrderList.add(itemOrder);
				totalPrice = itemOrderDAO.selectTotalPriceByOrderCode(itemOrder.getOrdercode());
				totalPriceMap.put(itemOrder.getOrdercode(), totalPrice);
			}
		}
		for (ComCodeVO comCode : itemOrderComCode) {
			comCodeMap.put(comCode.getComCode(), comCode.getComCodeNm());
		}
		
		
		int totalCount = itemOrderDAO.selectCount(cri);
		BranchPageMaker pageMaker = new BranchPageMaker();
		pageMaker.setCri(cri); 
		pageMaker.setTotalCount(totalCount);
		 
		dataMap.put("itemOrderList", itemOrderList);
		dataMap.put("pageMaker", pageMaker);
		dataMap.put("comCodeMap",comCodeMap);
		dataMap.put("totalPriceMap",totalPriceMap);
		return dataMap;
		
	}
	@Override
	public Map<String, Object> adminItemOrdeList(BranchCriteria cri) throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String,String> comCodeMap = new HashMap<String, String>();		
		List<ItemOrderVO> itemOrderList = new ArrayList<ItemOrderVO>();
		List<BranchVO> branchList = branchDAO.selectBranchList();
		dataMap.put("branchList", branchList);
		
		
		cri.setPerPageNum(10);
		 
		List<ComCodeVO> itemOrderComCode = comCodeDAO.selectItemOrderCode();
		itemOrderList = itemOrderDAO.selectAdminItemOrderList(cri);
		for (ComCodeVO comCode : itemOrderComCode) {
			comCodeMap.put(comCode.getComCode(), comCode.getComCodeNm());
		}
		
		
		int totalCount = itemOrderDAO.selectAdminItemOrderCount();
		BranchPageMaker pageMaker = new BranchPageMaker();
		pageMaker.setCri(cri); 
		pageMaker.setTotalCount(totalCount);
		 
		dataMap.put("itemOrderList", itemOrderList);
		dataMap.put("pageMaker", pageMaker);
		dataMap.put("comCodeMap",comCodeMap);
		return dataMap;
		
	}
	
	@Override
	public List<ItemOrderDetailVO> getItemOrdeDetail(String ordercode) throws Exception{
		List<ItemOrderDetailVO> itemOrderDetailList = null;
		itemOrderDetailList=itemOrderDAO.selectItemOrderDetailByOrdercode(ordercode);
		return itemOrderDetailList;
	}
	@Override
	public String getBranchCode(String ordercode) throws Exception{
		String branchCode =itemOrderDAO.selectItemOrderBranchCodeByOrdercode(ordercode);
		return branchCode;
		
	}
	@Override
	public ItemOrderVO getItemOrder(String ordercode) throws Exception{
		ItemOrderVO itemOrder = itemOrderDAO.selectItemOrderByOrdercode(ordercode);
		
		return itemOrder; 
	}
	@Override
	public List<LaundryArticlesVO> getLaundryArticles() throws Exception{
		List<LaundryArticlesVO> laundryArticles = laundryArticlesDAO.selectLandryArticlesStock();
		return laundryArticles;
	}
	
	@Override
	public Map<String,String> comCode() throws Exception{
		 Map<String, String> comCodeMap = new HashMap<String, String>();
		 List<ComCodeVO> itemOrderComCode = comCodeDAO.selectItemOrderCode();
		 for (ComCodeVO comCode : itemOrderComCode) {
				comCodeMap.put(comCode.getComCode(), comCode.getComCodeNm());
			}
		 return comCodeMap;
	}
	@Override
	public void updateState(ItemOrderVO itemOrder) throws Exception{
		 
		List<ItemOrderDetailVO> itemOrderDetailList= itemOrderDAO.selectItemOrderDetailByOrdercode(itemOrder.getOrdercode());
		String branchCode=itemOrderDAO.selectItemOrderBranchCodeByOrdercode(itemOrder.getOrdercode());
		itemOrderDAO.updateItemOrderStatusByOrderCode(itemOrder);
		
		for (ItemOrderDetailVO itemOrderDetail : itemOrderDetailList) {
			ItemInsertVO itemInsert = new ItemInsertVO();
			LaundryGoodsStockVO laundryGoodsStock = new LaundryGoodsStockVO();
			itemInsert.setArticlesCode(itemOrderDetail.getArticlesCode());
			itemInsert.setBranchCode(branchCode);
			itemInsert.setItemCount(itemOrderDetail.getOrderCount());
			itemInsert.setOrderNo(itemOrderDetail.getOrdercode());
			itemInsertDAO.insertItemIsert(itemInsert);
			
			laundryGoodsStock.setArticlesCode(itemOrderDetail.getArticlesCode());
			laundryGoodsStock.setBranchCode(branchCode);
			laundryGoodsStock.setSupplyCount(itemOrderDetail.getOrderCount());
			laundryGoodsStockDAO.updateLaundryGoodsStockCountByVO(laundryGoodsStock);
		}
	}
	@Override
	public void updateStateNotRecive(ItemOrderVO itemOrder) throws Exception{
		itemOrderDAO.updateItemOrderStatusByOrderCode(itemOrder);
		
	}
	@Override
	public void deleteItemorder(String ordercode) throws Exception{
		itemOrderDAO.deleteItemOrderDetailRemove(ordercode);
		itemOrderDAO.deleteItemOrderRemoveByOrdercode(ordercode);
	}
	
	@Override 
	public Map<String, Object> selectItemOrderList() throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int status01Count = itemOrderDAO.selectItemOrder01();
		int status02Count = itemOrderDAO.selectItemOrder02();
		dataMap.put("status01", status01Count);
		dataMap.put("status02", status02Count);
		return dataMap;
	}
	
} 
