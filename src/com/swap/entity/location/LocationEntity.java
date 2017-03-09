package com.swap.entity.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class LocationEntity {
	
	@Id
	@Column(name = "zip_code")
	private Long zipCode;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	private String country;

	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
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
	
}
