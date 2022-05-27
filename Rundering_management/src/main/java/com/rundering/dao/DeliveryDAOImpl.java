package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.LaundryOrderDetailVO;
import com.rundering.dto.LaundryOrderVO;

public class DeliveryDAOImpl implements DeliveryDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}

	// 수거 리스트 가져오기
	@Override
	public List<LaundryOrderVO> selectPickupWait(LaundryOrderVO laundryOrder) throws Exception {
		List<LaundryOrderVO> pickupList = session.selectList("Delivery-Mapper.selectPickupWait", laundryOrder);
		return pickupList;
	}

	// 수거 완료 리스트 가져오기
	@Override
	public List<LaundryOrderVO> selectPickupCompleteList(LaundryOrderVO laundryOrder) throws Exception {
		List<LaundryOrderVO> pickupList = session.selectList("Delivery-Mapper.selectPickupWaitCompleteList",
				laundryOrder);
		return pickupList;
	}

	// 배송 리스트 가져오기
	@Override
	public List<LaundryOrderVO> selectDeliveryList(LaundryOrderVO laundryOrder) throws Exception {
		List<LaundryOrderVO> collect = session.selectList("Delivery-Mapper.selectDeliveryList", laundryOrder);
		return collect;
	}

	// 배송 완료 리스트
	@Override
	public List<LaundryOrderVO> selectDeliveryComList(LaundryOrderVO laundryOrder) throws Exception {
		List<LaundryOrderVO> collect = session.selectList("Delivery-Mapper.selectDeliveryComList", laundryOrder);
		return collect;
	}

	// 수거완료(상태 변경)
	@Override
	public void updatePickUpCom(LaundryOrderVO laundryOrder) throws Exception {
		session.selectList("Delivery-Mapper.updatePickUpCom", laundryOrder);
	}

	// 배송 상세 order
	@Override
	public LaundryOrderVO selectOrderByOrderNo(String orderNo) throws Exception {
		return session.selectOne("Delivery-Mapper.selectOrderByOrderNo", orderNo);
	}

	// 배송 상세 detail
	@Override
	public List<LaundryOrderDetailVO> selectOrdertDetailList(String orderNo) throws Exception {
		return session.selectList("Delivery-Mapper.selectOrdertDetailList", orderNo);
	}
	
	// 지점 도착 일괄 처리(수거 완료 후)
	@Override
	public void updateToBranch(LaundryOrderVO laundryOrder) throws Exception {
		session.update("Delivery-Mapper.updateToBranch", laundryOrder);
	}

	@Override
	public List<LaundryOrderVO> sortAddressAsc(LaundryOrderVO laundryOrder) throws Exception {
		return session.selectList("Delivery-Mapper.sortAddressAsc", laundryOrder);
	}
	
	@Override
	public List<LaundryOrderVO> sortAddressDesc(LaundryOrderVO laundryOrder) throws Exception {
		return session.selectList("Delivery-Mapper.sortAddressDesc", laundryOrder);
	}
	
	//주문번호에 할당된 파일번호 불러오기
	@Override
	public String getAttachFileNo(String orderNo) throws Exception {
		return session.selectOne("Delivery-Mapper.getAttachFileNo",orderNo);
	}

	@Override
	public int selectPickupCount(String branchCode) throws Exception {
		int count = session.selectOne("Delivery-Mapper.selectPickupCount", branchCode);
		return count;
	}
	@Override
	public int selectDeliveryCount(String branchCode) throws Exception {
		int count = session.selectOne("Delivery-Mapper.selectDeliveryCount", branchCode);
		return count;
	}

}
