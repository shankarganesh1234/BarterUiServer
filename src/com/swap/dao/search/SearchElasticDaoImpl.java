package com.swap.dao.search;

import java.io.IOException;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.swap.common.constants.Constants;
import com.swap.common.httpclient.SwapHttpClientFactory;

@Component
public class SearchElasticDaoImpl implements SearchElasticDao {

	private static final Logger logger = Logger.getLogger(SearchElasticDaoImpl.class);

	@Inject
	private SwapHttpClientFactory swapHttpClientFactory;

	@Override
	public JSONObject searchItems(String query, Long zip) {
		return invokeSearch(Constants.ELASTICSEARCH_BASE_URL, Constants.ELASTICSEARCH_INDEX_NAME, Constants.ELASTICSEARCH_INDEX_TYPE_ITEM, query, zip);

	}

	/**
	 * Search items in the swap index/ type item, for items that match the query
	 * 
	 * @param baseUrl
	 * @param indexName
	 * @param type
	 * @param query
	 * @return
	 */
	private JSONObject invokeSearch(String baseUrl, String indexName, String type, String query, Long zip) {

		CloseableHttpResponse response = null;
		JSONObject result = null;
		try {

			CloseableHttpClient closeableHttpClient = swapHttpClientFactory.getHttpClient();
			HttpGet get = new HttpGet(baseUrl + indexName + type + Constants.ELASTICSEARCH_SEARCH_QUERY_KEY + URLEncoder.encode(query, "UTF-8"));
			get.setHeader(Constants.ACCEPT_HEADER, MediaType.APPLICATION_JSON);
			get.setHeader(Constants.CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON);
			response = closeableHttpClient.execute(get);

			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				
				HttpEntity entity = response.getEntity();
				result = new JSONObject(IOUtils.toString(entity.getContent(), "UTF-8"));
				EntityUtils.consume(entity);
				
			} else {
				logger.debug("An error occurred while searching for items in elastic search");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	@Override
	public JSONObject autoComplete(JSONObject autoCompleteRequest) {
		return invokeAutoComplete(Constants.ELASTICSEARCH_BASE_URL, Constants.ELASTICSEARCH_INDEX_NAME, Constants.ELASTICSEARCH_INDEX_TYPE_ITEM, autoCompleteRequest);
	}
	
	public JSONObject invokeAutoComplete(String baseUrl, String indexName, String type, JSONObject autoCompleteRequest) {
		CloseableHttpResponse response = null;
		JSONObject result = null;
		try {

			CloseableHttpClient closeableHttpClient = swapHttpClientFactory.getHttpClient();
			HttpPost post = new HttpPost(baseUrl + indexName + type + "_search");
			post.setHeader(Constants.ACCEPT_HEADER, MediaType.APPLICATION_JSON);
			post.setHeader(Constants.CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON);
			StringEntity requestEntity = new StringEntity(autoCompleteRequest.toString(), ContentType.APPLICATION_JSON);
			post.setEntity(requestEntity);
			response = closeableHttpClient.execute(post);

			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				
				HttpEntity entity = response.getEntity();
				result = new JSONObject(IOUtils.toString(entity.getContent(), "UTF-8"));
				EntityUtils.consume(entity);
				
			} else {
				logger.debug("An error occurred while searching for items in elastic search");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	@Override
	public JSONObject searchItems(String query) {
		return invokeSearchWithQuery(Constants.ELASTICSEARCH_BASE_URL, Constants.ELASTICSEARCH_INDEX_NAME, Constants.ELASTICSEARCH_INDEX_TYPE_ITEM, query);
	}
	
	private JSONObject invokeSearchWithQuery(String baseUrl, String indexName, String type, String query) {
		CloseableHttpResponse response = null;
		JSONObject result = null;
		try {

			CloseableHttpClient closeableHttpClient = swapHttpClientFactory.getHttpClient();
			HttpPost post = new HttpPost(baseUrl + indexName + type + Constants.ELASTICSEARCH_SEARCH_QUERY_KEY);
			post.setHeader(Constants.ACCEPT_HEADER, MediaType.APPLICATION_JSON);
			post.setHeader(Constants.CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON);
			StringEntity requestEntity = new StringEntity(query, ContentType.APPLICATION_JSON);
			post.setEntity(requestEntity);
			response = closeableHttpClient.execute(post);

			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				
				HttpEntity entity = response.getEntity();
				result = new JSONObject(IOUtils.toString(entity.getContent(), "UTF-8"));
				EntityUtils.consume(entity);
				
			} else {
				logger.debug("An error occurred while searching for items in elastic search");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}	

}
