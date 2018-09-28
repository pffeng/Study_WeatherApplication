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
	private WeatherDataClient weatherDataClient;
	
	/**
	 *  ���ݳ���ID��ȡWeather���װ��������Ϣ(ע������WeatherReponse��װ����Ӧ����Ϣ)
	 * @param cityId
	 * @return
	 */
	@Override
	public Weather getDataByCityId(String cityId) {
		//����������API΢�������ṩ
		WeatherResponse resp = weatherDataClient.getDataByCityId(cityId);
		Weather data = resp.getData();
		return data;
	}

}
