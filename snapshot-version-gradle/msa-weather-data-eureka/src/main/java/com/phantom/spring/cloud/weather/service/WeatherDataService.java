package com.phantom.spring.cloud.weather.service;

import com.phantom.spring.cloud.weather.vo.WeatherResponse;

/**
 * Weather Data Service
 * @author Administrator
 *
 */

public interface WeatherDataService {
	
	/**
	 * ���ݳ���ID��ѯ��������
	 * @param cityId
	 *
	 */
	WeatherResponse getDataByCityId(String cityId);
	
	/**
	 * ���ݳ������Ʋ�ѯ��������
	 * @param cityName
	 *
	 */
	WeatherResponse getDataByCityName(String cityName);
	
}
