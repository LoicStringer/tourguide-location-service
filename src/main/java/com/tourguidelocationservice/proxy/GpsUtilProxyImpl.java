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
		VisitedLocationBean userLocation = visitedLocationMapper.mapVisitedLocation(gpsUtil.getUserLocation(userId));
		if(userLocation==null)
			throw new GpsUtilException("A problem occured with external library \"GpsUtil\" : can't retrieve the actual user location.");
		return userLocation ;
	}

	@Override
	public List<AttractionBean> getAttractions() throws GpsUtilException {
		List<AttractionBean> attractionsList = gpsUtil.getAttractions().stream()
				.map(at -> attractionMapper.mapAttraction(at)).collect(Collectors.toList());
		if(attractionsList==null||attractionsList.isEmpty())
			throw new GpsUtilException("A problem occured with external library \"GpsUtil\" : can't retrieve the attractions list.");
		return attractionsList;
	}

}
