package com.rundering.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.Criteria;
import com.rundering.command.PageMaker;
import com.rundering.dao.BranchApplicationDAO;
import com.rundering.dao.BranchDAO;
import com.rundering.dao.EmployeesDAO;
import com.rundering.dao.LaundryArticlesDAO;
import com.rundering.dao.LaundryFixturesDAO;
import com.rundering.dao.LaundryGoodsStockDAO;
import com.rundering.dao.MemberDAO;
import com.rundering.dto.BranchApplicationVO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.LaundryArticlesVO;
import com.rundering.dto.LaundryFixturesVO;
import com.rundering.dto.LaundryGoodsStockVO;
import com.rundering.dto.MemberVO;
import com.rundering.util.SensSms;
import com.rundering.util.UserSha256;

public class BranchApplicationServiceImpl implements BranchApplicationService{

	BranchApplicationDAO branchApplicationDAO;
	BranchDAO branchDAO;
	MemberDAO memberDAO;
	LaundryFixturesDAO laundryFixturesDAO;
	LaundryArticlesDAO laundryArticlesDAO;
	LaundryGoodsStockDAO laundryGoodsStockDAO;
	EmployeesDAO employeesDAO;
	
	MailSendService mailSendService; 
	
	public void setBranchApplicationDAO(BranchApplicationDAO branchApplicationDAO) {
		this.branchApplicationDAO = branchApplicationDAO;
	}
	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	public void setLaundryFixturesDAO(LaundryFixturesDAO laundryFixturesDAO) {
		this.laundryFixturesDAO = laundryFixturesDAO;
	}
	public void setLaundryArticlesDAO(LaundryArticlesDAO laundryArticlesDAO) {
		this.laundryArticlesDAO = laundryArticlesDAO;
	}
	public void setLaundryGoodsStockDAO(LaundryGoodsStockDAO laundryGoodsStockDAO) {
		this.laundryGoodsStockDAO = laundryGoodsStockDAO;
	}
	public void setEmployeesDAO(EmployeesDAO employeesDAO) {
		this.employeesDAO = employeesDAO;
	}
	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	private SensSms sensSms;
	public void setSensSms(SensSms sensSms) {
		this.sensSms = sensSms;
	}
	
	
	
	@Override
	public Map<String, Object> selectBranchApplicationList(Criteria cri) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<BranchApplicationVO>branchApplicationList = branchApplicationDAO.selectBranchApplication(cri);
				
		int count = branchApplicationDAO.selectBranchApplicationCount(cri);
		int work = branchApplicationDAO.selectWorkBranchApplicationCount();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);
		
		dataMap.put("work",work);
		dataMap.put("branchApplicationList", branchApplicationList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	@Override
	public BranchApplicationVO selectBranchApplication(String applicationNo) throws Exception{
		return branchApplicationDAO.selectBranchApplicationByapplicationNo(applicationNo);
	}
	
	@Override
	public BranchVO selectBranch(String phone) throws Exception{
		MemberVO member = memberDAO.selectMemberByPhone(phone);
		EmployeesVO emp = employeesDAO.selectEmployeeByMemberNo(member.getMemberNo());
		
		return branchDAO.selectBranchByBranchCode(emp.getBranchCode());
	}
	 
	@Override
	public void updateRejectContent(BranchApplicationVO branchApplication) throws Exception{
		branchApplicationDAO.updateApprovalreturnContentsByBranchApplicationVO(branchApplication);
		sendMesage(branchApplication.getPhone());
	}
	@Override
	public void updateApproval(BranchApplicationVO branchApplication) throws Exception{
		branchApplicationDAO.updateApprovalreturnYByBranchApplicationVO(branchApplication);
		sendMesage(branchApplication.getPhone());
	}

	@Override
	public void updateExamination(BranchApplicationVO branchApplication,EmployeesVO emp) throws Exception {
		branchApplicationDAO.updateExaminationByBranchApplicationVO(branchApplication);
		sendMesage(branchApplication.getPhone());
	}
	@Override
	public void updateVoluntaryCheck(BranchApplicationVO branchApplication) throws Exception{
		branchApplicationDAO.updateProgressStatusCode08ByBranchApplicationVO(branchApplication);
		sendMesage(branchApplication.getPhone());
	}
	
	private void sendMesage(String phoneNum) {
		//문자
		try {
		sensSms.sendSMS(phoneNum.trim(), "[Rundering]\n지점신청 처리상태가 변경되었습니다.\n지점신청확인에서 확인해주세요.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String selectBranchCode(String area) throws Exception{
		String maxBranchCode = branchDAO.selectBranchCodeByArea(area);
		if(maxBranchCode==null) {
			return area+"01";
		}
		int branchCode = Integer.parseInt(maxBranchCode)+1;
		String result = ""+branchCode;
		if(branchCode<100000) {
			result="0"+branchCode;
		}
	
		return result;
	}
	@Override
	public void enrollmentRegist(MemberVO member,BranchVO branch,List<LaundryFixturesVO> laundryFixturesVOList,String applicationNo) {
		List<LaundryArticlesVO> laundryArticlesList=null;
		EmployeesVO emp= new EmployeesVO();
		String memberNo = null;
				
		String branchCode = branch.getBranchCode();
		try {
			memberNo=memberDAO.selectMemberNoSeq();
			member.setMemberNo(memberNo);
			memberDAO.insertBranchMemberByMemberVO(member);
			branchDAO.insertBranch(branch);
			laundryArticlesList=laundryArticlesDAO.selectLandryArticlesStock();
			for (LaundryFixturesVO laundryFixtures : laundryFixturesVOList) {
				laundryFixturesDAO.insertFixturesByFixtrues(laundryFixtures);
			}
			BranchApplicationVO branchApplication = new BranchApplicationVO();
			branchApplication.setApplicationNo(Integer.parseInt(applicationNo));
			branchApplicationDAO.updateProgressStatusCode10ByBranchApplicationVO(branchApplication);
			
			LaundryGoodsStockVO laundryGoodsStock = new LaundryGoodsStockVO();
			for (LaundryArticlesVO laundryArticles : laundryArticlesList) {
				laundryGoodsStock.setArticlesCode(laundryArticles.getArticlesCode());
				laundryGoodsStock.setBranchCode(branchCode);
				laundryGoodsStockDAO.insertLaundryGoodsStock(laundryGoodsStock);
			}
			emp.setMemberno(memberNo);
			emp.setBranchCode(branchCode);
			
			employeesDAO.employeeBranchRegist(emp);
			
			emp= employeesDAO.selectEmployeeByMno(memberNo);
			
			member.setId(emp.getEmployeeId());
			String pwd = UserSha256.encrypt(emp.getEmployeeId());
			member.setPassword(pwd);
			member.setMemberNo(emp.getMemberno());
			memberDAO.updateMember(member);
			
			mailSendService.sendIdPwMail(emp.getMemberno());
			
			//고객문자알림
			try {
				sensSms.sendSMS(member.getPhone().trim(), "[Rundering]\n지점등록이 완료되었습니다.\n이메일을 확인해주세요.");
				} catch (Exception e) {
					e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	
	
}
