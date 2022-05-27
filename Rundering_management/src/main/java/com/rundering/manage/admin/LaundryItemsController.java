package com.rundering.manage.admin;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.command.Criteria;
import com.rundering.command.LaundryItemsRegistCommand;
import com.rundering.dto.LaundryItemsVO;
import com.rundering.service.LaundryItemsService;


@Controller
@RequestMapping("/admin/laundryitems")
public class LaundryItemsController {
	
	@Resource(name = "laundryItemsService")
	private LaundryItemsService laundryItemsService;
	
	@RequestMapping("/list")
	public ModelAndView laundryItemsList(Criteria cri, ModelAndView mnv) throws Exception {
		String url = "admin/laundryitems/laundryitems_list";
		Map<String, Object> dataMap = laundryItemsService.getLaundryItemsList(cri);
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		return mnv;

	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String laundryItemRegistForm() {
		return "admin/laundryitems/laundryitems_regist";
	}
	@RequestMapping(value =  "/regist", method = RequestMethod.POST)
	public String laundryItemRegist(LaundryItemsRegistCommand command, ModelAndView mnv,RedirectAttributes rttr  ) throws SQLException {
		
		LaundryItemsVO laundryItems = command.toLaundryItemsVO();
		laundryItemsService.regist(laundryItems);
		rttr.addFlashAttribute("from", "regist");
		return "redirect:/admin/laundryitems/regist";
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView laundryItemModify(String laundryItemsCode, ModelAndView mnv) throws SQLException {
		String url="admin/laundryitems/laundryitems_modify";
		LaundryItemsVO laundryItems = laundryItemsService.getLaundryItemsByItemCode(laundryItemsCode);
		
		mnv.addObject("laundryItems",laundryItems);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String Modify(LaundryItemsVO laundryItems,
						     HttpServletRequest request,
							 RedirectAttributes rttr)throws Exception{
		String url = "redirect:/admin/laundryitems/modifyForm";
		
		laundryItemsService.modify(laundryItems);
		
		rttr.addAttribute("laundryItemsCode",laundryItems.getLaundryItemsCode());
		rttr.addFlashAttribute("from","laundryItems");
		return url;
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.GET)
	public String remove(String laundryItemsCode,RedirectAttributes rttr) throws Exception{
		String url="redirect:/admin/laundryitems/list";
			
		System.out.println(laundryItemsCode);
		laundryItemsService.remove(laundryItemsCode);		
		
		rttr.addAttribute("laundryItemsCode",laundryItemsCode);
		rttr.addFlashAttribute("from","remove");
		
		return url;
	}
	
}
