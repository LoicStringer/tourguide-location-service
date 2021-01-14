package com.tourguidelocationservice.bean;

import java.util.UUID;

public class AttractionBean {
	
	public UUID attractionId;
	public String attractionName;
    public String city;
    public String state;
    public double latitude;
    public double longitude;
    
	public AttractionBean() {
	}

	public AttractionBean(UUID attractionId, String attractionName, String city, String state, double latitude,
			double longitude) {
		super();
		this.attractionId = attractionId;
		this.attractionName = attractionName;
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public UUID getAttractionId() {
		return attractionId;
	}

	public void setAttractionId(UUID attractionId) {
		this.attractionId = attractionId;
	}

	public String getAttractionName() {
		return attractionName;
	}

	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "AttractionBean [attractionId=" + attractionId + ", attractionName=" + attractionName + ", city=" + city
				+ ", state=" + state + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

    
}
