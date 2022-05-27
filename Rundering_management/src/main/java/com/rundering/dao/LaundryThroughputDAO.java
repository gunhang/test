package com.rundering.dao;

import java.util.List;

import com.rundering.command.AppCriteria;
import com.rundering.command.BranchInfoDetailCommand;
import com.rundering.dto.BranchVO;
import com.rundering.dto.LaundryThroughPutVO;

public interface LaundryThroughputDAO {
	public List<LaundryThroughPutVO> throughputList() throws Exception;

	public List<LaundryThroughPutVO> throughputList(AppCriteria cri) throws Exception;

	public int throughputListCount(AppCriteria cri) throws Exception;
	
	public LaundryThroughPutVO selectLaundryQuatoByThroughputNo(String throughputNo) throws Exception;
	
	public BranchInfoDetailCommand selectBranchDetail(String branchCode) throws Exception;
	
	//표 리스트 출력(지점별)
	public List<LaundryThroughPutVO> branchQuotaTable(String branchCode) throws Exception;
	//표 리스트 출력(일자별)
	public List<LaundryThroughPutVO> branchTableDate(String date) throws Exception;
	//branchCode 와 date를 입력받아 입력받은 날로부터 일주일 처리량 가져오기
	public List<LaundryThroughPutVO> selectWeeksBranchLaundryThroughPut(LaundryThroughPutVO throughPutVO);
	
	//최대 세탁량(수정)
	public void updateBranchLndrpcrymslmcoqy(BranchVO branch) throws Exception;
	
	
}
