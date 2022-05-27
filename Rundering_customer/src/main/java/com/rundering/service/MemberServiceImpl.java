package com.rundering.service;

import java.util.List;

import com.rundering.command.MemberAddCommand;
import com.rundering.dao.MemberAddressDAO;
import com.rundering.dao.MemberDAOImpl;
import com.rundering.dto.MemberAddressVO;
import com.rundering.dto.MemberVO;

public class MemberServiceImpl implements MemberService {
	private MemberDAOImpl memberDAO;
	public void setMemberDAO(MemberDAOImpl memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	private MemberAddressDAO memberAddressDAO;
	public void setMemberAddressDAO(MemberAddressDAO memberAddressDAO) {
		this.memberAddressDAO = memberAddressDAO;
	}

	

	@Override
	public MemberVO getMember(String id) throws Exception {
		MemberVO member = memberDAO.selectMemberById(id);
		return member;
	}

	@Override
	public List<String> getAuthList(String memberNo) throws Exception {
		List<String> auth = memberDAO.selectAuthByMemberNo(memberNo);
		return auth;
	}

	@Override
	public void loginFailIncrease(String memberNo) throws Exception {
		memberDAO.updateLoginfailCountByMemberNo(memberNo);
	}

	@Override
	public void loginSuccess(String memberNo) throws Exception {
		memberDAO.updateLastLoginByMemberNo(memberNo);
		memberDAO.updateLoginfailZeroByMemberNo(memberNo);
	}

	// ID중복체크
	@Override
	public MemberVO checkId(String id) throws Exception {
		MemberVO member = memberDAO.checkId(id);
		return member;
	}

	// EMAIL중복체크
	@Override
	public MemberAddCommand checkEmail(String email) throws Exception {
		MemberAddCommand mac = memberDAO.checkEmail(email);
		return mac;
	}

	// Password 체크
	@Override
	public String checkPw(String id) throws Exception {
		String pw = memberDAO.checkPw(id);
		return pw;
	}

	// 회원가입
	@Override
	public void memberJoin(MemberVO member) throws Exception {
		memberDAO.memberJoin(member);
	}

	// 회원정보수정
	@Override
	public void modifyMember(MemberAddCommand mac) throws Exception {
		//커맨드 주입
		MemberVO member = mac.toMember();
		MemberAddressVO memberAdd = mac.toAddr();
		
		//회원테이블의 회원정보 수정
		memberDAO.modifyMember(member);
		//기본주소지 수정
		memberAddressDAO.updateDefaultAddress(memberAdd);
	}

	// 회원 탈퇴(비활성화)
	@Override
	public void deleteMember(String id) throws Exception {
		memberDAO.deleteMember(id);
	}

	@Override
	public int idFind(MemberVO member) throws Exception {
		int check = memberDAO.selectIdFindByMember(member);
		return check;
	}

	@Override
	public int passwordFind(MemberVO member) throws Exception {
		int check = memberDAO.selectPasswordFindByMember(member);
		return check;
	}

	@Override
	public String getFindId(MemberVO member) throws Exception {
		String id = memberDAO.selectGetFindIdByMember(member);
		return id;
	}

	@Override
	public void updatePassword(MemberVO member) throws Exception {

		memberDAO.updatePasswordByMember(member);
		member = memberDAO.selectMemberById(member.getId());
		memberDAO.updateLoginfailZeroByMemberNo(member.getMemberNo());
	}

}
