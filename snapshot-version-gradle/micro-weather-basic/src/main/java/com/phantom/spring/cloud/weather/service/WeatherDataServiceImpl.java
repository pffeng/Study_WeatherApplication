package com.phantom.spring.cloud.weather.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
	@Autowired
	private RestTemplate restTemplate;
	
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
		ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
		
		ObjectMapper mapper = new ObjectMapper();//���JSON�Ͷ����ת��
		WeatherResponse resp = null;
		String strBody = null;
		//����ɹ����ȡ��Ӧ�壬ת����WeatherReponse��������ģ�͹������
		if (respString.getStatusCodeValue() == 200) {
			strBody = respString.getBody();
		} else {
			System.out.println("�����URI��" + uri + " �����ڣ�");
		}
		try {
			resp = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}

}
