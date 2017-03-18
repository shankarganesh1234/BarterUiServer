package com.swap.dao.search;

import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;

public interface SearchElasticDao {

	List<CompletionSuggestion.Entry> autoComplete(SuggestBuilder suggestBuilder);
	SearchResponse searchItems(QueryBuilder query, int from, int limit);
}
