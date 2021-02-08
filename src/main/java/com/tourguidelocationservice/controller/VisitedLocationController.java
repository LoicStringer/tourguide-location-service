package com.tourguidelocationservice.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tourguidelocationservice.bean.VisitedLocationBean;
import com.tourguidelocationservice.exception.GpsUtilException;
import com.tourguidelocationservice.exception.UserServiceException;
import com.tourguidelocationservice.service.VisitedLocationService;

@RestController
public class VisitedLocationController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private VisitedLocationService visitedLocationService;

	@GetMapping("/users/{userId}/locations/latest")
	public ResponseEntity<VisitedLocationBean> getUserLocation(@PathVariable UUID userId) throws GpsUtilException, UserServiceException{
		log.info("Processing user location.Returns a visited location bean from external library GpsUtil.");
		return ResponseEntity.ok(visitedLocationService.getUserLocation(userId));
	}
}
