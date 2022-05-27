package com.rundering.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.BranchCriteria;
import com.rundering.command.BranchPageMaker;
import com.rundering.dao.ComCodeDAO;
import com.rundering.dao.ItemInsertDAO;
import com.rundering.dao.ItemOrderDAO;
import com.rundering.dao.ItemOutDAO;
import com.rundering.dao.LaundryArticlesDAO;
import com.rundering.dao.LaundryGoodsStockDAO;
import com.rundering.dto.ItemInsertVO;
import com.rundering.dto.ItemOutVO;
import com.rundering.dto.ItemVO;
import com.rundering.dto.LaundryArticlesVO;
import com.rundering.dto.LaundryGoodsStockVO;
import com.rundering.scheduler.OrderTaskScheduler;
import com.rundering.util.ComCodeUtil;
import com.sun.mail.imap.protocol.Item;

public class ItemServiceImpl implements ItemService {
	
	LaundryGoodsStockDAO LaundryGoodsStockDAO;
	
	ComCodeDAO comCodeDAO;
	
	ItemInsertDAO itemInsertDAO;
	LaundryArticlesDAO laundryArticlesDAO;
	ItemOutDAO itemOutDAO;
	
	ItemOrderDAO itemOrderDAO;
	
	public void setLaundryGoodsStockDAO(LaundryGoodsStockDAO laundryGoodsStockDAO) {
		LaundryGoodsStockDAO = laundryGoodsStockDAO;
	}
	public void setComCodeDAO(ComCodeDAO comCodeDAO) {
		this.comCodeDAO = comCodeDAO;
	}
	
	public void setItemInsertDAO(ItemInsertDAO itemInsertDAO) {
		this.itemInsertDAO = itemInsertDAO;
	}
	public void setItemOutDAO(ItemOutDAO itemOutDAO) {
		this.itemOutDAO = itemOutDAO;
	}
	public void setLaundryArticlesDAO(LaundryArticlesDAO laundryArticlesDAO) {
		this.laundryArticlesDAO = laundryArticlesDAO;
	}
	public void setItemOrderDAO(ItemOrderDAO itemOrderDAO) {
		this.itemOrderDAO = itemOrderDAO;
	}
	
	
	@Override
	public void branchAutoOrder(String branchCode) throws Exception{
		OrderTaskScheduler taskScheduler = new OrderTaskScheduler();
		/* 키 : 아티클 코드 벨류 아티클 이름 */
		Map<String, Integer> articlesPrice = new HashMap<String, Integer>();
		// 지점 가져오기
		

		List<LaundryArticlesVO> laundrArticlesList = laundryArticlesDAO.selectLandryArticlesStock();
		// 물품가격을 합함
		for (LaundryArticlesVO laundryArticles : laundrArticlesList) {

		articlesPrice.put(laundryArticles.getArticlesCode(), laundryArticles.getPrice());
		}
		taskScheduler.autoOrderMethod(branchCode,articlesPrice,LaundryGoodsStockDAO,itemOrderDAO);
	}
	
	@Override
	public Map<String, Object> selectItemVOList(BranchCriteria cri) throws Exception {
		ComCodeUtil comCodeUtil = new ComCodeUtil();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		comCodeUtil.getCodeListMap("CLCODE", dataMap, comCodeDAO);
		
		List<ItemVO> itemList = null;
		itemList=LaundryGoodsStockDAO.selectItemListByBranchCode(cri);
		int count = LaundryGoodsStockDAO.selectItemCountByBranchCode(cri);
		int status02 = itemOrderDAO.selectItemOrder02ByBranchCode(cri);
		int status03 = itemOrderDAO.selectItemOrder03ByBranchCode(cri);
		int status04 = itemOrderDAO.selectItemOrder04ByBranchCode(cri);
		
		
		BranchPageMaker pageMaker = new BranchPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);
		
		dataMap.put("status02", status02);
		dataMap.put("status03", status03);
		dataMap.put("status04", status04);
		dataMap.put("itemList", itemList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	@Override
	public List<ItemVO> selectDDItemByItem(ItemVO item) throws Exception {
	 	List<ItemVO> itemList = new ArrayList<ItemVO>();
		for(int i = 0; i < 7; i++) {
			ItemVO resultItem = new ItemVO();
			
			item.setNumber(i);
			item.setNumber2(i+1);
			resultItem= itemInsertDAO.selectDayItemByItem(item);
			itemList.add(resultItem);
			
		}
		
		
		return itemList;
	}
	@Override
	public List<ItemVO> selectMMItemByItem(ItemVO item) throws Exception {
		List<ItemVO> itemList = new ArrayList<ItemVO>();
	
		for(int i = 0; i < 7; i++) {
			ItemVO resultItem = new ItemVO();
			
			item.setNumber(i*30);
			item.setNumber2((i+1)*30);
			resultItem=itemInsertDAO.selectDayItemByItem(item);
			itemList.add(resultItem);
			
		}
		return itemList;
	}
	@Override
	public List<ItemVO> select3MItemByItem(ItemVO item) throws Exception {
		List<ItemVO> itemList = new ArrayList<ItemVO>();
	
		for(int i = 0; i < 7; i++) {
			ItemVO resultItem = new ItemVO();
			
			item.setNumber(i*90);
			item.setNumber2((i+1)*90);
			resultItem=itemInsertDAO.selectDayItemByItem(item);
			itemList.add(resultItem);
			
		}
		return itemList;
	}
	@Override
	public List<ItemVO> selectYYItemByItem(ItemVO item) throws Exception {
		List<ItemVO> itemList = new ArrayList<ItemVO>();
		for(int i = 0; i < 7; i++) {
			ItemVO resultItem = new ItemVO();
			
			item.setNumber(i*365);
			item.setNumber2((i+1)*365);
			resultItem=itemInsertDAO.selectDayItemByItem(item);
			itemList.add(resultItem);
			
		}
		
		
		return itemList;
	}
	
	@Override
	public void useAutoYn(LaundryGoodsStockVO laundryGoodsStock)  throws Exception{
		LaundryGoodsStockDAO.updateLaundryGoodsStockAutoOrderByVO(laundryGoodsStock);
	}
	@Override
	public void updatePoint(LaundryGoodsStockVO laundryGoodsStock) throws Exception {
		LaundryGoodsStockDAO.updateLaundryGoodsStockAutoOrderPointByVO(laundryGoodsStock);
		
	}
	@Override
	public void updateCount(LaundryGoodsStockVO laundryGoodsStock) throws Exception {
		LaundryGoodsStockDAO.updateLaundryGoodsStockAutoOrderCountByVO(laundryGoodsStock);
		
	}
	@Override
	public void updateSupllyCount(LaundryGoodsStockVO laundryGoodsStock) throws Exception{
		
		
		ItemOutVO itemOut = new ItemOutVO();
		int supplyCount = LaundryGoodsStockDAO.selectSupplyCountByVO(laundryGoodsStock);
		if(supplyCount==laundryGoodsStock.getSupplyCount()) {
			return;
		}
		
		itemOut.setBranchCode(laundryGoodsStock.getBranchCode());
		itemOut.setArticlesCode(laundryGoodsStock.getArticlesCode());
		itemOut.setItemcount(supplyCount-laundryGoodsStock.getSupplyCount());
		itemOutDAO.insertItemOut(itemOut);
		
		
		LaundryGoodsStockDAO.updateLaundryGoodsStockSupplyCountByVO(laundryGoodsStock);
	}
	
	@Override
	public Map<String, Object> itemInsertList(BranchCriteria cri,String articlesCode) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<LaundryArticlesVO> laundryArticlesList= laundryArticlesDAO.selectLandryArticlesStock();
		
		
		List<ItemInsertVO> itemInsertList = null;
		itemInsertList=itemInsertDAO.selectItemInsertList(cri);
		int count = itemInsertDAO.selectItemInsertCount(cri);
		BranchPageMaker pageMaker = new BranchPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);
		
		dataMap.put("laundryArticlesList", laundryArticlesList);
		dataMap.put("itemInsertList", itemInsertList);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}
	@Override
	public Map<String, Object> itemOutList(BranchCriteria cri,String articlesCode) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<LaundryArticlesVO> laundryArticlesList= laundryArticlesDAO.selectLandryArticlesStock();
		
		
		List<ItemOutVO> itemOutList = null;
		itemOutList=itemOutDAO.selectItemOutList(cri);
		int count = itemOutDAO.selectItemOutCount(cri);
		BranchPageMaker pageMaker = new BranchPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);
		
		dataMap.put("laundryArticlesList", laundryArticlesList);
		dataMap.put("itemOutList", itemOutList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	@Override
	public void updateSupplyCountCancel(String itemOutCode) throws Exception {
		ItemOutVO itemOut=itemOutDAO.selectItemOutByOutItemCode(itemOutCode);
		LaundryGoodsStockVO laundryGoodsStock = new LaundryGoodsStockVO();
		laundryGoodsStock.setArticlesCode(itemOut.getArticlesCode());
		laundryGoodsStock.setSupplyCount(itemOut.getItemcount());
		laundryGoodsStock.setBranchCode(itemOut.getBranchCode());
		itemOutDAO.deleteItemOut(itemOut);
		LaundryGoodsStockDAO.updateLaundryGoodsStockSupplyCountPlusByVO(laundryGoodsStock);
		
		
	}
	@Override
	public List<ItemVO> selectDDItemOutByItem(ItemVO item) throws Exception {
		List<ItemVO> itemList = new ArrayList<ItemVO>();
		for(int i = 0; i < 7; i++) {
			ItemVO resultItem = new ItemVO();
			
			item.setNumber(i);
			item.setNumber2(i+1);
			resultItem= itemOutDAO.selectDayItemOutByItem(item);
			itemList.add(resultItem);
			
		}
		
		
		return itemList;
	}
	@Override
	public List<ItemVO> selectMMItemOutByItem(ItemVO item) throws Exception {
		List<ItemVO> itemList = new ArrayList<ItemVO>();
		
		for(int i = 0; i < 7; i++) {
			ItemVO resultItem = new ItemVO();
			
			item.setNumber(i*30);
			item.setNumber2((i+1)*30);
			resultItem= itemOutDAO.selectDayItemOutByItem(item);
			itemList.add(resultItem);
			
		}
		return itemList;
	}
	@Override
	public List<ItemVO> selectYYItemOutByItem(ItemVO item) throws Exception {
		List<ItemVO> itemList = new ArrayList<ItemVO>();
		
		for(int i = 0; i < 7; i++) {
			ItemVO resultItem = new ItemVO();
			
			item.setNumber(i*90);
			item.setNumber2((i+1)*90);
			resultItem= itemOutDAO.selectDayItemOutByItem(item);
			itemList.add(resultItem);
			
		}
		return itemList;
	}
	@Override
	public List<ItemVO> select3MItemOutByItem(ItemVO item) throws Exception {
		List<ItemVO> itemList = new ArrayList<ItemVO>();
		for(int i = 0; i < 7; i++) {
			ItemVO resultItem = new ItemVO();
			
			item.setNumber(i*365);
			item.setNumber2((i+1)*365);
			resultItem= itemOutDAO.selectDayItemOutByItem(item);
			itemList.add(resultItem);
			
		}
		
		
		return itemList;
	}
	
}
