package com.rundering.manage.admin;


import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.ItemOrderVO;
import com.rundering.service.AdminItemOrderService;
import com.rundering.service.ItemOrderService;
import com.rundering.service.ItemService;

@Controller
@RequestMapping("/admin")
public class BranchOrderController {
	
	@Resource(name="adminItemOrderService")
	private AdminItemOrderService adminItemOrderService;
	@Autowired
	ItemOrderService itemOrderService;
	@Autowired
	ItemService itemService;
	
	@RequestMapping("/branchorder/list")
	public String branchItemOrderList(Model model) throws Exception {
		String url = "/admin/branchorder/equipment_order_list";
		Map<String, Object> dataMap = null;
		dataMap = itemOrderService.selectItemOrderList();
		model.addAttribute("dataMap", dataMap);
		
		return url;
	}
	@RequestMapping(value="/branchorder/orderlist",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> ItemOrderList(BranchCriteria cri,@RequestParam( defaultValue = "1") int page){
		ResponseEntity<Map<String, Object>> resp = null;
		cri.setPage(page);
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
	@RequestMapping(value="/branchorder/itemlist",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> itemList(BranchCriteria cri,@RequestParam( defaultValue = "1") int page,HttpSession session){
		ResponseEntity<Map<String, Object>> resp = null;
		cri.setPage(page);
		EmployeesVO emp = (EmployeesVO)session.getAttribute("loginEmployee");
		cri.setPerPageNum(10);
		cri.setBranchCode(emp.getBranchCode());
		Map<String, Object> dataMap=null;
		try {
			dataMap = itemService.selectItemVOList(cri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		
		
		return resp;
	}
	
	
	
	@RequestMapping("/branchorder/detail")
	public ModelAndView branchOrderDetail(String ordercode, ModelAndView mnv) throws SQLException{
		String url = "admin/branchorder/equipment_order_detail";
		
		Map<String, Object> dataMap = adminItemOrderService.getItemOrderDetailList(ordercode);
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value="/branchorder/modifyStatus", method=RequestMethod.POST)
	public String branchOrderModify(ItemOrderVO itemOrder, RedirectAttributes rttr) throws Exception{
		String url="redirect:/admin/branchorder/detail";
		
		adminItemOrderService.modifyStatus(itemOrder);
		
		rttr.addFlashAttribute("from","modify");
		rttr.addAttribute("ordercode", itemOrder.getOrdercode());
		
		return url;
		
	}
}
