package com.swap.transformer.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.swap.entity.category.CategoryEntity;
import com.swap.models.category.CategoryRequest;
import com.swap.models.category.CategoryResponse;

@Service
public class CategoryTransformerImpl implements CategoryTransformer {

	@Override
	public CategoryEntity createCategoryEntity(CategoryRequest category) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setCategoryName(category.getCategoryName());
		return categoryEntity;
	}
	
	@Override
	public CategoryEntity updateCategoryEntity(CategoryRequest category) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setCategoryId(category.getCategoryId());
		categoryEntity.setCategoryName(category.getCategoryName());
		return categoryEntity;
	}

	@Override
	public CategoryResponse createCategoryResponse(CategoryEntity entity) {
		CategoryResponse category = new CategoryResponse();
		BeanUtils.copyProperties(entity, category);
		return category;
	}

	@Override
	public CategoryEntity createCategoryEntityFromId(Long id) {
		CategoryEntity entity = new CategoryEntity();
		entity.setCategoryId(id);
		return entity;
	}

	@Override
	public List<CategoryResponse> convertToCategoryList(List<CategoryEntity> categoryEntities) {
		List<CategoryResponse> categories = new ArrayList<>(categoryEntities.size());
		for(CategoryEntity entity : categoryEntities) {
			categories.add(createCategoryResponse(entity));
		}
		return categories;
	}
}
