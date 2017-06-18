package com.swap.service.notification;

import java.util.List;

import com.swap.common.enums.NotificationStatusEnum;
import com.swap.common.enums.NotificationTypeEnum;
import com.swap.models.notification.NotificationModel;

public interface NotificationService {

	boolean createNotification(String interestId, String userId, NotificationStatusEnum status, NotificationTypeEnum type);
	boolean updateStatusToRead(String userId, String interestId);
	List<NotificationModel> getUnreadNotificationsForUser(String userId);
	void sendNotificationWebsocket(String userId);
}
