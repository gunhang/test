package com.rundering.service;

import java.util.List;
import java.util.Map;

import com.rundering.command.AppCriteria;
import com.rundering.command.BranchInfoDetailCommand;
import com.rundering.dto.BranchVO;
import com.rundering.dto.LaundryThroughPutVO;

public interface LaundryThroughputService {
	
	public Map<String, Object> getThroughputList(AppCriteria cri) throws Exception;
	
	public Map<String, Object> getLaundryQuatoByThroughputNo(String throughputNo) throws Exception;
	
	public BranchInfoDetailCommand getBranchDetail(String branchCode) throws Exception;
	
	public List<LaundryThroughPutVO> branchQuotaTable(String branchCode) throws Exception;
	
	public List<LaundryThroughPutVO> branchTableDate(String date) throws Exception;
	//branchCode 와 date를 입력받아 입력받은 날로부터 일주일 처리량 가져오기
	public List<LaundryThroughPutVO> getWeeksBranchThroughput(LaundryThroughPutVO throughPutVO);
	
	//최대 세탁량(수정)
	public void updateBranchLndrpcrymslmcoqy (BranchVO branch) throws Exception;
	
}	
