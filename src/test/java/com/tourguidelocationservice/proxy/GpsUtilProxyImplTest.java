package com.tourguidelocationservice.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.bean.VisitedLocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.mapper.AttractionMapper;
import com.tourguidelocationservice.mapper.VisitedLocationMapper;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@ExtendWith(MockitoExtension.class)
class GpsUtilProxyImplTest {

	@Mock
	private GpsUtil gpsUtil;
	
	@Mock
	private AttractionMapper attractionMapper;
	
	@Mock
	private VisitedLocationMapper visitedLocationMapper;
	
	@InjectMocks
	private GpsUtilProxyImpl gpsUtilProxyImpl;
	
	@Test
	void getAttractionsListTest() throws GpsUtilException {
		List<Attraction> attractionsList  = new ArrayList<Attraction>();
		Attraction attraction = new Attraction("Flatiron Building", "New York City", "NY", 40.741112,
				-73.989723);
		Attraction attractionBis = new Attraction("Bronx Zoo", "Bronx", "NY", 40.852905, -73.872971);
		attractionsList.add(attraction);
		attractionsList.add(attractionBis);
		AttractionBean attractionBean = new AttractionBean(null,"Flatiron Building", "New York City", "NY", 40.741112,
				-73.989723);
		AttractionBean attractionBeanBis = new AttractionBean(null,"Bronx Zoo", "Bronx", "NY", 40.852905, -73.872971);
		
		when(gpsUtil.getAttractions()).thenReturn(attractionsList);
		when(attractionMapper.mapAttraction(attraction)).thenReturn(attractionBean);
		when(attractionMapper.mapAttraction(attractionBis)).thenReturn(attractionBeanBis);
		
		List<AttractionBean> attractionsBeanList = gpsUtilProxyImpl.getAttractions();
		assertEquals("Flatiron Building",attractionsBeanList.get(0).getAttractionName());
	}
	
	@Test
	void getUserLocationTest() throws GpsUtilException {
		VisitedLocation gpsUserlocation = new VisitedLocation(UUID.randomUUID(),new Location(20.50,20.50), new Date());
		VisitedLocationBean userLocation = new VisitedLocationBean(gpsUserlocation.userId,new LocationBean(20.50,20.50),gpsUserlocation.timeVisited);
		
		when(gpsUtil.getUserLocation(any(UUID.class))).thenReturn(gpsUserlocation);
		when(visitedLocationMapper.mapVisitedLocation(gpsUserlocation)).thenReturn(userLocation);
		userLocation = gpsUtilProxyImpl.getUserLocation(gpsUserlocation.userId);
		
		assertEquals(20.50,userLocation.getLocation().getLatitude());
	}

	@Test
	void isExpectedExceptionThrownWhenGpsUtilReturnsNullOrEmptyList() {
		when(gpsUtil.getAttractions()).thenReturn(null);
		assertThrows(GpsUtilException.class,()->gpsUtilProxyImpl.getAttractions());
		when(gpsUtil.getAttractions()).thenReturn(new ArrayList<Attraction>());
		assertThrows(GpsUtilException.class,()->gpsUtilProxyImpl.getAttractions());
	}
	
	@Test
	void isExpectedExceptionThrownWhenGpsUtilReturnsNullUserLocation() {
		when(gpsUtil.getUserLocation(any(UUID.class))).thenReturn(null);
		assertThrows(GpsUtilException.class,()->gpsUtilProxyImpl.getUserLocation(UUID.randomUUID()));
	}
	
}
