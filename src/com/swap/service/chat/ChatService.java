package com.swap.service.chat;

import com.swap.models.chat.ChatDetailsRequest;
import com.swap.models.chat.ChatDetailsResponse;

public interface ChatService {

	void createChatDetails(ChatDetailsRequest chatDetails);

	ChatDetailsResponse getChatChannelByOriginalUser(String originalUserId);
	
	ChatDetailsResponse getChatChannelByInterestedUser(String interestedUserId);

	void deleteChatChannel(String channelId);
}
