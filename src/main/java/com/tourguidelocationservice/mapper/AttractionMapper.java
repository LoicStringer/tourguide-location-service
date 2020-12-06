package com.tourguidelocationservice.mapper;

import org.springframework.stereotype.Component;

import com.tourguidelocationservice.bean.AttractionBean;

import gpsUtil.location.Attraction;

@Component
public class AttractionMapper  {
	
	public AttractionBean mapAttraction(Attraction attraction) {
		AttractionBean attractionBean = new AttractionBean();
		attractionBean.setAttractionName(attraction.attractionName);
		attractionBean.setAttractionCity(attraction.city);
		attractionBean.setAttractionState(attraction.state);
		attractionBean.setLatitude(attraction.latitude);
		attractionBean.setLongitude(attraction.longitude);
		return attractionBean;
	}
	
	

}
