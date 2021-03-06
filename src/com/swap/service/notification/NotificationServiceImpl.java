package com.swap.service.notification;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.common.enums.NotificationStatusEnum;
import com.swap.common.enums.NotificationTypeEnum;
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
	
	@Override
	@Transactional
	public boolean createNotification(String interestId, String userId, NotificationStatusEnum status, NotificationTypeEnum type) {
		boolean result = false;
		//tx and get entity
		NotificationEntity notificationEntity = notificationTransformer.createNotificationEntity(interestId, userId, status, type);
		
		// create db record
		result = notificationDao.createInterest(notificationEntity);
		
		// send notification
		if(result) {
			sendNotificationWebsocket(userId);
		}
		return result;
	}

	@Override
	@Transactional
	public boolean updateStatusToRead(String interestId, String userId) {
		// update read status
		boolean result = notificationDao.updateStatusToRead(interestId, userId, NotificationStatusEnum.READ);
		
		// send notification
		if(result) {
			sendNotificationWebsocket(userId);
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
	@Transactional
	public void sendNotificationWebsocket(String userId) {

		// send the notifications if the websocket is open for the user
		if (NotificationSessionHandler.isWebsocketPresent(userId)) {
			// get latest notification status for the user
			List<NotificationModel> notifications = getUnreadNotificationsForUser(userId);

			// not checking for null notifications, since that is a valid scenario
			if (StringUtils.isNotBlank(userId)) {
				NotificationSessionHandler.sendToSession(userId, notifications);
			}
		}
	}
}
