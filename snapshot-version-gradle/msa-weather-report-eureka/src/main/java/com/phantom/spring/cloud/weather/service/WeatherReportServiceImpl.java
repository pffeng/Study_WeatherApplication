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
	 *  根据城市ID获取Weather类封装的天气信息(注：不是WeatherReponse封装的响应体信息)
	 * @param cityId
	 * @return
	 */
	@Override
	public Weather getDataByCityId(String cityId) {
		//TODO 改为由天气数据API微服务来提供
		Weather data = new Weather();
		data.setAqi("50");
		data.setCity("漳州");
		data.setGanmao("各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。");
		data.setWendu("34");
		
		Yesterday yesterday = new Yesterday();
		yesterday.setDate("12日星期日");
		yesterday.setFl("<![CDATA[<3级]]>");
		yesterday.setFx("南风");
		yesterday.setHigh("高温 39℃");
		yesterday.setLow("低温 27℃");
		yesterday.setType("多云");
		data.setYesterday(yesterday);
		
		List<Forecast> forecastList = new ArrayList<>();
		
		Forecast forecast = new Forecast();
		forecast.setDate("13日星期一");
		forecast.setType("多云");
		forecast.setFengli("<![CDATA[3-4级]]>");
		forecast.setFengxiang("东南风");
		forecast.setHigh("高温 38℃");
		forecast.setLow("低温 27℃");
		forecastList.add(forecast);
		
		forecast = new Forecast();
		forecast.setDate("14日星期二");
		forecast.setType("中雨");
		forecast.setFengli("<![CDATA[3-4级]]>");
		forecast.setFengxiang("东风");
		forecast.setHigh("高温 34℃");
		forecast.setLow("低温 27℃");
		forecastList.add(forecast);
		
		forecast = new Forecast();
		forecast.setDate("15日星期三");
		forecast.setType("小雨");
		forecast.setFengli("<![CDATA[3级]]>");
		forecast.setFengxiang("东南风");
		forecast.setHigh("高温 32℃");
		forecast.setLow("低温 27℃");
		forecastList.add(forecast);
		
		forecast = new Forecast();
		forecast.setDate("16日星期四");
		forecast.setType("小雨");
		forecast.setFengli("<![CDATA[3级]]>");
		forecast.setFengxiang("南风");
		forecast.setHigh("高温 35℃");
		forecast.setLow("低温 27℃");
		forecastList.add(forecast);
		
		forecast = new Forecast();
		forecast.setDate("17日星期五");
		forecast.setType("小雨");
		forecast.setFengli("<![CDATA[3级]]>");
		forecast.setFengxiang("西北风");
		forecast.setHigh("高温 37℃");
		forecast.setLow("低温 27℃");
		forecastList.add(forecast);
		
		data.setForcast(forecastList);
		return data;
	}

}
