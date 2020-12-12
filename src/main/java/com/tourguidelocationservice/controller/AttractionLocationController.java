package com.tourguidelocationservice.controller;

import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.service.AttractionLocationService;

@RestController
public class AttractionLocationController {

	@Autowired
	private AttractionLocationService attractionLocationService;
	
	@GetMapping("/attractions")
	public ResponseEntity<List<AttractionBean>> getAttractions(){
		return ResponseEntity.ok(attractionLocationService.getAttractions());
	}
	
	@PostMapping("/attractions/distances")
	public ResponseEntity<TreeMap<Double,AttractionBean>> getDistancesToAttractions(@RequestBody LocationBean location){
		return ResponseEntity.ok(attractionLocationService.getDistancesToAttractions(location));
		
		
	}
	
}
