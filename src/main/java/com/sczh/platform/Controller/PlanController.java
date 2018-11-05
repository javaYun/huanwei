package com.sczh.platform.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 车辆作业调度控制类
 * @author yunluo
 *
 */
@Controller
@RequestMapping("plan")
public class PlanController {
	
	/**
	 * 进入作业计划展示页面
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/joinTranOperaPlan")
	public ModelAndView joinTranOperaPlan(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("plan/joinTranOperaPlan");
		return mav;
	}
	
	/**
	 * 进入垃圾桶溢满显示
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/trashCanOverflow")
	public ModelAndView trashCanOverflow(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("plan/trashCan");
		return mav;
	}
	
	/**
	 * 进入作业计划展示页面
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/addLifeGabargePage")
	public ModelAndView addLifeGabargePage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("plan/addLifeGabargePage");
		return mav;
	}
	

}
