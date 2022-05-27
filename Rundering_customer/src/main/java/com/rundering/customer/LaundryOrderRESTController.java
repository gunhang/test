package com.rundering.customer;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rundering.dao.PaymentDAO;
import com.rundering.dto.PaymentVO;
import com.rundering.util.FormatUtil;
import com.rundering.util.MakeFileName;
import com.rundering.util.PhoneResDTO.SendSmsResponse;
import com.rundering.util.SensSms;

@RestController
@RequestMapping("/order")
public class LaundryOrderRESTController {
	
	
	@Autowired
	private SensSms sensSms;
	@Autowired
	private PaymentDAO paymentDAO;
	@Resource(name = "laundryorderpicturePath")
	private String picturePath;
	
	//문자인증
	@RequestMapping(value = "/certifyPhoneNum", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> certifyPhoneNum(String phoneNumber) throws Exception {
		
		ResponseEntity<Map<String, Object>> result = null;
		Map<String, Object> dataMap = new HashMap<>();
		
		String randomNum = String.format("[%06d]", (int)(Math.random() * 999999));
		
		try {
			SendSmsResponse sendSmsResponse = sensSms.sendSMS(phoneNumber, randomNum+" Rundering에서 보낸 인증번호 입니다.");
			if(sendSmsResponse.getStatusCode().equals("202") || sendSmsResponse.getStatusCode().equals("200")) {
				dataMap.put("randomNum", FormatUtil.RemoveSpecialChar(randomNum));
				result = new ResponseEntity<Map<String, Object>>(dataMap,HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return result;
	}
	
	//결제
	@RequestMapping(value = "/payment", method = RequestMethod.POST, consumes="application/json;")
	public ResponseEntity<Map<String, Object>> payment(@RequestBody PaymentVO payment) throws Exception {
		
		ResponseEntity<Map<String, Object>> result = null;
		Map<String, Object> dataMap = new HashMap<>();
		
		try {
			paymentDAO.insertPayment(payment);
			String insertResult = "success";
			dataMap.put("insertResult",insertResult);
			result = new ResponseEntity<Map<String, Object>>(dataMap,HttpStatus.OK);
		} catch (SQLException e) {
			result = new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return result;
	}
	

	private String savePicture(MultipartFile multi) throws Exception {
		String fileName = null;

		/* 파일유무확인 */
		if (!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {

			/* 파일저장폴더설정 */
			String uploadPath = picturePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
			File storeFile = new File(uploadPath, fileName);

			storeFile.mkdirs();

			// local HDD 에 저장.
			multi.transferTo(storeFile);

		}
		return fileName;
	}

	@RequestMapping(value = "/picture", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> picture(@RequestParam("pictureFile") MultipartFile multi)
			throws Exception {

		ResponseEntity<String> entity = null;

		String result = "";
		HttpStatus status = null;

		/* 파일저장확인 */
		if ((result = savePicture(multi)) == null) {
			result = "업로드 실패";
			status = HttpStatus.BAD_REQUEST;
		} else {
			status = HttpStatus.OK;
		}
		entity = new ResponseEntity<String>(result, status);

		return entity;

	}
	@RequestMapping(value = "/deletePicture", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> deletePicture(String deleteFileName) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		String result = "";
		HttpStatus status = null;
		
		File imageFile = new File(picturePath, deleteFileName);
		if (!deleteFileName.isEmpty() && imageFile.exists()) {
			imageFile.delete();
			result = deleteFileName+"파일 삭제 성공";
			status = HttpStatus.OK;
		}else {
			result = "파일이 존재하지 않습니다.";
			status = HttpStatus.BAD_REQUEST;
		}
		entity = new ResponseEntity<String>(result, status);
		
		return entity;
		
	}

	// 사진파일띄울거면 수정
//	@RequestMapping(value = "/getPicture", produces = "text/plain;charset=utf-8")
//	public ResponseEntity<byte[]> getPicture(String id) throws Exception {
//
//		String picture = memberService.getMember(id).getPicture();
//
//		InputStream in = null;
//		ResponseEntity<byte[]> entity = null;
//		String imgPath = this.picturePath;
//
//		try {
//			in = new FileInputStream(new File(imgPath, picture));
//
//			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.CREATED);
//
//		} finally {
//			in.close();
//		}
//		return entity;
//	}
	
		
}
