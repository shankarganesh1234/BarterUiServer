package com.swap.transformer.search;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONObject;

import com.swap.models.elasticsearch.ItemDocument;
import com.swap.models.search.SearchResponse;

public interface SearchElasticTransformer {
	SearchResponse convertToSearchResponse(JSONObject object) throws JsonParseException, JsonMappingException, IOException;
	JSONObject createItemAutoCompleteRequest(String term);
	List<ItemDocument> convertoToAutocompleteResponse(JSONObject object) throws JsonParseException, JsonMappingException, IOException;
	String convertToSearchRequest(String searchQuery, Long zip);
}
