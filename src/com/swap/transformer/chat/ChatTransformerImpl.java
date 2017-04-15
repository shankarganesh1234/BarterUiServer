package com.swap.transformer.chat;

import org.springframework.stereotype.Service;

import com.swap.entity.chat.ChatEntity;
import com.swap.entity.user.UserEntity;
import com.swap.models.chat.ChatDetailsRequest;
import com.swap.models.chat.ChatDetailsResponse;

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

}
