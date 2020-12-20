package com.tourguidelocationservice.proxy;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

@Service
public class GpsUtilProxyImpl implements IGpsUtilProxy{
	
	@Autowired
	private GpsUtil gpsUtil;

	@Override
	public VisitedLocation getUserLocation(UUID userId) {
		return gpsUtil.getUserLocation(userId);
	}

	@Override
	public List<Attraction> getAttractions() {
		return gpsUtil.getAttractions();
	}
	
	

}
