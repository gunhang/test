
package com.rundering.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.BranchCriteria;
import com.rundering.command.BranchPageMaker;
import com.rundering.command.PageMaker;
import com.rundering.dao.LaundryFixturesDAO;
import com.rundering.dto.LaundryFixturesVO;

public class LaundryFixturesServiceImpl implements LaundryFixturesService {

	private LaundryFixturesDAO laundryFixturesDAO;

	public void setLaundryFixturesDAO(LaundryFixturesDAO laundryFixturesDAO) {
		this.laundryFixturesDAO = laundryFixturesDAO;
	}

	@Override
	public String getFixturesCodeAsRequest(String branchCode, String fixturesName) throws SQLException {
		String fixtures = laundryFixturesDAO.getFixturesCodeAsRequest(branchCode, fixturesName);
		return fixtures;
	}
	@Override
	public List<LaundryFixturesVO> selectBranchFixturesList(String branchCode) throws Exception {
		return 	laundryFixturesDAO.selectFixturesListByBranchCode(branchCode);
	}
	@Override
	public Map<String, Object> selectBranchFixturs(BranchCriteria cri) throws Exception{
		List<LaundryFixturesVO> fixturesList = laundryFixturesDAO.selectFixturesList(cri);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		int totalCount = laundryFixturesDAO.selectFixturesListCount(cri);
		
		BranchPageMaker pageMaker = new BranchPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("fixturesList", fixturesList);
		dataMap.put("pageMaker", pageMaker);
	
		return dataMap;
	}
	@Override
	public void updateFixturesStauts(LaundryFixturesVO laundryFixtures) throws Exception{
		laundryFixturesDAO.updateFixturesStatusByFixturesCode(laundryFixtures);
		
	}

}
