package com.tourguidelocationservice.model;

public class AttractionDto {
	
	private String attractionName;
	private String attractionCity;
	private String attractionState;
	private  double latitude;
	private double longitude;
	
	public AttractionDto() {
	}

	public String getAttractionName() {
		return attractionName;
	}

	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
	}

	public String getAttractionCity() {
		return attractionCity;
	}

	public void setAttractionCity(String attractionCity) {
		this.attractionCity = attractionCity;
	}

	public String getAttractionState() {
		return attractionState;
	}

	public void setAttractionState(String attractionState) {
		this.attractionState = attractionState;
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
		result = prime * result + ((attractionCity == null) ? 0 : attractionCity.hashCode());
		result = prime * result + ((attractionName == null) ? 0 : attractionName.hashCode());
		result = prime * result + ((attractionState == null) ? 0 : attractionState.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		AttractionDto other = (AttractionDto) obj;
		if (attractionCity == null) {
			if (other.attractionCity != null)
				return false;
		} else if (!attractionCity.equals(other.attractionCity))
			return false;
		if (attractionName == null) {
			if (other.attractionName != null)
				return false;
		} else if (!attractionName.equals(other.attractionName))
			return false;
		if (attractionState == null) {
			if (other.attractionState != null)
				return false;
		} else if (!attractionState.equals(other.attractionState))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AttractionDto [attractionName=" + attractionName + ", attractionCity=" + attractionCity
				+ ", attractionState=" + attractionState + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
}
