package com.phantom.spring.cloud.weather.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Weather Data Collection Server.
 * @author Administrator
 *
 */
@Service
public class WeatherDataCollectionServerImpl implements WeatherDataCollectionServer {
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
	private static final long TIME_OUT = 1800L;//���ñ����ڻ����е�ʱ��
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * ͬ����������
	 * @param cityId
	 */
	@Override
	public void syncDataByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		this.saveWeatherData(uri);//�����б��л�ȡ��URI���ݶ�ȡ���ݱ��浽���浱��
	}
	
	/**
	 * ������������
	 * @param uri
	 */
	private void saveWeatherData(String uri) {
		String key = uri;
		String strBody = null;
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		
		//���÷���ӿڻ�ȡ����
		ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
		if (respString.getStatusCodeValue() == 200) {
			strBody = respString.getBody();
		}
		
		//����д�뻺��
		ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
	}

}
