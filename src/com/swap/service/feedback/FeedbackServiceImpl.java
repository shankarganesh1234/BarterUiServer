package com.swap.service.feedback;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.dao.feedback.FeedbackDao;
import com.swap.models.feedback.FeedbackRequest;
import com.swap.transformer.feedback.FeedbackTransformer;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Inject
	FeedbackDao feedbackDao;
	
	@Inject
	FeedbackTransformer feedbackTransformer;
	
	@Override
	@Transactional
	public boolean sendFeedback(FeedbackRequest request) {
		
		boolean result = false;
		try {
			feedbackDao.insertFeedback(feedbackTransformer.createFeedbackEntity(request));
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

}
