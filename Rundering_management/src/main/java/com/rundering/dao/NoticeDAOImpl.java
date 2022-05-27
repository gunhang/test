package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.Criteria;
import com.rundering.dto.NoticeVO;


public class NoticeDAOImpl implements NoticeDAO {

	private SqlSession session;
	
	
	public void setSession(SqlSession session) {
		this.session = session;
	}


	@Override
	public List<NoticeVO> selectNoticeList(Criteria cri) throws SQLException {
		
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<NoticeVO> noticeList = session.selectList("Notice-Mapper.selectSearchNoticeList",cri,rowBounds);
		return noticeList;
	}


	@Override
	public int selectSearchNoticeListCount(Criteria cri) throws SQLException {
		int count = session.selectOne("Notice-Mapper.selectSearchNoticeListCount", cri);
		return count;
	}


	@Override
	public NoticeVO selectNoticeByNno(int noticeno) throws SQLException {
		NoticeVO number = session.selectOne("Notice-Mapper.selectNoticeByNno", noticeno);
		return number;
	}


	@Override
	public NoticeVO selectNoticeByImage(String imageFile) throws SQLException {
		NoticeVO imagefile = session.selectOne("ns.id", imageFile);
		return imagefile;
	}


	@Override
	public void increaseViewCount(int noticeno) throws SQLException {
		session.selectOne("Notice-Mapper.increaseViewCount", noticeno);
	}


	@Override
	public int selectNoticeSequenceNextValue() throws SQLException {
		int seq_num=session.selectOne("Notice-Mapper.selectNoticeSequenceNextValue");
		return seq_num;
	}
	
	@Override
	public int selectReplySequenceNextValue() throws SQLException {
		int seq_num=session.selectOne("Notice-Mapper.selectReplySequenceNextValue");
		return seq_num;
	}

	@Override
	public void insertNotice(NoticeVO notice) throws SQLException {
		session.update("Notice-Mapper.insertNotice", notice);
	}


	@Override
	public void updateNotice(NoticeVO notice) throws SQLException {
		session.update("Notice-Mapper.updateNotice", notice);	
	}


	@Override
	public void deleteNotice(int noticeno) throws SQLException {
		session.update("Notice-Mapper.deleteNotice", noticeno);
	}


	


}
