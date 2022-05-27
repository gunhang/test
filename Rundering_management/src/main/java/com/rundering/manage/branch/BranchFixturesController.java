package com.rundering.manage.branch;

import java.util.Map;

import javax.management.ObjectName;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rundering.command.BranchCriteria;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.LaundryFixturesVO;
import com.rundering.service.LaundryFixturesService;

@Controller
@RequestMapping("branch/fixtures")
public class BranchFixturesController {
	@Autowired
	LaundryFixturesService laundryFixturesService;
	
	@RequestMapping("list")
	public String list(Model model,BranchCriteria cri,HttpSession session)  throws Exception{
		
		String url ="branch/fixtures/fixtures_list";
		Map<String, Object> dataMap = null;
		EmployeesVO emp =(EmployeesVO)session.getAttribute("loginEmployee");
		cri.setBranchCode(emp.getBranchCode());
		dataMap=laundryFixturesService.selectBranchFixturs(cri);
		model.addAttribute("dataMap", dataMap);
		
		return url;
	}
	@RequestMapping("asform")
	public String regist(Model model,LaundryFixturesVO laundryFixtures) throws Exception{
		String url = "branch/fixtures/fixtures_asregist";
		return url;
	}
	
	@RequestMapping("statusModify")
	@ResponseBody
	public ResponseEntity<String> statusModify(LaundryFixturesVO laundryFixtures) {
		ResponseEntity<String> resp=null;
		try {
			laundryFixturesService.updateFixturesStauts(laundryFixtures);
			resp = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
