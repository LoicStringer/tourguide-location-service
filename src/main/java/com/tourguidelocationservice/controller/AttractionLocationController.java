package com.tourguidelocationservice.controller;

import java.util.TreeMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.service.AttractionLocationService;

@RestController
public class AttractionLocationController {

	@Autowired
	private AttractionLocationService attractionLocationService;
	
	@PostMapping("/attractions/distances")
	public ResponseEntity<TreeMap<Double,AttractionBean>> getDistancesToAttractionsMap (@RequestBody @Valid LocationBean location) throws GpsUtilException{
		return ResponseEntity.ok(attractionLocationService.getDistancesToAttractions(location));
	}
	
}
