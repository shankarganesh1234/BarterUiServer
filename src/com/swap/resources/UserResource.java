package com.swap.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.swap.models.user.UserResponse;
import com.swap.service.user.UserService;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@Inject
	UserService userService;

	@GET
	@Path("/{userId}")
	public UserResponse getUser(@PathParam("userId") String userId) {
		return userService.getUserById(userId);
	}

	@DELETE
	@Path("/{userId}")
	public Boolean deleteUser(@PathParam("userId") String userId) {
		return userService.deleteUserByUserId(userId);
	}

}
