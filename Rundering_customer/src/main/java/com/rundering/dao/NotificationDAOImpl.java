package com.rundering.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rundering.dto.NotificationVO;

public class NotificationDAOImpl implements NotificationDAO{
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public int selectNotificationSequenceNextValue() throws SQLException {
	  return session.selectOne("Notification-Mapper.selectNotificationSequenceNextValue");
	}
	@Override
	public void insertNotification(NotificationVO notificationVO) throws Exception {
		session.update("Notification-Mapper.insertNotification", notificationVO);
	}
	@Override
	public List<NotificationVO> selectNotificationListByEmployeeId(String employeeId) throws Exception {
		return session.selectList("Notification-Mapper.selectNotificationListByEmployeeId",employeeId);
	}
	@Override
	public void updateCheckedNotification(String ntcnId) throws Exception {
		session.update("Notification-Mapper.updateCheckedNotification", ntcnId);
	}

	@Override
	public void updateCheckedAllNotification(String employeeId) throws Exception {
		session.update("Notification-Mapper.updateCheckedAllNotification", employeeId);
	}


}
