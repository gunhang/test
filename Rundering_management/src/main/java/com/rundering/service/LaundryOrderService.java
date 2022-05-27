package com.rundering.service;

import java.util.List;
import java.util.Map;

import com.rundering.command.AdminLaundryOrderListCriteria;
import com.rundering.command.BranchCriteria;
import com.rundering.dto.AttachVO;
import com.rundering.dto.LaundryOrderVO;
import com.rundering.dto.OrderDelayDTO;

public interface LaundryOrderService {

	Map<String, Object> laundryOrderList(BranchCriteria cri) throws Exception;

	void updateStatus(List<LaundryOrderVO> laundryOrderList) throws Exception;
	public List<AttachVO> selectAttachList(String attchNo) throws Exception;

	
	Map<String, Object> getAdminlaundryOrderList(AdminLaundryOrderListCriteria cri) throws Exception;
	
	Map<String, Object> getConfirmOrderAssignmentInfo(AdminLaundryOrderListCriteria cri) throws Exception;
	
	Map<String, Object> getlaundryOrderByOrderNo(String orderNo) throws Exception;

	String selectGetBranchCode(String branchCode) throws Exception;

	Map<String, Object> assignmentOrder(AdminLaundryOrderListCriteria cri) throws Exception;

	Map<String, Object> piChart() throws Exception;
	
	Map<String,Object> orderDelay(OrderDelayDTO orderDelay) throws Exception;

	Map<String, Object> branchOrderPiChart(String branchCode) throws Exception;

	Map<String, Object> autoAssignmentOrder() throws Exception;
	
	Map<String, Object> handOverToDeliveryEmployee(String branchCode) throws Exception;
	
}
