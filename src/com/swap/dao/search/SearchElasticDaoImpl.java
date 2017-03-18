package com.swap.dao.search;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.stereotype.Component;

import com.swap.common.components.ElasticTransportClient;
import com.swap.common.constants.Constants;

@Component
public class SearchElasticDaoImpl implements SearchElasticDao {

	private static final Logger logger = Logger.getLogger(SearchElasticDaoImpl.class);

	@Inject
	private ElasticTransportClient elasticTransportClient;

	@Override
	public List<CompletionSuggestion.Entry> autoComplete(SuggestBuilder suggestBuilder) {

		SearchResponse searchResponse = elasticTransportClient.getTransportClient()
				.prepareSearch(Constants.ELASTICSEARCH_INDEX_NAME).setTypes(Constants.ELASTICSEARCH_INDEX_TYPE_ITEM)
				.suggest(suggestBuilder).execute().actionGet();

		CompletionSuggestion compSuggestion = searchResponse.getSuggest().getSuggestion("suggest");
        List<CompletionSuggestion.Entry> entryList = compSuggestion.getEntries();
        return entryList;
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	public SearchResponse searchItems(QueryBuilder query, int from, int limit) {
		logger.debug("Entering searchItems");
		SearchResponse searchResponse = null;
		try {
			SearchRequestBuilder srb = elasticTransportClient.getTransportClient()
					.prepareSearch(Constants.ELASTICSEARCH_INDEX_NAME)
					.setTypes(Constants.ELASTICSEARCH_INDEX_TYPE_ITEM);
			srb.setQuery(query);
			srb.setSearchType(SearchType.QUERY_THEN_FETCH).setFrom(from).setSize(limit);
			searchResponse = srb.execute().actionGet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Exiting searchItems");
		return searchResponse;
	}
}
