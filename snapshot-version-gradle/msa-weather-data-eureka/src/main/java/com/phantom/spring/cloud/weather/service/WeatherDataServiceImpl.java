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
 * WeatherDataService ʵ�֣��ӵ�����API��ȡ��������,����ģ�Ͳ����أ�
 * @author Administrator
 *
 */

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);//��ʱ����ʾ��
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * ���ݳ���ID��ѯ��������
	 * @param cityId
	 * @return
	 */
	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		return this.doGetWeather(uri);
	}

	/**
	 * ���ݳ������Ʋ�ѯ��������
	 * @param cityName
	 * @return
	 */
	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri = WEATHER_URI + "city=" + cityName;
		return this.doGetWeather(uri);
		}

	/**
	 * ���ݷ��ص���Ϣ�������ģ�͵Ĺ���
	 * @param uri
	 * @return
	 */
	private WeatherResponse doGetWeather(String uri) {
		String key = uri;
		String strBody = null;
		ObjectMapper mapper = new ObjectMapper();//���JSON�Ͷ����ת��
		WeatherResponse resp = null;
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();//��ȡ�ַ���������
		
		//��黺������ݣ������ȡ����
		if(stringRedisTemplate.hasKey(key)) {
			logger.info("Info: Redis has data.");
			strBody = ops.get(key);
		}else {
			//û�е����ݣ��׳��쳣(��������΢����ֻ�ṩ���ݲ�ѯ�Ĺ���)
			logger.info("Info: Redis doesn't have data.");
			throw new RuntimeException("Don't have data.");
		}
		
		try {
			//��������ת��ΪWeatherResponse����
			resp = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			logger.error("Exception: " + e);
		}
		return resp;
	}

}
