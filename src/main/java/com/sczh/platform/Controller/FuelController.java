package com.sczh.platform.Controller;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
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

import com.sczh.platform.po.CarInfo;
import com.sczh.platform.po.DeviceInfo;
import com.sczh.platform.po.Et100;
import com.sczh.platform.po.MenuInfo;
import com.sczh.platform.po.Point;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.po.WorkPoint;
import com.sczh.platform.po.Model.PageModelDomain;
import com.sczh.platform.po.Model.WorkPointRate;
import com.sczh.platform.service.CdsService;
import com.sczh.platform.service.DeviceInfoService;
import com.sczh.platform.service.MenuInfoService;
import com.sczh.platform.service.Tub01Service;
import com.sczh.platform.service.UserService;
import com.yunlao.uti.gpspoint.DetermineGpsRange;
import com.yunlao.util.MyLong;
import com.yunlao.util.SimpleDateUtil;

@Controller
@RequestMapping("fuel")
public class FuelController {
	@Autowired
	private Tub01Service tub01Service;

	@Autowired
	private MenuInfoService menuInfoService;
	
	@Autowired
	private CdsService cdsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeviceInfoService deviceInfoService;

	
	/**
	 * 跳转到设备数据显示页面
	 * 
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/disPlayDeviceGpsMap")
	public ModelAndView disPlayDeviceGpsMap(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("fuel/disPlayDeviceGpsMap");
	
		return mav;
	}*/
	/**
	 * 跳转到设备数据显示页面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/baidumap")
	public ModelAndView baidumap(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("baidumap/baidumap");
	
		return mav;
	}

	/**
	 * 跳转到sczhGps显示页面 -- SCZHGPS
	 * 
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/displaySczhGps")
	public ModelAndView displaySczhGps(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("fuel/displaySczhGps");
		
		return mav;
	}*/
	
	/**
	 * 获取Tub01油耗表数据
	 */

	@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getSczhListDate")
	public List<Et100> getSczhListDate() {
		List<Et100> list = tub01Service.getEt100ListTotal();
		List<Et100> list1 = new ArrayList<Et100>();
		for (Et100 e : list) {
			if(e.getSourcedevicetype().endsWith("SCZHGPS")){
				list1.add(e);
			}
		}
		return list1;
	}

	/**
	 * 获取Tub01油耗表数据
	 */
	@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getTubListDate")
	public List<Et100> getTubListDate(HttpSession session) {
		/**
		 * 获取userId Admin等超级账号，unitID为0
		 */
		String userid= "";
		
		try {
			 userid=session.getAttribute("userid").toString();//根据session会话获取当前useId
		} catch (Exception e) {
			userid= "";
		
		}
			UserInfo user =userService.selectByPrimaryKey(userid);
			String unitId= "";
			String unitName = "";
			
			if(user != null){
				unitId= user.getUnitid();
				unitName = user.getUnitname();
	
			}
			/**
			 * 非超级管理员的情况下，查看自己单位设备的油耗情况
			 */
			List<DeviceInfo> devices = new ArrayList<DeviceInfo>();
			if(unitId != null){
				devices = deviceInfoService.getDeviceInfoListTotalByUnit(unitId);//获取当前单位的devices
			}
		List<Et100> list = tub01Service.getEt100ListTotal();
		List<Et100> list1 = new ArrayList<Et100>();
		for (Et100 e : list) {
			if(e.getSourcedevicetype().endsWith("TUB01")){
				list1.add(e);
			}
		}
		return list1;
	}

	/**
	 * 跳转到车辆信息页面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/backTrack")
	public ModelAndView backTrack(HttpServletRequest request,HttpSession session) {
		ModelAndView mav = new ModelAndView("baidumap/backTrack");
		/**
		 * 获取userId Admin等超级账号，unitID为0
		 */
		String userid= "";
		
		try {
			 userid=session.getAttribute("userid").toString();//根据session会话获取当前useId
		} catch (Exception e) {
			userid= "";
		
		}
			UserInfo user =userService.selectByPrimaryKey(userid);
			String unitId= "";
			String unitName = "";
			
			if(user != null){
				unitId= user.getUnitid();
				unitName = user.getUnitname();
	
			}
		/**
		 * 获取车辆信息并且放到轨迹页面
		 */
			List<CarInfo> carInfos = new ArrayList<CarInfo>();
			if(unitId != null){
				carInfos = cdsService.getCarInfoListTotal(unitId);
			}else{
				carInfos = cdsService.getCarInfoListTotal();
			}
		mav.addObject("carInfos", carInfos);
		return mav;
	}


	/**
	 * 获取et100数据
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getEt100ListDate")
	public Map<String, Object> getEt100ListDate(@RequestBody Map<String, String> map1, HttpSession session) {
		String sourceDeviceId = (String)map1.get("sourcedeviceid");
	    String endDate = (String)map1.get("endDate");
	    String startDate = (String)map1.get("startDate");
	    Map<String, Object> map = new HashMap();
	    List<Et100> list = null;
	    if ("-001".equals(sourceDeviceId)) {
	      list = this.tub01Service.getEt100ListTotal();
	    } else if (startDate.isEmpty()) {
	      list = this.tub01Service.getEt100ListTotalBySourcedeviceid(sourceDeviceId);
	    } else {
	      list = this.tub01Service.getEt100sBetweenTimeAndSouceDeviceId("2017-10-12 00:00:00", "2017-10-13 00:00:00", sourceDeviceId);
	    }
	    if (list.size() > 0)
	    {
	      map.put("list", list);
	      Point point = selectPointById(session);
	      map.put("point", point);
	      if (point != null) {
	        map.put("isPointInPolygon", Boolean.valueOf(isPointInPolygon(list, point)));
	      }
	    }
	    return map;

	}


	/**
	 * 判断GPS是否在围城范围内
	 * @return
	 */
	private boolean isPointInPolygon(List<Et100> list, Point point) {
		
		boolean isPointInPolygon = true;
		for (Et100 et100 : list) {
			Point2D.Double p = new Point2D.Double(Double.parseDouble(et100
					.getGpsLongitude()), Double.parseDouble(et100
					.getGpsLatitude()));

			List<Point2D.Double> pts = new ArrayList<Point2D.Double>();
			pts.add(new Point2D.Double(Double.parseDouble(point.getPoint1()
					.split(",")[0]), Double.parseDouble(point.getPoint1()
					.split(",")[1])));
			pts.add(new Point2D.Double(Double.parseDouble(point.getPoint2()
					.split(",")[0]), Double.parseDouble(point.getPoint2()
					.split(",")[1])));
			pts.add(new Point2D.Double(Double.parseDouble(point.getPoint3()
					.split(",")[0]), Double.parseDouble(point.getPoint3()
					.split(",")[1])));
			pts.add(new Point2D.Double(Double.parseDouble(point.getPoint4()
					.split(",")[0]), Double.parseDouble(point.getPoint4()
					.split(",")[1])));
			isPointInPolygon = DetermineGpsRange.IsPtInPoly(p, pts);

		}

		return isPointInPolygon;
	}

	/**
	 * 跳转到设备信息页面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/electronicSetting")
	public ModelAndView electronicSetting(HttpSession session) {
		ModelAndView mav = new ModelAndView("baidumap/electronicSetting");
		/**
		 * 获取userId Admin等超级账号，unitID为0
		 */
		String userid= "";
		
		try {
			 userid=session.getAttribute("userid").toString();//根据session会话获取当前useId
		} catch (Exception e) {
			userid= "";
		
		}
			UserInfo user =userService.selectByPrimaryKey(userid);
			String unitId= "";
			String unitName = "";
			if(user != null){
				unitId= user.getUnitid();
				unitName = user.getUnitname();
				
			}
			/**
			 * 根据unitId  获取当前单位所使用的GPS设备
			 */
			List<DeviceInfo> list = new ArrayList<DeviceInfo>();
			if(unitId != null){
				list = deviceInfoService.getDeviceInfoListTotalByUnitAndDeviceType(unitId, "GPS设备");
				
			}else{
				list = deviceInfoService.getDeviceInfoListTotalByDeviceType("GPS设备");
			}
			mav.addObject("deviceLists", list);
		return mav;

	}

	/**
	 * 保存电子围栏信息
	 * 
	 * @param carInfo
	 * @return
	 */
	@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/inserPoint", method = RequestMethod.POST)
	public int inserPoint(@RequestBody Point point,HttpSession session) {// 添加到数据库表Point
		/**
		 * 获取userId Admin等超级账号，unitID为0
		 */
		String userid= "";
		
		try {
			 userid=session.getAttribute("userid").toString();//根据session会话获取当前useId
		} catch (Exception e) {
			userid= "";
		
		}
			UserInfo user =userService.selectByPrimaryKey(userid);
			String unitId= "";
			String unitName = "";
			
			if(user != null){
				unitId= user.getUnitid();
				unitName = user.getUnitname();
				point.setUserid(userid);
				point.setUnitname(unitName);
				point.setUnitid(unitId);
			}
			String id = "";
		if(unitId != null){//保证每个单位只有一个unitID
			id = unitId+"0001";
		}else{
			id = "0001";
		}
		point.setId(id);
		if (tub01Service.selectPointByPrimaryKey(id) != null) {
			return tub01Service.updatePointByPrimaryKeySelective(point);
		} else {
			return tub01Service.insertPoint(point);
		}

	}

	/**
	 * 获取电子围栏数据
	 * @param carInfo
	 * @return
	 */

	private Point selectPointById(HttpSession session) {// 添加到数据库表Point
		/**
		 * 获取userId Admin等超级账号，unitID为0
		 */
		String userid= "";
		
		try {
			 userid=session.getAttribute("userid").toString();//根据session会话获取当前useId
		} catch (Exception e) {
			userid= "";
		
		}
			UserInfo user =userService.selectByPrimaryKey(userid);
			String unitId= "";
			String unitName = "";
			
			if(user != null){
				unitId= user.getUnitid();
				unitName = user.getUnitname();
	
			}
			String id = "";
		if(unitId != null){//保证每个单位只有一个unitID
			id = unitId+"0001";
		}else{
			id = "0001";
		}
		Point p = tub01Service.selectPointByPrimaryKey(id);
		return p;
	}

	/**
	 * 跳转到工作站点页面
	 * 
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/workPoint")
	public ModelAndView workPoint() {
		ModelAndView mav = new ModelAndView("fuel/workPoint");

		return mav;
	}*/

	/**
	 * 获取到垃圾站点的数据
	 */
	@ResponseBody
	@RequestMapping("getWorkPointsData")
	public PageModelDomain getWorkPointsData(@RequestBody PageModelDomain pageModel,HttpSession session) {
		/**
		 * 获取userId Admin等超级账号，unitID为0
		 */
		String userid= "";
		
		try {
			 userid=session.getAttribute("userid").toString();//根据session会话获取当前useId
			
		} catch (Exception e) {
			userid= "";
		
		}
			UserInfo user =userService.selectByPrimaryKey(userid);
			String unitId= "";
			if(user != null){
				unitId= user.getUnitid();
			}
		PageModelDomain model = new PageModelDomain();
		List<WorkPoint> workPoints = new ArrayList<WorkPoint>();
		List<Et100> et100sNoNuUnit = tub01Service.getEt100ListByTime(pageModel.getStartTime(),pageModel.getEndTime());// 根据时间获取到相应的gps数据	
		List<Et100> et100s = new ArrayList<Et100>();
		if(unitId == null){//对于超级管理员，查看所有站点的频率
			workPoints = tub01Service.getWorkPointList();// 先获取到所有的站点
			model.setPageTotalSize(workPoints.size());
			
		}else{
			workPoints = tub01Service.getWorkPointListByUnit(unitId);// 先获取到所有的站点,只获取自己站点的数据
			model.setPageTotalSize(workPoints.size());
			
		}
		
		List<WorkPointRate> workRates = new ArrayList<WorkPointRate>();
		/**
		 * ① 600个站点分组统计，统计完数据然后根据频率进行排序 ② 组合每个站点的频率和时间
		 */
		for (int i = 0; i < workPoints.size(); i++) {
			WorkPoint point = workPoints.get(i);
			boolean isInWorkoint = false;// true表示在站点范围内
			boolean isOutWorkPoint = true;// true表示在站点范围外
			Date startTime = null;// Gps进入工作站点的起始时间
			Date endTime = null;// GPS进入工作站点的结束时间
			long workTime = 0l;// 进入站点的总时间
			int workNum = 0;// 进入站点的工作次数
			WorkPointRate workRate = new WorkPointRate();
			for (Et100 et100 : et100s) {
				if(unitId != null){//匹配是否为本单位的设备
					DeviceInfo device = deviceInfoService.selectByPrimaryKey(et100.getSourcedeviceid());
					if(device == null){
						return null;
					}else{
						if(!unitId.equals(device.getUnitid())){
							return null;
						}
					}
				}
				Point2D.Double p = new Point2D.Double(Double.parseDouble(et100
						.getGpsLongitude()), Double.parseDouble(et100
						.getGpsLatitude()));
				List<Point2D.Double> pts = new ArrayList<Point2D.Double>();
				pts.add(new Point2D.Double(Double.parseDouble(point.getPoint1()
						.split(",")[0]), Double.parseDouble(point.getPoint1()
						.split(",")[1])));
				pts.add(new Point2D.Double(Double.parseDouble(point.getPoint2()
						.split(",")[0]), Double.parseDouble(point.getPoint2()
						.split(",")[1])));
				pts.add(new Point2D.Double(Double.parseDouble(point.getPoint3()
						.split(",")[0]), Double.parseDouble(point.getPoint3()
						.split(",")[1])));
				pts.add(new Point2D.Double(Double.parseDouble(point.getPoint4()
						.split(",")[0]), Double.parseDouble(point.getPoint4()
						.split(",")[1])));
				isInWorkoint = DetermineGpsRange.IsPtInPoly(p, pts);
				if (DetermineGpsRange.IsPtInPoly(p, pts)) {
					System.out.println("点在多边形内");
				} else {
					System.out.println("点在多边形外");
				}
				/**
				 * 判断GPS数据较于之前的行为 第一次进入站点有效区域内 start
				 */
				if (isInWorkoint && isOutWorkPoint) {
					startTime = SimpleDateUtil.getDateByString(et100
							.getGpsDatetime());
					endTime = null;
				}
				/**
				 * 判断GPS数据第一次进入站点有效区域内end 判断GPS跳出此工作站点范围 start
				 */
				if (!isInWorkoint && !isOutWorkPoint) {
					workNum++;
					endTime = SimpleDateUtil.getDateByString(et100
							.getGpsDatetime());
					workTime = workTime
							+ SimpleDateUtil.getDateChangeSeconds(startTime,
									endTime);
					startTime = null;
				}
				/**
				 * GPS跳出站点范围判断结束 GPS事物状态改变 start
				 */
				if (isInWorkoint) {// true;
					isOutWorkPoint = false;// 在站点以内了
				} else {
					isOutWorkPoint = true;
				}
			}
			if (startTime != null && endTime == null) {// 至当前周期结束时间一直在该站点范围的情况下
				workNum++;
				workTime = workTime
						+ SimpleDateUtil.getDateChangeSeconds(startTime,
								new Date());
				workRate.setWorking(true);// 表示当前时间，该车辆尚在此工作站工作
			} else {
				workRate.setWorking(false);
			}
			workRate.setPointId(point.getId());
			workRate.setPointName(point.getPointname());
			workRate.setWorkNum(workNum);
			int time = new Long(workTime).intValue();
			workRate.setWorkTimeRate(SimpleDateUtil.secToTime(time));//
			if(workNum != 0){
				workRate.setWorkTimeone(SimpleDateUtil.secToTime(new Long(workTime
						/ workNum).intValue()));
			}else{
				workRate.setWorkTimeone("0");
			}
			workRates.add(workRate);
		}
		Collections.sort(workRates);// 排序
		/**
		 * 将所有站点的结果统计组合后，根据频率进行重新排序，按照页面的需求，向页面传递相应的数据
		 */
		List<WorkPointRate> displayList = new ArrayList<WorkPointRate>();
		int startSize = pageModel.getPageSize()
				* (pageModel.getCurrentPage() - 1);
		int endSize = pageModel.getPageSize() * (pageModel.getCurrentPage());
		if (endSize >= workPoints.size()) {
			endSize = workPoints.size();
		}
		for (int i = startSize; i < endSize; i++) {
			displayList.add(workRates.get(i));
		}
		Collections.sort(displayList);// 排序
		model.setWorkRates(displayList);
		return model;
	}

	/**
	 * 跳转到垃圾站点设计页面
	 * 
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/workPointSet")
	public ModelAndView workPointSet() {
		ModelAndView mav = new ModelAndView("fuel/workPointSet");
		return mav;
	}*/

	/**
	 * 保存垃圾站点电子围栏信息
	 * 
	 * @param carInfo
	 * @return
	 */
	@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "insertWorkPointData", method = RequestMethod.POST)
	public int insertWorkPointData(@RequestBody WorkPoint point,HttpSession session) {// 添加到数据库表WorkPoint
		/**
		 * 获取userId Admin等超级账号，unitID为0
		 */
		String userid= "";
		
		try {
			 userid=session.getAttribute("userid").toString();//根据session会话获取当前useId
		} catch (Exception e) {
			userid= "";
		
		}
			UserInfo user =userService.selectByPrimaryKey(userid);
			String unitId= "";
			String unitName = "";
			if(user != null){
				unitId= user.getUnitid();
				unitName = user.getUnitname();
				point.setUserid(userid);
				point.setUnitname(unitName);
				point.setUnitid(unitId);
			}
			
		point.setId(MyLong.getRand().toString());
		if (tub01Service.selectBypointName(point.getPointname()) == null) {
			return tub01Service.insertWorkPointData(point);
		} else {
			return 0;
		}

	}

}
