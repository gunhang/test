package com.rundering.manage.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rundering.dto.MenuVO;
import com.rundering.service.MenuService;

@Controller
public class AdminConmonController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/admin/index")
	public String index(@RequestParam(defaultValue = "A000000") String menuCode, Model model) throws Exception {
		String url = "admin/admin_index";
		Map<String, List<MenuVO>> dataMap = menuService.getAdminMenuList();
		List<String> key = new ArrayList<String>(); 
		for (String keyValue : dataMap.keySet()) {
			key.add(keyValue);
		}
		
		MenuVO menu = menuService.MenuByMenuCode(menuCode);
		
		model.addAttribute("key", key);
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("menu", menu);
		return url;
	}
	
	@RequestMapping("/admin/common/main")
	public String main(@RequestParam(defaultValue = "A000000") String menuCode, Model model) throws Exception {
		String url = "/admin/main";
		Map<String, List<MenuVO>> dataMap = menuService.getAdminMenuList();
		List<String> key = new ArrayList<String>(); 
		for (String keyValue : dataMap.keySet()) {
			key.add(keyValue);
		}
		MenuVO menu = menuService.MenuByMenuCode(menuCode);
		
		model.addAttribute("key", key);
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("menu", menu);
		return url;
	}
}
