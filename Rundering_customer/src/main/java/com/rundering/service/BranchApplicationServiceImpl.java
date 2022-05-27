package com.rundering.service;


import java.util.List;

import com.rundering.dao.AttachDAO;
import com.rundering.dao.BranchApplicationDAO;
import com.rundering.dao.BranchDAO;
import com.rundering.dao.EmployeesDAO;
import com.rundering.dao.NotificationDAO;
import com.rundering.dto.AttachVO;
import com.rundering.dto.BranchApplicationVO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.NotificationVO;



public class BranchApplicationServiceImpl implements BranchApplicationService {

	private BranchApplicationDAO branchApplicationDAO;
	private AttachDAO attachDAO;

	
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}
	public void setBranchApplicationDAO(BranchApplicationDAO branchApplicationDAO) {
		this.branchApplicationDAO = branchApplicationDAO;
	}
	private EmployeesDAO employeesDAO;
	public void setEmployeesDAO(EmployeesDAO employeesDAO) {
		this.employeesDAO = employeesDAO;
	}
	private BranchDAO branchDAO;
	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}
	private NotificationDAO notificationDAO;
	public void setNotificationDAO(NotificationDAO notificationDAO) {
		this.notificationDAO= notificationDAO;
	} 

	// 지점 신청
	@Override
	public void branchApplicate(BranchApplicationVO bv,AttachVO attach) throws Exception {
		
		// FILE_NO 변수에 저장해놓기
		int intNo =  attachDAO.selectFileNo();
		String atchFileNo = intNo + "";  
		attach.setBizType("임대차계약서");
		bv.setLeasecontractFile(atchFileNo);
		attach.setAtchFileNo(atchFileNo);
		attach.setAtchFileSeq(1);
		
		// 임대차계약서(첨부파일) DB에 저장
		attachDAO.insertAttach(attach);
		// 지점신청
		branchApplicationDAO.branchApplicate(bv);
		
		//본사 직원들에게 알림
		BranchVO branchVO = branchDAO.selectBranchByBranchCode("000000");
		List<EmployeesVO> employeesList = employeesDAO.selectEmployeesByBranchCode(branchVO.getBranchCode());
		NotificationVO notificationVO = new NotificationVO();
			for (EmployeesVO employeesVO : employeesList) {
			
				int sequence = notificationDAO.selectNotificationSequenceNextValue();
				notificationVO.setNtcnId(String.valueOf(sequence));
				notificationVO.setEmployeeId(employeesVO.getEmployeeId());
				notificationVO.setNtcnknd("BA"); // 알림종류 공통코드 - 지점신청
				notificationVO.setNtcncn(bv.getApplicateName());
				notificationVO.setNtcnclickhourUrl("'/runderingmanage/admin/branchapplication/contract','A020300'");
				notificationDAO.insertNotification(notificationVO);
			}
	}
	
	
	// 지점 신청 확인 들어가기전 본인인증
	@Override
	public BranchApplicationVO getSelfAuthentification(BranchApplicationVO bv) throws Exception {
		return branchApplicationDAO.selectSelfAuthentification(bv);
	}
	
	//지점 신청 심사내역
	@Override
	public void updateJudge(BranchApplicationVO bv) throws Exception {
		branchApplicationDAO.updateJudge(bv);
		
	}


}
