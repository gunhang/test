package com.rundering.service;

import com.rundering.dto.AttachVO;
import com.rundering.dto.BranchApplicationVO;

public interface BranchApplicationService {
	
	// 지점신청
	void  branchApplicate(BranchApplicationVO bv, AttachVO attach) throws Exception;
	
	// 지점 신청 확인 들어가기전 본인인증 
	public BranchApplicationVO getSelfAuthentification(BranchApplicationVO bv) throws Exception;
	
	//지점 신청 심사내역
	public void updateJudge(BranchApplicationVO bv) throws Exception;
}
