package com.rundering.service;

import java.util.List;

import com.rundering.dao.ComCodeDAOImpl;
import com.rundering.dto.ComCodeVO;

public class ComCodeServiceImpl implements ComCodeService {

	ComCodeDAOImpl comCodeDAO;

	public void setComCodeDAO(ComCodeDAOImpl comCodeDAO) {
		this.comCodeDAO = comCodeDAO;
	}
	
	
	// 전체 부서 가져오기
	@Override
	public List<ComCodeVO> getDepartment() throws Exception {

		return comCodeDAO.getDepartment();
	}

	// 전체 부서 가져오기
	@Override
	public List<ComCodeVO> getPosition() throws Exception {

		return comCodeDAO.getPosition();
	}


	
}
