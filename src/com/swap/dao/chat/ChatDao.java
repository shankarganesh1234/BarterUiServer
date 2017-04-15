package com.swap.dao.chat;

import com.swap.entity.chat.ChatEntity;

public interface ChatDao {

	void createChatDetails(ChatEntity chatDetails);

	ChatEntity getChatChannelByOriginalUser(String originalUserId);
	
	ChatEntity getChatChannelByInterestedUser(String interestedUserId);

	void deleteChatChannel(String channelId);
}
