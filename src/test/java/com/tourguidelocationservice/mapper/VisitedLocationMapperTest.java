package com.tourguidelocationservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tourguidelocationservice.bean.VisitedLocationBean;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

class VisitedLocationMapperTest {

	
	private VisitedLocationMapper visitedLocationMapper;
	
	@BeforeEach
	void setUp() {
		visitedLocationMapper = new VisitedLocationMapper();
	}
	
	@Test
	void mapVisitedLocationTest() {
		VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(),new Location(20.50,20.50),new Date());
		VisitedLocationBean visitedLocationBean = visitedLocationMapper.mapVisitedLocation(visitedLocation);
		assertEquals(20.50, visitedLocationBean.getLocation().getLatitude());
	}

}
