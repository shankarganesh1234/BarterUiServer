package com.swap.common.cache;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UniqueIdGenerator {

	private UUID uuid = null;
	
	public String getUniqueId() {
		uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
