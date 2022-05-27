package com.rundering.util;

import java.util.List;

public class PhoneResDTO {
	
	public static class SmsRequest {
		private String type = "SMS";
		private String contentType = "COMM";
		private String countryCode = "82";
		private String from = "01027293071";
		private String content = "회원가입 휴대폰 인증 코드입니다.";
		private List<SmsMessage> messages;

		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getContentType() {
			return contentType;
		}
		public void setContentType(String contentType) {
			this.contentType = contentType;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public List<SmsMessage> getMessages() {
			return messages;
		}
		public void setMessages(List<SmsMessage> messages) {
			this.messages = messages;
		}
		
		public static class SmsMessage {
			private String to;
			private String content;
			public SmsMessage(String to, String content) {
				super();
				this.to = to;
				this.content = content;
			}
			public String getTo() {
				return to;
			}
			public void setTo(String to) {
				this.to = to;
			}
			public String getContent() {
				return content;
			}
			public void setContent(String content) {
				this.content = content;
			}
		}
	}
	
	
	public static class SendSmsResponse { 
		private String statusCode;
		private String statusName;
		private String requestId;
		private String requestTime;
		public String getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}
		public String getStatusName() {
			return statusName;
		}
		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}
		public String getRequestId() {
			return requestId;
		}
		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}
		public String getRequestTime() {
			return requestTime;
		}
		public void setRequestTime(String requestTime) {
			this.requestTime = requestTime;
		}
		public SendSmsResponse() {
		}
		
		public SendSmsResponse(String statusCode, String statusName, String requestId, String requestTime) {
			super();
			this.statusCode = statusCode;
			this.statusName = statusName;
			this.requestId = requestId;
			this.requestTime = requestTime;
		} 
		
	}

	
}