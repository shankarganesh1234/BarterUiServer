package com.swap.models.listing;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	private String public_id;
	private String version;
	private String signature;
	private String width;
	private String height;
	private String format;
	private String resource_type;
	private long bytes;
	private String url;
	private String secure_url;
	private Date createdAt;

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

	public long getBytes() {
		return bytes;
	}

	public void setBytes(long bytes) {
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Image [public_id=").append(public_id).append(", version=").append(version)
				.append(", signature=").append(signature).append(", width=").append(width).append(", height=")
				.append(height).append(", format=").append(format).append(", resource_type=").append(resource_type)
				.append(", bytes=").append(bytes).append(", url=").append(url).append(", secure_url=")
				.append(secure_url).append(", createdAt=").append(createdAt).append("]");
		return builder.toString();
	}
}
