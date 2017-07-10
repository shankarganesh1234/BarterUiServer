package com.swap.service.chat;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swap.common.constants.Constants;
import com.swap.dao.chat.ChatDao;
import com.swap.dao.chat.ChatElasticDao;
import com.swap.entity.chat.ChatEntity;
import com.swap.models.chat.ChatDetailsRequest;
import com.swap.models.chat.ChatDetailsResponse;
import com.swap.models.chat.CreateUserRequest;
import com.swap.models.chat.UserResponse;
import com.swap.models.elasticsearch.ChatHistoryDocument;
import com.swap.models.sendbird.SendbirdWebhookRequest;
import com.swap.transformer.chat.ChatTransformer;

@Service
public class ChatServiceImpl implements ChatService {

	private static final Logger logger = Logger.getLogger(ChatServiceImpl.class);

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
		logger.debug("ChatHistoryDocument final :  " + document);
		return chatElasticDaoImpl.appendChatHistory(document);
	}

	@Override
	public List<ChatHistoryDocument> getChatHistory(String channelId) {
		return chatElasticDaoImpl.getChatHistory(channelId);
	}

	@Override
	public UserResponse createSendbirdUser(CreateUserRequest createUser) {
		
		logger.debug("Entering sendbird create user : " + createUser.toString());
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse getUserResponse = null;
		ObjectMapper objectMapper = new ObjectMapper();
		UserResponse userResponse = null;
		try {
		    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			URIBuilder sendbirdGetUserUri = new URIBuilder(Constants.SENDBIRD_BASE_URL + "users");
			HttpPost httpPost = new HttpPost(sendbirdGetUserUri.toString());
			httpPost.addHeader("Content-Type", "application/json, charset=utf8");
			httpPost.addHeader("Api-Token", "12b06f794e23bb53bcf9aa0afbd95ff17212ca31");
			
			StringEntity entity = new StringEntity(objectMapper.writeValueAsString(createUser));
			httpPost.setEntity(entity);
			
			getUserResponse = httpclient.execute(httpPost);
			
			
			
			HttpEntity userEntity = getUserResponse.getEntity();
			String content = EntityUtils.toString(userEntity);
			
			if(getUserResponse == null || getUserResponse.getStatusLine().getStatusCode() != 200) {
				return null;
			}
			
            userResponse = objectMapper.readValue(content, UserResponse.class);

            // do something useful with the response body
			// and ensure it is fully consumed
			EntityUtils.consume(userEntity);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (getUserResponse != null)
				try {
					getUserResponse.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		logger.debug("Exiting sendbird create user : " + userResponse.toString());

		return userResponse;
	}

	/**
	 * Get the user from sendbird based on the user id
	 */
	@Override
	public UserResponse getSendbirdUser(String userId) {
		
		logger.debug("Entering sendbird getSendbirdUser : " + userId);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse getUserResponse = null;
		ObjectMapper objectMapper = new ObjectMapper();
		UserResponse userResponse = null;
		try {
		    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			URIBuilder sendbirdGetUserUri = new URIBuilder(Constants.SENDBIRD_BASE_URL + "users/" + userId);
			HttpGet httpGet = new HttpGet(sendbirdGetUserUri.toString());
			httpGet.addHeader("Content-Type", "application/json, charset=utf8");
			httpGet.addHeader("Api-Token", "12b06f794e23bb53bcf9aa0afbd95ff17212ca31");
			getUserResponse = httpclient.execute(httpGet);

			HttpEntity userEntity = getUserResponse.getEntity();
			String content = EntityUtils.toString(userEntity);
            
			// return null if status is not 200
			if(getUserResponse == null || getUserResponse.getStatusLine().getStatusCode() != 200) {
							return null;
			}			
			
			userResponse = objectMapper.readValue(content, UserResponse.class);

            // do something useful with the response body
			// and ensure it is fully consumed
			EntityUtils.consume(userEntity);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (getUserResponse != null)
				try {
					getUserResponse.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		logger.debug("exiting sendbird getSendbirdUser : " + userResponse);
		return userResponse;
	}
}
