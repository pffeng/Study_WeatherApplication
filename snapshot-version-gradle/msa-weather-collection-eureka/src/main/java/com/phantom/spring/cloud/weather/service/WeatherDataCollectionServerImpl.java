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
	private static final long TIME_OUT = 1800L;//设置保存在缓存中的时间
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 同步天气数据
	 * @param cityId
	 */
	@Override
	public void syncDataByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		this.saveWeatherData(uri);//将从列表中获取的URI依据读取数据保存到缓存当中
	}
	
	/**
	 * 缓存天气数据
	 * @param uri
	 */
	private void saveWeatherData(String uri) {
		String key = uri;
		String strBody = null;
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		
		//调用服务接口获取数据
		ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
		if (respString.getStatusCodeValue() == 200) {
			strBody = respString.getBody();
		}
		
		//数据写入缓存
		ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
	}

}
