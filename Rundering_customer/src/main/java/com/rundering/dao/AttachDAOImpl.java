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
	
	//atch_file_no_seq.nextval
	@Override
	public int selectFileNo() throws Exception {
		int atchFileNoSeq= session.selectOne("Attach-Mapper.selectFileNo");
		return atchFileNoSeq;
	}

	@Override
	public void insertAttach(AttachVO attach) throws SQLException {
		session.update("Attach-Mapper.insertAttach",attach);
	}
	
	//파일첨부 번호로 저장된 파일 정보 가져오기
	@Override
	public List<AttachVO> selectAttachVOByFileNo(String fileNo)  throws Exception{
		return session.selectList("Attach-Mapper.selectAttachVOByFileNo", fileNo);
	}
	
	// 파일 삭제 
	@Override
	public void deleteAttchFileRemoveByFileNo(String fileNo) throws Exception{
		session.delete("Attach-Mapper.deleteAttchFileRemoveByFileNo", fileNo);
	}
	
	//계약서 첨부 파일 수정
	@Override
	public void updateToContractFile(AttachVO attach) throws Exception {
		session.update("Attach-Mapper.updateToContractFile" , attach);
	}
	
	//첨부파일 시퀀스 가져오기
	@Override
	public int getAttachNoSeq(String atchFileNo) throws Exception {
		return session.selectOne("Attach-Mapper.getAttachNoSeq", atchFileNo);
	}
	
	// 파일다운로드를 위한 해당 파일의 정보가져오기
	@Override
	public AttachVO getAttachForDownload(AttachVO attach) throws Exception {
		return session.selectOne("Attach-Mapper.getAttachForDownload", attach);
	}
	
	// 계약서 파일을 가져오기
	@Override
	public List<AttachVO> getContract() throws Exception {
		return session.selectList("Attach-Mapper.getContract");
	}
	
	// 이미지 출력
	@Override
	public List<AttachVO> selectAttachVOByFileNoAndSeq2(AttachVO attach) throws Exception {
		return session.selectList("Attach-Mapper.selectAttachVOByFileNoAndSeq2", attach);
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
}
