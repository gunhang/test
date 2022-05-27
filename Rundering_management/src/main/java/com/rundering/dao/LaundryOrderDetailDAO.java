package com.rundering.dao;

import java.util.List;

import com.rundering.dto.LaundryOrderDetailVO;

public interface LaundryOrderDetailDAO {
	
	//세탁주문상세 조회 - ByOrderNo
	public List<LaundryOrderDetailVO> selectlaundryOrderDetailListByOrderNo(String orderNo);
		

}
