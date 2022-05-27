package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.EmployeesVO;

public class EmployeesDAOImpl implements EmployeesDAO{
	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	 //지점에 속한 배송사원 목록 가져오기 - by branchCode
	@Override
	public List<EmployeesVO> selectDeliveryDepartmentEmployeesByBranchCode(String branchCode) {
		List<EmployeesVO> deliveryDepartmentEmployees = session.selectList("Employees-Mapper.selectDeliveryDepartmentEmployeesByBranchCode",branchCode);
		return deliveryDepartmentEmployees;
	}
		 
	public List<EmployeesVO> selectEmployeesByBranchCode(String branchCode) {
		List<EmployeesVO> employees = session.selectList("Employees-Mapper.selectEmployeesByBranchCode",branchCode);
		return employees;
	}
	
	
 
	
}
