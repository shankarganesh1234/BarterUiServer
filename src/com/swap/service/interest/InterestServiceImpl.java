package com.swap.service.interest;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.common.enums.NotificationStatusEnum;
import com.swap.common.enums.NotificationTypeEnum;
import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.dao.interest.InterestDao;
import com.swap.entity.interest.InterestEntity;
import com.swap.models.interest.InterestRequest;
import com.swap.models.interest.InterestResponse;
import com.swap.models.interest.InterestsResponse;
import com.swap.models.notification.NotificationModel;
import com.swap.service.listing.ItemService;
import com.swap.service.notification.NotificationService;
import com.swap.transformer.interest.InterestTransformer;
import com.swap.validator.interest.InterestValidator;

@Service
public class InterestServiceImpl implements InterestService {

	private static final Logger logger = Logger.getLogger(InterestServiceImpl.class);

	@Inject
	private InterestValidator interestValidtor;

	@Inject
	private InterestDao interestDao;
	
	@Inject
	private ItemService listingService;

	@Inject
	private InterestTransformer interestTransformer;
	
	@Inject
	private NotificationService notificationService;
	
	@Override
	@Transactional
	public void createInterest(InterestRequest request) {

		try {
			// validate request
			interestValidtor.validateCreateInterestRequest(request);
			// validate request data integrity
			validateRequestIntegrity(request);
			
			if (request.getOneSidedInterestFlag()) {
				createLike(request);
			} else {
				createDislike(request);
			}
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
	}

	@Override
	@Transactional
	public void updateInterest(InterestRequest request) {
		try {
			interestValidtor.validateUpdateInterestRequest(request);
			InterestEntity entity = interestTransformer.createUpdateEntity(request);
			interestDao.updateInterested(entity);
			
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

	}
	
	@Override
	@Transactional
	public void deleteInterest(Long interestId) {
		try {
			interestDao.deleteInterest(interestId);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.DELETE_INTEREST_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.DELETE_INTEREST_FAILURE);
		}
	}

	@Override
	@Transactional
	public InterestResponse getInterestById(Long id) {

		InterestResponse interestResponse = null;
		try {
			InterestEntity entity = interestDao.getInterestById(id);
			interestResponse = interestTransformer.createResponseFromEntity(entity);
			notificationService.updateStatusToRead(String.valueOf(id), entity.getOriginalUser().getUserId());
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
		return interestResponse;
	}

	@Override
	@Transactional
	public InterestsResponse getInterestsByInterestedUser(Long userId) {
		InterestsResponse interests = null;
		try {
			List<InterestEntity> interestEntities = interestDao.getInterestsByInterestedUser(userId);
			List<InterestResponse> interestResonseList = interestTransformer.createResponseListFromEntityList(interestEntities);
			interests = new InterestsResponse();
			interests.setInterests(interestResonseList);
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
		return interests;
	}

	@Override
	@Transactional
	public InterestsResponse getInterestedByOriginalUser(Long userId) {
		InterestsResponse interests = null;
		try {
			List<InterestEntity> interestEntities = interestDao.getInterestedByItemOwnerUser(userId);
			List<InterestResponse> interestResonseList = interestTransformer.createResponseListFromEntityList(interestEntities);
			interests = new InterestsResponse();
			interests.setInterests(interestResonseList);
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
		return interests;
	}
	
	/**
	 * Validate data integrity for request parameters
	 * @param request
	 */
	private void validateRequestIntegrity(InterestRequest request) {
		
		String originalItemUser = request.getOriginalUser();
		Long originalItemId = request.getOriginalItem();
		
		String interestedUser = request.getInterestedUser();
		List<Long> swappableItemIds = request.getSwappableItemIds();
		
		List<Long> originalUserItems = listingService.getListingIdsByUserId(originalItemUser);
		interestValidtor.validateItemBelongsToUser(originalUserItems, originalItemId);
		
		List<Long> interestedUserItems = listingService.getListingIdsByUserId(interestedUser);
		for(Long itemId : swappableItemIds) {
			interestValidtor.validateItemBelongsToUser(interestedUserItems, itemId);
		}
	}
	
	/**
	 * Create a positive interest, setting the one sided interest flag to TRUE
	 * @param request
	 */
	private void createLike(InterestRequest request) {
		
			List<InterestEntity> interestEntities = interestTransformer.createEntityList(request);
			if (interestEntities != null && !interestEntities.isEmpty()) {
				for (InterestEntity entity : interestEntities) {
					InterestEntity interest = interestDao.createInterested(entity);
					
					// section for generating notification for user
					notificationService.createNotification(String.valueOf(interest.getInterestId()), interest.getOriginalUser().getUserId(), NotificationStatusEnum.UNREAD, NotificationTypeEnum.MY_OFFERS);
				}
			}
	}
	
	/**
	 * Create a dislike, setting the one sided interest flag to false
	 * @param request
	 */
	private void createDislike(InterestRequest request) {
		List<InterestEntity> interestEntities = interestTransformer.createEntityList(request);
		InterestEntity entity = interestEntities.get(0);
		interestDao.createInterested(entity);
	}

	@Override
	@Transactional
	public InterestsResponse getInterests(String userId, String itemId, boolean isOwner) {
		InterestsResponse interests = null;
		try {
			List<InterestEntity> interestEntities = interestDao.getInterests(userId, itemId, isOwner);
			List<InterestResponse> interestResonseList = interestTransformer.createResponseListFromEntityList(interestEntities);
			interests = new InterestsResponse();
			interests.setInterests(interestResonseList);
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
		return interests;
	}
}
