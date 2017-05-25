package com.swap.websocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.swap.models.notification.NotificationModel;

@Service
@ApplicationScoped
public final class NotificationSessionHandler {
	private static final Map<String, Session> sessions = new HashMap<>();
	private static final ObjectMapper mapper = new ObjectMapper();
	/**
	 * Add a new user websocket session to the sessions map
	 * @param userId
	 * @param session
	 */
	public static void addSession(String userId, Session session) {
		sessions.put(userId, session);
	}
	
	/**
	 * Remove a user from the websocket sessions map
	 * @param userId
	 */
	public static void removeSession(String userId) {
		sessions.remove(userId);
	}
	
	/**
	 * Send a message to a user session
	 * @param userId
	 * @param message
	 */
	public static void sendToSession(String userId, List<NotificationModel> interestNotifications) {
		try {
			sessions.get(userId).getBasicRemote().sendText(mapper.writeValueAsString(interestNotifications));
		} catch (Exception ex) {
			sessions.remove(userId);
		}
	}
	
	/**
	 * Check if active websocket session is present for user
	 * @param userId
	 * @return
	 */
	public static boolean isWebsocketPresent(String userId) {
		return sessions.containsKey(userId);
	}
}
