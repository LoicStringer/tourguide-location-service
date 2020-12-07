package com.tourguidelocationservice.mapper;

import com.tourguidelocationservice.bean.LocationBean;

import gpsUtil.location.Location;

public class LocationMapper {
	
	public LocationBean mapLocation(Location location) {
		LocationBean locationBean = new LocationBean();
		locationBean.setLatitude(location.latitude);
		locationBean.setLongitude(location.longitude);
		return locationBean;
	}

}
