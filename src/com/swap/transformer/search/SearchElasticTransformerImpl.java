package com.swap.transformer.search;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion.Entry.Option;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.stereotype.Component;

import com.swap.common.constants.Constants;
import com.swap.models.elasticsearch.ItemDocument;
import com.swap.models.search.BarterySearchRequest;
import com.swap.models.search.BarterySearchResponse;

@Component
public class SearchElasticTransformerImpl implements SearchElasticTransformer {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public BarterySearchResponse convertToSearchResponse(SearchResponse searchResponse,
			BarterySearchRequest searchRequest) {

		BarterySearchResponse barterySearchResponse = null;

		if (searchResponse == null)
			return null;

		barterySearchResponse = new BarterySearchResponse();
		barterySearchResponse.setTotal(extractTotal(searchResponse));
		barterySearchResponse.setItems(extractItems(searchResponse));

		// set query and zip back in response
		barterySearchResponse.setSearch(searchRequest.getSearch());
		barterySearchResponse.setZip(searchRequest.getZip());
		barterySearchResponse.setLimit(searchRequest.getLimit());
		barterySearchResponse.setStart(searchRequest.getStart());
		barterySearchResponse.setPage(searchRequest.getPage());

		return barterySearchResponse;
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	private long extractTotal(SearchResponse searchResponse) {
		return searchResponse.getHits().getTotalHits();
	}

	/**
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	private List<ItemDocument> extractItems(SearchResponse searchResponse) {

		List<ItemDocument> items = new LinkedList<>();
		SearchHit[] hits = searchResponse.getHits().getHits();

		for (int i = 0; i < hits.length; i++) {
			SearchHit hit = hits[i];
			ItemDocument document;
			try {
				document = objectMapper.readValue(hit.getSourceAsString(), ItemDocument.class);
				items.add(document);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return items;
	}

	@Override
	public SuggestBuilder createItemAutoCompleteRequest(String term) {

		SuggestBuilder suggestBuilder = new SuggestBuilder();
		CompletionSuggestionBuilder completionSuggestionBuilder = new CompletionSuggestionBuilder("titleSuggest");
		completionSuggestionBuilder.prefix(term);
		suggestBuilder.addSuggestion("suggest", completionSuggestionBuilder);
		suggestBuilder.setGlobalText("suggestions");
		return suggestBuilder;
	}

	@Override
	public List<String> convertoToAutocompleteResponse(List<CompletionSuggestion.Entry> results) {
		if (CollectionUtils.isEmpty(results))
			return null;

		Set<String> documents = new LinkedHashSet<>();
		CompletionSuggestion.Entry entry = results.get(0);
		List<Option> options = entry.getOptions();
		for (Option option : options) {
			documents.add(option.getText().string());
		}
		return new LinkedList<String>(documents);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public QueryBuilder createSearchRequest(BarterySearchRequest request) {

		String searchTerm = StringUtils.isBlank(request.getSearch()) ? "*"
				: request.getSearch().toLowerCase().trim() + "*";
		
		Long zip = request.getZip();
		
		//BoolQueryBuilder bqb = null;
		BoolQueryBuilder bqb = QueryBuilders.boolQuery().must(QueryBuilders.wildcardQuery(Constants.TITLE_LOWERCASE, searchTerm));
		
		// add category query if present
		if(!request.getCategoryName().equalsIgnoreCase(Constants.ALL_CATEGORIES)) {
			bqb.must(QueryBuilders.matchQuery(Constants.CATEGORY_NAME, request.getCategoryName()));
		}
		
		// add zip code if present
		if(request.getZip() != null) {
			bqb.filter(QueryBuilders.termQuery(Constants.ZIP, zip));
		}
		
		return bqb;
	}
}
