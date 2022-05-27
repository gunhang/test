package com.rundering.service;

import com.rundering.dao.MemberAddressDAO;
import com.rundering.dto.MemberAddressVO;

public class MemberAddressServiceImpl implements MemberAddressService {

	private MemberAddressDAO memberAddressDAO;
	public void setMemberAddressDAO(MemberAddressDAO memberAddressDAO) {
		this.memberAddressDAO = memberAddressDAO;
	}
	
	//주소지 등록
	@Override
	public void memberAddressRegist(MemberAddressVO memberAdd) throws Exception {
		
		String ta = memberAdd.getAdd1().substring(0,2);
		String ar = memberAdd.getAdd1().substring(3,5);
		
		if(ta.equals("서울")) {
			memberAdd.setToparea("01");
		}else if (ta.equals("부산")) {
			memberAdd.setToparea("02");
		}else if (ta.equals("대구")) {
			memberAdd.setToparea("03");
		}else if (ta.equals("인천")) {
			memberAdd.setToparea("04");
		}else if (ta.equals("광주")) {
			memberAdd.setToparea("05");
		}else if (ta.equals("대전")) {
			if(ar.equals("동구")) {
				memberAdd.setToparea("06");
				memberAdd.setArea("0601");
			}else if(ar.equals("중구")) {
				memberAdd.setToparea("06");
				memberAdd.setArea("0602");
			}else if(ar.equals("서구")) {
				memberAdd.setToparea("06");
				memberAdd.setArea("0603");
			}else if(ar.equals("유성")) {
				memberAdd.setToparea("06");
				memberAdd.setArea("0604");
			}else if(ar.equals("대덕")) {
				memberAdd.setToparea("06");
				memberAdd.setArea("0605");
			}
		}else if (ta.equals("울산")) {
			memberAdd.setToparea("07");
		}else if (ta.equals("세종")) {
			memberAdd.setToparea("08");
		}else {
			
		}
		
		memberAddressDAO.addRegist(memberAdd);
		
	}

}