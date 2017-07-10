package com.swap.models.common;

public class GeoLocation {

	private String zip;
	private String latitude;
	private String longitude;
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "GeoLocation [zip=" + zip + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
