package com.rundering.service;

import java.util.ArrayList;
import java.util.List;

import com.rundering.dao.AttachDAO;
import com.rundering.dto.AttachVO;

public class AttachService {

	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}

	public List<AttachVO> getAttachVOList(String fileNo) throws Exception {
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		attachList = attachDAO.selectAttachVOByFileNo(fileNo);
		return attachList;
	}
	
	public List<AttachVO> contract() throws Exception{
		List<AttachVO> contractList = new ArrayList<AttachVO>();
		contractList = attachDAO.getContract();
		return contractList;
	} 

	public void fileRemove(String fileNo) throws Exception {
		attachDAO.deleteAttchFileRemoveByFileNo(fileNo);
	}

	public int insertFile(List<AttachVO> attachList) throws Exception {
		int atchFileNo = attachDAO.selectFileNo();

		for (AttachVO attach : attachList) {
			attach.setAtchFileNo(String.valueOf(atchFileNo));
			attachDAO.insertAttach(attach);
		}
		return atchFileNo;
	}
	
	// 계약서 신청 파일 수정
	public void updateToContractFile(AttachVO attach) throws Exception{
		attachDAO.updateToContractFile(attach);
	}
	
	// 지점 신청 계약서 첨부파일 추가(등록)
	public void insertContractFile(AttachVO attach) throws Exception{
		int seq = attachDAO.getAttachNoSeq(attach.getAtchFileNo());
		
		attach.setAtchFileSeq(++seq);
		
		attachDAO.insertAttach(attach);
	}
	
	// 파일다운로드를 위한 해당 파일의 정보가져오기
	public AttachVO getAttachForDownload(AttachVO attach) throws Exception {
		return attachDAO.getAttachForDownload(attach);
	}
	
	// 이미지 출력
	public List<AttachVO> selectAttachVOByFileNoAndSeq2(AttachVO attach) throws Exception {
		return attachDAO.selectAttachVOByFileNoAndSeq2(attach);
	}
}
