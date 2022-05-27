package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.LaundryOrderDetailVO;

public class LaundryOrderDetailDAOImpl implements LaundryOrderDetailDAO{
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<LaundryOrderDetailVO> selectlaundryOrderDetailListByOrderNo(String orderNo) {
		return session.selectList("LaundryOrderDetail-Mapper.selectlaundryOrderDetailListByOrderNo", orderNo);
	}

	

}
