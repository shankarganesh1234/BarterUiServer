package com.swap.transformer.feedback;

import com.swap.entity.feedback.FeedbackEntity;
import com.swap.models.feedback.FeedbackRequest;

public interface FeedbackTransformer {
	FeedbackEntity createFeedbackEntity(FeedbackRequest feedbackRequest);
}
