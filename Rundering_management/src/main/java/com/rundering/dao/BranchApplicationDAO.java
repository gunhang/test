package com.rundering.dao;

import java.util.List;

import com.rundering.command.BranchCriteria;
import com.rundering.command.Criteria;
import com.rundering.dto.BranchApplicationVO;

public interface BranchApplicationDAO {
	
	
	public BranchApplicationVO selectBranchApplicationByapplicationNo(String applicationNo ) throws Exception;

	List<BranchApplicationVO> selectBranchApplication(Criteria cri) throws Exception;
	
	public int selectBranchApplicationCount(Criteria cri) throws Exception;
	
	public void updateApprovalreturnContentsByBranchApplicationVO(BranchApplicationVO branchApplication) throws Exception;
	
	public void updateApprovalreturnYByBranchApplicationVO(BranchApplicationVO branchApplication) throws Exception;

	public void updateExaminationByBranchApplicationVO(BranchApplicationVO branchApplication) throws Exception;

	void updateProgressStatusCode08ByBranchApplicationVO(BranchApplicationVO branchApplication) throws Exception;

	void updateProgressStatusCode10ByBranchApplicationVO(BranchApplicationVO branchApplication) throws Exception;

	int selectWorkBranchApplicationCount() throws Exception;
}
