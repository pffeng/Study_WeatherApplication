package com.phantom.spring.cloud.weather.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
	@Autowired
	private RestTemplate restTemplate;
	
	//根据城市ID查询天气数据
	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		return this.doGetWeather(uri);
	}

	//根据城市名称查询天气数据
	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri = WEATHER_URI + "city=" + cityName;
		return this.doGetWeather(uri);
		}
	
    //对请求的信息进行解析,完成数据模型（对应用数据的封装）的构建
	public WeatherResponse doGetWeather(String uri) {
		ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
		
		ObjectMapper mapper = new ObjectMapper();//完成JSON和对象的转化
		WeatherResponse resp = null;
		String strBody = null;
		//请求成功则获取响应体，转化成WeatherReponse对象，数据模型构建完毕
		if (respString.getStatusCodeValue() == 200) {
			strBody = respString.getBody();
		} else {
			System.out.println("请求的URI：" + uri + " 不存在！");
		}
		try {
			resp = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}

}
