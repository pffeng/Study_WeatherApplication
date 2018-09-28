package com.phantom.spring.cloud.weather.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//ָ����Ҫ���ѵķ���
@FeignClient(name="msa-weather-city-eureka")
public interface CityClient {
	
	@GetMapping("/cities")
	public String listCity();
	
}
