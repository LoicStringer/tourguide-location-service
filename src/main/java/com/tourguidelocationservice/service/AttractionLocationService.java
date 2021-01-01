package com.tourguidelocationservice.service;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.mapper.AttractionMapper;
import com.tourguidelocationservice.proxy.GpsUtilProxyImpl;

@Service
public class AttractionLocationService {

	@Autowired
	private GpsUtilProxyImpl gpsUtilProxyImpl;

	@Autowired
	private AttractionMapper attractionMapper;

	@Autowired
	private DistanceCalculationService distanceCalculationService;

	public TreeMap<Double, AttractionBean> getDistancesToAttractions(LocationBean location) {
		TreeMap<Double, AttractionBean> distancesToAttractionsMap = new TreeMap<Double, AttractionBean>();
		List<AttractionBean> attractionsList = getAttractions();
		attractionsList.forEach(at -> {
			LocationBean attractionLocation = new LocationBean(at.latitude, at.longitude);
			double distance = 
					distanceCalculationService.getDistance(location,attractionLocation);
			distancesToAttractionsMap.put(distance, at);
		});
		return distancesToAttractionsMap;
	}

	private List<AttractionBean> getAttractions() {
		return gpsUtilProxyImpl.getAttractions().parallelStream().map(at -> attractionMapper.mapAttraction(at))
				.collect(Collectors.toList());
	}

}
