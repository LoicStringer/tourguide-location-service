package com.tourguidelocationservice;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gpsUtil.GpsUtil;

@Configuration
public class TourguideLocationServiceConfig {

	@Bean
	public GpsUtil getGpsUtil() {
		Locale.setDefault(Locale.ENGLISH);
		return new GpsUtil();
	}
	
	
}
