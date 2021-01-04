package com.tourguidelocationservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tourguidelocationservice.bean.VisitedLocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.service.VisitedLocationService;
import com.tourguidelocationservice.validation.ValidUUID;

@RestController
public class VisitedLocationController {
	
	@Autowired
	private VisitedLocationService visitedLocationService;

	@GetMapping("/users/{userId}/visited-locations/latest")
	public ResponseEntity<VisitedLocationBean> getUserLocation(@PathVariable @ValidUUID UUID userId) throws GpsUtilException{
		return ResponseEntity.ok(visitedLocationService.getUserLocation(userId));
	}
}
