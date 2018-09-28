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
 * WeatherDataService ʵ�֣��ӵ�����API��ȡ��������,����ģ�Ͳ����أ�
 * @author Administrator
 *
 */

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);//��ʱ����ʾ��
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
	private static final long TIME_OUT = 10L;//���ó�ʱ��ʱ��Ϊ10s
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	//���ݳ���ID��ѯ��������
	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		return this.doGetWeather(uri);
	}

	//���ݳ������Ʋ�ѯ��������
	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri = WEATHER_URI + "city=" + cityName;
		return this.doGetWeather(uri);
		}
	
    //���������Ϣ���н���,�������ģ�ͣ���Ӧ�����ݵķ�װ���Ĺ���
	public WeatherResponse doGetWeather(String uri) {
		String key = uri; //��Redis���ݿ��У�uri��Ϊ�������ݵ�Ψһ��ʶ
		String strBody = null;
		ObjectMapper mapper = new ObjectMapper();//���JSON�Ͷ����ת��
		WeatherResponse resp = null;
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();//��ȡ�ַ���������
		
		//��黺������ݣ������ȡ����
		if(stringRedisTemplate.hasKey(key)) {
			logger.info("Info: Redis has data.");
			strBody = ops.get(key);
		}else {
			//����û�е����ݣ�����õ����������ݽӿ�
			logger.info("Info: Redis doesn't have data.");
			ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
			if (respString.getStatusCodeValue() == 200) {
				strBody = respString.getBody();
			} else {
				logger.error("Error: " + "�����uri��" + uri + " �����ڣ�");
			}
			//����ȡ������д�뻺�浱��
			ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
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