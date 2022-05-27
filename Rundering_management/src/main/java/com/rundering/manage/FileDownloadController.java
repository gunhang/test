package com.rundering.manage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rundering.dto.AttachVO;
import com.rundering.service.AttachService;

@Controller
public class FileDownloadController {
	
	@Resource(name = "attachService")
	private AttachService attachService;
	
		// 파일다운로드 - AttachVO의 atchFileNo, saveFileNm를 통해 파일 가져오기
		@RequestMapping("/file/filedownload")
		@ResponseBody
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
}
