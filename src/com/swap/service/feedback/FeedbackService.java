package com.swap.service.feedback;

import com.swap.models.feedback.FeedbackRequest;

public interface FeedbackService {

	boolean sendFeedback(FeedbackRequest request);
}
