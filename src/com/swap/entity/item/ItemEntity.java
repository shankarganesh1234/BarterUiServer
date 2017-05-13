package com.swap.entity.item;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.swap.db.listeners.ItemEntityInterceptor;
import com.swap.entity.category.CategoryEntity;
import com.swap.entity.location.LocationEntity;
import com.swap.entity.user.UserEntity;

@Entity
@EntityListeners(value = ItemEntityInterceptor.class)
@Table(name = "item")
public class ItemEntity {
	@Column(name = "title")
	private String title;

	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;

	@Column(name = "description")
	private String description;

	@OneToOne
	@JoinColumn(name = "zip_code")
	private LocationEntity zipCode;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity userId;

	@OneToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity categoryId;

	@Column(name = "condition")
	private String condition;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "num_of_reviews")
	private int numOfReviews;

	@Column(name = "num_of_interests")
	private int numOfInterests;

	@Column(name = "item_stage")
	private String itemStage;

	@Column(name = "story")
	private String story;

	@Column(name = "active_interests")
	private Boolean activeInterests;
	
	@Column(name = "upsert_date")
	private Timestamp upsertDate;
	
	@OneToMany(mappedBy = "item", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ImageEntity> images = new LinkedList<ImageEntity>();
    
    public List<ImageEntity> getImages() {
		return images;
	}

	public void setImages(List<ImageEntity> images) {
		this.images = images;
	}

	public Timestamp getUpsertDate() {
		return upsertDate;
	}

	public void setUpsertDate(Timestamp upsertDate) {
		this.upsertDate = upsertDate;
	}

	public Boolean getActiveInterests() {
		return activeInterests;
	}

	public void setActiveInterests(Boolean activeInterests) {
		this.activeInterests = activeInterests;
	}

	public LocationEntity getZipCode() {
		return zipCode;
	}

	public void setZipCode(LocationEntity zipCode) {
		this.zipCode = zipCode;
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
}