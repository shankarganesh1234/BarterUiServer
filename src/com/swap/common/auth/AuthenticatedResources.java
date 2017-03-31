package com.swap.common.auth;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class AuthenticatedResources {

	// contains the key:uri and value:method type for authenticated resources
	// if present in the map, then access token is mandatory and checked against service provider
	private Map<String, String> authenicatedResourcesMap = new HashMap<>();

	public Map<String, String> getAuthenicatedResourcesMap() {
		return authenicatedResourcesMap;
	}

	public void setAuthenicatedResourcesMap(Map<String, String> authenicatedResourcesMap) {
		this.authenicatedResourcesMap = authenicatedResourcesMap;
	}
	
	@PostConstruct
	public void init() {
		authenicatedResourcesMap.put("listing", "POST,PUT,DELETE");
	}
}
