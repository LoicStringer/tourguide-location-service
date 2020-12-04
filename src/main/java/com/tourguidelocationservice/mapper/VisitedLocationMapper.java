package com.tourguidelocationservice.mapper;

import org.springframework.stereotype.Component;

import com.tourguidelocationservice.model.VisitedLocationDto;

import gpsUtil.location.VisitedLocation;

@Component
public class VisitedLocationMapper {
	
	public VisitedLocationDto mapVisitedLocation(VisitedLocation visitedLocation) {
		VisitedLocationDto visitedLocationDto = new VisitedLocationDto();
		visitedLocationDto.setUserId(visitedLocation.userId);
		visitedLocationDto.setLocation(visitedLocation.location);
		visitedLocationDto.setTimeVisited(visitedLocation.timeVisited);
		return visitedLocationDto; 
	}

}
