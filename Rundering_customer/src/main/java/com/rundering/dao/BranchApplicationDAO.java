package com.rundering.dao;

import com.rundering.dto.BranchApplicationVO;

public interface BranchApplicationDAO {
	
	// 지점신청
	public void  branchApplicate(BranchApplicationVO bv) throws Exception;
	
	// 지점 신청 확인 들어가기전 본인인증 
	public BranchApplicationVO selectSelfAuthentification(BranchApplicationVO bv) throws Exception;
	
	//지점 신청 심사내역 
	public void updateJudge(BranchApplicationVO bv) throws Exception;
	
}
