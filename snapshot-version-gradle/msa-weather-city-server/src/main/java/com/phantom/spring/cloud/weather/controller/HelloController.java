package com.phantom.spring.cloud.weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller.(��Ϊ����)
 * @author Administrator
 *
 */

//�ÿ����������׺Ϊhello�����󣬷���JSON
@RestController
public class HelloController {
	//@RequestMapping("/hello")
	@GetMapping("/hello")
	public String hello() {
		return "hello world!";
	}
}
