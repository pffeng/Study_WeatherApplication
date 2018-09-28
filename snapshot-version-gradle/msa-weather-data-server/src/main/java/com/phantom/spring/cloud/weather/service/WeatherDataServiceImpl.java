package com.phantom.spring.cloud.weather.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phantom.spring.cloud.weather.vo.WeatherResponse;

/**
 * WeatherDataService 实现（从第三方API获取天气数据,构建模型并返回）
 * @author Administrator
 *
 */

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);//有时不提示？
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 根据城市ID查询天气数据
	 * @param cityId
	 * @return
	 */
	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		return this.doGetWeather(uri);
	}

	/**
	 * 根据城市名称查询天气数据
	 * @param cityName
	 * @return
	 */
	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri = WEATHER_URI + "city=" + cityName;
		return this.doGetWeather(uri);
		}

	/**
	 * 根据返回的信息完成数据模型的构建
	 * @param uri
	 * @return
	 */
	private WeatherResponse doGetWeather(String uri) {
		String key = uri;
		String strBody = null;
		ObjectMapper mapper = new ObjectMapper();//完成JSON和对象的转化
		WeatherResponse resp = null;
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();//获取字符串操作类
		
		//检查缓存的数据，有则读取数据
		if(stringRedisTemplate.hasKey(key)) {
			logger.info("Info: Redis has data.");
			strBody = ops.get(key);
		}else {
			//没有的数据，抛出异常(天气数据微服务只提供数据查询的功能)
			logger.info("Info: Redis doesn't have data.");
			throw new RuntimeException("Don't have data.");
		}
		
		try {
			//将内容体转化为WeatherResponse对象
			resp = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			logger.error("Exception: " + e);
		}
		return resp;
	}

}
