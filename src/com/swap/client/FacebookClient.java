package com.swap.client;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swap.common.constants.Constants;
import com.swap.models.login.FbLongLivedTokenResponse;

@Component
public class FacebookClient {

	private static final Logger logger = Logger.getLogger(FacebookClient.class);

	/**
	 * Get the long lived access token from FB
	 * 
	 * @return
	 */
	public FbLongLivedTokenResponse getLongLivedAccessToken(String shortAccessToken) {
		
		logger.debug("Entering getLongLivedAccessToken");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse fbResponse = null;
		FbLongLivedTokenResponse response = null;
		ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			URIBuilder fbUri = new URIBuilder(Constants.FB_LONG_TOKEN);
			fbUri.addParameter("grant_type", "fb_exchange_token");
			fbUri.addParameter("client_id", "422821098053082");
			fbUri.addParameter("client_secret", "37a589b26576cebe77529d7f24ba7d3b");
			fbUri.addParameter("fb_exchange_token", shortAccessToken);
			HttpGet httpGet = new HttpGet(fbUri.toString());
			fbResponse = httpclient.execute(httpGet);
			System.out.println(fbResponse.getStatusLine());
			HttpEntity fbEntity = fbResponse.getEntity();
			
            String content = EntityUtils.toString(fbEntity);
            response = objectMapper.readValue(content, FbLongLivedTokenResponse.class);
    		logger.debug("Received response from FB : " + content);

			// do something useful with the response body
			// and ensure it is fully consumed
			EntityUtils.consume(fbEntity);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fbResponse != null)
				try {
					fbResponse.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return response;
	}
}
