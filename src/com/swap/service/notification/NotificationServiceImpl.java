package com.swap.service.notification;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.common.enums.NotificationStatusEnum;
import com.swap.dao.notification.NotificationDao;
import com.swap.entity.notification.NotificationEntity;
import com.swap.models.notification.NotificationModel;
import com.swap.transformer.notification.NotificationTransformer;
import com.swap.websocket.NotificationSessionHandler;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Inject
	private NotificationDao notificationDao;
	
	@Inject
	private NotificationTransformer notificationTransformer;
	
//	@Inject
//	private NotificationSessionHandler notificationSessionHandler;
	
	@Override
	@Transactional
	public boolean createNotification(NotificationModel notificationModel) {
		boolean result = false;
		//tx and get entity
		NotificationEntity notificationEntity = notificationTransformer.txNotificationModel(notificationModel);
		
		// create db record
		result = notificationDao.createInterest(notificationEntity);
		
		// send notification
		if(result) {
			sendNotificationWebsocket(notificationModel.getUserId());
		}
		return result;
	}

	@Override
	@Transactional
	public boolean updateStatusToRead(String id) {
		// update read status
		boolean result = notificationDao.updateStatusToRead(id, NotificationStatusEnum.READ);
		
		// send notification
		if(result) {
			sendNotificationWebsocket(id);
		}
		return result;
	}

	@Override
	@Transactional
	public List<NotificationModel> getUnreadNotificationsForUser(String userId) {
		List<NotificationEntity> notificationEntities = notificationDao.getUnreadNotificationsForUser(userId);
		return notificationTransformer.txNotificationEntities(notificationEntities);
	}

	@Override
	public void sendNotificationWebsocket(String userId) {

		// send the notifications if the websocket is open for the user
		if (NotificationSessionHandler.isWebsocketPresent(userId)) {
			// get latest notification status for the user
			List<NotificationModel> notifications = getUnreadNotificationsForUser(userId);

			if (CollectionUtils.isNotEmpty(notifications)) {
				StringBuilder builder = new StringBuilder();
				for (NotificationModel model : notifications) {
					builder.append(model.getInterestId() + ",");
				}
				String interests = builder.substring(0, builder.length() - 1);
				NotificationSessionHandler.sendToSession(userId, interests);
			}
		}
	}
}
