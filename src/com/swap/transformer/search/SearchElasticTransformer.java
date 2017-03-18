package com.swap.transformer.search;

import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;

import com.swap.models.search.BarterySearchRequest;
import com.swap.models.search.BarterySearchResponse;

public interface SearchElasticTransformer {

	BarterySearchResponse convertToSearchResponse(SearchResponse searchResponse, BarterySearchRequest searchRequest);

	SuggestBuilder createItemAutoCompleteRequest(String term);

	List<String> convertoToAutocompleteResponse(List<CompletionSuggestion.Entry> results);

	QueryBuilder createSearchRequest(BarterySearchRequest request);
}
