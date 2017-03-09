package com.swap.service.search;

import java.util.List;

import com.swap.models.elasticsearch.ItemDocument;
import com.swap.models.search.SearchRequest;
import com.swap.models.search.SearchResponse;

public interface SearchService {

	SearchResponse searchItems(SearchRequest searchRequest);
	List<ItemDocument> autoComplete(String term);
}

