package com.phantom.spring.cloud.weather.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.phantom.spring.cloud.weather.vo.City;
import com.phantom.spring.cloud.weather.vo.WeatherResponse;

/**
 * Data Client.
 * @author Administrator
 *
 */
@FeignClient(name="msa-weather-eureka-client-zuul")
public interface DataClient {
	/**
	 * 获取城市列表
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/city/cities")
	List<City> listCity() throws Exception;
	
	
	/**
	 * 根据城市ID查询天气数据
	 * @param cityId
	 * @return
	 */
	@GetMapping("/data/weather/cityId/{cityId}")
	WeatherResponse getDataByCityId(@PathVariable("cityId")String cityId);
}
