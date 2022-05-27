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
	// 파일다운로드를 위한 해당 파일의 정보가져오기
	public AttachVO getAttachForDownload(AttachVO attach) throws Exception {
		return attachDAO.getAttachForDownload(attach);
	}
	
	// 이미지 출력
	public List<AttachVO> selectAttachVOByFileNoAndSeq2(AttachVO attach) throws Exception {
		return attachDAO.selectAttachVOByFileNoAndSeq2(attach);
	}
	
	// 파일다운로드를 위한 해당 파일의 정보가져오기(파일번호,업무구분)
	public AttachVO getDownloadFile(AttachVO attach) throws Exception {
		return attachDAO.getDownloadFile(attach);
	}
}
