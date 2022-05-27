package com.rundering.manage.admin;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.command.Criteria;
import com.rundering.command.NoticeModifyCommand;
import com.rundering.command.NoticeRegistCommand;
import com.rundering.dto.AttachVO;
import com.rundering.dto.NoticeVO;
import com.rundering.manage.HomeController;
import com.rundering.service.AttachService;
import com.rundering.service.NoticeService;
import com.rundering.util.GetAttachesByMultipartFileAdapter;

@Controller
@RequestMapping("/admin/notice")
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	NoticeService noticeService;
	@Autowired
	AttachService attachService;
	@Resource(name = "boardPath")
	private String boardPath;
	
	
	@RequestMapping("/list")
	public ModelAndView noticeList(Criteria cri, ModelAndView mnv) {
		String url = "admin/notice/notice_list";
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
	
	
	@RequestMapping("/registform")
	public String noticeRegistForm() throws Exception{
		String url = "/admin/notice/notice_regist";
		
		return url;
	}
	
	@RequestMapping(value = "/noticereplylist",method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
	@ResponseBody
	private Map<String, Object> replyList(String replyno,Criteria cri,@RequestParam(defaultValue = "1")String page){
		Map<String, Object> dataMap=null;
		int pageInt = Integer.parseInt(page);
		cri.setPerPageNum(5);
		cri.setPage(pageInt);
		try {
			dataMap = noticeService.getNoticeReplyList(replyno,cri);
				// TODO Auto-generated catch block
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	
	@RequestMapping(value="/regist",  method = RequestMethod.POST)
	public String regist(NoticeRegistCommand noticecmd,HttpServletRequest request,
						RedirectAttributes rttr) throws Exception{
		String url = "redirect:/admin/notice/list";
		
		//notice.setTitle((String)request.getAttribute("XSStitle"));
		
		List<AttachVO> attachList = GetAttachesByMultipartFileAdapter.save(noticecmd.getUploadFile(), this.boardPath,"공지사항");
		
		noticeService.regist(noticecmd, attachList);		
		
		rttr.addFlashAttribute("from","regist");
		
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView noticeDetail(int noticeno,  @RequestParam(defaultValue="") String from, ModelAndView mnv) throws Exception {
		
		String url="admin/notice/notice_detail";
		Map<String, Object> dataMap = null;
		if(!from.equals("list")) {
			dataMap = noticeService.getNoticeForModify(noticeno);
		}else {
			dataMap = noticeService.getNotice(noticeno);
			url="redirect:/admin/notice/detail.do?noticeno="+noticeno;
		}
		
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(int noticeno,ModelAndView mnv) throws Exception{
		String url="admin/notice/notice_modify";
		
		Map<String, Object> dataMap = null;
		dataMap = noticeService.getNoticeForModify(noticeno);
		
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String noticeModify(NoticeModifyCommand noticecmd,
						     HttpServletRequest request,
							 RedirectAttributes rttr)throws Exception{
		String url = "redirect:/admin/notice/detail";
		
		//notice.setTitle(HTMLInputFilter.htmlSpecialChars(notice.getTitle()));
		//notice.setTitle((String)request.getAttribute("XSStitle"));
		
		

		// 파일 저장
		List<AttachVO> attachList = GetAttachesByMultipartFileAdapter.save(noticecmd.getUploadFile(), this.boardPath,"공지사항");

		
		noticeService.modify(noticecmd, attachList);
		
		rttr.addAttribute("noticeno",noticecmd.getNoticeno());
		rttr.addFlashAttribute("from","modify");
		
		return url;
	}
	
	
	@RequestMapping(value="/remove",method=RequestMethod.GET)
	public String remove(int noticeno,RedirectAttributes rttr) throws Exception{
		String url="redirect:/admin/notice/detail";
			
		noticeService.remove(noticeno);		
		
		rttr.addFlashAttribute("from","remove");
		rttr.addAttribute("noticeno",noticeno);
		
		return url;
	}
	
	
	@RequestMapping(value = "/suggest", method = RequestMethod.GET)
	public String noticeSuggest(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "admin/notice/suggest";
	}
	
	@RequestMapping(value = "/suggestdetail", method = RequestMethod.GET)
	public String noticeSuggestDetail(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "admin/notice/suggest_detail";
	}
	
	
}
