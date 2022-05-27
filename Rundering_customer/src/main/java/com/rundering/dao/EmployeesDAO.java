package com.rundering.dao;

import java.util.List;

import com.rundering.dto.EmployeesVO;

public interface EmployeesDAO {
	
    //지점에 속한 배송사원 목록 가져오기 - by branchCode
    public List<EmployeesVO> selectDeliveryDepartmentEmployeesByBranchCode(String branchCode);
    
    //지점에 속한 사원 목록 가져오기 - by branchCode
    public List<EmployeesVO> selectEmployeesByBranchCode(String branchCode);
    
}
