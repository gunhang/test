package com.rundering.manage.admin;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.command.CustomerListCriteria;
import com.rundering.service.MemberService;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
	
	@Autowired
	MemberService memberService;

	@RequestMapping("/list")
	public ModelAndView customerList(CustomerListCriteria cri, ModelAndView mnv) {
		String url = "admin/customer/customer_list";
		Map<String, Object> dataMap = null;
		try {
			dataMap = memberService.getMemberList(cri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="/cancel",method=RequestMethod.GET)
	public String cancelSecession(String memberNo, RedirectAttributes rttr)throws Exception{
		String url = "redirect:/admin/customer/list";
		
		memberService.cancelSecession(memberNo);
		
		rttr.addAttribute("memberNo",memberNo);
		rttr.addFlashAttribute("from","cancel");
		
		return url;
	}
	

}
