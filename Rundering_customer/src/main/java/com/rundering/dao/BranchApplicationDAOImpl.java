package com.rundering.dao;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.BranchApplicationVO;

public class BranchApplicationDAOImpl implements BranchApplicationDAO {
	SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}

	// 지점신청
	@Override
	public void branchApplicate(BranchApplicationVO bv) throws Exception {
		session.update("BranchApplication-Mapper.insertBranchApplication",bv);
	}

	// 지점 신청 확인 들어가기전 본인인증
	@Override
	public BranchApplicationVO selectSelfAuthentification(BranchApplicationVO bv) throws Exception {
		return session.selectOne("BranchApplication-Mapper.selectSelfAuthentification", bv);
	}

	//지점 신청 심사내역 
	@Override
	public void updateJudge(BranchApplicationVO bv) throws Exception {
		session.update("BranchApplication-Mapper.updateJudge", bv);
		
	}
	

}
