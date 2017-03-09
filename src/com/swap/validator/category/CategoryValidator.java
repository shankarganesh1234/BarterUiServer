package com.swap.validator.category;

import com.swap.entity.category.CategoryEntity;
import com.swap.models.category.CategoryRequest;

public interface CategoryValidator {

	void validateInsertRequest(CategoryRequest category);
	void validateUpdateRequest(CategoryRequest category);
	void validateCategoryId(Long id);
	void validateResponse(CategoryEntity entity);
}
