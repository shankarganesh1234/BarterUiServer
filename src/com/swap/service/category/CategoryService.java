package com.swap.service.category;

import com.swap.models.category.CategoriesResponse;
import com.swap.models.category.CategoryRequest;
import com.swap.models.category.CategoryResponse;

public interface CategoryService {

	CategoryResponse getCategory(Long categoryId);
	CategoriesResponse getAllCategories();
	CategoryResponse createCategory(CategoryRequest category);
	CategoryResponse updateCategory(CategoryRequest category);
	CategoryResponse deleteCategory(Long categoryId);
}
