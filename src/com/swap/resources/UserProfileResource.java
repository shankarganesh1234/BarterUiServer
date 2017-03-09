package com.swap.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.swap.models.profile.UserProfileRequest;
import com.swap.models.profile.UserProfileResponse;
import com.swap.service.profile.UserProfileService;

@Path("userprofile")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserProfileResource {

	@Inject
	UserProfileService profileService;

	@Path("{userName}")
	@GET
	public UserProfileResponse getProfile(@PathParam("userName") String userName) {
		return profileService.getUserProfile(userName);
	}

	@POST
	public UserProfileResponse createUserProfile(UserProfileRequest userProfile) {
		return profileService.createUserProfile(userProfile);
	}

	@PUT
	public UserProfileResponse updateCategory(UserProfileRequest userProfile) {
		return profileService.updateUserProfile(userProfile);
	}

	@DELETE
	@Path("{userName}")
	public void deleteUser(@PathParam("userName") String userName) {
		profileService.deleteUserProfile(userName);
	}

}
