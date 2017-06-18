package com.swap.service.search;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.stereotype.Service;

import com.swap.common.constants.Constants;
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
		SearchResponse result = null;
		try {
			// validate request
			searchValidator.validateSearchElasticRequest(searchRequest);

			// get final query
			QueryBuilder query = searchElasticTransformer.createSearchRequest(searchRequest);
			
			// call DAO and get response
			// if query is null, it would mean the search is called from landing page
			if(query == null) {
				result = searchElasticDao.searchItems(query, searchRequest.getStart(), searchRequest.getLimit(), Constants.DEFAULT_SORTFIELD, SortOrder.DESC);
			} else {
				result = searchElasticDao.searchItems(query, searchRequest.getStart(), searchRequest.getLimit(), null, null);
			}

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
