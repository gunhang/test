package com.rundering.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.rundering.dto.AttachVO;
import com.rundering.service.AttachService;


public class FileUtil {
	//
	//비동기 파일 저장후 이름 가져오기 orginalfileName 는 원본 파일명 fileName 저장된 파일명
	public Map<String, String> saveFile(MultipartFile multiFile,String savePath){
		Map<String, String> fileMap = new HashMap<String, String>();
		if (multiFile != null) {
				String fileName = MakeFileName.toUUIDFileName(multiFile.getOriginalFilename(), "$$");
				File target = new File(savePath, fileName);
				target.mkdirs();
				try {
					multiFile.transferTo(target);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fileMap.put("orginalName", multiFile.getOriginalFilename());
				fileMap.put("fileName", fileName);
		}
		return fileMap;
	}
	
	//파일 이름 파라미터 fileNames, filePath 파일 경로 , biztype 업무구분 , regUser 작성자
	public List<AttachVO> getAttachVOList(String[] fileNames,String filePath,String bizType,AttachService attachService){
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		int seq = 1;
		if(fileNames!=null) {
			for (String  fileName : fileNames) {
				if(fileName!=null) {
					File target = new File(filePath, fileName);
					AttachVO attach =  new AttachVO();
					attach.setAtchFileSeq(seq);
					attach.setFilePath(filePath);
					attach.setBizType(bizType);
					attach.setFileNm(MakeFileName.parseFileNameFromUUID(fileName, "\\$\\$") );
					attach.setSaveFileNm(fileName);
					attach.setFileContType(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());
					attach.setFileSize(target.length()/1024);
					attachList.add(attach);
				}
			}
		}
		return attachList;
	}
		
	// 이미지 불러오기 위한 바이트 데이터 스크립트 or html 태그 src or url 속성에서 get 형식으로 불러와서 배열을 내보내주면됨
	public ResponseEntity<List<byte[]>> getPicture(String fileNo,AttachService attachService) throws Exception{
		// 내일 이미지 불러오는거 좀더 쉽게 만들어 주도록 하죠
		List<AttachVO> attachList = null;
		ResponseEntity<List<byte[]>> entity = null;
		attachList = attachService.getAttachVOList(fileNo);
		List<byte[]> byteArrayList = new ArrayList<byte[]>();
		for (AttachVO attach : attachList) {
			InputStream in = null;
			String imgPath =attach.getFilePath();
			try {
				in = new FileInputStream(new File(imgPath, attach.getSaveFileNm()));
				byte[] fileByte=IOUtils.toByteArray(in);
				byteArrayList.add(fileByte);
				System.out.println(fileByte);
			}catch (Exception e) {
				e.printStackTrace();
			} 
			finally {
				in.close();
			}
		}
		entity = new ResponseEntity<List<byte[]>>(byteArrayList, HttpStatus.CREATED);
		return entity;
	}
	
	// 이미지 불러오기 위한 바이트 데이터 스크립트 or html 태그 src or url 속성에서 get 형식으로 불러와서 배열을 내보내주면됨
	public ResponseEntity<List<byte[]>> getPicture(AttachVO atch,AttachService attachService) throws Exception{
		// 내일 이미지 불러오는거 좀더 쉽게 만들어 주도록 하죠
		List<AttachVO> attachList = null;
		ResponseEntity<List<byte[]>> entity = null;
		attachList = attachService.selectAttachVOByFileNoAndSeq2(atch);
		List<byte[]> byteArrayList = new ArrayList<byte[]>();
		for (AttachVO attach : attachList) {
			InputStream in = null;
			String imgPath =attach.getFilePath();
			try {
				in = new FileInputStream(new File(imgPath, attach.getSaveFileNm()));
				byte[] fileByte=IOUtils.toByteArray(in);
				byteArrayList.add(fileByte);
				System.out.println(fileByte);
			}catch (Exception e) {
				e.printStackTrace();
			} 
			finally {
				in.close();
			}
		}
		entity = new ResponseEntity<List<byte[]>>(byteArrayList, HttpStatus.CREATED);
		return entity;
	}
	
	//파일 삭제
	public void remove(String filePath,String fileName) {
		File imageFile = new File(filePath, fileName);
		if (imageFile.exists()) {
			imageFile.delete();
		}
	}
	public void dbRemove(String fileNo,AttachService attachService) throws Exception{
		attachService.fileRemove(fileNo);
	}

}
