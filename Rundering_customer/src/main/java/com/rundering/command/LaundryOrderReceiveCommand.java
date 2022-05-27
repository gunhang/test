package com.rundering.command;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rundering.dto.AttachVO;
import com.rundering.dto.LaundryOrderDetailVO;
import com.rundering.dto.LaundryOrderVO;
import com.rundering.dto.MemberAddressVO;
import com.rundering.util.MakeFileName;

public class LaundryOrderReceiveCommand {
	
	//세탁주문테이블 컬럼
	private String addressNo;           //주소번호
	private String setDefaultAddr="N";      //기본주소지로 설정
	private String zip="";                 //우편번호
	private String add1="";                //주소
	private String add2="";                //상세주소
	private String contactNumber;       //배송연락처
	private String pickupRequestDate;   //수거요청일
	private String requestDetails;      //요청사항
	private String totalPrice="0";     		 //총가격
	private String paymentNo="";      		//결제번호
	
	//세탁주문상세테이블 컬럼
	private String[] laundryItemsCode;  //세탁품목코드
	
	//첨부파일 컬럼
	private String[] saveFileNm;	//저장파일명
	
	
	public String getSetDefaultAddr() {
		return setDefaultAddr;
	}
	public void setSetDefaultAddr(String setDefaultAddr) {
		this.setDefaultAddr = setDefaultAddr;
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getPickupRequestDate() {
		return pickupRequestDate;
	}
	public void setPickupRequestDate(String pickupRequestDate) {
		this.pickupRequestDate = pickupRequestDate;
	}
	public String getAddressNo() {
		return addressNo;
	}
	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}
	public String getRequestDetails() {
		return requestDetails;
	}
	public void setRequestDetails(String requestDetails) {
		this.requestDetails = requestDetails;
	}
	public String[] getLaundryItemsCode() {
		return laundryItemsCode;
	}
	public void setLaundryItemsCode(String[] laundryItemsCode) {
		this.laundryItemsCode = laundryItemsCode;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	
	public String[] getSaveFileNm() {
		return saveFileNm;
	}
	public void setSaveFileNm(String[] saveFileNm) {
		this.saveFileNm = saveFileNm;
	}
	public LaundryOrderVO toLaundryOrderVO() throws ParseException {
		LaundryOrderVO laundryOrder = new LaundryOrderVO();
		laundryOrder.setContactNumber(this.contactNumber);

		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		Date toDate = fm.parse(this.pickupRequestDate);
		laundryOrder.setPickupRequestDate(toDate);
		
		laundryOrder.setZip(this.zip);  
		laundryOrder.setAdd1(this.add1);
		laundryOrder.setAdd2(this.add2);
		laundryOrder.setRequestDetails(this.requestDetails);
		laundryOrder.setTotalPrice(Integer.parseInt(this.totalPrice));
		laundryOrder.setPaymentNo(this.paymentNo);
		
		return laundryOrder;
	}
	
	public MemberAddressVO toMemberAddressVO() throws ParseException{
		MemberAddressVO memberAddress = new MemberAddressVO();
		memberAddress.setZip(this.zip);
		memberAddress.setAdd1(this.add1);
		memberAddress.setAdd2(this.add2);
		memberAddress.setDefaultYn(this.setDefaultAddr);
		return memberAddress;
	}
	
	public List<LaundryOrderDetailVO> toLaundryOrderDetailVOList() throws Exception{
		
		List<LaundryOrderDetailVO> laundryOrderDetailList = new ArrayList<LaundryOrderDetailVO>();
		if (this.laundryItemsCode != null && this.laundryItemsCode.length > 0) {
			
			if(this.laundryItemsCode[0].length() < 6 && this.laundryItemsCode.length == 2) {
				LaundryOrderDetailVO vo = new LaundryOrderDetailVO();
				vo.setLaundryItemsCode(this.laundryItemsCode[0]);
				vo.setQuantity(Integer.parseInt(this.laundryItemsCode[1]));
				laundryOrderDetailList.add(vo);
			}else {
				for (String string : this.laundryItemsCode) {
					LaundryOrderDetailVO vo = new LaundryOrderDetailVO();
					String[] split = string.split(",");
					vo.setLaundryItemsCode(split[0]);
					vo.setQuantity(Integer.parseInt(split[1]));
					laundryOrderDetailList.add(vo);
				}
			}
		}
		return laundryOrderDetailList;
	}
	
	public void setAddress(MemberAddressVO memberAddress) {
		this.zip = memberAddress.getZip();
		this.add1 = memberAddress.getAdd1();
		if(memberAddress.getAdd2() != null) {
			this.add2 =  memberAddress.getAdd2();
		}
	}
	
	public List<AttachVO> toAttachVOList(String savePath) {
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		if (this.saveFileNm != null && this.saveFileNm.length > 0) {
			int cnt = 1;
			for (String saveFile : saveFileNm) {

				String fileNm = MakeFileName.parseFileNameFromUUID(saveFile, "\\$\\$");
				File file = new File(savePath, saveFile);
				file.length();

				// DB에 저장할 attach에 file 내용 추가.
				AttachVO attach = new AttachVO();
				attach.setFileNm(fileNm);
				attach.setSaveFileNm(saveFile);
				attach.setFilePath(savePath);
				attach.setFileSize(file.length()/1024);
				attach.setBizType("세탁주문");
				attach.setFileContType(saveFile.substring(saveFile.lastIndexOf(".") + 1).toUpperCase());
				attach.setAtchFileSeq(cnt);
				cnt++;
				
				attachList.add(attach);
			}
		}
		return attachList;
	}
	
}
