package com.phantom.spring.cloud.weather.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.phantom.spring.cloud.weather.service.CityDataService;
import com.phantom.spring.cloud.weather.service.WeatherDataService;
import com.phantom.spring.cloud.weather.vo.City;

/**
 * Weather Data SyncJob.
 * Quartz��ÿ�������Ϊһ��Job
 * @author Administrator
 *
 */
public class WeatherDataSyncJob extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);
	@Autowired
	private CityDataService cityDataService;
	@Autowired
	private WeatherDataService weatherDataService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("Weather Data SyncJob. start!");
		//��ȡ����ID�б�
		List<City> cityList = null;
		try {
			cityList = cityDataService.listCity();
		} catch (Exception e) {
			logger.error("Exception! " + e);
		}
		//��������ID��ȡ����
		for(City city : cityList) {
			String cityId = city.getCityId();
			logger.info("Weather Data Sync Job, cityId: " + cityId);
			weatherDataService.syncDataByCityId(cityId);
		}
		logger.info("Weather Data SyncJob. end!");
	}

}
