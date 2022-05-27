package com.rundering.dao;

import java.util.List;

import com.rundering.dto.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> selectReplyByReplyNo(String replyNo) throws Exception;

	public int selectReplySeq() throws Exception;

	public void insertReplyByReplyVO(ReplyVO reply) throws Exception;

	public void insertReplyByReplyVOFirst(ReplyVO reply) throws Exception;
	
	public int selectReplyCountByReplyno(String replyno) throws Exception;
	
	// 마이페이지 - 디테일 요청사항 댓글 등록
	public void insertMypageRe(ReplyVO rv) throws Exception;
	// 시퀀스 계산
	public int getSeq(int replyno) throws Exception;
	// 댓글 리스트 불러오기
	public List<ReplyVO> getReList(int replyno) throws Exception;
	// 댓글 삭제
	public void deleteReply(int replyno)throws Exception;
	// 댓글 삭제(삭제여부'Y'로 변경)
	public void removeReply(ReplyVO rv)throws Exception;
	// 댓글 수정
	public void modifyReply(ReplyVO rv)throws Exception;
}
