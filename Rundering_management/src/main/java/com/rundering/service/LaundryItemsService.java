package com.rundering.service;

import java.sql.SQLException;
import java.util.Map;

import com.rundering.command.Criteria;
import com.rundering.dto.LaundryItemsVO;

public interface LaundryItemsService {
	
	public Map<String, Object> getLaundryItemsList(Criteria cri) throws Exception;
	
	//modify때문에 vo정보를 가져오는 메소드 생성
	public LaundryItemsVO getLaundryItemsByItemCode(String laundryItemsCode) throws SQLException;
	
	void regist(LaundryItemsVO laundryItems) throws SQLException;
	
	void modify(LaundryItemsVO laundryItems) throws SQLException;
	
	void remove(String laundryItemsCode) throws SQLException;
}
