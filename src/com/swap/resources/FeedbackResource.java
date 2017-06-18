package com.swap.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.swap.models.feedback.FeedbackRequest;
import com.swap.service.feedback.FeedbackService;

@Path("/feedback")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FeedbackResource {

	@Inject
	FeedbackService feedbackService;
	
	@POST
	public boolean sendFeedback(FeedbackRequest feedbackRequest) {
		return feedbackService.sendFeedback(feedbackRequest);
	}
}
