package com.phantom.spring.cloud.weather.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.phantom.spring.cloud.weather.service.CityClient;
import com.phantom.spring.cloud.weather.service.WeatherReportService;
import com.phantom.spring.cloud.weather.vo.City;

/**
 * Weather Report Controller.
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/report")
public class WeatherReportController {
	private static final Logger logger = LoggerFactory.getLogger(WeatherReportController.class);
	//���ע��Ľӿ�ֻ��һ��ʵ����������Spring���ͨ��component scan�����Զ���interface��ʵ����װ����
	@Autowired
	private WeatherReportService weatherReportService;
	@Autowired
	private CityClient cityClient;
	
	//���ݳ���ID��ѯ�������ݣ���װ����ģ�Ͳ�������Ӧ��ҳ��
	@GetMapping("/cityId/{cityId}")
	//@PathVariabel���Խ� URL ��ռλ�������󶨵��������������������
	public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId,Model model) throws Exception {
		// ����ģ��
		// ��ȡ����ID�б�
		List<City> cityList = null;
		try {
			//�ɳ�������API΢�����ṩ����
			cityList = cityClient.listCity();
		} catch (Exception e) {
			logger.error("Exception! " + e);
		}
		model.addAttribute("title", "Phantom������Ԥ��");
		model.addAttribute("cityId", cityId);
		model.addAttribute("cityList", cityList);
		model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
		
		return new ModelAndView("weather/report", "reportModel", model);
	}
	
}
