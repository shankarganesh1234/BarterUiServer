package com.swap.transformer.notification;

import java.util.List;

import com.swap.entity.notification.NotificationEntity;
import com.swap.models.notification.NotificationModel;

public interface NotificationTransformer {
	NotificationEntity txNotificationModel(NotificationModel notificationModel);
	List<NotificationModel> txNotificationEntities(List<NotificationEntity> notificationEntities);
	NotificationModel txNotificationEntity(NotificationEntity notificationEntity);
}
