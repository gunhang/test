package com.rundering.service;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.FAQModifyCommand;
import com.rundering.command.FAQRegistCommand;
import com.rundering.command.MyOrderCriteria;
import com.rundering.command.MyOrderPageMaker;
import com.rundering.dao.AttachDAO;
import com.rundering.dao.BranchDAO;
import com.rundering.dao.EmployeesDAO;
import com.rundering.dao.FAQDAO;
import com.rundering.dao.NotificationDAO;
import com.rundering.dto.AttachVO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.FAQVO;
import com.rundering.dto.NotificationVO;

public class FAQServiceImpl implements FAQService {
	
	private FAQDAO faqDAO;
	
	public void setFaqDAO(FAQDAO faqDAO) {
		this.faqDAO = faqDAO;
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
	public Map<String, Object> getFAQList(MyOrderCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 현재 page 번호에 맞게 리스트를 가져오기
		List<FAQVO> faqList = faqDAO.selectSearchFAQList(cri);

		// 전체 board 개수
		int totalCount = faqDAO.selectSearchFAQListCount(cri);

		// PageMaker 생성.
		MyOrderPageMaker pageMaker = new MyOrderPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("faqList", faqList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	
	
	
	@Override
	public Map<String, Object> getFAQModify(int faqno) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		FAQVO faq = faqDAO.selectFAQByFaqno(faqno);
		if(faq != null && faq.getAtchFileNo() != null) {
			List<AttachVO> attachList = attachDAO.selectAttachVOByFileNo(faq.getAtchFileNo());
			dataMap.put("attachList", attachList);
		}
		
		dataMap.put("faq", faq);
		
		return dataMap;
	}
	
	@Override
	public void regist(FAQRegistCommand faqcmd, List<AttachVO> attachList) throws Exception {
		
		int faqno = faqDAO.selectFAQSequenceNextValue();
		
		FAQVO faqvo = faqcmd.toFAQVO();
		faqvo.setFaqno(faqno);
		
		faqDAO.insertFAQ(faqvo);
		
		if(faqvo.getOrderno() != null && !faqvo.getOrderno().isEmpty()) {
			faqDAO.updateFAQOrderNo(faqvo);
		}
		
		if(attachList != null && attachList.size() > 0) {
			int atchFileNo = attachDAO.selectFileNo();

			for (AttachVO attach : attachList) {
				attach.setAtchFileNo(String.valueOf(atchFileNo));
				attachDAO.insertAttach(attach);
			}
			faqvo.setAtchFileNo(String.valueOf(atchFileNo));
			faqDAO.updateFAQAtchFileNo(faqvo);
		}
		
		
		//본사 직원들에게 알림
		BranchVO branchVO = branchDAO.selectBranchByBranchCode("000000");
		List<EmployeesVO> employeesList = employeesDAO.selectEmployeesByBranchCode(branchVO.getBranchCode());
		NotificationVO notificationVO = new NotificationVO();
			for (EmployeesVO employeesVO : employeesList) {
				int sequence = notificationDAO.selectNotificationSequenceNextValue();
				notificationVO.setNtcnId(String.valueOf(sequence));
				notificationVO.setEmployeeId(employeesVO.getEmployeeId());
				notificationVO.setNtcnknd("IQ"); // 알림종류 공통코드 - 고객문의
				notificationVO.setNtcncn(faqvo.getQuestion());
				notificationVO.setNtcnclickhourUrl("'/runderingmanage/admin/question/list','A060200'");
				notificationDAO.insertNotification(notificationVO);
			}
	}
	
	@Override
	public void modify(FAQModifyCommand faqcmd, List<AttachVO> attachList) throws Exception {
		FAQVO faqvo = faqcmd.toFAQVO();
		String atchFileNo = faqDAO.selectFAQByFaqno(faqvo.getFaqno()).getAtchFileNo();
		
		faqDAO.updateFAQ(faqvo);
		
		if(atchFileNo != null) { //글에 첨부파일이 있었을 경우
			// 파일 삭제
			if (faqcmd.getDeleteFile() != null && faqcmd.getDeleteFile().size() > 0) {
				//삭제 파일이 1개인 경우 ,을 기준으로 List에 index0과 1로 나눠 저장되어 들어옴
				if(!faqcmd.getDeleteFile().get(0).contains(",")) {
					AttachVO attach = new AttachVO();
					attach.setAtchFileNo(atchFileNo);
					attach.setAtchFileSeq(Integer.parseInt(faqcmd.getDeleteFile().get(1)));
					attach = attachDAO.selectAttachVOByFileNoAndSeq(attach);
					
					removeAttach(attach);
				}else {
					for (String atchFileNoAndSeq : faqcmd.getDeleteFile()) {
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
				faqvo.setAtchFileNo(String.valueOf(atchFileNo));
			}
		}
		
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
	public void remove(int faqno) throws Exception {
		//첨부파일 있는지 확인 후 삭제
		String atchFileNo = faqDAO.selectFAQByFaqno(faqno).getAtchFileNo();
		if(atchFileNo != null) { 
			List<AttachVO> attachList = attachDAO.selectAttachVOByFileNo(atchFileNo);
			if (attachList.size() > 0) for (AttachVO attachVO : attachList) {
					removeAttach(attachVO);
			}
		}
		
		faqDAO.deleteFAQ(faqno);
	}
	
	/* 주문번호 */
	@Override
	public Map<String, Object> getOrderList(String memberNo) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		List<FAQVO> orderList = faqDAO.selectOrderno(memberNo);

		dataMap.put("orderList", orderList);

		return dataMap;
	}

	/* 아코디언 */	
	@Override
	public Map<String, Object> getFAQFrequentlyList(MyOrderCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		// 현재 page 번호에 맞게 리스트를 가져오기
		List<FAQVO> frequentlyList = faqDAO.selectFrequentlyList(cri);
		
		// 전체 board 개수
		int totalCount = faqDAO.selectFrequentlyListCount(cri);
		
		// PageMaker 생성.
		MyOrderPageMaker pageMaker = new MyOrderPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("frequentlyList", frequentlyList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	// 내 문의내역
	@Override
	public Map<String, Object> getMyFAQList(MyOrderCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 현재 page 번호에 맞게 리스트를 가져오기
		List<FAQVO> faqList = faqDAO.MypageFAQList(cri);

		// 전체 board 개수
		int totalCount = faqDAO.MypageFAQListCount(cri);

		// PageMaker 생성.
		MyOrderPageMaker pageMaker = new MyOrderPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("faqList", faqList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

}
