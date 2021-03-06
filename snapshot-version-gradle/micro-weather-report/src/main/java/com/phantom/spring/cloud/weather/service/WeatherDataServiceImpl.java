package com.phantom.spring.cloud.weather.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	private static final long TIME_OUT = 1800L;//设置保存在缓存中的时间
	@Autowired
	private RestTemplate restTemplate;
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
	 * 对请求的信息进行解析,完成数据模型（对应用数据的封装）的构建
	 * @param uri
	 * @return
	 */
	private WeatherResponse doGetWeather(String uri) {
		String key = uri; //在Redis数据库中，uri作为天气数据的唯一标识
		String strBody = null;
		ObjectMapper mapper = new ObjectMapper();//完成JSON和对象的转化
		WeatherResponse resp = null;
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();//获取字符串操作类
		
		//检查缓存的数据，有则读取数据
		if(stringRedisTemplate.hasKey(key)) {
			logger.info("Info: Redis has data.");
			strBody = ops.get(key);
		}else {
			//没有的数据，则调用第三方的数据接口写入缓存
			logger.info("Info: Redis doesn't have data.");
			ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
			if (respString.getStatusCodeValue() == 200) {
				strBody = respString.getBody();
			} else {
				logger.error("Error! " + uri + "doesn't exist.");
			}
			//数据写入缓存
			ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
		}
		
		try {
			//将内容体转化为WeatherResponse对象
			resp = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			logger.error("Exception: " + e);
		}
		return resp;
	}
	
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
	 * 将天气数据缓存到Redis当中
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
		} else {
			logger.error("Error! " + uri + "doesn't exist.");
		}
		
		//数据写入缓存
		ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
	}

}
