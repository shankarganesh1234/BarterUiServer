package com.swap.common.constants;

public class Constants {

	// prop names
	public static final String ELASTICSEARCH_BASEURL = "elasticsearch.baseurl";
	public static final String ELASTICSEARCH_CLUSTERNAME = "elasticsearch.clustername";
	public static final String ELASTICSEARCH_INDEXNAME = "elasticsearch.indexname";
	public static final String ELASTICSEARCH_INDEXTYPE = "elasticsearch.indextype";
	public static final String ELASTICSEARCH_HOST = "elasticsearch.host";
	public static final String ELASTICSEARCH_CHAT_INDEXNAME = "elasticsearch.chat.indexname";
	public static final String ELASTICSEARCH_CHAT_INDEXTYPE = "elasticsearch.chat.indextype";
	
	public static final String ELASTICSEARCH_LOG_CLUSTERNAME = "elasticsearch.log.clustername";
	public static final String ELASTICSEARCH_LOG_HOST = "elasticsearch.log.host";
	public static final String ELASTICSEARCH_LOG_INDEXNAME = "elasticsearch.log.indexname";
	public static final String ELASTICSEARCH_LOG_INDEXTYPE = "elasticsearch.log.indextype";
	
	
	// category cache
	public static final String CACHE_CATEGORY = "category";
	public static final String CACHE_CATEGORIES = "categories";
	public static final Integer CACHE_CATEGORIES_EXPIRE_AFTER = 30;
	public static final Integer CACHE_CATEGORY_MAX_SIZE = 10;

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
	public static final String TITLE_LOWERCASE = "titleLowerCase";
	public static final String ZIP = "zipCode";
	public static final String BLANK = "";
	public static final String CLUSTER_NAME = "cluster.name";
	
	// pagination 
	public static final Integer LIMIT = 20;
	public static final Integer START = 0;
	public static final Integer PAGE = 1;
	
	
	//search
	public static final String ALL_CATEGORIES = "All categories";
	public static final String CATEGORY_NAME = "categoryName";
	
	//location services
	public static final String GOOGLE_LOCATION_API_URL = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=";
	public static final String LOCATION = "location";
	public static final String DEFAULT_SEARCH_DISTANCE = "10";
	public static final String GEO_FILE_NAME = "geolocation.txt";
	public static final String ENCODING_UTF = "UTF-8";
	public static final String RESULTS = "results";
	public static final String GEOMETRY = "geometry";
	public static final String LAT = "lat";
	public static final String LNG = "lng";
	public static final String DEFAULT_SORTFIELD = "upsertDate";
	
	// sendbird 
	public static final String SENDBIRD_BASE_URL = "https://api.sendbird.com/v3/";

	// facebook
	public static final String PROVIDER_ID = "facebook";
	public static final String AUTHORIZATION = "Authorization";
	public static final String FB_LONG_TOKEN = "https://graph.facebook.com/oauth/access_token";
	public static final String GRANT_TYPE = "grant_type";
	public static final String EXCHANGE_TOKEN = "fb_exchange_token";
	public static final String CLIENT_ID = "client_id";
	public static final String CLIENT_SECRET = "client_secret";
	public static final String FB_CLIENT_ID_VALUE = "422821098053082";
	public static final String FB_CLIENT_SECRET_VALUE = "37a589b26576cebe77529d7f24ba7d3b";

}
