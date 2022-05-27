package com.rundering.manage.admin;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.josephoconnell.html.HTMLInputFilter;
import com.rundering.command.Criteria;
import com.rundering.command.PageMaker;
import com.rundering.dto.MemberVO;
import com.rundering.dto.ReplyVO;
import com.rundering.service.NoticeService;
import com.rundering.service.ReplyService;

@Controller
@RequestMapping("admin/reply")
public class ReplyController {

	@Autowired
	ReplyService replySerivce;
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping(value = "list",method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> replyList(String replyno) {
		ResponseEntity<Map<String, Object>> resp = null;
		Map<String, Object> dataMap=null;
		try {
			dataMap = replySerivce.getRequestReplyList(replyno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		return resp;
	}
	
	
	
	@RequestMapping(value = "regist",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> regist (@RequestParam(defaultValue = "1")String page,ReplyVO reply,HttpSession session,Criteria cri){
		ResponseEntity<String> resp = null;
		MemberVO member = (MemberVO) session.getAttribute("loginMember");
		reply.setMemberno(member.getMemberNo());
		reply.setReplyContent(HTMLInputFilter.htmlSpecialChars(reply.getReplyContent()));
		try {
			replySerivce.registReply(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String replyNo =  String.valueOf(reply.getReplyno());
		
		cri.setPerPageNum(5);
		Map<String, Object> dataMap=null;;
		try {
			dataMap = noticeService.getNoticeReplyList(replyNo, cri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
		System.out.println(pageMaker);
		int realEndPage = pageMaker.getRealEndPage();
		
		resp = new ResponseEntity<String>(realEndPage + "", HttpStatus.OK);		
		
		return resp;
	}
	
	@RequestMapping(value = "modify",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> modify (ReplyVO reply,HttpSession session,Criteria cri,@RequestParam(defaultValue = "1")String page){
		ResponseEntity<String> resp = null;
		reply.setReplyContent(HTMLInputFilter.htmlSpecialChars(reply.getReplyContent()));
		try {
			replySerivce.replyModify(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp = new ResponseEntity<String>(page, HttpStatus.OK);		
		return resp;
	}
	
	
	
	@RequestMapping(value="remove")
	@ResponseBody
	public ResponseEntity<String> remove (ReplyVO reply,@RequestParam(defaultValue = "1")String page, Criteria cri) {
		ResponseEntity<String> resp = null;
		try {
			replySerivce.replyRemove(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String replyNo =  String.valueOf(reply.getReplyno());
		cri.setPerPageNum(5);
		Map<String, Object> dataMap=null;;
		
		try {
			dataMap = noticeService.getNoticeReplyList(replyNo, cri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pageInt = Integer.parseInt(page);
		
		PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
		
		int realEndPage = pageMaker.getRealEndPage();
		if (pageInt > realEndPage) {
			pageInt = realEndPage;
		}
		
		resp = new ResponseEntity<String>(pageInt + "", HttpStatus.OK);		
		
		return resp;
				
		
		
	}
	
	
	
}
