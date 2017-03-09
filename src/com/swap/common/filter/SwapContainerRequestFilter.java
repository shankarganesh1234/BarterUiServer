package com.swap.common.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.swap.common.components.CommonRequestComponent;

@Provider
public class SwapContainerRequestFilter implements ContainerRequestFilter {

	private static final Logger logger = Logger.getLogger(SwapContainerRequestFilter.class);
	
	@Inject 
	CommonRequestComponent reqComponent;
	
	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException {
		logger.debug("Entering SwapContainerRequestFilter.filter()");
		setLocale(containerRequestContext);
		logger.debug("Exiting SwapContainerRequestFilter.filter()");
	}
	
	/**
	 * Set the request locale
	 * @param containerRequest
	 */
	private void setLocale(ContainerRequestContext containerRequest) {
		
		if(containerRequest == null)
			return;
		
		if(!CollectionUtils.isEmpty(containerRequest.getAcceptableLanguages())) {
			reqComponent.setLocale(containerRequest.getAcceptableLanguages().get(0));
		}
	}
}
