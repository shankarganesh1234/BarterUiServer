package com.swap.dao.chat;

import java.util.List;

import com.swap.models.elasticsearch.ChatHistoryDocument;

public interface ChatElasticDao {

	boolean appendChatHistory(ChatHistoryDocument chatDocument);
	List<ChatHistoryDocument> getChatHistory(String channelId);
}
