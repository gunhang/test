package com.rundering.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignUpController {
	
	@RequestMapping("/3agree")
	public String agree() {
		String url ="/signup/termsConditions_agree";
		
		return url;
	}
	
}
