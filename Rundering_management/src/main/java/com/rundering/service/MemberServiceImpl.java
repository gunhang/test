package com.rundering.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.AppCriteria;
import com.rundering.command.AppPageMaker;
import com.rundering.command.CustomerListCriteria;
import com.rundering.command.CustomerListPageMaker;
import com.rundering.dao.BranchDAO;
import com.rundering.dao.EmployeesDAO;
import com.rundering.dao.MemberAddressDAO;
import com.rundering.dao.MemberDAO;
import com.rundering.dao.NotificationDAO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.MemberVO;
import com.rundering.dto.NotificationVO;
import com.rundering.util.UserSha256;

public class MemberServiceImpl implements MemberService {

	MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
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
	private MemberAddressDAO memberAddressDAO; 

	public void setMemberAddressDAO(MemberAddressDAO memberAddressDAO) {
		this.memberAddressDAO = memberAddressDAO;
	}
	
	@Override
	public MemberVO getEmployee(String id) throws Exception {
		MemberVO member = memberDAO.selectEmployeeById(id);
		return member;
	}

	// 사원등록신청
	@Override
	public void applicationEmployee(MemberVO member) throws Exception {
		memberDAO.applicationEmployee(member);
		
		//신청한 지점 직원들에게 알림
		List<EmployeesVO> employeesList = employeesDAO.selectEmployeesByBranchCode(member.getPassword());
		NotificationVO notificationVO = new NotificationVO();
			for (EmployeesVO employeesVO : employeesList) {
				int sequence = notificationDAO.selectNotificationSequenceNextValue();
				notificationVO.setNtcnId(String.valueOf(sequence));
				notificationVO.setEmployeeId(employeesVO.getEmployeeId());
				notificationVO.setNtcnknd("EA"); // 알림종류 공통코드 - 사원등록신청
				notificationVO.setNtcncn(member.getName());
				notificationVO.setNtcnclickhourUrl("'/runderingmanage/admin/employeeapplication/main','A070000'");
				notificationDAO.insertNotification(notificationVO);
			}
		
	}

	// 등록신청 사원의 고유번호 가져오기
	@Override
	public MemberVO getMemberNo(String phone) throws Exception {
		MemberVO member = memberDAO.getMemberNo(phone);
		return member;
	}

	// 로그인시 지점코드가 같은 사원신청 리스트 가져오기
	@Override
	public Map<String, Object> getEmplAppList(AppCriteria cri) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		List<MemberVO> appList = memberDAO.getEmplAppList(cri);

		// 전체 list 개수
		int totalCount = memberDAO.applistCount(cri);
		// PageMaker 생성.
		AppPageMaker pageMaker = new AppPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("appList", appList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	// 등록 신청 사원의 디테일 정보 가져오기
	@Override
	public MemberVO getEmpAppinfo(String memberNO) throws Exception {
		MemberVO mv = memberDAO.getEmpAppinfo(memberNO);

		return mv;
	}
	
	//권한그룹
	@Override
	public List<String> getAuthList(String memberNo) throws Exception {
		List<String> auth = memberDAO.selectAuthByMemberNo(memberNo);
		return auth;
	}

	// 등록 신청 사원 반려
	@Override
	public void removeByNo(String memberNo) throws Exception {
		memberAddressDAO.removeByNo(memberNo);
		memberDAO.removeByNo(memberNo);
	}
	
	//고객 정보 	
	@Override
	public MemberVO getMember(String id) throws Exception {
		MemberVO member = memberDAO.selectMemberById(id);
		return member;
	}

	//고객리스트
	@Override
	public Map<String, Object> getMemberList(CustomerListCriteria cri) throws Exception {
		CustomerListCriteria searchCri = (CustomerListCriteria)cri;
		Map<String, Object> dataMap = new HashMap<String, Object>();

		CustomerListPageMaker pageMaker = new CustomerListPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(memberDAO.selectMemberListCount(searchCri));

		List<MemberVO> memberList = memberDAO.selectMemberList(searchCri);

		dataMap.put("memberList", memberList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}


	// 새로운 비밀번호로 변경
	@Override
	public void modifyPwById(MemberVO mv) throws Exception {
		
		mv.setEmail(UserSha256.encrypt(mv.getEmail()));
		
		memberDAO.modifyPwById(mv);
	}

	//탈퇴 취소
	@Override
	public void cancelSecession(String memberNo) throws Exception {
		memberDAO.updateSecession(memberNo);
	}

}
