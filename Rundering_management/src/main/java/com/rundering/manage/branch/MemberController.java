package com.rundering.manage.branch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/branch/member")
public class MemberController {

	
	@RequestMapping("/list")
	private String list() {
		String url="branch/member/member_list";
		return url;
	}
}
