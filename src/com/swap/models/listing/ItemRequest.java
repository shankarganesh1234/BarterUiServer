package com.swap.models.listing;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRequest {
	private String title;
	private Long itemId;
	private String description;
	private Long zipCode;
	private Long userId;
	private Long categoryId;
	private Long imageId;
	private String condition;
	private int quantity;
	private int NumOfReviews;
	private int NumOfInterests;
	private String itemStage;
	private String story;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getNumOfReviews() {
		return NumOfReviews;
	}

	public void setNumOfReviews(int numOfReviews) {
		NumOfReviews = numOfReviews;
	}

	public int getNumOfInterests() {
		return NumOfInterests;
	}

	public void setNumOfInterests(int numOfInterests) {
		NumOfInterests = numOfInterests;
	}

	public String getItemStage() {
		return itemStage;
	}

	public void setItemStage(String itemStage) {
		this.itemStage = itemStage;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListingRequest [title=").append(title).append(", itemId=").append(itemId)
				.append(", description=").append(description).append(", zipCode=").append(zipCode).append(", userId=")
				.append(userId).append(", categoryId=").append(categoryId).append(", imageId=").append(imageId)
				.append(", condition=").append(condition).append(", quantity=").append(quantity)
				.append(", NumOfReviews=").append(NumOfReviews).append(", NumOfInterests=").append(NumOfInterests)
				.append(", itemStage=").append(itemStage).append(", story=").append(story).append("]");
		return builder.toString();
	}
}