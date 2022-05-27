package com.rundering.service;

import java.util.List;
import java.util.Map;

import com.rundering.command.EmployeesCommand;
import com.rundering.command.EmployeesListCriteria;
import com.rundering.dto.ComCodeVO;
import com.rundering.dto.EmployeesVO;

public interface EmployeesService {
	
	public EmployeesVO getEmployee(String memberno) throws Exception;
	
	//사원리스트 가져오기
	public Map<String, Object> getEmployeeList(EmployeesListCriteria cri) throws Exception;
	
	//사원등록
	public void employeeRegist(EmployeesVO ev) throws Exception;
	
	//전체 부서 가져오기
	public List<ComCodeVO> getDepartment() throws Exception;
	
	//전체 부서 가져오기
	public List<ComCodeVO> getPosition() throws Exception;
	
	//고유번호를 이용한 사원 조회
    public EmployeesVO getEmployeeByNo(String memberno) throws Exception;
    
    //지점코드로 지점명 가져오기
    public String getBranchName(String branchCode) throws Exception;
    
}
