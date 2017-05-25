package com.swap.websocket;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint(encoders={NotificationEncoder.class}, value="/notifications/{userId}")
public class NotificationSocket {

//	@Inject
//	NotificationSessionHandler sessionHandler;
	
	@OnOpen
	public void open(Session session, @PathParam("userId") final String userId, EndpointConfig config) {
		NotificationSessionHandler.addSession(userId, session);
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
		//System.out.println("Sending text back");
		//sessionHandler.sendToSession(userId, null);
	}
}
