package com.phantom.spring.cloud.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phantom.spring.cloud.weather.vo.Weather;
import com.phantom.spring.cloud.weather.vo.WeatherResponse;

/**
 * Weather Report Service
 * @author Administrator
 *
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {
	@Autowired
	private WeatherDataService weatherDataService;
	
	/**
	 *  根据城市ID获取Weather类封装的天气信息(注：不是WeatherReponse封装的响应体信息)
	 * @param cityId
	 * @return
	 */
	@Override
	public Weather getDataByCityId(String cityId) {
		WeatherResponse resp = weatherDataService.getDataByCityId(cityId);
		return resp.getData();
	}

}
