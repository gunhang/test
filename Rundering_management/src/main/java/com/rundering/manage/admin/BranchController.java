package com.rundering.manage.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rundering.command.AppCriteria;
import com.rundering.command.BranchInfoDetailCommand;
import com.rundering.dto.BranchVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.LaundryThroughPutVO;
import com.rundering.service.LaundryThroughputService;

@Controller
@RequestMapping("/admin/branchinfo")
public class BranchController {
	
	@Resource(name = "laundryThroughputService")
	private LaundryThroughputService laundryThroughputService;
	
	@RequestMapping("/information")
	public String pointInfo() {
		return "admin/branchinfo/branch_info";
	}
	
	@RequestMapping("/infodetail")
	public ModelAndView pointInfodetail(ModelAndView mnv, String branchCode) throws Exception{
		String url="admin/branchinfo/branch_info_detail";
		
		BranchInfoDetailCommand branchDetail = laundryThroughputService.getBranchDetail(branchCode);
		
		mnv.addObject("branchDetail", branchDetail);
		mnv.setViewName(url);
		
		return mnv;
	}
	@RequestMapping("/detail")
	public String pointDetail() {
		return "admin/branchinfo/branch_detail";
	}
	@RequestMapping("/amount")
	public String pointAmount() {
		return "admin/branchinfo/branch_amount";
	}
	
	@RequestMapping("/main")
	public ModelAndView branchQuota(AppCriteria cri, ModelAndView mnv) throws Exception{
		String url="/admin/branchinfo/branch_quota";
		
		Map<String, Object> dataMap = laundryThroughputService.getThroughputList(cri);
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/quotadetail")
	public ModelAndView branchQuotaDetail(String throughputNo, ModelAndView mnv) throws Exception{ 
		String url="/admin/branchinfo/branch_quota_detail";
		
		Map<String, Object> dataMap = laundryThroughputService.getLaundryQuatoByThroughputNo(throughputNo);

		//mnv.addObject("throughput",throughput);
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/autosavepoint",method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<String> autosavepoint(BranchVO branch,HttpSession session) {
		ResponseEntity<String> entity = null;
		EmployeesVO emp=(EmployeesVO) session.getAttribute("loginEmployee");
		branch.setBranchCode(emp.getBranchCode());
		try {
			laundryThroughputService.updateBranchLndrpcrymslmcoqy(branch);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	
	@RequestMapping("/branchdata")
	public ResponseEntity<List<LaundryThroughPutVO>> tableAndChart(String branchCode) throws Exception{
		ResponseEntity<List<LaundryThroughPutVO>> entity = null;
		LaundryThroughPutVO lv = new LaundryThroughPutVO();
		lv.setBranchCode(branchCode);
		try {
			List<LaundryThroughPutVO> branchTableList = laundryThroughputService.branchQuotaTable(lv.getBranchCode());
			entity = new ResponseEntity<List<LaundryThroughPutVO>>(branchTableList, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<LaundryThroughPutVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@RequestMapping("/datedata")
	public ResponseEntity<List<LaundryThroughPutVO>> tableDate(String date) throws Exception{
		ResponseEntity<List<LaundryThroughPutVO>> entity = null;
		try {
			List<LaundryThroughPutVO> branchTableList = laundryThroughputService.branchTableDate(date);
			entity = new ResponseEntity<List<LaundryThroughPutVO>>(branchTableList, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<LaundryThroughPutVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping(value = "/getWeeksBranchThroughput", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public ResponseEntity<List<LaundryThroughPutVO>> getBranchThroughput(String branchCode, String date) throws Exception{
		ResponseEntity<List<LaundryThroughPutVO>> entity = null;
		try {
			LaundryThroughPutVO throughPutVO = new LaundryThroughPutVO();
			throughPutVO.setBranchCode(branchCode);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date datetype = formatter.parse(date);
			throughPutVO.setDate(datetype);
			List<LaundryThroughPutVO> branchLaundryThroughList = laundryThroughputService.getWeeksBranchThroughput(throughPutVO);
			entity = new ResponseEntity<List<LaundryThroughPutVO>>(branchLaundryThroughList, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<LaundryThroughPutVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
}
