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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attractionId == null) ? 0 : attractionId.hashCode());
		result = prime * result + ((attractionName == null) ? 0 : attractionName.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttractionBean other = (AttractionBean) obj;
		if (attractionId == null) {
			if (other.attractionId != null)
				return false;
		} else if (!attractionId.equals(other.attractionId))
			return false;
		if (attractionName == null) {
			if (other.attractionName != null)
				return false;
		} else if (!attractionName.equals(other.attractionName))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AttractionBean [attractionId=" + attractionId + ", attractionName=" + attractionName + ", city=" + city
				+ ", state=" + state + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

    
}
