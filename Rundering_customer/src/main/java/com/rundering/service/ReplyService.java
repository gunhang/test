package com.rundering.service;

import com.rundering.dao.ReplyDAO;
import com.rundering.dto.ReplyVO;

public class ReplyService {

	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	
	public void insertMypageRe(ReplyVO rv) throws Exception {
		// 시퀀스 번호 구하기
		int seq = replyDAO.getSeq(rv.getReplyno());
		rv.setReplynoSeq(++seq);
		// 댓글 등록
		replyDAO.insertMypageRe(rv);
	}

	// 댓글 삭제
	public void removeReply(ReplyVO rv) throws Exception{
		replyDAO.removeReply(rv);
	}
	
	// 댓글 수정
	public void modifyReply(ReplyVO rv) throws Exception{
		replyDAO.modifyReply(rv);
	}
	
}
