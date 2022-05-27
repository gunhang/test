package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.dto.LaundryItemsVO;

public interface LaundryItemsDAO {
	
	//의류 시퀀스
	public String selectClothingSequenceNextValue() throws SQLException;
	//침구 시퀀스
	public String selectBeddingSequenceNextValue() throws SQLException;
	//신발 시퀀스
	public String selectShoesSequenceNextValue() throws SQLException;
	
	
	//세탁품목 등록
	public void insertLaundryItems(LaundryItemsVO laundryItems) throws SQLException;
	//세탁품목 조회 - BylaundryItemsCode
	public LaundryItemsVO selectLaundryItemsBylaundryItemsCode(String laundryItemsCode) throws SQLException;
	//세탁품목 조회 - 전체 
	public List<LaundryItemsVO> selectlaundryItemsList() throws SQLException;
}
