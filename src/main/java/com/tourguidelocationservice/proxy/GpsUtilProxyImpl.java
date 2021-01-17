package com.tourguidelocationservice.proxy;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.VisitedLocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.mapper.AttractionMapper;
import com.tourguidelocationservice.mapper.VisitedLocationMapper;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

@Service
public class GpsUtilProxyImpl implements IGpsUtilProxy {

	@Autowired
	private AttractionMapper attractionMapper;

	@Autowired
	private VisitedLocationMapper visitedLocationMapper;

	@Autowired
	private GpsUtil gpsUtil;

	@Override
	public VisitedLocationBean getUserLocation(UUID userId) throws GpsUtilException {
		VisitedLocation gpsUserLocation = gpsUtil.getUserLocation(userId);
		if(gpsUserLocation==null)
			throw new GpsUtilException("A problem occured with external library \"GpsUtil\" : can't retrieve the actual user location.");
		VisitedLocationBean userLocation = visitedLocationMapper.mapVisitedLocation(gpsUserLocation);
		return userLocation ;
	}

	@Override
	public List<AttractionBean> getAttractions() throws GpsUtilException {
		List<Attraction> attractionsList = gpsUtil.getAttractions();
		if(attractionsList==null||attractionsList.isEmpty())
			throw new GpsUtilException("A problem occured with external library \"GpsUtil\" : can't retrieve the attractions list.");
		List<AttractionBean> attractionBeansList = attractionsList.stream()
				.map(at -> attractionMapper.mapAttraction(at))
				.collect(Collectors.toList());
		return attractionBeansList;
	}

}
