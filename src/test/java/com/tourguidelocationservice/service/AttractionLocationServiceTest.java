package com.tourguidelocationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeAll;
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
	private GpsUtilProxyImpl gpsUtilImpl;

	private static LocationBean location;
	private static List<AttractionBean> attractionsList;

	@BeforeAll
	static void setUp() {
		location = new LocationBean(20.50, 20.50);
		attractionsList = new ArrayList<AttractionBean>();
		AttractionBean attraction = new AttractionBean(null, "Flatiron Building", "New York City", "NY", 40.741112,
				-73.989723);
		AttractionBean attractionBis = new AttractionBean(null, "Bronx Zoo", "Bronx", "NY", 40.852905, -73.872971);
		attractionsList.add(attraction);
		attractionsList.add(attractionBis);
	}

	@Test
	void getDistancesToAttractionsTest() throws GpsUtilException, InvalidLocationException {
		
		LocationBean attractionLocation = new LocationBean(attractionsList.get(0).getLatitude(),attractionsList.get(0).getLongitude());
		LocationBean attractionLocationBis = new LocationBean(attractionsList.get(1).getLatitude(),attractionsList.get(1).getLongitude());
		
		when(gpsUtilImpl.getAttractions()).thenReturn(attractionsList);
		when(calculator.checkIfLocationIsValid(location)).thenReturn(true);
		when(calculator.checkIfLocationIsValid(attractionLocation)).thenReturn(true);
		when(calculator.checkIfLocationIsValid(attractionLocationBis)).thenReturn(true);
		when(calculator.getDistance(location,
				new LocationBean(attractionsList.get(0).getLatitude(), attractionsList.get(0).getLongitude())))
						.thenReturn(10.00);
		when(calculator.getDistance(location,
				new LocationBean(attractionsList.get(1).getLatitude(), attractionsList.get(1).getLongitude())))
						.thenReturn(20.00);

		TreeMap<Double, AttractionBean> distancesToAttractions = attractionLocationService
				.getDistancesToAttractions(location);

		assertEquals(2, distancesToAttractions.size());
		assertEquals("Flatiron Building", distancesToAttractions.get(10.00).getAttractionName());
		assertEquals("Bronx Zoo", distancesToAttractions.get(20.00).getAttractionName());
	}

	@Test
	void isExpectedExceptionThrownWhenLocationIsInvalid() {
		when(calculator.checkIfLocationIsValid(any(LocationBean.class))).thenReturn(false);
		assertThrows(InvalidLocationException.class,()->attractionLocationService.getDistancesToAttractions(location));
	}

	@Test
	void isExpectedExceptionThrownWhenAttractionsListIsNullOrEmpty() throws GpsUtilException {
		when(calculator.checkIfLocationIsValid(location)).thenReturn(true);
		when(gpsUtilImpl.getAttractions()).thenThrow(GpsUtilException.class);
		assertThrows(GpsUtilException.class,()->attractionLocationService.getDistancesToAttractions(location));
	}
}
