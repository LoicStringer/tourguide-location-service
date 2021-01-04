package com.tourguidelocationservice.bean;

import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class AttractionBean {
	
	public UUID attractionId;
	public String attractionName;
    public String city;
    public String state;
    
    @Min(value = -180)
   	@Max(value = 180)
    public double longitude;
    
    @Min(value = -90)
   	@Max(value = 90)
    public double latitude;
    
	public AttractionBean() {
	}

	public AttractionBean(UUID attractionId, String attractionName, String city, String state, double longitude,
			double latitude) {
		super();
		this.attractionId = attractionId;
		this.attractionName = attractionName;
		this.city = city;
		this.state = state;
		this.longitude = longitude;
		this.latitude = latitude;
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

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	
    
}
