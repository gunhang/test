package com.rundering.dao;

import java.util.List;

import com.rundering.dto.MenuVO;

public interface MenuDAO {

	public List<MenuVO> selectBranchMainMenu() throws Exception;
	public List<MenuVO> selectAdminMainMenu() throws Exception;
	
	public List<MenuVO> selectSubMenu() throws Exception; 
	
	public MenuVO selectMenuByMenuCode(String menuCode) throws Exception;
	
	public MenuVO selectMenuByMenuName(String menuName) throws Exception;
	

}
