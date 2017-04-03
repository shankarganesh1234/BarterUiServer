package com.swap.models.listing;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.swap.common.enums.ItemConditionEnum;
import com.swap.common.enums.ItemStageEnum;
import com.swap.entity.category.CategoryEntity;
import com.swap.entity.item.ImageEntity;
import com.swap.entity.location.LocationEntity;
import com.swap.entity.user.UserEntity;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	private String title;
	private Long itemId;
	private String description;
	private LocationEntity zipCode;
	private UserEntity userId;
	private CategoryEntity categoryId;
	private ImageEntity image_id;
	private ItemConditionEnum condition;
	private int quantity;
	private int numOfReviews;
	private int numOfInterests;
	private ItemStageEnum itemStage;
	private String story;

	public LocationEntity getZipCode() {
		return zipCode;
	}

	public void setZipCode(LocationEntity zipCode) {
		this.zipCode = zipCode;
	}

	public ImageEntity getImage_id() {
		return image_id;
	}

	public void setImage_id(ImageEntity image_id) {
		this.image_id = image_id;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [title=").append(title).append(", itemId=").append(itemId).append(", description=")
				.append(description).append(", zipCode=").append(zipCode).append(", userId=").append(userId)
				.append(", categoryId=").append(categoryId).append(", image_id=").append(image_id)
				.append(", condition=").append(condition).append(", quantity=").append(quantity)
				.append(", numOfReviews=").append(numOfReviews).append(", numOfInterests=").append(numOfInterests)
				.append(", itemStage=").append(itemStage).append(", story=").append(story).append("]");
		return builder.toString();
	}
}