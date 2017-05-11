package com.swap.service.chat;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.swap.dao.chat.ChatDao;
import com.swap.dao.chat.ChatElasticDao;
import com.swap.entity.chat.ChatEntity;
import com.swap.models.chat.ChatDetailsRequest;
import com.swap.models.chat.ChatDetailsResponse;
import com.swap.models.elasticsearch.ChatHistoryDocument;
import com.swap.models.sendbird.SendbirdWebhookRequest;
import com.swap.transformer.chat.ChatTransformer;

@Service
public class ChatServiceImpl implements ChatService {

	@Inject
	private ChatTransformer chatTransformer;
	
	@Inject
	private ChatDao chatDao;
	
	@Inject
	private ChatElasticDao chatElasticDaoImpl;
	
	@Override
	public void createChatDetails(ChatDetailsRequest chatDetails) {
		ChatEntity entity = chatTransformer.createEntity(chatDetails);
		chatDao.createChatDetails(entity);
	}

	@Override
	public ChatDetailsResponse getChatChannelByOriginalUser(String originalUserId) {
		ChatEntity chatEntity = chatDao.getChatChannelByOriginalUser(originalUserId);
		return chatTransformer.createResponse(chatEntity);
	}

	@Override
	public ChatDetailsResponse getChatChannelByInterestedUser(String interestedUserId) {
		ChatEntity chatEntity = chatDao.getChatChannelByOriginalUser(interestedUserId);
		return chatTransformer.createResponse(chatEntity);
	}

	@Override
	public void deleteChatChannel(String channelId) {
		chatDao.deleteChatChannel(channelId);
	}

	@Override
	public boolean appendChatHistory(SendbirdWebhookRequest request) {
		ChatHistoryDocument document = chatTransformer.createChatHistoryDocument(request);
		return chatElasticDaoImpl.appendChatHistory(document);
	}

	@Override
	public List<ChatHistoryDocument> getChatHistory(String channelId) {
		return chatElasticDaoImpl.getChatHistory(channelId);
	}
}
