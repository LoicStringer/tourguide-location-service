package com.tourguidelocationservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.VisitedLocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.mapper.VisitedLocationMapper;
import com.tourguidelocationservice.proxy.GpsUtilProxyImpl;
import com.tourguidelocationservice.proxy.UserProxy;

@Service
public class VisitedLocationService {
	
	@Autowired
	private UserProxy userProxy;

	@Autowired
	private GpsUtilProxyImpl gpsUtilProxyImpl;
	
	@Autowired
	private VisitedLocationMapper visitedLocationMapper;
	
	public VisitedLocationBean getUserLocation (UUID userId) throws GpsUtilException {
		VisitedLocationBean visitedLocation = userProxy.getUserLatestVisitedLocation(userId);
		if (visitedLocation == null) {
			visitedLocation = visitedLocationMapper.mapVisitedLocation(gpsUtilProxyImpl.getUserLocation(userId));
			userProxy.addUserVisitedLocation(userId, visitedLocation);
		}
		return visitedLocation;
	}

}
