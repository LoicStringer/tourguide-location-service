package com.tourguidelocationservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.proxy.GpsUtilProxy;

import gpsUtil.location.VisitedLocation;

@Service
public class VisitedLocationService {

	@Autowired
	private GpsUtilProxy gpsUtilProxy;
	
	public VisitedLocation getUserLocation (UUID userId) {
		return gpsUtilProxy.getUserLocation(userId);
	}

}
