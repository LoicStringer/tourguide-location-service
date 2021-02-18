package com.tourguidelocationservice.proxy;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tourguidelocationservice.bean.VisitedLocationBean;

@FeignClient(name = "${feignClient.userService.name}", url= "${feignClient.userService.url}")
public interface UserProxy {

	@PostMapping("/users/{userId}/locations")
	VisitedLocationBean addUserVisitedLocation(@PathVariable ("userId") UUID userId, @RequestBody VisitedLocationBean visitedLocation);
}
