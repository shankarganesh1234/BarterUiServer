package com.swap.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.swap.models.chat.ChatDetailsRequest;
import com.swap.models.chat.ChatDetailsResponse;
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
	
	@POST
	public void sendBirdWebHook(String sendbirdWebHookJson) {
		System.out.println(sendbirdWebHookJson);
	}
	
}
