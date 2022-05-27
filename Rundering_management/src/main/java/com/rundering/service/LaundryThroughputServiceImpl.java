package com.rundering.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.AppCriteria;
import com.rundering.command.AppPageMaker;
import com.rundering.command.BranchInfoDetailCommand;
import com.rundering.command.PageMaker;
import com.rundering.dao.LaundryThroughputDAO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.LaundryThroughPutVO;

public class LaundryThroughputServiceImpl implements LaundryThroughputService{

	LaundryThroughputDAO laundryThroughputDAO;
	
	public void setLaundryThroughputDAO(LaundryThroughputDAO laundryThroughputDAO) {
		this.laundryThroughputDAO = laundryThroughputDAO;
	}
	
	@Override
	public Map<String, Object> getThroughputList(AppCriteria cri) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<LaundryThroughPutVO> throughputList = laundryThroughputDAO.throughputList(cri);
		
		// 전체 board 개수
		int totalCount = laundryThroughputDAO.throughputListCount(cri);
		// PageMaker 생성.
		AppPageMaker pageMaker = new AppPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("throughputList", throughputList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
		
	}

	@Override
	public Map<String, Object> getLaundryQuatoByThroughputNo(String throughputNo) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		LaundryThroughPutVO throughput = laundryThroughputDAO.selectLaundryQuatoByThroughputNo(throughputNo);
		List<LaundryThroughPutVO> throughputList =  laundryThroughputDAO.throughputList();
		
		dataMap.put("throughput", throughput);
		dataMap.put("throughputList", throughputList);
		
		return dataMap;
	}

	@Override
	public BranchInfoDetailCommand getBranchDetail(String branchCode) throws Exception {
		BranchInfoDetailCommand branchDetail = laundryThroughputDAO.selectBranchDetail(branchCode);
		
		return branchDetail;
	}

	@Override
	public List<LaundryThroughPutVO> branchQuotaTable(String branchCode) throws Exception {
		List<LaundryThroughPutVO> branchTableList = laundryThroughputDAO.branchQuotaTable(branchCode);
		return branchTableList;
	}

	@Override
	public List<LaundryThroughPutVO> branchTableDate(String date) throws Exception {
		List<LaundryThroughPutVO> branchTableList = laundryThroughputDAO.branchTableDate(date);
		
		return branchTableList;
	}

	@Override
	public List<LaundryThroughPutVO> getWeeksBranchThroughput(LaundryThroughPutVO throughPutVO) {
		return laundryThroughputDAO.selectWeeksBranchLaundryThroughPut(throughPutVO);
	}
	
	@Override
	public void updateBranchLndrpcrymslmcoqy(BranchVO branch) throws Exception {
		laundryThroughputDAO.updateBranchLndrpcrymslmcoqy(branch);
	}
}
