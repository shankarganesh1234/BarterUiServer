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

import com.swap.models.interest.InterestRequest;
import com.swap.models.interest.InterestResponse;
import com.swap.models.interest.InterestsResponse;
import com.swap.service.interest.InterestService;

@Path("/interest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InterestResource {

	@Inject
	private InterestService interestService;

	@POST
	public boolean createInterested(InterestRequest request) {
		interestService.createInterest(request);
		return true;
	}

	@PUT
	@Path("{interestId}")
	public void updateInterested(@PathParam("interestId") Long interestId, InterestRequest request) {
		request.setInterestId(interestId);
		interestService.updateInterest(request);
	}

	
	@DELETE
	@Path("/{interestId}")
	public void deleteInterest(@PathParam("interestId") Long interestId) {
		interestService.deleteInterest(interestId);
	}

	@GET
	@Path("/{interestId}")
	public InterestResponse getInterestById(@PathParam("interestId") Long interestId) {
		return interestService.getInterestById(interestId);
	}

	@GET
	@Path("interesteduser/{userId}")
	public InterestsResponse getInterestsByInterestedUser(@PathParam("userId") Long userId) {
		return interestService.getInterestsByInterestedUser(userId);
	}

	@GET
	@Path("originaluser/{userId}")
	public InterestsResponse getInterestedByItemOwnerUser(@PathParam("userId") Long userId) {
		return interestService.getInterestedByOriginalUser(userId);
	}

//	@GET
//	public List<InterestResponse> getInteresedByUserForUser(Long byUserId, Long forUserId) {
//		return null;
//	}
}
