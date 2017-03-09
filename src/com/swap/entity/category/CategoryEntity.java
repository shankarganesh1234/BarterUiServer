package com.swap.entity.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
@Entity
@Table(name="category")
public class CategoryEntity {
	
	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long categoryId;
	
	@Column(name="category_name")
	private String categoryName;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoryEntity [categoryId=").append(categoryId).append(", categoryName=").append(categoryName)
				.append("]");
		return builder.toString();
	}
}
