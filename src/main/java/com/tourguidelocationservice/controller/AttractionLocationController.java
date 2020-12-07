package com.tourguidelocationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourguidelocationservice.service.AttractionLocationService;

import gpsUtil.location.Attraction;

@RestController
public class AttractionLocationController {

	@Autowired
	private AttractionLocationService attractionLocationService;
	
	@GetMapping(value = "/attractions")
	public ResponseEntity<List<Attraction>> getAttractions(){
		return ResponseEntity.ok(attractionLocationService.getAttractions());
	}
	
	
}
