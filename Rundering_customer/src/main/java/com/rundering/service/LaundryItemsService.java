package com.rundering.service;

import java.util.Map;

import com.rundering.dto.LaundryItemsVO;

public interface LaundryItemsService {
	
	//세탁품목 등록
	void regist(LaundryItemsVO laundryItems) throws Exception;
	// 세탁품목 전체 목록조회 - 이용안내
	Map<String, Object> getlaundryItemsList() throws Exception;
	
}
