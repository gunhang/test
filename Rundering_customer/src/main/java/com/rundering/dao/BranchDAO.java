package com.rundering.dao;

import java.util.List;

import com.rundering.dto.BranchVO;

public interface BranchDAO {
	
	// 전체 지점정보 가져오기
	public List<BranchVO> selectBranchList() throws Exception;
	
	//지점조회 - by branchCode
	public BranchVO selectBranchByBranchCode(String branchCode) throws Exception;
	
	// 지역별 지점목록 가져오기 - by 공통코드 지역분류코드 AREA
	public List<BranchVO> selectBranchListByArea(String area) throws Exception;
	
	// 지역의 세탁처리여유량 (지역내지점의 세탁처리가능량 - 현재기준 오늘 지역내지점의 주문할당량 = 처리여유량) by 공통코드 지역분류코드 AREA
	public int selectExcessCapacityOfTodayLaundryByArea(String area) throws Exception;
	
	// 지점의 세탁처리여유량 (지점의 세탁처리가능량 - 현재기준 오늘 지점의 주문할당량 = 처리여유량) by branchCode
	public int selectExcessCapacityOfTodayLaundryByBranchCode(String branchCode) throws Exception;
		
	//로그인시 해당 사원의 지점코드를 통한 지점 정보 가져오기
	public BranchVO getBranchByCode(String branchCode) throws Exception;

	String selectBranchCodeByArea(String area) throws Exception;

	void insertBranch(BranchVO branch) throws Exception;
	
}
