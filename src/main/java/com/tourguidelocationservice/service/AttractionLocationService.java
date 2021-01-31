package com.tourguidelocationservice.service;

import java.util.List;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.exception.InvalidLocationException;
import com.tourguidelocationservice.proxy.GpsUtilProxyImpl;

@Service
public class AttractionLocationService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GpsUtilProxyImpl gpsUtilProxyImpl;

	@Autowired
	private DistanceCalculationService distanceCalculationService;

	public TreeMap<Double, AttractionBean> getDistancesToAttractions(LocationBean location) throws GpsUtilException, InvalidLocationException {
		log.debug("Checking if provided user location is valid.");
		if(distanceCalculationService.checkIfLocationIsValid(location)==false) {
			log.error("The provided user location used to calculate the distances to attractionsis not valid.");
			throw new InvalidLocationException("The provided location is not valid");
		}
		
		TreeMap<Double, AttractionBean> distancesToAttractionsMap = new TreeMap<Double, AttractionBean>();
		log.debug("Getting the attractions list.");
		List<AttractionBean> attractionsList = getAttractionsList();
		attractionsList.stream().forEach(at->{
			LocationBean attractionLocation = new LocationBean(at.latitude, at.longitude);
			distanceCalculationService.checkIfLocationIsValid(attractionLocation);
			double distance = distanceCalculationService.getDistance(location, attractionLocation);
			distancesToAttractionsMap.put(distance, at);
		});
	
		return distancesToAttractionsMap;
	}

	private List<AttractionBean> getAttractionsList() throws GpsUtilException {
		return gpsUtilProxyImpl.getAttractions();
	}

}
