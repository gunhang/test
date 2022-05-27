package com.rundering.customer;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.service.LaundryItemsService;

@Controller
@RequestMapping("/guide")
public class GuideController {
	
	@Resource(name="laundryItemsService")
	private LaundryItemsService laundryItemsService;

	@RequestMapping("/price/list")
	public ModelAndView priceList(ModelAndView mnv) throws Exception {
		String url = "/guide/price_guide";
		
		Map<String, Object> dataMap = laundryItemsService.getlaundryItemsList();
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/howuse")
	public String howuse() {
		String url = "/guide/how_use";
		return url;
	}
	
	@RequestMapping("/area")
	public String servicearea() {
		String url = "/guide/service_area";
		return url;
	}

	
	
}
