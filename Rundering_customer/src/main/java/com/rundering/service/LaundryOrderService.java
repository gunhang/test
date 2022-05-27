package com.rundering.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rundering.command.MyOrderCriteria;
import com.rundering.dto.LaundryOrderDetailVO;
import com.rundering.dto.LaundryOrderVO;

public interface LaundryOrderService {
	
	// 세탁주문접수
	String orderReceive(LaundryOrderVO laundryOrder, List<LaundryOrderDetailVO> laundryOrderDetailVOList) throws Exception;
	
	// 결제정보 금액확인
	Map<String, Object> checkOrder(List<LaundryOrderDetailVO> laundryOrderDetailVOList) throws SQLException;
	
	// 세탁주문조회
	LaundryOrderVO getLaundryOrder(String orderNo) throws SQLException;
	
	// 마이페이지 - 내 전체 주문내역 가져오기
	public Map<String, Object> getMyOrderList(MyOrderCriteria cri) throws Exception;

	// 마이페이지 - 진행중인 주문내역 가져오기
	public Map<String, Object> getMyOrderIngList(MyOrderCriteria cri) throws Exception;
	
	// 마이페이지 - 완료된 내 주문내역 가져오기
	public Map<String, Object> getMyCompleteOrderList(MyOrderCriteria cri) throws Exception;
	
	// 마이페이지 - 내 주문내역 디테일
	public Map<String, Object> getDetail(String orderNo) throws Exception;
	
	// 주문취소
	public void cancelOrder(LaundryOrderVO laundryOrder) throws Exception;
	
	// 마이페이지 - 주문내역_요청사항 수정
	public void modifyReq(LaundryOrderVO laundryOrder) throws Exception;
	
	// 마이페이지 - 주문내역_요청사항 삭제
	public void removeReq(String orderNo) throws Exception;
	
	// 진행중인 세탁주문 내역 가져오기
	public int ingCount(MyOrderCriteria cri)throws Exception;
}
