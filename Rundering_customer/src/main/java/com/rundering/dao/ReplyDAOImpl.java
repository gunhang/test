package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.ReplyVO;

public class ReplyDAOImpl implements ReplyDAO{
	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<ReplyVO> selectReplyByReplyNo(String replyNo) throws Exception{
		return session.selectList("Reply-Mapper.selectReplyByReplyNo", replyNo);
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
	public int selectReplyCountByReplyno(String replyno) throws Exception {
		return session.selectOne("Reply-Mapper.selectReplyCountByReplyno", replyno);
	}
	
	// 마이페이지 - 디테일 요청사항 댓글 등록
	@Override
	public void insertMypageRe(ReplyVO rv) throws Exception {
		session.update("Reply-Mapper.insertMypageRe", rv);
	}

	// 시퀀스 계산
	@Override
	public int getSeq(int replyno) throws Exception {
		return session.selectOne("Reply-Mapper.getSeq", replyno);
	}

	// 댓글 리스트 불러오기
	@Override
	public List<ReplyVO> getReList(int replyno) throws Exception {
		return session.selectList("Reply-Mapper.getReList", replyno);
	}

	// 댓글 삭제
	@Override
	public void deleteReply(int replyno) throws Exception {
		session.delete("Reply-Mapper.deleteReply", replyno);
	}

	// 댓글 삭제(삭제여부'Y'로 변경)
	@Override
	public void removeReply(ReplyVO rv) throws Exception {
		session.update("Reply-Mapper.removeReply", rv);
	}
	
	// 댓글 수정
	@Override
	public void modifyReply(ReplyVO rv) throws Exception {
		session.update("Reply-Mapper.updateReply", rv);
	}
	
	
}
