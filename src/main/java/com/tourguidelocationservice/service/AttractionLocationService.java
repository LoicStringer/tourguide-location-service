package com.tourguidelocationservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.proxy.GpsUtilProxy;

import gpsUtil.location.Attraction;

@Service
public class AttractionLocationService {
	
	@Autowired
	private GpsUtilProxy gpsUtilProxy;
	
	public List<Attraction> getAttractions(){
		 return gpsUtilProxy.getAttractions();
	}

}
