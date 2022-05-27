package com.rundering.manage.branch;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.command.Criteria;
import com.rundering.service.ItemOrderService;
import com.rundering.service.LaundryItemsService;

@Controller
@RequestMapping("/branch/laundryitems")
public class LaundryStoresController {
	
	@Autowired
	LaundryItemsService laundryItemsService;
	
	@RequestMapping("/list")
	private ModelAndView laundryItemsList(Criteria cri, ModelAndView mnv) throws Exception {
		String url = "branch/laundryitems/laundryitems_list";
		Map<String, Object> dataMap = laundryItemsService.getLaundryItemsList(cri);
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
}
