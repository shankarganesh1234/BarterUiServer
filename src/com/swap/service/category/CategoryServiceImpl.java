package com.swap.service.category;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swap.common.constants.Constants;
import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.dao.category.CategoryDao;
import com.swap.entity.category.CategoryEntity;
import com.swap.models.category.CategoriesResponse;
import com.swap.models.category.CategoryRequest;
import com.swap.models.category.CategoryResponse;
import com.swap.transformer.category.CategoryTransformer;
import com.swap.validator.category.CategoryValidator;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);
	
	@Inject
	private CategoryDao categoryDao;
	
	@Inject
	private CategoryTransformer categoryTransformer;
	
	@Inject
	private CategoryValidator categoryValidator;
	
	/**
	 * Get a category based on the category id
	 * Every request goes through the following steps 
	 * 1. Request validation
	 * 2. Calling DAO and getting response
	 * 3. Response validation
	 */
	@Transactional
	@Override
	@Cacheable(value = Constants.CACHE_CATEGORY)
	public CategoryResponse getCategory(Long categoryId) {
		
		CategoryResponse categoryRespose = null;
		
		try {
		// validate request
		categoryValidator.validateCategoryId(categoryId);
		
		// tx request
		CategoryEntity entity = categoryTransformer.createCategoryEntityFromId(categoryId);
		
		// call DAO
		entity = categoryDao.getCategory(entity);
		
		// validate response
		categoryValidator.validateResponse(entity);
		
		// tx response
		categoryRespose = categoryTransformer.createCategoryResponse(entity);
		
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
		return categoryRespose;
	}

	/**
	 * Get all categories
	 */
	@Transactional
	@Override
	@Cacheable(Constants.CACHE_CATEGORIES)
	public CategoriesResponse getAllCategories() {
		
		CategoriesResponse categories = null;
		try {
		// invoke DAO
		List<CategoryEntity> categoryEntities = categoryDao.getAllCategories();
		
		// tx response
		List<CategoryResponse> categrories = categoryTransformer.convertToCategoryList(categoryEntities);
		
		// set in response
		categories = new CategoriesResponse();
		categories.setCategories(categrories);
		
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORIES_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.GET_CATEGORIES_FAILURE);
		}
		return categories;
	}
	
	/**
	 * Create a new category
	 */
	@Transactional
	@Override
	@CacheEvict(value = Constants.CACHE_CATEGORY)
	public CategoryResponse createCategory(CategoryRequest category) {
		
		CategoryResponse categoryResponse = null;
		
		try {
		// validate request
		categoryValidator.validateInsertRequest(category);
		
		// tx request
		CategoryEntity entity = categoryTransformer.createCategoryEntity(category);
		
		// invoke dao
		entity = categoryDao.createCategory(entity);
		
		// validate response
		categoryValidator.validateResponse(entity);
		
		// tx response
		categoryResponse = categoryTransformer.createCategoryResponse(entity);
		
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.CREATE_CATEGORY_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.CREATE_CATEGORY_FAILURE);
		}
		return categoryResponse;
	}
	
	/**
	 * Update an existing category
	 */
	@Transactional
	@Override
	@CacheEvict(value = Constants.CACHE_CATEGORY)
	public CategoryResponse updateCategory(CategoryRequest category) {
		
		CategoryResponse categoryResponse = null;
		
		try {
		// validate request
		categoryValidator.validateUpdateRequest(category);
		
		// tx request
		CategoryEntity entity = categoryTransformer.updateCategoryEntity(category);
		
		// invoke dao
		entity = categoryDao.updateCategory(entity);
		
		// validate response
		categoryValidator.validateResponse(entity);
		
		// tx response
		categoryResponse = categoryTransformer.createCategoryResponse(entity);
		
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.UPDATE_CATEGORY_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.UPDATE_CATEGORY_FAILURE);
		}
		
		return categoryResponse;
	}
	
	/**
	 * Delete an existing category
	 */
	@Transactional
	@Override
	@CacheEvict(value = Constants.CACHE_CATEGORY)
	public CategoryResponse deleteCategory(Long categoryId) {
		
		CategoryResponse categoryResponse = null;
		
		try {
		// validate request
		categoryValidator.validateCategoryId(categoryId);
		
		// tx request
		CategoryEntity entity = categoryTransformer.createCategoryEntityFromId(categoryId);
		
		// invoke DAO
		entity = categoryDao.deleteCategory(entity);
		
		// validate response
		categoryValidator.validateResponse(entity);
		
		// tx response
		categoryResponse = categoryTransformer.createCategoryResponse(entity);
		
		} catch (SwapException ex) {
			logger.error(ex);
			throw ex;
		} catch (HibernateException ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.DELETE_CATEGORY_FAILURE);
		} catch (Exception ex) {
			logger.error(ex);
			throw new SwapException(ErrorEnum.DELETE_CATEGORY_FAILURE);
		}
		
		return categoryResponse;
	}

}
