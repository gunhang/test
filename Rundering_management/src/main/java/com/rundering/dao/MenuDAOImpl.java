package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.MenuVO;

public class MenuDAOImpl implements MenuDAO{
	
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<MenuVO> selectBranchMainMenu() throws Exception {
		List<MenuVO> menu = session.selectList("Menu-Mapper.selectBranchMainMenu");
		return menu;
	}
	public List<MenuVO> selectAdminMainMenu() throws Exception {
		List<MenuVO> menu = session.selectList("Menu-Mapper.selectAdminMainMenu");
		return menu;
	}

	@Override
	public List<MenuVO> selectSubMenu() throws Exception {
		List<MenuVO> menu = session.selectList("Menu-Mapper.selectSubMenu");
		return menu;
	}

	@Override
	public MenuVO selectMenuByMenuCode(String menuCode) throws Exception {
		MenuVO menu = session.selectOne("Menu-Mapper.selectMenuByMenuCode",menuCode);
		return menu;
	}

	@Override
	public MenuVO selectMenuByMenuName(String menuName) throws Exception {
		MenuVO menu = session.selectOne("Menu-Mapper.selectMenuByMenuName",menuName);
		return menu;
	}
	

	
}
