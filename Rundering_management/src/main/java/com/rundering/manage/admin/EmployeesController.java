package com.rundering.manage.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.command.Criteria;
import com.rundering.command.CustomerListCriteria;
import com.rundering.command.EmployeesListCriteria;
import com.rundering.service.EmployeesService;
import com.rundering.service.MemberService;

@Controller
@RequestMapping("/admin/employees")
public class EmployeesController {
	
	@Autowired
	EmployeesService employeesService;

	@RequestMapping("/main")
	public ModelAndView employeesList(EmployeesListCriteria cri, ModelAndView mnv) {
		String url = "admin/employee/employees_list";
		Map<String, Object> dataMap = null;
		try {
			dataMap = employeesService.getEmployeeList(cri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		return mnv;
	}

}
