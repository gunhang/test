package com.rundering.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rundering.dao.ComCodeDAO;
import com.rundering.dao.NotificationDAO;
import com.rundering.dto.NotificationVO;
import com.rundering.util.ComCodeUtil;

public class NotificationServiceImpl implements NotificationService{
	
	private ComCodeDAO comCodeDAO;
	public void setComCodeDAO(ComCodeDAO comCodeDAO) {
		this.comCodeDAO = comCodeDAO;
	}
	
	private NotificationDAO notificationDAO;
	public void setNotificationDAO(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}
	
	@Override
	public Map<String, Object> getNotification(String employeeId) throws Exception {
		ComCodeUtil comCodeUtil =new ComCodeUtil();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String,String> notificationTypeMap = new HashMap<String, String>();
		comCodeUtil.getCodeMap("NOTIFICATION_TYPE", notificationTypeMap, comCodeDAO);
		
		List<NotificationVO> notificationList = notificationDAO.selectNotificationListByEmployeeId(employeeId);
		
		dataMap.put("notificationList", notificationList);
		dataMap.put("notificationTypeMap", notificationTypeMap);
		
		return dataMap;
	}

	@Override
	public void checkNotification(String ntcnId) throws Exception {
		notificationDAO.updateCheckedNotification(ntcnId);
	}

	@Override
	public void checkAllNotification(String employeeId) throws Exception {
		notificationDAO.updateCheckedAllNotification(employeeId);
	}

}
