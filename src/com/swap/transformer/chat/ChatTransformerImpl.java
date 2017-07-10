package com.swap.transformer.chat;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.swap.common.CommonUtil;
import com.swap.entity.chat.ChatEntity;
import com.swap.entity.user.UserEntity;
import com.swap.models.chat.ChatDetailsRequest;
import com.swap.models.chat.ChatDetailsResponse;
import com.swap.models.elasticsearch.ChatHistoryDocument;
import com.swap.models.sendbird.Channel;
import com.swap.models.sendbird.Member;
import com.swap.models.sendbird.Payload;
import com.swap.models.sendbird.SendbirdWebhookRequest;
import com.swap.models.sendbird.Sender;

@Service
public class ChatTransformerImpl implements ChatTransformer {

	private static final Logger logger = Logger.getLogger(ChatTransformerImpl.class);

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
		document.setMessage(CommonUtil.decodeMessage(payload.getMessage()));
		document.setMessageTimestamp(calendar.getTime());
		document.setSenderId(sender.getUser_id());
		
		Member[] members = request.getMembers();
		
		if(members != null) {
			logger.debug("Checking members" + members.toString());
			for(Member member: members) {
				logger.debug("member : " + member.toString());
				logger.debug("sender user id : " + sender.getUser_id());
				if(!member.getUser_id().equalsIgnoreCase(sender.getUser_id())) {
					logger.debug("Entered receiver loop");
					document.setReceiverName(member.getUser_id());
					document.setInterestId(CommonUtil.decodeInterestId(payload.getMessage()));
					document.setOnline(member.isIs_online());
				}
			}
		}
		return document;
	}

}
