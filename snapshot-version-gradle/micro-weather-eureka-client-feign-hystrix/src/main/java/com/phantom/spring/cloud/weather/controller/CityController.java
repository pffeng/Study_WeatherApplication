package com.phantom.spring.cloud.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.phantom.spring.cloud.weather.service.CityClient;

/**
 * city controller.
 * @author Administrator
 *
 */

@RestController
public class CityController {
	@Autowired
	private CityClient cityClient;
	
	@GetMapping("/cities")
	@HystrixCommand(fallbackMethod="defaultCities")
	public String listCity() {
		return cityClient.listCity();
	}
	
	public String defaultCities(){
		return "City Data Server is down!";
	}
}
