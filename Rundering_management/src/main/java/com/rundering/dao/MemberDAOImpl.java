package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.AppCriteria;
import com.rundering.command.CustomerListCriteria;
import com.rundering.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public MemberVO selectMemberByMemberNo(String memberNo) throws Exception {
		MemberVO member = session.selectOne("Member-Mapper.selectMemberByMemberNo", memberNo);
		return member;
	}
	
	// 아이디를 통한 사원의 정보 가져오기
	@Override
	public MemberVO selectEmployeeById(String id) throws Exception {
		MemberVO member = session.selectOne("Member-Mapper.selectEmployeeById", id);
		return member;
	}

	// 사원등록신청
	@Override
	public void applicationEmployee(MemberVO member) throws Exception {
		session.update("Member-Mapper.applicationEmployee", member);
	}

	// 등록신청 사원의 고유번호 가져오기
	@Override
	public MemberVO getMemberNo(String phone) throws Exception {

		MemberVO member = session.selectOne("Member-Mapper.getMemberById", phone);

		return member;
	}

	// 사원등록 후 아디디 및 비밀번호 변경
	@Override
	public void updateMember(MemberVO member) throws Exception {
		 session.selectOne("Member-Mapper.updatemplmem", member); 

	}

	// 로그인시 지점코드가 같은 사원신청 리스트 가져오기
	@Override
	public List<MemberVO> getEmplAppList(AppCriteria cri) throws Exception {

		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<MemberVO> appList = session.selectList("Member-Mapper.getemplapplist", cri, rowBounds);

		return appList;
	}

	// 리스트 개수 체크
	@Override
	public int applistCount(AppCriteria cri) throws SQLException {
		int count = session.selectOne("Member-Mapper.applistCount", cri);
		return count;
	}
	
	// 등록 신청 사원의 디테일 정보 가져오기
	@Override
	public MemberVO getEmpAppinfo(String memberNO) throws Exception {
		
		MemberVO member = session.selectOne("Member-Mapper.getEmpAppinfo", memberNO);

		return member;
	}
	
	// 등록 신청 사원 반려 
	@Override
	public void removeByNo(String memberNo) throws Exception {
		session.delete("Member-Mapper.removeByNo",memberNo);
	}

	//권한그룹
	@Override
	public List<String> selectAuthByMemberNo(String memberNo) throws Exception {
		List<String> auth = session.selectList("Member-Mapper.selectAuthByMemberNo", memberNo);
		return auth;
	}
	
	
	
	
	
	//고객 정보
	@Override
	public MemberVO selectMemberById(String id) throws Exception {
		MemberVO member = session.selectOne("Member-Mapper.selectMemberById", id);
		return member;
	}

	//고객리스트
	@Override
	public List<MemberVO> selectMemberList(CustomerListCriteria cri) throws Exception {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<MemberVO> memberList 
		= session.selectList("Member-Mapper.selectSearchMemberList",cri,rowBounds);
		
		return memberList;
	}

	//일반 리스트 전체 개수
	@Override
	public int selectMemberListCount() throws Exception {
		int totalCount = session.selectOne("Member-Mapper.selectMemberListCount");
		return totalCount;
	}

	//검색 결과의 전체 리스트 개수
	@Override
	public int selectMemberListCount(CustomerListCriteria cri) throws Exception {
		int totalCount = session.selectOne("Member-Mapper.selectSearchMemberListCount",cri);
		return totalCount;
	}

	// 새로운 비밀번호로 변경
	@Override
	public void modifyPwById(MemberVO mv) throws Exception {
		session.selectOne("Member-Mapper.modifyPwById",mv);
	}
	@Override
	public void insertBranchMemberByMemberVO(MemberVO member) throws Exception{
		session.insert("Member-Mapper.insertBranchMemberByMemberVO", member);
	}

	@Override
	public String selectMemberNoSeq() throws Exception {
		return session.selectOne("Member-Mapper.selectMemberNoSeq");
	}
	@Override
	public MemberVO selectMemberByPhone(String phone) throws Exception {
					
		return session.selectOne("Member-Mapper.selectMemberByPhone",phone);
		 
	}
	
	//주문 고객의 연락처 가져오기
	@Override
	public String getPhoneNum(String memberNo) throws Exception {
		return session.selectOne("Member-Mapper.getPhoneNum",memberNo);
	}

	@Override
	public void updateSecession(String memberNo) throws Exception {
		session.update("Member-Mapper.updateSecession",memberNo);
	}

}
