package com.swap.resources;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swap.models.chat.ChatDetailsResponse;
import com.swap.models.elasticsearch.ChatHistoryDocument;
import com.swap.models.sendbird.SendbirdWebhookRequest;
import com.swap.service.chat.ChatService;

@Path("chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatResource {

	@Inject
	ChatService chatService;
	
	@Path("/originaluser/{originalUserId}")
	@GET
	public ChatDetailsResponse getChatDetailsByOriginalUser(@PathParam("originalUserId") String originalUserId) {
		return chatService.getChatChannelByOriginalUser(originalUserId);
	}
	
	@Path("/interestedUser/{interestedUserId}")
	@GET
	public ChatDetailsResponse getChatDetailsByInterestedUser(@PathParam("interestedUserId") String interestedUserId) {
		return chatService.getChatChannelByInterestedUser(interestedUserId);
	}
	
//	@POST
//	public void createChatDetail(ChatDetailsRequest request) {
//		chatService.createChatDetails(request);
//	}
	
	/**
	 * Need to keep it post as sendbird expects it to be a POST
	 * @param sendbirdWebHookJson
	 */
	@Path("/history")
	@POST
	public boolean sendbirdChatHistoryWebhook(String request) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		SendbirdWebhookRequest sendbirdRequest;
		boolean result = false;
		try {
		    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			sendbirdRequest = objectMapper.readValue(request, SendbirdWebhookRequest.class);
			result = chatService.appendChatHistory(sendbirdRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Path("/history")
	@GET
	public List<ChatHistoryDocument> getChatHistory(@QueryParam("channelId") String channelId) {
		return chatService.getChatHistory(channelId);
	}
	
}
