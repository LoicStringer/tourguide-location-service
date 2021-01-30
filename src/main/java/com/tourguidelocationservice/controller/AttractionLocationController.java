package com.tourguidelocationservice.controller;

import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.exception.InvalidLocationException;
import com.tourguidelocationservice.service.AttractionLocationService;

@RestController
public class AttractionLocationController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AttractionLocationService attractionLocationService;
	
	@PostMapping("/attractions/distances")
	public ResponseEntity<TreeMap<Double,AttractionBean>> getDistancesToAttractionsMap (@RequestBody LocationBean location) throws GpsUtilException, InvalidLocationException{
		log.info("Processing the distances calculation to attractions from the provided user location."+System.lineSeparator()
		+"Returns a TreeMap where key = distance and value = attraction bean.");
		return ResponseEntity.ok(attractionLocationService.getDistancesToAttractions(location));
	}
	
}
