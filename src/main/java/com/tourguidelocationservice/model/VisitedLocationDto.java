package com.tourguidelocationservice.model;

import java.util.Date;
import java.util.UUID;

import gpsUtil.location.Location;



public class VisitedLocationDto {

	private UUID userId;
	private Location location;
	private Date timeVisited;
	
	public VisitedLocationDto() {
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getTimeVisited() {
		return timeVisited;
	}

	public void setTimeVisited(Date timeVisited) {
		this.timeVisited = timeVisited;
	}
	
}
