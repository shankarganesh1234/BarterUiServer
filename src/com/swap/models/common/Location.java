package com.swap.models.common;

public class Location {
	private String id;

	private String address;

	private String city;

	private String state;

	private String country;

	private String zipCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Location [id=").append(id).append(", address=").append(address).append(", city=").append(city)
				.append(", state=").append(state).append(", country=").append(country).append(", zipCode=")
				.append(zipCode).append("]");
		return builder.toString();
	}

}
