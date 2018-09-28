package com.phantom.spring.cloud.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public String listCity() {
		return cityClient.listCity();
	}
}
