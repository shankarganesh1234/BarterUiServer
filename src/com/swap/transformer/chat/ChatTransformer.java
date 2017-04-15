package com.swap.transformer.chat;

import com.swap.entity.chat.ChatEntity;
import com.swap.models.chat.ChatDetailsRequest;
import com.swap.models.chat.ChatDetailsResponse;

public interface ChatTransformer {

	ChatEntity createEntity(ChatDetailsRequest chatDetails);
	ChatDetailsResponse createResponse(ChatEntity chatEntity);
	
}

