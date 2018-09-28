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
	//针对注入的接口只有一个实现类的情况，Spring框架通过component scan可以自动将interface于实现组装起来
	@Autowired
	private WeatherReportService weatherReportService;
	@Autowired
	private CityClient cityClient;
	
	//根据城市ID查询天气数据，封装数据模型并返回响应的页面
	@GetMapping("/cityId/{cityId}")
	//@PathVariabel可以将 URL 中占位符参数绑定到控制器处理方法的入参中
	public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId,Model model) throws Exception {
		// 构建模型
		// 获取城市ID列表
		List<City> cityList = null;
		try {
			//由城市数据API微服务提供数据
			cityList = cityClient.listCity();
		} catch (Exception e) {
			logger.error("Exception! " + e);
		}
		model.addAttribute("title", "Phantom的天气预报");
		model.addAttribute("cityId", cityId);
		model.addAttribute("cityList", cityList);
		model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
		
		return new ModelAndView("weather/report", "reportModel", model);
	}
	
}
