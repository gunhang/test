package com.rundering.dto;

public class MenuVO {
	private String menuCode;      //메뉴ID
	private String upperMenuCode; //상위메뉴ID
	private String menuSe;        //메뉴구분
	private int menuOrd;          //메뉴순서
	private String menuName;      //메뉴명
	private String menuDesc;      //메뉴설명
	private String menuUrl;       //메뉴URL
	private String useYn;         //사용여부
	
	
	public MenuVO(String menuCode, String upperMenuCode, String menuSe, int menuOrd, String menuName, String menuDesc,
			String menuUrl, String useYn) {
		super();
		this.menuCode = menuCode;
		this.upperMenuCode = upperMenuCode;
		this.menuSe = menuSe;
		this.menuOrd = menuOrd;
		this.menuName = menuName;
		this.menuDesc = menuDesc;
		this.menuUrl = menuUrl;
		this.useYn = useYn;
	}
	
	
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getUpperMenuCode() {
		return upperMenuCode;
	}
	public void setUpperMenuCode(String upperMenuCode) {
		this.upperMenuCode = upperMenuCode;
	}
	public String getMenuSe() {
		return menuSe;
	}
	public void setMenuSe(String menuSe) {
		this.menuSe = menuSe;
	}
	public int getMenuOrd() {
		return menuOrd;
	}
	public void setMenuOrd(int menuOrd) {
		this.menuOrd = menuOrd;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
}
