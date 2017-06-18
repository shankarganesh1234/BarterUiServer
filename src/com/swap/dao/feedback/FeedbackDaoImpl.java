package com.swap.dao.feedback;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.swap.common.CommonUtil;
import com.swap.entity.feedback.FeedbackEntity;

@Service
public class FeedbackDaoImpl implements FeedbackDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insertFeedback(FeedbackEntity feedbackEntity) {
		feedbackEntity.setInsertDate(CommonUtil.getCurrentDate());
		sessionFactory.getCurrentSession().save(feedbackEntity);
	}
}
