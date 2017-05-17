package com.swap.transformer.chat;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.swap.entity.chat.ChatEntity;
import com.swap.entity.user.UserEntity;
import com.swap.models.chat.ChatDetailsRequest;
import com.swap.models.chat.ChatDetailsResponse;
import com.swap.models.elasticsearch.ChatHistoryDocument;
import com.swap.models.sendbird.Channel;
import com.swap.models.sendbird.Payload;
import com.swap.models.sendbird.SendbirdWebhookRequest;
import com.swap.models.sendbird.Sender;

@Service
public class ChatTransformerImpl implements ChatTransformer {

	@Override
	public ChatEntity createEntity(ChatDetailsRequest chatDetails) {
		
		ChatEntity entity = new ChatEntity();
		entity.setChannelId(chatDetails.getChannelId());
		
		UserEntity originalUser = new UserEntity();
		originalUser.setUserId(chatDetails.getOriginalUserId());
		entity.setOriginalUserId(originalUser);
		
		UserEntity interestedUser = new UserEntity();
		interestedUser.setUserId(chatDetails.getInterestedUserId());
		entity.setOriginalUserId(interestedUser);
		
		return entity;
	}

	@Override
	public ChatDetailsResponse createResponse(ChatEntity chatEntity) {
		ChatDetailsResponse response = new ChatDetailsResponse();
		response.setChannelId(chatEntity.getChannelId());
		response.setInterestedUserId(chatEntity.getInterestedUserId().getUserId());
		response.setOriginalUserId(chatEntity.getOriginalUserId().getUserId());
		return response;
	}

	@Override
	public ChatHistoryDocument createChatHistoryDocument(SendbirdWebhookRequest request) {
		
		ChatHistoryDocument document = new ChatHistoryDocument();
		Sender sender = request.getSender();
		Payload payload = request.getPayload();
		Channel channel = request.getChannel();
		
		// get date
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(payload.getCreated_at());

		document.setChatChannelId(channel.getChannel_url());
		document.setMessage(payload.getMessage());
		document.setMessageTimestamp(calendar.getTime());
		document.setSenderId(sender.getUser_id());
		
		return document;
	}

}
