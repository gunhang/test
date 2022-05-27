package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.LaundryArticlesVO;
import com.rundering.dto.LaundryFixturesVO;

public interface LaundryFixturesDAO {

	String getFixturesCodeAsRequest(String branchCode, String fixturesName) throws SQLException;

	List<LaundryFixturesVO> selectFixturesListByBranchCode(String branchCode) throws Exception;

	void insertFixturesByFixtrues(LaundryFixturesVO laundryFixtures) throws Exception;
	
	LaundryFixturesVO selectLaundryFixtureByFixturesCode(String fixturesCode) throws Exception;
	List<LaundryFixturesVO> selectFixturesList(BranchCriteria cri) throws Exception;
	int selectFixturesListCount(BranchCriteria cri) throws Exception;
	void updateFixturesStatusByFixturesCode(LaundryFixturesVO laundryFixtures ) throws Exception;

}
