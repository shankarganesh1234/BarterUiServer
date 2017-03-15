package com.swap.service.search;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.dao.search.SearchElasticDao;
import com.swap.models.elasticsearch.ItemDocument;
import com.swap.models.search.SearchRequest;
import com.swap.models.search.SearchResponse;
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
	public SearchResponse searchItems(SearchRequest searchRequest) {

		SearchResponse searchResponse = null;

		try {
			// validate request
			searchValidator.validateSearchElasticRequest(searchRequest);

			// get final query
			String query = searchElasticTransformer.convertToSearchRequest(searchRequest.getSearch(),  searchRequest.getZip());
			
			// call DAO and get response
			JSONObject result = searchElasticDao.searchItems(query);

			// validate response
			searchResponse = searchElasticTransformer.convertToSearchResponse(result);

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
	public List<ItemDocument> autoComplete(String term) {
		List<ItemDocument> itemDocuments = null;

		if(term == null || term.isEmpty())
			return null;
		
		try {
			
			JSONObject autoCompleteRequest = searchElasticTransformer.createItemAutoCompleteRequest(term);
			// call DAO and get response
			JSONObject result = searchElasticDao.autoComplete(autoCompleteRequest);
			// validate response
			itemDocuments = searchElasticTransformer.convertoToAutocompleteResponse(result);

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
