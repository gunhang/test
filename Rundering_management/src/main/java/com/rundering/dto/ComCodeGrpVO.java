package com.rundering.dto;

public class ComCodeGrpVO {
	private String comCodeGrp; 	//공통코드그룹
	private String comCodeGrpNm;//코드그룹명
	private String codeGrpDesc; //코드그룹설명
	private String useYn; 		//사용여부
	private String upperComCode;//상위공통코드
	
	
	public ComCodeGrpVO() {
		super();
	}


	public String getComCodeGrp() {
		return comCodeGrp;
	}


	public void setComCodeGrp(String comCodeGrp) {
		this.comCodeGrp = comCodeGrp;
	}


	public String getComCodeGrpNm() {
		return comCodeGrpNm;
	}


	public void setComCodeGrpNm(String comCodeGrpNm) {
		this.comCodeGrpNm = comCodeGrpNm;
	}


	public String getCodeGrpDesc() {
		return codeGrpDesc;
	}


	public void setCodeGrpDesc(String codeGrpDesc) {
		this.codeGrpDesc = codeGrpDesc;
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
