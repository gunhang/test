package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.dto.AttachVO;

public interface AttachDAO {
	
	//atch_file_no_seq.nextval 가져오기
	public int selectFileNo() throws Exception;
	//파일정보 insert
	public void insertAttach(AttachVO attach) throws SQLException;
	public List<AttachVO> selectAttachVOByFileNo(String fileNo) throws Exception;
	// 파일 삭제
	public  void deleteAttchFileRemoveByFileNo(String fileNo) throws Exception;
	//atchFileNo 과 atchFileSeq 로 파일 삭제
	public void deleteAttchFileRemoveByFileNoAndSeq(AttachVO attach) throws Exception;
	// 계약서 첨부 파일 수정
	public void updateToContractFile(AttachVO attach) throws Exception;
	//첨부파일 시퀀스 가져오기
	public int getAttachNoSeq(String atchFileNo)throws Exception;
	// 파일다운로드를 위한 해당 파일의 정보가져오기
	public AttachVO getAttachForDownload(AttachVO attach) throws Exception;
	// 계약서 파일을 가져오기
	public List<AttachVO> getContract() throws Exception;
	// 이미지 출력
	public List<AttachVO> selectAttachVOByFileNoAndSeq2(AttachVO attach) throws Exception;
	//ATTACHVO 가져오기 - atchFileNo 과 atchFileSeq 로
	public AttachVO selectAttachVOByFileNoAndSeq(AttachVO attach) throws Exception;
	//attach의 현재 존재하는 마지막 seq번호 가져오기
	public int selectLastSeqAttachVOByFileNo(String atchFileNo) throws Exception;
}
