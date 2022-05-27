package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.AdminLaundryOrderListCriteria;
import com.rundering.command.BranchCriteria;
import com.rundering.dto.LaundryOrderVO;
import com.rundering.dto.OrderDelayDTO;

public class LaundryOrderDAOImpl implements LaundryOrderDAO{
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<LaundryOrderVO> selectLaundryOrderList(BranchCriteria cri) throws Exception{
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);
		return session.selectList("LaundryOrder-Mapper.selectLaundryOrderList", cri,rowBounds);
		
	}
	@Override
	public int selectCount(BranchCriteria cri) throws Exception{
		int count = session.selectOne("LaundryOrder-Mapper.selectCount",cri);
		return count;
	}
	@Override
	public int selectCount(AdminLaundryOrderListCriteria cri) throws Exception{
		int count = session.selectOne("LaundryOrder-Mapper.selectAdminLaundryOrderListCount",cri);
		return count;
	}

	@Override
	public void updateLaundryOrderStatusByOrderNo(LaundryOrderVO laundryOrderVO) throws Exception {
		session.update("LaundryOrder-Mapper.updateLaundryOrderStatusByOrderNo", laundryOrderVO);
		
	}

	@Override
	public List<LaundryOrderVO> selectAdminLaundryOrderList(AdminLaundryOrderListCriteria cri) throws Exception {
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);
		return session.selectList("LaundryOrder-Mapper.selectAdminLaundryOrderList",cri,rowBounds);
	}

	@Override
	public LaundryOrderVO selectLaundryOrderByOrderNo(String orderNo) throws SQLException {
		return session.selectOne("LaundryOrder-Mapper.selectLaundryOrderByOrderNo",orderNo);
	}

	@Override
	public List<LaundryOrderVO> selectLaundryOrderListNotAssignedToBranchByArea(String area) throws Exception {
		return session.selectList("LaundryOrder-Mapper.selectLaundryOrderListNotAssignedToBranchByArea",area);
	}

	@Override
	public void updateLaundryOrderbranchCode(LaundryOrderVO laundryOrder) throws Exception {
		session.update("LaundryOrder-Mapper.updateLaundryOrderbranchCode", laundryOrder);
	}

	@Override
	public List<LaundryOrderVO> selectAllLaundryOrderList(AdminLaundryOrderListCriteria cri) throws Exception {
		return session.selectList("LaundryOrder-Mapper.selectAdminLaundryOrderList",cri);
	}

	@Override
	public List<LaundryOrderVO> selectLaundryOrderListPickUpRequestDateTodayByBranchCode(String branchCode) throws Exception {
		return session.selectList("LaundryOrder-Mapper.selectLaundryOrderListPickUpRequestDateTodayByBranchCode",branchCode);
	}

	@Override
	public void updateLaundryOrderPickupEmployeeId(LaundryOrderVO laundryOrder) throws Exception {
		session.update("LaundryOrder-Mapper.updateLaundryOrderPickupEmployeeId", laundryOrder);
	}

	@Override
	public void updateLaundryOrderDeliveryEmployeeId(LaundryOrderVO laundryOrder) throws Exception {
		session.update("LaundryOrder-Mapper.updateLaundryOrderDeliveryEmployeeId", laundryOrder);
	}

	@Override
	public List<LaundryOrderVO> selectCompletedLaundryOrderListByBranchCode(String branchCode) throws Exception {
		return session.selectList("LaundryOrder-Mapper.selectCompletedLaundryOrderListByBranchCode",branchCode);
	}

	@Override
	public LaundryOrderVO selectLaundryOrderByReplyNo(int replyNo) throws Exception{
		return session.selectOne("LaundryOrder-Mapper.selectLaundryOrderByReplyNo",replyNo);
	}

	@Override
	public int selectLaundryOrderCountTodayByBranchCode(String branchCode) throws Exception{
		
		return session.selectOne("LaundryOrder-Mapper.selectLaundryOrderCountTodayByBranchCode",branchCode);
	}
	@Override 
	public OrderDelayDTO selectLaundryOrderLateDeliveryByBranchCode(OrderDelayDTO orderDelay) throws Exception{
		return session.selectOne("LaundryOrder-Mapper.selectLaundryOrderLateDeliveryByBranchCode",orderDelay);
	}
	@Override
	public int selectTodayRequestDate(BranchCriteria cri) throws Exception{
		return session.selectOne("LaundryOrder-Mapper.selectTodayRequestDate",cri);
	}
	@Override
	public int selectLastdayRequestDate(BranchCriteria cri) throws Exception{
		return session.selectOne("LaundryOrder-Mapper.selectLastdayRequestDate",cri);
	}
	@Override
	public int selectNoBranchLaundryOrderCount() throws Exception{
		return session.selectOne("LaundryOrder-Mapper.selectNoBranchLaundryOrderCount");
	}
	
	


}
