package com.swap.common.cache;

import javax.annotation.PostConstruct;

import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.stereotype.Component;

@Component
public class CommonCacheManager {

	private SimpleCacheManager simpleCacheManager;

	public SimpleCacheManager getSimpleCacheManager() {
		return simpleCacheManager;
	}
	
	@PostConstruct
	public void init() {
		simpleCacheManager = new SimpleCacheManager();
	}
}
