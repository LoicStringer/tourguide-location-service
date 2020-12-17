package com.tourguidelocationservice.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.bean.VisitedLocationBean;
import com.tourguidelocationservice.mapper.VisitedLocationMapper;
import com.tourguidelocationservice.proxy.GpsUtilProxy;
import com.tourguidelocationservice.proxy.UserProxy;

@Service
public class VisitedLocationService {
	
	@Autowired
	private UserProxy userProxy;

	@Autowired
	private GpsUtilProxy gpsUtilProxy;
	
	@Autowired
	private VisitedLocationMapper visitedLocationMapper;
	
	public VisitedLocationBean getUserLocation (UUID userId) {
		VisitedLocationBean visitedLocation = userProxy.getUserLatestVisitedLocation(userId);
		if (visitedLocation == null)
			visitedLocation = visitedLocationMapper.mapVisitedLocation(gpsUtilProxy.getUserLocation(userId));
		return visitedLocation;
	}

	/*

	public UsersLocationsList getEachUsersLocationsList() {
		UsersLocationsList usersLocationsList = new UsersLocationsList();
		DataContainer.usersData.entrySet().forEach(entry -> {
			UserLocation userLocation = new UsersLocationsList().new UserLocation(entry.getKey(),
					getUserLastVisitedLocation(entry.getKey()).getLocation());
			usersLocationsList.addUserLocation(userLocation);
		});
		return usersLocationsList;
	}
	
	*/
}
