package com.tourguidelocationservice.service;

import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.LocationBean;

@Service
public class DistanceCalculationService {

	private static final double MEAN_EARTH_RADIUS_MILES_VALUE = 3958.7613;
	
	public double getDistance(LocationBean loc1, LocationBean loc2) {

		double lat1 = loc1.getLatitude();
		double lon1 = loc1.getLongitude();
		double lat2 = loc2.getLatitude();
		double lon2 = loc2.getLongitude();

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon1 - lon2);

		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = MEAN_EARTH_RADIUS_MILES_VALUE * c;
		
		return distance;
	}
	
	public boolean checkIfLocationIsValid(LocationBean location) {
		if(checkIfLatitudeIsValid(location.getLatitude())==false
				||checkIfLongitudeIsValid(location.getLongitude())==false)
			return false;
		return true;
	}
	
	private boolean checkIfLatitudeIsValid(double latitude) {
		return latitude >= -90.00 && latitude <= 90.00 ;
	}
	
	private boolean checkIfLongitudeIsValid(double longitude) {
		return longitude >= -180.00 && longitude <= 180.00 ;
	}
}
