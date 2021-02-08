package com.tourguidelocationservice.proxy;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tourguidelocationservice.bean.VisitedLocationBean;

@FeignClient(name = "tourguide-user-service", url= "localhost:9001")
public interface UserProxy {

	@PostMapping("/users/{userId}/locations")
	VisitedLocationBean addUserVisitedLocation(@PathVariable ("userId") UUID userId, @RequestBody VisitedLocationBean visitedLocation);
}
