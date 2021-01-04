package com.tourguidelocationservice.proxy;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tourguidelocationservice.bean.VisitedLocationBean;
import com.tourguidelocationservice.validation.ValidUUID;

@FeignClient(name = "tourguide-user-service", url= "localhost:9001")
public interface UserProxy {

	@GetMapping("/users/{userId}/visited-locations/latest")
	VisitedLocationBean getUserLatestVisitedLocation(@PathVariable("userId") @ValidUUID UUID userId);
	
	@PostMapping("/users/{userId}/visited-locations")
	VisitedLocationBean addUserVisitedLocation(@PathVariable ("userId") @ValidUUID UUID userId, @RequestBody VisitedLocationBean visitedLocation);
}
