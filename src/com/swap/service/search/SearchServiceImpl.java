package com.swap.service.search;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.stereotype.Service;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.dao.search.SearchElasticDao;
import com.swap.models.search.BarterySearchRequest;
import com.swap.models.search.BarterySearchResponse;
import com.swap.transformer.search.SearchElasticTransformer;
import com.swap.validator.search.SearchElasticValidator;

@Service
public class SearchServiceImpl implements SearchService {

	private static final Logger logger = Logger.getLogger(SearchServiceImpl.class);

	@Inject
	private SearchElasticTransformer searchElasticTransformer;

	@Inject
	private SearchElasticValidator searchValidator;

	@Inject
	private SearchElasticDao searchElasticDao;

	/**
	 * Search the items based on the parameters provided in the search request
	 */
	@Override
	public BarterySearchResponse searchItems(BarterySearchRequest searchRequest) {

		BarterySearchResponse searchResponse = null;

		try {
			// validate request
			searchValidator.validateSearchElasticRequest(searchRequest);

			// get final query
			QueryBuilder query = searchElasticTransformer.createSearchRequest(searchRequest);
			
			// call DAO and get response
			SearchResponse result = searchElasticDao.searchItems(query, searchRequest.getStart(), searchRequest.getLimit());

			// validate response
			searchResponse = searchElasticTransformer.convertToSearchResponse(result, searchRequest);
			
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.SEARCH_SERVICE_FAILURE);
		}
		return searchResponse;
	}

	@Override
	public List<String> autoComplete(String term) {
		List<String> itemDocuments = null;

		if(term == null || term.isEmpty())
			return null;
		
		try {
			
			SuggestBuilder suggestBuilder = searchElasticTransformer.createItemAutoCompleteRequest(term);
			// call DAO and get response
			List<CompletionSuggestion.Entry> results = searchElasticDao.autoComplete(suggestBuilder);
			// validate response
			itemDocuments = searchElasticTransformer.convertoToAutocompleteResponse(results);

		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.SEARCH_SERVICE_FAILURE);
		}
		return itemDocuments;
	}
}
