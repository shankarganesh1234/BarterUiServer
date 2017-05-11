package com.swap.dao.chat;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortMode;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Service;

import com.swap.common.components.ElasticTransportClient;
import com.swap.common.constants.Constants;
import com.swap.models.elasticsearch.ChatHistoryDocument;

@Service
public class ChatElasticDaoImpl implements ChatElasticDao {

	private static final Logger logger = Logger.getLogger(ChatElasticDaoImpl.class);

	@Inject
	private ElasticTransportClient elasticTransportClient;

	@Inject
	private PropertiesFactoryBean envProps;

	ObjectMapper mapper = new ObjectMapper(); // create once, reuse

	@Override
	public boolean appendChatHistory(ChatHistoryDocument chatDocument) {
		boolean result = false;
		try {
			byte[] chatHistorySource = mapper.writeValueAsBytes(chatDocument);
			IndexResponse indexResponse = elasticTransportClient.getTransportClient()
					.prepareIndex(envProps.getObject().getProperty(Constants.ELASTICSEARCH_CHAT_INDEXNAME),
							envProps.getObject().getProperty(Constants.ELASTICSEARCH_CHAT_INDEXTYPE))
					.setSource(chatHistorySource).get();
			if (indexResponse != null && indexResponse.status().equals(RestStatus.CREATED)) {
				logger.debug("chat history indexed successfully");
				result = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ChatHistoryDocument> getChatHistory(String channelId) {
		
		List<ChatHistoryDocument> chatHistoryDocuments = new LinkedList<>();
		SearchResponse searchResponse = null;

		try {
			SearchRequestBuilder srb = elasticTransportClient.getTransportClient()
					.prepareSearch(envProps.getObject().getProperty(Constants.ELASTICSEARCH_CHAT_INDEXNAME))
					.setTypes(envProps.getObject().getProperty(Constants.ELASTICSEARCH_CHAT_INDEXTYPE));
			QueryBuilder qb = QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("chatChannelId", channelId));
			srb.setQuery(qb);
			SortOrder sortOrder = SortOrder.ASC;
			FieldSortBuilder sortBuilder = SortBuilders.fieldSort("messageTimestamp").order(sortOrder).sortMode(SortMode.MIN);
			srb.addSort(sortBuilder);
			
			searchResponse = srb.execute().actionGet();
			
			
			SearchHit[] hits = searchResponse.getHits().getHits();

			for (int i = 0; i < hits.length; i++) {
				SearchHit hit = hits[i];
				try {
					ChatHistoryDocument document = mapper.readValue(hit.getSourceAsString(), ChatHistoryDocument.class);
					chatHistoryDocuments.add(document);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return chatHistoryDocuments;
	}
}
