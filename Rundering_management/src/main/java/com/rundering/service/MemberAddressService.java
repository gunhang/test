package com.rundering.service;

import com.rundering.dto.MemberAddressVO;

public interface MemberAddressService {
	
	// 주소지 등록
	public void memberAddressRegist(MemberAddressVO memberAdd) throws Exception;
	 
}