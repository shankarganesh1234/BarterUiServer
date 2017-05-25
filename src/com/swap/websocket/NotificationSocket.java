package com.swap.websocket;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.swap.service.notification.NotificationService;

@ApplicationScoped
@ServerEndpoint(configurator=SpringConfigurator.class, value="/notifications/{userId}")
public class NotificationSocket {

	@Inject
	private NotificationService notificationService;
	
	@OnOpen
	public void open(Session session, @PathParam("userId") final String userId, EndpointConfig config) {
		// add session
		NotificationSessionHandler.addSession(userId, session);
		// send notifications
		notificationService.sendNotificationWebsocket(userId);
	}

	@OnClose
	public void close(Session session, @PathParam("userId") final String userId) {
		NotificationSessionHandler.removeSession(userId);
	}

	@OnError
	public void onError(Throwable error) {
		error.printStackTrace();
	}

	@OnMessage
	public void handleMessage(String message, Session session, @PathParam("userId") final String userId) {
		System.out.println("Message recieved : " + message);
	}

}
