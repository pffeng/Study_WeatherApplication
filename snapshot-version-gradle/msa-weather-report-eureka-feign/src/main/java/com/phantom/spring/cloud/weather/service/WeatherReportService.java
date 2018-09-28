package com.phantom.spring.cloud.weather.service;

import com.phantom.spring.cloud.weather.vo.Weather;

/**
 * Weather Report Service.
 * @author Administrator
 *
 */
public interface WeatherReportService {
	
	/**
	 *  ���ݳ���ID��ѯ������Ϣ
	 * @param cityId
	 * @return
	 */
	Weather getDataByCityId(String cityId);
	
}
