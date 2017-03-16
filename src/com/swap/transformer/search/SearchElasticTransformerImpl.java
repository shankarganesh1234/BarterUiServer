package com.swap.transformer.search;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.swap.common.constants.Constants;
import com.swap.models.elasticsearch.ItemDocument;
import com.swap.models.search.SearchRequest;
import com.swap.models.search.SearchResponse;

@Component
public class SearchElasticTransformerImpl implements SearchElasticTransformer {

	@Override
	public SearchResponse convertToSearchResponse(JSONObject object)
			throws JsonParseException, JsonMappingException, IOException {

		SearchResponse searchResponse = null;

		if (object == null)
			return null;

		searchResponse = new SearchResponse();
		searchResponse.setTotal(extractTotal(object));
		searchResponse.setItems(extractItems(object));
		return searchResponse;
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	private long extractTotal(JSONObject object) {
		return object.getJSONObject("hits").getLong("total");
	}

	/**
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	private List<ItemDocument> extractItems(JSONObject object)
			throws JsonParseException, JsonMappingException, IOException {
		// ObjectMapper mapper = new ObjectMapper();
		List<ItemDocument> items = new LinkedList<>();
		JSONArray hits = object.getJSONObject("hits").getJSONArray("hits");
		for (int i = 0; i < hits.length(); i++) {
			JSONObject hit = hits.getJSONObject(i);
			JSONObject source = hit.getJSONObject("_source");
			// Item item = mapper.readValue(source.toString(), Item.class);
			ItemDocument item = createItem(source);
			items.add(item);
		}
		return items;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	private ItemDocument createItem(JSONObject obj) {
		ItemDocument item = new ItemDocument();
		item.setTitle(obj.getString("title") != null ? obj.getString("title") : null);
		item.setDescription(obj.getString("description") != null ? obj.getString("description") : null);
		item.setImageUrl(obj.optString("imageUrl"));
		return item;
	}

	@Override
	public JSONObject createItemAutoCompleteRequest(String term) {
		JSONObject autoCompleteRequest = new JSONObject();

		JSONObject field = new JSONObject();
		field.put("field", "titleSuggest");

		JSONObject itemSuggest = new JSONObject();
		itemSuggest.put("prefix", term);
		itemSuggest.put("completion", field);

		JSONObject suggest = new JSONObject();
		suggest.put("item-suggest", itemSuggest);

		autoCompleteRequest.put("suggest", suggest);
		return autoCompleteRequest;
	}

	@Override
	public List<ItemDocument> convertoToAutocompleteResponse(JSONObject object)
			throws JsonParseException, JsonMappingException, IOException {
		return extractAutocompleteItems(object);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	private List<ItemDocument> extractAutocompleteItems(JSONObject obj) {
		// ObjectMapper mapper = new ObjectMapper();
		List<ItemDocument> items = new LinkedList<>();
		JSONArray hits = obj.getJSONObject("suggest").getJSONArray("item-suggest").getJSONObject(0).getJSONArray("options");
		for (int i = 0; i < hits.length(); i++) {
			JSONObject hit = hits.getJSONObject(i);
			JSONObject source = hit.getJSONObject("_source");
			// Item item = mapper.readValue(source.toString(), Item.class);
			ItemDocument item = createItem(source);
			items.add(item);
		}
		return items;
	}

	@Override
	public String convertToSearchRequest(SearchRequest request) {
		
		String query = new String(Constants.SEARCH_ZIP_TITLE_QUERY);
		query = query.replace(Constants.WILDCARD_KEY, Constants.TITLE);
		query = query.replace(Constants.WILDCARD_VALUE, "*"+request.getSearch().trim()+"*");
		
		query = query.replace(Constants.FILTER_KEY, Constants.ZIP);
		
		JSONObject obj = new JSONObject(query);
		obj.getJSONObject("query").getJSONObject("bool").getJSONObject("filter").getJSONObject("term").put(Constants.ZIP, request.getZip());
		obj.put("size", request.getLimit());
		obj.put("from", request.getStart());
		return obj.toString();
	}
}
