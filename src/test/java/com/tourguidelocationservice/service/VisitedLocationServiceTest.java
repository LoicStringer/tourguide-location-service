package com.tourguidelocationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.bean.VisitedLocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.exception.UserServiceException;
import com.tourguidelocationservice.proxy.GpsUtilProxyImpl;
import com.tourguidelocationservice.proxy.UserProxy;

@ExtendWith(MockitoExtension.class)
class VisitedLocationServiceTest {

	@Mock
	private UserProxy userProxy;
	
	@Mock
	private GpsUtilProxyImpl gpsUtilProxyImpl;
	
	@InjectMocks
	private VisitedLocationService visitedLocationService;
	
	private static VisitedLocationBean userLastVisitedLocation ;
	private VisitedLocationBean userActualLocation;
	
	@BeforeAll
	static void setUp() {
		userLastVisitedLocation = new VisitedLocationBean(UUID.randomUUID(),new LocationBean(20.50,20.50),new Date());
	}
	
	@Test
	void getUserLocationTestWhenLastAndActualAreEqual() throws GpsUtilException, UserServiceException {
		when(userProxy.getUserLatestVisitedLocation(any(UUID.class))).thenReturn(userLastVisitedLocation);
		when(gpsUtilProxyImpl.getUserLocation(any(UUID.class))).thenReturn(userLastVisitedLocation);
		assertEquals(userLastVisitedLocation,visitedLocationService.getUserLocation(userLastVisitedLocation.getUserId()));
	}

	@Test
	void getUserLocationTestWhenLastAndActualAreDifferent() throws GpsUtilException, UserServiceException {
		userActualLocation = new VisitedLocationBean(userLastVisitedLocation.getUserId(),new LocationBean(48.50,48.50),new Date());
		when(userProxy.getUserLatestVisitedLocation(userLastVisitedLocation.getUserId())).thenReturn(userLastVisitedLocation);
		when(gpsUtilProxyImpl.getUserLocation(userLastVisitedLocation.getUserId())).thenReturn(userActualLocation);
		assertEquals(userActualLocation,visitedLocationService.getUserLocation(userLastVisitedLocation.getUserId()));
	}
	
	@Test
	void isExpectedExceptionThrownWhenUserActualLocationIsNull() throws GpsUtilException{
		when(userProxy.getUserLatestVisitedLocation(any(UUID.class))).thenReturn(userLastVisitedLocation);
		when(gpsUtilProxyImpl.getUserLocation(any(UUID.class))).thenThrow(GpsUtilException.class);
		assertThrows(GpsUtilException.class,()-> visitedLocationService.getUserLocation(UUID.randomUUID()));
	}
	
	@Test
	void isExpectedExceptionThrownWhenUserLastLocationFromListIsNull() throws GpsUtilException, UserServiceException {
		when(userProxy.getUserLatestVisitedLocation(any(UUID.class))).thenReturn(null);
		assertThrows(UserServiceException.class,()-> visitedLocationService.getUserLocation(UUID.randomUUID()));
	}
}
