package com.rundering.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.AppCriteria;
import com.rundering.command.BranchInfoDetailCommand;
import com.rundering.dto.BranchVO;
import com.rundering.dto.LaundryThroughPutVO;

public class LaundryThroughputDAOImpl implements LaundryThroughputDAO{
	
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<LaundryThroughPutVO> throughputList(AppCriteria cri) throws Exception {
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);	
		
		List<LaundryThroughPutVO> throughputList = session.selectList("LaundryThroughput-Mapper.laundryQuotaList", cri, rowBounds);
		
		return throughputList;
	}
	
	@Override
	public List<LaundryThroughPutVO> throughputList() throws Exception {
		List<LaundryThroughPutVO> throughputList = session.selectList("LaundryThroughput-Mapper.laundryQuotaList");
		return throughputList;
	}

	@Override
	public int throughputListCount(AppCriteria cri) throws Exception {
		int count = session.selectOne("LaundryThroughput-Mapper.laundryQuotaListCount", cri);
		return count;
	}

	@Override
	public LaundryThroughPutVO selectLaundryQuatoByThroughputNo(String throughputNo) throws Exception {
		
		LaundryThroughPutVO throughput = session.selectOne("LaundryThroughput-Mapper.laundryQuotaDetail", throughputNo);
		
		return throughput;
	}
	@Override
	public BranchInfoDetailCommand selectBranchDetail(String branchCode) throws Exception {
		BranchInfoDetailCommand branchList = session.selectOne("LaundryThroughput-Mapper.branchDetail", branchCode);
		
		return branchList;
	}
	
	//표 리스트 출력(지점별)
	@Override
	public List<LaundryThroughPutVO> branchQuotaTable(String branchCode) throws Exception {
		List<LaundryThroughPutVO> branchTableList = session.selectList("LaundryThroughput-Mapper.branchQuotaTable", branchCode);
		return branchTableList;
	}
	//표 리스트 출력(일자별)
	@Override
	public List<LaundryThroughPutVO> branchTableDate(String date) throws Exception {
		List<LaundryThroughPutVO> branchTableList = session.selectList("LaundryThroughput-Mapper.changeDateBranchQuotaTable", date);
		return branchTableList;
	}

	@Override
	public List<LaundryThroughPutVO> selectWeeksBranchLaundryThroughPut(LaundryThroughPutVO throughPutVO) {
		List<LaundryThroughPutVO> branchLaundryThroughPutList = session.selectList("LaundryThroughput-Mapper.selectWeeksBranchLaundryThroughPut", throughPutVO);
		return branchLaundryThroughPutList;
	}
	@Override
	public void updateBranchLndrpcrymslmcoqy(BranchVO branch) throws Exception {
		session.update("LaundryThroughput-Mapper.updateBranchLndrpcrymslmcoqy",branch);
	}

}
