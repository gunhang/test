package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rundering.command.MemberAddCommand;
import com.rundering.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO{
	SqlSession session;

	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	@Override
	public MemberVO selectMemberById(String id) throws Exception {
		MemberVO member= session.selectOne("Member-Mapper.selectMemberById", id);
		return member;
	}
	@Override
	public List<String> selectAuthByMemberNo(String memberNo) throws Exception {
		List<String> auth = session.selectList("Member-Mapper.selectAuthByMemberNo",memberNo);
		return auth;
	}
	@Override
	public void updateLoginfailCountByMemberNo(String memberNo) throws Exception{
		session.update("Member-Mapper.updateLoginfailCountByMemberNo", memberNo);
	}
	@Override 
	public void updateLoginfailZeroByMemberNo(String memberNo) throws Exception{
		session.update("Member-Mapper.updateLoginfailZeroByMemberNo", memberNo);
	}
	@Override
	public void updateLastLoginByMemberNo(String memberNo) throws Exception{
		session.update("Member-Mapper.updateLastLoginByMemberNo",memberNo);
	}
	@Override
	public int selectIdFindByMember(MemberVO member) throws Exception{
		int check =session.selectOne("Member-Mapper.selectIdFindByMember",member);
		return check;
	}
	@Override
	public int selectPasswordFindByMember(MemberVO member) throws Exception{
		int check =session.selectOne("Member-Mapper.selectPasswordFindByMember",member);
		return check;
	}
	@Override
	public String selectGetFindIdByMember(MemberVO member) throws Exception{
		String id = session.selectOne("Member-Mapper.selectGetFindIdByMember",member);
		return id;
	}
	@Override
	public void updatePasswordByMember(MemberVO member) throws Exception{
		session.update("Member-Mapper.updatePasswordByMember",member);
	}
	
	//ID중복체크
	@Override
	public MemberVO checkId(String id) throws Exception {
		MemberVO member = session.selectOne("Member-Mapper.checkId", id);
		return member;
	}
	
	//EMAIL중복체크
	@Override
	public MemberAddCommand checkEmail(String email) throws Exception {
		MemberAddCommand mac = session.selectOne("Member-Mapper.checkEmail", email);
		return mac;
	}
	
	//회원가입(등록)
	@Override
	public void memberJoin(MemberVO member) throws Exception {
		session.update("Member-Mapper.memberJoin",member);
	}
	
	//회원정보수정
	@Override
	public void modifyMember(MemberVO member) throws Exception {
		session.update("Member-Mapper.modifyMember",member);	
	}
	
	//회원탈퇴(비활성화)
	@Override
	public void deleteMember(String id) throws Exception {
		session.update("Member-Mapper.deleteMember",id);	
	}
	
	// 개인정보 변경시 패스워드 체크
	@Override
	public String checkPw(String id) throws Exception {
		String pw = session.selectOne("Member-Mapper.checkPw",id);
		return pw;
	}
	
	// 사원ID를 통해 배송기사 연락처 가져오기
	@Override
	public String getDelivery(String id) throws Exception {
		return session.selectOne("Member-Mapper.getDelivery", id); 
	}
	

	
}
