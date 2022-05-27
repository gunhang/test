package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.MyOrderCriteria;
import com.rundering.dto.PaymentVO;

public class PaymentDAOImpl implements PaymentDAO{
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public void insertPayment(PaymentVO payment) throws SQLException {
		session.update("Payment-Mapper.insertPayment", payment);
	}

	@Override
	public void updatePaymentOrderNo(PaymentVO payment) throws SQLException {
		session.update("Payment-Mapper.updatePaymentOrderNo", payment);
	}

	// 결제 취소
	@Override
	public void cancelPayment(String orderNo) throws Exception {
		session.update("Payment-Mapper.cancelPayment", orderNo);
	}
	
	// 결제 내역
	@Override
	public List<PaymentVO> getComList(MyOrderCriteria cri) throws Exception {
		//페이징 처리를 위한 것들
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return session.selectList("Payment-Mapper.getComList", cri,rowBounds);
	}
	
	// 결제 개수 체크
	@Override
	public int comCount(MyOrderCriteria cri) throws Exception {
		return session.selectOne("Payment-Mapper.comCount",cri);
	}
	
	// 주문 취소 내역
	@Override
	public List<PaymentVO> getCancelList(MyOrderCriteria cri) throws Exception {
		//페이징 처리를 위한 것들
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return session.selectList("Payment-Mapper.getCancelList", cri,rowBounds);
	}
	
	// 주문취소 개수
	@Override
	public int cancelCount(MyOrderCriteria cri) throws Exception {
		return session.selectOne("Payment-Mapper.cancelCount",cri);
	}

}
