package com.swap.validator.search;

import org.json.JSONObject;

import com.swap.models.search.BarterySearchRequest;

public interface SearchElasticValidator {

	void validateSearchElasticRequest(BarterySearchRequest searchRequest);
	void validateSearchElasticResponse(JSONObject searchElasticResponse);
}
