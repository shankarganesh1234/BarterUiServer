package com.swap.db.listeners;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.rest.RestStatus;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

import com.swap.common.components.ElasticTransportClient;
import com.swap.common.constants.Constants;
import com.swap.entity.item.ItemEntity;
import com.swap.models.elasticsearch.ItemDocument;

/**
 * Hibernate entity listeners that hook on to the entity lifecycle and listen to
 * configured events. Currently interested in PostUpdate and PostInsert events
 * 
 * @author shanganesh
 *
 */
@Component
public class ItemEntityInterceptor
		implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

	private static final Logger logger = Logger.getLogger(ItemEntityInterceptor.class);

	@Inject
	private ElasticTransportClient elasticTransportClient;

	@Inject
	private PropertiesFactoryBean envProps;

	ObjectMapper mapper = new ObjectMapper(); // create once, reuse

	/**
	 * 
	 * @param item
	 * @return
	 */
	private ItemDocument createItemDocument(ItemEntity item) {
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		ItemDocument itemDocument = new ItemDocument();
		BeanUtils.copyProperties(item, itemDocument);
		itemDocument.setZipCode((item.getZipCode() != null && item.getZipCode().getZipCode() != null
				? item.getZipCode().getZipCode() : null));
		itemDocument.setCategoryName((item.getCategoryId() != null && item.getCategoryId().getCategoryName() != null
				? item.getCategoryId().getCategoryName() : null));
		itemDocument.setTitleSuggest(item.getTitle());
		itemDocument.setImageUrl((item.getImage_id() != null && item.getImage_id().getUrl() != null)
				? item.getImage_id().getUrl() : null);
		return itemDocument;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6244581472535277877L;

	/**
	 * Index the document within elasticsearch node using http client
	 * 
	 * @param baseUrl
	 * @param indexName
	 * @param type
	 * @param item
	 */
	private void indexDocument(String baseUrl, String indexName, String type, ItemEntity item) {

		if (item == null) {
			logger.debug("Item instance was null. Nothing to index in elasticsearch");
		}

		Long itemId = item.getItemId();
		if (itemId == null) {
			logger.debug("Item id field is null/blank. Cannot index without an id");
		}

		try {
			// generate json
			ItemDocument itemDocument = createItemDocument(item);
			byte[] itemSource = mapper.writeValueAsBytes(itemDocument);
			IndexResponse indexResponse = elasticTransportClient.getTransportClient()
					.prepareIndex(envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXNAME),
							envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXTYPE), String.valueOf(itemId))
					.setSource(itemSource).get();
			if (indexResponse != null && indexResponse.status().equals(RestStatus.CREATED)) {
				logger.debug("Item with ItemId = " + itemId + " indexed successfully");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug(
					"Exception occurred within ListingEntityInterceptor.indexDocument(). Failing silently and proceeding");
		}
	}

	/**
	 * Index the document within elasticsearch node using http client
	 * 
	 * @param baseUrl
	 * @param indexName
	 * @param type
	 * @param item
	 */
	private void updateDocument(String baseUrl, String indexName, String type, ItemEntity item) {

		if (item == null) {
			logger.debug("Item instance was null. Nothing to index in elasticsearch");
		}

		Long itemId = item.getItemId();
		if (itemId == null) {
			logger.debug("Item id field is null/blank. Cannot index without an id");
		}

		try {
			// generate json
			ItemDocument itemDocument = createItemDocument(item);
			byte[] itemSource = mapper.writeValueAsBytes(itemDocument);
			UpdateResponse updateResponse = elasticTransportClient.getTransportClient()
					.prepareUpdate(envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXNAME),
							envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXTYPE), String.valueOf(itemId))
					.setDoc(itemSource).get();
			if (updateResponse != null && updateResponse.status().equals(RestStatus.CREATED)) {
				logger.debug("Item with ItemId = " + itemId + " updated successfully");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug(
					"Exception occurred within ListingEntityInterceptor.indexDocument(). Failing silently and proceeding");
		}
	}

	/**
	 * Index the document within elasticsearch node using http client
	 * 
	 * @param baseUrl
	 * @param indexName
	 * @param type
	 * @param item
	 */
	private void deleteDocument(String baseUrl, String indexName, String type, ItemEntity item) {

		if (item == null) {
			logger.debug("Item instance was null. Nothing to index in elasticsearch");
		}

		Long itemId = item.getItemId();
		if (itemId == null) {
			logger.debug("Item id field is null/blank. Cannot index without an id");
		}

		CloseableHttpResponse response = null;
		try {
			DeleteResponse deleteResponse = elasticTransportClient.getTransportClient()
					.prepareDelete(envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXNAME),
							envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXTYPE), String.valueOf(itemId))
					.get();
			if (deleteResponse != null && deleteResponse.status().equals(RestStatus.OK)) {
				logger.debug("Item with ItemId = " + itemId + " deleted successfully");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug(
					"Exception occurred within ListingEntityInterceptor.deleteDocument(). Failing silently and proceeding");
		} finally {

			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		System.out.println("inside post update");

		Object obj = event.getEntity();
		if (obj == null)
			return;

		if (obj instanceof ItemEntity) {
			logger.debug("Entered within post update listener. Begin indexing document");
			try {
				updateDocument(envProps.getObject().getProperty(Constants.ELASTICSEARCH_BASEURL),
						envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXNAME),
						envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXTYPE), (ItemEntity) obj);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		System.out.println("inside post insert");

		Object obj = event.getEntity();
		if (obj == null)
			return;

		if (obj instanceof ItemEntity) {
			logger.debug("Entered within post insert listener. Begin indexing document");
			try {
				indexDocument(envProps.getObject().getProperty(Constants.ELASTICSEARCH_BASEURL),
						envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXNAME),
						envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXTYPE), (ItemEntity) obj);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
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
		if (obj == null)
			return;

		if (obj instanceof ItemEntity) {
			logger.debug("Entered within post refresh listener. Begin indexing document");
			try {
				deleteDocument(envProps.getObject().getProperty(Constants.ELASTICSEARCH_BASEURL),
						envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXNAME),
						envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXTYPE), (ItemEntity) obj);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
