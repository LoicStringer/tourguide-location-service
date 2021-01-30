package com.tourguidelocationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.exception.InvalidLocationException;
import com.tourguidelocationservice.proxy.GpsUtilProxyImpl;


@ExtendWith(MockitoExtension.class)
class AttractionLocationServiceTest {

	
	
	@InjectMocks
	private AttractionLocationService attractionLocationService;

	@Mock
	private DistanceCalculationService calculator;
	
	@Mock
	private GpsUtilProxyImpl gpsUtilProxyImpl;
	
	@Test
	void getDistancesToAttractionsTest() throws GpsUtilException, InvalidLocationException {
		LocationBean location = new LocationBean(20.50, 20.50);
		LocationBean locationBis = new LocationBean(20.50, 20.50);
		List<AttractionBean>  attractionsList = new ArrayList<AttractionBean>();
		AttractionBean attraction = new AttractionBean(null, "Flatiron Building", "New York City", "NY", 40.741112,
				-73.989723);
		AttractionBean attractionBis = new AttractionBean(null, "Bronx Zoo", "Bronx", "NY", 40.852905, -73.872971);
		LocationBean attractionLocation = new LocationBean(attraction.getLatitude(),attraction.getLongitude());
		LocationBean attractionLocationBis = new LocationBean(attractionBis.getLatitude(),attractionBis.getLongitude());
		
		attractionsList.add(attraction);
		attractionsList.add(attractionBis);
		
		when(gpsUtilProxyImpl.getAttractions()).thenReturn(attractionsList);
		when(calculator.checkIfLocationIsValid(any(LocationBean.class))).thenReturn(true);
		when(calculator.getDistance(location, attractionLocation)).thenReturn(10.00);
		when(calculator.getDistance(locationBis, attractionLocationBis)).thenReturn(20.00);
		
		TreeMap<Double, AttractionBean> distancesToAttractions = attractionLocationService
				.getDistancesToAttractions(location);

		assertEquals(2, distancesToAttractions.size());
		assertEquals("Flatiron Building", distancesToAttractions.get(10.00).getAttractionName());
		assertEquals("Bronx Zoo", distancesToAttractions.get(20.00).getAttractionName());
		
	}

	@Test
	void isExpectedExceptionThrownWhenLocationIsInvalid() throws InvalidLocationException {
		LocationBean location = new LocationBean(-92.00,120.00);
		//when(calculator.checkIfLocationIsValid(location)).thenReturn(false);
		assertThrows(InvalidLocationException.class,
				() -> attractionLocationService.getDistancesToAttractions(location));
	}

	@Test
	void isExpectedExceptionThrownWhenAttractionsListIsNullOrEmpty() throws GpsUtilException, InvalidLocationException {
		LocationBean location = new LocationBean(20.50, 20.50);
		when(calculator.checkIfLocationIsValid(location)).thenReturn(true);
		when(gpsUtilProxyImpl.getAttractions()).thenThrow(GpsUtilException.class);
		assertThrows(GpsUtilException.class, () -> attractionLocationService.getDistancesToAttractions(location));
	}
}
