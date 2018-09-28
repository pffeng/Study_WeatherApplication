package com.phantom.spring.cloud.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.phantom.spring.cloud.weather.vo.Forecast;
import com.phantom.spring.cloud.weather.vo.Weather;
import com.phantom.spring.cloud.weather.vo.Yesterday;

/**
 * Weather Report Service
 * @author Administrator
 *
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {
	/**
	 *  ���ݳ���ID��ȡWeather���װ��������Ϣ(ע������WeatherReponse��װ����Ӧ����Ϣ)
	 * @param cityId
	 * @return
	 */
	@Override
	public Weather getDataByCityId(String cityId) {
		//TODO ��Ϊ����������API΢�������ṩ
		Weather data = new Weather();
		data.setAqi("50");
		data.setCity("����");
		data.setGanmao("���������������ˣ�������ð���ʽϵ͡�������ⳤ�ڴ��ڿյ������У��Է���ð��");
		data.setWendu("34");
		
		Yesterday yesterday = new Yesterday();
		yesterday.setDate("12��������");
		yesterday.setFl("<![CDATA[<3��]]>");
		yesterday.setFx("�Ϸ�");
		yesterday.setHigh("���� 39��");
		yesterday.setLow("���� 27��");
		yesterday.setType("����");
		data.setYesterday(yesterday);
		
		List<Forecast> forecastList = new ArrayList<>();
		
		Forecast forecast = new Forecast();
		forecast.setDate("13������һ");
		forecast.setType("����");
		forecast.setFengli("<![CDATA[3-4��]]>");
		forecast.setFengxiang("���Ϸ�");
		forecast.setHigh("���� 38��");
		forecast.setLow("���� 27��");
		forecastList.add(forecast);
		
		forecast = new Forecast();
		forecast.setDate("14�����ڶ�");
		forecast.setType("����");
		forecast.setFengli("<![CDATA[3-4��]]>");
		forecast.setFengxiang("����");
		forecast.setHigh("���� 34��");
		forecast.setLow("���� 27��");
		forecastList.add(forecast);
		
		forecast = new Forecast();
		forecast.setDate("15��������");
		forecast.setType("С��");
		forecast.setFengli("<![CDATA[3��]]>");
		forecast.setFengxiang("���Ϸ�");
		forecast.setHigh("���� 32��");
		forecast.setLow("���� 27��");
		forecastList.add(forecast);
		
		forecast = new Forecast();
		forecast.setDate("16��������");
		forecast.setType("С��");
		forecast.setFengli("<![CDATA[3��]]>");
		forecast.setFengxiang("�Ϸ�");
		forecast.setHigh("���� 35��");
		forecast.setLow("���� 27��");
		forecastList.add(forecast);
		
		forecast = new Forecast();
		forecast.setDate("17��������");
		forecast.setType("С��");
		forecast.setFengli("<![CDATA[3��]]>");
		forecast.setFengxiang("������");
		forecast.setHigh("���� 37��");
		forecast.setLow("���� 27��");
		forecastList.add(forecast);
		
		data.setForcast(forecastList);
		return data;
	}

}
