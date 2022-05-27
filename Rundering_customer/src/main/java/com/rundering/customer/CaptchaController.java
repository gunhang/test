package com.rundering.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rundering.util.CaptchaUtil;

import nl.captcha.Captcha; 

@Controller
public class CaptchaController {
	
	@RequestMapping("/captcha") 
	public String captcha() {
		return "captcha"; 
	}
	
	@RequestMapping("/captchaImg") 
	@ResponseBody
	public void captchaImg(HttpServletRequest request, HttpServletResponse response) throws Exception{
		new CaptchaUtil().captcaImg(request, response);
	}
	
	@RequestMapping("/captchaAudio")
	@ResponseBody 
	public void captchaAudio(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME); 
		String getAnswer = captcha.getAnswer(); 
		new CaptchaUtil().captchaAudio(request, response, getAnswer);
	}
	
	@RequestMapping("/chkAnswer") 
	@ResponseBody 
	public String chkAnswer(HttpServletRequest request, HttpServletResponse response) {
		String result = ""; 
		Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME); 
		String ans = request.getParameter("answer"); 

		if(ans!=null && !"".equals(ans)) { 
			if(captcha.isCorrect(ans)) { 
				request.getSession().removeAttribute(Captcha.NAME); 
				result = "200";
				}else { 
					result = "300"; 
				} 
			} 
		return result;
	}




	
}
