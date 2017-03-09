package com.swap.models.category;

import java.util.List;

public class CategoriesResponse {

	private List<CategoryResponse> categories;

	public List<CategoryResponse> getCategories() {
		return categories;
	}
	
	public void setCategories(List<CategoryResponse> categories) {
		this.categories = categories;
	}
}
