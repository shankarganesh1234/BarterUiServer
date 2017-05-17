package com.swap.models.elasticsearch;

import java.util.Date;

public class ItemDocument {

	private String title;
	private Long itemId;
	private String description;
	private Long zipCode;
	private String userName;
	private String categoryName;
	private String imageUrl;
	private String condition;
	private int quantity;
	private int numOfReviews;
	private int numOfInterests;
	private String itemStage;
	private String story;
	private Boolean activeInterests;
	private String titleSuggest;
	private String city;
	private Date upsertDate;
	
	public Date getUpsertDate() {
		return upsertDate;
	}
	public void setUpsertDate(Date upsertDate) {
		this.upsertDate = upsertDate;
	}
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
	public Long getZipCode() {
		return zipCode;
	}
	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public Boolean getActiveInterests() {
		return activeInterests;
	}
	public void setActiveInterests(Boolean activeInterests) {
		this.activeInterests = activeInterests;
	}
	public String getTitleSuggest() {
		return titleSuggest;
	}
	public void setTitleSuggest(String titleSuggest) {
		this.titleSuggest = titleSuggest;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
