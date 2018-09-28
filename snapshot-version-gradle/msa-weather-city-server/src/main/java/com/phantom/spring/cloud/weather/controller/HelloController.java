package com.phantom.spring.cloud.weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller.(作为测试)
 * @author Administrator
 *
 */

//该控制器处理后缀为hello的请求，返回JSON
@RestController
public class HelloController {
	//@RequestMapping("/hello")
	@GetMapping("/hello")
	public String hello() {
		return "hello world!";
	}
}
