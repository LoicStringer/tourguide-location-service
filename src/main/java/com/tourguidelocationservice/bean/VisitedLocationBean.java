package com.tourguidelocationservice.bean;

import java.util.Date;
import java.util.UUID;

import gpsUtil.location.Location;



public class VisitedLocationBean {

	private UUID userId;
	private Location location;
	private Date timeVisited;
	
	public VisitedLocationBean() {
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
