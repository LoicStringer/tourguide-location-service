package com.tourguidelocationservice;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import gpsUtil.GpsUtil;

@Configuration
public class TourguideLocationServiceConfig {

	@Bean
	public GpsUtil getGpsUtil() {
		Locale.setDefault(Locale.ENGLISH);
		return new GpsUtil();
	}

	@Bean
	   public MessageSource messageSource() {
	      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	      messageSource.setBasename("classpath:messages_exceptions");
	      messageSource.setDefaultEncoding("UTF-8");
	      return messageSource;
	   }
}
