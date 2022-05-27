package com.rundering.service;

import java.util.List;
import java.util.Map;

import com.rundering.dto.AttachVO;
import com.rundering.dto.LaundryArticlesVO;
import com.rundering.dto.LaundryOrderVO;

public interface DeliveryService { 
	//수거 리스트
	Map<String, Object> getPickupWaitList(LaundryOrderVO laundryOrder) throws Exception;
	//수거 완료ㆍ취소(상태 변경)
	void updatePickUpCom(LaundryOrderVO laundryOrder) throws Exception;
	//배송 리스트
	Map<String, Object> getDeliveryList(LaundryOrderVO laundryOrder) throws Exception;
	//배송 완료ㆍ취소(상태버튼)
	
	//수거ㆍ배송 상세
	Map<String, Object> getOrderDetailByOrderNo(String orderNo, AttachVO attach) throws Exception;
	
	// 지점 도착 일괄 처리(수거 완료 후)
	void updateToBranch(LaundryOrderVO laundryOrder)throws Exception;
	
 	List<LaundryOrderVO> sortAddressAsc(LaundryOrderVO laundryOrder) throws Exception;
	
 	List<LaundryOrderVO> sortAddressDesc(LaundryOrderVO laundryOrder) throws Exception;
 	
 	//파일 업로드
 	public void regist(LaundryOrderVO laundryOrder,AttachVO attach) throws Exception;
 	
 	//수거ㆍ배송중인 갯수
 	Map<String, Object> getOrderCount(String branchCode) throws Exception;
}
