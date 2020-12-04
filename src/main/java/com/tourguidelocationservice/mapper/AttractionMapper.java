package com.tourguidelocationservice.mapper;

import org.springframework.stereotype.Component;

import com.tourguidelocationservice.model.AttractionDto;

import gpsUtil.location.Attraction;

@Component
public class AttractionMapper  {
	
	public AttractionDto mapAttraction(Attraction attraction) {
		AttractionDto attractionDto = new AttractionDto();
		attractionDto.setAttractionName(attraction.attractionName);
		attractionDto.setAttractionCity(attraction.city);
		attractionDto.setAttractionState(attraction.state);
		attractionDto.setLatitude(attraction.latitude);
		attractionDto.setLongitude(attraction.longitude);
		return attractionDto;
	}
	
	

}
