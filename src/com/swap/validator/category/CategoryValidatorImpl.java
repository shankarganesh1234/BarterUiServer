package com.swap.validator.category;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.swap.common.error.ErrorEnum;
import com.swap.common.exceptions.SwapException;
import com.swap.entity.category.CategoryEntity;
import com.swap.models.category.CategoryRequest;

@Service
public class CategoryValidatorImpl implements CategoryValidator {

	/**
	 * Validate the category request
	 */
	@Override
	public void validateInsertRequest(CategoryRequest category) {
	
		if(category == null)
			throw new SwapException(ErrorEnum.INVALID_CATEGORY_REQUEST);
		
		validateCategoryName(category.getCategoryName());
	}

	/**
	 * Validate the category request
	 */
	@Override
	public void validateUpdateRequest(CategoryRequest category) {
	
		if(category == null)
			throw new SwapException(ErrorEnum.INVALID_CATEGORY_REQUEST);
		
		validateCategoryId(category.getCategoryId());
		validateCategoryName(category.getCategoryName());
		
	}
	
	/**
	 * Validate the category id
	 */
	public void validateCategoryId(Long id) {
		if(id == null) {
			throw new SwapException(ErrorEnum.INVALID_CATEGORY_ID);
		}
	}
	
	/**
	 * Validate the category name
	 * @param categoryName
	 */
	public void validateCategoryName(String categoryName) {
		if(categoryName == null || StringUtils.isEmpty(categoryName)) {
			throw new SwapException(ErrorEnum.INVALID_CATEGORY_NAME);
		}
	}

	/**
	 * Validate the category response
	 */
	@Override
	public void validateResponse(CategoryEntity entity) {
		if(entity == null) {
			throw new SwapException(ErrorEnum.CATEGORY_DAO_ERROR);
		}
	}

}
