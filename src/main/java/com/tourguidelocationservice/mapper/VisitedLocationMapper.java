package com.tourguidelocationservice.mapper;

import org.springframework.stereotype.Component;

import com.tourguidelocationservice.bean.VisitedLocationBean;

import gpsUtil.location.VisitedLocation;

@Component
public class VisitedLocationMapper {
	
	public VisitedLocationBean mapVisitedLocation(VisitedLocation visitedLocation) {
		VisitedLocationBean visitedLocationBean = new VisitedLocationBean();
		visitedLocationBean.setUserId(visitedLocation.userId);
		visitedLocationBean.setLocation(visitedLocation.location);
		visitedLocationBean.setTimeVisited(visitedLocation.timeVisited);
		return visitedLocationBean; 
	}

}
