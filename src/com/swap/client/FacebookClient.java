package com.swap.client;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.swap.common.components.CommonObjMapper;
import com.swap.common.constants.Constants;
import com.swap.models.login.FbLongLivedTokenResponse;

@Component
public class FacebookClient {

	private static final Logger logger = Logger.getLogger(FacebookClient.class);

	@Inject
	private CommonObjMapper objectMapper;
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

		try {
			URIBuilder fbUri = new URIBuilder(Constants.FB_LONG_TOKEN);
			fbUri.addParameter(Constants.GRANT_TYPE, Constants.EXCHANGE_TOKEN);
			fbUri.addParameter(Constants.CLIENT_ID, Constants.FB_CLIENT_ID_VALUE);
			fbUri.addParameter(Constants.CLIENT_SECRET, Constants.FB_CLIENT_SECRET_VALUE);
			fbUri.addParameter(Constants.EXCHANGE_TOKEN, shortAccessToken);
			HttpGet httpGet = new HttpGet(fbUri.toString());
			fbResponse = httpclient.execute(httpGet);
			HttpEntity fbEntity = fbResponse.getEntity();
			String content = EntityUtils.toString(fbEntity);
            response = objectMapper.getObjMapperIgnoreUnknown().readValue(content, FbLongLivedTokenResponse.class);
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
