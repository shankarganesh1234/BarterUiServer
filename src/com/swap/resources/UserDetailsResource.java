package com.swap.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.swap.models.user.UserResponse;
import com.swap.models.userdetails.UserDetailsRequest;
import com.swap.service.profile.UserDetailsService;

@Path("userdetails")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserDetailsResource {

	@Inject
	UserDetailsService userDetailsService;

	@POST
	public UserResponse createUserProfile(UserDetailsRequest userDetails) {
		return userDetailsService.createUserDetails(userDetails);
	}

	@PUT
	public UserResponse updateCategory(UserDetailsRequest userDetails) {
		return userDetailsService.updateUserDetails(userDetails);
	}
}
