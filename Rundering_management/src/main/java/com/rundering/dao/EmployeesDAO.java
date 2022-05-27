package com.rundering.dao;

import java.util.List;

import com.rundering.command.CustomerListCriteria;
import com.rundering.command.EmployeesCommand;
import com.rundering.command.EmployeesListCriteria;
import com.rundering.dto.EmployeesVO;

public interface EmployeesDAO {
	
	public EmployeesVO selectEmployeeByMno(String MemberNo) throws Exception;

	//사원리스트 조회
	public List<EmployeesCommand> selectEmployeeList(EmployeesListCriteria cri) throws Exception;
	
	// 일반 리스트 전체 개수
	int selectEmployeeListCount() throws Exception;

	// 검색 결과의 전체 리스트 개수
	int selectEmployeeListCount(EmployeesListCriteria cri) throws Exception;

	//사원등록
    public void employeeRegist(EmployeesVO ev) throws Exception;
    
    //고유번호를 이용한 사원 조회
    public EmployeesVO getEmployeeByNo(String memberno) throws Exception;
    
    //지점에 속한 배송사원 목록 가져오기 - by branchCode
    public List<EmployeesVO> selectDeliveryDepartmentEmployeesByBranchCode(String branchCode);
    
    //지점에 속한 사원 목록 가져오기 - by branchCode
    public List<EmployeesVO> selectEmployeesByBranchCode(String branchCode);
    
    public void employeeBranchRegist(EmployeesVO employee) throws Exception;
    
    public EmployeesVO selectEmployeeByMemberNo(String memberNo) throws Exception;
}
