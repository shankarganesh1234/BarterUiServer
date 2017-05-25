package com.swap.websocket;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.swap.models.notification.NotificationModel;

public class NotificationEncoder implements Encoder.Text<NotificationModel> {

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(NotificationModel object) throws EncodeException {
		return Json.createObjectBuilder()
				.add("id", object.getId())
				.add("interestId", object.getInterestId())
				.add("status", object.getStatus())
				.add("type", object.getType())
				.add("userId", object.getUserId())
				.add("createTimestamp", object.getCreateTimestamp().toString())
				.build().toString();
	}
}
