package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.dto.LaundryOrderDetailVO;

public interface LaundryOrderDetailDAO {
	
	// 세탁주문상세 등록
	public void insertLaundryOrderDetail(LaundryOrderDetailVO laundryOrderDetail) throws SQLException;
	
	// 주문내역 상세 
	public List<LaundryOrderDetailVO> getMyorderDetail(String orderNo) throws Exception;
		

}
