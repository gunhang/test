package com.rundering.service;

import java.sql.SQLException;
import java.util.List;

import com.rundering.dao.MemberAddressDAO;
import com.rundering.dto.MemberAddressVO;

public class MemberAddressServiceImpl implements MemberAddressService{
	private MemberAddressDAO memberAddressDAO;
	public void setMemberAddressDAO(MemberAddressDAO memberAddressDAO) {
		this.memberAddressDAO = memberAddressDAO;
	}
	
	//주소지 등록
	@Override
	public void memberAddressRegist(MemberAddressVO memberAdd) throws Exception {
		
		if(memberAdd.getDefaultYn() == null) {
			memberAdd.setDefaultYn("N");
		}else if(memberAdd.getDefaultYn().equals("Y")) {
			memberAddressDAO.updateAllMemberAddressDefaultN(memberAdd.getMemberNo());
		}
		
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
		}
		
		
		memberAddressDAO.addRegist(memberAdd);
		
	}
	
	//기본주소지 조회
	@Override
	public MemberAddressVO getDefaultMemberAddress(String memberNo) throws SQLException {
		return memberAddressDAO.selectDefaultMemberAddressByMemberNo(memberNo);
	}
	
	//주소지 조회 - 주소번호로
	@Override
	public MemberAddressVO getMemberAddress(String addressNo) throws SQLException {
		return memberAddressDAO.selectMemberAddressByAddressNo(addressNo);
	}
	
	//회원주소지 목록 조회 - memberNo로
	@Override
	public List<MemberAddressVO> getMemberAddressList(String memberNo) throws SQLException {
		return memberAddressDAO.selectMemberAddressListByMemberNo(memberNo);
	}
	
	//지역코드 설정
	@Override
	public MemberAddressVO getAreaCode(MemberAddressVO memberAdd) {
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
		}
		
		return memberAdd;
	}

	//기본 주소지로 등록
	@Override
	public void defaultAddressRegist(MemberAddressVO memberAdd) throws Exception {
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
		}
		
		memberAddressDAO.updateDefaultReset(memberAdd.getMemberNo());
		memberAddressDAO.addRegist(memberAdd);
		
	}
	
	// 주소지 추가 등록
	@Override
	public void addAddressRegist(MemberAddressVO memberAdd) throws Exception {
			
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
		}
		
		memberAddressDAO.addrRegist(memberAdd);
		
	}

	
	// 기본 주소지로 변경 및 수정 
	@Override
	public void modifyDefaultAddress(MemberAddressVO memberAdd) throws Exception {
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
		}
		
		memberAddressDAO.updateDefaultReset(memberAdd.getMemberNo());
		memberAddressDAO.modifyDefaultAddr(memberAdd);
		
	}
	
	// 주소지 수정
	@Override
	public void modifyAddress(MemberAddressVO memberAdd) throws Exception {
		
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
		}
		
		memberAddressDAO.modifyAddr(memberAdd);
		
	}
	
	// 주소지 삭제(기본주소지 제외)
	@Override
	public void removeByAddressNo(String memberNo) throws Exception {
		memberAddressDAO.removeByAddressNo(memberNo);
		
	}

	
	
}
