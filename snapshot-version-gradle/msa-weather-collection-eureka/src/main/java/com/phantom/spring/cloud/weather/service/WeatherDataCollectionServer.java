package com.phantom.spring.cloud.weather.service;

/**
 * Weather Data Collection Server.
 * @author Administrator
 *
 */
public interface WeatherDataCollectionServer {
	
	/**
	 * ���ݳ���IDͬ����������
	 * @param cityId
	 */
	void syncDataByCityId(String cityId);
	
}
