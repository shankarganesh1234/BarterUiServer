package com.swap.service.review;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.dao.review.ReviewDao;
import com.swap.entity.review.ReviewEntity;
import com.swap.models.review.ReviewRequest;
import com.swap.models.review.ReviewResponse;
import com.swap.models.review.ReviewsResponse;
import com.swap.transformer.review.ReviewTransformer;
import com.swap.validator.review.ReviewValidator;

@Service
public class ReviewServiceImpl implements ReviewService {

	private static final Logger logger = Logger.getLogger(ReviewServiceImpl.class);

	@Inject
	private ReviewDao reviewDao;

	@Inject
	private ReviewValidator reviewValidator;

	@Inject
	private ReviewTransformer reviewTransformer;

	@Override
	@Transactional
	public void createReview(ReviewRequest request) {
		try {
			// validate request
			reviewValidator.validateCreateReviewRequest(request);
			
			// create entity
			ReviewEntity entity = reviewTransformer.createEntityFromRequest(request);
			
			// invoke dao
			reviewDao.createReview(entity);
			
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.REVIEW_SERVICE_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.REVIEW_SERVICE_FAILURE);
		}
	}

	@Override
	@Transactional
	public void updateReview(ReviewRequest request, Long reviewId) {
		try {
			// validate request
			request.setReviewId(reviewId);
			reviewValidator.validateUpdateReviewRequest(request);
			
			// create entity
			ReviewEntity entity = reviewTransformer.createEntityFromRequest(request);
			
			// invoke dao
			reviewDao.updateReview(entity);
			
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.REVIEW_SERVICE_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.REVIEW_SERVICE_FAILURE);
		}

	}

	@Override
	@Transactional
	public ReviewResponse getReviewByReviewId(Long reviewId) {
		ReviewResponse response = null;
		try {
			ReviewEntity entity = reviewDao.getReviewByReviewId(reviewId);
			response = reviewTransformer.createResponse(entity);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.CREATE_INTEREST_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.CREATE_INTEREST_FAILURE);
		}
		return response;
	}

	@Override
	@Transactional
	public ReviewsResponse getReviewsForUser(Long userId) {
		ReviewsResponse response = null;
		try {
			List<ReviewEntity> entities = reviewDao.getReviewsForUser(userId);
			response = reviewTransformer.createResponses(entities);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.CREATE_INTEREST_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.CREATE_INTEREST_FAILURE);
		}
		return response;
	}

	@Override
	@Transactional
	public ReviewsResponse getReviewsByUser(Long userId) {
		ReviewsResponse response = null;
		try {
			List<ReviewEntity> entities = reviewDao.getReviewsByUser(userId);
			response = reviewTransformer.createResponses(entities);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.CREATE_INTEREST_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.CREATE_INTEREST_FAILURE);
		}
		return response;
	}

	@Override
	@Transactional
	public ReviewsResponse getReviewsForUserByUser(Long forUser, Long byUser) {
		ReviewsResponse response = null;
		try {
			List<ReviewEntity> entities = reviewDao.getReviewsForUserByUser(forUser, byUser);
			response = reviewTransformer.createResponses(entities);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.CREATE_INTEREST_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.CREATE_INTEREST_FAILURE);
		}
		return response;
	}

	@Override
	@Transactional
	public void deleteReviewByReviewId(Long id) {
		
		try {
			reviewDao.deleteReviewByReviewId(id);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.REVIEW_SERVICE_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.REVIEW_SERVICE_FAILURE);
		}
	}

}
