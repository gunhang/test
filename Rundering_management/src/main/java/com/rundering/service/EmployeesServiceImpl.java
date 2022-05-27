package com.rundering.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.EmployeesCommand;
import com.rundering.command.EmployeesListCriteria;
import com.rundering.command.EmployeesListPageMaker;
import com.rundering.dao.BranchDAO;
import com.rundering.dao.ComCodeDAO;
import com.rundering.dao.EmployeesDAO;
import com.rundering.dao.MemberDAO;
import com.rundering.dto.ComCodeVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.MemberVO;
import com.rundering.util.UserSha256;

public class EmployeesServiceImpl implements EmployeesService {

	EmployeesDAO employeesDAO;

	MemberDAO memberDAO;

	ComCodeDAO comCodeDAO;
	
	BranchDAO branchDAO;

	public void setEmployeesDAO(EmployeesDAO employeesDAO) {
		this.employeesDAO = employeesDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	public void setComCodeDAO(ComCodeDAO comCodeDAO) {
		this.comCodeDAO = comCodeDAO;
	}
	
	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}

	@Override
	public EmployeesVO getEmployee(String memberno) throws Exception {
		EmployeesVO employee = employeesDAO.selectEmployeeByMno(memberno);
		return employee;
	}

	// 사원등록
	@Override
	public void employeeRegist(EmployeesVO ev) throws Exception {

		employeesDAO.employeeRegist(ev);

		ev = employeesDAO.getEmployeeByNo(ev.getMemberno());

		if (ev != null) {

			// 임시비밀번호 해쉬처리
			String pw = UserSha256.encrypt(ev.getEmployeeId());

			MemberVO mv = new MemberVO();

			mv.setId(ev.getEmployeeId());
			mv.setPassword(pw);
			mv.setMemberNo(ev.getMemberno());

			memberDAO.updateMember(mv);

		}

	}

	// 전체 부서 가져오기
	@Override
	public List<ComCodeVO> getDepartment() throws Exception {

		return comCodeDAO.getDepartment();
	}

	// 전체 직책 가져오기
	@Override
	public List<ComCodeVO> getPosition() throws Exception {

		return comCodeDAO.getPosition();
	}

	// 고유번호를 이용한 사원 조회
	@Override
	public EmployeesVO getEmployeeByNo(String memberno) throws Exception {

		EmployeesVO ev = employeesDAO.getEmployeeByNo(memberno);

		return ev;
	}

	@Override
	public String getBranchName(String branchCode) throws Exception {
		return branchDAO.getBranchByCode(branchCode).getBranchName();
	}

	//사원리스트 조회
	@Override
	public Map<String, Object> getEmployeeList(EmployeesListCriteria cri) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		EmployeesListPageMaker pageMaker = new EmployeesListPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(employeesDAO.selectEmployeeListCount(cri));
		
		List<EmployeesCommand> employeesList = employeesDAO.selectEmployeeList(cri);
		
		dataMap.put("employeesList", employeesList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}



}
