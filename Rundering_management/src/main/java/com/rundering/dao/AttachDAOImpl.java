package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.AttachVO;

public class AttachDAOImpl implements AttachDAO{
	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	@Override
	public int selectFileNo() throws Exception {
		int seq= session.selectOne("Attach-Mapper.selectFileNo");
		return seq;
	}

	@Override
	public void insertOrderGoodsAtach(AttachVO attach) throws Exception {
		session.update("Attach-Mapper.insertLaundryArticlesAttach",attach);
	}
	
	@Override
	public List<AttachVO> selectAttachByArticlesCode(int atchFileNo) throws Exception {
		List<AttachVO> articles = session.selectList("Attach-Mapper.selectLaundryArticlesAttach", atchFileNo); 
		return articles;
	}
	@Override
	public void insertAttach(AttachVO attach) throws SQLException {
		session.update("Attach-Mapper.insertAttach",attach);
	}
	@Override
	public List<AttachVO> selectAttachVOByFileNo(String fileNo)  throws Exception{
		return session.selectList("Attach-Mapper.selectAttachVOByFileNo", fileNo);
	}
	@Override
	public void deleteAttchFileRemoveByFileNo(String fileNo) throws Exception{
		session.delete("Attach-Mapper.deleteAttchFileRemoveByFileNo", fileNo);
	}
	
	// 파일번호의 시퀸스를 구하기위해 받아오는 DAO
	@Override
	public int getAttachNoSeq(String atchFileNo) throws Exception {
		return session.selectOne("Attach-Mapper.getAttachNoSeq", atchFileNo);
	}
	// 수거 및 배송 취소시 업로드된 이미지 삭제
	@Override
	public void deleteDeliveryPicture(AttachVO attach) throws Exception {
		session.selectOne("Attach-Mapper.deleteDeliveryPicture", attach);
		
	}
	// 파일다운로드를 위한 해당 파일의 정보가져오기
	@Override
	public AttachVO getAttachForDownload(AttachVO attach) throws Exception {
		return session.selectOne("Attach-Mapper.getAttachForDownload", attach);
	}
	@Override
	public void deleteAttchFileRemoveByFileNoAndSeq(AttachVO attach) throws Exception {
		session.delete("Attach-Mapper.deleteAttchFileRemoveByFileNoAndSeq", attach);
	}
	@Override
	public AttachVO selectAttachVOByFileNoAndSeq(AttachVO attach) throws Exception {
		return session.selectOne("Attach-Mapper.selectAttachVOByFileNoAndSeq", attach);
	}
	@Override
	public int selectLastSeqAttachVOByFileNo(String atchFileNo) throws Exception {
		return session.selectOne("Attach-Mapper.selectLastSeqAttachVOByFileNo", atchFileNo);
	}
	@Override
	public List<AttachVO> selectAttachVOByFileNoAndSeq2(AttachVO attach) throws Exception {
		return session.selectList("Attach-Mapper.selectAttachVOByFileNoAndSeq2", attach);
	}
	// 파일다운로드를 위한 해당 파일의 정보가져오기(파일번호,업무구분)
	@Override
	public AttachVO getDownloadFile(AttachVO attach) throws Exception {
		return session.selectOne("Attach-Mapper.getDownloadFile", attach);
	}
	
	
	

}
