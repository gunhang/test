package com.rundering.command;

import java.util.ArrayList;
import java.util.List;

import com.rundering.dto.BranchVO;
import com.rundering.dto.LaundryFixturesVO;
import com.rundering.dto.MemberVO;

public class EnrollmentRegistCommand {
	String branchCode;
	String name;
	String phone;
	String email;
	String branchName;
	String branchContact;
	String toparea;
	String area;
	String zip;
	String add1;
	String add2;
	int branchLndrpcrymslmcoqy=0;
	int A001=0;
	int A002=0;
	int A003=0;
	
	
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchContact() {
		return branchContact;
	}
	public void setBranchContact(String branchContact) {
		this.branchContact = branchContact;
	}
	public String getToparea() {
		return toparea;
	}
	public void setToparea(String toparea) {
		this.toparea = toparea;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public int getBranchLndrpcrymslmcoqy() {
		return branchLndrpcrymslmcoqy;
	}
	public void setBranchLndrpcrymslmcoqy(int branchLndrpcrymslmcoqy) {
		this.branchLndrpcrymslmcoqy = branchLndrpcrymslmcoqy;
	}
	public int getA001() {
		return A001;
	}
	public void setA001(int a001) {
		A001 = a001;
	}
	public int getA002() {
		return A002;
	}
	public void setA002(int a002) {
		A002 = a002;
	}
	public int getA003() {
		return A003;
	}
	public void setA003(int a003) {
		A003 = a003;
	}
	
	public MemberVO getMemberVO () {
		MemberVO member =new MemberVO();
		member.setName(name);
		member.setPhone(phone);
		member.setEmail(email);
		return member;
	}
	public BranchVO getBranchVO () {
		BranchVO branch = new BranchVO();
		branch.setBranchCode(branchCode);			
		branch.setBranchName(branchName);
		branch.setBranchContact(branchContact);
		branch.setArea(area);
		branch.setToparea(toparea);
		branch.setZip(zip);
		branch.setAdd1(add1);
		branch.setAdd2(add2);
		branch.setBranchLndrpcrymslmcoqy(branchLndrpcrymslmcoqy);
		return branch;
	}
	
	
	public List<LaundryFixturesVO> getLaundryFixturesVOList() {
		List<LaundryFixturesVO> laundryFixturesList= new ArrayList<LaundryFixturesVO>();
		for(int i = 1 ; i<=A001; i++) {
			LaundryFixturesVO laundryFixtures = new LaundryFixturesVO();
			String fixturesCode=null;
			if(A001<10) {
				fixturesCode="A"+branchCode+"0"+i+"01";
			}
			if(A001>=10) {
				fixturesCode="A"+branchCode+""+i+"01";
			}
			String fixturesName = "세탁기"+i+"호기";
			laundryFixtures.setBranchCode(branchCode);
			laundryFixtures.setFixturesName(fixturesName);
			laundryFixtures.setFixturesCode(fixturesCode);
			laundryFixtures.setArticlesCode("A001");
			laundryFixturesList.add(laundryFixtures);
		}
		for(int i = 1 ; i<=A002; i++) {
			LaundryFixturesVO laundryFixtures = new LaundryFixturesVO();
			String fixturesCode=null;
			if(A001<10) {
				fixturesCode="A"+branchCode+"0"+i+"02";
			}
			if(A001>=10) {
				fixturesCode="A"+branchCode+""+i+"02";
			}
			String fixturesName = "건조기"+i+"호기";
			laundryFixtures.setBranchCode(branchCode);
			laundryFixtures.setFixturesName(fixturesName);
			laundryFixtures.setFixturesCode(fixturesCode);
			laundryFixtures.setArticlesCode("A002");
			laundryFixturesList.add(laundryFixtures);
		}
		for(int i = 1 ; i<=A003; i++) {
			LaundryFixturesVO laundryFixtures = new LaundryFixturesVO();
			String fixturesCode=null;
			if(A001<10) {
				fixturesCode="A"+branchCode+"0"+i+"03";
			}
			if(A001>=10) {
				fixturesCode="A"+branchCode+""+i+"03";
			}
			String fixturesName = "에어컨"+i+"호기";
			laundryFixtures.setBranchCode(branchCode);
			laundryFixtures.setFixturesName(fixturesName);
			laundryFixtures.setFixturesCode(fixturesCode);
			laundryFixtures.setArticlesCode("A003");
			laundryFixturesList.add(laundryFixtures);
		}
		
		
		
		return laundryFixturesList;
	}
	
	
}
