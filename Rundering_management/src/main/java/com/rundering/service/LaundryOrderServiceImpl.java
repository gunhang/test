package com.rundering.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.rundering.command.AdminLaundryOrderListCriteria;
import com.rundering.command.BranchCriteria;
import com.rundering.command.BranchInfoDetailCommand;
import com.rundering.command.BranchPageMaker;
import com.rundering.command.PageMaker;
import com.rundering.dao.AttachDAO;
import com.rundering.dao.BranchDAO;
import com.rundering.dao.ComCodeDAO;
import com.rundering.dao.FAQDAO;
import com.rundering.dao.LaundryItemsDAO;
import com.rundering.dao.LaundryOrderDAO;
import com.rundering.dao.LaundryOrderDetailDAO;
import com.rundering.dao.LaundryThroughputDAO;
import com.rundering.dao.MemberDAO;
import com.rundering.dao.ReplyDAO;
import com.rundering.dto.AttachVO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.FAQVO;
import com.rundering.dto.LaundryOrderDetailVO;
import com.rundering.dto.LaundryOrderVO;
import com.rundering.dto.MemberVO;
import com.rundering.dto.OrderDelayDTO;
import com.rundering.scheduler.OrderTaskScheduler;
import com.rundering.util.ComCodeUtil;
import com.rundering.util.FormatUtil;

public class LaundryOrderServiceImpl implements LaundryOrderService {
	
	private LaundryOrderDAO laundryOrderDAO;
	public void setLaundryOrderDAO(LaundryOrderDAO laundryOrderDAO) {
		this.laundryOrderDAO = laundryOrderDAO;
	}
	private LaundryOrderDetailDAO laundryOrderDetailDAO;
	public void setLaundryOrderDetailDAO(LaundryOrderDetailDAO laundryOrderDetailDAO) {
		this.laundryOrderDetailDAO = laundryOrderDetailDAO;
	}
	private LaundryItemsDAO laundryItemsDAO;
	public void setLaundryItemsDAO(LaundryItemsDAO laundryItemsDAO) {
		this.laundryItemsDAO = laundryItemsDAO;
	}
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	private ComCodeDAO comCodeDAO;
	public void setComCodeDAO(ComCodeDAO comCodeDAO) {
		this.comCodeDAO = comCodeDAO;
	}
	private BranchDAO branchDAO;
	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}
	private LaundryThroughputDAO laundryThroughputDAO;
	public void setLaundryThroughputDAO(LaundryThroughputDAO laundryThroughputDAO) {
		this.laundryThroughputDAO = laundryThroughputDAO;
	}
	private FAQDAO faqDAO;
	public void setFaqDAO(FAQDAO faqDAO) {
		this.faqDAO = faqDAO;
	}
	private OrderTaskScheduler orderTaskScheduler;
	public void setOrderTaskScheduler(OrderTaskScheduler orderTaskScheduler) {
		this.orderTaskScheduler = orderTaskScheduler;
	}
	@Override
	public Map<String,Object> laundryOrderList(BranchCriteria cri) throws Exception{
		ComCodeUtil comCodeUtil =new ComCodeUtil();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String,String> orderCodeMap = new HashMap<String, String>();
		Map<String,String> laundryCodeMap = new HashMap<String, String>();
		comCodeUtil.getCodeMap("LAUNDRY_STATUS", laundryCodeMap, comCodeDAO);
		comCodeUtil.getUpperCodeMap("ORDER_STATUS", orderCodeMap, comCodeDAO);
		List<LaundryOrderVO> laundryOrderList = laundryOrderDAO.selectLaundryOrderList(cri);
		Map<String, Object> detailMap = new HashMap<String, Object>();
		
		int lastDate = laundryOrderDAO.selectLastdayRequestDate(cri);
		int todayDate = laundryOrderDAO.selectTodayRequestDate(cri);
		
		
		for (LaundryOrderVO laundryOrder : laundryOrderList) {
			List<LaundryOrderDetailVO> LaundryOrderDetailList = laundryOrderDetailDAO.selectlaundryOrderDetailListByOrderNo(laundryOrder.getOrderNo());
			detailMap.put(laundryOrder.getOrderNo(), LaundryOrderDetailList);
		}
		
		// 전체 board 개수
		int totalCount = laundryOrderDAO.selectCount(cri);

		// PageMaker 생성.
		BranchPageMaker pageMaker = new BranchPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		
		
		dataMap.put("lastDate",lastDate);
		dataMap.put("todayDate",todayDate);
		dataMap.put("laundryOrderList", laundryOrderList);
		dataMap.put("pageMaker", pageMaker);
		dataMap.put("laundryCodeMap",laundryCodeMap);
		dataMap.put("orderCodeMap",orderCodeMap);
		dataMap.put("detailMap",detailMap);

		return dataMap;
	}
	@Override
	public void updateStatus(List<LaundryOrderVO> laundryOrderList) throws Exception{
		for (LaundryOrderVO laundryOrder : laundryOrderList) {
			laundryOrderDAO.updateLaundryOrderStatusByOrderNo(laundryOrder);
		}
	}
	@Override
	public List<AttachVO> selectAttachList(String attchNo) throws Exception{
		return attachDAO.selectAttachVOByFileNo(attchNo);
	}
	@Override
	public Map<String, Object> getAdminlaundryOrderList(AdminLaundryOrderListCriteria cri) throws Exception {
		ComCodeUtil comCodeUtil =new ComCodeUtil();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String,String> orderCodeMap = new HashMap<String, String>();
		Map<String,String> areaCodeMap = new HashMap<String, String>();
		Map<String,String> branchNameMap = new HashMap<String, String>();
		comCodeUtil.getUpperCodeMap("ORDER_STATUS", orderCodeMap, comCodeDAO);
		comCodeUtil.getCodeMap("AREA", areaCodeMap, comCodeDAO);
		
		List<BranchVO> branchList = branchDAO.selectBranchList();
		for (BranchVO branchVO : branchList) {
			branchNameMap.put(branchVO.getBranchCode(), branchVO.getBranchName());
		}
		
		int noBranchOrder = laundryOrderDAO.selectNoBranchLaundryOrderCount();
		
		List<LaundryOrderVO> laundryOrderList = laundryOrderDAO.selectAdminLaundryOrderList(cri);
		
		int totalCount = laundryOrderDAO.selectCount(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("noBranchOrder",noBranchOrder);
		dataMap.put("laundryOrderList", laundryOrderList);
		dataMap.put("pageMaker", pageMaker);
		dataMap.put("orderCodeMap",orderCodeMap);
		dataMap.put("areaCodeMap",areaCodeMap);
		dataMap.put("branchNameMap",branchNameMap);
		return dataMap;
	}
	@Override
	public Map<String, Object> getlaundryOrderByOrderNo(String orderNo) throws Exception {
		ComCodeUtil comCodeUtil =new ComCodeUtil();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String,String> orderCodeMap = new HashMap<String, String>();
		Map<String,String> areaCodeMap = new HashMap<String, String>();
		Map<String,String> branchNameMap = new HashMap<String, String>();
		comCodeUtil.getUpperCodeMap("ORDER_STATUS", orderCodeMap, comCodeDAO);
		comCodeUtil.getCodeMap("AREA", areaCodeMap, comCodeDAO);
		
		LaundryOrderVO laundryOrder = laundryOrderDAO.selectLaundryOrderByOrderNo(orderNo);
		if(laundryOrder==null) {
			return null;
		}
		List<LaundryOrderDetailVO> laundryOrderDetailList = laundryOrderDetailDAO.selectlaundryOrderDetailListByOrderNo(orderNo);
		
		BranchInfoDetailCommand branchDetail = null;;
		
		if(laundryOrder.getBranchCode() != null) {
			branchDetail = laundryThroughputDAO.selectBranchDetail(laundryOrder.getBranchCode());
			
			BranchVO branch = branchDAO.selectBranchByBranchCode(laundryOrder.getBranchCode());
			laundryOrder.setBranchCode(branch.getBranchName());
		}
		MemberVO member = memberDAO.selectMemberByMemberNo(laundryOrder.getMemberNo());
		laundryOrder.setMemberNo(member.getName());
		
		laundryOrder.setContactNumber(FormatUtil.hyphenationPhoneNum(laundryOrder.getContactNumber()));
		
		List<FAQVO> faqList = faqDAO.selectFAQListByOrderNo(laundryOrder.getOrderNo());
		
		dataMap.put("orderCodeMap",orderCodeMap);
		dataMap.put("areaCodeMap",areaCodeMap);
		dataMap.put("branchNameMap",branchNameMap);
		dataMap.put("laundryOrder", laundryOrder);
		dataMap.put("laundryOrderDetailList", laundryOrderDetailList);
		dataMap.put("branchDetail", branchDetail);
		dataMap.put("faqList", faqList);
		
		
		return dataMap;
	}
	@Override 
	public String selectGetBranchCode(String branchCode) {
		String result=null;
		try {
			BranchVO branch=branchDAO.selectBranchByBranchCode(branchCode);
			result= branch.getBranchName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public Map<String, Object> getConfirmOrderAssignmentInfo(AdminLaundryOrderListCriteria cri) throws Exception {
		ComCodeUtil comCodeUtil =new ComCodeUtil();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String,String> orderCodeMap = new HashMap<String, String>();
		Map<String,String> areaCodeMap = new HashMap<String, String>();
		Map<String,String> branchNameMap = new HashMap<String, String>();
		List<BranchVO> excessCapacityList = new ArrayList<BranchVO>();
		comCodeUtil.getUpperCodeMap("ORDER_STATUS", orderCodeMap, comCodeDAO);
		comCodeUtil.getCodeMap("AREA", areaCodeMap, comCodeDAO);
		
		List<BranchVO> branchList = branchDAO.selectAvailableBranchList();
		for (BranchVO branchVO : branchList) {
			branchNameMap.put(branchVO.getBranchCode(), branchVO.getBranchName());
		}
		
		List<LaundryOrderVO> laundryOrderList = new ArrayList<LaundryOrderVO>();
		
		if(cri.getSelectAllOrderNo()!=null && cri.getSelectAllOrderNo().equals("true")) {
			String orderStatus = cri.getListOrderStatus().toString();
			orderStatus = orderStatus.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
			cri.setOrderStatus(orderStatus);
			laundryOrderList = laundryOrderDAO.selectAllLaundryOrderList(cri);
			
		}else {
			for (String orderNo : cri.getListSelectOrderNo()) {
				LaundryOrderVO orderVO = laundryOrderDAO.selectLaundryOrderByOrderNo(orderNo);
				laundryOrderList.add(orderVO);
			}
		}
		
		for (BranchVO branchVO : branchList) {
			if(branchVO.getBranchCode().equals("000000")) continue;
			BranchVO branch = new BranchVO();
			branch.setBranchCode(branchVO.getBranchCode());
			branch.setBranchLndrpcrymslmcoqy(branchDAO.selectExcessCapacityOfTomorrowLaundryByBranchCode(branchVO.getBranchCode()));
			excessCapacityList.add(branch);
		}
		dataMap.put("branchList", branchList);
		dataMap.put("excessCapacityList", excessCapacityList);
		
		int totalCount = laundryOrderList.size();
		
		dataMap.put("laundryOrderList", laundryOrderList);
		dataMap.put("orderCodeMap",orderCodeMap);
		dataMap.put("areaCodeMap",areaCodeMap);
		dataMap.put("branchNameMap",branchNameMap);
		dataMap.put("totalCount",totalCount);
		return dataMap;
	}
	@Override
	public Map<String, Object> assignmentOrder(AdminLaundryOrderListCriteria cri) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		int countOrder = 0;
		LaundryOrderVO order = new LaundryOrderVO();
		for (String orderNo : cri.getListSelectOrderNo()) {
			order.setOrderNo(orderNo);
			order.setBranchCode(cri.getBranchCode());
			laundryOrderDAO.updateLaundryOrderbranchCode(order);
			System.out.println(orderNo+" : "+cri.getBranchCode()+"에 할당");
			countOrder++;
		}
		String branchName = branchDAO.selectBranchByBranchCode(cri.getBranchCode()).getBranchName();
		
		dataMap.put("countOrder", countOrder);
		dataMap.put("branchName", branchName);
		return dataMap;
	}
	@Override
	public Map<String, Object> piChart() throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<BranchVO> branchList =branchDAO.selectBranchList();
		dataMap.put("branchList", branchList);
		for (BranchVO branch : branchList) {
			int count= laundryOrderDAO.selectLaundryOrderCountTodayByBranchCode(branch.getBranchCode());
			dataMap.put(branch.getBranchCode(), count);
		}
		return dataMap;
	}
	@Override
	public Map<String, Object> orderDelay(OrderDelayDTO orderDelay) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Integer> countList = new ArrayList<Integer>();
		List<Date> dateList = new ArrayList<Date>();
		/*1주일씩 데이터를 가져옴*/
		for (int i = 0 ; i <7; i++) {
			orderDelay.setStartNumber((i+1)*7);
			orderDelay.setEndNumber(i*7);
			
			OrderDelayDTO resultOrderDelay= laundryOrderDAO.selectLaundryOrderLateDeliveryByBranchCode(orderDelay);
			
			countList.add(resultOrderDelay.getCount());
			dateList.add(resultOrderDelay.getDay());
		}
		dataMap.put("countList", countList);
		dataMap.put("dateList", dateList);
		
		
		return dataMap;
	}
	@Override
	public Map<String,Object> branchOrderPiChart(String branchCode) throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		BranchVO branch = branchDAO.selectBranchByBranchCode(branchCode);
		int count= laundryOrderDAO.selectLaundryOrderCountTodayByBranchCode(branchCode);
		dataMap.put("branch", branch);
		dataMap.put("count",count);
		
		return dataMap;
	}
	@Override
	public Map<String, Object> autoAssignmentOrder() throws Exception {
		Map<String, Object> dataMap = orderTaskScheduler.assignLaundryOrderToBranch();
		return dataMap;
	}
	@Override
	public Map<String, Object> handOverToDeliveryEmployee(String branchCode) throws Exception {
		Map<String, Object> dataMap = orderTaskScheduler.assignLaundryOrderToBranchDeliveryEmployee(branchCode);
		return dataMap;
	}
}
