package com.rundering.dao;

import com.rundering.dto.MemberAddressVO;

public interface MemberAddressDAO {
	
	// 주소지 등록
	public void addRegist(MemberAddressVO memberAdd) throws Exception;
	// 반려사원 주소 삭제
	public void removeByNo(String memberNo)throws Exception;
	
}