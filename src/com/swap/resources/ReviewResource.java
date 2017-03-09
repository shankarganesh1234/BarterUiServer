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

import com.swap.models.review.ReviewRequest;
import com.swap.models.review.ReviewResponse;
import com.swap.models.review.ReviewsResponse;
import com.swap.service.review.ReviewService;

@Path("/review")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource {
	
	@Inject
	private ReviewService reviewService;

	@POST
	public void createReview(ReviewRequest request) {
		reviewService.createReview(request);
	}

	@PUT
	@Path("{reviewId}")
	public void updateReview(@PathParam("reviewId") Long reviewId, ReviewRequest request) {
		reviewService.updateReview(request, reviewId);
	}

	
	@DELETE
	@Path("/{reviewId}")
	public void deleteReview(@PathParam("reviewId") Long reviewId) {
		reviewService.deleteReviewByReviewId(reviewId);
	}

	@GET
	@Path("/{reviewId}")
	public ReviewResponse getReviewById(@PathParam("reviewId") Long reviewId) {
		return reviewService.getReviewByReviewId(reviewId);
	}

	@GET
	@Path("byuser/{userId}")
	public ReviewsResponse getReviewsByUser(@PathParam("userId") Long userId) {
		return reviewService.getReviewsByUser(userId);
	}

	@GET
	@Path("foruser/{userId}")
	public ReviewsResponse getReviewsForUser(@PathParam("userId") Long userId) {
		return reviewService.getReviewsForUser(userId);
	}
	
	@GET
	@Path("foruserbyuser/{forUser}/{byUser}")
	public ReviewsResponse getReviewsForUser(@PathParam("forUser") Long forUser, @PathParam("byUser") Long byUser) {
		return reviewService.getReviewsForUserByUser(forUser, byUser);
	}
}
