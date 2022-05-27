package com.rundering.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.BranchCriteria;
import com.rundering.command.BranchPageMaker;
import com.rundering.command.Criteria;
import com.rundering.command.PageMaker;
import com.rundering.dao.AsRequestDAO;
import com.rundering.dao.BranchDAO;
import com.rundering.dao.EmployeesDAO;
import com.rundering.dao.LaundryArticlesDAO;
import com.rundering.dao.LaundryFixturesDAO;
import com.rundering.dao.NotificationDAO;
import com.rundering.dto.AsRequestVO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.NotificationVO;

public class AsRequestServiceImpl implements AsRequestService {

	private AsRequestDAO asRequestDAO;

	public void setAsRequestDAO(AsRequestDAO asRequestDAO) {
		this.asRequestDAO = asRequestDAO;
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
	private LaundryFixturesDAO laundryFixturesDAO;
	public void setLaundryFixturesDAO(LaundryFixturesDAO laundryFixturesDAO) {
		this.laundryFixturesDAO = laundryFixturesDAO;
	}

	@Override
	public Map<String, Object> getAsRequestList(Criteria cri) throws SQLException {

		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 현재 page 번호에 맞게 리스트를 가져오기
		List<AsRequestVO> asRequestList = asRequestDAO.selectSearchAsRequestList(cri);

		// 전체 board 개수
		int totalCount = asRequestDAO.selectSearchAsRequestListCount(cri);

		// PageMaker 생성.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("asRequestList", asRequestList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	@Override
	public Map<String, Object> getBranchAsRequestList(BranchCriteria cri) throws SQLException {

		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 현재 page 번호에 맞게 리스트를 가져오기
		List<AsRequestVO> asRequestList = asRequestDAO.selectBranchSearchAsRequestList(cri);

		// 전체 board 개수
		int totalCount = asRequestDAO.selectBranchSearchAsRequestListCount(cri);

		// PageMaker 생성.
		BranchPageMaker pageMaker = new BranchPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("asRequestList", asRequestList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

//	@Override
//	public AsRequestVO getAsRequest(int asno) throws SQLException {
//		asRequestDAO.checkAsRequest(asno);
//		
//		AsRequestVO asRequest = asRequestDAO.selectAsRequestByAsno(asno);
//		return asRequest;
//	}

	@Override
	public AsRequestVO getCheck(int asno) throws SQLException {
		asRequestDAO.checkAsRequest(asno);

		AsRequestVO asRequest = asRequestDAO.selectAsRequestByAsno(asno);
		return asRequest;
	}

	@Override
	public AsRequestVO getOk(int asno) throws SQLException {
		asRequestDAO.okAsRequest(asno);
		
		AsRequestVO asRequest = asRequestDAO.selectAsRequestByAsno(asno);
		return asRequest;
	}

	@Override
	public AsRequestVO getAsRequestModify(int asno) throws SQLException {
		AsRequestVO asRequest = asRequestDAO.selectAsRequestByAsno(asno);
		return asRequest;
	}

	@Override
	public void regist(AsRequestVO asRequest) throws Exception {
		asRequestDAO.insertAsRequest(asRequest);
		
		//본사 직원들에게 알림
		BranchVO branchVO = branchDAO.selectBranchByBranchCode("000000");
		List<EmployeesVO> employeesList = employeesDAO.selectEmployeesByBranchCode(branchVO.getBranchCode());
		NotificationVO notificationVO = new NotificationVO();
			for (EmployeesVO employeesVO : employeesList) {
				int sequence = notificationDAO.selectNotificationSequenceNextValue();
				notificationVO.setNtcnId(String.valueOf(sequence));
				notificationVO.setEmployeeId(employeesVO.getEmployeeId());
				notificationVO.setNtcnknd("AS"); // 알림종류 공통코드 - AS요청
				notificationVO.setNtcncn(branchDAO.getBranchByCode(asRequest.getBranchCode()).getBranchName());
				notificationVO.setNtcnclickhourUrl("'/runderingmanage/admin/asrequest/list','A020400'");
				notificationDAO.insertNotification(notificationVO);
			}
	}

	@Override
	public void getItemList(AsRequestVO asRequest) throws SQLException {
		asRequestDAO.selectItemList(asRequest);
		
	}

	@Override
	public void modify(AsRequestVO asRequest) throws SQLException {
		asRequestDAO.updateAsRequest(asRequest);
	}

	@Override
	public void remove(int asno) throws SQLException {
		asRequestDAO.deleteAsRequest(asno);
	}


}
