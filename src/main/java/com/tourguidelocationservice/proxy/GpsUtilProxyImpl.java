package com.tourguidelocationservice.proxy;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.exception.ExceptionMessage;
import com.tourguidelocationservice.exception.GpsUtilException;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

@Service
public class GpsUtilProxyImpl implements IGpsUtilProxy{
	
	@Autowired 
	private ExceptionMessage exceptionMessage;
	
	@Autowired
	private GpsUtil gpsUtil;

	@Override
	public VisitedLocation getUserLocation(UUID userId) throws GpsUtilException {
		VisitedLocation userLocation = gpsUtil.getUserLocation(userId);
		if(userLocation==null)
			throw new GpsUtilException(exceptionMessage.get("user.location.notfound"));
		return userLocation;
	}

	@Override
	public List<Attraction> getAttractions() throws GpsUtilException {
		List<Attraction> attractionsList = gpsUtil.getAttractions();
		if(attractionsList.isEmpty()||attractionsList==null)
			throw new GpsUtilException(exceptionMessage.get("attractions.list.notfound"));
		return attractionsList;
	}
	
}
