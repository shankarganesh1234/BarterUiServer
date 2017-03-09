package com.swap.db.listeners;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import com.swap.common.constants.Constants;
import com.swap.common.httpclient.SwapHttpClientFactory;
import com.swap.entity.listing.ListingEntity;
import com.swap.models.elasticsearch.ItemDocument;

/**
 * Hibernate entity listeners that hook on to the entity lifecycle and listen
 * to configured events.
 * Currently interested in PostUpdate and PostInsert events
 * @author shanganesh
 *
 */
@Component
public class ListingEntityInterceptor implements  PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

	private static final Logger logger = Logger.getLogger(ListingEntityInterceptor.class);

	@Inject
	private SwapHttpClientFactory httpClientFactory;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6244581472535277877L;
	
	/**
	 * Index the document within elasticsearch node using http client
	 * @param baseUrl
	 * @param indexName
	 * @param type
	 * @param item
	 */
	private void indexDocument(String baseUrl, String indexName, String type, ListingEntity item) {
		
		if(item == null) {
			logger.debug("Item instance was null. Nothing to index in elasticsearch");
		}
		
		Long itemId = item.getItemId();
		if(itemId == null) {
			logger.debug("Item id field is null/blank. Cannot index without an id");
		}
		
		CloseableHttpResponse response = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String itemJson = mapper.writeValueAsString(createItemDocument(item));
			StringEntity entity = new StringEntity(itemJson);
			CloseableHttpClient closeableHttpClient = httpClientFactory.getHttpClient();
			
			HttpPut put = new HttpPut(baseUrl + indexName + type + itemId);
			put.setEntity(entity);
			put.setHeader(Constants.ACCEPT_HEADER, MediaType.APPLICATION_JSON);
			put.setHeader(Constants.CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON);
			
			response = closeableHttpClient.execute(put);
			if(response != null && response.getStatusLine().getStatusCode() == 200) {
				logger.debug("Item with ItemId = " + itemId + " indexed successfully");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug("Exception occurred within ListingEntityInterceptor.indexDocument(). Failing silently and proceeding");
		} finally {
			
			if(response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	
	/**
	 * Index the document within elasticsearch node using http client
	 * @param baseUrl
	 * @param indexName
	 * @param type
	 * @param item
	 */
	private void deleteDocument(String baseUrl, String indexName, String type, ListingEntity item) {
		
		if(item == null) {
			logger.debug("Item instance was null. Nothing to index in elasticsearch");
		}
		
		Long itemId = item.getItemId();
		if(itemId == null) {
			logger.debug("Item id field is null/blank. Cannot index without an id");
		}
		
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient closeableHttpClient = httpClientFactory.getHttpClient();
			
			HttpDelete delete = new HttpDelete(baseUrl + indexName + type + itemId);
			delete.setHeader(Constants.ACCEPT_HEADER, MediaType.APPLICATION_JSON);
			delete.setHeader(Constants.CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON);
			
			response = closeableHttpClient.execute(delete);
			if(response != null && response.getStatusLine().getStatusCode() == 200) {
				logger.debug("Item with ItemId = " + itemId + " deleted successfully");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug("Exception occurred within ListingEntityInterceptor.deleteDocument(). Failing silently and proceeding");
		} finally {
			
			if(response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	private ItemDocument createItemDocument(ListingEntity item) {
		ItemDocument document = new ItemDocument();
		document.setActiveInterests(item.getActiveInterests());
		document.setCategoryName(item.getCategoryId() != null ? item.getCategoryId().getCategoryName() : null);
		document.setCity(item.getZipCode() != null ? item.getZipCode().getCity() : null);
		document.setCondition(item.getCondition());
		document.setDescription(item.getDescription());
		document.setImageUrl(item.getImage_id() != null ? item.getImage_id().getUrl() : null);
		document.setItemId(item.getItemId());
		document.setItemStage(document.getItemStage());
		document.setNumOfInterests(item.getNumOfInterests());
		document.setNumOfReviews(item.getNumOfReviews());
		document.setQuantity(item.getQuantity());
		document.setStory(item.getStory());
		document.setTitle(item.getTitle());
		document.setTitleSuggest(item.getTitle());
		document.setUserName(item.getUserId() != null ? item.getUserId().getUserName() : null);
		document.setZipCode(item.getZipCode() != null ? item.getZipCode().getZipCode() : null);
		return document;
	}
	
	public SwapHttpClientFactory getHttpClient() {
		return httpClientFactory;
	}

	public void setHttpClient(SwapHttpClientFactory httpClientFactory) {
		this.httpClientFactory = httpClientFactory;
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		System.out.println("inside post update");
		
		Object obj = event.getEntity();
		if(obj == null)
			return;
		
		if(obj instanceof ListingEntity) {
			logger.debug("Entered within post update listener. Begin indexing document");
			indexDocument(Constants.ELASTICSEARCH_BASE_URL, Constants.ELASTICSEARCH_INDEX_NAME, Constants.ELASTICSEARCH_INDEX_TYPE_ITEM, (ListingEntity) obj);
		}
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		System.out.println("inside post insert");
		
		Object obj = event.getEntity();
		if(obj == null)
			return;
		
		if(obj instanceof ListingEntity) {
			logger.debug("Entered within post insert listener. Begin indexing document");
			indexDocument(Constants.ELASTICSEARCH_BASE_URL, Constants.ELASTICSEARCH_INDEX_NAME, Constants.ELASTICSEARCH_INDEX_TYPE_ITEM, (ListingEntity) obj);
		}
		
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		System.out.println("inside post delete");
		
		Object obj = event.getEntity();
		if(obj == null)
			return;
		
		if(obj instanceof ListingEntity) {
			logger.debug("Entered within post refresh listener. Begin indexing document");
			deleteDocument(Constants.ELASTICSEARCH_BASE_URL, Constants.ELASTICSEARCH_INDEX_NAME, Constants.ELASTICSEARCH_INDEX_TYPE_ITEM, (ListingEntity) obj);
		}
		
	}

}
