package com.swap.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.social.connect.UserProfile;

import com.swap.models.login.LoginRequest;
import com.swap.service.login.LoginService;

@Path("login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

	@Inject
	private LoginService loginService;
	
	@Path("{providerId}")
	@POST
	public UserProfile socialLogin(@PathParam("providerId") String providerId, LoginRequest request) {
		return loginService.connectUser(request, providerId);
	}

	
}
