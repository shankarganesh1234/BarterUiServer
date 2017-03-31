package com.swap.common.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.swap.common.auth.AuthenticatedResources;
import com.swap.common.components.CommonRequestComponent;
import com.swap.common.constants.Constants;
import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
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

	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException {
		logger.debug("Entering SwapContainerRequestFilter.filter()");

		if (!isAuthenticated(containerRequestContext)) {
			throw new SwapException(ErrorEnum.INVALID_ACCESS_TOKEN);
		}
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
}
