package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.MyOrderCriteria;
import com.rundering.dto.LaundryOrderVO;

public class LaundryOrderDAOImpl implements LaundryOrderDAO{
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	// 세탁주문번호생성
	@Override
	public String selectLaundryOrderSequenceNextValue() throws SQLException {
		String orderNo = session.selectOne("LaundryOrder-Mapper.selectLaundryOrderSequenceNextValue");
		return orderNo;
	}
	
	// 세탁주문접수
	@Override
	public void insertLaundryOrder(LaundryOrderVO laundryOrder) throws SQLException {
		session.update("LaundryOrder-Mapper.insertLaundryOrder", laundryOrder);
	}

	@Override
	public LaundryOrderVO selectLaundryOrderByOrderNo(String orderNo) throws SQLException {
		LaundryOrderVO laundryOrder = session.selectOne("LaundryOrder-Mapper.selectLaundryOrderByOrderNo",orderNo);
		return laundryOrder;
	}
	
	// 마이페이지 - 전체 주문내역 가져오기
	@Override
	public List<LaundryOrderVO> getMyOrderList(MyOrderCriteria cri) throws Exception {
		
		//페이징 처리를 위한 것들
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<LaundryOrderVO> myOrderList = session.selectList("LaundryOrder-Mapper.getmyorderList", cri, rowBounds);

		return myOrderList;
	}
	
	// 마이페이지 - 주문내역 전체 개수 체크
	@Override
	public int myOrderList(MyOrderCriteria cri) throws SQLException {
		int count = session.selectOne("LaundryOrder-Mapper.myOrderListCount", cri);
		return count;
	}
	
	// 마이페이지 - 주문내역 진행중인 내역 가져오기
	@Override
	public List<LaundryOrderVO> getMyOrderIngList(MyOrderCriteria cri) throws Exception {
		//페이징 처리를 위한 것들
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<LaundryOrderVO> myOrderList = session.selectList("LaundryOrder-Mapper.getmyorderIngList", cri, rowBounds);

		return myOrderList;
	}
	
	// 마이페이지 - 진행중인 주문내역 개수 체크
	@Override
	public int myOrderIngList(MyOrderCriteria cri) throws SQLException {
		return session.selectOne("LaundryOrder-Mapper.myCompleteOrderIngListCount", cri);
	}
	
	// 마이페이지 - 완료된 내 주문내역 가져오기
	@Override
	public List<LaundryOrderVO> getMyCompleteOrderList(MyOrderCriteria cri) throws Exception {
		//페이징 처리를 위한 것들
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<LaundryOrderVO> myOrderList = session.selectList("LaundryOrder-Mapper.getmyCompleteorderList", cri, rowBounds);

		return myOrderList;
	}

	// 마이페이지 - 완료된 주문내역 개수 체크
	@Override
	public int myCompleteOrderList(MyOrderCriteria cri) throws SQLException {
		return session.selectOne("LaundryOrder-Mapper.myCompleteOrderListCount", cri);
	}

	// 마이페이지 - 주문내역 디테일
	@Override
	public LaundryOrderVO getmyorderByorderNo(String orderNo) throws Exception {
		return session.selectOne("LaundryOrder-Mapper.getmyorderByorderNo", orderNo);
	}

	// 마이페이지 - 주문취소
	@Override
	public void cancelLaundryOrder(String orderNo) throws Exception {
		session.update("LaundryOrder-Mapper.cancelLaundryOrder", orderNo);
	}

	// 마이페이지 - 요청사항 수정
	@Override
	public void modifyReq(LaundryOrderVO laundryOrder) throws Exception {
		session.update("LaundryOrder-Mapper.modifyReq", laundryOrder);
	}
		
	// 마이페이지 - 요청사항 삭제
	@Override
	public void removeReq(String orderNo) throws Exception {
		session.update("LaundryOrder-Mapper.removeReq", orderNo);
	}

	
}
