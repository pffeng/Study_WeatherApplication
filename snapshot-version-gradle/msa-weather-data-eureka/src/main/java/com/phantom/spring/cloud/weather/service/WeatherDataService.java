package com.phantom.spring.cloud.weather.service;

import com.phantom.spring.cloud.weather.vo.WeatherResponse;

/**
 * Weather Data Service
 * @author Administrator
 *
 */

public interface WeatherDataService {
	
	/**
	 * 根据城市ID查询天气数据
	 * @param cityId
	 *
	 */
	WeatherResponse getDataByCityId(String cityId);
	
	/**
	 * 根据城市名称查询天气数据
	 * @param cityName
	 *
	 */
	WeatherResponse getDataByCityName(String cityName);
	
}
