package com.rundering.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rundering.captcha.SetTextProducer;

import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.NumbersAnswerProducer;

public class CaptchaUtil {


    public void captcaImg(HttpServletRequest request, HttpServletResponse response) {
        Captcha captcha = new Captcha.Builder(260, 60)
                .addText(new NumbersAnswerProducer(6))
                .addNoise().addNoise().addNoise()
                .addBackground(new GradiatedBackgroundProducer())
                .addBorder()
                .build();



        request.getSession().setAttribute(Captcha.NAME, captcha);
        CaptchaServletUtil.writeImage(response, captcha.getImage());

    }

    public void captchaAudio(HttpServletRequest request, HttpServletResponse response, String answer) {
        Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
        String getAnswer = answer;

        if(getAnswer == null || getAnswer.equals("")) getAnswer = captcha.getAnswer();

        AudioCaptcha ac = new AudioCaptcha.Builder()
                .addAnswer(new SetTextProducer(getAnswer))
                .addVoice()
                .addNoise()
                .build();


        CaptchaServletUtil.writeAudio(response, ac.getChallenge());

//        request.getSession().setAttribute("captcha", ac.getAnswer());
    }


}
