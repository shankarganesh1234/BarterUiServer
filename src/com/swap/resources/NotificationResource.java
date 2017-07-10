package com.swap.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.swap.models.notification.NotificationModel;
import com.swap.service.notification.NotificationService;

@Path("notifications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationResource {

	@Inject
	NotificationService notificationService;
	
	@Path("{userId}")
	@GET
	public List<NotificationModel> getUserNotifications(@PathParam("userId") String  userId) {
		return notificationService.getUnreadNotificationsForUser(userId);
	}
}
