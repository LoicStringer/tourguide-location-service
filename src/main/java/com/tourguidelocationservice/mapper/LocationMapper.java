package com.tourguidelocationservice.mapper;

import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.LocationBean;

import gpsUtil.location.Location;

@Service
public class LocationMapper {
	
	public LocationBean mapLocationToLocationBean(Location location) {
		LocationBean locationBean = new LocationBean();
		locationBean.setLatitude(location.latitude);
		locationBean.setLongitude(location.longitude);
		return locationBean;
	}

	public Location mapLocationBeanToLocation(LocationBean locationBean) {
		Location location = new Location(locationBean.getLatitude(), locationBean.getLongitude());
		return location;
	}
}
