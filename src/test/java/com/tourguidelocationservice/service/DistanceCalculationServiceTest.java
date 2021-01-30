package com.tourguidelocationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tourguidelocationservice.bean.LocationBean;


class DistanceCalculationServiceTest {
	
	private DistanceCalculationService distanceCalculationService;

	@BeforeEach
	void setUp() {
		distanceCalculationService = new DistanceCalculationService();
	}
	
	@Test
	void checkValidLocationTest()  {
		LocationBean location = new LocationBean(89.99999,179.9999);
		LocationBean locationTwo = new LocationBean(-91.00,120.00);
		LocationBean locationThree = new LocationBean(-89.00,181.00);
		LocationBean locationFour = new LocationBean(-89.00,-181.00);
		LocationBean locationFive = new LocationBean(90.00001,179.9999);
		LocationBean locationSix = new LocationBean(-89.99999,-179.9999);
		assertTrue(distanceCalculationService.checkIfLocationIsValid(location));
		assertFalse(distanceCalculationService.checkIfLocationIsValid(locationTwo));
		assertFalse(distanceCalculationService.checkIfLocationIsValid(locationThree));
		assertFalse(distanceCalculationService.checkIfLocationIsValid(locationFour));
		assertFalse(distanceCalculationService.checkIfLocationIsValid(locationFive));
		assertTrue(distanceCalculationService.checkIfLocationIsValid(locationSix));
	}

	@Test
	void getDistanceTest() {
		LocationBean loc1 = new LocationBean(20.50,20.50);
		LocationBean loc2 = new LocationBean(48.00,48.00);
		assertEquals(2438.6099005082897,distanceCalculationService.getDistance(loc1, loc2));
	}
	
}
