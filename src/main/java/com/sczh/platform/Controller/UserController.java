package com.sczh.platform.Controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sczh.platform.po.AreaRelation;
import com.sczh.platform.po.LjflHandDevice;
import com.sczh.platform.po.MenuInfo;
import com.sczh.platform.po.SignInRecord;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.po.Model.ExportExcelFile;
import com.sczh.platform.po.Model.PageModelDomain;
import com.sczh.platform.po.Model.SignInPageDomain;
import com.sczh.platform.service.AreaService;
import com.sczh.platform.service.MenuInfoService;
import com.sczh.platform.service.SignInRecordService;
import com.sczh.platform.service.UserService;
import com.yunlao.util.ExcelUtil;
import com.yunlao.util.MD5andKL;
import com.yunlao.util.MyLong;
import com.yunlao.util.Point;

import java.awt.Menu;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/user" })
public class UserController {

	private static String id = null;
//	private static String loginid = null;
//	public static String unitid = null; 
	
	@Autowired
	private UserService userService;
	@Autowired
	private MenuInfoService menuInfoService;

	@Autowired
	private AreaService areaServiceImpl;
	@Autowired
	private SignInRecordService signinRecordService;
	
	
	/**
	 * 判断当前是否有用户登录
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/islogin", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String islogin(HttpSession session) throws Exception {

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		
			Object attribute = session.getAttribute("userid");
			System.out.println("userid:"+attribute);
			if(attribute==null||attribute.equals("")){
				
				jsonObject.put("statusCode", "0");
				jsonObject.put("referer", "/login.html");
				jsonObject.put("message", "请先登录");
				return jsonObject.toString();
			
		
		} else {

			if(attribute=="1"||attribute.equals("1")){
				
				jsonObject.put("statusCode", "1");
				jsonObject.put("referer", "/admin_index.html");
				jsonObject.put("message", "登录成功");
				return jsonObject.toString();
			}else{
				
			
			jsonObject.put("statusCode", "200");
			jsonObject.put("referer", "/");
			jsonObject.put("message", "登录成功");
			return jsonObject.toString();
			}
		}
	}
	
	/**
	 * 登录
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String login(@RequestBody String jsondata,HttpSession session) throws Exception {

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		JSONObject parseObject = JSONObject.parseObject(jsondata);
		
		String longitude = parseObject.getString("longitude");
		String latitude = parseObject.getString("latitude");
		
		
		/*if (longitude == null || longitude == "") {


			jsonObject.put("statusCode", "0");
			jsonObject.put("referer", "");
			jsonObject.put("message", "longitude为null");
			return jsonObject.toString();
		}
		if (latitude == null || latitude == "") {
			
		
			jsonObject.put("statusCode", "0");
			jsonObject.put("referer", "");
			jsonObject.put("message", "latitude为null");
			
			return jsonObject.toString();
		}
		
		
		double latitudeDoneD = Double.parseDouble(latitude);
		double longitudeDoneD = Double.parseDouble(longitude);
		
		
		
		ArrayList<Double> polygonXA = new ArrayList<Double>();
		polygonXA.add(116.439263);
		polygonXA.add(116.454058);
		polygonXA.add(116.465754);
		polygonXA.add(116.442613);
		
		ArrayList<Double> polygonYA = new ArrayList<Double>();
		polygonYA.add(39.96414);
		polygonYA.add(39.978399);
		polygonYA.add(39.969138);
		polygonYA.add(39.948619);
		
		
		Point point =  new Point();
		boolean pointInPolygon = point.isPointInPolygon(longitudeDoneD, latitudeDoneD, polygonXA, polygonYA);
		
		if(pointInPolygon==false){
			
			jsonObject.put("statusCode", "0");
			jsonObject.put("referer", "");
			jsonObject.put("message", "此坐标位置不正确");
			
			return jsonObject.toString();
		}*/
		
		
		String username = parseObject.getString("username");
		String password = parseObject.getString("password");

		password = MD5andKL.MD5(password);
		
		UserInfo userInfo = userService.selectByUserNameAndPassword(username,
				password);

		if (userInfo != null) {
			String authority = userInfo.getAuthority();
			String loginid=userInfo.getId();
			String unitid = userInfo.getUnitid();
			session.setAttribute("userid", loginid);
			session.setAttribute("unitid", unitid);
			Gson gson = new Gson();
			String json = gson.toJson(authority);

			if(username.equals("admin")){
				
				jsonObject.put("statusCode", "200");
				jsonObject.put("referer", "/admin_index.html");
				jsonObject.put("message", json);
				return jsonObject.toString();
			}else{
			
			jsonObject.put("statusCode", "200");
			jsonObject.put("referer", "/");
			jsonObject.put("message", json);
			return jsonObject.toString();
			}
		} else {

			jsonObject.put("statusCode", "0");
			jsonObject.put("referer", "");
			jsonObject.put("message", "用户名或密码错误");
			return jsonObject.toString();
		}
	}

	/**
	 * 获取权限
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/authority",produces = { "application/json;charset=UTF-8" })
	public String authority(HttpServletRequest request,HttpSession session) throws Exception {

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		String menuId= request.getParameter("menuId") == null ? "" : request
				.getParameter("menuId");
		System.out.println("menuId:"+menuId);
		UserInfo userInfo = userService.selectByPrimaryKey(session.getAttribute("userid").toString());
		
		if (userInfo != null) {
			String authority = userInfo.getAuthority();
			List<MenuInfo> getquanxian = getquanxian(authority);
			List<MenuInfo> menu = new ArrayList<MenuInfo>();
			for (MenuInfo menuInfo : getquanxian) {
				System.out.println("1:"+menuInfo.getPid());
				System.out.println("2"+menuId);
				if(menuInfo.getPid().equals(menuId)){
					
					menu.add(menuInfo);
				}
			}
			
			Gson gson = new Gson();
			String json = gson.toJson(menu);

			System.out.println(json);
			return json;
		} else {

			jsonObject.put("statusCode", "0");
			jsonObject.put("referer", "");
			jsonObject.put("message", "没有权限");
			return jsonObject.toString();
		}
	}
	
	/**
	 * 获取人员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/userinfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String userinfo() throws Exception {

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		List<UserInfo> selectListUserInfos = userService.selectListUserInfos();
		for (UserInfo userInfo : selectListUserInfos) {
			System.out.println("zx:"+userInfo);
			String authority = userInfo.getAuthority();
			System.out.println("1:"+authority);
			//Gson gson = new Gson();
			//String parseObject = gson.toJson(authority);
			//System.out.println("quanx:"+parseObject);
			if (authority==null||authority.equals("")) {
				
			}else{
			List<MenuInfo> getquanxian = getquanxian(authority);
			String quanxian = "首页";
			for (MenuInfo menuInfo : getquanxian) {
				
				String text = menuInfo.getText();
				quanxian=quanxian+","+text;
			}
			userInfo.setAuthority(quanxian);
			}
			//MenuInfo stud = (MenuInfo) JSONObject.toJavaObject(parseObject,MenuInfo.class);
			//System.out.println("menuinfo:"+stud);
		}
		Gson gson = new Gson();
		String json = gson.toJson(selectListUserInfos);

		jsonObject.put("rows", json);
		jsonObject.put("total", selectListUserInfos.size());
		System.out.println(jsonObject.toString());
		return jsonObject.toString();

	}
	
	
	/**
	 * 修改权限人
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String user(HttpServletRequest request,@RequestBody  String jsondata) throws Exception {

		//JSONObject parseObject = JSONObject.parseObject(jsondata);
		//id = parseObject.getString("id");
		
		String id1= request.getParameter("id") == null ? "" : request
				.getParameter("id");
		
		id = id1.replace("'","");
		System.out.println("id==" + id);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		jsonObject.put("message", "成功");
		
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
	
	/**
	 * 获取所有权限
	 * 
	 * @param jsondata
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getauthority", produces = { "application/json;charset=UTF-8" })
	public String getauthority() throws Exception {
		Gson gson = new Gson();
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		List<Object> data = new ArrayList<Object>();//最大一层 
		
		List<MenuInfo> selectMenuInfos = menuInfoService.selectMenuInfos();
		for (MenuInfo menuInfo : selectMenuInfos) {
			String pid = menuInfo.getPid();
			if (pid .equals( "0")) {
				String id1 = menuInfo.getId();
				Map<String, Object> menumap = new HashMap<String, Object>();//一级目录  基本信息
				menumap.put("id", id1);
				menumap.put("pid", pid);
				menumap.put("text", menuInfo.getText());
				menumap.put("state", menuInfo.getState());
				menumap.put("iconCls", menuInfo.getIconcls());
				menumap.put("url", menuInfo.getUrl());
				List<MenuInfo> selectMenuInfosByPid = null;
				try {

					selectMenuInfosByPid = menuInfoService
							.selectMenuInfosByPid(id1);
				} catch (Exception e) {
					// TODO: handle exception
					selectMenuInfosByPid = null;
				}

				if (selectMenuInfosByPid != null
						|| selectMenuInfosByPid.equals("")) {
					
					List<Object> data2 = new ArrayList<Object>();//包含多个二级目录的list  车辆信息，垃圾桶满溢信息
					
					for (MenuInfo menuInfo2 : selectMenuInfosByPid) {
						Map<String, Object> menumap2 = new HashMap<String, Object>();//二级目录的基本信息  车辆信息
						String id2 = menuInfo2.getId();
						menumap2.put("id", id2);
						menumap2.put("pid", menuInfo2.getPid());
						menumap2.put("text", menuInfo2.getText());
						menumap2.put("state", menuInfo2.getState());
						menumap2.put("iconCls", menuInfo2.getIconcls());
						menumap2.put("url", menuInfo2.getUrl());

						List<MenuInfo> selectMenuInfosByPid2 = null;
						try {

							selectMenuInfosByPid2 = menuInfoService
									.selectMenuInfosByPid(id2);
						} catch (Exception e) {
							// TODO: handle exception
							selectMenuInfosByPid2 = null;
						}

						if (selectMenuInfosByPid2 != null
								|| selectMenuInfosByPid2.equals("")) {

							List<Object> data3 = new ArrayList<Object>();//包含多个 三级目录的list  清运记录、满溢状态GIS显示
							for (MenuInfo menuInfo3 : selectMenuInfosByPid2) {
								Map<String, Object> menumap3 = new HashMap<String, Object>();//三级目录信息 清运记录
								String id3 = menuInfo3.getId();
								menumap3.put("id", id3);
								menumap3.put("pid", menuInfo3.getPid());
								menumap3.put("text", menuInfo3.getText());
								menumap3.put("state", menuInfo3.getState());
								menumap3.put("iconCls", menuInfo3.getIconcls());
								menumap3.put("url", menuInfo3.getUrl());
								data3.add(menumap3);
							}
							
							menumap2.put("children", data3);
						}
						data2.add(menumap2);
					}
					menumap.put("children", data2);
				}
				data.add(menumap);
			}		
		}
		
		String json = gson.toJson(data);

		jsonObject.put("", json);
		System.out.println(json);
		return json;

	}

	/**
	 * 保存用户的权限
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/saveauthority", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String saveauthority(HttpServletRequest request,@RequestBody String jsondata) throws Exception {

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		
		if(id==null||id.equals("")){
			
			jsonObject.put("message", "请先提交需要配置此权限的人员");
	    	return jsonObject.toString();
		}
		
		String idstr = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		System.out.println("id==" + idstr);
		String id1 = idstr.replace("'", "");
	    String[] arr = id1.split(",");
	    System.out.println("arr:"+arr);
		
	    List<MenuInfo> authoritylist = new ArrayList<MenuInfo>();
	    
	    
	    for (int i =0;i<arr.length;i++) {
	    	String string = arr[i];
	    	System.out.println(string);
	    	MenuInfo selectMenuInfo = menuInfoService.selectMenuInfo(arr[i]);
	    	authoritylist.add(selectMenuInfo);
		}
	    
	    String authority = authoritylist.toString();
	    
	    System.out.println(authority);
	    
	    System.out.println(id);
	    UserInfo selectByPrimaryKey = userService.selectByPrimaryKey(id);
	    selectByPrimaryKey.setAuthority(authority);
	    
	    int updateByPrimaryKey = userService.updateByPrimaryKeyWithBLOBs(selectByPrimaryKey);
	    
	    if(updateByPrimaryKey==1){
	    	
			jsonObject.put("message", "成功");
			return jsonObject.toString();
	    }else{
	    	
	    	jsonObject.put("message", "失败");
	    	return jsonObject.toString();
	    }

	}

	
	
	@ResponseBody
	@RequestMapping(value = "/a", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String a(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		// String parameter = SpringmvcUtils.getParameter("parameters");
		Map<String, Object> data = new HashMap<String, Object>();

		JSONObject parseObject = JSONObject.parseObject("parameter");
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		String staffId = parseObject.getString("staffId");
		String prizeProvideId = parseObject.getString("prizeProvideId");
		String prizeName = parseObject.getString("prizeName");
		String exchangePsd = parseObject.getString("exchangePsd");
		String carId = parseObject.getString("carId");

		System.out.println(staffId);
		System.out.println(prizeProvideId);
		System.out.println(prizeName);
		System.out.println(exchangePsd);
		System.out.println(carId);

		Gson gson = new Gson();
		String json = gson.toJson(data);

		jsonObject.put("result", "0");
		jsonObject.put("data", json);
		jsonObject.put("msg", null);
		return jsonObject.toString();
	}
	
	
	// 权限
		public static List<MenuInfo> getquanxian(String authority) {

			System.out.println("2:"+authority);
				String substring = authority.substring(10, authority.length() - 1);
				String[] split = substring.split(", MenuInfo ");
				List<MenuInfo> quanxian = new ArrayList<MenuInfo>();
				for (int i = 0; i < split.length; i++) {

					String str = split[i];
					System.out.println(str);
				
					String substring2 = str.substring(1, str.length() - 1);
					String[] split2 = substring2.split(", ");

					MenuInfo menuInfo = new MenuInfo();
					for (int j = 0; j < split2.length; j++) {
						String str2 = split2[j];
						int indexOf = str2.indexOf("=");
						
						if (str2.contains("pid")) {
							String pid = str2.substring(indexOf + 1);
							menuInfo.setPid(pid);
							
						} else if (str2.contains("url")) {
							String url = str2.substring(indexOf + 1);
							menuInfo.setUrl(url);
						} else if (str2.contains("text")) {
							String text = str2.substring(indexOf + 1);
							menuInfo.setText(text);
						} else if (str2.contains("state")) {
							String state = str2.substring(indexOf + 1);
								menuInfo.setState(state);
						} else if (str2.contains("iconcls")) {
							String iconcls = str2.substring(indexOf + 1);
							menuInfo.setIconcls(iconcls);
						}else if(str2.contains("code")){
							
							String code = str2.substring(indexOf + 1);
							menuInfo.setCode(code);
						}else{
							String id = str2
									.substring(indexOf + 1);
							menuInfo.setId(id);
						}
						
					}
					quanxian.add(menuInfo);
				}
				return quanxian;
			}
		
		
		/**
		 * 获取signin数据
		 * 
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/getsigninrecord")
		public SignInPageDomain getsigninrecord(
				@RequestBody SignInPageDomain recordPageDomain) {

			// 获取搜索时间
			Date time = recordPageDomain.getTime();
			System.out.println("time==" + time);
			String time2;
			if (time == null) {
				System.out.println("进来了");
				time2 = " ";
				System.out.println("time2==" + time2);
			} else {
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
				time2 = ft.format(time);
			}

			System.out.println(time2);

			SignInPageDomain signInPageDomain = new SignInPageDomain();
			signInPageDomain = recordPageDomain;
			int startSize = recordPageDomain.getPageSize()
					* (recordPageDomain.getCurrentPage() - 1);

			List<SignInRecord> selectListSignInList = signinRecordService
					.selectListSignInList(startSize + "",
							recordPageDomain.getPageSize() + "", time2 + "");

			signInPageDomain.setContent(selectListSignInList);
			signInPageDomain.setTotalNum(signinRecordService
					.selectListSignInListTotal(time2 + "").size());

			return recordPageDomain;
		}
		
		@ResponseBody
		@RequestMapping("getMobileDeviceManageData")
		public PageModelDomain getMobileDeviceManageData(
				@RequestBody PageModelDomain domain) {

			PageModelDomain pageModelDomain = new PageModelDomain();
			pageModelDomain = domain;

			String code = domain.getCode();
			String name = domain.getName();

			if (code == null || code == "") {

				code = ".";
			}
			if (name == null || name == "") {

				name = ".";
			}

			System.out.println(code);
			System.out.println(name);
			int startSize = domain.getPageSize() * (domain.getCurrentPage() - 1);
			List<LjflHandDevice> selecthanddeviceinfos = userService
					.selecthanddeviceinfos(startSize + "", domain.getPageSize()
							+ "", name, code);

			for (LjflHandDevice ljflHandDevice : selecthanddeviceinfos) {

				String lastloginuserid = ljflHandDevice.getLastloginuserid();

				UserInfo selectByPrimaryKey = userService
						.selectByPrimaryKey(lastloginuserid);

				if (selectByPrimaryKey == null) {

					ljflHandDevice.setLastloginuserid(null);
				} else {

					String username = selectByPrimaryKey.getUsername();

					ljflHandDevice.setLastloginuserid(username);
				}
			}

			pageModelDomain.setHanddeviceinfo(selecthanddeviceinfos);
			pageModelDomain.setPageTotalSize(userService
					.selecthanddeviceinfostotal(name, code).size());

			System.out.println(pageModelDomain.toString());

			return pageModelDomain;
		}
		
		@ResponseBody
		@RequestMapping("deleteMobileDeviceManageData")
		public Map<String, String> deleteMobileDeviceManageData(@RequestBody String jsondata){
			
			Map<String,String > map = new HashMap<String, String>();
			JSONObject parseObject = JSONObject.parseObject(jsondata);
			
			String id = parseObject.getString("id");
			
			int deleteById = userService.deleteById(id);
			if(deleteById==1){
				
				map.put("message", "删除成功");
			}else{
				
				map.put("message", "删除失败");
			}
			
			return map;
		}
		
		/**
		 * 导出手持端数据
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/exportExcel")
		public String exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			PageModelDomain pageModel = new PageModelDomain();
			/**
			 * 封装PageModelDomain
			 */
			String code = request.getParameter("code");
			String name = request.getParameter("lastLoginUserId");
			pageModel.setCode(code);
			pageModel.setName(name);
		
			PageModelDomain page = getLjflScoreExportExcel(pageModel);
			/**
			 * 获取到数据
			 */
			List<LjflHandDevice> ljflHandDevices = page.getHanddeviceinfo();

			// 填充Objects数据
			// List<Object> Objects=createData(request);
			List<Map<String, Object>> list = createExcelRecord(ljflHandDevices);
			String columnNames[] = { "id", "设备号", "", "最近登录用户", "最近登录时间", "最近登录地点" };// 列名
			String keys[] = { "id", "code", "name", "logindate", "loginaddress" };// map中的key
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			String fileName = "手持端设备登录信息表";
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String((ExportExcelFile.processFileName(request, fileName) + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				// Simple read/write loop.
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
			return null;
		}
		
		public PageModelDomain getLjflScoreExportExcel(@RequestBody PageModelDomain pageModelDomain){
			PageModelDomain page = new PageModelDomain();
			if(pageModelDomain.getName() ==null || pageModelDomain.getName().isEmpty()){//居民id
				pageModelDomain.setName(".");
			}
			if(pageModelDomain.getCode() ==null || pageModelDomain.getCode().isEmpty()){
				pageModelDomain.setCode(".");
			}
			List<LjflHandDevice> ljflHandDevicelist = new ArrayList<LjflHandDevice>();
			
			ljflHandDevicelist = userService.selecthanddeviceinfostotal(pageModelDomain.getName(), pageModelDomain.getCode());
			
			for (LjflHandDevice ljflHandDevice : ljflHandDevicelist) {

				String lastloginuserid = ljflHandDevice.getLastloginuserid();

				UserInfo selectByPrimaryKey = userService
						.selectByPrimaryKey(lastloginuserid);

				if (selectByPrimaryKey == null) {

					ljflHandDevice.setLastloginuserid(null);
				} else {

					String username = selectByPrimaryKey.getUsername();

					ljflHandDevice.setLastloginuserid(username);
				}
				
				Date time = ljflHandDevice.getLastlogintime();
				SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				ljflHandDevice.setId(dateFormater.format(time));
				
			}
			
			page.setPageTotalSize(ljflHandDevicelist.size());
			page.setHanddeviceinfo(ljflHandDevicelist);
			return page;
			
		}

		private List<Map<String, Object>> createExcelRecord(List<LjflHandDevice> ljflHandDevices) {
			List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sheetName", "手持端设备登录信息表");
			listmap.add(map);
			LjflHandDevice ljflDevice = null;
			for (int j = 0; j < ljflHandDevices.size(); j++) {
				ljflDevice = ljflHandDevices.get(j);
				Map<String, Object> mapValue = new HashMap<String, Object>();
				mapValue.put("id", j+1);
				mapValue.put("code", ljflDevice.getCode());
				mapValue.put("name", ljflDevice.getLastloginuserid());
				mapValue.put("logindate", ljflDevice.getId());
				mapValue.put("loginaddress", ljflDevice.getAddress());
				listmap.add(mapValue);
			}
			return listmap;
			
		}
		
		/**
		 * 获取当前登录用户
		 */
		@ResponseBody
		@RequestMapping(value="getuser")
		public String getuser(HttpServletRequest request,HttpSession session){
			
			String id = session.getAttribute("userid").toString();
			
			UserInfo selectByPrimaryKey = userService.selectByPrimaryKey(id);
			
			Gson gson = new Gson();
			String json = gson.toJson(selectByPrimaryKey);
			System.out.println(json);
			return json;
			
		}
		
}


