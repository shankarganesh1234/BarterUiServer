package com.swap.service.search;

import java.util.List;

import com.swap.models.search.BarterySearchRequest;
import com.swap.models.search.BarterySearchResponse;

public interface SearchService {

	BarterySearchResponse searchItems(BarterySearchRequest searchRequest);
	List<String> autoComplete(String term);
}

