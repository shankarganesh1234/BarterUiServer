package com.swap.service.listing;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.dao.listing.ListingDao;
import com.swap.entity.listing.ListingEntity;
import com.swap.models.listing.Item;
import com.swap.models.listing.ListingRequest;
import com.swap.transformer.listing.ListingTransformer;
import com.swap.validator.listing.ListingValidator;

@Service
public class ListingServiceImpl implements ListingService {

	private static final Logger logger = Logger.getLogger(ListingServiceImpl.class);

	@Inject
	private ListingDao listingDao;

	@Inject
	private ListingTransformer listingTransformer;

	@Inject
	private ListingValidator listingValidator;

	@Transactional
	@Override
	public Item getListingByItemId(Long itemId) {
		try {
			listingValidator.validateItemId(itemId);
			ListingEntity entity = listingTransformer.createListingEntityFromId(itemId, true);
			entity = listingDao.getListingByItemId(entity);
			listingValidator.validateResponse(entity);
			return listingTransformer.createaListingModel(entity);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_LISTING_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_LISTING_FAILURE);
		}
	}

	@Transactional
	@Override
	public List<Item> getListingsByUserId(Long userId) {
		try {
			listingValidator.validateUserId(userId);
			ListingEntity entity = listingTransformer.createListingEntityFromId(userId, false);
			List<ListingEntity> ListingEntities = listingDao.getListingsByUserId(entity);
			return listingTransformer.convertFromEntitiesToItemList(ListingEntities);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORY_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORY_FAILURE);
		}
	}

	@Transactional
	@Override
	public void listing(ListingRequest listingRequest) {
		try {
			listingValidator.validateListingRequest(listingRequest);
			Item item = listingTransformer.convertRequestToItem(listingRequest, "POST");
			ListingEntity entity = listingTransformer.createListingEntity(item);
			listingDao.createListing(entity);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORY_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORY_FAILURE);
		}
	}

	@Transactional
	@Override
	public void updateListing(ListingRequest listingRequest) {
		try {
			listingValidator.validateUpdateRequest(listingRequest);
			Item item = listingTransformer.convertRequestToItem(listingRequest, "PUT");
			ListingEntity entity = listingTransformer.createListingEntity(item);
			listingDao.updateListing(entity);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORY_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORY_FAILURE);
		}
	}

	@Transactional
	@Override
	public void deleteListing(Long itemId) {
		try {
			listingValidator.validateItemId(itemId);
			ListingEntity entity = listingTransformer.createListingEntityFromId(itemId, true);
			listingDao.deleteListing(entity);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORY_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORY_FAILURE);
		}
	}

	@Override
	@Transactional
	public List<Long> getListingIdsByUserId(Long userId) {
		try {
			listingValidator.validateUserId(userId);
			return listingDao.getListingIdsByUserId(userId);
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORY_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORY_FAILURE);
		}
	}
}