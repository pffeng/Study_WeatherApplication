/**
 * 
 */
package com.phantom.spring.cloud.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.phantom.spring.cloud.weather.vo.City;
import com.phantom.spring.cloud.weather.vo.WeatherResponse;

/**
 * DataClient Fallback.
 * @author Administrator
 *
 */
@Component
public class DataClientFallback implements DataClient {

	@Override
	public List<City> listCity() throws Exception {
		List<City> cityList = null;
		cityList = new ArrayList<>();
		
		City city = new City();
		city.setCityId("101230601");
		city.setCityName("ÕÄÖÝ(Ä¬ÈÏ)");
		cityList.add(city);
		
		return cityList;
	}

	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		return null;
	}

}
