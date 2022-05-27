package com.rundering.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rundering.dto.AttachVO;

public class GetAttachesByMultipartFileAdapter {
	
	public static List<AttachVO> save(List<MultipartFile> multiFiles, String savePath, String bizType)	throws Exception{
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		//저장 -> attachVO -> list.add
		if (multiFiles != null) {
			int cnt = 1;
			for (MultipartFile multi : multiFiles) {
				
				String fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
				File target = new File(savePath, fileName);
				String orginalFileName= MakeFileName.parseFileNameFromUUID(fileName, "\\$\\$"); 
				target.mkdirs();

				multi.transferTo(target);
				
				AttachVO attach = new AttachVO();
				
				attach.setFilePath(savePath);
				attach.setSaveFileNm(fileName);
				attach.setFileNm(orginalFileName);
				attach.setFileContType(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());
				attach.setFileSize(target.length()/1024);
				attach.setBizType(bizType);
				attach.setAtchFileSeq(cnt);
				cnt++;
				
				attachList.add(attach);
			}
		}
		return attachList;		

	}
}
