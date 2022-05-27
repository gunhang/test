package com.rundering.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.Criteria;
import com.rundering.command.PageMaker;
import com.rundering.dao.LaundryOrderDAO;
import com.rundering.dao.MemberDAO;
import com.rundering.dao.ReplyDAO;
import com.rundering.dto.LaundryOrderVO;
import com.rundering.dto.MemberVO;
import com.rundering.dto.ReplyVO;
import com.rundering.util.SensSms;

public class ReplyServiceImpl implements ReplyService {
	ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}

	MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	private SensSms sensSms;
	public void setSensSms(SensSms sensSms) {
		this.sensSms = sensSms;
	}
	private LaundryOrderDAO laundryOrderDAO;
	public void setLaundryOrderDAO(LaundryOrderDAO laundryOrderDAO) {
		this.laundryOrderDAO = laundryOrderDAO;
	}
	
	@Override
	public Map<String, Object> getRequestReplyList(String replyno) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int replynoInt =Integer.parseInt(replyno); 
		List<ReplyVO> replyList = replyDAO.selectReplyByReplyNo(replynoInt);
		dataMap.put("replyList", replyList);
		
		return dataMap;
	}
	
	@Override
	public void registReply(ReplyVO reply) throws Exception {
		int count =replyDAO.selectReplyCheckByReplyno(reply.getReplyno());
		reply.setCount(count);
		replyDAO.insertReplyByReplyVO(reply);

		LaundryOrderVO orderVO = laundryOrderDAO.selectLaundryOrderByReplyNo(reply.getReplyno());
		//댓글등록시 문자
		try {
		sensSms.sendSMS(orderVO.getContactNumber().trim(), "[Rundering]\n지점의 연락사항이 있습니다.\n주문내역에서 확인해주세요.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void replyRemove(ReplyVO reply) throws Exception {
		replyDAO.deleteReply(reply);
	}
	@Override
	public void replyModify(ReplyVO reply) throws Exception{
		replyDAO.updateReply(reply);
	}

}
