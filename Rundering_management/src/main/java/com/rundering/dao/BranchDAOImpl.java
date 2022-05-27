package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.BranchVO;

public class BranchDAOImpl implements BranchDAO {
	
	SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	// 전체 지점정보 가져오기
	public List<BranchVO> selectBranchList() throws Exception {
		List<BranchVO> branchList = session.selectList("Branch-Mapper.selectBranchList");
		return branchList;
	}
	//지점조회 - by branchCode
	@Override
	public BranchVO selectBranchByBranchCode(String branchCode) throws Exception {
		return session.selectOne("Branch-Mapper.selectBranchByBranchCode", branchCode);
	}
	// 전체 지점정보 가져오기
	public List<BranchVO> selectBranchListByArea(String area) throws Exception {
		List<BranchVO> branchList = session.selectList("Branch-Mapper.selectBranchListByArea",area);
		return branchList;
	}
	
	//로그인시 해당 사원의 지점코드를 통한 지점 정보 가져오기
	@Override
	public BranchVO getBranchByCode(String branchCode) throws Exception {
		BranchVO bv = session.selectOne("Branch-Mapper.getBranchByCode",branchCode);
		return bv;
	}
	//지역의 세탁처리여유량 내일이 수거요청일인(지역내지점의 세탁처리가능량 - 지역의 주문량 = 처리여유량)
	@Override
	public int selectExcessCapacityOfTomorrowLaundryByArea(String area) throws Exception {
		return session.selectOne("Branch-Mapper.selectExcessCapacityOfTomorrowLaundryByArea",area);
	}

	@Override
	public int selectExcessCapacityOfTomorrowLaundryByBranchCode(String branchCode) throws Exception {
		return session.selectOne("Branch-Mapper.selectExcessCapacityOfTomorrowLaundryByBranchCode",branchCode);
	}
	@Override
	public String selectBranchCodeByArea(String area) throws Exception{
		return session.selectOne("Branch-Mapper.selectBranchCodeByArea", area);
	}
	@Override
	public void insertBranch(BranchVO branch) throws Exception{
		session.insert("Branch-Mapper.insertBranch", branch);
	}

	@Override
	public List<BranchVO> selectAvailableBranchList() throws Exception {
		List<BranchVO> branchList = session.selectList("Branch-Mapper.selectAvailableBranchList");
		return branchList;
	}

}
