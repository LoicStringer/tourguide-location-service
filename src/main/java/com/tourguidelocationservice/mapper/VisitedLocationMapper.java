package com.tourguidelocationservice.mapper;

import com.tourguidelocationservice.bean.VisitedLocationBean;

import gpsUtil.location.VisitedLocation;

public class VisitedLocationMapper {
	
	public VisitedLocationBean mapVisitedLocation(VisitedLocation visitedLocation) {
		VisitedLocationBean visitedLocationBean = new VisitedLocationBean();
		LocationMapper locationMapper = new LocationMapper();
		visitedLocationBean.setUserId(visitedLocation.userId);
		visitedLocationBean.setLocation(locationMapper.mapLocation(visitedLocation.location));
		visitedLocationBean.setTimeVisited(visitedLocation.timeVisited);
		return visitedLocationBean;
	}

}
