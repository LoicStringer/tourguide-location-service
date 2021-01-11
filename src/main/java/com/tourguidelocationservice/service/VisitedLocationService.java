package com.tourguidelocationservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.VisitedLocationBean;

import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.exception.UserServiceException;
import com.tourguidelocationservice.proxy.GpsUtilProxyImpl;
import com.tourguidelocationservice.proxy.UserProxy;

@Service
public class VisitedLocationService {

	@Autowired
	private UserProxy userProxy;

	@Autowired
	private GpsUtilProxyImpl gpsUtilProxyImpl;

	public VisitedLocationBean getUserLocation(UUID userId) throws GpsUtilException, UserServiceException {
		VisitedLocationBean userLastVisitedLocation = userProxy.getUserLatestVisitedLocation(userId);
		if (userLastVisitedLocation == null)
			throw new UserServiceException(
					"A problem occured with the \"TourGuideUserService\" : can't retrieve the user last visited location from his list");
		VisitedLocationBean actualVisitedLocation = gpsUtilProxyImpl.getUserLocation(userId);
		if (userLastVisitedLocation == null
				| !userLastVisitedLocation.getLocation().equals(actualVisitedLocation.getLocation())) {
			userProxy.addUserVisitedLocation(userId, actualVisitedLocation);
			userLastVisitedLocation = actualVisitedLocation;
		}
		return userLastVisitedLocation;
	}

}
