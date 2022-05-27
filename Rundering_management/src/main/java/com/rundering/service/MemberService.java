package com.rundering.service;

import java.util.List;
import java.util.Map;

import com.rundering.command.AppCriteria;
import com.rundering.command.CustomerListCriteria;
import com.rundering.dto.MemberVO;

public interface MemberService {

	// 사원등록신청
	public void applicationEmployee(MemberVO member) throws Exception;

	// 등록사원의 고유번호 가져오기
	public MemberVO getMemberNo(String phone) throws Exception;

	// 신청 등록 사원의 디테일 출력
	public MemberVO getEmpAppinfo(String memberNO) throws Exception;

	// 로그인시 지점코드가 같은 사원신청 리스트 가져오기
	public Map<String, Object> getEmplAppList(AppCriteria cri) throws Exception;

	// 사원여부
	public MemberVO getEmployee(String id) throws Exception;
	
	// 권한그룹코드 분류
	public List<String> getAuthList(String memberNo) throws Exception;

	// 등록 신청 사원 반려 
	public void removeByNo(String memberNo) throws Exception;

	// 고객 리스트 조회
	public Map<String, Object> getMemberList(CustomerListCriteria cri) throws Exception;
	
	// 고객 정보 조회
	public MemberVO getMember(String id) throws Exception;
	
	
	// 새로운 비밀번호로 변경
	public void modifyPwById(MemberVO mv) throws Exception;
	
	//탈퇴 취소
	public void cancelSecession(String memberNo) throws Exception;

}
