package com.rundering.service;

import java.util.List;
import java.util.Map;

import com.rundering.command.Criteria;
import com.rundering.dto.BranchApplicationVO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.LaundryFixturesVO;
import com.rundering.dto.MemberVO;

public interface BranchApplicationService { 
	public Map<String, Object> selectBranchApplicationList(Criteria cri) throws Exception;
	 
	public void updateRejectContent(BranchApplicationVO branchApplication) throws Exception;

	void updateApproval(BranchApplicationVO branchApplication) throws Exception;

	BranchApplicationVO selectBranchApplication(String applicationNo) throws Exception;
	

	void updateExamination(BranchApplicationVO branchApplication, EmployeesVO emp) throws Exception;

	void updateVoluntaryCheck(BranchApplicationVO branchApplication) throws Exception;

	String selectBranchCode(String area) throws Exception;



	void enrollmentRegist(MemberVO member, BranchVO branch, List<LaundryFixturesVO> laundryFixturesVOList,
			String applicationNo);

	BranchVO selectBranch(String phone) throws Exception;
	
} 
