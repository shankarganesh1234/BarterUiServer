package com.swap.transformer.notification;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.swap.common.enums.NotificationStatusEnum;
import com.swap.common.enums.NotificationTypeEnum;
import com.swap.entity.notification.NotificationEntity;
import com.swap.models.notification.NotificationModel;

@Component
public class NotificationTransformerImpl implements NotificationTransformer {

	@Override
	public NotificationEntity txNotificationModel(NotificationModel notificationModel) {
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity.setInterestId(notificationModel.getInterestId());
		notificationEntity.setUserId(notificationModel.getUserId());
		notificationEntity.setType(notificationModel.getType().name());
		notificationEntity.setStatus(notificationModel.getStatus().name());
		return notificationEntity;
	}

	@Override
	public NotificationModel txNotificationEntity(NotificationEntity notificationEntity) {
		NotificationModel notificationModel = new NotificationModel();
		notificationModel.setInterestId(notificationEntity.getInterestId());
		notificationModel.setUserId(notificationEntity.getUserId());
		notificationModel.setType(NotificationTypeEnum.fromValue(notificationEntity.getType()));
		notificationModel.setStatus(NotificationStatusEnum.fromValue(notificationEntity.getStatus()));
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

	@Override
	public NotificationEntity createNotificationEntity(String interestId, String userId, NotificationStatusEnum status,
			NotificationTypeEnum type) {
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity.setInterestId(interestId);
		notificationEntity.setUserId(userId);
		notificationEntity.setType(type.name());
		notificationEntity.setStatus(status.name());
		return notificationEntity;
	}	
}
