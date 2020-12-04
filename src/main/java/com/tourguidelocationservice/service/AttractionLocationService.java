package com.tourguidelocationservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguidelocationservice.mapper.AttractionMapper;
import com.tourguidelocationservice.model.AttractionDto;
import com.tourguidelocationservice.proxy.GpsUtilProxy;

@Service
public class AttractionLocationService {
	
	@Autowired
	private AttractionMapper attractionMapper;
	
	@Autowired
	private GpsUtilProxy gpsUtilProxy;
	
	public List<AttractionDto> getAttractions(){
		List<AttractionDto> attractionDtosList = gpsUtilProxy.getAttractions()
				.stream()
				.map(at-> attractionMapper.mapAttraction(at))
				.collect(Collectors.toList());
		return attractionDtosList;
	}

}
