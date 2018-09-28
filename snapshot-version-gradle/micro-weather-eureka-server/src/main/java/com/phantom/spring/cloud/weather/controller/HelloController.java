package com.phantom.spring.cloud.weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello controller.
 * @author Administrator
 *
 */

@RestController
public class HelloController {
	//@RequestMapping("/hello")
	@GetMapping("/hello")
	public String hello() {
		return "hello world!";
	}
}
