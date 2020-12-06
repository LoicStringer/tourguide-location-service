package com.tourguidelocationservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.bean.VisitedLocationBean;
import com.tourguidelocationservice.mapper.VisitedLocationMapper;
import com.tourguidelocationservice.proxy.GpsUtilProxy;

@Service
public class VisitedLocationService {
	
	@Autowired
	private VisitedLocationMapper visitedLocationMapper;
	
	@Autowired
	private GpsUtilProxy gpsUtilProxy;
	
	public VisitedLocationBean getUserLocation (UUID userId) {
		return visitedLocationMapper.mapVisitedLocation(gpsUtilProxy.getUserLocation(userId));
	}

}
