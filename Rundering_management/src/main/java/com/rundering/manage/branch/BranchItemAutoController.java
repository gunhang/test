package com.rundering.manage.branch;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.BranchVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.ItemVO;
import com.rundering.dto.LaundryArticlesVO;
import com.rundering.dto.LaundryGoodsStockVO;
import com.rundering.service.ItemService;
import com.rundering.service.LaundryArticlesService;

@Controller
@RequestMapping("/branch/itemauto")
public class BranchItemAutoController {
	
	@Autowired
	ItemService itemService;

	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	private String itemAutolist(BranchCriteria cri, Model model,HttpSession session) throws Exception {
		String url = "/branch/itemauto/item_list";
		EmployeesVO emp=(EmployeesVO) session.getAttribute("loginEmployee");
		cri.setBranchCode( emp.getBranchCode());
		cri.setPerPageNum(4);
		Map<String, Object> dataMap = null;
		dataMap=itemService.selectItemVOList(cri);
		model.addAttribute("dataMap", dataMap);
		return url;
	}
	@RequestMapping(value="autouse",method = RequestMethod.POST)
	private String autouse(LaundryGoodsStockVO laundryGoodsStock,HttpSession session) throws Exception{
		String url = "redirect:/branch/itemauto/list";
		EmployeesVO emp=(EmployeesVO) session.getAttribute("loginEmployee");
		laundryGoodsStock.setBranchCode(emp.getBranchCode());
		itemService.useAutoYn(laundryGoodsStock);
		
		
		return url;
	}
	@RequestMapping(value = "autosavepoint",method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<String> autosavepoint(LaundryGoodsStockVO laundryGoodsStock,HttpSession session) {
		ResponseEntity<String> resp = null;
		EmployeesVO emp=(EmployeesVO) session.getAttribute("loginEmployee");
		laundryGoodsStock.setBranchCode(emp.getBranchCode());
		try {
			itemService.updatePoint(laundryGoodsStock);
			resp = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resp;
	}
	@RequestMapping(value="autoButton",method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<String> autoButton(String branchCode, HttpSession session) {
		
	    EmployeesVO emp =  (EmployeesVO)session.getAttribute("loginEmployee");
	    branchCode=emp.getBranchCode();
		try {
			itemService.branchAutoOrder(branchCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<String> resp = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return resp;
	
	}
	
	@RequestMapping(value = "autosavecount",method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<String> autosavecount(LaundryGoodsStockVO laundryGoodsStock,HttpSession session) {
		ResponseEntity<String> resp = null;
		EmployeesVO emp=(EmployeesVO) session.getAttribute("loginEmployee");
		laundryGoodsStock.setBranchCode(emp.getBranchCode());
		try {
			itemService.updateCount(laundryGoodsStock);
			resp = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resp;
	}
	
	@RequestMapping(value = "savesupplycount",method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<String> savesupplycount(LaundryGoodsStockVO laundryGoodsStock,HttpSession session) {
		ResponseEntity<String> resp = null;
		EmployeesVO emp=(EmployeesVO) session.getAttribute("loginEmployee");
		laundryGoodsStock.setBranchCode(emp.getBranchCode());
		try {
			itemService.updateSupllyCount(laundryGoodsStock);
			resp = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resp;
	}
	
	
	
	@RequestMapping(value="chart",method =RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	private ResponseEntity<List<ItemVO>> chart(int chartDay,String articlesCode,HttpSession session){
		ItemVO item = new ItemVO();
		item.setArticlesCode(articlesCode);
		EmployeesVO emp = (EmployeesVO)session.getAttribute("loginEmployee");
		item.setBranchCode(emp.getBranchCode());
		
		List<ItemVO> itemList = new ArrayList<ItemVO>();
		ResponseEntity<List<ItemVO>> resp =null;
		
			try {
				if(chartDay == 1) {
					itemList=itemService.selectDDItemByItem(item);
				}
				if(chartDay == 30) {
					itemList=itemService.selectMMItemByItem(item);
				}
				if(chartDay == 90) {
					itemList=itemService.select3MItemByItem(item);
				}
				if(chartDay == 365) {
					itemList=itemService.selectYYItemByItem(item);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		resp= new ResponseEntity<List<ItemVO>>(itemList, HttpStatus.OK);
		
		
		return resp;
		
	}
	@RequestMapping(value="chartout",method =RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	private ResponseEntity<List<ItemVO>> chartOut(int chartDay,String articlesCode,HttpSession session){
		ItemVO item = new ItemVO();
		item.setArticlesCode(articlesCode);
		EmployeesVO emp = (EmployeesVO)session.getAttribute("loginEmployee");
		item.setBranchCode(emp.getBranchCode());
		
		List<ItemVO> itemList = new ArrayList<ItemVO>();
		ResponseEntity<List<ItemVO>> resp =null;
		
			try {
				if(chartDay == 1) {
					itemList=itemService.selectDDItemOutByItem(item);
				}
				if(chartDay == 30) {
					itemList=itemService.selectMMItemOutByItem(item);
				}
				if(chartDay == 90) {
					itemList=itemService.select3MItemOutByItem(item);
				}
				if(chartDay == 365) {
					itemList=itemService.selectYYItemOutByItem(item);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		resp= new ResponseEntity<List<ItemVO>>(itemList, HttpStatus.OK);
		
		
		return resp;
		
	}
	

}
