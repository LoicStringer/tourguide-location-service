package com.tourguidelocationservice.bean;

public class AttractionBean {
	
	public String attractionName;
    public String city;
    public String state;
    public double longitude;
    public double latitude;
    
	public AttractionBean() {
	}

	public AttractionBean(String attractionName, String city, String state, double longitude, double latitude) {
		this.attractionName = attractionName;
		this.city = city;
		this.state = state;
		this.longitude = longitude;
		this.latitude = latitude;
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
