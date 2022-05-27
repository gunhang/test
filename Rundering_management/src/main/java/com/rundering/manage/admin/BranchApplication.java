package com.rundering.manage.admin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.command.Criteria;
import com.rundering.command.EnrollmentRegistCommand;
import com.rundering.dao.ComCodeDAO;
import com.rundering.dto.AttachVO;
import com.rundering.dto.BranchApplicationVO;
import com.rundering.dto.BranchVO;
import com.rundering.dto.EmployeesVO;
import com.rundering.dto.LaundryFixturesVO;
import com.rundering.dto.MemberVO;
import com.rundering.service.AttachService;
import com.rundering.service.BranchApplicationService;
import com.rundering.util.ComCodeUtil;

@RequestMapping("/admin/branchapplication")
@Controller
public class BranchApplication {

	@Autowired
	BranchApplicationService branchApplicationService;
	@Autowired
	ComCodeDAO comCodeDAO;
	@Autowired 
	AttachService attachService;
	
	@RequestMapping("/contract")
	public ModelAndView branchContract(ModelAndView mnv,Criteria cri) {
		cri.setPerPageNum(5);
		Map<String, Object> dataMap=null;
		try {
			dataMap=branchApplicationService.selectBranchApplicationList(cri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName("/admin/branchapplication/contract_evaluation");
		return mnv;
	}
	@RequestMapping(value = "/applicationData" ,method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<BranchApplicationVO> applicationData(String applicationNo){
		BranchApplicationVO branchApplication = null;
		ResponseEntity<BranchApplicationVO> resp = null;
		try {
			branchApplication = branchApplicationService.selectBranchApplication(applicationNo);
			resp =new ResponseEntity<BranchApplicationVO>(branchApplication, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}
	
	@RequestMapping(value = "/applicationAreaData" ,method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> applicationAreaData(String applicationNo){
		BranchApplicationVO branchApplication = null;
		ResponseEntity<Map<String, Object>> resp = null;
		ComCodeUtil comCodeUtil = new ComCodeUtil();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String,Object > areaCode =new HashMap<String, Object>();
		Map<String,Object > topAreaCode =new HashMap<String, Object>();
		
		try {
			branchApplication = branchApplicationService.selectBranchApplication(applicationNo);
			comCodeUtil.getCodeListMap("AREA",areaCode , comCodeDAO);
			comCodeUtil.getCodeListMap("TOPAREA",topAreaCode , comCodeDAO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataMap.put("branchApplication", branchApplication);
		dataMap.put("areaCode",areaCode);
		dataMap.put("topAreaCode",topAreaCode);
		
		
		resp =new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		
		return resp;
	}
	
	
	@RequestMapping(value = "/applicationAreaComplateData" ,method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> applicationAreaComplateData(String applicationNo){
		BranchApplicationVO branchApplication = null;
		ResponseEntity<Map<String, Object>> resp = null;
		BranchVO branch = null;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			branchApplication = branchApplicationService.selectBranchApplication(applicationNo);
			branch =branchApplicationService.selectBranch(branchApplication.getPhone());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataMap.put("branchApplication", branchApplication);
		dataMap.put("branch",branch);
		
		resp =new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		return resp;
	}
	
	
	@RequestMapping(value=  "/approvalreturnContentsRegist",method = RequestMethod.POST)
	public String approvalreturnContentsRegist(BranchApplicationVO brnachApplication,HttpSession session) {
		String url="redirect:/admin/branchapplication/contract";
		EmployeesVO emp= (EmployeesVO)session.getAttribute("loginEmployee");
		brnachApplication.setCharger(emp.getEmployeeId());
		try {
			branchApplicationService.updateRejectContent(brnachApplication);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}
	@RequestMapping(value=  "/requestOK",method = RequestMethod.POST)
	public String requestOK(BranchApplicationVO brnachApplication,HttpSession session) {
		String url="redirect:/admin/branchapplication/contract";
		EmployeesVO emp= (EmployeesVO)session.getAttribute("loginEmployee");
		brnachApplication.setCharger(emp.getEmployeeId());
		try {
			branchApplicationService.updateApproval(brnachApplication);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}
	@RequestMapping(value=  "/examineRegist",method = RequestMethod.POST)
	public String examineRegist(BranchApplicationVO branchApplication,HttpSession session, RedirectAttributes attr) {
		String url="redirect:/admin/branchapplication/contract";
		EmployeesVO emp= (EmployeesVO)session.getAttribute("loginEmployee");
		
		try {
			branchApplicationService.updateExamination(branchApplication,emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}
	@RequestMapping("boluntaryOK")
	public String boluntaryOK(BranchApplicationVO branchApplication) {
		String url="redirect:/admin/branchapplication/contract";
		try {
			branchApplicationService.updateVoluntaryCheck(branchApplication);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	@RequestMapping(value = "enrollmentRegist",method = RequestMethod.POST)
	public String enrollmentRegist(EnrollmentRegistCommand enrollmentRegistCommand,String applicationNo) throws Exception{
		String url="redirect:/admin/branchapplication/contract";
		String area=enrollmentRegistCommand.getArea();
		String branchCode= branchApplicationService.selectBranchCode(area);
		
		
		enrollmentRegistCommand.setBranchCode(branchCode);
		MemberVO member = enrollmentRegistCommand.getMemberVO();
	    BranchVO branch=enrollmentRegistCommand.getBranchVO();
	    List<LaundryFixturesVO> laundryFixturesList= enrollmentRegistCommand.getLaundryFixturesVOList();
	    branchApplicationService.enrollmentRegist(member, branch, laundryFixturesList, applicationNo);
	    
	    
		
		
		return url;
	}
	
	// 파일다운로드
		@RequestMapping("/file/filedownload") 
		public void fileDownload(HttpServletRequest request,AttachVO attach ,HttpServletResponse response) throws Exception { 
			
			attach = attachService.getDownloadFile(attach);
			
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
	@RequestMapping("/pdfview") 
	public void pdfView(HttpServletRequest request,AttachVO attach ,HttpServletResponse response) throws Exception { 
		
		attach = attachService.getDownloadFile(attach);
		
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

		
			response.setContentType("application/pdf;charset=utf-8"); 
			response.setContentLength((int)file.length()); 
			response.addHeader("Content-Disposition", "inline;filename=\""+reFilename+"\""); 

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
