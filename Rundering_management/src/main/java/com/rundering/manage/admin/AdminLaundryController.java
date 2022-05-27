package com.rundering.manage.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rundering.command.AdminLaundryOrderListCriteria;
import com.rundering.service.LaundryOrderService;

@Controller
@RequestMapping("/admin/laundryorder")
public class AdminLaundryController {
	
	@Resource(name = "laundryOrderService")
	LaundryOrderService laundryOrderService;
	
	@RequestMapping("/list")
	public String laundryOrderList(AdminLaundryOrderListCriteria cri,Model model) throws Exception {
		Map<String, Object> dataMap = laundryOrderService.getAdminlaundryOrderList(cri);
		model.addAllAttributes(dataMap);
		return "admin/laundryorder/laundry_order_list";
	}
	@RequestMapping("/detail")
	public String laundryOrderDetail(String orderNo,Model model) throws Exception { 
		Map<String, Object> dataMap = laundryOrderService.getlaundryOrderByOrderNo(orderNo);
		model.addAllAttributes(dataMap);
		return "admin/laundryorder/laundry_order_detail";
	}

}
