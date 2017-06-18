package com.swap.transformer.search;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion.Entry.Option;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.stereotype.Component;

import com.swap.common.components.GeoLocationStore;
import com.swap.common.constants.Constants;
import com.swap.models.common.GeoLocation;
import com.swap.models.elasticsearch.ItemDocument;
import com.swap.models.search.BarterySearchRequest;
import com.swap.models.search.BarterySearchResponse;

@Component
public class SearchElasticTransformerImpl implements SearchElasticTransformer {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Inject
	private GeoLocationStore geoLocationStore;
	
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
	 * Creates regular as well as default search queries, for querying elasticsearch
	 * data
	 * @param request
	 * @return
	 */
	public QueryBuilder createSearchRequest(BarterySearchRequest request) {

		BoolQueryBuilder bqb = null;
		// when zip is 0, the case represents when the user just lands on the
		// landing page
		// and default results need to be shown
		if (request.getZip() == 0) {

			
			
		} else {

			String searchTerm = StringUtils.isBlank(request.getSearch()) ? "*"
					: "*" + request.getSearch().toLowerCase().trim() + "*";

			bqb = QueryBuilders.boolQuery().must(QueryBuilders.wildcardQuery(Constants.TITLE_LOWERCASE, searchTerm));

			// add category query if present
			if (!request.getCategoryName().equalsIgnoreCase(Constants.ALL_CATEGORIES)) {
				bqb.must(QueryBuilders.matchQuery(Constants.CATEGORY_NAME, request.getCategoryName())
						.operator(Operator.AND));
			}

			// add geo distance query if present
			if (request.getZip() != null) {
				GeoDistanceQueryBuilder geoDistanceQueryBuilder = createGeoDistanceQuery(request);
				if (geoDistanceQueryBuilder != null) {
					bqb.filter(createGeoDistanceQuery(request));
				}
			}
		}

		return bqb;
	}
	
	/**
	 * Create a geo distance query after converting zip to lat/lon
	 * @return
	 */
	public GeoDistanceQueryBuilder createGeoDistanceQuery(BarterySearchRequest request) {
		
		if(request.getZip() == null) 
			return null;
		
		GeoLocation geoLocation = getGeoLocation(request.getZip());
		double lat = Double.valueOf(geoLocation.getLatitude());
		double lon = Double.valueOf(geoLocation.getLongitude());
		
		String distance = request.getDistance() != null ? String.valueOf(request.getDistance()) : Constants.DEFAULT_SEARCH_DISTANCE;
		return QueryBuilders.geoDistanceQuery(Constants.LOCATION).point(lat, lon).distance(distance, DistanceUnit.MILES);
	}
	
	/**
	 * Get coordinates for zip from map or from google maps api
	 * @return
	 */
	public GeoLocation getGeoLocation(Long zip) {
		GeoLocation geoLocation = null;
		
		geoLocation = geoLocationStore.getGeoLocationMap().get(String.valueOf(zip));
		
		if(geoLocation == null) {
			// zip not present in map, call google maps api
			geoLocation = geoLocationStore.getLocationFromGoogle(zip);
		}
		return geoLocation;
	}
}
