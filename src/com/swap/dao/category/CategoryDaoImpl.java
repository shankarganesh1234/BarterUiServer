package com.swap.dao.category;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.swap.entity.category.CategoryEntity;

@Service
public class CategoryDaoImpl implements CategoryDao {

	@Inject
	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get an existing category
	 */
	@Override
	public CategoryEntity getCategory(CategoryEntity entity) {
		return sessionFactory.getCurrentSession().get(CategoryEntity.class, entity.getCategoryId());
	}

	/**
	 * Create a new existing category
	 */
	@Override
	public CategoryEntity createCategory(CategoryEntity categoryEntity) {
		sessionFactory.getCurrentSession().save(categoryEntity);
		return categoryEntity;
	}

	/**
	 * Update an existing category
	 */
	@Override
	public CategoryEntity updateCategory(CategoryEntity categoryEntity) {
		sessionFactory.getCurrentSession().update(categoryEntity);
		return categoryEntity;
	}

	/**
	 * Delete a category
	 */
	@Override
	public CategoryEntity deleteCategory(CategoryEntity categoryEntity) {
		sessionFactory.getCurrentSession().delete(categoryEntity);
		return categoryEntity;
	}

	@Override
	public List<CategoryEntity> getAllCategories() {
		@SuppressWarnings("unchecked")
		TypedQuery<CategoryEntity> query = sessionFactory.getCurrentSession().createQuery("FROM CategoryEntity");
		List<CategoryEntity> result = query.getResultList();
		return result;
	}
}
