package com.tourguidelocationservice.proxy;

import java.util.List;
import java.util.UUID;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

public interface IGpsUtilProxy {
	
	VisitedLocation getUserLocation (UUID userId);
	List<Attraction> getAttractions();
}