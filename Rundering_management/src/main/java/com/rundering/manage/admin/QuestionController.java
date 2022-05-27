package com.rundering.manage.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/question")
public class QuestionController {
	
	@RequestMapping("/frequently")
	public String questionFrequently() {
		return "admin/question/frequently_questions";
	}
	@RequestMapping("/reply")
	public String questionReply() {
		return "admin/question/question_reply";
	}
}
