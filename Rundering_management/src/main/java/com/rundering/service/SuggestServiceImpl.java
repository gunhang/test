package com.rundering.service;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.Criteria;
import com.rundering.command.PageMaker;
import com.rundering.command.SuggestModifyCommand;
import com.rundering.command.SuggestRegistCommand;
import com.rundering.dao.AttachDAO;
import com.rundering.dao.BranchDAO;
import com.rundering.dao.EmployeesDAO;
import com.rundering.dao.NotificationDAO;
import com.rundering.dao.SuggestDAO;
import com.rundering.dto.AttachVO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.NotificationVO;
import com.rundering.dto.SuggestVO;

public class SuggestServiceImpl implements SuggestService {

	private SuggestDAO suggestDAO;

	public void setSuggestDAO(SuggestDAO suggestDAO) {
		this.suggestDAO = suggestDAO;
	}
	private BranchDAO branchDAO;
	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}
	private EmployeesDAO employeesDAO;
	public void setEmployeesDAO(EmployeesDAO employeesDAO) {
		this.employeesDAO = employeesDAO;
	}
	private NotificationDAO notificationDAO;
	public void setNotificationDAO(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}

	@Override
	public Map<String, Object> getSuggestList(Criteria cri) throws SQLException {

		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 현재 page 번호에 맞게 리스트를 가져오기
		List<SuggestVO> suggestList = suggestDAO.selectSearchSuggestList(cri);

		// 전체 board 개수
		int totalCount = suggestDAO.selectSearchSuggestListCount(cri);

		// PageMaker 생성.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("suggestList", suggestList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	@Override
	public Map<String, Object> getSuggest(int sno) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		suggestDAO.increaseViewCount(sno);

		SuggestVO suggest = suggestDAO.selectSuggestBySno(sno);
		if(suggest != null && suggest.getAtchFileNo() != null) {
			List<AttachVO> attachList = attachDAO.selectAttachVOByFileNo(suggest.getAtchFileNo());
			dataMap.put("attachList", attachList);
		}
		
		dataMap.put("suggest", suggest);
		return dataMap;
	}

	@Override
	public SuggestVO getCheck(int sno) throws SQLException {
		suggestDAO.checkSuggest(sno);
		
		SuggestVO suggest = suggestDAO.selectSuggestBySno(sno);
		return suggest;
	}

	@Override
	public Map<String, Object> getSuggestModify(int sno) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SuggestVO suggest = suggestDAO.selectSuggestBySno(sno);
		if(suggest != null && suggest.getAtchFileNo() != null) {
			List<AttachVO> attachList = attachDAO.selectAttachVOByFileNo(suggest.getAtchFileNo());
			dataMap.put("attachList", attachList);
		}
		
		dataMap.put("suggest", suggest);
		return dataMap;
	}

	@Override
	public void regist(SuggestRegistCommand suggest, List<AttachVO> attachList) throws Exception {
		SuggestVO suggestVO = suggest.toSuggestVO();
		
		if(attachList != null && attachList.size() > 0) {
			int atchFileNo = attachDAO.selectFileNo();

			for (AttachVO attach : attachList) {
				attach.setAtchFileNo(String.valueOf(atchFileNo));
				attachDAO.insertAttach(attach);
			}
			suggestVO.setAtchFileNo(String.valueOf(atchFileNo));
		}
		
		suggestDAO.insertSuggest(suggestVO);
		
		//본사 직원들에게 알림
		BranchVO branchVO = branchDAO.selectBranchByBranchCode("000000");
		List<EmployeesVO> employeesList = employeesDAO.selectEmployeesByBranchCode(branchVO.getBranchCode());
		NotificationVO notificationVO = new NotificationVO();
			for (EmployeesVO employeesVO : employeesList) {
				int sequence = notificationDAO.selectNotificationSequenceNextValue();
				notificationVO.setNtcnId(String.valueOf(sequence));
				notificationVO.setEmployeeId(employeesVO.getEmployeeId());
				notificationVO.setNtcnknd("SG"); // 알림종류 공통코드 - 건의사항
				notificationVO.setNtcncn(branchDAO.getBranchByCode(suggest.getBranchName()).getBranchName());
				notificationVO.setNtcnclickhourUrl("'/runderingmanage/admin/suggest/list','A050200'");
				notificationDAO.insertNotification(notificationVO);
			}
	}

	@Override
	public void modify(SuggestModifyCommand suggestcmd, List<AttachVO> attachList) throws Exception {
		SuggestVO suggestVO = suggestcmd.toSuggestVO();
		String atchFileNo = suggestDAO.selectSuggestBySno(suggestVO.getSno()).getAtchFileNo();
		
		if(atchFileNo != null) { //글에 첨부파일이 있었을 경우
			// 파일 삭제
			if (suggestcmd.getDeleteFile() != null && suggestcmd.getDeleteFile().size() > 0) {
				//삭제 파일이 1개인 경우 ,을 기준으로 List에 index0과 1로 나눠 저장되어 들어옴
				if(!suggestcmd.getDeleteFile().get(0).contains(",")) {
					AttachVO attach = new AttachVO();
					attach.setAtchFileNo(atchFileNo);
					attach.setAtchFileSeq(Integer.parseInt(suggestcmd.getDeleteFile().get(1)));
					attach = attachDAO.selectAttachVOByFileNoAndSeq(attach);
					
					removeAttach(attach);
				}else {
					for (String atchFileNoAndSeq : suggestcmd.getDeleteFile()) {
						String[] split = atchFileNoAndSeq.split(",");
						int atchFileSeq = Integer.parseInt(split[1]);
						
						AttachVO attach = new AttachVO();
						attach.setAtchFileNo(atchFileNo);
						attach.setAtchFileSeq(atchFileSeq);
						attach = attachDAO.selectAttachVOByFileNoAndSeq(attach);

						removeAttach(attach);
					}					
				}
			}
			// 파일 추가시
			if(attachList != null && attachList.size() >0) {
				int attachNoSeq = attachDAO.getAttachNoSeq(atchFileNo);
				if(attachNoSeq > 0) {
					int lastSeq = attachDAO.selectLastSeqAttachVOByFileNo(atchFileNo);
					for (AttachVO attach : attachList) {
						attach.setAtchFileNo(atchFileNo);
						attach.setAtchFileSeq(++lastSeq);
						attachDAO.insertAttach(attach);
					}
				}else {
					//첨부파일이 있었으나 모두 삭제해서 첨부파일번호만 공지사항테이블에 존재하는 경우
					for (AttachVO attach : attachList) {
						attach.setAtchFileNo(atchFileNo);
						attachDAO.insertAttach(attach);
					}
				}
			}
			
		}else {//글에 첨부파일이 없었을 경우
			if(attachList != null && attachList.size() >0) {
				int addAtchFileNo = attachDAO.selectFileNo();
	
				for (AttachVO attach : attachList) {
					attach.setAtchFileNo(String.valueOf(addAtchFileNo));
					attachDAO.insertAttach(attach);
				}
				suggestVO.setAtchFileNo(String.valueOf(atchFileNo));
			}
		}
		
		suggestDAO.updateSuggest(suggestVO);
	}
	
	//attach 파일, DB삭제
		private void removeAttach(AttachVO attach) throws Exception {
			// DB에 저장된 저장경로 참고! - properties의 저장경로 변경 가능성
			File deleteFile = new File(attach.getFilePath(), attach.getSaveFileNm());

			if (deleteFile.exists()) {
				deleteFile.delete(); // File삭제
			}
			attachDAO.deleteAttchFileRemoveByFileNoAndSeq(attach); //DB삭제
		}

	@Override
	public void remove(int sno) throws Exception {
		String atchFileNo = suggestDAO.selectSuggestBySno(sno).getAtchFileNo();
		if(atchFileNo != null) { 
			List<AttachVO> attachList = attachDAO.selectAttachVOByFileNo(atchFileNo);
			if (attachList.size() > 0) for (AttachVO attachVO : attachList) {
					removeAttach(attachVO);
				}
		}
		
		suggestDAO.deleteSuggest(sno);
	}
}
