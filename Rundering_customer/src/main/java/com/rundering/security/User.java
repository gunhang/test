package com.rundering.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rundering.dto.MemberVO;
import com.rundering.service.MemberService;


public class User implements UserDetails {

	private MemberVO member;
	private MemberService memberService;
	private String userFail;
	
	
	public User(MemberVO member,MemberService memberService) {	
		this.member = member;
		this.memberService=memberService;
	}
	
	 
	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities(){
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();	
		List<String> authList;
		try {
			authList = memberService.getAuthList(member.getMemberNo());
		for(int i=0; i<authList.size();i++) {
			roles.add(new SimpleGrantedAuthority(authList.get(i)));
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}
 
	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getId();
	}


	public String getUserFail() {
		return userFail;
	}


	public void setUserFail(String userFail) {
		this.userFail = userFail;
	}


	@Override
	public boolean isAccountNonExpired() { //기간제 계정의 경우 기간만료 여부  : enabled =4
		return member.getEnableWhether()!=4;
 	}

	@Override
	public boolean isAccountNonLocked() { //사용 정지 혹은 휴먼계정  : enabled =3
		return member.getEnableWhether()!=3;
	}

	@Override
	public boolean isCredentialsNonExpired() {//인증정보 만료 여부 : enabled =2
		return member.getEnableWhether()!=2;
	}

	@Override
	public boolean isEnabled() {// 탈퇴 혹은 삭제 : enabled = 0
		return member.getEnableWhether()!=0;
	}
	
	public MemberVO getMemberVO() {
		return this.member;
	}

}





