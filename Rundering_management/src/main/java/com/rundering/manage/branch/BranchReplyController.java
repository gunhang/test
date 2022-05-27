package com.rundering.manage.branch;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.josephoconnell.html.HTMLInputFilter;
import com.rundering.command.Criteria;
import com.rundering.dto.MemberVO;
import com.rundering.dto.ReplyVO;
import com.rundering.service.ReplyService;

@Controller
@RequestMapping("branch/reply")
public class BranchReplyController {

	@Autowired
	ReplyService replySerivce;
	
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
	public ResponseEntity<String> regist (ReplyVO reply,HttpSession session,Criteria cri){
		ResponseEntity<String> resp = null;
		MemberVO member = (MemberVO) session.getAttribute("loginMember");
		reply.setMemberno(member.getMemberNo());
		reply.setReplyContent(HTMLInputFilter.htmlSpecialChars(reply.getReplyContent()));
		try {
			replySerivce.registReply(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	@RequestMapping(value = "modify",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> modify (ReplyVO reply,HttpSession session,Criteria cri){
		ResponseEntity<String> resp = null;
		reply.setReplyContent(HTMLInputFilter.htmlSpecialChars(reply.getReplyContent()));
		try {
			replySerivce.replyModify(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	@RequestMapping(value="remove")
	@ResponseBody
	public void remove (ReplyVO reply) {
		try {
			replySerivce.replyRemove(reply);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
