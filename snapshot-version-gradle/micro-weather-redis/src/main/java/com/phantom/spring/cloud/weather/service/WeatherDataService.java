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
	 * @author Administrator
	 *
	 */
	WeatherResponse getDataByCityId(String cityId);
	
	/**
	 * ���ݳ������Ʋ�ѯ��������
	 * @author Administrator
	 *
	 */
	WeatherResponse getDataByCityName(String cityName);
	
}
