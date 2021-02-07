package com.tourguidelocationservice.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserProxy userProxy;

	@Autowired
	private GpsUtilProxyImpl gpsUtilProxyImpl;

	public VisitedLocationBean getUserLocation(UUID userId) throws GpsUtilException, UserServiceException {
		//log.debug("Querying user location to external library GpsUtil.");
		VisitedLocationBean userLocation = gpsUtilProxyImpl.getUserLocation(userId);
		
		try {
			//log.debug("Adding the retrieved user location to the user's visited location list." + System.lineSeparator() 
			//+"Calling tourguide-user-service.");
			userProxy.addUserVisitedLocation(userId, userLocation);
		}catch (FeignException fEx) {
			//log.error("Feign exception was raised. "+ fEx.getMessage());
			throw new UserServiceException("User service problem occurred when trying to add the user location", fEx);
		}
		
		return userLocation;
	}

}
