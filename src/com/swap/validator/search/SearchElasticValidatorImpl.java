package com.swap.validator.search;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.swap.models.search.BarterySearchRequest;

@Component
public class SearchElasticValidatorImpl implements SearchElasticValidator {

	@Override
	public void validateSearchElasticRequest(BarterySearchRequest searchRequest) {
		
	}

	@Override
	public void validateSearchElasticResponse(JSONObject searchElasticResponse) {
		// TODO Auto-generated method stub
		
	}
}
