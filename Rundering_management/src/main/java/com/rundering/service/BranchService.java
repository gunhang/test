package com.rundering.service;

import java.util.List;

import com.rundering.dto.BranchVO;

public interface BranchService {

	// 전체 지점정보 가져오기
	public List<BranchVO> getBranchList() throws Exception;
	
	// 로그인시 해당 사원의 지점코드를 통한 지점 정보 가져오기
	public BranchVO getBranchByCode(String branchCode) throws Exception;
	
	
		
}
