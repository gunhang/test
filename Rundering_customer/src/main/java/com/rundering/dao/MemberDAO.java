package com.rundering.dao;

import java.util.List;

import com.rundering.command.MemberAddCommand;
import com.rundering.dto.MemberVO;

public interface MemberDAO {
	
	// 회원아이디를 통한 회원정보 가져오기
	public MemberVO selectMemberById(String id) throws Exception;
	public List<String> selectAuthByMemberNo(String memberNo) throws Exception;
	public void updateLoginfailCountByMemberNo(String memberNo) throws Exception;
	public void updateLoginfailZeroByMemberNo(String memberNo) throws Exception;
	public void updateLastLoginByMemberNo(String memberNo) throws Exception;

	// 아이디 중복체크
	public MemberVO checkId(String id) throws Exception;
	
	// 이메일 중복체크
	public MemberAddCommand checkEmail(String email) throws Exception;
	
	// 개인정보 변경시 패스워드 체크
	public String checkPw(String pw) throws Exception; 
	
	// 회원 가입
	public void memberJoin(MemberVO member) throws Exception;

	// 회원 정보 수정
	public void modifyMember(MemberVO member) throws Exception;

	// 회원정보 삭제(비활성화)
	public void deleteMember(String id) throws Exception;
	
	// 사원ID를 통해 배송기사 연락처 가져오기
	public String getDelivery(String id) throws Exception;
	
	public int selectIdFindByMember(MemberVO member) throws Exception;
	public int selectPasswordFindByMember(MemberVO member) throws Exception;
	public String selectGetFindIdByMember(MemberVO member) throws Exception;
	public void updatePasswordByMember(MemberVO member) throws Exception;
	
}
