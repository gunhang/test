package com.rundering.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rundering.dao.MenuDAO;
import com.rundering.dto.MenuVO;

public class MenuServiceImpl implements MenuService {
	
	private MenuDAO menuDAOBean;
	
	public void setMenuDAOBean(MenuDAO menuDAOBean) {
		this.menuDAOBean = menuDAOBean;
	}
	@Override
	public List<MenuVO> MainBranchMenuList() throws Exception {
		List<MenuVO> menuList = menuDAOBean.selectBranchMainMenu(); 
		return menuList;
	}
	@Override
	public List<MenuVO> MainAdminMenuList() throws Exception {
		List<MenuVO> menuList = menuDAOBean.selectAdminMainMenu(); 
		return menuList;
	}
	@Override
	public List<MenuVO> SubMenuList() throws Exception {
		List<MenuVO> menuList = menuDAOBean.selectSubMenu();
		return menuList;
	}
	@Override
	public Map<String, List<MenuVO>> getAdminMenuList() throws Exception{
		Map<String, List<MenuVO>> dataMap = new LinkedHashMap<String, List<MenuVO>>();
		List<MenuVO> menuList = menuDAOBean.selectAdminMainMenu(); 
		List<MenuVO> subMenuList = menuDAOBean.selectSubMenu();
		
		for (MenuVO menuVO : menuList) {
			String mainMenu = menuVO.getMenuCode();
			List<MenuVO> addList = new ArrayList<MenuVO>();
			addList.add(menuVO);
			
			for (MenuVO subMenuVO : subMenuList) {
				String subUpperMenu = subMenuVO.getUpperMenuCode();
				if(mainMenu.equals(subUpperMenu)) {
					addList.add(subMenuVO);
				}
			}
			dataMap.put(mainMenu, addList);
		}
		
		return dataMap;
	}
	
	public Map<String, List<MenuVO>> getBranchMenuList() throws Exception{
		Map<String, List<MenuVO>> dataMap = new LinkedHashMap<String, List<MenuVO>>();
		List<MenuVO> menuList = menuDAOBean.selectBranchMainMenu(); 
		List<MenuVO> subMenuList = menuDAOBean.selectSubMenu();
		
		for (MenuVO menuVO : menuList) {
			String mainMenu = menuVO.getMenuCode();
			List<MenuVO> addList = new ArrayList<MenuVO>();
			addList.add(menuVO);
			
			for (MenuVO subMenuVO : subMenuList) {
				String subUpperMenu = subMenuVO.getUpperMenuCode();
				if(mainMenu.equals(subUpperMenu)) {
					addList.add(subMenuVO);
				}
			}
			dataMap.put(mainMenu, addList);
		}
		
		return dataMap;
	}

	@Override
	public MenuVO MenuByMenuCode(String menuCode) throws Exception {
		MenuVO menu = menuDAOBean.selectMenuByMenuCode(menuCode);
		return menu;
	}

	@Override
	public MenuVO MenuByMenuName(String menuName) throws Exception {
		MenuVO menu = menuDAOBean.selectMenuByMenuName(menuName);
		return menu;
	}


}
