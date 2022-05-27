package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.command.Criteria;
import com.rundering.dto.LaundryItemsVO;

public interface LaundryItemsDAO {
	
	//의류 시퀀스
	public String SelectClothingSequenceNextValue() throws SQLException;
	//침구 시퀀스
	public String SelectBeddingSequenceNextValue() throws SQLException;
	//신발 시퀀스
	public String SelectShoesSequenceNextValue() throws SQLException;
	
	
	//세탁품목 등록
	public void insertLaundryItems(LaundryItemsVO laundryItems) throws SQLException;
	//세탁품목 조회 - BylaundryItemsCode
	public LaundryItemsVO selectLaundryItemsBylaundryItemsCode(String laundryItemsCode) throws SQLException;
	//세탁품목 조회 - 전체 
	public List<LaundryItemsVO> selectlaundryItemsList(Criteria cri) throws SQLException;
	
	int selectLaundryItemsCriteriaTotalCount(Criteria cri) throws SQLException;
	
	void updateLaundryItem(LaundryItemsVO laundryItems) throws SQLException;
	
	void deleteLaundryItem(String laundryItemsCode) throws SQLException;
}
