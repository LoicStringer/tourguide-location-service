package com.tourguidelocationservice.service;

import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.exception.InvalidLocationException;
import com.tourguidelocationservice.proxy.GpsUtilProxyImpl;

@Service
public class AttractionLocationService {

	@Autowired
	private GpsUtilProxyImpl gpsUtilProxyImpl;

	@Autowired
	private DistanceCalculationService distanceCalculationService;

	public TreeMap<Double, AttractionBean> getDistancesToAttractions(LocationBean location) throws GpsUtilException, InvalidLocationException {
		
		if(!distanceCalculationService.checkIfLocationIsValid(location))
			throw new InvalidLocationException("The provided location is invalid");
		
		TreeMap<Double, AttractionBean> distancesToAttractionsMap = new TreeMap<Double, AttractionBean>();
		List<AttractionBean> attractionsList = getAttractionsList();
		
		for(AttractionBean attraction:attractionsList) {
			LocationBean attractionLocation = new LocationBean(attraction.latitude, attraction.longitude);
			
			if(distanceCalculationService.checkIfLocationIsValid(attractionLocation)==false)
				throw new InvalidLocationException("The provided "+ attraction.getAttractionName()+" location is invalid");
			
			double distance = distanceCalculationService.getDistance(location, attractionLocation);
			distancesToAttractionsMap.put(distance, attraction);
		};
		System.out.println(distancesToAttractionsMap);
		return distancesToAttractionsMap;
	}

	private List<AttractionBean> getAttractionsList() throws GpsUtilException {
		return gpsUtilProxyImpl.getAttractions();
	}

}
