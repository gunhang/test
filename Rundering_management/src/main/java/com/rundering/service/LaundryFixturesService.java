package com.rundering.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.LaundryFixturesVO;

public interface LaundryFixturesService {

	String getFixturesCodeAsRequest(String branchCode, String fixturesName) throws SQLException;

	List<LaundryFixturesVO> selectBranchFixturesList(String branchCode) throws Exception;

	Map<String, Object> selectBranchFixturs(BranchCriteria cri) throws Exception;

	void updateFixturesStauts(LaundryFixturesVO laundryFixtures) throws Exception;
}
