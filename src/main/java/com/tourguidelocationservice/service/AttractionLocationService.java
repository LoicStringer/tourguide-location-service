package com.tourguidelocationservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.mapper.AttractionMapper;
import com.tourguidelocationservice.mapper.LocationMapper;
import com.tourguidelocationservice.proxy.GpsUtilProxy;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;

@Service
public class AttractionLocationService {

	@Autowired
	private GpsUtilProxy gpsUtilProxy;

	@Autowired
	private AttractionMapper attractionMapper;

	@Autowired
	private LocationMapper locationMapper;

	@Autowired
	private DistanceCalculationService distanceCalculationService;

	public List<AttractionBean> getAttractions() {
		return gpsUtilProxy.getAttractions().stream().map(at -> attractionMapper.mapAttraction(at))
				.collect(Collectors.toList());
	}

	public TreeMap<Double, AttractionBean> getDistancesToAttractions(LocationBean location) {
		TreeMap<Double, AttractionBean> disatncesToAttractionsMap = new TreeMap<Double, AttractionBean>();
		List<Attraction> attractionsList = gpsUtilProxy.getAttractions();
		attractionsList.forEach(at -> {
			Location attractionLocation = new Location(at.latitude, at.longitude);
			double distance = 
					distanceCalculationService.getDistance(locationMapper.mapLocationBeanToLocation(location),attractionLocation);
			disatncesToAttractionsMap.put(distance, attractionMapper.mapAttraction(at));
		});
		return disatncesToAttractionsMap;
	}

	
}
