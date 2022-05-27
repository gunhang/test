package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.command.MyOrderCriteria;
import com.rundering.dto.PaymentVO;

public interface PaymentDAO {
	
	// 결제등록
	public void insertPayment(PaymentVO payment) throws SQLException;
	// 주문번호 설정
	public void updatePaymentOrderNo(PaymentVO payment) throws SQLException;
	// 결제 취소
	public void cancelPayment(String orderNo) throws Exception;
	// 결제 내역
	public List<PaymentVO> getComList(MyOrderCriteria cri) throws Exception;
	// 결제 내역 개수
	public int comCount(MyOrderCriteria cri) throws Exception;
	// 주문 취소 내역
	public List<PaymentVO> getCancelList(MyOrderCriteria cri) throws Exception;
	// 주문 취소 내역 개수
	public int cancelCount(MyOrderCriteria cri) throws Exception;
	
}
