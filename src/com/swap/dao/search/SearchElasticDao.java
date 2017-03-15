package com.swap.dao.search;

import org.json.JSONObject;

public interface SearchElasticDao {

	JSONObject searchItems(String query, Long zip);
	JSONObject autoComplete(JSONObject autoCompleteRequest);
	JSONObject searchItems(String query);
}
