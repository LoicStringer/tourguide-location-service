package com.tourguidelocationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.LocationBean;

@Service
public class DistanceCalculationService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static final double MEAN_EARTH_RADIUS_MILES_VALUE = 3958.7613;
	
	public double getDistance(LocationBean loc1, LocationBean loc2) {
		log.debug("Processing distance calculation method using the haversine formula. Return value in miles.");
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
	
	public boolean checkIfLocationIsValid(LocationBean location)  {
		log.debug("Checking if the location is valid, latitude between -90/90 and longitude between -180/180");
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
