package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.rundering.command.Criteria;
import com.rundering.dto.LaundryItemsVO;

public class LaundryItemsDAOImpl implements LaundryItemsDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	// 의류 시퀀스
	@Override
	public String SelectClothingSequenceNextValue() throws SQLException{
		return session.selectOne("LaundryItems-Mapper.selectClothingSequenceNextValue");
	}
	// 침구 시퀀스
	@Override	
	public String SelectBeddingSequenceNextValue() throws SQLException{
		return session.selectOne("LaundryItems-Mapper.selectBeddingSequenceNextValue");
	}
	// 신발 시퀀스
	@Override
	public String SelectShoesSequenceNextValue() throws SQLException{
		return session.selectOne("LaundryItems-Mapper.selectShoesSequenceNextValue");
	}

	// 세탁품목 등록
	@Override
	public void insertLaundryItems(LaundryItemsVO laundryItems) throws SQLException{
		session.update("LaundryItems-Mapper.insertLaundryItems", laundryItems);
	}
	
	// 세탁품목 조회 - BylaundryItemsCode
	@Override
	public LaundryItemsVO selectLaundryItemsBylaundryItemsCode(String laundryItemsCode) throws SQLException {
		return session.selectOne("LaundryItems-Mapper.selectLaundryItemsBylaundryItemsCode",laundryItemsCode);
	}
	
	// 세탁품목 전체 목록조회 - 이용 안내
	@Override
	public List<LaundryItemsVO> selectlaundryItemsList(Criteria cri) throws SQLException {
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);		
		
		return session.selectList("LaundryItems-Mapper.selectSearchLaundryItemList", cri, rowBounds);
	}

	@Override
	public int selectLaundryItemsCriteriaTotalCount(Criteria cri) throws SQLException {
		int count = session.selectOne("LaundryItems-Mapper.selectLaundryItemsCriteriaTotalCount",cri);
		return count;
	}


	@Override
	public void updateLaundryItem(LaundryItemsVO laundryItems) throws SQLException {
		session.update("LaundryItems-Mapper.updateLaundryItems", laundryItems);
	}

	@Override
	public void deleteLaundryItem(String laundryItemsCode) throws SQLException {
		session.update("LaundryItems-Mapper.deleteLaundryItems", laundryItemsCode);
	}

}
