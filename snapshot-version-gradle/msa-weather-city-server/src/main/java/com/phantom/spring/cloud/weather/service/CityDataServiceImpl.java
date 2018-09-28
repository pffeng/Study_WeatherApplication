package com.phantom.spring.cloud.weather.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.phantom.spring.cloud.weather.util.XmlBuilder;
import com.phantom.spring.cloud.weather.vo.City;
import com.phantom.spring.cloud.weather.vo.CityList;

/**
 * City Data Service.
 * @author Administrator
 *
 */
@Service
public class CityDataServiceImpl implements CityDataService {

	@Override
	public List<City> listCity() throws Exception {
		//读取XML文件
		Resource res = new ClassPathResource("citylist.xml");//根据类路径(Spring提供文件的名称)获取资源
		BufferedReader br = new BufferedReader(new InputStreamReader(res.getInputStream(), "utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while(null != (line = br.readLine())) {
			buffer.append(line);
		}
		br.close();
		//将XML文件转化为Java对象
		CityList cityList = (CityList)XmlBuilder.xmlStrToObject(CityList.class, buffer.toString());
		return cityList.getCityList();
	}

}
