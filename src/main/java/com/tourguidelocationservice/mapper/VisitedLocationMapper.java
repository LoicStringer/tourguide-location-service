package com.tourguidelocationservice.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.VisitedLocationBean;

import gpsUtil.location.VisitedLocation;

@Service
public class VisitedLocationMapper {
	
	@Autowired
	private LocationMapper locationMapper;
	
	public VisitedLocationBean mapVisitedLocation(VisitedLocation visitedLocation) {
		VisitedLocationBean visitedLocationBean = new VisitedLocationBean();
		visitedLocationBean.setUserId(visitedLocation.userId);
		visitedLocationBean.setLocation(locationMapper.mapLocationToLocationBean(visitedLocation.location));
		visitedLocationBean.setTimeVisited(visitedLocation.timeVisited);
		return visitedLocationBean;
	}

}
