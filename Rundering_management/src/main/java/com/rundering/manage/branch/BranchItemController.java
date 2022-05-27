package com.rundering.manage.branch;

import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.command.BranchCriteria;
import com.rundering.command.BranchPageMaker;
import com.rundering.command.PageMaker;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.ItemOrderDetailVO;
import com.rundering.dto.ItemOrderVO;
import com.rundering.dto.LaundryArticlesVO;
import com.rundering.service.ItemOrderService;
import com.rundering.service.ItemService;

@Controller
@RequestMapping("/branch/item")
public class BranchItemController {
	@Autowired
	ItemService itemService;
	@Autowired
	ItemOrderService itemOrderService;
	
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	private String list(BranchCriteria cri, Model model,HttpSession session) throws Exception {
		String url = "/branch/item/item_list";
		EmployeesVO emp=(EmployeesVO) session.getAttribute("loginEmployee");
		cri.setBranchCode( emp.getBranchCode());
		cri.setPerPageNum(4);
		Map<String, Object> dataMap = null;
		dataMap=itemService.selectItemVOList(cri);
		model.addAttribute("dataMap", dataMap);
		return url;
	}
	@RequestMapping(value="/orderlist",method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Map<String, Object>> orderlist(BranchCriteria cri, ModelAndView mnv,HttpSession session) {
		ResponseEntity<Map<String, Object>> resp = null;
		Map<String, Object> dataMap=null;
		cri.setPerPageNum(4);
		try {
			dataMap = itemOrderService.itemOrdeList(cri,session);
			resp = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		
		 
		return resp;
		
	}
	
	
	@RequestMapping(value="/insertList",method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Map<String, Object>> insertList(BranchCriteria cri,HttpSession session,int page) {
		EmployeesVO emp =(EmployeesVO)session.getAttribute("loginEmployee");
		cri.setPage(page);
		cri.setPerPageNum(4);
		cri.setBranchCode(emp.getBranchCode());
		ResponseEntity<Map<String, Object>> resp = null;
		Map<String, Object> dataMap=null;
		
		try {
			 dataMap = itemService.itemInsertList(cri, "");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp=  new ResponseEntity<Map<String,Object>>(dataMap,HttpStatus.OK);	
		
		return resp;
	}
	@RequestMapping(value="/outlist",method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Map<String, Object>> outList(BranchCriteria cri,HttpSession session,int page) {
		EmployeesVO emp =(EmployeesVO)session.getAttribute("loginEmployee");
		cri.setPage(page);
		cri.setPerPageNum(4);
		cri.setBranchCode(emp.getBranchCode());
		ResponseEntity<Map<String, Object>> resp = null;
		Map<String, Object> dataMap=null;
		
		try {
			 dataMap = itemService.itemOutList(cri, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp=  new ResponseEntity<Map<String,Object>>(dataMap,HttpStatus.OK);	
		
		return resp;
	}
	@RequestMapping("outcancel")
	@ResponseBody
	private ResponseEntity<String> outCancel(String outItemCode,BranchCriteria cri,HttpSession session,String page){
		ResponseEntity<String> resp = null;
		try {
			itemService.updateSupplyCountCancel(outItemCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		EmployeesVO emp=(EmployeesVO) session.getAttribute("loginEmployee");
		cri.setBranchCode( emp.getBranchCode());
		cri.setPerPageNum(4);
		Map<String, Object> dataMap = null;
		
		try {
			dataMap=itemService.itemOutList(cri, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pageInt = Integer.parseInt(page);
		
		BranchPageMaker pageMaker = (BranchPageMaker) dataMap.get("pageMaker");
		
		int realEndPage = pageMaker.getRealEndPage();
		if (pageInt > realEndPage) {
			pageInt = realEndPage;
			page =String.valueOf(pageInt);
		}
		
		resp = new ResponseEntity<String>(page,HttpStatus.OK);
		
		return resp;
		
	}
	
	@RequestMapping("/detail")
	private ModelAndView detail(String ordercode,ModelAndView mnv,HttpSession session) throws Exception{
		String url= "/branch/itemorder/itemorder_detail";
		String branchCode = itemOrderService.getBranchCode(ordercode);
		EmployeesVO employee =(EmployeesVO) session.getAttribute("loginEmployee");
		Map<String, String> comCodeMap=itemOrderService.comCode();
		
		if(employee==null) {
			url ="/common/loginform";
			mnv.setViewName(url);
			return mnv;
		}
		
		if(!branchCode.equals(employee.getBranchCode())){
			url ="/branch/index";
			mnv.setViewName(url);
			return mnv;
		}
		ItemOrderVO itemOrder = itemOrderService.getItemOrder(ordercode);
		List<ItemOrderDetailVO> itemOrderDetailList= itemOrderService.getItemOrdeDetail(ordercode);
		List<LaundryArticlesVO> laundryArticlesList = itemOrderService.getLaundryArticles();
		Map<String, String> articlesMap = new HashMap<String, String>();
		for (LaundryArticlesVO LaundryArticles : laundryArticlesList) {
			articlesMap.put(LaundryArticles.getArticlesCode(), LaundryArticles.getArticlesName());
		}
		
		mnv.addObject("articlesMap", articlesMap);
		
		mnv.addObject("itemOrderDetailList", itemOrderDetailList);
		mnv.addObject("itemOrder", itemOrder);
		mnv.addObject("comCodeMap",comCodeMap);
		mnv.setViewName(url);
		return mnv;
	} 
	@RequestMapping("/modify")
	private String modify(String ordercode,RedirectAttributes rttr) throws Exception{
		String url = "redirect:/branch/itemorder/detail?ordercode="+ordercode;
		ItemOrderVO itemOrder = new ItemOrderVO();
		itemOrder.setOrdercode(ordercode);
		itemOrder.setItemOrderStatus("06");
		itemOrderService.updateState(itemOrder);
		//여기에 재고관리 쪽 재고 업데이트 나중에 넣어야 함
		rttr.addFlashAttribute("from", "modify");
		return url;
	}
	@RequestMapping("/remove")
	private String remove(String ordercode,RedirectAttributes rttr) throws Exception{
		String url = "redirect:/branch/itemorder/list";
		itemOrderService.deleteItemorder(ordercode);
		
		rttr.addFlashAttribute("from", "remove");
		return url;
	}
	@RequestMapping("/notreceived")
	private String notreceived(String ordercode,RedirectAttributes rttr) throws Exception{
		String url = "redirect:/branch/itemorder/detail?ordercode="+ordercode;
		ItemOrderVO itemOrder = new ItemOrderVO();
		itemOrder.setOrdercode(ordercode);
		itemOrder.setItemOrderStatus("04");
		itemOrderService.updateStateNotRecive(itemOrder);
		rttr.addFlashAttribute("from", "modify"); 
		return url;
	}
	
	

}
