package com.swap.common.constants;

public class Constants {

	// request response cache
	public static final String REQ_RES_CACHE = "ReqResCache";
	public static final Integer CACHE_REQ_RES_EXPIRE_AFTER = 10;
	public static final Integer CACHE_REQ_RES_MAX_SIZE = 10;
	public static final Integer CACHE_EVICT_ASYNC_THREAD_POOL_SIZE = 5;
	public static final Integer CACHE_INITIAL_DELAY = 60;
	public static final Integer CACHE_FIXED_RATE = 60;

	// category cache
	public static final String CACHE_CATEGORY = "category";
	public static final String CACHE_CATEGORIES = "categories";
	public static final Integer CACHE_CATEGORIES_EXPIRE_AFTER = 30;
	public static final Integer CACHE_CATEGORY_MAX_SIZE = 10;
	public static final String CATEGORY_ID = "categoryId";

	// elasticsearch
	//public static final String ELASTICSEARCH_BASE_URL = "http://54.202.10.115:9200/";
	public static final String ELASTICSEARCH_BASE_URL = "http://localhost:9200/";
	public static final String ELASTICSEARCH_INDEX_NAME = "swap/";
	public static final String ELASTICSEARCH_INDEX_TYPE_ITEM = "item/";
	public static final String ACCEPT_HEADER = "Accept";
	public static final String CONTENT_TYPE_HEADER = "Content-type";
	public static final String ELASTICSEARCH_SEARCH_QUERY_KEY = "_search?q=";

	// ERROR CODES
	public static final Integer INTERNAL_SERVER_ERROR = 500;

	// Cloudinary
	public static final String CLOUD_NAME = "cloud_name";
	public static final String MY_CLOUD_NAME = "swapimage";
	public static final String API_KEY = "api_key";
	public static final String MY_API_KEY = "522425139524674";
	public static final String API_SECRET = "api_secret";
	public static final String MY_API_SECRET = "yHvoLV44z0M6AMWn4Z54VQlFlIY";
}
