package com.swap.dao.notification;

import java.util.List;

import com.swap.common.enums.NotificationStatusEnum;
import com.swap.entity.notification.NotificationEntity;

public interface NotificationDao {

	boolean createInterest(NotificationEntity notificationEntity);
	boolean updateStatusToRead(String interestId, String userId, NotificationStatusEnum notificationStatus);
	List<NotificationEntity> getUnreadNotificationsForUser(String userId);
}
