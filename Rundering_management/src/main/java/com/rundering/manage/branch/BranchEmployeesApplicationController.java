package com.rundering.manage.branch;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.command.AppCriteria;
import com.rundering.dto.BranchVO;
import com.rundering.dto.ComCodeVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.MemberVO;
import com.rundering.service.BranchService;
import com.rundering.service.EmployeesService;
import com.rundering.service.MailSendService;
import com.rundering.service.MemberService;

@Controller
@RequestMapping("branch/employeeapplication")
public class BranchEmployeesApplicationController {

	@Autowired
	MemberService memberService;

	@Autowired
	EmployeesService employeesService;

	@Autowired
	BranchService branchService;
	
	@Autowired
	private MailSendService mailSendService;

	@RequestMapping("/main")
	public ModelAndView employeeapplication(HttpServletRequest request, ModelAndView mnv, AppCriteria cri)
			throws Exception {
		String url = "branch/employee/employeeapplication_main";

		HttpSession session = request.getSession();
		EmployeesVO ev = (EmployeesVO) session.getAttribute("loginEmployee");

		cri.setPassword(ev.getBranchCode());

		List<BranchVO> branchList = branchService.getBranchList();

		BranchVO bv = branchService.getBranchByCode(ev.getBranchCode());

		Map<String, Object> dataMap = memberService.getEmplAppList(cri);

		List<ComCodeVO> dpList = employeesService.getDepartment();

		List<ComCodeVO> poList = employeesService.getPosition();

		mnv.addObject("dpList", dpList);
		mnv.addObject("poList", poList);
		mnv.addObject("bv", bv);
		mnv.addObject("branchList", branchList);
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping("/detail")
	public ResponseEntity<MemberVO> detail(String memberNo) throws Exception {

		ResponseEntity<MemberVO> entity = null;

		try {
			MemberVO mv = memberService.getEmpAppinfo(memberNo);

			if (mv != null) {
				entity = new ResponseEntity<MemberVO>(mv, HttpStatus.OK);
			} else {
				System.out.println("실행 안됨!!!");
			}
		} catch (SQLException e) {
			entity = new ResponseEntity<MemberVO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}

	@RequestMapping("/remove")
	public ResponseEntity<String> remove(String memberNo) throws Exception {

		ResponseEntity<String> entity = null;

		try {
			memberService.removeByNo(memberNo);

			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;

	}

	@RequestMapping("/regist")
	@ResponseBody
	public ResponseEntity<String> regeist(EmployeesVO ev) throws Exception {
		ResponseEntity<String> entity = null;

		try {
			
			employeesService.employeeRegist(ev);

			mailSendService.sendIdPwMail(ev.getMemberno());
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;

	}

}
