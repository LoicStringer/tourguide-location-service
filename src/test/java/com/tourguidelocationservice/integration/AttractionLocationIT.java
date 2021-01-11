package com.tourguidelocationservice.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.proxy.GpsUtilProxyImpl;
import com.tourguidelocationservice.service.DistanceCalculationService;


@SpringBootTest
@AutoConfigureMockMvc
class AttractionLocationIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private GpsUtilProxyImpl gpsUtilProxyImpl;

	@Autowired
	private DistanceCalculationService distanceCalculationService;

	@Test
	void test() throws Exception {
		LocationBean location = new LocationBean(20.50, 20.50);

		mockMvc.perform(MockMvcRequestBuilders.post("/attractions/distances").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(location))).andExpect(status().isOk()).andReturn();
		
		
		
	}

}
