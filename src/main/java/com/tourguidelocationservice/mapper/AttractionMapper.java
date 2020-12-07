package com.tourguidelocationservice.mapper;

import com.tourguidelocationservice.bean.AttractionBean;

import gpsUtil.location.Attraction;

public class AttractionMapper {
	
	public AttractionBean mapAttraction(Attraction attraction) {
		AttractionBean attractionBean = new AttractionBean();
		attractionBean.setAttractionName(attraction.attractionName);
		attractionBean.setCity(attraction.city);
		attractionBean.setState(attraction.state);
		attractionBean.setLongitude(attraction.longitude);
		attractionBean.setLatitude(attraction.latitude);
		return attractionBean;
	}

}
