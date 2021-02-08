package com.tourguidelocationservice.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.bean.VisitedLocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.exception.UserServiceException;
import com.tourguidelocationservice.proxy.GpsUtilProxyImpl;
import com.tourguidelocationservice.proxy.UserProxy;

import feign.FeignException;

@SpringBootTest
@AutoConfigureMockMvc
class VisitedLocationServiceTestIT {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GpsUtilProxyImpl gpsUtilProxyImpl;
	
	@MockBean
	private UserProxy userProxy;
	
	private static VisitedLocationBean userLocation;
	
	@BeforeAll
	static void setUp() throws GpsUtilException {
		userLocation = new VisitedLocationBean(UUID.randomUUID(),new LocationBean(20.50,120.50),new Date());
	}
	
	@Test
	void test() throws Exception {
		when(gpsUtilProxyImpl.getUserLocation(any(UUID.class))).thenReturn(userLocation);
		when(userProxy.addUserVisitedLocation(any(UUID.class), any(VisitedLocationBean.class))).thenReturn(userLocation);
		
		mockMvc.perform(get("/users/"+UUID.randomUUID()+"/locations/latest"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.location.latitude").value(20.50))
				.andExpect(jsonPath("$.location.longitude").value(120.50));
	}

	@Test
	void isExpectedExceptionThrownWhenUserLocationIsNotFound() throws Exception {
		when(gpsUtilProxyImpl.getUserLocation(any(UUID.class))).thenThrow(GpsUtilException.class);
		
		mockMvc.perform(get("/users/"+UUID.randomUUID()+"/locations/latest"))
				.andExpect(status().isNotFound())
				.andExpect(result->assertTrue(result.getResolvedException() instanceof GpsUtilException));
	}
	
	@Test
	void isExpectedExceptionThrownWhenUserLocationIsNotAdded() throws Exception {
		when(gpsUtilProxyImpl.getUserLocation(any(UUID.class))).thenReturn(userLocation);
		when(userProxy.addUserVisitedLocation(any(UUID.class), any(VisitedLocationBean.class))).thenThrow(FeignException.class);
		
		mockMvc.perform(get("/users/"+UUID.randomUUID()+"/locations/latest"))
		.andExpect(status().isNotFound())
		.andExpect(result->assertTrue(result.getResolvedException() instanceof UserServiceException));
	}
}
