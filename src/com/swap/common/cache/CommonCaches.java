package com.swap.common.cache;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;
import com.swap.common.constants.Constants;

@Configuration
@EnableCaching
public class CommonCaches {

	@Inject
	CommonCacheManager commonCacheManager;

	@Bean
	public CacheManager cacheManager() {

		SimpleCacheManager simpleCacheManager = commonCacheManager.getSimpleCacheManager();

		// category caches
		GuavaCache categoryCache = new GuavaCache(Constants.CACHE_CATEGORY,
				CacheBuilder.newBuilder().maximumSize(Constants.CACHE_CATEGORY_MAX_SIZE).build());
		GuavaCache categoriesCache = new GuavaCache(Constants.CACHE_CATEGORIES, CacheBuilder.newBuilder()
				.expireAfterAccess(Constants.CACHE_CATEGORIES_EXPIRE_AFTER, TimeUnit.MINUTES).build());

		simpleCacheManager.setCaches(Arrays.asList(categoryCache, categoriesCache));

		// start background thread for cache poke at regular intervals
		// startBackgroundThreadForCachePoke(reqResCache);
		return simpleCacheManager;
	}
}
