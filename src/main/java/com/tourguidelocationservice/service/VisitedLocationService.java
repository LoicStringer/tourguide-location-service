package com.tourguidelocationservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.VisitedLocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.exception.UserServiceException;
import com.tourguidelocationservice.proxy.GpsUtilProxyImpl;
import com.tourguidelocationservice.proxy.UserProxy;

import feign.FeignException;

@Service
public class VisitedLocationService {

	@Autowired
	private UserProxy userProxy;

	@Autowired
	private GpsUtilProxyImpl gpsUtilProxyImpl;

	public VisitedLocationBean getUserLocation(UUID userId) throws GpsUtilException, UserServiceException {
		VisitedLocationBean userLocation = gpsUtilProxyImpl.getUserLocation(userId);
		try {
			userProxy.addUserVisitedLocation(userId, userLocation);
		}catch (FeignException fEx) {
			throw new UserServiceException("User service problem occurred when trying to add the user location", fEx);
		}
		
		return userLocation;
	}

}
