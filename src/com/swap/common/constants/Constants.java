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
	public static final String ELASTICSEARCH_INDEX_NAME = "swap";
	public static final String ELASTICSEARCH_INDEX_TYPE_ITEM = "item";
	public static final String ACCEPT_HEADER = "Accept";
	public static final String CONTENT_TYPE_HEADER = "Content-type";
	public static final String ELASTICSEARCH_SEARCH_QUERY_KEY = "_search";

	// ERROR CODES
	public static final Integer INTERNAL_SERVER_ERROR = 500;

	// Cloudinary
	public static final String CLOUD_NAME = "cloud_name";
	public static final String MY_CLOUD_NAME = "damwv9ygo";
	public static final String API_KEY = "api_key";
	public static final String MY_API_KEY = "165899739553247";
	public static final String API_SECRET = "api_secret";
	public static final String MY_API_SECRET = "9GxNEKNAbmPp7Wj7f3kpvm5UW7w";
	
	// DSL

	public static final String TITLE = "title";
	public static final String ZIP = "zipCode";
	public static final String BLANK = "";
	public static final String FILTER = "filter";
	public static final String TERM = "term";
	public static final String QUERY = "query";
	public static final String BOOL = "bool";
	public static final String FROM = "from";
	public static final String SIZE = "size";
	public static final String CLUSTER_NAME = "cluster.name";
	public static final String CLUSTER_NAME_VALUE = "bartery-cluster";
	public static final String ELASTICSEARCH_HOST = "localhost";
	
	
	// pagination 
	public static final Integer LIMIT = 8;
	public static final Integer START = 0;
	public static final Integer PAGE = 1;
	
	// login
	public static final String PROVIDER_ID = "facebook";
	public static final String AUTHORIZATION = "Authorization";
	
}
