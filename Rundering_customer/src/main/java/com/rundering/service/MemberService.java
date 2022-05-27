package com.rundering.service;

import java.util.List;

import com.rundering.command.MemberAddCommand;
import com.rundering.dto.MemberVO;

public interface MemberService {

	public MemberVO getMember(String id) throws Exception;

	public List<String> getAuthList(String memberNo) throws Exception;

	public void loginFailIncrease(String memberNo) throws Exception;
	public void loginSuccess(String memberNo) throws Exception;
	// 아이디 중복체크
	public MemberVO checkId(String id) throws Exception;
	
	// Email 중복체크
	public MemberAddCommand checkEmail(String email) throws Exception;
	
	// Passowrd 체크
	public String checkPw(String id) throws Exception;

	// 회원 가입
	public void memberJoin(MemberVO member) throws Exception;

	// 회원 정보 수정
	public void modifyMember(MemberAddCommand mac) throws Exception;

	// 회원정보 삭제(비활성화)
	public void deleteMember(String id) throws Exception;

	
	public int idFind(MemberVO member) throws Exception;
	public int passwordFind(MemberVO member) throws Exception;
	public String getFindId(MemberVO member) throws Exception;
	public void updatePassword(MemberVO member) throws Exception;
}
