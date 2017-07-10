package com.swap.common.components;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonObjMapper {

	private ObjectMapper objMapper = new ObjectMapper();
	
	/**
	 * Return an instance of object mapper, with ignore unknown properties
	 * @return
	 */
	public ObjectMapper getObjMapperIgnoreUnknown() {
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objMapper;
	}
}
