package com.rundering.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.command.Criteria;
import com.rundering.command.PageMaker;
import com.rundering.dao.AttachDAO;
import com.rundering.dao.BranchDAO;
import com.rundering.dao.ComCodeDAO;
import com.rundering.dao.LaundryArticlesDAO;
import com.rundering.dao.LaundryGoodsStockDAO;
import com.rundering.dto.AttachVO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.ComCodeVO;
import com.rundering.dto.LaundryArticlesVO;
import com.rundering.dto.LaundryGoodsStockVO;
import com.rundering.util.ComCodeUtil;

public class LaundryArticlesServiceImpl implements LaundryArticlesService {

	private LaundryArticlesDAO laundryArticlesDAO;
	private AttachDAO attachDAO;
	private ComCodeDAO comCodeDAO;
	private BranchDAO branchDAO;
	private LaundryGoodsStockDAO laundryGoodsStockDAO;

	public void setLaundryArticlesDAO(LaundryArticlesDAO laundryArticlesDAO) {
		this.laundryArticlesDAO = laundryArticlesDAO;
	}
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}
	
	public void setComCodeDAO(ComCodeDAO comCodeDAO) {
		this.comCodeDAO = comCodeDAO;
	}
	
	public void setLaundryGoodsStockDAO(LaundryGoodsStockDAO laundryGoodsStockDAO) {
		this.laundryGoodsStockDAO = laundryGoodsStockDAO;
	}
	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}

	@Override
	public Map<String, Object> getLaundryArticles(Criteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ComCodeUtil comCodeUtil = new ComCodeUtil();
		
		try {
			comCodeUtil.getCodeListMap("EACH", dataMap, comCodeDAO);
			comCodeUtil.getCodeListMap("CLCODE", dataMap, comCodeDAO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<LaundryArticlesVO> laundryArticlesList = laundryArticlesDAO.NotALaundryArticlesList(cri);
		// 전체 board 개수
		int totalCount = laundryArticlesDAO.selectLaundryArticlesCriteriaTotalCount(cri);

		
		
		// PageMaker 생성.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("laundryArticlesList", laundryArticlesList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	@Override
	public void regist(LaundryArticlesVO laundryArticles,AttachVO attach) throws Exception {
		int seq=attachDAO.selectFileNo();
		String strSeq = Integer.toString(seq);
		attach.setAtchFileNo(strSeq);
		
		String articlesCode=laundryArticlesDAO.selectLaundryArticles(laundryArticles.getClcode());
		if(articlesCode!=null) {
			int articlesNumber= Integer.parseInt(articlesCode);
			articlesNumber++;
			if(articlesNumber<10) {
				articlesCode =laundryArticles.getClcode()+"0"+articlesNumber;
			}else {
				articlesCode=laundryArticles.getClcode()+""+articlesNumber;
			}
		}else {
			articlesCode=laundryArticles.getClcode()+"01";
		}
		laundryArticles.setAtchFileNo(strSeq);
		
		laundryArticles.setArticlesCode(articlesCode);
		attachDAO.insertOrderGoodsAtach(attach);
		laundryArticlesDAO.insertLaundryArticles(laundryArticles);
		
		List<BranchVO> branchList = branchDAO.selectBranchList();
		for (BranchVO branch : branchList) {
			LaundryGoodsStockVO laundryGoodsStock = new LaundryGoodsStockVO();
			laundryGoodsStock.setArticlesCode(articlesCode);
			laundryGoodsStock.setBranchCode(branch.getBranchCode());
			laundryGoodsStockDAO.insertLaundryGoodsStock(laundryGoodsStock);
		}
	}

	@Override
	public LaundryArticlesVO getLaundryArticles(String articlesCode) throws SQLException {
		LaundryArticlesVO laundryArticles = laundryArticlesDAO.selectLaundryArticlesListByArticlesCode(articlesCode);
		
		return laundryArticles;
	} 


	@Override
	public void modify(LaundryArticlesVO laundryArticles) throws SQLException {
		
		laundryArticlesDAO.updateLaundryArticles(laundryArticles);
	}

	@Override
	public void remove(String articlesCode) throws SQLException {
		laundryArticlesDAO.deleteLaundryArticles(articlesCode);
		
	}
	@Override
	public Map<String, Object> getItemCode() throws Exception{
		ComCodeUtil codeUtil = new ComCodeUtil();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		codeUtil.getCodeListMap("CLCODE", dataMap, comCodeDAO);
		return dataMap;
	}
}
