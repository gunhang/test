package com.rundering.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.Criteria;
import com.rundering.command.PageMaker;
import com.rundering.dao.AdminItemOrderDAO;
import com.rundering.dao.ItemOrderDAO;
import com.rundering.dao.LaundryGoodsStockDAO;
import com.rundering.dto.ItemOrderDetailVO;
import com.rundering.dto.ItemOrderVO;
import com.rundering.dto.LaundryGoodsStockVO;

public class AdminItemOrderServiceImpl implements AdminItemOrderService{
	private AdminItemOrderDAO adminItemOrderDAO;
	
	private LaundryGoodsStockDAO laundryGoodsStockDAO;
	
	public void setAdminItemOrderDAO(AdminItemOrderDAO adminItemOrderDAO) {
		this.adminItemOrderDAO = adminItemOrderDAO;
	}
	public void setLaundryGoodsStockDAO(LaundryGoodsStockDAO laundryGoodsStockDAO) {
		this.laundryGoodsStockDAO = laundryGoodsStockDAO;
	}

	
	@Override
	public Map<String, Object> getItemOrderList(Criteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		// 현재 page 번호에 맞게 리스트를 가져오기
		List<ItemOrderVO> itemOrderList = adminItemOrderDAO.selectSearchItemOrderList(cri);

		// 전체 board 개수
		int totalCount = adminItemOrderDAO.selectSearchItemOrderListCount(cri);
		
		// PageMaker 생성.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("itemOrderList", itemOrderList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public Map<String, Object> getItemOrderDetailList(String ordercode) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//주문정보
		ItemOrderVO itemOrder = adminItemOrderDAO.selectItemOrderDetail(ordercode);
		//물품발주 상세품목
		List<ItemOrderDetailVO> itemOrderDetail = adminItemOrderDAO.selectItemOrderDetailList(ordercode);
		
		
		dataMap.put("itemOrder", itemOrder);
		dataMap.put("itemOrderDetail", itemOrderDetail);
		
		return dataMap;
	}

	@Override
	public void modifyStatus(ItemOrderVO itemOrder) throws Exception {
		adminItemOrderDAO.modifyStatus(itemOrder);
		if(itemOrder.getItemOrderStatus().equals("03")){
			List<ItemOrderDetailVO> detailList = adminItemOrderDAO.selectItemOrderDetailList(itemOrder.getOrdercode());
			for (ItemOrderDetailVO detail : detailList) {
				LaundryGoodsStockVO laundryGoodsStock = new LaundryGoodsStockVO();
				laundryGoodsStock.setSupplyCount(detail.getOrderCount());
				laundryGoodsStock.setArticlesCode(detail.getArticlesCode());
				laundryGoodsStockDAO.updateAdminGoodsStockSupplyByLaundryGoodsStock(laundryGoodsStock);
			}
			
		}
		
	}
	
}
