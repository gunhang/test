package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.command.MyOrderCriteria;
import com.rundering.dto.LaundryOrderVO;

public interface LaundryOrderDAO {
	
	// 세탁주문번호생성
	public String selectLaundryOrderSequenceNextValue() throws SQLException;
	
	// 세탁주문접수
	public void insertLaundryOrder(LaundryOrderVO laundryOrder) throws SQLException;
	
	// 세탁주문조회 - 주문번호로
	public LaundryOrderVO selectLaundryOrderByOrderNo(String orderNo) throws SQLException;
	
	// 마이페이지 - 전체 주문내역 가져오기
	public List<LaundryOrderVO> getMyOrderList(MyOrderCriteria cri) throws Exception;

	// 마이페이지 - 진행중인 주문내역 가져오기
	public List<LaundryOrderVO> getMyOrderIngList(MyOrderCriteria cri) throws Exception;
	
	// 마이페이지 - 완료된 내 주문내역 가져오기
	public List<LaundryOrderVO> getMyCompleteOrderList(MyOrderCriteria cri) throws Exception;
	
	// 마이페이지 - 주문내역 전체 개수 체크
	public int myOrderList(MyOrderCriteria cri) throws SQLException;
	
	// 마이페이지 - 주문내역 진행중인 개수 체크
	public int myOrderIngList(MyOrderCriteria cri) throws SQLException;
	
	// 마이페이지 - 완료된 주문내역 개수 체크
	public int myCompleteOrderList(MyOrderCriteria cri) throws SQLException;
	
	// 마이페이지 - 주문내역 디테일
	public LaundryOrderVO getmyorderByorderNo(String orderNo) throws Exception;
	
	// 마이페이지 - 주문취소
	public void cancelLaundryOrder(String orderNo) throws Exception;
	
	// 마이페이지 - 요청사항 수정
	public void modifyReq(LaundryOrderVO laundryOrder) throws Exception;
	
	// 마이페이지 - 요청사항 삭제
	public void removeReq(String orderNo) throws Exception;
	
	
}
