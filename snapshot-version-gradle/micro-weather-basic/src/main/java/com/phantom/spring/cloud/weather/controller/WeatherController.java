package com.phantom.spring.cloud.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phantom.spring.cloud.weather.service.WeatherDataService;
import com.phantom.spring.cloud.weather.vo.WeatherResponse;

/**
 * Weather Controller.
 * @author Administrator
 *
 */

/**
 * 如果需要返回JSON，XML或自定义mediaType内容到页面,对应的方法需要用RestController注释
 * 如果需要返回到指定页面,需要用Controller注释配合视图解析器InternalResourceViewResolver
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	@Autowired
	//注入的接口只有一个实现的情况，Spring框架通过component scan可以自动将interface于实现组装起来
	private WeatherDataService weatherDataService;
	
	//根据城市ID查询天气数据
	@GetMapping("/cityId/{cityId}")
	//@PathVariabel可以将 URL 中占位符参数绑定到控制器处理方法的入参中
	public WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId) {
		return weatherDataService.getDataByCityId(cityId);
	}
	
	//根据城市名称查询天气数据
	//因为需支持中文参数的传值，中文字符在Tomcat新版本中属于非法字符，无法GET请求，故使用POST请求
	@PostMapping("/cityName")
	public WeatherResponse getDataByCityName(@RequestParam("name") String cityName) {
		/**
		 * 注释的原因
		 * 1.Tomcat发送的GET请求不需要转码
		 * 2.转码后的URL没有返回结果，但不经Tomcat实测有结果
		 * String encodeName = "";
		 *   try {
		 *	        encodeName = URLEncoder.encode(cityName, "utf-8");
		 *	 } catch (UnsupportedEncodingException e) {
		 *			// TODO Auto-generated catch block
		 *			e.printStackTrace();
		 *   }
		 */
		return weatherDataService.getDataByCityName(cityName);
	}
	
}
