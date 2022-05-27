package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.MemberAddressVO;

public class MemberAddressDAOImpl implements MemberAddressDAO{
	SqlSession session;

	
	public void setSession(SqlSession session) {
		this.session = session;
	}

	
	@Override
	public void addRegist(MemberAddressVO memberAdd) throws Exception {
		session.update("MemberAddress-Mapper.addressRegist",memberAdd);
	}


	@Override
	public MemberAddressVO selectDefaultMemberAddressByMemberNo(String memberNo) throws SQLException {
		return session.selectOne("MemberAddress-Mapper.selectDefaultMemberAddressByMemberNo", memberNo);
	}

	
	@Override
	public List<MemberAddressVO> selectMemberAddressListByMemberNo(String memberNo) {
		return session.selectList("MemberAddress-Mapper.selectMemberAddressListByMemberNo",memberNo);
	}


	@Override
	public void updateAllMemberAddressDefaultN(String memberNo) throws SQLException {
		session.update("MemberAddress-Mapper.updateAllMemberAddressDefaultN",memberNo);
	}


	@Override
	public MemberAddressVO selectMemberAddressByAddressNo(String addressNo) throws SQLException {
		return session.selectOne("MemberAddress-Mapper.selectMemberAddressByAddressNo", addressNo);
	}

	//기본 주소지 수정
	@Override
	public void updateDefaultAddress(MemberAddressVO memberAdd) throws Exception {
		session.update("MemberAddress-Mapper.updateDefaultAddress",memberAdd);
		
	}

	//주소지 추가 등록
	@Override
	public void addrRegist(MemberAddressVO memberAdd) throws Exception {
		session.update("MemberAddress-Mapper.addaddressRegist",memberAdd);
	}

	//기본 주소지 초기화
	@Override
	public void updateDefaultReset(String memberNo) throws Exception {
		session.update("MemberAddress-Mapper.updateDefaultReset",memberNo);
	}

	// 기본 주소지로 변경 및 수정 
	@Override
	public void modifyDefaultAddr(MemberAddressVO memberAdd) throws Exception {
		session.update("MemberAddress-Mapper.modifyDefaultAddr",memberAdd);
	}

	// 주소지 수정
	@Override
	public void modifyAddr(MemberAddressVO memberAdd) throws Exception {
		session.update("MemberAddress-Mapper.modifyAddr",memberAdd);
	}

	// 주소지 삭제(기본주소지 제외)
	@Override
	public void removeByAddressNo(String memberNo) throws Exception {
		session.update("MemberAddress-Mapper.removeByAddressNo",memberNo);
		
	}
	
	
}
