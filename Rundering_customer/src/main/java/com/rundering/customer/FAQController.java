package com.rundering.customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.command.FAQModifyCommand;
import com.rundering.command.FAQRegistCommand;
import com.rundering.command.MyOrderCriteria;
import com.rundering.dto.AttachVO;
import com.rundering.dto.FAQVO;
import com.rundering.dto.MemberVO;
import com.rundering.service.FAQService;
import com.rundering.util.GetAttachesByMultipartFileAdapter;

@Controller
@RequestMapping("/question")
public class FAQController {

	@Autowired
	FAQService faqService;
	@Resource(name = "boardPath")
	private String boardPath;

	//아코디언
	@RequestMapping("/faq")
	private ModelAndView frequentlyList(MyOrderCriteria cri, HttpServletRequest request, ModelAndView mnv) throws Exception {
		String url = "question/frequently_questions";
		
		Map<String, Object> dataMap = faqService.getFAQFrequentlyList(cri);
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	// 리스트
	@RequestMapping(value = "/list")
	private ModelAndView faqList(MyOrderCriteria cri, ModelAndView mnv) throws Exception {
		String url = "question/question_list";

		Map<String, Object> dataMap = faqService.getFAQList(cri);
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping("/registForm")
	private String faqRegistForm(Model model, HttpSession session) {
		
		MemberVO member =(MemberVO)session.getAttribute("loginUser");
		try {
			Map<String, Object> dataMap = faqService.getOrderList(member.getMemberNo());
			model.addAttribute("dataMap", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String url = "question/question_regist";

		return url;
	}

	@RequestMapping(value = "/regist",  method = RequestMethod.POST)
	public String faqRegist(FAQRegistCommand faqCmd, HttpServletRequest request, RedirectAttributes rttr) throws Exception {

		String url = "redirect:/question/list";
		
		List<AttachVO> attachList = GetAttachesByMultipartFileAdapter.save(faqCmd.getUploadFile(), this.boardPath,"고객문의");
		
		faqService.regist(faqCmd,attachList);

		rttr.addFlashAttribute("from", "regist");

		return url;
	}

	@RequestMapping(value = "/detail")
	private ModelAndView faqDetail(int faqno, @RequestParam(defaultValue = "") String from, HttpServletRequest request,
			ModelAndView mnv, HttpSession session) throws Exception {

		String url = "question/question_detail";
		Map<String, Object> dataMap = null;

		dataMap = faqService.getFAQModify(faqno);

		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(MyOrderCriteria cri, Model model, HttpSession session, int faqno, ModelAndView mnv) throws Exception {

		String url = "question/question_modify";
		Map<String, Object> dataMap = null;
		dataMap = faqService.getFAQModify(faqno);

		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPost(FAQModifyCommand faqcmd, HttpServletRequest request, RedirectAttributes rttr) throws Exception {

		String url = "redirect:/question/detail";
		

		List<AttachVO> attachList = GetAttachesByMultipartFileAdapter.save(faqcmd.getUploadFile(), this.boardPath,"고객문의");
		
		faqService.modify(faqcmd, attachList);

		rttr.addAttribute("faqno", faqcmd.getFaqno());
		rttr.addFlashAttribute("from", "modify");

		return url;
	}

	@RequestMapping(value = "/remove")
	public ResponseEntity<String> remove(int faqno, RedirectAttributes rttr) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {	
			faqService.remove(faqno);

			rttr.addFlashAttribute("from", "remove");
			rttr.addAttribute("faqno", faqno);
	
			
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
		
		} catch (SQLException e) {
			
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	
		return entity;

	}
}
