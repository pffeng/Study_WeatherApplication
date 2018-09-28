package com.phantom.spring.cloud.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phantom.spring.cloud.weather.service.WeatherDataService;
import com.phantom.spring.cloud.weather.vo.WeatherResponse;

/**
 * Weather Controller.
 * @author Administrator
 *
 */

/**
 * �����Ҫ����JSON��XML���Զ���mediaType���ݵ�ҳ��,��Ӧ�ķ�����Ҫ��RestControllerע��
 * �����Ҫ���ص�ָ��ҳ��,��Ҫ��Controllerע�������ͼ������InternalResourceViewResolver
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	@Autowired
	//ע��Ľӿ�ֻ��һ��ʵ�ֵ������Spring���ͨ��component scan�����Զ���interface��ʵ����װ����
	private WeatherDataService weatherDataService;
	
	//���ݳ���ID��ѯ��������
	@GetMapping("/cityId/{cityId}")
	//@PathVariabel���Խ� URL ��ռλ�������󶨵��������������������
	public WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId) {
		return weatherDataService.getDataByCityId(cityId);
	}
	
	//���ݳ������Ʋ�ѯ��������
	//��Ϊ��֧�����Ĳ����Ĵ�ֵ�������ַ���Tomcat�°汾�����ڷǷ��ַ����޷�GET���󣬹�ʹ��POST����
	@PostMapping("/cityName")
	public WeatherResponse getDataByCityName(@RequestParam("name") String cityName) {
		/**
		 * ע�͵�ԭ��
		 * 1.Tomcat���͵�GET������Ҫת��
		 * 2.ת����URLû�з��ؽ����������Tomcatʵ���н��
		 * String encodeName = "";
		 *   try {
		 *	        encodeName = URLEncoder.encode(cityName, "utf-8");
		 *	 } catch (UnsupportedEncodingException e) {
		 *			// TODO Auto-generated catch block
		 *			e.printStackTrace();
		 *   }
		 */
		return weatherDataService.getDataByCityName(cityName);
	}
	
}
