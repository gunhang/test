package com.rundering.service;

import java.util.Map;

public interface NotificationService {
	
	//사원번호로 안읽은 알림 가져오기
	public Map<String, Object> getNotification(String employeeId) throws Exception;
	//알림 읽음 처리
	public void checkNotification(String ntcnId) throws Exception;
	//모든 알림 읽음 처리
	public void checkAllNotification(String employeeId) throws Exception;
}
