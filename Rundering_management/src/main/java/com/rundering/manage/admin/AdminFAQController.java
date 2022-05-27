package com.rundering.manage.admin;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.command.AcoCriteria;
import com.rundering.command.FAQCriteria;
import com.rundering.dto.FAQVO;
import com.rundering.dto.MemberVO;
import com.rundering.service.FAQService;

@Controller
@RequestMapping("/admin/question")
public class AdminFAQController {

	@Autowired
	FAQService faqService;
	
	//아코디언
	@RequestMapping("/faq")
	private ModelAndView frequentlyList(AcoCriteria cri, HttpServletRequest request, ModelAndView mnv) throws Exception {
		String url = "admin/question/frequently_questions";
		
		Map<String, Object> dataMap = faqService.getFAQFrequentlyList(cri);
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
		
	// 리스트
	@RequestMapping(value = "/list")
	private ModelAndView faqList(FAQCriteria cri, ModelAndView mnv) throws Exception {
		String url = "admin/question/question_list";

		Map<String, Object> dataMap = faqService.getFAQList(cri);
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping(value = "/detail")
	private ModelAndView faqDetail(int faqno, @RequestParam(defaultValue = "") String from, HttpServletRequest request,
			ModelAndView mnv, HttpSession session) throws Exception {

		String url = "admin/question/question_detail";
		Map<String, Object> dataMap = null;


		dataMap = faqService.getFAQReply(faqno);

		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping("/replyForm")
	public ModelAndView replyForm(int faqno, ModelAndView mnv) throws Exception {

		String url = "admin/question/question_reply";
		
		Map<String, Object> dataMap = null;
		dataMap = faqService.getFAQReply(faqno);

		mnv.addAllObjects(dataMap);

		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String replyPost(FAQVO faq, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/admin/question/detail";
		
		faqService.reply(faq);

		rttr.addAttribute("faqno", faq.getFaqno());
		rttr.addFlashAttribute("from", "reply");

		return url;
	}
	
	
	// 자주묻는질문 등록
	@RequestMapping("/insert")
	@ResponseBody
	public ResponseEntity<String> insert(HttpServletRequest request,FAQVO faq)throws Exception {
		ResponseEntity<String> entity = new ResponseEntity<String>("OK", HttpStatus.OK);
		//세션을 통한 사원의 고유번호 불러오기
		HttpSession session = request.getSession();
		MemberVO mv = (MemberVO)session.getAttribute("loginMember");
		
		faq.setWriter(mv.getMemberNo());

		faqService.insertQnA(faq);
		
		return entity;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResponseEntity<String> modify(FAQVO fv) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			faqService.modify(fv);
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@RequestMapping("/remove")
	public ResponseEntity<String> remove(int faqno) throws Exception {
		ResponseEntity<String> entity = null;

		try {
			faqService.remove(faqno);
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
}
