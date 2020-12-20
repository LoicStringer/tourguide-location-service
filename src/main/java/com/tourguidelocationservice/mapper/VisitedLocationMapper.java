package com.tourguidelocationservice.mapper;

import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.bean.VisitedLocationBean;

import gpsUtil.location.VisitedLocation;

@Service
public class VisitedLocationMapper {
	
	public VisitedLocationBean mapVisitedLocation(VisitedLocation visitedLocation) {
		VisitedLocationBean visitedLocationBean = new VisitedLocationBean();
		visitedLocationBean.setUserId(visitedLocation.userId);
		visitedLocationBean.setLocation(new LocationBean(visitedLocation.location.latitude,visitedLocation.location.longitude));
		visitedLocationBean.setTimeVisited(visitedLocation.timeVisited);
		return visitedLocationBean;
	}

}
