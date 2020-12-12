package com.tourguidelocationservice.service;

import org.springframework.stereotype.Service;

import gpsUtil.location.Location;

@Service
public class DistanceCalculationService {

	private static final double MEAN_EARTH_RADIUS_MILES_VALUE = 3958.7613;
	
	public double getDistance(Location loc1, Location loc2) {
		
		double lat1 = loc1.latitude;
		double lon1 = loc1.longitude;
		double lat2 = loc2.latitude;
		double lon2 = loc2.longitude;

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon1 - lon2);

		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = MEAN_EARTH_RADIUS_MILES_VALUE * c;
		
		return distance;
	}
	
	
}
