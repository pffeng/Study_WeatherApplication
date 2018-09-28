package com.phantom.spring.cloud.weather.service;

import java.util.List;

import com.phantom.spring.cloud.weather.vo.City;

/**
 * City Data Service.
 * @author Administrator
 *
 */
public interface CityDataService {
		
	/**
	 * 获取City列表
	 * @return
	 * @throws Exception
	 */
	List<City> listCity() throws Exception;
	
}
