package com.sczh.platform.Controller;

import java.util.ArrayList;
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
import com.sczh.platform.po.DriverInfo;
import com.sczh.platform.po.LjflResidentType;
import com.sczh.platform.po.LjflSmartCard;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.service.CdsService;
import com.sczh.platform.service.DeviceInfoService;
import com.sczh.platform.service.LjflResidentTypeService;
import com.sczh.platform.service.UserService;
import com.yunlao.util.MyLong;
import com.yunlao.util.PageModelDomain;

@Controller
@RequestMapping("cds")
public class CarDirectSystemController {
	@Autowired
	private CdsService cdsService;
	@Autowired
	private DeviceInfoService deviceInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private LjflResidentTypeService ljflResidentTypeService;
	/**
	 * 跳转到车辆信息页面
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/joinCarPage")
	public ModelAndView joinCarPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("car/joinCarPage");
		return mav;
	}
	/**
	 * 添加车辆信息数据
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/addCarPage")
	public ModelAndView addCarPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("car/editCarPage");
		String File_NAME = request.getParameter("File_NAME");
		if(File_NAME!=null && !File_NAME.isEmpty()){
			CarInfo carInfo = cdsService.selectCarInfoByPrimaryKey(File_NAME);
			if(carInfo !=null){
				mav.addObject("carInfo", carInfo);
			}
		}
		return mav;
	}
	/**
	 * 保存车辆信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertCarInfo",method=RequestMethod.POST)
	public  Map<String, String>  insertCarInfo(@RequestBody CarInfo carInfo , HttpSession session){
		/**
		 * 获取userId Admin等超级账号，unitID为0
		 */
		String userid= "";
		String unitId = "";
		try {
			 userid=session.getAttribute("userid").toString();//根据session会话获取当前useId
			
		} catch (Exception e) {
			userid= "";
		
		}
		try {
			 unitId = userService.selectByPrimaryKey(userid).getUnitid();
		} catch (Exception e) {
			unitId = "";
		}
		
		
		Map<String, String> map = new HashMap<String, String>();
		if(carInfo!=null ){
			String id= MyLong.getRand().toString();
			carInfo.setId(id);
			carInfo.setUserid(userid);//将userId保存到carInfo里面
			carInfo.setUnitid(unitId);//保存单位ID
			int num = cdsService.insertCarInfo(carInfo);
			map.put("isSuccess", num+"");
		}else{
			try {
				throw new  Exception("数据不能为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 获取车辆信息
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getCurrentCarInfo")
	public PageModelDomain getCurrentCarInfo(@RequestBody PageModelDomain pmd , HttpSession session){
		/**
		 * 获取userId Admin等超级账号，unitID为0
		 */
		String userid= "";
		String unitId = "";
		try {
			 userid=session.getAttribute("userid").toString();//根据session会话获取当前useId
			
		} catch (Exception e) {
			userid= "";
		
		}
		try {
			 unitId = userService.selectByPrimaryKey(userid).getUnitid();
		} catch (Exception e) {
			unitId = "";
		}
		PageModelDomain page = new PageModelDomain();
		if(unitId == null){
			page = pmd;
			int startSize = pmd.getPageSize()*(pmd.getCurrentPage()-1);
			page.setCarinfos(cdsService.getCarInfoList(startSize+"", pmd.getPageSize()+""));
			page.setTotalNum(cdsService.getCarInfoListTotal().size());
		}else{
			page = pmd;
			int startSize = pmd.getPageSize()*(pmd.getCurrentPage()-1);
			page.setCarinfos(cdsService.getCarInfoList(startSize+"", pmd.getPageSize()+"",unitId));
			page.setTotalNum(cdsService.getCarInfoListTotal(unitId).size());
		}
		
	
		return page;	
	}
	/**
	 * 删除车辆信息
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/deleteCarInfoByIds")
	public int deleteCarInfoByIds(@RequestBody CarInfo carInfo){
	//	List<PageModelDomain> list = new ArrayList<PageModelDomain>();	
		String ids = carInfo.getId();
		int num = cdsService.deleteByPrimaryKeys(ids);
		return num;	
	}
	/**
	 * 修改车辆信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateCarInfo",method=RequestMethod.POST)
	public  Map<String, String>  updateCarInfo(@RequestBody CarInfo carInfo){//添加到数据库表t_user
		Map<String, String> map = new HashMap<String, String>();
		if(carInfo!=null ){
			int num = cdsService.updateCarInfoByPrimaryKey(carInfo);
			map.put("isSuccess", num+"");
		}else{
			try {
				throw new  Exception("修改数据不能为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 跳转到驾驶员信息页面
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/joinDriverPage")
	public ModelAndView joinDriverPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("car/joinDriverPage");
		return mav;
	}
	/**
	 * 进入驾驶员信息页面
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/addDriverPage")
	public ModelAndView addDriverPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("car/addDriverPage");
		String File_NAME = request.getParameter("File_NAME");
		if(File_NAME!=null && !File_NAME.isEmpty()){
			DriverInfo driver = cdsService.selectDriverInfoByPrimaryKey(File_NAME);
			if(driver != null){
				mav.addObject("driver", driver);
			}
		}
		return mav;
	}
	/**
	 * 保存驾驶员信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertDriverInfo",method=RequestMethod.POST)
	public  Map<String, String>  insertDriverInfo(@RequestBody DriverInfo driver,HttpSession session){//添加到数据库表t_user
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
	
		Map<String, String> map = new HashMap<String, String>();
		if(driver!=null ){
			String id= MyLong.getRand().toString();
			driver.setId(id);
			if(user != null){
				driver.setUnitid(unitId);
				driver.setUnitname(unitName);
			}
			driver.setUserid(userid);
			int num = cdsService.insertDriverInfo(driver);
			
			map.put("isSuccess", num+"");
		}else{
			try {
				throw new  Exception("数据不能为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 获取驾驶员信息
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getDriverInfos")
	public PageModelDomain getDriverInfos(@RequestBody PageModelDomain pmd,HttpSession session){
	//	List<PageModelDomain> list = new ArrayList<PageModelDomain>();	
		/**
		 * 获取userId Admin等超级账号，unitID为0
		 */
		String userid= "";
		String unitId = "";
		try {
			 userid=session.getAttribute("userid").toString();//根据session会话获取当前useId
			
		} catch (Exception e) {
			userid= "";
		
		}
		try {
			 unitId = userService.selectByPrimaryKey(userid).getUnitid();
		} catch (Exception e) {
			unitId = "";
		}
		PageModelDomain page = new PageModelDomain();
		page = pmd;
		if(unitId == null){
		int startSize = pmd.getPageSize()*(pmd.getCurrentPage()-1);
		List<DriverInfo> list1 = cdsService.getDriverInfoList(startSize+"", pmd.getPageSize()+"");
		page.setDriverInfos(list1);
		page.setTotalNum(cdsService.getDriverInfoListTotal().size());
		}else{
			int startSize = pmd.getPageSize()*(pmd.getCurrentPage()-1);
			List<DriverInfo> list1 = cdsService.getDriverInfoListByUnit(startSize+"", pmd.getPageSize()+"",unitId);
			page.setDriverInfos(list1);
			page.setTotalNum(cdsService.getDriverInfoListTotalByUnit(unitId).size());
		}
		return page;	
	}
	/**
	 * 修改驾驶员信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateDriverInfo",method=RequestMethod.POST)
	public  Map<String, String>  updateDriverInfo(@RequestBody DriverInfo driver){//添加到数据库表t_user
		Map<String, String> map = new HashMap<String, String>();
		if(driver!=null ){
			int num = cdsService.updateDriverInfoByPrimaryKey(driver);
			map.put("isSuccess", num+"");
		}else{
			try {
				throw new  Exception("修改数据不能为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 删除驾驶员信息
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/deleteDirverByIds")
	public int deleteDirverByIds(@RequestBody DriverInfo driver){
	//	List<PageModelDomain> list = new ArrayList<PageModelDomain>();	
		String ids = driver.getId();
		Map<String, Object> map = new HashMap<String,Object>();
		List<String> list = new ArrayList<String>();
		String[] idss = ids.split(",");
		for (String str : idss) {
			list.add(str);
		}
		map.put("idsList", list);
		int num = cdsService.deleteDriverInfoByPrimaryKeys(map);
		return num;	
	}
	/**
	 *跳转到设备管理页面 gps
	 * @param request
	 * @return
	 *//*
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/deviceManage")
	public ModelAndView deviceManage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("gps/deviceManage");
		return mav;
	}*/
	/**
	 *跳转到设备管理页面 gps
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/setDevice")
	public ModelAndView setDevice(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("gps/setDevice");
		return mav;
	}
	/**
	 * 保存设备信息
	 * @param DeviceInfo deviceInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/addDeviceInfo",method=RequestMethod.POST)
	public  Map<String, String>  addDeviceInfo(@RequestBody DeviceInfo deviceInfo,HttpSession session){//添加到数据库表t_user
		Map<String, String> map = new HashMap<String, String>();
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
		if(deviceInfo!=null ){
			DeviceInfo d = deviceInfoService.selectByPrimaryKey(deviceInfo.getId());
			if(d != null){
				map.put("isSuccess", "id");//设备号重复
			}else{
				deviceInfo.setUnitid(unitId);
				deviceInfo.setUnitname(unitName);
				deviceInfo.setUserid(userid);
				deviceInfoService.insert(deviceInfo);
				map.put("isSuccess", "1");//数据录入成功
			}
			
		}else{
			map.put("isSuccess", "data");//数据不能为空
			try {
				throw new  Exception("数据不能为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 跳转到设备信息页面
	 * @param request
	 * @return
	 *//*
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/dispLayDeviceData")
	public ModelAndView dispLayDeviceData(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("gps/dispLayDeviceData");
		return mav;
	}*/
	/**
	 * 获取设备信息
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getCurrentDeviceInfo")
	public PageModelDomain getCurrentDeviceInfo(@RequestBody PageModelDomain pmd,HttpSession session){
	//	List<PageModelDomain> list = new ArrayList<PageModelDomain>();	
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
		PageModelDomain page = new PageModelDomain();
		page = pmd;
		if(unitId == null){
			int startSize = pmd.getPageSize()*(pmd.getCurrentPage()-1);
			System.out.println(startSize);
			page.setDeviceInfos(deviceInfoService.getDeviceInfoList(startSize+"", pmd.getPageSize()+""));
			page.setTotalNum(deviceInfoService.getDeviceInfoListTotal().size());
		}else{
			int startSize = pmd.getPageSize()*(pmd.getCurrentPage()-1);
			page.setDeviceInfos(deviceInfoService.getDeviceInfoListByUnit(startSize+"", pmd.getPageSize()+"",unitId));
			page.setTotalNum(deviceInfoService.getDeviceInfoListTotalByUnit(unitId).size());
		}
	
		return page;	
	}
	/**
	 * 修改设备信息
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateDevicePage")
	public ModelAndView updateDevicePage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("redirect:http://localhost:8080/html/gps/updateDevicePage.html");
		String File_NAME = request.getParameter("fileName");
		if(File_NAME!=null && !File_NAME.isEmpty()){
			DeviceInfo deviceInfo = deviceInfoService.selectByPrimaryKey(File_NAME);
			if(deviceInfo !=null){
				mav.addObject("deviceInfo", deviceInfo);
			}
		}
		return mav;
	}
	/**
	 * 修改设备信息
	 * @param DeviceInfo deviceInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateDeviceInfo",method=RequestMethod.POST)
	public  Map<String, String>  updateDeviceInfo(@RequestBody DeviceInfo deviceInfo,HttpSession session){//添加到数据库表t_user
		Map<String, String> map = new HashMap<String, String>();
		
		if(deviceInfo!=null ){
			deviceInfoService.updateByPrimaryKey(deviceInfo);
			map.put("isSuccess","1");//数据不能为空
		}else{
			map.put("isSuccess", "data");//数据不能为空
			try {
				throw new  Exception("数据不能为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 删除驾驶员信息
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/deleteDviceInfo")
	public int deleteDviceInfo(@RequestBody DeviceInfo deviceInfo){
	//	List<PageModelDomain> list = new ArrayList<PageModelDomain>();	
		String ids = deviceInfo.getId();
		Map<String, Object> map = new HashMap<String,Object>();
		List<String> list = new ArrayList<String>();
		String[] idss = ids.split(",");
		for (String str : idss) {
			list.add(str);
		}
		map.put("idsList", list);
		int num = deviceInfoService.deleteDeviceInfoByPrimaryKeys(map);
		return num;	
	}
/*	*//**
	 * 居民后台管理  居民类型
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("joinResidentTypePage")
	public ModelAndView joinResidentTypePage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("resident/joinResidentTypePage");
		mav.addObject("userId", userId);
		return mav;
	}*/
	/**
	 * 获取满溢状态配置信息
	 * 分页查询
	 */
	@ResponseBody
	@RequestMapping(value = "/queryResidentTypeByParamters", method = RequestMethod.POST)
	public com.sczh.platform.po.Model.PageModelDomain queryResidentTypeByParamters(@RequestBody com.sczh.platform.po.Model.PageModelDomain pageModelDomain,HttpSession session){
		if(pageModelDomain.getfCode() ==null || pageModelDomain.getfCode().isEmpty()){
			pageModelDomain.setfCode(null);
		}
		if(pageModelDomain.getfName() ==null || pageModelDomain.getfName().isEmpty()){
			pageModelDomain.setfName(null);
		}
	
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("f_code", pageModelDomain.getfCode());
		map.put("f_name", pageModelDomain.getfName());
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		map.put("userId", pageModelDomain.getUserid());
		UserInfo userInfo = userService.selectByPrimaryKey(session.getAttribute("userid").toString());
		if("admin".equals(userInfo.getUsername())){
			map.put("userId", null);
			map.put("unitId", null);
		}else{
			map.put("unitId", userInfo.getUnitid());
		}
	
		int size = ljflResidentTypeService.selectLjflResidentTypeByParamters(map);//总的数据
		pageModelDomain.setPageTotalNum(size);
		int currentNum =  (pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize();//此次获取的第一个数据
		int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();//此次获取的可能性最后一个数据
		int pageSize = pageModelDomain.getPageSize();
		if(endNum>=size && currentNum<size){//当最后一条不满足的时候
			pageSize = size-currentNum;
		}
		pageModelDomain.setCurrentNum(currentNum);
		pageModelDomain.setPageSize(pageSize);
		map.put("currentNum", pageModelDomain.getCurrentNum());
		if(pageModelDomain.getCurrentNum()+pageModelDomain.getPageSize()>size){
			map.put("endNum", size - pageModelDomain.getCurrentNum());
		}else{
			map.put("endNum", pageModelDomain.getPageSize());
		}
		

		List<LjflResidentType> list = ljflResidentTypeService.selectLjflResidentTypeByPamaters(map);
		pageModelDomain.setLjflResidentTypeList(list);
//		pageModelDomain.setLjflSpillOverConfigList(list);
		return pageModelDomain;
	}
	/**
	 * 保存居民类型信息
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertResidentType",method=RequestMethod.POST)
	public  Map<String, String>  insertResidentType(@RequestBody LjflResidentType type,HttpSession session){//添加到数据库表departmentmanage
		Map<String, String> map = new HashMap<String,String>();
		String id = MyLong.getRandStr(11);
		type.setId(id);
		Date date = new Date();
		type.setCreatetime(date);
		type.setTenantid("4eaf9423eb0043648e502134586b088a");
		type.setStatus(0);
		UserInfo userInfo = userService.selectByPrimaryKey(session.getAttribute("userid").toString());
		if("admin".equals(userInfo.getUsername())){
			type.setUnitid(null);
		}else{
			type.setUserid(userInfo.getId());
			type.setUnitid(userInfo.getUnitid());
		}
		int i =  ljflResidentTypeService.insertResidentType(type);
		map.put("result", i+"");
		return map;
	}
	/**
	 * 更新居民类型信息
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateResidentType",method=RequestMethod.POST)
	public  Map<String, String>  updateResidentType(@RequestBody  LjflResidentType type){//添加到数据库表departmentmanage
	
		Map<String, String> map = new HashMap<String,String>();
		Date date = new Date();
		type.setLastchangetime(date);
		type.setTenantid("4eaf9423eb0043648e502134586b088a");
		int i =  ljflResidentTypeService.updateResidentType(type);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 更新删除居民信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/deleteResidentType",method=RequestMethod.POST)
	public  Map<String, String>  deleteLjflSpliOverConfig(@RequestBody  LjflResidentType type){//添加到数据库表departmentmanage
	
		Map<String, String> map = new HashMap<String,String>();
		LjflResidentType t = ljflResidentTypeService.selectResidentTypById(type.getId());
		t.setBeendeleted(type.getBeendeleted());
		Date date = new Date();
		type.setTenantid("4eaf9423eb0043648e502134586b088a");
		type.setDeletedtime(date);
		int i =  ljflResidentTypeService.updateResidentType(t);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 获取居民类型信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getResidentTypeById",method=RequestMethod.POST)
	public  LjflResidentType  getResidentTypeById(@RequestBody LjflResidentType type){//添加到数据库表departmentmanage
		type = ljflResidentTypeService.selectResidentTypById(type.getId());
		return type;
	}
	/**
	 * 居民后台管理 智能卡
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("joinSmartCardPage")
	public ModelAndView joinSmartCardPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("resident/joinSmartCardPage");
		mav.addObject("userId", userId);
		return mav;
	}*/
	/**
	 * 获取智能卡信息
	 * 分页查询
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLjflSmartCardByParamters", method = RequestMethod.POST)
	public com.sczh.platform.po.Model.PageModelDomain queryLjflSmartCardByParamters(@RequestBody com.sczh.platform.po.Model.PageModelDomain pageModelDomain,HttpSession session){
		if(pageModelDomain.getfCCode() ==null || pageModelDomain.getfCCode().isEmpty()){
			pageModelDomain.setfCCode(null);
		}
		if(pageModelDomain.getfBindName() ==null || pageModelDomain.getfBindName().isEmpty()){
			pageModelDomain.setfBindName(null);
		}
		if(pageModelDomain.getfBindType() ==null || pageModelDomain.getfBindType().isEmpty()||"-1".equals(pageModelDomain.getfBindType())){
			pageModelDomain.setfBindType(null);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("f_c_code", pageModelDomain.getfCCode());
		map.put("f_bind_name", pageModelDomain.getfBindName());
		map.put("f_bind_type", pageModelDomain.getfBindType());
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		map.put("userId", pageModelDomain.getUserid());
		UserInfo userInfo = userService.selectByPrimaryKey(session.getAttribute("userid").toString());
		if("admin".equals(userInfo.getUsername())){
			map.put("userId", null);
			map.put("unitId", null);
		}else{
			map.put("unitId", userInfo.getUnitid());
		}
	
		int size = ljflResidentTypeService.selectLjflSmartCardByParamters(map);//总的数据
		pageModelDomain.setPageTotalNum(size);
		int currentNum =  (pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize();//此次获取的第一个数据
		int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();//此次获取的可能性最后一个数据
		int pageSize = pageModelDomain.getPageSize();
		if(endNum>=size && currentNum<size){//当最后一条不满足的时候
			pageSize = size-currentNum;
		}
		pageModelDomain.setCurrentNum(currentNum);
		pageModelDomain.setPageSize(pageSize);
		map.put("currentNum", pageModelDomain.getCurrentNum());
		if(pageModelDomain.getCurrentNum()+pageModelDomain.getPageSize()>size){
			map.put("endNum", size - pageModelDomain.getCurrentNum());
		}else{
			map.put("endNum", pageModelDomain.getPageSize());
		}
		

		List<LjflSmartCard> list = ljflResidentTypeService.selectLjflSmartCardByPamaters(map);
		pageModelDomain.setLjflSmartCardList(list);
//		pageModelDomain.setLjflSpillOverConfigList(list);
		return pageModelDomain;
	}
	/**
	 * 保存智能卡信息
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertLjflSmartCard",method=RequestMethod.POST)
	public  Map<String, String>  insertLjflSmartCard(@RequestBody LjflSmartCard card,HttpSession session){//添加到数据库表departmentmanage
		Map<String, String> map = new HashMap<String,String>();
		String id = MyLong.getRandStr(11);
		card.setId(id);
		Date date = new Date();
		card.setCreatetime(date);
		card.setTenantid("4eaf9423eb0043648e502134586b088a");
		card.setStatus(0);
		UserInfo userInfo = userService.selectByPrimaryKey(session.getAttribute("userid").toString());
		if("admin".equals(userInfo.getUsername())){
			card.setUnitid(null);
		}else{
			card.setUnitid(userInfo.getUnitid());
		}
		card.setUserid(userInfo.getId());
		int i =  ljflResidentTypeService.insertLjflSmartCard(card);
		map.put("result", i+"");
		return map;
	}
	/**
	 * 更新智能卡信息
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateLjflSmartCard",method=RequestMethod.POST)
	public  Map<String, String>  updateLjflSmartCard(@RequestBody  LjflSmartCard card){//添加到数据库表departmentmanage
	
		Map<String, String> map = new HashMap<String,String>();
		Date date = new Date();
		card.setLastchangetime(date);
		card.setTenantid("4eaf9423eb0043648e502134586b088a");
		LjflSmartCard t = ljflResidentTypeService.selectLjflSmartCardById(card.getId());
		card.setUnitid(t.getUnitid());
		card.setfMemo(card.getfMemo());
		int i =  ljflResidentTypeService.updateLjflSmartCard(card);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 更新删除智能卡信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/deleteLjflSmartCard",method=RequestMethod.POST)
	public  Map<String, String>  deleteLjflSmartCard(@RequestBody  LjflSmartCard card){//添加到数据库表departmentmanage
	
		Map<String, String> map = new HashMap<String,String>();
		LjflSmartCard t = ljflResidentTypeService.selectLjflSmartCardById(card.getId());
		t.setBeendeleted(card.getBeendeleted());
		Date date = new Date();
		t.setTenantid("4eaf9423eb0043648e502134586b088a");
		t.setDeletedtime(date);
		int i =  ljflResidentTypeService.updateLjflSmartCard(t);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 获取智能卡信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getLjflSmartCardById",method=RequestMethod.POST)
	public  LjflSmartCard  getLjflSmartCardById(@RequestBody LjflSmartCard card){//添加到数据库表departmentmanage
		card = ljflResidentTypeService.selectLjflSmartCardById(card.getId());
		return card;
	}
}
