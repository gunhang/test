package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.Criteria;
import com.rundering.dto.ReplyVO;

public class ReplyDAOImpl implements ReplyDAO{
	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override 
	public List<ReplyVO> selectReplyByReplyNo(int replyNo) throws Exception{
		return session.selectList("Reply-Mapper.selectReplyByReplyNo", replyNo);
	}
	@Override 
	public List<ReplyVO> selectReplyByReplyNo(int replyNo,Criteria cri) throws Exception{
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return session.selectList("Reply-Mapper.selectReplyByReplyNo", replyNo,rowBounds);
		
	}
	@Override
	public int selectReplySeq() throws Exception{
		return session.selectOne("Reply-Mapper.selectReplySeq");
	}
	@Override
	public void insertReplyByReplyVO(ReplyVO reply) throws Exception{
		 session.insert("Reply-Mapper.insertReplyByReplyVO",reply);
	}
	@Override
	public void insertReplyByReplyVOFirst(ReplyVO reply) throws Exception{
		session.insert("Reply-Mapper.insertReplyByReplyVOFirst",reply);
	}

	@Override 
	public int selectReplyCountByReplyno(int replyno) throws Exception {
		return session.selectOne("Reply-Mapper.selectReplyCountByReplyno", replyno);
	}
	@Override
	public int selectReplyCheckByReplyno(int replyno) throws Exception{
		return session.selectOne("Reply-Mapper.selectReplyCheckByReplyno",replyno);
	}
	@Override
	public void deleteReply(ReplyVO reply) throws Exception{
		session.delete("Reply-Mapper.deleteReply",reply);
	}
	@Override
	public void updateReply(ReplyVO reply) throws Exception{
		session.update("Reply-Mapper.updateReply",reply);
	}
	
	
	
	 
}
