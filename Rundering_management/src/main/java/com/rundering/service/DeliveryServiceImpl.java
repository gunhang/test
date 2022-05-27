package com.rundering.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.dao.AttachDAO;
import com.rundering.dao.DeliveryDAO;
import com.rundering.dao.MemberDAO;
import com.rundering.dto.AttachVO;
import com.rundering.dto.LaundryOrderDetailVO;
import com.rundering.dto.LaundryOrderVO;
import com.rundering.util.SensSms;

public class DeliveryServiceImpl implements DeliveryService{

	private DeliveryDAO deliveryDAO;
	private AttachDAO attachDAO;
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}
	
	public void setLaundryOrderDAO(DeliveryDAO deliveryDAO) {
		this.deliveryDAO = deliveryDAO;
	}
	private SensSms sensSms;
	public void setSensSms(SensSms sensSms) {
		this.sensSms = sensSms;
	}
	
	// 수거 리스트 가져오기
	@Override
	public Map<String, Object> getPickupWaitList(LaundryOrderVO laundryOrder) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<LaundryOrderVO> pickupList = deliveryDAO.selectPickupWait(laundryOrder);
		List<LaundryOrderVO> pickupCompleteList = deliveryDAO.selectPickupCompleteList(laundryOrder);
		
		dataMap.put("pickupList", pickupList);
		dataMap.put("pickupCompleteList", pickupCompleteList);
		
		return dataMap;
	}
	//배송 수거 완료(상태 변경)
	@Override
	public void updatePickUpCom(LaundryOrderVO laundryOrder) throws Exception {
		deliveryDAO.updatePickUpCom(laundryOrder);
		
		// Attach_File_No 가져오기
		String an = deliveryDAO.getAttachFileNo(laundryOrder.getOrderNo());
		AttachVO av = new AttachVO();
		av.setAtchFileNo(an);
		
		if(laundryOrder.getOrderStatus().equals("02")) {
			av.setBizType("수거완료사진");
			attachDAO.deleteDeliveryPicture(av);
		}else if(laundryOrder.getOrderStatus().equals("07")) {
			av.setBizType("배송완료사진");
			attachDAO.deleteDeliveryPicture(av);
		}
		
	}
	
	
	//배송 리스트
	@Override
	public Map<String, Object> getDeliveryList(LaundryOrderVO laundryOrder) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<LaundryOrderVO> deliveryList = deliveryDAO.selectDeliveryList(laundryOrder);
		List<LaundryOrderVO> deliveryCompleteList = deliveryDAO.selectDeliveryComList(laundryOrder);
		
		dataMap.put("deliveryList",deliveryList);
		dataMap.put("deliveryCompleteList",deliveryCompleteList);
		
		return dataMap;
	}
	
	//배송 완료ㆍ취소(상태버튼)
	
	
	// 수거ㆍ배송 상세
	@Override
	public Map<String, Object> getOrderDetailByOrderNo(String orderNo, AttachVO attach) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		LaundryOrderVO detail = deliveryDAO.selectOrderByOrderNo(orderNo);
		// 고객 연락처 가져오기
		String phone = memberDAO.getPhoneNum(detail.getMemberNo());
		detail.setPhone(phone);
		
		List<LaundryOrderDetailVO> detailList = deliveryDAO.selectOrdertDetailList(orderNo);
		List<AttachVO> avList = attachDAO.selectAttachVOByFileNo(detail.getAtchFileNo());
		
		dataMap.put("avList", avList);
		dataMap.put("detail", detail);
		dataMap.put("detailList", detailList);
		
		return dataMap;
	}
	
	// 지점 도착 일괄 처리(수거 완료 후)
	@Override
	public void updateToBranch(LaundryOrderVO laundryOrder) throws Exception {
		deliveryDAO.updateToBranch(laundryOrder);
	}
	
	// 주소별 오름차순 정렬
	@Override
	public List<LaundryOrderVO> sortAddressAsc(LaundryOrderVO laundryOrder) throws Exception {
		List<LaundryOrderVO> lo = deliveryDAO.sortAddressAsc(laundryOrder);
		return lo;
	}
	
	// 주소별 내림  정렬
	@Override
	public List<LaundryOrderVO> sortAddressDesc(LaundryOrderVO laundryOrder) throws Exception {
		List<LaundryOrderVO> lo = deliveryDAO.sortAddressDesc(laundryOrder);
		return lo;
	}
	
	
	// 수거 및 배송 완료사진 업로드 및 상태변경 
	@Override
	public void regist(LaundryOrderVO laundryOrder,AttachVO attach) throws Exception {
		//업무구분
		String bizType = null;
		String status = null;
		if(laundryOrder.getOrderStatus().equals("03")) {
			bizType = "수거완료사진";
			status = "수거완료";
		}else if(laundryOrder.getOrderStatus().equals("08") || laundryOrder.getOrderStatus().equals("09")) {
			bizType = "배송완료사진";
			status = "배송완료";
		}
		
		String attachNo = deliveryDAO.getAttachFileNo(laundryOrder.getOrderNo());
	
		laundryOrder.setAtchFileNo(attachNo);
		
		
		attach.setBizType(bizType);
		
		// 파일 시퀀스
		int seq = attachDAO.getAttachNoSeq(laundryOrder.getAtchFileNo());
		
		if(seq == 0) {
			seq = 1;
		}else if(seq > 0) {
			seq += 1;
		}
		
		attach.setAtchFileSeq(seq);
		
		attach.setAtchFileNo(laundryOrder.getAtchFileNo());
		attachDAO.insertAttach(attach);
		deliveryDAO.updatePickUpCom(laundryOrder);
		
		//고객 문자알림
		try {
		sensSms.sendSMS(laundryOrder.getContactNumber().trim(), "[Rundering]\n고객님의 세탁물이 "+status+"되었습니다.\n주문내역에서 확인해주세요.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getOrderCount(String branchCode) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		int pickupCount = deliveryDAO.selectPickupCount(branchCode);
		int deliveryCount = deliveryDAO.selectDeliveryCount(branchCode);
		
		dataMap.put("pickupCount", pickupCount);
		dataMap.put("deliveryCount", deliveryCount);
		
		return dataMap;
	}
}
