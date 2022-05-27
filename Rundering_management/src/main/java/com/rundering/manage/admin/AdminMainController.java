package com.rundering.manage.admin;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.command.BranchCriteria;
import com.rundering.command.Criteria;
import com.rundering.service.ItemOrderService;
import com.rundering.service.LaundryOrderService;
import com.rundering.service.NoticeService;
import com.rundering.service.SuggestService;


@RequestMapping("/admin/main")
@Controller
public class AdminMainController {
	
	@Autowired
	NoticeService noticeService;
	@Autowired
	SuggestService suggestService;
	@Autowired
	ItemOrderService itemOrderService;
	@Autowired
	LaundryOrderService laundryOrderService;
	
	
	@RequestMapping(value = "noticelist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> mainNoticeList(Criteria cri) {
		Map<String, Object> dataMap = null;
		try {
			dataMap=noticeService.getNoticeList(cri);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResponseEntity<Map<String, Object>> resp = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		return resp;
	}
	@RequestMapping(value = "suggestlist")
	private ResponseEntity<Map<String, Object>> suggestList(Criteria cri)  {
		Map<String, Object> dataMap = null;
		try {
			 dataMap = suggestService.getSuggestList(cri);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResponseEntity<Map<String, Object>> resp = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		return resp;

	}
	@RequestMapping(value="/orderlist",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> orderList(BranchCriteria cri){
		ResponseEntity<Map<String, Object>> resp = null;
		Map<String, Object> dataMap=null;
		try {
			dataMap = itemOrderService.adminItemOrdeList(cri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		
		
		return resp;
	}
	@RequestMapping(value="/chart",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public ResponseEntity<Map<String, Object>> orderList(){
		ResponseEntity<Map<String, Object>> resp = null;
		Map<String, Object> dataMap=null;
		try {
			dataMap = laundryOrderService.piChart();
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		
		
		return resp;
	}
	
}
