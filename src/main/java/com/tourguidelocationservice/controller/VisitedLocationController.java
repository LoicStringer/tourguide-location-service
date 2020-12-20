package com.tourguidelocationservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tourguidelocationservice.bean.LocationBean;
import com.tourguidelocationservice.service.VisitedLocationService;

@RestController
public class VisitedLocationController {
	
	@Autowired
	private VisitedLocationService visitedLocationService;

	@GetMapping("/users/{userId}/visited-locations/latest")
	public ResponseEntity<LocationBean> getUserLocation(@PathVariable UUID userId){
		return ResponseEntity.ok(visitedLocationService.getUserLocation(userId));
	}
}
