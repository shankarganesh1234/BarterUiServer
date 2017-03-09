package com.swap.validator.search;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.models.search.SearchRequest;

@Component
public class SearchElasticValidatorImpl implements SearchElasticValidator {

	@Override
	public void validateSearchElasticRequest(SearchRequest searchRequest) {
		
		if(searchRequest == null || StringUtils.isBlank(searchRequest.getQuery())) {
			throw new SwapException(ErrorEnum.INVALID_SEARCH_QUERY);
		}
	}

	@Override
	public void validateSearchElasticResponse(JSONObject searchElasticResponse) {
		// TODO Auto-generated method stub
		
	}

}
