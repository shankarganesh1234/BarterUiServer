package com.swap.entity.item;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class ImageEntity {
	@Id
	@Column(name = "image_id")
	private String public_id;

	@Column(name = "version")
	private String version;

	@Column(name = "signature")
	private String signature;

	@Column(name = "width")
	private String width;

	@Column(name = "height")
	private String height;

	@Column(name = "format")
	private String format;

	@Column(name = "resource_type")
	private String resource_type;

	@Column(name = "bytes")
	private Integer bytes;

	@Column(name = "url")
	private String url;

	@Column(name = "secure_url")
	private String secure_url;

	@Column(name = "upsert_date")
	private Timestamp upsertDate;
	
	@Column(name = "item_id")
	private Long item;
	
	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
	}

	public String getPublic_id() {
		return public_id;
	}

	public void setPublic_id(String public_id) {
		this.public_id = public_id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public Integer getBytes() {
		return bytes;
	}

	public void setBytes(Integer bytes) {
		this.bytes = bytes;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSecure_url() {
		return secure_url;
	}

	public void setSecure_url(String secure_url) {
		this.secure_url = secure_url;
	}

	public Timestamp getUpsertDate() {
		return upsertDate;
	}

	public void setUpsertDate(Timestamp upsertDate) {
		this.upsertDate = upsertDate;
	}
}
