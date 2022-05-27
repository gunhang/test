package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import com.rundering.dto.NotificationVO;

public interface NotificationDAO {
	
	// 시퀀스 가져오기
	public int selectNotificationSequenceNextValue() throws SQLException;
	//알림 insert
	public void insertNotification(NotificationVO notificationVO) throws Exception;
	//안읽은 알림 목록 조회 - By employeeId
	public List<NotificationVO> selectNotificationListByEmployeeId(String employeeId) throws Exception;
	//알림 읽음 update
	public void updateCheckedNotification(String ntcnId) throws Exception;
	//모든 알림 읽음 update
	public void updateCheckedAllNotification(String employeeId) throws Exception;
	
}
