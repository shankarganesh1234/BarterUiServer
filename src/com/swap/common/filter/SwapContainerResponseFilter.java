package com.swap.common.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.springframework.cache.support.SimpleCacheManager;

@Provider
public class SwapContainerResponseFilter implements ContainerResponseFilter {

	@Inject 
	SimpleCacheManager cacheManager;
	
	@Override
	public void filter(ContainerRequestContext arg0, ContainerResponseContext arg1) throws IOException {
		MultivaluedMap<String, Object> headers = arg1.getHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		//headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org		
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");		
		headers.add("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
	}
}
