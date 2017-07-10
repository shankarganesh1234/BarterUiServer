package com.swap.service.chat;

import java.util.List;

import com.swap.models.chat.ChatDetailsRequest;
import com.swap.models.chat.ChatDetailsResponse;
import com.swap.models.chat.CreateUserRequest;
import com.swap.models.chat.UserResponse;
import com.swap.models.elasticsearch.ChatHistoryDocument;
import com.swap.models.sendbird.SendbirdWebhookRequest;

public interface ChatService {

	void createChatDetails(ChatDetailsRequest chatDetails);

	ChatDetailsResponse getChatChannelByOriginalUser(String originalUserId);
	
	ChatDetailsResponse getChatChannelByInterestedUser(String interestedUserId);

	void deleteChatChannel(String channelId);
	
	boolean appendChatHistory(SendbirdWebhookRequest request);
	
	List<ChatHistoryDocument> getChatHistory(String channelId);
	
	UserResponse createSendbirdUser(CreateUserRequest createUser);
	
	UserResponse getSendbirdUser(String userId);
}
