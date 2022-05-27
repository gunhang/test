package com.rundering.service;

import java.util.Map;

import com.rundering.command.Criteria;
import com.rundering.dto.ReplyVO;

public interface ReplyService {
	
	void registReply(ReplyVO reply) throws Exception;
	Map<String, Object> getRequestReplyList(String replyno) throws Exception;
	void replyRemove(ReplyVO reply) throws Exception;
	void replyModify(ReplyVO reply) throws Exception;
}
