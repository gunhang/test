package com.rundering.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.MyOrderCriteria;
import com.rundering.command.MyOrderPageMaker;
import com.rundering.dao.PaymentDAO;
import com.rundering.dto.PaymentVO;

public class PaymentService {

	private PaymentDAO paymentDAO;
	public void setPaymentDAO(PaymentDAO paymentDAO) {
		this.paymentDAO = paymentDAO;
	}
	
	// 주문 취소 내역
	public Map<String, Object> getCancelList(MyOrderCriteria cri) throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<PaymentVO> cancelList = paymentDAO.getCancelList(cri);
		
		// 전체 list 개수
		int totalCount = paymentDAO.cancelCount(cri);
		// PageMaker 생성.
		MyOrderPageMaker pageMaker = new MyOrderPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("cancelList",cancelList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	// 결제 내역
	public Map<String, Object> getComList(MyOrderCriteria cri) throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<PaymentVO> comList = paymentDAO.getComList(cri);
		
		// 전체 list 개수
		int totalCount = paymentDAO.comCount(cri);
		// PageMaker 생성.
		MyOrderPageMaker pageMaker = new MyOrderPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("comList",comList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
}
