package com.tourguidelocationservice.proxy;

import java.util.List;
import java.util.UUID;

import com.tourguidelocationservice.bean.AttractionBean;
import com.tourguidelocationservice.bean.VisitedLocationBean;

public interface IGpsUtilProxy {
	
	VisitedLocationBean getUserLocation (UUID userId) throws Exception;
	List<AttractionBean> getAttractions() throws Exception;
}
