package com.rundering.dto;

public class ComCodeVO {
	private String comCodeGrp; 	//공통코드그룹
	private String comCode; 	//공통코드
	private String comCodeNm; 	//공통코드명
	private String comCodeDesc; //공통코드설명
	private String useYn; 		//사용여부
	private String upperComCode;//상위공통코드
	
	
	public ComCodeVO() {
		super();
	}


	public String getComCodeGrp() {
		return comCodeGrp;
	}


	public void setComCodeGrp(String comCodeGrp) {
		this.comCodeGrp = comCodeGrp;
	}


	public String getComCode() {
		return comCode;
	}


	public void setComCode(String comCode) {
		this.comCode = comCode;
	}


	public String getComCodeNm() {
		return comCodeNm;
	}


	public void setComCodeNm(String comCodeNm) {
		this.comCodeNm = comCodeNm;
	}


	public String getComCodeDesc() {
		return comCodeDesc;
	}


	public void setComCodeDesc(String comCodeDesc) {
		this.comCodeDesc = comCodeDesc;
	}


	public String getUseYn() {
		return useYn;
	}


	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}


	public String getUpperComCode() {
		return upperComCode;
	}


	public void setUpperComCode(String upperComCode) {
		this.upperComCode = upperComCode;
	}
	
	
	
}
