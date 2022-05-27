package com.rundering.manage.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rundering.command.AdminLaundryOrderListCriteria;
import com.rundering.dao.BranchDAO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.PaymentVO;
import com.rundering.service.LaundryOrderService;

@RestController
@RequestMapping("/admin/laundryorder")
public class AdminLaundryRESTController {

	@Autowired
	LaundryOrderService laundryOrderService;

	// 할당 정보확인
	@RequestMapping(value = "/confirmAssignment", method = RequestMethod.POST, consumes = "application/json;")
	public ResponseEntity<Map<String, Object>> confirmAssignment(@RequestBody AdminLaundryOrderListCriteria cri,
			Model model) throws Exception {
		ResponseEntity<Map<String, Object>> result = null;
		Map<String, Object> dataMap = new HashMap<>();
		try {
			dataMap = laundryOrderService.getConfirmOrderAssignmentInfo(cri);
			result = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return result;
	}

	// 지점에 주문 할당
	@RequestMapping(value = "/assignmentOrder", method = RequestMethod.POST, consumes = "application/json;")
	public ResponseEntity<Map<String, Object>> assignmentOrder(@RequestBody AdminLaundryOrderListCriteria cri,
			Model model) throws Exception {

		ResponseEntity<Map<String, Object>> result = null;
		Map<String, Object> dataMap = new HashMap<>();
		
		try {
			dataMap = laundryOrderService.assignmentOrder(cri);
			result = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR); e.printStackTrace(); }
		 
		return result;
	}
	
	// 버튼을 통한 - 지점 주문 자동 할당
	@RequestMapping(value = "/autoAssignmentOrder", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> autoAssignmentOrder() throws Exception {
		
		ResponseEntity<Map<String, Object>> result = null;
		Map<String, Object> dataMap = new HashMap<>();
		
		try {
			dataMap = laundryOrderService.autoAssignmentOrder();
			result = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR); 
			e.printStackTrace(); 
		}
		return result;
	}
	// 버튼을 통한 - 세탁완료주문 지점의 배송기사에게 자동 할당
	@RequestMapping(value = "/handOverToDeliveryEmployee", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> handOverToDeliveryEmployee(HttpSession session) throws Exception {
		
		ResponseEntity<Map<String, Object>> result = null;
		Map<String, Object> dataMap = new HashMap<>();
		
		EmployeesVO emp = (EmployeesVO) session.getAttribute("loginEmployee");
		
		try {
			dataMap = laundryOrderService.handOverToDeliveryEmployee(emp.getBranchCode());
			result = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR); 
			e.printStackTrace(); 
		}
		return result;
	}

}
