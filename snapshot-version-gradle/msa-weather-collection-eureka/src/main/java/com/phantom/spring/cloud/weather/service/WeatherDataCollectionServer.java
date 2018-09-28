package com.phantom.spring.cloud.weather.service;

/**
 * Weather Data Collection Server.
 * @author Administrator
 *
 */
public interface WeatherDataCollectionServer {
	
	/**
	 * 根据城市ID同步天气数据
	 * @param cityId
	 */
	void syncDataByCityId(String cityId);
	
}
