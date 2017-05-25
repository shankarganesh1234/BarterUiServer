package com.swap.service.listing;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.dao.item.ItemDao;
import com.swap.entity.item.ItemEntity;
import com.swap.models.listing.Item;
import com.swap.models.listing.ItemRequest;
import com.swap.transformer.listing.ItemTransformer;
import com.swap.validator.listing.ItemValidator;
import com.swap.websocket.NotificationSessionHandler;

@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger logger = Logger.getLogger(ItemServiceImpl.class);

	@Inject
	private ItemDao listingDao;

	@Inject
	private ItemTransformer listingTransformer;

	@Inject
	private ItemValidator listingValidator;
	
	@Transactional
	@Override
	public Item getListingByItemId(Long itemId) {
		try {
			listingValidator.validateItemId(itemId);
			ItemEntity entity = listingTransformer.createListingEntityFromId(String.valueOf(itemId), true);
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
	public List<Item> getListingsByUserId(String userId) {
		try {
			listingValidator.validateUserId(userId);
			ItemEntity entity = listingTransformer.createListingEntityFromId(userId, false);
			List<ItemEntity> ListingEntities = listingDao.getListingsByUserId(entity);
			
			// mock : delete later
			NotificationSessionHandler.sendToSession(userId, "1,2,3,4,5");
			//
			
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
	public Item listing(ItemRequest listingRequest) {
		Item item = null;
		try {
			listingValidator.validateListingRequest(listingRequest);
			item = listingTransformer.convertRequestToItem(listingRequest, "POST");
			ItemEntity entity = listingTransformer.createListingEntity(item);
			listingDao.createListing(entity);
			item.setItemId(entity.getItemId());
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
		return item;
	}

	@Transactional
	@Override
	public Item updateListing(ItemRequest listingRequest) {
		Item item = null;
		try {
			listingValidator.validateUpdateRequest(listingRequest);
			item = listingTransformer.convertRequestToItem(listingRequest, "PUT");
			ItemEntity entity = listingTransformer.createListingEntity(item);
			listingDao.updateListing(entity);
			item.setItemId(entity.getItemId());
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
		return item;
	}

	@Transactional
	@Override
	public void deleteListing(Long itemId) {
		try {
			listingValidator.validateItemId(itemId);
			ItemEntity entity = listingTransformer.createListingEntityFromId(String.valueOf(itemId), true);
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
	public List<Long> getListingIdsByUserId(String userId) {
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