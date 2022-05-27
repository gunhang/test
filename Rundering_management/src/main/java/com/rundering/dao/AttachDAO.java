package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.dto.AttachVO;

public interface AttachDAO {
	public void insertOrderGoodsAtach(AttachVO attach) throws Exception;
	
	public int selectFileNo() throws Exception;
	
	public List<AttachVO> selectAttachByArticlesCode(int atchFileNo) throws Exception;
	public void insertAttach(AttachVO attach) throws SQLException;
	public List<AttachVO> selectAttachVOByFileNo(String fileNo) throws Exception;
	public void deleteAttchFileRemoveByFileNo(String fileNo) throws Exception;
	//atchFileNo 과 atchFileSeq 로 파일 삭제
	public void deleteAttchFileRemoveByFileNoAndSeq(AttachVO attach) throws Exception;
	// 파일번호의 시퀸스를 구하기위해 받아오는 DAO
	public int getAttachNoSeq(String atchFileNo) throws Exception;
	// 수거 및 배송 취소시 업로드된 이미지 삭제
	public void deleteDeliveryPicture(AttachVO attach)throws Exception;
	// 파일다운로드를 위한 해당 파일의 정보가져오기(파일번호,uuid붙어 저장된 이름)
	public AttachVO getAttachForDownload(AttachVO attach) throws Exception;
	// 파일다운로드를 위한 해당 파일의 정보가져오기(파일번호,업무구분)
	public AttachVO getDownloadFile(AttachVO attach) throws Exception;
	//ATTACHVO 가져오기 - atchFileNo 과 atchFileSeq 로
	public AttachVO selectAttachVOByFileNoAndSeq(AttachVO attach) throws Exception;
	//attach의 현재 존재하는 마지막 seq번호 가져오기
	public int selectLastSeqAttachVOByFileNo(String atchFileNo) throws Exception;
	
	public List<AttachVO> selectAttachVOByFileNoAndSeq2(AttachVO attach) throws Exception;

}


