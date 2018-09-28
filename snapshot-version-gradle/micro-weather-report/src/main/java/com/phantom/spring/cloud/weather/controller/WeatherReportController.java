package com.phantom.spring.cloud.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.phantom.spring.cloud.weather.service.CityDataService;
import com.phantom.spring.cloud.weather.service.WeatherReportService;

/**
 * Weather Report Controller.
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/report")
public class WeatherReportController {
	
	//���ע��Ľӿ�ֻ��һ��ʵ����������Spring���ͨ��component scan�����Զ���interface��ʵ����װ����
	@Autowired
	private WeatherReportService weatherReportService;
	@Autowired
	private CityDataService cityDataService;
	
	//���ݳ���ID��ѯ�������ݣ���װ����ģ�Ͳ�������Ӧ��ҳ��
	@GetMapping("/cityId/{cityId}")
	//@PathVariabel���Խ� URL ��ռλ�������󶨵��������������������
	public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId,Model model) throws Exception {
		//����ģ��
		model.addAttribute("title", "Phantom������Ԥ��");
		model.addAttribute("cityId", cityId);
		model.addAttribute("cityList", cityDataService.listCity());
		model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
		
		return new ModelAndView("weather/report", "reportModel", model);
	}
	
}
