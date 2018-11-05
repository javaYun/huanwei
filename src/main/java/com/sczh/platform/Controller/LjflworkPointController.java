package com.sczh.platform.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sczh.platform.po.Et100;
import com.sczh.platform.po.KitchenInforecord;
import com.sczh.platform.po.LjflClean;
import com.sczh.platform.po.LjflTrashCanWorkPoint;
import com.sczh.platform.po.Model.KitchenInfoRecordPageDomain;
import com.sczh.platform.po.Model.TrashCleanPageDomain;
import com.sczh.platform.po.Model.TrashDetailPageDomain;
import com.sczh.platform.service.LjflTrashCanWorkPointService;
import com.sczh.platform.service.LjflcleanService;
import com.sczh.platform.service.Tub01Service;

/**
 * 智能垃圾桶满溢情况展现接口
 * 
 * @author yunluo
 *
 */

@Controller
@RequestMapping("/trashcan")
public class LjflworkPointController {
	@Autowired
	private LjflTrashCanWorkPointService LjflTrashCanWorkPointService;

	@Autowired
	private LjflcleanService ljflcleanService;

	@Autowired
	private Tub01Service tub01service;

	/**
	 * 展现垃圾满溢情况
	 * 
	 * @param username
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/getTrashCanPoint")
	public ModelAndView getTrashCanStatus() {

		List<LjflTrashCanWorkPoint> selectLjflTrashCanWorkPointList = LjflTrashCanWorkPointService
				.selectLjflTrashCanWorkPointList();
		ModelAndView mav = new ModelAndView();

		mav.addObject("LjflTrashCanWorkPointList", selectLjflTrashCanWorkPointList);
		mav.setViewName("baidumap/getTrashCanPoint");
		return mav;
	}*/

	
	/**
	 * 进入无卡电子称页面
	 *//*
	@ResponseBody
	@RequestMapping(value = "/electronicscale")
	public ModelAndView electronicscale(){
		
		ModelAndView mav = new ModelAndView("baidumap/electronicscale");
		return mav;
		
	}*/
	
	/**
	 * 获取无卡电子称称重明细
	 */
	@ResponseBody
	@RequestMapping(value = "/getelectronicscaleDetail")
	public KitchenInfoRecordPageDomain getelectronicscaleDetail(@RequestBody KitchenInfoRecordPageDomain recordPageDomain){
		
		System.out.println("进啦额了");
		
		KitchenInfoRecordPageDomain kitchenInfoRecordPageDomain = new KitchenInfoRecordPageDomain();
		kitchenInfoRecordPageDomain=recordPageDomain;
		int startSize = recordPageDomain.getPageSize()*(recordPageDomain.getCurrentPage()-1);
		
		List<KitchenInforecord> kitchenrecordinfoList = tub01service.kitchenrecordinfoList(startSize + "",
						recordPageDomain.getPageSize() + "");
		
		kitchenInfoRecordPageDomain.setContent(kitchenrecordinfoList);

		kitchenInfoRecordPageDomain.setTotalNum(tub01service
				.kitchenrecordinfoListTotal().size());

		System.out.println(kitchenInfoRecordPageDomain);
		
		return kitchenInfoRecordPageDomain;
	}
	
	
	/**
	 * 删除无卡电子称信息
	 */
	@ResponseBody
	@RequestMapping(value="deletekitcheninfo")
	public Map<String, String> deletekitcheninfo(@RequestBody String jsondate){
		
		Map<String, String> map = new HashMap<String, String>();
		
		JSONObject parseObject = JSONObject.parseObject(jsondate);
		String string = parseObject.getString("id");
		System.out.println("string1:"+string);
		String[] split = string.split(",");
		
		for (int i = 0; i < split.length; i++) {
			
			 tub01service.deleteByPrimaryKey(split[i]);
			
		}
		map.put("message", "删除成功");
		
		return  map;
	}
	
	/**
	 * 获取垃圾桶满溢明细
	 * 
	 * @param detailPageDomain
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTrashCanDetail")
	public TrashDetailPageDomain getTrashCanDetail(
			@RequestBody TrashDetailPageDomain detailPageDomain,
			HttpSession session) {

		TrashDetailPageDomain trashDetailPageDomain = new TrashDetailPageDomain();
		trashDetailPageDomain = detailPageDomain;
		int startSize = detailPageDomain.getPageSize()
				* (detailPageDomain.getCurrentPage() - 1);
		List<LjflTrashCanWorkPoint> selectLjflTrashCanWorkPointInfoList = LjflTrashCanWorkPointService
				.selectLjflTrashCanWorkPointInfoList(startSize + "",
						detailPageDomain.getPageSize() + "");

		int i = 0;
		for (LjflTrashCanWorkPoint LjflTrashCanWorkPoint : selectLjflTrashCanWorkPointInfoList) {

			List<Et100> et100ListTotal = tub01service.getEt100ListTotal();

			Et100 et100 = et100ListTotal.get(i);

			String sourcedevicetype = et100.getSourcedevicetype();

			if (sourcedevicetype.endsWith("electronicScale")) {

				String heightvalue = et100.getHeightvalue();
				LjflTrashCanWorkPoint.setTrashcanweight(heightvalue);
			} else {

				System.out.println(sourcedevicetype);
			}
			i++;

		}

		System.out.println(selectLjflTrashCanWorkPointInfoList);

		trashDetailPageDomain.setContent(selectLjflTrashCanWorkPointInfoList);

		trashDetailPageDomain.setTotalNum(LjflTrashCanWorkPointService
				.selectLjflTrashCanWorkPointInfoListTotal().size());

		System.out.println(selectLjflTrashCanWorkPointInfoList);
		
		System.out.println(trashDetailPageDomain.toString());
		
		return trashDetailPageDomain;

	}

	/**
	 * 展现垃圾清运情况
	 * 
	 * @param username
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/getCleanRecord")
	public ModelAndView getCleanRecord(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("baidumap/getCleanRecord");
		return mav;
	}*/

	/**
	 * 展现垃圾清运情况
	 * 
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCleanTable")
	public TrashCleanPageDomain getCleanTable(
			@RequestBody TrashCleanPageDomain trashcanPage, HttpSession session) {

		// 搜索的时间
		Date time = trashcanPage.getTime();
		System.out.println(time);
		String time2;
		if (time == null) {

			time2 = " ";
			System.out.println(time2);
		} else {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			time2 = ft.format(time);
		}

		System.out.println(time2);
		TrashCleanPageDomain trashCleanPageDomain = new TrashCleanPageDomain();
		trashCleanPageDomain = trashcanPage;
		int startSize = trashcanPage.getPageSize()
				* (trashcanPage.getCurrentPage() - 1);
		List<LjflClean> selectLjflCleanInfoList = ljflcleanService
				.selectLjflCleanInfoList(startSize + "",
						trashcanPage.getPageSize() + "", time2 + "");

		trashCleanPageDomain.setContent(selectLjflCleanInfoList);

		trashCleanPageDomain.setTotalNum(ljflcleanService
				.selectLjflCleanInfoListTotal(time2 + "").size());

		System.out.println(selectLjflCleanInfoList);
		return trashcanPage;
	}

	/**
	 * 插入垃圾桶重量
	 * 
	 * @param jsonData
	 * @return
	 */
	// 修改垃圾桶明细信息
	// http://localhost:8080/CDS/trashcan/insertdata
	@RequestMapping(value = "/insertdata", method = RequestMethod.POST)
	public @ResponseBody LjflTrashCanWorkPoint insertDevice(@RequestBody String jsonData) {

		System.out.println("jsondata==" + jsonData);

		// LjflTrashCanWorkPoint ljflexpand = new LjflTrashCanWorkPoint();

		JSONObject map = JSONObject.parseObject(jsonData);
		System.out.println("map==" + map);

		// 桶编号
		int id = map.getIntValue("id");

		System.out.println("id==" + id);

		LjflTrashCanWorkPoint ljflexpand = LjflTrashCanWorkPointService.selectByPrimaryKey(id);

		System.out.println("ljflexpend==" + ljflexpand.toString());

		ljflexpand.setTrashcanweight(map.getString("trashcanweight"));
		ljflexpand.setUploadtime(map.getDate("uploadtime"));
		// 高度
		String s1 = map.getString("string1").trim();
		System.out.println("s1==" + s1);

		int indexOf1 = s1.indexOf("S");
		int indexOf = s1.indexOf("=");
		String s2;
		if (s1.concat("=") != null) {

			s2 = s1.substring(indexOf + 1, s1.length());

		} else if (s1.concat("S") != null) {

			s2 = s1.substring(indexOf1 + 1, s1.length());

		} else {

			s2 = s1;
		}

		ljflexpand.setString1(s2);

		LjflTrashCanWorkPointService.updateByPrimaryKey(ljflexpand);

		return ljflexpand;
	}

	/*@RequestMapping(value = "/getBaidumap")
	public ModelAndView getBaidumap() {

		ModelAndView mav = new ModelAndView();

		LjflTrashCanWorkPoint selectByPrimaryKey = LjflTrashCanWorkPointService
				.selectByPrimaryKey(1);
		mav.setViewName("plan/baidumap");
		mav.addObject("info", selectByPrimaryKey);
		return mav;
	}*/

	@ResponseBody
	@RequestMapping(value = "/getBaidumapinfo")
	public List<LjflTrashCanWorkPoint> getBaidumapInfo() {

		List<LjflTrashCanWorkPoint> list = LjflTrashCanWorkPointService
				.selectLjflTrashCanWorkPointList();
		System.out.println("list==" + list);

		return list;

	}

}
