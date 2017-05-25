package com.swap.transformer.notification;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.swap.entity.notification.NotificationEntity;
import com.swap.models.notification.NotificationModel;

@Component
public class NotificationTransformerImpl implements NotificationTransformer {

	@Override
	public NotificationEntity txNotificationModel(NotificationModel notificationModel) {
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity.setInterestId(notificationModel.getInterestId());
		notificationEntity.setUserId(notificationModel.getUserId());
		notificationEntity.setType(notificationModel.getType());
		notificationEntity.setStatus(notificationModel.getStatus());
		return notificationEntity;
	}

	@Override
	public NotificationModel txNotificationEntity(NotificationEntity notificationEntity) {
		NotificationModel notificationModel = new NotificationModel();
		notificationModel.setInterestId(notificationEntity.getInterestId());
		notificationModel.setUserId(notificationEntity.getUserId());
		notificationModel.setType(notificationEntity.getType());
		notificationModel.setStatus(notificationEntity.getStatus());
		notificationModel.setId(notificationEntity.getId());
		return notificationModel;
	}
	
	@Override
	public List<NotificationModel> txNotificationEntities(List<NotificationEntity> notificationEntities) {
		
		if(CollectionUtils.isEmpty(notificationEntities))
			return null;
		
		List<NotificationModel> notifications = new ArrayList<>();
		
		for(NotificationEntity notificationEntity : notificationEntities) {
			NotificationModel model = txNotificationEntity(notificationEntity);
			notifications.add(model);
		}
		return notifications;
	}	
}
