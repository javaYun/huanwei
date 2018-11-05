package com.sczh.platform.Controller.api;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sczh.platform.po.AreaCommunity;
import com.sczh.platform.po.AreaGov;
import com.sczh.platform.po.AreaRelation;
import com.sczh.platform.po.CloudWxLoginUser;
import com.sczh.platform.po.LjflAccountModify;
import com.sczh.platform.po.LjflHandDevice;
import com.sczh.platform.po.LjflPrize;
import com.sczh.platform.po.LjflPrizeProvide;
import com.sczh.platform.po.LjflPrizeProvideRecord;
import com.sczh.platform.po.LjflPrizeProvideRecord1;
import com.sczh.platform.po.LjflPutRecord;
import com.sczh.platform.po.LjflPutRecord1;
import com.sczh.platform.po.LjflRecycleObject;
import com.sczh.platform.po.LjflRecycleRecord1;
import com.sczh.platform.po.LjflRecycleType;
import com.sczh.platform.po.LjflScheduleJobConfig;
import com.sczh.platform.po.LjflScoreRecord;
import com.sczh.platform.po.LjflScoreRecord1;
import com.sczh.platform.po.LjflSmartCard;
import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.LjflType;
import com.sczh.platform.po.MenuInfo;
import com.sczh.platform.po.MenuInfo1;
import com.sczh.platform.po.PersonInfo;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.service.ApiService;
import com.sczh.platform.service.AreaService;
import com.sczh.platform.service.LjflAccountModifyService;
import com.sczh.platform.service.LjflPrizeService;
import com.sczh.platform.service.LjflPutRecordService;
import com.sczh.platform.service.LjflScoreService;
import com.sczh.platform.service.LjflStaffDetailService;
import com.sczh.platform.service.PersonService;
import com.sczh.platform.service.UserService;
import com.yunlao.util.MD5andKL;
import com.yunlao.util.Point;
import com.yunlao.util.SpringmvcUtils;
import com.yunlao.util.TableUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.patterns.IfPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "api/user" })
public class UserApiController {
	@Autowired
	private UserService userService;
	@Autowired
	private AreaService AreaService;
	@Autowired
	private ApiService apiService;
	@Autowired
	private LjflStaffDetailService ljflStaffDetailService;
	@Autowired
	private LjflPutRecordService ljflPutRecordService;
	@Autowired
	private PersonService personService;
	@Autowired
	private LjflScoreService ljflScoreService;
	@Autowired
	private LjflPrizeService LjflPrizeService;
	@Autowired
	private LjflAccountModifyService LjflAccountModifyService;

	/**
	 * 回收物品类型
	 */
	@ResponseBody
	@RequestMapping(value = "/loadRecycleType", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String loadRecycleType(HttpServletRequest request)
			throws IOException {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");

		JSONObject parseObject = JSONObject.parseObject(parameter);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		// 手持端，打开app就同步数据，此时没有登录，没有Tenantid，后台自己获取
		String tenantId = parseObject.getString("tenantId");
		if (tenantId == null || tenantId == "") {
			String tenantCode = parseObject.getString("tenantCode");
			LjflScheduleJobConfig selectByTenantCode = apiService
					.selectByTenantCode(tenantCode);
			tenantId = selectByTenantCode.getTenantid();
		}

		if (tenantId == null || tenantId == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "tenantId为null");
			return jsonObject.toString();
		}

		List<LjflRecycleType> selectByTenantId = apiService
				.selectByTenantId(tenantId);

		if (selectByTenantId != null) {

			Gson gson = new Gson();
			String json = gson.toJson(selectByTenantId);

			jsonObject.put("result", "0");
			jsonObject.put("data", json);
			jsonObject.put("msg", null);

		}

		System.out.println(parameter);

		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	/**
	 * 回收资源种类
	 */
	@ResponseBody
	@RequestMapping(value = "/loadRecycleObject", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String loadRecycleObject(HttpServletRequest request)
			throws IOException {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");

		JSONObject parseObject = JSONObject.parseObject(parameter);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		// 手持端，打开app就同步数据，此时没有登录，没有Tenantid，后台自己获取
		String tenantId = parseObject.getString("tenantId");
		if (tenantId == null || tenantId == "") {
			String tenantCode = parseObject.getString("tenantCode");
			LjflScheduleJobConfig selectByTenantCode = apiService
					.selectByTenantCode(tenantCode);
			tenantId = selectByTenantCode.getTenantid();
		}

		if (tenantId == null || tenantId == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "tenantId为null");
			return jsonObject.toString();
		}

		List<LjflRecycleObject> selectObjectByTenantId = apiService
				.selectObjectByTenantId(tenantId);

		if (selectObjectByTenantId != null) {

			Gson gson = new Gson();
			String json = gson.toJson(selectObjectByTenantId);

			jsonObject.put("result", "0");
			jsonObject.put("data", json);
			jsonObject.put("msg", null);

		}

		System.out.println(parameter);

		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String login(HttpServletRequest request) throws IOException {

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");

		JSONObject parseObject = JSONObject.parseObject(parameter);

		String userName = parseObject.getString("userName");
		String password = parseObject.getString("password");
		String latitudeDone = parseObject.getString("latitudeDone");
		String longitudeDone = parseObject.getString("longitudeDone");
		
		if (longitudeDone == null || longitudeDone == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "longitudeDone为null");
			return jsonObject.toString();
		}
		if (latitudeDone == null || latitudeDone == "") {
			
			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "latitudeDone为null");
			return jsonObject.toString();
		}
		
		
		System.out.println("纬度："+latitudeDone);
		System.out.println("经度："+longitudeDone);
		
		double latitudeDoneD = Double.parseDouble(latitudeDone);
		double longitudeDoneD = Double.parseDouble(longitudeDone);
		
		ArrayList<Double> polygonXA = new ArrayList<Double>();
		polygonXA.add(116.39184);
		polygonXA.add(116.493671);
		polygonXA.add(116.385375);
		polygonXA.add(116.470548);
		
		ArrayList<Double> polygonYA = new ArrayList<Double>();
		polygonYA.add(39.968496);
		polygonYA.add(39.963986);
		polygonYA.add(39.871131);
		polygonYA.add(39.876);
	
		
		Point point =  new Point();
		boolean pointInPolygon = point.isPointInPolygon(longitudeDoneD, latitudeDoneD, polygonXA, polygonYA);
		
		if(pointInPolygon==false){
			
			jsonObject.put("result", "1");
			jsonObject.put("data", "1");
			jsonObject.put("msg", "此坐标不在东四街道");
			System.out.println(jsonObject.toString());
			
			return jsonObject.toString();
		}
		
		password = MD5andKL.MD5(password);

		UserInfo userInfo = userService.selectByUserNameAndPassword(userName,
				password);
		if (userInfo != null) {

			Gson gson = new Gson();
			String json = gson.toJson(userInfo);

			jsonObject.put("result", "0");
			jsonObject.put("data", json);
			jsonObject.put("msg", null);
			System.out.println(jsonObject.toString());
			return jsonObject.toString();

		} else {
			jsonObject.put("result", "0");
			jsonObject.put("data", "用户名或密码错误");
			jsonObject.put("msg", null);
			System.out.println(jsonObject.toString());
			return jsonObject.toString();
		}

		
		

		//System.out.println(jsonObject.toString());
		//return jsonObject.toString();
	}

	/**
	 * 获取权限
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getAuthority", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getAuthority(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");
		JSONObject parseObject = JSONObject.parseObject(parameter);

		String userId = parseObject.getString("userId");

		UserInfo selectByPrimaryKey = userService.selectByPrimaryKey(userId);
		// 获取app权限
		String authority = selectByPrimaryKey.getAuthority();
		String substring = authority.substring(10, authority.length() - 1);
		String[] split = substring.split(", MenuInfo ");
		List<MenuInfo1> quanxian = new ArrayList<MenuInfo1>();
		for (int i = 0; i < split.length; i++) {

			String str = split[i];

			if (str.contains("APP")) {

				String substring2 = str.substring(1, str.length() - 1);
				String[] split2 = substring2.split(", ");

				MenuInfo1 menuInfo = new MenuInfo1();
				for (int j = 0; j < split2.length; j++) {
					String str2 = split2[j];
					int indexOf = str2.indexOf("=");

					if (str2.contains("pid")) {
						String pId = str2.substring(indexOf + 1);
						if (pId.equals("")) {
							menuInfo.setPID("0");
						} else {
							menuInfo.setPID(pId);
						}
						
					} else if (str2.contains("url")) {
						String onclick = str2.substring(indexOf + 1);
						menuInfo.setOnclick(onclick);
					} else if (str2.contains("text")) {
						String name = str2.substring(indexOf + 1);
						menuInfo.setName(name);
					} else if (str2.contains("state")) {
						String state = str2.substring(indexOf + 1);
						
					} else if (str2.contains("iconcls")) {
						String iconcls = str2.substring(indexOf + 1);
						
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
			} else {

			}
		}
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		if (selectByPrimaryKey != null) {

			Gson gson = new Gson();
			String json = gson.toJson(quanxian);

			jsonObject.put("result", "0");
			jsonObject.put("data", json);
			jsonObject.put("msg", null);

		}

		System.out.println(selectByPrimaryKey);
		System.out.println(parameter);

		String string = jsonObject.toString();

		System.out.println(jsonObject.toString());
		return string;
	}

	/**
	 * 获取区域
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getCounty", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getArea(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<AreaGov> selectaraegovinfos = AreaService
				.selectaraegovinfos("county");
		List<AreaRelation> county = new ArrayList<AreaRelation>();
		for (AreaGov areaGov : selectaraegovinfos) {

			String id = areaGov.getId();
			AreaRelation selectById = AreaService.selectById(id);

			county.add(selectById);
		}

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		if (county != null) {

			Gson gson = new Gson();
			String json = gson.toJson(county);

			jsonObject.put("result", "0");
			jsonObject.put("data", json);
			jsonObject.put("msg", null);

		}

		System.out.println(county.toString());

		String string = jsonObject.toString();
		System.out.println(string);
		return string;
	}

	/*
	 * 获取街道
	 */
	@ResponseBody
	@RequestMapping(value = "/getStreet", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getStreet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<AreaGov> selectaraegovinfos = AreaService
				.selectaraegovinfos("street");
		List<AreaRelation> street = new ArrayList<AreaRelation>();
		for (AreaGov areaGov : selectaraegovinfos) {

			String id = areaGov.getId();
			AreaRelation selectById = AreaService.selectById(id);

			street.add(selectById);
		}

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		if (street != null) {

			Gson gson = new Gson();
			String json = gson.toJson(street);

			jsonObject.put("result", "0");
			jsonObject.put("data", json);
			jsonObject.put("msg", null);

		}

		System.out.println(street.toString());

		String string = jsonObject.toString();
		System.out.println(string);
		return string;
	}

	/**
	 * 获取社区
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getCommunity", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getCommunity(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<AreaGov> selectaraegovinfos = AreaService
				.selectaraegovinfos("community");
		List<AreaRelation> community = new ArrayList<AreaRelation>();
		for (AreaGov areaGov : selectaraegovinfos) {

			String id = areaGov.getId();
			AreaRelation selectById = AreaService.selectById(id);

			community.add(selectById);
		}

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		if (community != null) {

			Gson gson = new Gson();
			String json = gson.toJson(community);

			jsonObject.put("result", "0");
			jsonObject.put("data", json);
			jsonObject.put("msg", null);

		}

		System.out.println(community.toString());

		String string = jsonObject.toString();
		System.out.println(string);
		return string;
	}

	/**
	 * 获取蜂巢
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getNest", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getNest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<AreaCommunity> selectareaCommunities = AreaService
				.selectareaCommunities();

		List<AreaRelation> community = new ArrayList<AreaRelation>();
		for (AreaCommunity areaCommunity : selectareaCommunities) {

			String id = areaCommunity.getId();
			AreaRelation selectById = AreaService.selectById(id);

			community.add(selectById);
		}

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		if (community != null) {

			Gson gson = new Gson();
			String json = gson.toJson(community);

			jsonObject.put("result", "0");
			jsonObject.put("data", json);
			jsonObject.put("msg", null);

		}

		System.out.println(community.toString());

		String string = jsonObject.toString();
		System.out.println(string);
		return string;
	}

	/**
	 * 获取登录情况
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	/*@ResponseBody
	@RequestMapping(value = "/loginInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String loginInfo(HttpServletRequest request) throws IOException {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");

		JSONObject parseObject = JSONObject.parseObject(parameter);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		String lastLoginTimestr = parseObject.getString("lastLoginTime");
		String latitudeDone = parseObject.getString("latitudeDone");
		String longitudeDone = parseObject.getString("longitudeDone");
		String code = parseObject.getString("code");
		String lastLoginUserId = parseObject.getString("lastLoginUserId");
		Date lastLoginTime = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			lastLoginTime = sdf.parse(lastLoginTimestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		double latitudeDoneD = Double.parseDouble(latitudeDone);
		double longitudeDoneD = Double.parseDouble(longitudeDone);
		
		ArrayList<Double> polygonXA = new ArrayList<Double>();
		polygonXA.add(116.44092);
		polygonXA.add(116.440232);
		polygonXA.add(116.423308);
		polygonXA.add(116.423901);
		
		ArrayList<Double> polygonYA = new ArrayList<Double>();
		polygonYA.add(39.930788);
		polygonYA.add(39.947404);
		polygonYA.add(39.947127);
		polygonYA.add(39.930849);
		
		
		Point point =  new Point();
		boolean pointInPolygon = point.isPointInPolygon(longitudeDoneD, latitudeDoneD, polygonXA, polygonYA);
		
		if(pointInPolygon==false){
			
			jsonObject.put("result", "1");
			jsonObject.put("data", "1");
			jsonObject.put("msg", "此坐标不在东四街道");
		}
		
		
		LjflHandDevice selectByHandDevice = apiService.selectByHandDevice(code);
		Date date = new Date();
		if (selectByHandDevice != null) {

			selectByHandDevice.setLastchangetime(date);
			selectByHandDevice.setLastlogintime(lastLoginTime);
			selectByHandDevice
					.setLatitudedone(Double.parseDouble(latitudeDone));
			selectByHandDevice.setLongitudedone(Double
					.parseDouble(longitudeDone));
			selectByHandDevice.setLastloginuserid(lastLoginUserId);

			int updateHandDevice = apiService
					.updateHandDevice(selectByHandDevice);
			if (updateHandDevice == 1) {

				System.out.println("签到成功");
			}

		} else {
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			LjflHandDevice ljflHandDevice = new LjflHandDevice();
			ljflHandDevice.setId(id);
			ljflHandDevice.setCreatetime(date);
			ljflHandDevice.setLastchangetime(date);
			ljflHandDevice.setStatus(0);
			ljflHandDevice.setBeendeleted(0);
			ljflHandDevice.setTenantid("1");
			ljflHandDevice.setAddress("");
			ljflHandDevice.setCode(code);
			ljflHandDevice.setLastlogintime(lastLoginTime);
			ljflHandDevice.setLastloginuserid(lastLoginUserId);
			ljflHandDevice.setLatitudedone(Double.parseDouble(latitudeDone));
			ljflHandDevice.setLongitudedone(Double.parseDouble(longitudeDone));

			int insertHandDevice = apiService.insertHandDevice(ljflHandDevice);
			if (insertHandDevice == 1) {
				System.out.println("ok");
			}
		}

		jsonObject.put("result", "0");
		jsonObject.put("data", null);
		jsonObject.put("msg", "成功");
	
		
		System.out.println(parameter);

		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}*/

	/**
	 * 获取刷卡信息
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getcardInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getcardInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");

		JSONObject parseObject = JSONObject.parseObject(parameter);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> housemap = new HashMap<String, Object>();
		Map<String, Object> staffDetailMap = new HashMap<String, Object>();// 人员
		Map<String, Object> rubbishMap = new HashMap<String, Object>();
		String code = parseObject.getString("cardId");

		if (code != null || code != "") {

			LjflSmartCard selectByCcode = apiService.selectByCcode(code);

			System.out.println(selectByCcode);

			String getfBindId = selectByCcode.getfBindId();
			LjflStaffDetail selectByPrimaryKey = ljflStaffDetailService
					.selectByPrimaryKey(getfBindId);

			AreaCommunity selectByPrimaryKey3 = AreaService
					.selectByPrimaryKey(selectByPrimaryKey.getfHouseholdId());

			LjflType selectBycode = apiService.selectBycode("CYLJ");

			housemap.put("id", selectByPrimaryKey3.getId());
			housemap.put("name", selectByPrimaryKey3.getCommunityname());
			housemap.put("code", selectByPrimaryKey3.getCommunitycode());

			staffDetailMap.put("id", selectByPrimaryKey.getId());
			staffDetailMap.put("name", selectByPrimaryKey.getName());
			staffDetailMap.put("score", selectByPrimaryKey.getfCurrentScore());

			rubbishMap.put("category", "");
			rubbishMap.put("name", selectBycode.getfName());
			rubbishMap.put("code", selectBycode.getfCode());

			data.put("houses", housemap);
			data.put("recycleCategory", "");
			data.put("rubbishType", rubbishMap);
			data.put("cardType", selectByCcode.getfBindType());
			data.put("staffDetail", staffDetailMap);

			if (data != null) {

				Gson gson = new Gson();
				String json = gson.toJson(data);

				jsonObject.put("result", "0");
				jsonObject.put("data", json);
				jsonObject.put("msg", null);

			}

		}
		// System.out.println(userInfo);
		System.out.println(parameter);

		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	/**
	 * 根据手机号查询信息
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getcardInfoByPhone", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getcardInfoByCode(HttpServletRequest request)
			throws IOException {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> housemap = new HashMap<String, Object>();
		Map<String, Object> staffDetailMap = new HashMap<String, Object>();// 人员
		Map<String, Object> rubbishMap = new HashMap<String, Object>();

		JSONObject parseObject = JSONObject.parseObject(parameter);

		String phone = parseObject.getString("number");
		LjflStaffDetail selectByPhone = ljflStaffDetailService
				.selectByPhone(phone);
		String getfHouseholdId = selectByPhone.getfHouseholdId();

		String id = selectByPhone.getId();
		LjflSmartCard selectByfBindId = apiService.selectByfBindId(id);
		AreaCommunity selectByPrimaryKey3 = AreaService
				.selectByPrimaryKey(getfHouseholdId);
		LjflType selectBycode = apiService.selectBycode("CYLJ");

		housemap.put("id", selectByPrimaryKey3.getId());
		housemap.put("name", selectByPrimaryKey3.getCommunityname());
		housemap.put("code", selectByPrimaryKey3.getCommunitycode());

		staffDetailMap.put("id", selectByPhone.getId());
		staffDetailMap.put("name", selectByPhone.getName());
		staffDetailMap.put("score", selectByPhone.getfCurrentScore());

		rubbishMap.put("category", "");
		rubbishMap.put("name", selectBycode.getfName());
		rubbishMap.put("code", selectBycode.getfCode());

		data.put("houses", housemap);
		data.put("recycleCategory", "");
		data.put("rubbishType", rubbishMap);
		data.put("cardType", selectByfBindId.getfBindType());
		data.put("staffDetail", staffDetailMap);

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		if (data != null) {

			Gson gson = new Gson();
			String json = gson.toJson(data);

			jsonObject.put("result", "0");
			jsonObject.put("data", json);
			jsonObject.put("msg", null);

		}

		// System.out.println(userInfo);
		System.out.println(parameter);

		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	/**
	 * 提交重量
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/saveweight", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String saveweight(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");
		Map<String, Object> data = new HashMap<String, Object>();

		
		JSONObject parseObject = JSONObject.parseObject(parameter);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		if(parameter==null||parameter.equals("")){
			
			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "parameters为null");
			return jsonObject.toString();
		}else{
			
			System.out.println("===============================================================================");
			System.out.println(parameter);
		}
		// 确认人id
		String comfirmUserId = parseObject.getString("comfirmUserId");
		if (comfirmUserId == null || comfirmUserId == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "comfirmUserId为null");
			return jsonObject.toString();
		}
		// 单位id
		String unitId = parseObject.getString("unitId");
		if (unitId == null || unitId == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "unitId为null");
			return jsonObject.toString();
		}
		// 经纬度
		String latitudeDone = parseObject.getString("latitudeDone");
		if (latitudeDone == null || latitudeDone == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "latitudeDone为null");
			return jsonObject.toString();
		}
		String longitudeDone = parseObject.getString("longitudeDone");
		if (longitudeDone == null || longitudeDone == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "longitudeDone为null");
			return jsonObject.toString();
		}
		// 投放时间
		String swingTimestr = parseObject.getString("swingTime");
		if (swingTimestr == null || swingTimestr == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "swingTime为null");
			return jsonObject.toString();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date swingTime = null;
		swingTime = sdf.parse(swingTimestr);

		// 手持端传过来的id
		String recordId = parseObject.getString("recordId");
		if (recordId == null || recordId == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "recordId为null");
			return jsonObject.toString();
		}
		// 重量
		Object weightMap = parseObject.get("weightMap");
		if (weightMap == null || weightMap == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "weightMap为null");
			return jsonObject.toString();
		}

		JSONObject parseObject2 = JSONObject.parseObject(weightMap.toString());
		String name = parseObject2.getString("name");
		if (name == null || name == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "name为null");
			return jsonObject.toString();
		}
		// 垃圾类型
		String code = parseObject2.getString("code");
		if (code == null || code == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "该类型不存在");
			return jsonObject.toString();
		}
		LjflType selectBycode = apiService.selectBycode(code);

		// 重量
		String weight = parseObject2.getString("weight");
		if (weight == null || weight == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "weight为null");
			return jsonObject.toString();
		}

		// 居民id
		String staffDetailId = parseObject.getString("staffDetailId");
		if (staffDetailId == null || staffDetailId == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "staffDetailId为null");
			return jsonObject.toString();
		}
		LjflStaffDetail selectByPrimaryKey = ljflStaffDetailService
				.selectByPrimaryKey(staffDetailId);
		if (selectByPrimaryKey == null) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "居民不存在");
			return jsonObject.toString();
		}

		String tablename = TableUtil.getTableNameString("ljfl_put_record",
				swingTimestr);
		String tablename2 = TableUtil.getTableNameString("ljfl_score_record",
				swingTimestr);
		 String todayTime = TableUtil.getTodayTime(swingTimestr);
		 List<LjflScoreRecord> ljflPutRecordsList = ljflScoreService.selectBydate(tablename2,todayTime,staffDetailId);
		 double totalscore = 0; 
		 BigDecimal bd1 = new BigDecimal(totalscore);
		 for (LjflScoreRecord ljflScoreRecord : ljflPutRecordsList) {
			 double db2 = 0;
			 String getfAddType = ljflScoreRecord.getfAddType();
			 
			
			 
			 if(getfAddType=="swingCard" || getfAddType.equals("swingCard")){
				 
				 System.out.println("1"+ljflScoreRecord.toString());
				 
				 Double getfAddScore = ljflScoreRecord.getfAddScore(); 
				 
				 
				 db2 = getfAddScore; 
				 
			 }
			 
			 BigDecimal bd2 = new BigDecimal(db2);
			 
			 System.out.println("3"+bd1);
			 System.out.println("4"+bd2);
			 
			 bd1 = bd1.add(bd2);
			 
			 System.out.println("5"+bd1);
		 
		 }
		 BigDecimal bd3 = new BigDecimal("8");
		 
		 if(bd1.compareTo(bd3)==1){
		 
			 jsonObject.put("result", "0"); jsonObject.put("data", "");
			 jsonObject.put("msg", "今日已达上限"); 
			 return jsonObject.toString(); 
		 }
		
		String time = TableUtil.getTableNameString("", swingTimestr);
		// 户
		String getfHouseholdId = selectByPrimaryKey.getfHouseholdId();

		LjflPutRecord1 ljflPutRecord = new LjflPutRecord1();
		ljflPutRecord.setId(recordId);
		ljflPutRecord.setCreatetime(swingTimestr);
		ljflPutRecord.setLastchangetime(swingTimestr);
		ljflPutRecord.setStatus("0");
		ljflPutRecord.setTenantid(parseObject.getString("unitId"));
		ljflPutRecord.setBeendeleted("0");
		ljflPutRecord.setDivisiontime(swingTimestr);
		ljflPutRecord.setfConfirmLatitudeDone(latitudeDone);
		ljflPutRecord.setfConfirmLongitudeDone(longitudeDone);
		ljflPutRecord.setfConfirmUserId(comfirmUserId);
		ljflPutRecord.setfSwingTime(swingTimestr);
		ljflPutRecord.setfStaffDetailId(staffDetailId);
		ljflPutRecord.setfHouseholdId(getfHouseholdId);
		ljflPutRecord.setfWeight(weight);
		ljflPutRecord.setfConfirmRubbishTypeId(selectBycode.getId());

		System.out.println(ljflPutRecord.toString());

		ljflPutRecord.setTablename(tablename);
		int insert = ljflPutRecordService.insert(ljflPutRecord);

		BigDecimal b2 = new BigDecimal(weight);
		BigDecimal b4 = new BigDecimal("2");
		// 积分
		BigDecimal multiply = b2.multiply(b4);
		BigDecimal max = new BigDecimal("5");

		if (multiply.compareTo(max) == 1) {

			multiply = max;
		}

		LjflScoreRecord1 ljflScoreRecord = new LjflScoreRecord1();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		ljflScoreRecord.setId(id);
		Date date = new Date();
		ljflScoreRecord.setCreatetime(swingTimestr);
		ljflScoreRecord.setLastchangetime(swingTimestr);
		ljflScoreRecord.setStatus("0");
		ljflScoreRecord.setBeendeleted("0");
		ljflScoreRecord.setTenantid(unitId);
		ljflScoreRecord.setfAddObjectId(recordId + time);
		ljflScoreRecord.setfAddScore(multiply.toString());
		ljflScoreRecord.setfAddScoreInTheory((b2.multiply(b4)).toString());
		ljflScoreRecord.setfAddType("swingCard");
		ljflScoreRecord.setfScoreTime(swingTimestr);
		ljflScoreRecord.setfStaffDetailId(staffDetailId);
		ljflScoreRecord.setDivisiontime(swingTimestr);
		
		ljflScoreRecord.setTablename(tablename2);
		int insert2 = ljflScoreService.insert(ljflScoreRecord);

		Double getfCurrentScore = selectByPrimaryKey.getfCurrentScore();
		BigDecimal b1 = new BigDecimal(getfCurrentScore.toString());
		double doubleValue = b1.add(multiply).doubleValue();
		selectByPrimaryKey.setfCurrentScore(doubleValue);
		Double getfTotalScore = selectByPrimaryKey.getfTotalScore();
		BigDecimal b3 = new BigDecimal(getfTotalScore.toString());
		double doubleValue2 = b3.add(multiply).doubleValue();
		selectByPrimaryKey.setfTotalScore(doubleValue2);
		selectByPrimaryKey.setLastchangetime(swingTime);
		selectByPrimaryKey.setfLastAddScoreTime(swingTime);
		int updateByPrimaryKey = ljflStaffDetailService
				.updateByPrimaryKey(selectByPrimaryKey);
		
		System.out.println("1:"+updateByPrimaryKey);
		System.out.println("2:"+insert);
		System.out.println("3:"+insert2);
		
		if (updateByPrimaryKey == 1 && insert == 1 && insert2 == 1) {
			data.put("score", multiply);
			data.put("currentScore", doubleValue);
			if (data != null) {

				Gson gson = new Gson();
				String json = gson.toJson(data);

				jsonObject.put("result", "0");
				jsonObject.put("data", json);
				jsonObject.put("msg", "保存成功");

			}
			return jsonObject.toString();
		} else {

			jsonObject.put("result", "0");
			jsonObject.put("data", null);
			jsonObject.put("msg", "提交失败");
			return jsonObject.toString();

		}

	}

	/**
	 * 资源回收
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/saveRecycle", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String saveRecycle(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");
		Map<String, Object> data = new HashMap<String, Object>();

		JSONObject parseObject = JSONObject.parseObject(parameter);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		// 手持端传过来的id
		String recordId = parseObject.getString("recordId");
		if (recordId == null || recordId == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "recordId为null");
			return jsonObject.toString();
		}

		// 居民id
		String staffDetailId = parseObject.getString("staffDetailId");
		if (staffDetailId == null || staffDetailId == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "staffDetailId为null");
			return jsonObject.toString();
		}
		LjflStaffDetail selectByPrimaryKey = ljflStaffDetailService
				.selectByPrimaryKey(staffDetailId);
		if (selectByPrimaryKey == null) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "居民不存在");
			return jsonObject.toString();
		}
		// 回收物品id
		String recycleObjectId = parseObject.getString("recycleObjectId");
		if (recycleObjectId == null || recycleObjectId == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "recycleObjectId为null");
			return jsonObject.toString();
		}
		LjflRecycleObject selectRecycleObject = apiService
				.selectRecycleObject(recycleObjectId);
		if (selectRecycleObject == null) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "回收物品类型不存在");
			return jsonObject.toString();
		}
		// 单位id
		String unitId = parseObject.getString("unitId");
		if (unitId == null || unitId == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "unitId为null");
			return jsonObject.toString();
		}
		// 回收人员id
		String recycleStaffId = parseObject.getString("recycleStaffId");
		if (recycleStaffId == null || recycleStaffId == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "recycleStaffId为null");
			return jsonObject.toString();
		}
		// 确认时间
		String confirmTimeStr = parseObject.getString("confirmTimeStr");
		if (confirmTimeStr == null || confirmTimeStr == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "confirmTimeStr为null");
			return jsonObject.toString();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date confirmTime = null;
		confirmTime = sdf.parse(confirmTimeStr);

		// 积分类型
		String scoreType = parseObject.getString("scoreType");
		if (scoreType == null || scoreType == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "scoreType为null");
			return jsonObject.toString();
		}
		// 总积分
		String totalScoreValue = parseObject.getString("totalScoreValue");
		if (totalScoreValue == null || totalScoreValue == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "totalScoreValue为null");
			return jsonObject.toString();
		}

		String unitValue = parseObject.getString("unitValue");
		if (unitValue == null || unitValue == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "unitValue为null");
			return jsonObject.toString();
		}
		// 经纬度
		String latitudeDone = parseObject.getString("latitudeDone");
		if (latitudeDone == null || latitudeDone == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "latitudeDone为null");
			return jsonObject.toString();
		}
		String longitudeDone = parseObject.getString("longitudeDone");
		if (longitudeDone == null || longitudeDone == "") {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "longitudeDone为null");
			return jsonObject.toString();
		}

		LjflRecycleRecord1 ljflRecycleRecord1 = new LjflRecycleRecord1();
		ljflRecycleRecord1.setId(recordId);
		ljflRecycleRecord1.setCreatetime(confirmTimeStr);
		ljflRecycleRecord1.setLastchangetime(confirmTimeStr);
		ljflRecycleRecord1.setStatus("0");
		ljflRecycleRecord1.setTenantid(parseObject.getString("unitId"));
		ljflRecycleRecord1.setBeendeleted("0");
		ljflRecycleRecord1.setDivisiontime(confirmTimeStr);
		ljflRecycleRecord1.setfConfirmTime(confirmTimeStr);
		ljflRecycleRecord1.setfRecycleStaffId(recycleStaffId);
		ljflRecycleRecord1.setfScoreType(scoreType);
		ljflRecycleRecord1.setfTotalScoreValue(totalScoreValue);
		ljflRecycleRecord1.setfUnitValue(unitValue);
		ljflRecycleRecord1.setfRecycleObjectId(recycleObjectId);
		ljflRecycleRecord1.setfStaffDetailId(staffDetailId);
		ljflRecycleRecord1.setLatitudedone(latitudeDone);
		ljflRecycleRecord1.setLongitudedone(longitudeDone);

		String tablename = TableUtil.getTableNameString("ljfl_recycle_record",
				confirmTimeStr);
		ljflRecycleRecord1.setTablename(tablename);
		System.out.println(ljflRecycleRecord1.toString());
		int insert = apiService.insertRecycleRecord(ljflRecycleRecord1);

		String time = TableUtil.getTableNameString("", confirmTimeStr);
		LjflScoreRecord1 ljflScoreRecord = new LjflScoreRecord1();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		ljflScoreRecord.setId(id);
		Date date = new Date();
		ljflScoreRecord.setCreatetime(confirmTimeStr);
		ljflScoreRecord.setLastchangetime(confirmTimeStr);
		ljflScoreRecord.setStatus("0");
		ljflScoreRecord.setBeendeleted("0");
		ljflScoreRecord.setTenantid(unitId);
		ljflScoreRecord.setfAddObjectId(recordId + time);
		ljflScoreRecord.setfAddScore(totalScoreValue);
		ljflScoreRecord.setfAddScoreInTheory(totalScoreValue);
		ljflScoreRecord.setfAddType("recycle");
		ljflScoreRecord.setfScoreTime(confirmTimeStr);
		ljflScoreRecord.setfStaffDetailId(staffDetailId);
		ljflScoreRecord.setDivisiontime(confirmTimeStr);
		String tablename2 = TableUtil.getTableNameString("ljfl_score_record",
				confirmTimeStr);
		ljflScoreRecord.setTablename(tablename2);
		int insert2 = ljflScoreService.insert(ljflScoreRecord);

		Double getfCurrentScore = selectByPrimaryKey.getfCurrentScore();
		BigDecimal b1 = new BigDecimal(getfCurrentScore.toString());
		BigDecimal b2 = new BigDecimal(Double.parseDouble(totalScoreValue));
		double doubleValue = b1.add(b2).doubleValue();
		selectByPrimaryKey.setfCurrentScore(doubleValue);
		Double getfTotalScore = selectByPrimaryKey.getfTotalScore();
		BigDecimal b3 = new BigDecimal(getfTotalScore.toString());
		double doubleValue2 = b3.add(b2).doubleValue();
		selectByPrimaryKey.setfTotalScore(doubleValue2);
		selectByPrimaryKey.setLastchangetime(confirmTime);
		selectByPrimaryKey.setfLastAddScoreTime(confirmTime);

		int updateByPrimaryKey = ljflStaffDetailService
				.updateByPrimaryKey(selectByPrimaryKey);
		if (updateByPrimaryKey == 1 && insert == 1 && insert2 == 1) {
			data.put("score", b2);
			data.put("totalScore", doubleValue);
			if (data != null) {

				Gson gson = new Gson();
				String json = gson.toJson(data);

				jsonObject.put("result", "0");
				jsonObject.put("data", json);
				jsonObject.put("msg", "回收记录上传成功");

			}
			return jsonObject.toString();
		}
		return jsonObject.toString();
	}

	@ResponseBody
	@RequestMapping(value = { "/getaddress" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST }, produces = { "application/json;charset=UTF-8" })
	public String getaddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");
		Map<String, Object> data = new HashMap<String, Object>();

		com.alibaba.fastjson.JSONObject parseObject = com.alibaba.fastjson.JSONObject
				.parseObject(parameter);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		String Id = parseObject.getString("id");
		PersonInfo selectByUserId = personService.selectByUserId(Id);

		// 蜂巢
		String areaid = selectByUserId.getAreaid();
		AreaCommunity selectByPrimaryKey = AreaService.selectByPrimaryKey(areaid);
		String communitydescribe = selectByPrimaryKey.getCommunitydescribe();
		if(communitydescribe=="1" || communitydescribe.equals("1")){
			
			jsonObject.put("result", "-1");
			jsonObject.put("data", "null");
			jsonObject.put("msg", null);
			return jsonObject.toString();
		}else{
		
		data.put("fengchao", areaid);
		AreaRelation selectById = AreaService.selectById(areaid);
		// 居委会
		String getpId = selectById.getpId();
		data.put("shequ", getpId);
		AreaRelation selectById2 = AreaService.selectById(getpId);
		// 街道
		String getpId2 = selectById2.getpId();
		data.put("jiedao", getpId2);
		AreaRelation selectById3 = AreaService.selectById(getpId2);
		// 区
		String getpId3 = selectById3.getpId();
		data.put("qu", getpId3);
		Gson gson = new Gson();
		String json = gson.toJson(data);

		jsonObject.put("result", "0");
		jsonObject.put("data", json);
		jsonObject.put("msg", null);
		return jsonObject.toString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getprizedesc", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getprizedesc(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");
		// Map<String, Object> data = new HashMap<String, Object>();

		JSONObject parseObject = JSONObject.parseObject(parameter);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		System.out.println(path);
		System.out.println(basePath);
		List<LjflPrize> selectPrizeInfoListTotal = LjflPrizeService
				.selectPrizeInfoListTotal();

		for (LjflPrize ljflPrize : selectPrizeInfoListTotal) {

			String getfPicture = ljflPrize.getfPicture();
			String photourl = basePath + getfPicture;
			ljflPrize.setfPicture(photourl);

		}

		Gson gson = new Gson();
		String json = gson.toJson(selectPrizeInfoListTotal);

		jsonObject.put("result", "0");
		jsonObject.put("data", json);
		jsonObject.put("msg", null);
		return jsonObject.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/exchangeGoods", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String exchangeGoods(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");
		Map<String, Object> data = new HashMap<String, Object>();

		JSONObject parseObject = JSONObject.parseObject(parameter);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		String staffId = parseObject.getString("staffId");
		if (staffId == null || staffId.equals("")) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "人员id为null");
			return jsonObject.toString();
		}

		CloudWxLoginUser selectBystaffId = userService.selectBystaffId(staffId);
		if (selectBystaffId == null || selectBystaffId.equals("")) {
			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "未设置手机号，商品无法兑换");
			return jsonObject.toString();
		}

		String prizeId = parseObject.getString("prizeId");
		if (prizeId == null || prizeId.equals("")) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "奖品id为null");
			return jsonObject.toString();
		}
		LjflPrize ljflprize = LjflPrizeService.selectByPrimaryKey1(prizeId);
		if (ljflprize == null || ljflprize.equals("")) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "没有此奖品");
			return jsonObject.toString();
		}
		String prizeScore = parseObject.getString("prizeScore");
		if (prizeScore == null || prizeScore.equals("")) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "奖品积分为null");
			return jsonObject.toString();
		}
		String exchangePsd = parseObject.getString("exchangePsd");
		if (exchangePsd == null || exchangePsd.equals("")) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "兑换密码为null");
			return jsonObject.toString();
		}

		String getfOriginalPassword = selectBystaffId.getfOriginalPassword();
		if (!getfOriginalPassword.equals(exchangePsd)) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "密码错误");
			return jsonObject.toString();
		}

		String carId = parseObject.getString("carId");
		if (carId == null || carId.equals("")) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "卡号为null");
			return jsonObject.toString();
		}

		String userid = parseObject.getString("id");
		if (userid == null || userid.equals("")) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "用户id为null");
			return jsonObject.toString();
		}
		String username = parseObject.getString("name");
		if (username == null || username.equals("")) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "用户name为null");
			return jsonObject.toString();
		}

		BigDecimal needscore = new BigDecimal(prizeScore);
		BigDecimal count = new BigDecimal(1);
		BigDecimal multiply = needscore.multiply(count);

		LjflStaffDetail ljflStaffDetail = ljflStaffDetailService
				.selectByPrimaryKey(staffId);
		Double getfCurrentScore = ljflStaffDetail.getfCurrentScore();
		BigDecimal CurrentScore = new BigDecimal(getfCurrentScore.toString());
		if (CurrentScore.compareTo(multiply) == -1) {
			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "你的积分不足");
			return jsonObject.toString();
		} else {

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sdf.format(date);
			
			LjflAccountModify ljflAccountModify = new LjflAccountModify();

			String accountmodifyid = UUID.randomUUID().toString()
					.replaceAll("-", "");
			ljflAccountModify.setId(accountmodifyid);
			ljflAccountModify.setCreatetime(date);
			ljflAccountModify.setLastchangetime(date);
			ljflAccountModify.setStatus(0);
			ljflAccountModify.setBeendeleted(0);
			ljflAccountModify.setTenantid("");
			ljflAccountModify.setfAddBalance(Double.valueOf("0"));
			ljflAccountModify.setfAddScore(0 - (multiply.doubleValue()));
			ljflAccountModify.setfModifyUserId(userid);
			ljflAccountModify.setfModifyUserName(username);
			ljflAccountModify.setfStaffDetailId(staffId);
			ljflAccountModify.setfMemo(ljflprize.getfName());

			int insert = LjflAccountModifyService.insert(ljflAccountModify);

			LjflScoreRecord1 ljflScoreRecord = new LjflScoreRecord1();
			String id1 = UUID.randomUUID().toString().replaceAll("-", "");
			String time = TableUtil.getTableNameString("", format);
			ljflScoreRecord.setId(id1);
			ljflScoreRecord.setCreatetime(format);
			ljflScoreRecord.setLastchangetime(format);
			ljflScoreRecord.setStatus("0");
			ljflScoreRecord.setBeendeleted("0");
			ljflScoreRecord.setTenantid(ljflStaffDetail.getTenantid());
			ljflScoreRecord.setfAddObjectId(accountmodifyid + time);
			ljflScoreRecord.setfAddScore("-" + (multiply.doubleValue()));
			ljflScoreRecord
					.setfAddScoreInTheory("-" + (multiply.doubleValue()));
			ljflScoreRecord.setfAddType("prizeProvide");
			ljflScoreRecord.setfScoreTime(format);
			ljflScoreRecord.setfStaffDetailId(staffId);
			ljflScoreRecord.setDivisiontime(format);
			String tablename2 = TableUtil.getTableNameString(
					"ljfl_score_record", format);
			ljflScoreRecord.setTablename(tablename2);
			int insert2 = ljflScoreService.insert(ljflScoreRecord);

			
			LjflPrizeProvide selectByPrizeId = LjflPrizeService
					.selectByPrizeId(prizeId);
			Integer getfTakeNum = selectByPrizeId.getfTakeNum();
			Integer takenum = getfTakeNum + 1;
			selectByPrizeId.setfTakeNum(takenum);
			selectByPrizeId.setLastchangetime(date);
			int updateByPrimaryKey1 = LjflPrizeService
					.updateByPrimaryKey(selectByPrizeId);

			LjflPrizeProvideRecord1 ljflPrizeProvideRecord = new LjflPrizeProvideRecord1();
			String id2 = UUID.randomUUID().toString().replaceAll("-", "");
			ljflPrizeProvideRecord.setId(id2);
			ljflPrizeProvideRecord.setCreatetime(date);
			ljflPrizeProvideRecord.setLastchangetime(date);
			ljflPrizeProvideRecord.setStatus(0);
			ljflPrizeProvideRecord.setBeendeleted(0);
			ljflPrizeProvideRecord
					.setTenantid("4eaf9423eb0043648e502134586b088a");
			ljflPrizeProvideRecord.setDivisiontime(date);
			ljflPrizeProvideRecord.setProvidenum(Integer.valueOf(1));
			ljflPrizeProvideRecord.setProvidestatus(1);
			ljflPrizeProvideRecord.setSpendscore(Double.valueOf(prizeScore));
			ljflPrizeProvideRecord.setPrizeid(prizeId);
			ljflPrizeProvideRecord.setPrizeprovideid(selectByPrizeId.getId());
			ljflPrizeProvideRecord.setStaffdetailid(staffId);
			String tablename3 = TableUtil.getTableNameString(
					"ljfl_prize_provide_record", format);
			ljflPrizeProvideRecord.setTablename(tablename3);
			int insert3 = LjflPrizeService.insert(ljflPrizeProvideRecord);

			
			double doubleValue = CurrentScore.subtract(multiply).doubleValue();
			ljflStaffDetail.setfCurrentScore(doubleValue);
			ljflStaffDetail.setLastchangetime(date);
			int updateByPrimaryKey = ljflStaffDetailService
					.updateByPrimaryKey(ljflStaffDetail);

			
			if (insert == 1 && insert2 == 1 && insert3 == 1
					&& updateByPrimaryKey1 == 1 && updateByPrimaryKey == 1) {

				jsonObject.put("result", "0");
				jsonObject.put("data", "");
				jsonObject.put("msg", "兑换成功");
				return jsonObject.toString();
			} else {

				int deleteByPrimaryKey = LjflAccountModifyService
						.deleteByPrimaryKey(accountmodifyid);
				int deleteByPrimaryKey2 = ljflScoreService
						.deleteByPrimaryKey(id1);
				int deletebyid = LjflPrizeService.deletebyid(id2, tablename3);
				if (deleteByPrimaryKey == 1 && deleteByPrimaryKey2 == 1
						&& deletebyid == 1) {
					jsonObject.put("result", "0");
					jsonObject.put("data", "");
					jsonObject.put("msg", "兑换失败");
					return jsonObject.toString();

				}
			}

		}

		Gson gson = new Gson();
		String json = gson.toJson(data);

		jsonObject.put("result", "0");
		jsonObject.put("data", json);
		jsonObject.put("msg", null);
		return jsonObject.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/queryprize", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String queryprize(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");
		Map<String, Object> data = new HashMap<String, Object>();

		JSONObject parseObject = JSONObject.parseObject(parameter);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		String cdkey = parseObject.getString("cdkey");

		String tablename = cdkey.substring(0, 6);

		LjflPrizeProvideRecord selectBycdKey = LjflPrizeService.selectBycdKey(
				"ljfl_prize_provide_record" + tablename, cdkey);
		if (selectBycdKey == null || selectBycdKey.equals("")) {

			jsonObject.put("result", "0");
			jsonObject.put("data", "");
			jsonObject.put("msg", "兑换码错误");
			return jsonObject.toString();
		} else {
			String prizeid = selectBycdKey.getPrizeid();
			LjflPrize selectByPrimaryKey1 = LjflPrizeService
					.selectByPrimaryKey1(prizeid);
			if (selectByPrimaryKey1 == null || selectByPrimaryKey1.equals("")) {

				jsonObject.put("result", "0");
				jsonObject.put("data", "");
				jsonObject.put("msg", "该商品已兑换结束");
				return jsonObject.toString();
			} else {

				String path = request.getContextPath();
				String basePath = request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort() + path + "/";

				String getfPicture = selectByPrimaryKey1.getfPicture();
				String photourl = basePath + getfPicture;
				selectByPrimaryKey1.setfPicture(photourl);

				Gson gson = new Gson();
				String json = gson.toJson(selectByPrimaryKey1);
				jsonObject.put("result", "0");
				jsonObject.put("data", json);
				jsonObject.put("msg", null);
				return jsonObject.toString();
			}

		}
	}

	@ResponseBody
	@RequestMapping(value = "/exchangeprize", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String exchangeprize(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");
		Map<String, Object> data = new HashMap<String, Object>();

		JSONObject parseObject = JSONObject.parseObject(parameter);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		String cdkey = parseObject.getString("cdkey");
		if(cdkey==null || cdkey.equals("")){
			jsonObject.put("result", "1");
			jsonObject.put("data", "");
			jsonObject.put("msg", "兑换码不能为空");
			return jsonObject.toString();
			
		}
		String staffId = parseObject.getString("staffId");
		if(staffId==null || staffId.equals("")){
			jsonObject.put("result", "1");
			jsonObject.put("data", "");
			jsonObject.put("msg", "居民id不能为空");
			return jsonObject.toString();
			
		}
		String userid = parseObject.getString("userId");
		
		if(userid==null || userid.equals("")){
			jsonObject.put("result", "1");
			jsonObject.put("data", "");
			jsonObject.put("msg", "用户id不能为空");
			return jsonObject.toString();
			
		}
		String tablename = cdkey.substring(0, 6);

		LjflPrizeProvideRecord selectBycdKey = LjflPrizeService.selectBycdKey(
				"ljfl_prize_provide_record" + tablename, cdkey);
		if (selectBycdKey == null || selectBycdKey.equals("")) {

			jsonObject.put("result", "1");
			jsonObject.put("data", "");
			jsonObject.put("msg", "兑换码错误");
			return jsonObject.toString();
		} else {

			String staffdetailid = selectBycdKey.getStaffdetailid();
			staffdetailid.equals("staffId");
			if(staffdetailid.equals(staffId) == true){
				
				Integer providestatus = selectBycdKey.getProvidestatus();
				if (providestatus == 1) {

					jsonObject.put("result", "1");
					jsonObject.put("data", "");
					jsonObject.put("msg", "商品已兑换");
					return jsonObject.toString();
				} else {

					Date date = new Date();
					selectBycdKey.setProvidestatus(1);
					selectBycdKey.setProvidedate(date);
					selectBycdKey.setReceivedate(date);
					selectBycdKey.setLastchangetime(date);
					selectBycdKey.setProvidemanid(userid);
					selectBycdKey.setTablename("ljfl_prize_provide_record" + tablename);
					int updateByPrimaryKey = LjflPrizeService
							.updateByPrimaryKey(selectBycdKey);
					if (updateByPrimaryKey == 1) {
						jsonObject.put("result", "0");
						jsonObject.put("data", "");
						jsonObject.put("msg", "兑换成功");
						return jsonObject.toString();
					} else {

						jsonObject.put("result", "1");
						jsonObject.put("data", "");
						jsonObject.put("msg", "兑换失败");
						return jsonObject.toString();
					}
				}
			}else{
				
				jsonObject.put("result", "1");
				jsonObject.put("data", "");
				jsonObject.put("msg", "居民错误");
				return jsonObject.toString();
			}
		}
	}

	@ResponseBody
	@RequestMapping(value = "/a", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String a(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		String parameter = SpringmvcUtils.getParameter("parameters");
		Map<String, Object> data = new HashMap<String, Object>();

		JSONObject parseObject = JSONObject.parseObject(parameter);
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
	
	
}
