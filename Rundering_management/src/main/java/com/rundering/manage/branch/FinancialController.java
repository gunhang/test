package com.rundering.manage.branch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/branch/financial")
public class FinancialController {
	
	@RequestMapping("/laundrysaleslist")
	private String laundrySalesList() {
		String url = "branch/financial/laundrysales_list";
		return url;
	}
	@RequestMapping("/memberlist")
	private String memberList() {
		String url = "branch/financial/member_list";
		return url;
	}
	@RequestMapping("/saleslist")
	private String salesList() {
		String url = "branch/financial/sales_list";
		return url;
	}
		
}
