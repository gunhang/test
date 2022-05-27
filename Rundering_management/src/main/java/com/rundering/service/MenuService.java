package com.rundering.service;

import java.util.List;
import java.util.Map;

import com.rundering.dto.MenuVO;

public interface MenuService {
	
	public List<MenuVO> MainBranchMenuList() throws Exception;
	public List<MenuVO> MainAdminMenuList() throws Exception;
	public List<MenuVO> SubMenuList() throws Exception;
	public MenuVO MenuByMenuCode(String menuCode) throws Exception;
	public MenuVO MenuByMenuName(String menuName) throws Exception;
	public Map<String, List<MenuVO>> getBranchMenuList() throws Exception;
	public Map<String, List<MenuVO>> getAdminMenuList() throws Exception;
}
