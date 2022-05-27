package com.rundering.dao;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.MemberAddressVO;

public class MemberAddressDAOImpl implements MemberAddressDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	// 주소지 등록
	@Override
	public void addRegist(MemberAddressVO memberAdd) throws Exception {
		session.update("MemberAddress-Mapper.addRegist", memberAdd);

	}
	
	// 반려사원 주소 삭제
	@Override
	public void removeByNo(String memberNo) throws Exception {
		session.delete("MemberAddress-Mapper.remove", memberNo);
	}

}