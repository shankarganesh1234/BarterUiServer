package com.swap.service.notification;

import java.util.List;

import com.swap.models.notification.NotificationModel;

public interface NotificationService {

	boolean createNotification(NotificationModel notificationModel);
	boolean updateStatusToRead(String userId, String interestId);
	List<NotificationModel> getUnreadNotificationsForUser(String userId);
	void sendNotificationWebsocket(String userId);
}
