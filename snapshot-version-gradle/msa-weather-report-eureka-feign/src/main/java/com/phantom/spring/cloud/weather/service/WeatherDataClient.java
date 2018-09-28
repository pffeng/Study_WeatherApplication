package com.phantom.spring.cloud.weather.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.phantom.spring.cloud.weather.vo.WeatherResponse;

/**
 * Weather Data Client.
 * @author Administrator
 *
 */
@FeignClient(name="msa-weather-data-eureka")
public interface WeatherDataClient {
	
	@GetMapping("/weather/cityId/{cityId}")
	WeatherResponse getDataByCityId(@PathVariable("cityId")String cityId);
}
