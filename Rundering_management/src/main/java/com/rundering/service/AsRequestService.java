package com.rundering.service;

import java.sql.SQLException;
import java.util.Map;

import com.rundering.command.BranchCriteria;
import com.rundering.command.Criteria;
import com.rundering.dto.AsRequestVO;

public interface AsRequestService {

	// 목록조회
	public Map<String, Object> getAsRequestList(Criteria cri) throws SQLException;

	// 상세보기
	/* AsRequestVO getAsRequest(int asno) throws SQLException; */

	// 확인미확인
	AsRequestVO getCheck(int asno) throws SQLException;
	
	// 조치오나료
	AsRequestVO getOk(int asno) throws SQLException;

	// 수정화면 상세
	AsRequestVO getAsRequestModify(int asno) throws SQLException;

	// 등록
	void regist(AsRequestVO asrequest) throws Exception;
	
	//물품리스트 중복제거
	void getItemList(AsRequestVO asrequest) throws SQLException;

	// 수정
	void modify(AsRequestVO asrequest) throws SQLException;

	// 삭제
	void remove(int asno) throws SQLException;

	Map<String, Object> getBranchAsRequestList(BranchCriteria cri) throws SQLException;

}
