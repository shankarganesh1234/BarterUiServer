package com.swap.transformer.notification;

import java.util.List;

import com.swap.common.enums.NotificationStatusEnum;
import com.swap.common.enums.NotificationTypeEnum;
import com.swap.entity.notification.NotificationEntity;
import com.swap.models.notification.NotificationModel;

public interface NotificationTransformer {
	NotificationEntity txNotificationModel(NotificationModel notificationModel);
	List<NotificationModel> txNotificationEntities(List<NotificationEntity> notificationEntities);
	NotificationModel txNotificationEntity(NotificationEntity notificationEntity);
	NotificationEntity createNotificationEntity(String interestId, String userId, NotificationStatusEnum status, NotificationTypeEnum type);
}
