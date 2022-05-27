package com.rundering.dto;

public class UserAuthVO {
	private String authGrpCode;  //권한그룹코드
	private String memberno;     //회원고유번호
	
	public String getAuthGrpCode() {
		return authGrpCode;
	}
	public void setAuthGrpCode(String authGrpCode) {
		this.authGrpCode = authGrpCode;
	}
	public String getMemberno() {
		return memberno;
	}
	public void setMemberno(String memberno) {
		this.memberno = memberno;
	}
	
	
	
}
