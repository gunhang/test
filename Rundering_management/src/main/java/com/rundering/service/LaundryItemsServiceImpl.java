package com.rundering.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.Criteria;
import com.rundering.command.PageMaker;
import com.rundering.dao.ComCodeDAO;
import com.rundering.dao.LaundryItemsDAO;
import com.rundering.dto.ComCodeVO;
import com.rundering.dto.LaundryItemsVO;
	
public class LaundryItemsServiceImpl implements LaundryItemsService{
	
	private LaundryItemsDAO laundryItemsDAO;
	public void setLaundryItemsDAO(LaundryItemsDAO laundryItemsDAO) {
		this.laundryItemsDAO = laundryItemsDAO;
	}
	
	private ComCodeDAO comCodeDAO;
	public void setComCodeDAO(ComCodeDAO comCodeDAO) {
		this.comCodeDAO = comCodeDAO;
	}

	@Override
	public void regist(LaundryItemsVO laundryItems) throws SQLException {
		// 세탁품목번호PK가 카테고리+시퀀스라서 카테고리별 시퀀스 처리
		switch (laundryItems.getLaundryCategory()) {
		case "CL":
			String ClothingSequence = laundryItemsDAO.SelectClothingSequenceNextValue();
			laundryItems.setLaundryItemsCode(ClothingSequence);
			break;
		case "BE":
			String beddingSequence = laundryItemsDAO.SelectBeddingSequenceNextValue();
			laundryItems.setLaundryItemsCode(beddingSequence);
			break;
		case "SH":
			String shoesSequence = laundryItemsDAO.SelectShoesSequenceNextValue();
			laundryItems.setLaundryItemsCode(shoesSequence);
			break;
		}
		
		laundryItemsDAO.insertLaundryItems(laundryItems);
	
		
	}

	@Override
	public Map<String, Object> getLaundryItemsList(Criteria cri) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<LaundryItemsVO> laundryItemsList = laundryItemsDAO.selectlaundryItemsList(cri);
		List<ComCodeVO> comCodeList =  comCodeDAO.selectLaundryCategory();
		Map<String, String> codeMap = new HashMap<String, String>();
		for (ComCodeVO comCodeVO : comCodeList) {
			codeMap.put(comCodeVO.getComCode(), comCodeVO.getComCodeNm()) ;
		}
		
		// 전체 board 개수
		int totalCount = laundryItemsDAO.selectLaundryItemsCriteriaTotalCount(cri);
		
		// PageMaker 생성.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("laundryItemsList", laundryItemsList);
		dataMap.put("pageMaker", pageMaker);
		dataMap.put("codeMap", codeMap);
		
		
		return dataMap;
	}


	@Override
	public void modify(LaundryItemsVO laundryItems) throws SQLException {
		laundryItemsDAO.updateLaundryItem(laundryItems);
	}

	@Override
	public void remove(String laundryItemsCode) throws SQLException {
		laundryItemsDAO.deleteLaundryItem(laundryItemsCode);
	}

	@Override
	public LaundryItemsVO getLaundryItemsByItemCode(String laundryItemsCode) throws SQLException {
		LaundryItemsVO laundryItem = laundryItemsDAO.selectLaundryItemsBylaundryItemsCode(laundryItemsCode);
		return laundryItem;
	}

	

}
