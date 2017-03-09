package com.swap.dao.category;

import java.util.List;

import com.swap.entity.category.CategoryEntity;

public interface CategoryDao {

	CategoryEntity getCategory(CategoryEntity categoryEntity);
	CategoryEntity createCategory(CategoryEntity categoryEntity);
	CategoryEntity updateCategory(CategoryEntity categoryEntity);
	CategoryEntity deleteCategory(CategoryEntity categoryEntity);
	List<CategoryEntity> getAllCategories();
}
