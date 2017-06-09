package com.swap.transformer.feedback;

import org.springframework.stereotype.Service;

import com.swap.entity.feedback.FeedbackEntity;
import com.swap.models.feedback.FeedbackRequest;

@Service
public class FeedbackTransformerImpl implements FeedbackTransformer {

	@Override
	public FeedbackEntity createFeedbackEntity(FeedbackRequest feedbackRequest) {
		FeedbackEntity entity = new FeedbackEntity();
		entity.setName(feedbackRequest.getName());
		entity.setEmail(feedbackRequest.getEmail());
		entity.setMessage(feedbackRequest.getMessage());
		return entity;
	}
}
