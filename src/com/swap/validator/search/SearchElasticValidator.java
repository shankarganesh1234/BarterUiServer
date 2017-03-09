package com.swap.validator.search;

import org.json.JSONObject;

import com.swap.models.search.SearchRequest;

public interface SearchElasticValidator {

	void validateSearchElasticRequest(SearchRequest searchRequest);
	void validateSearchElasticResponse(JSONObject searchElasticResponse);
}
