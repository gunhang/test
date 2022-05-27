package com.rundering.manage.branch;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.command.Criteria;
import com.rundering.dto.NoticeVO;
import com.rundering.service.NoticeService;



@Controller
@RequestMapping("/branch/notice")
public class BranchNoticeController {
	
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping(value = "/list")
	private ModelAndView noticeList(Criteria cri, ModelAndView mnv) {
		String url="branch/notice/notice_list";
		Map<String, Object> dataMap = null;		
		try {
			dataMap = noticeService.getNoticeList(cri);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value = "/detail")
	private ModelAndView noticeDetail(int noticeno,  @RequestParam(defaultValue="") String from, ModelAndView mnv) throws Exception {
		String url="branch/notice/notice_detail";
		Map<String, Object> dataMap = null;
		
		if(!from.equals("list")) {
			dataMap = noticeService.getNoticeForModify(noticeno);
		}else {
			dataMap = noticeService.getNotice(noticeno);
			url="redirect:/branch/notice/detail.do?noticeno="+noticeno;
		}
		
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	

	
	
	
}
