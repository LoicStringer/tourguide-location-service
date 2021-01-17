package com.tourguidelocationservice.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.exception.InvalidLocationException;
import com.tourguidelocationservice.proxy.GpsUtilProxyImpl;

@SpringBootTest
@AutoConfigureMockMvc
class AttractionLocationServiceTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GpsUtilProxyImpl gpsUtilProxyImpl;

	@BeforeEach
	void setUp() throws GpsUtilException {
		List<AttractionBean> attractionsList = new ArrayList<AttractionBean>();
		AttractionBean attractionBis = new AttractionBean(null, "Bronx Zoo", "Bronx", "NY", 40.852905, -73.872971);
		AttractionBean attraction = new AttractionBean(null, "Flatiron Building", "New York City", "NY", 40.741112,
				-73.989723);
		attractionsList.add(attraction);
		attractionsList.add(attractionBis);

		when(gpsUtilProxyImpl.getAttractions()).thenReturn(attractionsList);
	}

	@Test
	void getDistancesToAttractionsTest() throws JsonProcessingException, Exception {
		LocationBean location = new LocationBean(20.50, 20.50);

		mockMvc.perform(post("/attractions/distances").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(location))).andExpect(status().isOk())
				.andExpect(jsonPath("$").isMap())
				.andExpect(jsonPath("$['5521.813858041754'].attractionName").value("Bronx Zoo"))
				.andExpect(jsonPath("$['5530.055646748868'].attractionName").value("Flatiron Building"));
	}

	@Test
	void getDistancesToAttractionsInavalidLocationExceptionTest() throws Exception {
		LocationBean location = new LocationBean(91.00, 20.50);

		mockMvc.perform(post("/attractions/distances").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(location)))
				.andExpect(status().isBadRequest())
				.andExpect(result->assertTrue(result.getResolvedException() instanceof InvalidLocationException));
	}
	
	@Test
	void getDistancesToAttractionsGpsUtilExceptionTest() throws Exception {
		LocationBean location = new LocationBean(20.50, 20.50);
		
		when(gpsUtilProxyImpl.getAttractions()).thenThrow(GpsUtilException.class);
		
		mockMvc.perform(post("/attractions/distances").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(location)))
				.andExpect(status().isNotFound())
				.andExpect(result->assertTrue(result.getResolvedException() instanceof GpsUtilException));
	}
}
