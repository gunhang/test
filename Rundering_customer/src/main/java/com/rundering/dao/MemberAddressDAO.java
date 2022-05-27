package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.dto.MemberAddressVO;

public interface MemberAddressDAO {

	// 주소지 등록
	public void addRegist(MemberAddressVO memberAdd) throws Exception;
	
	// 회원의 기본주소지 모두 N으로 update
	public void updateAllMemberAddressDefaultN(String memberNo) throws SQLException;
	
	//기본 주소지 조회 - memberNo
	public MemberAddressVO selectDefaultMemberAddressByMemberNo(String memberNo) throws SQLException;
	
	//주소지 조회 - addressNo
	public MemberAddressVO selectMemberAddressByAddressNo(String addressNo) throws SQLException;
	
	//주소지 목록 조회 - memberNo
	public List<MemberAddressVO> selectMemberAddressListByMemberNo(String memberNo) throws SQLException;
	
	//기본 주소지 수정
	public void updateDefaultAddress(MemberAddressVO memberAdd) throws Exception;
	
	//주소지 추가 등록
	public void addrRegist(MemberAddressVO memberAdd) throws Exception;
	
	//기본 주소지 초기화
	public void updateDefaultReset(String memberNo) throws Exception;
	
	// 기본 주소지로 변경 및 수정 
	public void modifyDefaultAddr(MemberAddressVO memberAdd) throws Exception;
	
	// 주소지 수정
	public void modifyAddr(MemberAddressVO memberAdd) throws Exception;
	
	// 주소지 삭제(기본주소지 제외)
	public void removeByAddressNo(String memberNo) throws Exception;
}
