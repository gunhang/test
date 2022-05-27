package com.rundering.customer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.util.MakeFileName;
import com.rundering.dto.AttachVO;
import com.rundering.dto.BranchApplicationVO;
import com.rundering.service.AttachService;
import com.rundering.service.BranchApplicationService;

@RequestMapping("/branchapplication")
@Controller
public class BranchApplicationController {

	@Resource(name = "filePath")
	private String filePath;

	@Resource(name = "attachService")
	private AttachService attachService;

	@Resource(name = "branchApplicationService")
	private BranchApplicationService branchApplicationService;

	@RequestMapping("/regist")
	public void branchApplication() {
	}

	private Map<String, String> savePicture(MultipartFile multi) throws Exception {
		String fileName = null;
		String fileOrginalName = null;
		Map<String, String> dataMap = new HashMap<String, String>();
		/* 파일유무확인 */
		if (!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {
			/* 파일저장폴더설정 */
			String uploadPath = filePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
			fileOrginalName = multi.getOriginalFilename();
			File storeFile = new File(uploadPath, fileName);
			dataMap.put("fileName", fileName);
			dataMap.put("fileOrginalName", fileOrginalName);

			storeFile.mkdirs();

			// local HDD 에 저장.
			multi.transferTo(storeFile);

		}
		return dataMap;
	}

	@RequestMapping(value = "/picture", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public ResponseEntity<Map<String, String>> picture(@RequestParam("pictureFile") MultipartFile multi)
			throws Exception {
		ResponseEntity<Map<String, String>> entity = null;
		Map<String, String> result = null;
		HttpStatus status = null;

		/* 파일저장확인 */
		if ((result = savePicture(multi)) == null) {

			status = HttpStatus.BAD_REQUEST;
			result.put("result", "업로드 실패했습니다.");
		} else {
			status = HttpStatus.OK;
			result.put("result", "업로드 성공!!");
		}
		entity = new ResponseEntity<Map<String, String>>(result, status);

		return entity;
	}

	// 지점 신청 및 임대차 계약서 파일 업로드
	@RequestMapping(value = "/registform", method = RequestMethod.POST)
	public String registform(BranchApplicationVO bv, AttachVO attach) throws Exception {
		String url = "redirect:/home";

		String fileName = bv.getFileNm();

		File file = new File(filePath + fileName);
		String orginalFileName = MakeFileName.parseFileNameFromUUID(fileName, "\\$\\$");
		long fileSize = file.length() / 1024;
		String type = fileName.substring(fileName.lastIndexOf('.') + 1);
		attach.setFileContType(type);
		attach.setFileNm(orginalFileName);
		attach.setSaveFileNm(fileName);
		attach.setFileSize(fileSize);
		attach.setFilePath(filePath);

		branchApplicationService.branchApplicate(bv, attach);

		return url;
	}

	// 지점 신청 계약서 파일 수정
	@RequestMapping(value = "/modifyContractFile", method = RequestMethod.POST)
	public ResponseEntity<String> modifyContractFile(AttachVO attach) throws Exception {
		ResponseEntity<String> entity = null;

		File file = new File(filePath + attach.getSaveFileNm());
		String orginalFileName = MakeFileName.parseFileNameFromUUID(attach.getSaveFileNm(), "\\$\\$");
		long fileSize = file.length() / 1024;
		String type = attach.getSaveFileNm().substring(attach.getSaveFileNm().lastIndexOf('.') + 1);
		attach.setFileContType(type);
		attach.setFileNm(orginalFileName);
		attach.setFileSize(fileSize);
		attach.setFilePath(filePath);

		try {
			attachService.updateToContractFile(attach);
			entity = new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;

	}

	// 경로에 저장된 파일 삭제
	@RequestMapping(value = "/deletePicture", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> deletePicture(String deleteFileName) throws Exception {

		ResponseEntity<String> entity = null;

		String result = "";
		HttpStatus status = null;

		File imageFile = new File(filePath, deleteFileName);
		if (!deleteFileName.isEmpty() && imageFile.exists()) {
			imageFile.delete();
			result = deleteFileName + "파일 삭제 성공";
			status = HttpStatus.OK;
		} else {
			result = "파일이 존재하지 않습니다.";
			status = HttpStatus.BAD_REQUEST;
		}
		entity = new ResponseEntity<String>(result, status);

		return entity;
	}

	@RequestMapping("/my_branch_request")
	public ModelAndView myBranchRequest(ModelAndView mnv, BranchApplicationVO bv, AttachVO attach) throws Exception {
		String url = "branchapplication/my_branch_request";

		bv = branchApplicationService.getSelfAuthentification(bv);
		attach.setAtchFileNo(bv.getLeasecontractFile());

		List<AttachVO> avList = attachService.getAttachVOList(attach.getAtchFileNo());
		List<AttachVO> contractList = attachService.contract();

		mnv.addObject("bv", bv);
		mnv.addObject("avList", avList);
		mnv.addObject("contractList", contractList);
		mnv.setViewName(url);

		return mnv;
	}

	// 지점신청(심사 신청)
	@RequestMapping("/updateJudge")
	public ResponseEntity<String> updateJudge(BranchApplicationVO bv, AttachVO attach) throws Exception {
		ResponseEntity<String> entity = null;

		if (bv.getProgressStatusCode().equals("06")) {
			File file = new File(filePath + attach.getSaveFileNm());
			String orginalFileName = MakeFileName.parseFileNameFromUUID(attach.getSaveFileNm(), "\\$\\$");
			long fileSize = file.length() / 1024;
			String type = attach.getSaveFileNm().substring(attach.getSaveFileNm().lastIndexOf('.') + 1);
			attach.setFileContType(type);
			attach.setFileNm(orginalFileName);
			attach.setFileSize(fileSize);
			attach.setFilePath(filePath);
			attach.setBizType("수의계약서");
			attachService.insertContractFile(attach);
		}

		try {
			branchApplicationService.updateJudge(bv);

			entity = new ResponseEntity<String>("OK", HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}

	@RequestMapping("/self_authentification")
	public void selfAuthentification() {
	}

	// 파일다운로드
	@RequestMapping("/file/filedownload") 
	public void fileDownload(HttpServletRequest request,AttachVO attach ,HttpServletResponse response) throws Exception { 
		
		attach = attachService.getAttachForDownload(attach);
		
		String saveDir = attach.getFilePath(); 
		String fileName = attach.getSaveFileNm(); 
		File file = new File(saveDir + "/" + fileName); 
		FileInputStream fis = null; BufferedInputStream bis = null; 
		ServletOutputStream sos = null; 
		try { 
			fis = new FileInputStream(file); 
			bis = new BufferedInputStream(fis); 
			sos = response.getOutputStream(); 
			String reFilename = ""; 
			reFilename = URLEncoder.encode(attach.getFileNm(), "utf-8"); 
			reFilename = reFilename.replaceAll("\\+", "%20"); 

		
			response.setContentType("application/octet-stream;charset=utf-8"); 
			response.addHeader("Content-Disposition", "attachment;filename=\""+reFilename+"\""); 
			response.setContentLength((int)file.length()); 
			int read = 0; while((read = bis.read()) != -1) {sos.write(read);}
			
		}catch(IOException e) { 
			e.printStackTrace(); }finally { 
				try { 
					sos.close(); bis.close(); 
				}catch (IOException e) { 
					e.printStackTrace();
				} 
		} 
	}


	// 인증 후 지점 신청 확인
	@RequestMapping("/self_authentification/comfirm")
	@ResponseBody
	public ResponseEntity<String> selfAuthentificationCom(BranchApplicationVO bv) throws Exception {
		ResponseEntity<String> entity = null;

		try {
			bv = branchApplicationService.getSelfAuthentification(bv);

			if (bv.getPhone() == null || bv.getPhone().isEmpty()) {

				entity = new ResponseEntity<String>("NO", HttpStatus.OK);

			} else {
				entity = new ResponseEntity<String>("OK", HttpStatus.OK);

			}

		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}

	@RequestMapping("/storeApplication")
	public void selectStoreApplication() {
	}
}
