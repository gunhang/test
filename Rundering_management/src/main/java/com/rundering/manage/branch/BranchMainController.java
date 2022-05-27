package com.rundering.manage.branch;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rundering.command.BranchCriteria;
import com.rundering.command.Criteria;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.OrderDelayDTO;
import com.rundering.service.ItemOrderService;
import com.rundering.service.LaundryOrderService;
import com.rundering.service.NoticeService;

@RequestMapping("/branch/main")
@Controller
public class BranchMainController {
	
	@Autowired
	NoticeService noticeService;
	@Autowired
	ItemOrderService itemOrderService;
	@Autowired
	LaundryOrderService laundryOrderService;
	
	
	@RequestMapping(value = "noticelist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> BranchMainNoticeList(Criteria cri) {
		Map<String, Object> dataMap = null;
		try {
			dataMap=noticeService.getNoticeList(cri);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResponseEntity<Map<String, Object>> resp = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		return resp;
	}
	@RequestMapping(value="/orderlist",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> BranchMainOrderList(BranchCriteria cri,HttpSession session){
		ResponseEntity<Map<String, Object>> resp = null;
		Map<String, Object> dataMap=null;
		cri.setPerPageNum(10);
		try {
			dataMap = itemOrderService.itemOrdeList(cri, session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		
		
		return resp;
	}
	@RequestMapping(value="/chart",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> BaranchMainOrderChart(HttpSession session){
		ResponseEntity<Map<String, Object>> resp = null;
		Map<String, Object> dataMap=null;
		EmployeesVO emp =(EmployeesVO)session.getAttribute("loginEmployee");
		String branchCode = emp.getBranchCode();
		try {
			dataMap = laundryOrderService.branchOrderPiChart(branchCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		
		
		return resp;
	}
	@RequestMapping(value="/orderdelay",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> orderDelay(OrderDelayDTO orderDelay,HttpSession session){
		ResponseEntity<Map<String, Object>> resp = null;
		Map<String, Object> dataMap=null;
		EmployeesVO emp =(EmployeesVO)session.getAttribute("loginEmployee");
		orderDelay.setBranchCode(emp.getBranchCode()); 
		try {
			
			dataMap = laundryOrderService.orderDelay(orderDelay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		
		
		return resp;
	}
}
