package com.phantom.spring.cloud.weather.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.phantom.spring.cloud.weather.vo.City;

/**
 * City Client.
 * @author Administrator
 *
 */
@FeignClient(name="msa-weather-city-eureka")
public interface CityClient {
	
	@GetMapping("/cities")
	List<City> listCity() throws Exception;
}
