package com.rundering.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rundering.util.PhoneResDTO.SendSmsResponse;

public class SensSms {
	
	public String makeSignature(String time)
			throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		String space = " "; // one space
		String newLine = "\n"; // new line
		String method = "POST"; // method
		String url = "/sms/v2/services/" + ApplicationNaverSENS.getServiceid() + "/messages"; // url (include query
		String timestamp = time.toString(); // current timestamp (epoch)
		String accessKey = ApplicationNaverSENS.getAccesskey();
		String secretKey = ApplicationNaverSENS.getSecretkey();
		String message = new StringBuilder()
				.append(method)
				.append(space)
				.append(url)
				.append(newLine)
				.append(timestamp)
				.append(newLine)
				.append(accessKey)
				.toString();
		
		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		String encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
		return encodeBase64String;

	}
	
	public SendSmsResponse sendSMS(String phoneNumber, String content) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		  	String time = Long.toString(System.currentTimeMillis());
	        // 메세지 생성
	        List<PhoneResDTO.SmsRequest.SmsMessage> smsMessageList = new ArrayList<>();
	        PhoneResDTO.SmsRequest.SmsMessage smsMessage = new PhoneResDTO.SmsRequest.SmsMessage(phoneNumber, content);
	        smsMessageList.add(smsMessage);

	        PhoneResDTO.SmsRequest smsRequest = new PhoneResDTO.SmsRequest();
	        smsRequest.setMessages(smsMessageList);

	        // json 형태로 변환
	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonBody = objectMapper.writeValueAsString(smsRequest);

	        // 헤더 설정값 세팅
	        HttpHeaders headers = new HttpHeaders();
	        Charset utf8 = Charset.forName("UTF-8");
	        MediaType mediaType = new MediaType("application", "json", utf8);
	        headers.setContentType(mediaType);
	        headers.set("x-ncp-apigw-timestamp", time);
	        headers.set("x-ncp-iam-access-key", ApplicationNaverSENS.getAccesskey());

	        // signature 서명
	        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

	        HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);
	        RestTemplate restTemplate = new RestTemplate();
	        SendSmsResponse smsResponse = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + ApplicationNaverSENS.getServiceid() + "/messages"), body, SendSmsResponse.class);

	        return smsResponse;
	}
	
}