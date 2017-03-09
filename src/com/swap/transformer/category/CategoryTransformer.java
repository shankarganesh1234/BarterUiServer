package com.swap.transformer.category;

import java.util.List;

import com.swap.entity.category.CategoryEntity;
import com.swap.models.category.CategoryRequest;
import com.swap.models.category.CategoryResponse;

public interface CategoryTransformer {

	CategoryEntity createCategoryEntity(CategoryRequest categoryRequest);
	CategoryResponse createCategoryResponse(CategoryEntity entity);
	CategoryEntity updateCategoryEntity(CategoryRequest category);
	CategoryEntity createCategoryEntityFromId(Long id);
	List<CategoryResponse> convertToCategoryList(List<CategoryEntity> categoriesEntities);
}
