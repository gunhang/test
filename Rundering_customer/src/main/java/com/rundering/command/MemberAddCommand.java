package com.rundering.command;

import com.rundering.dto.MemberAddressVO;
import com.rundering.dto.MemberVO;

public class MemberAddCommand {
	
	private String memberNo; 	//회원고유번호
	private String id;			//아이디
	private String password;	//패스워드
	private String name;		//이름
	private String phone;		//연락처
	private String email;		//이메일
	private String zip; 		//우편번호
	private String add1;  		//주소
	private String add2; 	 	//상세주소
	
	
	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public MemberVO toMember() {
		MemberVO member = new MemberVO();
		member.setId(this.id);
		member.setPassword(this.password);
		member.setName(this.name);
		member.setPhone(this.phone);
		member.setEmail(this.email);
		
		return member;
	}
	
	public MemberAddressVO toAddr() {
		MemberAddressVO memberAdd = new MemberAddressVO();
		memberAdd.setMemberNo(this.memberNo);
		memberAdd.setZip(this.zip);
		memberAdd.setAdd1(this.add1);
		memberAdd.setAdd2(this.add2);
		
		return memberAdd;
	}
}
