package com.tourguidelocationservice.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class LocationBean {

	@Min(value = -180)
   	@Max(value = 180)
    public double longitude;
    
    @Min(value = -90)
   	@Max(value = 90)
    public double latitude;
	
	public LocationBean() {
	}

	public LocationBean(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
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
