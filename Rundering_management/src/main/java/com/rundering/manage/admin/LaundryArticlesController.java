package com.rundering.manage.admin;

import java.io.File;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rundering.command.Criteria;
import com.rundering.dto.AttachVO;
import com.rundering.dto.LaundryArticlesVO;
import com.rundering.service.LaundryArticlesService;
import com.rundering.util.MakeFileName;

@Controller
@RequestMapping("/admin")
public class LaundryArticlesController {

	@Resource(name = "picturePath")
	private String picturePath;

	@Resource(name = "laundryArticlesService")
	private LaundryArticlesService laundryArticlesService;

	@RequestMapping("/ordergoods/list")
	public ModelAndView OrderGoodsList(Criteria cri, ModelAndView mnv) throws SQLException {
		String url = "admin/ordergoods/ordergoods_list";
		cri.setPerPageNum(4);
		Map<String, Object> dataMap = laundryArticlesService.getLaundryArticles(cri);

		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		return mnv;
	}

	@RequestMapping("/ordergoods/registForm")
	public String OrderGoodsRegist() {
		String url = "admin/ordergoods/ordergoods_regist";

		return url;
	}

	@RequestMapping(value = "/ordergoods/regist", method = RequestMethod.POST)
	public String regist(LaundryArticlesVO laundryArticles,AttachVO attach, String fileName, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/admin/ordergoods/list";
		
		
		File file = new File(picturePath + fileName);
		String orginalFileName = MakeFileName.parseFileNameFromUUID(fileName, "\\$\\$");
		long fileSize = file.length() / 1024;
		String type = fileName.substring(fileName.lastIndexOf('.') + 1);
		attach.setFileContType(type);
		attach.setFileNm(orginalFileName);
		attach.setSaveFileNm(fileName);
		attach.setFileSize(fileSize);
		attach.setFilePath(picturePath);

		laundryArticlesService.regist(laundryArticles, attach);
		rttr.addFlashAttribute("from", "regist");

		return url;
	}

	@RequestMapping("/ordergoods/detail")
	public ModelAndView detail(String articlesCode, ModelAndView mnv) throws SQLException {
		String url = "admin/ordergoods/ordergoods_detail";
		
		LaundryArticlesVO laundryArticles = laundryArticlesService.getLaundryArticles(articlesCode);
		
		mnv.addObject("laundryArticles", laundryArticles);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping("/ordergoods/modifyForm")
	public ModelAndView ModifyForm(String articlesCode ,AttachVO attach, ModelAndView mnv) throws SQLException {
		String url = "admin/ordergoods/ordergoods_modify";

		LaundryArticlesVO laundryArticles = laundryArticlesService.getLaundryArticles(articlesCode);

//		String picture = MakeFileName.parseFileNameFromUUID(laundryArticles.getPicture(),"\\$\\$");
//		laundryArticles.setPicture(picture);

		mnv.addObject("laundryArticles", laundryArticles);
		mnv.setViewName(url);

		return mnv;
	}

	
	@RequestMapping(value="/ordergoods/modify", method=RequestMethod.POST)
	public String modifyPost(LaundryArticlesVO orderGoods,HttpServletRequest request, RedirectAttributes rttr) throws Exception{
		String url = "redirect:/admin/ordergoods/detail";
		
		// 신규 파일 변경 및 기존 파일 삭제 
		String oldPicture =laundryArticlesService.getLaundryArticles(orderGoods.getArticlesCode()).getPicture();
		if(orderGoods.getUploadPicture()!=null &&!orderGoods.getUploadPicture().isEmpty()){ 
			String fileName = savePicture(oldPicture, orderGoods.getPictureFile());
			orderGoods.setPicture(fileName); 
		}else { 
			orderGoods.setPicture(oldPicture);
		}
		
		laundryArticlesService.modify(orderGoods);
		
		rttr.addFlashAttribute("from","modify");
		rttr.addAttribute("articlesCode",orderGoods.getArticlesCode());

		return url; 
	}

	@RequestMapping(value = "/ordergoods/remove", method = RequestMethod.POST)
	public String remove(String articlesCode, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/admin/ordergoods/detail";
		laundryArticlesService.remove(articlesCode);

		rttr.addAttribute("articlesCode", articlesCode);
		rttr.addFlashAttribute("from", "remove");
		return url;
	}

	private String savePicture(String oldPicture, MultipartFile multi) throws Exception {
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

			if (oldPicture != null && !oldPicture.isEmpty()) {
				File oldFile = new File(uploadPath, oldPicture);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
		}
		return fileName;
	}
}
