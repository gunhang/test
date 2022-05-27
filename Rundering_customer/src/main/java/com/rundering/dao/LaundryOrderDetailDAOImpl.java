package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.LaundryOrderDetailVO;

public class LaundryOrderDetailDAOImpl implements LaundryOrderDetailDAO{
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	// 세탁주문상세 등록
	@Override
	public void insertLaundryOrderDetail(LaundryOrderDetailVO laundryOrderDetail) throws SQLException {
		session.update("LaundryOrderDetail-Mapper.insertLaundryOrderDetail",laundryOrderDetail);
	}

	// 주문내역 상세 
	@Override
	public List<LaundryOrderDetailVO> getMyorderDetail(String orderNo) throws Exception {
		return session.selectList("LaundryOrderDetail-Mapper.getMyorderDetail", orderNo);
	}

}
