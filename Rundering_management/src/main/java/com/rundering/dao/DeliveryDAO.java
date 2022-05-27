package com.rundering.dao;

import java.util.List;

import com.rundering.dto.LaundryOrderDetailVO;
import com.rundering.dto.LaundryOrderVO;

public interface DeliveryDAO {
	//수거 리스트
	List<LaundryOrderVO> selectPickupWait(LaundryOrderVO laundryOrder) throws Exception;
	//수거 완료 리스트
	List<LaundryOrderVO> selectPickupCompleteList(LaundryOrderVO laundryOrder) throws Exception;
	//수거 완료ㆍ취소(상태변경)
	void updatePickUpCom(LaundryOrderVO laundryOrder) throws Exception;
	
	//배송 리스트
	List<LaundryOrderVO> selectDeliveryList(LaundryOrderVO laundryOrder) throws Exception;
	//배송 완료 리스트
	List<LaundryOrderVO> selectDeliveryComList(LaundryOrderVO laundryOrder) throws Exception;
	//배송 완료ㆍ취소(상태변경)
 
	//수거ㆍ배송 상세
	LaundryOrderVO selectOrderByOrderNo(String orderNo)throws Exception;
	List<LaundryOrderDetailVO> selectOrdertDetailList(String orderNo) throws Exception;
	
	// 지점 도착 일괄 처리(수거 완료 후)
	void updateToBranch(LaundryOrderVO laundryOrder)throws Exception;
	
	//정렬처리
	List<LaundryOrderVO> sortAddressAsc(LaundryOrderVO laundryOrder)throws Exception;
	List<LaundryOrderVO> sortAddressDesc(LaundryOrderVO laundryOrder)throws Exception;
	
	//주문번호에 할당된 파일번호 불러오기
	String getAttachFileNo(String orderNo) throws Exception;
	
	//수거ㆍ배송중인 갯수
	int selectPickupCount(String branchCode) throws Exception;
	int selectDeliveryCount(String branchCode) throws Exception;
	
	
}
