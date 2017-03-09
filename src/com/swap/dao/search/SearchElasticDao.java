package com.swap.dao.search;

import org.json.JSONObject;

public interface SearchElasticDao {

	JSONObject searchItems(String query);
	JSONObject autoComplete(JSONObject autoCompleteRequest);
}
