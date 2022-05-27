package com.rundering.manage.admin;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.command.Criteria;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.SuggestVO;
import com.rundering.service.SuggestService;

@Controller
@RequestMapping("/admin/suggest")
public class AdminSuggestController {
	
	@Autowired
	SuggestService suggestService;

	@RequestMapping(value = "/list")
	private ModelAndView suggestList(Criteria cri, ModelAndView mnv) throws Exception {
		String url = "admin/suggest/suggest_list";
		
		Map<String, Object> dataMap = suggestService.getSuggestList(cri);
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);

		return mnv;
	}
	
	@RequestMapping(value = "/detail")
	private ModelAndView suggestDetail(int sno, @RequestParam(defaultValue = "") String from,
			HttpServletRequest request, ModelAndView mnv, HttpSession session) throws Exception {

		String url = "admin/suggest/suggest_detail";
		
		Map<String, Object> dataMap = null;
		SuggestVO suggest = null;

		if (!from.equals("list")) {
			dataMap = suggestService.getSuggestModify(sno);
		} else {
			dataMap = suggestService.getSuggest(sno);
			EmployeesVO employees = (EmployeesVO) session.getAttribute("loginEmployee");
			if (employees.getBranchCode().equals("000000")) {
				suggest = suggestService.getCheck(sno);
			}
			url = "redirect:/admin/suggest/detail?sno=" + sno;
		}

		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);

		return mnv;
	}
}
