package com.swap.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.swap.models.category.CategoriesResponse;
import com.swap.models.category.CategoryRequest;
import com.swap.models.category.CategoryResponse;
import com.swap.service.category.CategoryService;

@Path("category")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

	@Inject
	CategoryService categoryService;
	
	@Path("{categoryId}")
	@GET
	public CategoryResponse getCategory(@PathParam("categoryId") Long categoryId) {
		return categoryService.getCategory(categoryId);
	}
	
	@GET
	public CategoriesResponse getCategories() {
		return categoryService.getAllCategories();
	}
	
	@POST
	public CategoryResponse createCategory(CategoryRequest category) {
		return categoryService.createCategory(category);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CategoryResponse updateCategory(CategoryRequest category) {
		return categoryService.updateCategory(category);
	}
	
	@DELETE
	@Path("{categoryId}")
	public CategoryResponse deleteCategory(@PathParam("categoryId") Long categoryId) {
		return categoryService.deleteCategory(categoryId);
	}
}
