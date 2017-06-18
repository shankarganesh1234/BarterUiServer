package com.swap.models.listing;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.swap.common.enums.ItemConditionEnum;
import com.swap.common.enums.ItemStageEnum;
import com.swap.entity.category.CategoryEntity;
import com.swap.entity.item.ImageEntity;
import com.swap.entity.user.UserEntity;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	private String title;
	private Long itemId;
	private String description;
	private Long zipCode;
	private UserEntity userId;
	private CategoryEntity categoryId;
	private List<ImageEntity> images;
	private ItemConditionEnum condition;
	private int quantity;
	private int numOfReviews;
	private int numOfInterests;
	private ItemStageEnum itemStage;
	private String story;

	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonIgnore
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public CategoryEntity getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(CategoryEntity categoryId) {
		this.categoryId = categoryId;
	}

	public ItemConditionEnum getCondition() {
		return condition;
	}

	public void setCondition(ItemConditionEnum condition) {
		this.condition = condition;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getNumOfReviews() {
		return numOfReviews;
	}

	public void setNumOfReviews(int numOfReviews) {
		this.numOfReviews = numOfReviews;
	}

	public int getNumOfInterests() {
		return numOfInterests;
	}

	public void setNumOfInterests(int numOfInterests) {
		this.numOfInterests = numOfInterests;
	}

	public ItemStageEnum getItemStage() {
		return itemStage;
	}

	public void setItemStage(ItemStageEnum itemStage) {
		this.itemStage = itemStage;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public List<ImageEntity> getImages() {
		return images;
	}

	public void setImages(List<ImageEntity> images) {
		this.images = images;
	}		
}