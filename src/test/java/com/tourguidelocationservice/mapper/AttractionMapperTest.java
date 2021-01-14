package com.tourguidelocationservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tourguidelocationservice.bean.AttractionBean;

import gpsUtil.location.Attraction;

class AttractionMapperTest {

	private AttractionMapper attractionMapper;
	
	@BeforeEach
	void setUp() {
		attractionMapper = new AttractionMapper();
	}
	
	@Test
	void mapAttractionTest() {
		Attraction attraction =new Attraction("Flatiron Building", "New York City", "NY", 40.741112,
				-73.989723);
		AttractionBean attractionBean = attractionMapper.mapAttraction(attraction);
		assertEquals("Flatiron Building",attractionBean.getAttractionName());
	}

}
