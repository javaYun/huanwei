package com.sczh.platform.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("chart")
public class ChartController {

	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/chart1")
	private ModelAndView chart1() {
		
		ModelAndView mav = new ModelAndView("chart/ichartjs1");
		
		return mav;
	}
	
	
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/chart2")
	private ModelAndView chart2() {
		
		ModelAndView mav = new ModelAndView("chart/ichartjs2");
		
		return mav;
	}
	
	
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/chart3")
	private ModelAndView chart3() {
		
		ModelAndView mav = new ModelAndView("chart/ichartjs3");
		
		return mav;
	}
	
}
