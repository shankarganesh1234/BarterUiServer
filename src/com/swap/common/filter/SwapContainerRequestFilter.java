package com.swap.common.filter;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.util.CollectionUtils;

import com.swap.common.auth.AuthenticatedResources;
import com.swap.common.components.CommonRequestComponent;
import com.swap.common.components.ElasticLogTransportClient;
import com.swap.common.constants.Constants;
import com.swap.models.log.LogRequest;
import com.swap.service.login.LoginService;

@Provider
public class SwapContainerRequestFilter implements ContainerRequestFilter {

	private static final Logger logger = Logger.getLogger(SwapContainerRequestFilter.class);

	@Inject
	CommonRequestComponent reqComponent;

	@Inject
	AuthenticatedResources authenticatedResources;

	@Inject
	private LoginService loginService;

	@Context
	private HttpServletRequest httpServletRequest;

	@Inject
	private ElasticLogTransportClient elasticLogTransportClient;

	@Inject
	private PropertiesFactoryBean envProps;

	ObjectMapper mapper = new ObjectMapper(); // create once, reuse

	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException {
		logger.debug("Entering SwapContainerRequestFilter.filter()");

		// log the request
		logRequest(containerRequestContext);
		
		// if (!isAuthenticated(containerRequestContext)) {
		// throw new SwapException(ErrorEnum.INVALID_ACCESS_TOKEN);
		// }
		setLocale(containerRequestContext);
		logger.debug("Exiting SwapContainerRequestFilter.filter()");
	}

	/**
	 * Check access token for authenticated resources
	 */
	private boolean isAuthenticated(ContainerRequestContext containerRequestContext) {
		boolean isAuthenticated = false;

		String path = containerRequestContext.getUriInfo().getPath();
		String method = containerRequestContext.getMethod();

		if (path == null)
			return isAuthenticated;

		if (authenticatedResources.getAuthenicatedResourcesMap().containsKey(path)) {
			String methods = authenticatedResources.getAuthenicatedResourcesMap().get(path);

			if (!methods.contains(method)) {
				// no need for auth
				return true;
			}
			// secured resource, needs to check access token
			String accessToken = containerRequestContext.getHeaderString(Constants.AUTHORIZATION);
			if (accessToken == null || accessToken.isEmpty()) {
				// need token in authorization header
				return isAuthenticated;
			}

			if (loginService.hasExpired(Constants.PROVIDER_ID, accessToken)) {
				return isAuthenticated;
			}

			if (loginService.test(Constants.PROVIDER_ID, accessToken)) {
				isAuthenticated = true;
			}

		} else {
			isAuthenticated = true;
		}
		return isAuthenticated;
	}

	/**
	 * Set the request locale
	 * 
	 * @param containerRequest
	 */
	private void setLocale(ContainerRequestContext containerRequest) {

		if (containerRequest == null)
			return;

		if (!CollectionUtils.isEmpty(containerRequest.getAcceptableLanguages())) {
			reqComponent.setLocale(containerRequest.getAcceptableLanguages().get(0));
		}
	}

	/**
	 * Log every request to Elasticsearch log index
	 * @param containerRequestContext
	 */
	private void logRequest(ContainerRequestContext containerRequestContext) {
		try {

			String path = containerRequestContext.getUriInfo().getPath();
			String method = containerRequestContext.getMethod();
			String clientIp = httpServletRequest.getRemoteAddr();
			LogRequest request = new LogRequest(method, path, clientIp, new Date());

			byte[] logSource = mapper.writeValueAsBytes(request);

			elasticLogTransportClient.getTransportClient()
					.prepareIndex(envProps.getObject().getProperty(Constants.ELASTICSEARCH_LOG_INDEXNAME),
							envProps.getObject().getProperty(Constants.ELASTICSEARCH_LOG_INDEXTYPE))
					.setSource(logSource).get();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
