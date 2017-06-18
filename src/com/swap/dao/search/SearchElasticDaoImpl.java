package com.swap.dao.search;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

import com.swap.common.components.ElasticTransportClient;
import com.swap.common.constants.Constants;

@Component
public class SearchElasticDaoImpl implements SearchElasticDao {

	private static final Logger logger = Logger.getLogger(SearchElasticDaoImpl.class);

	@Inject
	private ElasticTransportClient elasticTransportClient;
	
	@Inject
	private PropertiesFactoryBean envProps;

	@Override
	public List<CompletionSuggestion.Entry> autoComplete(SuggestBuilder suggestBuilder) {

		List<CompletionSuggestion.Entry> entryList = null;
		try {
		SearchResponse searchResponse = elasticTransportClient.getTransportClient()
				.prepareSearch(envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXNAME)).setTypes(envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXTYPE))
				.suggest(suggestBuilder).execute().actionGet();

		CompletionSuggestion compSuggestion = searchResponse.getSuggest().getSuggestion("suggest");
        entryList = compSuggestion.getEntries();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        return entryList;
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	public SearchResponse searchItems(QueryBuilder query, int from, int limit, String sortField, SortOrder sortOrder) {
		logger.debug("Entering searchItems");
		SearchResponse searchResponse = null;
		try {
			SearchRequestBuilder srb = elasticTransportClient.getTransportClient()
					.prepareSearch(envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXNAME))
					.setTypes(envProps.getObject().getProperty(Constants.ELASTICSEARCH_INDEXTYPE));
			srb.setQuery(query);
			srb.setSearchType(SearchType.QUERY_THEN_FETCH).setFrom(from).setSize(limit);
			
			// sorting by time of create or update
			if(!StringUtils.isBlank(sortField)) {
				srb.addSort(sortField, sortOrder);
			}
			
			searchResponse = srb.execute().actionGet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Exiting searchItems");
		return searchResponse;
	}
}
