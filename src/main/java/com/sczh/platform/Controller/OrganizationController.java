package com.sczh.platform.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jsqlparser.statement.select.Select;

import org.apache.http.impl.client.AIMDBackoffManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sczh.platform.po.AreaCommunity;
import com.sczh.platform.po.AreaGov;
import com.sczh.platform.po.AreaRelation;
import com.sczh.platform.po.AreaRoleManager;
import com.sczh.platform.po.DepartmentInfo;
import com.sczh.platform.po.MenuInfo;
import com.sczh.platform.po.OMenuinfo;
import com.sczh.platform.po.PersonInfo;
import com.sczh.platform.po.UnitInfo;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.service.AreaService;
import com.sczh.platform.service.DepartmentService;
import com.sczh.platform.service.OMenuInfoService;
import com.sczh.platform.service.PersonService;
import com.sczh.platform.service.UnitService;
import com.sczh.platform.service.UserService;
import com.yunlao.util.MD5andKL;
import com.yunlao.util.MyLong;
import com.yunlao.util.SimpleDateUtil;

@Controller
@RequestMapping({ "/organization" })
public class OrganizationController {

	// private static String unitid = UserController.unitid;

	@Autowired
	private AreaService areaService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private OMenuInfoService omenuInfoService;
	@Autowired
	private PersonService personService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;

	/**
	 * 获取所有组织机构
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/loadAreaTree", produces = { "application/json;charset=UTF-8" })
	public String loadAreaTree() throws IOException {

		List<Object> data = new ArrayList<Object>();// 最大一层

		List<AreaRelation> list = new ArrayList<AreaRelation>();
		AreaRelation arearelation = new AreaRelation();
		arearelation.setIsarea("Y");
		list = areaService.selectAreaRelationAndIsArea(arearelation);

		for (AreaRelation areaRelation : list) {
			String pid = areaRelation.getpId();
			if (pid.equals("0")) {

				String id1 = areaRelation.getId();
				Map<String, Object> menumap1 = new HashMap<String, Object>();// 一级目录
																				// 北京市
				menumap1.put("id", id1);
				menumap1.put("pid", pid);
				menumap1.put("text", areaRelation.getName());
				menumap1.put("state", areaRelation.getState());
				menumap1.put("iconCls", areaRelation.getIconcls());
				menumap1.put("url", areaRelation.getOnclick());
				List<AreaRelation> selectAreaRelations1 = null;

				try {
					selectAreaRelations1 = areaService.selectAreaRelations(id1);

				} catch (Exception e) {
					selectAreaRelations1 = null;
				}

				if (selectAreaRelations1 != null
						|| selectAreaRelations1.equals("")) {

					List<Object> data2 = new ArrayList<Object>();// 包含多个二级目录的list
																	// 海淀区、朝阳区
					for (AreaRelation areaRelation2 : selectAreaRelations1) {
						Map<String, Object> menumap2 = new HashMap<String, Object>();// 二级目录的基本信息
																						// 朝阳区
						String id2 = areaRelation2.getId();
						menumap2.put("id", id2);
						menumap2.put("pid", areaRelation2.getpId());
						menumap2.put("text", areaRelation2.getName());
						menumap2.put("state", areaRelation2.getState());
						menumap2.put("iconCls", areaRelation2.getIconcls());
						menumap2.put("url", areaRelation2.getOnclick());

						List<AreaRelation> selectAreaRelations2 = null;
						try {

							selectAreaRelations2 = areaService
									.selectAreaRelations(id2);
						} catch (Exception e) {
							selectAreaRelations2 = null;
						}

						if (selectAreaRelations2 != null
								|| selectAreaRelations2.equals("")) {

							List<Object> data3 = new ArrayList<Object>();// 包含多个三级目录的list
																			// 劲松街道、亚运村街道
							for (AreaRelation areaRelation3 : selectAreaRelations2) {
								Map<String, Object> menumap3 = new HashMap<String, Object>();// 三级目录信息
																								// 劲松街道

								String id3 = areaRelation3.getId();
								menumap3.put("id", id3);
								menumap3.put("pid", areaRelation3.getpId());
								menumap3.put("text", areaRelation3.getName());
								menumap3.put("state", areaRelation3.getState());
								menumap3.put("iconCls",
										areaRelation3.getIconcls());
								menumap3.put("url", areaRelation3.getOnclick());

								List<AreaRelation> selectAreaRelations3 = null;
								try {

									selectAreaRelations3 = areaService
											.selectAreaRelations(id3);
								} catch (Exception e) {
									selectAreaRelations3 = null;
								}

								if (selectAreaRelations3 != null
										|| selectAreaRelations3.equals("")) {

									List<Object> data4 = new ArrayList<Object>();// 包含多个四级目录的list
																					// 劲松北社区、劲松西社区
									for (AreaRelation areaRelation4 : selectAreaRelations3) {
										Map<String, Object> menumap4 = new HashMap<String, Object>();// 四级目录信息
																										// 劲松北社区
										String id4 = areaRelation4.getId();

										menumap4.put("id", id4);
										menumap4.put("pid",
												areaRelation4.getpId());
										menumap4.put("text",
												areaRelation4.getName());
										menumap4.put("state",
												areaRelation4.getState());
										menumap4.put("iconCls",
												areaRelation4.getIconcls());
										menumap4.put("url",
												areaRelation4.getOnclick());
										List<AreaRelation> selectAreaRelations4 = null;
										try {

											selectAreaRelations4 = areaService
													.selectAreaRelations(id4);
										} catch (Exception e) {
											selectAreaRelations4 = null;
										}

										if (selectAreaRelations4 != null
												|| selectAreaRelations4
														.equals("")) {
											List<Object> data5 = new ArrayList<Object>();// 包含多个五级目录的list
																							// 劲松一区、劲松二区
											for (AreaRelation areaRelation5 : selectAreaRelations4) {
												Map<String, Object> menumap5 = new HashMap<String, Object>();// 五级目录信息
																												// 劲松一区
												String id5 = areaRelation5
														.getId();
												menumap5.put("id", id5);
												menumap5.put("pid",
														areaRelation5.getpId());
												menumap5.put("text",
														areaRelation5.getName());
												menumap5.put("state",
														areaRelation5
																.getState());
												menumap5.put("iconCls",
														areaRelation5
																.getIconcls());

												menumap5.put("url",
														areaRelation5
																.getOnclick());
												data5.add(menumap5);
											}
											menumap4.put("children", data5);
										}
										data4.add(menumap4);
									}

									menumap3.put("children", data4);
								}
								data3.add(menumap3);
							}

							menumap2.put("children", data3);
						}
						data2.add(menumap2);
					}
					menumap1.put("children", data2);
				}
				data.add(menumap1);

			}
		}

		/**
		 * 根据userId查询所在的areaId
		 */
		Gson gson = new Gson();
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		String json = gson.toJson(data);

		jsonObject.put("", json);
		System.out.println(json);
		return json;
	}

	/**
	 * 保存单位信息
	 * 
	 * @param carInfo
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/addunit", method = RequestMethod.POST)
	public String addunit(HttpServletRequest request,@RequestBody String data) throws ParseException {// 添加到数据库表departmentmanage
		String unitcode = "" + (int) ((Math.random() * 9 + 1) * 10000000);
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

		JSONObject parseObject = JSONObject.parseObject(data);
		
		String unitname = parseObject.getString("unitname");
		String areaname = parseObject.getString("areaname");
		String areaid = parseObject.getString("areaid");
		String unitdepict = parseObject.getString("unitdepict");
		String address = parseObject.getString("address");
		String corporate = parseObject.getString("corporate");
		String phone = parseObject.getString("phone");
		String unitadmin = parseObject.getString("unitadmin");
		String setuptimestr = parseObject.getString("setuptime");
		Date setuptime;
		Date date = new Date();
		if(setuptimestr==null||setuptimestr.equals("")){
			setuptime = date;
		}else{
		setuptime = ft.parse(setuptimestr);
		}
		String changetimestr = parseObject.getString("changetime");
		Date changetime;
		if(changetimestr==null||changetimestr.equals("")){
			changetime = date;
		}else{
			
			changetime = ft.parse(changetimestr);
		}
		String state = parseObject.getString("state");
		String unittype = parseObject.getString("unittype");

		UnitInfo selectByPrimaryKey = null;

		try {
			selectByPrimaryKey = unitService.selectByPrimaryKey(unitcode);

			if (selectByPrimaryKey == null || selectByPrimaryKey.equals("")) {

				System.out.println("结果：" + selectByPrimaryKey);

				UnitInfo unitInfo2 = new UnitInfo();
				unitInfo2.setAddress(address);
				unitInfo2.setChangetime(changetime);
				unitInfo2.setCorporate(corporate);
				unitInfo2.setPhone(phone);
				unitInfo2.setSetuptime(setuptime);
				unitInfo2.setState(state);
				unitInfo2.setUnitadmin(unitadmin);
				unitInfo2.setUnitcode(unitcode);
				unitInfo2.setUnitdepict(unitdepict);
				unitInfo2.setUnitname(unitname);
				unitInfo2.setUnittype(unittype);
				unitInfo2.setAreaid(areaid);
				unitInfo2.setAreaname(areaname);
				System.out.println(unitInfo2);
				int num = unitService.insert(unitInfo2);

				System.out.println("num===" + num);
				OMenuinfo oMenuinfo = new OMenuinfo();
				oMenuinfo.setName(unitname);
				oMenuinfo.setId(unitcode);
				oMenuinfo.setPID("0");
				int insert = omenuInfoService.insert(oMenuinfo);
				if (num == 1 && insert == 1) {

					jsonObject.put("statusCode", 200);
					jsonObject.put("tite", " 单位信息录入完成");
					jsonObject.put("message", " 单位信息录入完成");
					return jsonObject.toString();
				} else {
					jsonObject.put("statusCode", 0);
					jsonObject.put("tite", " 单位信息录入失败");
					jsonObject.put("message", " 单位信息录入失败");
					return jsonObject.toString();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception

			jsonObject.put("statusCode", 0);
			jsonObject.put("tite", " 注册失败，请重新注册");
			jsonObject.put("message", " 注册失败，请重新注册");
			return jsonObject.toString();
		}
		jsonObject.put("statusCode", 0);
		jsonObject.put("tite", " 注册失败，请重新注册");
		jsonObject.put("message", " 注册失败，请重新注册");
		return jsonObject.toString();
	}

	/**
	 * 获取省市
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getCity", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getCity(HttpServletRequest request) throws IOException {

		request.setCharacterEncoding("UTF-8");

		List<AreaGov> selectaraegovinfos = areaService
				.selectaraegovinfos("city");
		List<Object> city = new ArrayList<Object>();
		for (AreaGov areaGov : selectaraegovinfos) {
			Map<String, Object> map = new HashMap<String, Object>();
			String id = areaGov.getId();
			AreaRelation selectById = areaService.selectById(id);
			map.put("arearelationid", selectById.getArearelationid());
			map.put("text", selectById.getName());
			map.put("id", selectById.getId());
			map.put("pid", selectById.getpId());
			map.put("state", selectById.getState());
			map.put("iconCls", selectById.getIconcls());
			map.put("levelId", "4");

			city.add(map);
		}

		Gson gson = new Gson();
		String json = gson.toJson(city);
		System.out.println(json);
		return json;
	}

	/**
	 * 获取县区
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getCounty", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getArea(HttpServletRequest request) throws IOException {

		List<AreaGov> selectaraegovinfos = areaService
				.selectaraegovinfos("county");
		List<Object> county = new ArrayList<Object>();
		for (AreaGov areaGov : selectaraegovinfos) {
			Map<String, Object> map = new HashMap<String, Object>();
			String id = areaGov.getId();
			AreaRelation selectById = areaService.selectById(id);
			map.put("arearelationid", selectById.getArearelationid());
			map.put("id", selectById.getId());
			map.put("text", selectById.getName());
			map.put("pid", selectById.getpId());
			map.put("state", selectById.getState());
			map.put("iconCls", selectById.getIconcls());
			map.put("levelId", "5");
			county.add(map);
		}

		Gson gson = new Gson();
		String json = gson.toJson(county);
		System.out.println(json);
		return json;
	}

	/*
	 * 获取街道
	 */
	@ResponseBody
	@RequestMapping(value = "/getStreet", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getStreet(HttpServletRequest request) throws IOException {

		request.setCharacterEncoding("UTF-8");

		List<AreaGov> selectaraegovinfos = areaService
				.selectaraegovinfos("street");
		List<Object> street = new ArrayList<Object>();
		for (AreaGov areaGov : selectaraegovinfos) {
			Map<String, Object> map = new HashMap<String, Object>();
			String id = areaGov.getId();
			AreaRelation selectById = areaService.selectById(id);
			map.put("arearelationid", selectById.getArearelationid());
			map.put("id", selectById.getId());
			map.put("text", selectById.getName());
			map.put("pid", selectById.getpId());
			map.put("state", selectById.getState());
			map.put("iconCls", selectById.getIconcls());
			map.put("levelId", "6");
			street.add(map);
		}

		Gson gson = new Gson();
		String json = gson.toJson(street);
		System.out.println(json);
		return json;
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
	public String getCommunity(HttpServletRequest request) throws IOException {

		request.setCharacterEncoding("UTF-8");

		List<AreaGov> selectaraegovinfos = areaService
				.selectaraegovinfos("community");
		List<Object> community = new ArrayList<Object>();
		for (AreaGov areaGov : selectaraegovinfos) {
			Map<String, Object> map = new HashMap<String, Object>();
			String id = areaGov.getId();
			AreaRelation selectById = areaService.selectById(id);
			map.put("arearelationid", selectById.getArearelationid());
			map.put("id", selectById.getId());
			map.put("text", selectById.getName());
			map.put("pid", selectById.getpId());
			map.put("state", selectById.getState());
			map.put("iconCls", selectById.getIconcls());
			map.put("levelId", "7");
			community.add(map);
		}

		Gson gson = new Gson();
		String json = gson.toJson(community);
		System.out.println(json);
		return json;
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
	public String getNest(HttpServletRequest request) throws IOException {

		request.setCharacterEncoding("UTF-8");

		List<AreaCommunity> selectareaCommunities = areaService
				.selectareaCommunities();

		List<Object> community = new ArrayList<Object>();
		for (AreaCommunity areaCommunity : selectareaCommunities) {

			Map<String, Object> map = new HashMap<String, Object>();
			String id = areaCommunity.getId();
			AreaRelation selectById = areaService.selectById(id);

			map.put("arearelationid", selectById.getArearelationid());
			map.put("id", selectById.getId());
			map.put("text", selectById.getName());
			map.put("pid", selectById.getpId());
			map.put("state", selectById.getState());
			map.put("iconCls", selectById.getIconcls());
			map.put("levelId", "7");
			community.add(map);
		}

		Gson gson = new Gson();
		String json = gson.toJson(community);
		System.out.println(json);
		return json;
	}

	/**
	 * 获取区域下公司
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getAreaCompany", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getAreaCompany(HttpServletRequest request) throws IOException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");

		System.out.println(id);
		List<UnitInfo> selectByAreaId;
		if (id == null || id.equals("")) {
			selectByAreaId = unitService.selectUnitInfoList();

		} else {

			/*
			 * if(areaId==null || areaId.equals("")){ areaId="."; }
			 */
			selectByAreaId = unitService.selectByArea(id);
		}
		System.out.println(selectByAreaId.toString());
		Gson gson = new Gson();
		String json = gson.toJson(selectByAreaId);

		jsonObject.put("rows", json);
		jsonObject.put("total", selectByAreaId.size());
		System.out.println(jsonObject.toString());
		return jsonObject.toString();

	}

	/**
	 * 获取所有组织机构
	 * 
	 * @param jsondata
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getorganization", produces = { "application/json;charset=UTF-8" })
	public String getorganization(HttpSession session) throws Exception {
		Gson gson = new Gson();
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		List<Object> data = new ArrayList<Object>();// 最大一层 首创、首创智慧环卫

		Object object = session.getAttribute("unitid");


		if (object == null || object.equals("")) {
			List<OMenuinfo> selectOMenuInfoList = omenuInfoService
					.selectOMenuInfoList();
			for (OMenuinfo oMenuinfo : selectOMenuInfoList) {
				String pid = oMenuinfo.getPID();
				if (pid.equals("0")) {
					String id = oMenuinfo.getId();
					Map<String, Object> menumap = new HashMap<String, Object>();// 一级目录
					
					UnitInfo selectByPrimaryKey = unitService.selectByPrimaryKey(id);
					
					// 首创
					menumap.put("id", id);
					menumap.put("pid", id);
					menumap.put("text", oMenuinfo.getName());
					menumap.put("unitname", selectByPrimaryKey.getUnitname());
					menumap.put("states", selectByPrimaryKey.getState());
					menumap.put("leader", selectByPrimaryKey.getUnitadmin());
					menumap.put("state", "closed");
					
					
					data.add(menumap);
					}
				}
		} else {
			String unitid = object.toString();

			OMenuinfo selectomenuinfo = omenuInfoService
					.selectByPrimaryKey(unitid);
			String pid = selectomenuinfo.getPID();
				if (pid.equals("0")) {
					String id = selectomenuinfo.getId();
					Map<String, Object> menumap = new HashMap<String, Object>();// 一级目录
					UnitInfo selectByPrimaryKey = unitService.selectByPrimaryKey(id);															
					menumap.put("id", id);
					menumap.put("pid", id);
					menumap.put("text", selectomenuinfo.getName());
					menumap.put("unitname", selectByPrimaryKey.getUnitname());
					menumap.put("state", selectByPrimaryKey.getState());
					menumap.put("leader", selectByPrimaryKey.getUnitadmin());
					data.add(menumap);

				}
		
		}

		String json = gson.toJson(data);

		jsonObject.put("", json);
		System.out.println(json);
		return json;

	}

	/**
	 * 获取所有部门信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getdepartmentInfo", produces = { "application/json;charset=UTF-8" })
	public String getdepartmentInfo(HttpServletRequest request,
			HttpSession session) {

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		System.out.println(id);
		List<DepartmentInfo> selectDepartmentinfoList = null;

		Object object = session.getAttribute("unitid");
		String unitid = object.toString();

		if (id == null || id.equals("")) {

			if (unitid == null || unitid.equals("")) {
				selectDepartmentinfoList = departmentService
						.selectDepartmentinfoList();
			} else {
				selectDepartmentinfoList = departmentService
						.selectByUnitCode(unitid);
			}
		} else {

			selectDepartmentinfoList = departmentService.selectByUnitCode(id);
		}

		for (DepartmentInfo departmentInfo : selectDepartmentinfoList) {
			String unitcode = departmentInfo.getUnitcode();

			OMenuinfo selectByPrimaryKey2 = omenuInfoService
					.selectByPrimaryKey(unitcode);
			String pid = selectByPrimaryKey2.getPID();
			String name = null;
			if (pid == "0" || pid.equals("0")) {
				name = selectByPrimaryKey2.getName();
			} else {
				OMenuinfo selectByPrimaryKey = omenuInfoService
						.selectByPrimaryKey(pid);
				String pid2 = selectByPrimaryKey.getPID();
				if (pid2 == "0" || pid2.equals("0")) {

					name = selectByPrimaryKey.getName();
				} else {

					// 循环

				}
			}
			departmentInfo.setUnitcode(name);
		}

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		Gson gson = new Gson();
		String json = gson.toJson(selectDepartmentinfoList);

		jsonObject.put("rows", json);
		jsonObject.put("total", selectDepartmentinfoList.size());
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	/**
	 * 获取所有人员信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getallPersonInfo", produces = { "application/json;charset=UTF-8" })
	public String getallPersonInfo(HttpServletRequest request,
			HttpSession session) {

		Object object = session.getAttribute("unitid");

		String unitid = object.toString();

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		System.out.println(id);
		OMenuinfo selectByPrimaryKey = null;
		List<PersonInfo> personInfoList = null;
		if (id == null || id.equals("")) {

			if (unitid == null || unitid.equals("")) {
				personInfoList = personService.selectPersonInfoList();
			} else {
				selectByPrimaryKey = omenuInfoService
						.selectByPrimaryKey(unitid);
			}
		} else {
			selectByPrimaryKey = omenuInfoService.selectByPrimaryKey(id);
		}
		if (selectByPrimaryKey != null) {
			String pid = selectByPrimaryKey.getPID();
			if (pid.equals("0")) {

				String unitname = selectByPrimaryKey.getName();
				personInfoList = personService.selectByUnit(unitname);
			} else {

				OMenuinfo selectByPrimaryKey2 = omenuInfoService
						.selectByPrimaryKey(pid);
				String unitname = selectByPrimaryKey2.getName();
				String departmentname = selectByPrimaryKey.getName();
				personInfoList = personService.selectByUnitandDepartment(
						unitname, departmentname);
			}
		}
		
		for (PersonInfo personInfo : personInfoList) {
			
			String role = personInfo.getRole();
			if(role==null||role.equals("")){
				
			}else{
				AreaRoleManager selectAreaRole = areaService.selectAreaRole(role);
				String rolename = selectAreaRole.getRolename();
				if (rolename!=null) {
					personInfo.setRole(rolename);
				}
				
			}
		}
		
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		Gson gson = new Gson();
		String json = gson.toJson(personInfoList);

		jsonObject.put("rows", json);
		jsonObject.put("total", personInfoList.size());
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	/**
	 * 获取部门下的人员信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getPersonInfo", produces = { "application/json;charset=UTF-8" })
	public String getPersonInfo(HttpServletRequest request) {

		String id = request.getParameter("pid") == null ? "" : request
				.getParameter("pid");

		System.out.println("pid:" + id);
		List<Object> data = new ArrayList<Object>();
		List<OMenuinfo> selectByPid = omenuInfoService.selectByPid(id);
		for (OMenuinfo oMenuinfo : selectByPid) {
			String pid = oMenuinfo.getPID();
			
				String id1 = oMenuinfo.getId();
				Map<String, Object> menumap = new HashMap<String, Object>();// 一级目录
				
				DepartmentInfo selectByPrimaryKey = departmentService.selectByDepartmentCode(id1);
				
				String unitcode = selectByPrimaryKey.getUnitcode();
				UnitInfo selectByPrimaryKey2 = unitService.selectByPrimaryKey(unitcode);
				
				
				// 首创
				menumap.put("id", id1);
				menumap.put("pid", pid);
				menumap.put("text", oMenuinfo.getName());
				menumap.put("unitname", selectByPrimaryKey2.getUnitname());
				menumap.put("state", selectByPrimaryKey.getState());
				menumap.put("leader", selectByPrimaryKey.getDepartmentleader());
				data.add(menumap);
				
			}
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		Gson gson = new Gson();
		String json = gson.toJson(data);

		jsonObject.put("", json);
		System.out.println(json);
		return json;
	}

	/**
	 * 获取全部单位
	 */
	@ResponseBody
	@RequestMapping(value = "/getunit", produces = { "application/json;charset=UTF-8" })
	public String getunit(HttpServletRequest request) {

		List<UnitInfo> selectUnitInfoList = unitService.selectUnitInfoList();
		List<Object> unit = new ArrayList<Object>();
		for (UnitInfo unitInfo : selectUnitInfoList) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", unitInfo.getUnitcode());
			map.put("text", unitInfo.getUnitname());
			unit.add(map);
		}
		Gson gson = new Gson();

		String json = gson.toJson(unit);
		System.out.println(json);
		return json;
	}

	/**
	 * 保存部门信息
	 * 
	 * @param carInfo
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/adddepartment", method = RequestMethod.POST)
	public String adddepartment(HttpServletRequest request,
			@RequestBody String departmentinfo) throws ParseException {// 添加到数据库表departmentmanage
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String unitcode = request.getParameter("unitcode") == null ? ""
				: request.getParameter("unitcode");
		String departmentname = request.getParameter("departmentname") == null ? ""
				: request.getParameter("departmentname");
		String departmentdescribe = request.getParameter("departmentdescribe") == null ? ""
				: request.getParameter("departmentdescribe");
		String departmentleader = request.getParameter("departmentleader") == null ? ""
				: request.getParameter("departmentleader");
		String state = request.getParameter("state") == null ? "" : request
				.getParameter("state");
		String setuptimestr = request.getParameter("setuptime") == null ? ""
				: request.getParameter("setuptime");
		Date setuptime = ft.parse(setuptimestr);
		String changetimestr = request.getParameter("changetime") == null ? ""
				: request.getParameter("changetime");
		Date changetime = ft.parse(changetimestr);

		String i = "" + (int) ((Math.random() * 9 + 1) * 10);
		System.out.println("department==" + departmentinfo.toString());
		// String id= MyLong.getRand().toString();
		// departmentManage.setId(id);
		DepartmentInfo departmentInfo2 = new DepartmentInfo();
		departmentInfo2.setChangetime(changetime);
		departmentInfo2.setDepartmentcode(i);
		departmentInfo2.setDepartmentdescribe(departmentdescribe);
		departmentInfo2.setDepartmentleader(departmentleader);
		departmentInfo2.setDepartmentname(departmentname);
		departmentInfo2.setSetuptime(setuptime);
		departmentInfo2.setState(state);
		departmentInfo2.setUnitcode(unitcode);
		departmentInfo2.setPid(Integer.parseInt(unitcode));
		int num = departmentService.insert(departmentInfo2);

		System.out.println("num==" + num);

		OMenuinfo oMenuinfo = new OMenuinfo();
		oMenuinfo.setName(departmentname);
		System.out.println("name==" + departmentname);
		oMenuinfo.setId(i);
		System.out.println(i);
		oMenuinfo.setPID(unitcode);
		oMenuinfo.setOnclick("getPerson()");
		System.out.println("omenuinfo==" + oMenuinfo);
		int insert = omenuInfoService.insert(oMenuinfo);
		if (num == 1 && insert == 1) {

			jsonObject.put("statusCode", 200);
			jsonObject.put("tite", "部门信息录入完成");
			jsonObject.put("message", "部门信息录入完成");
			return jsonObject.toString();
		} else {

			jsonObject.put("statusCode", 0);
			jsonObject.put("tite", " 注册失败，请重新注册");
			jsonObject.put("message", " 注册失败，请重新注册");
			return jsonObject.toString();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/getrole", method = RequestMethod.POST)
	public String getrole() {

		List<AreaRoleManager> selectAreaRoleManager = areaService
				.selectAreaRoleManager();
		List<Object> arearole = new ArrayList<Object>();
		for (AreaRoleManager areaRoleManager : selectAreaRoleManager) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", areaRoleManager.getId());
			map.put("text", areaRoleManager.getRolename());
			arearole.add(map);
		}
		Gson gson = new Gson();

		String json = gson.toJson(arearole);
		System.out.println(json);
		return json;
	}

	/**
	 * 保存人员信息
	 * 
	 * @param carInfo
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/addperson", method = RequestMethod.POST)
	public String addperson(HttpServletRequest request,
			@RequestBody String departmentinfo) throws ParseException {// 添加到数据库表departmentmanage
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String areaname = request.getParameter("areaname") == null ? ""
				: request.getParameter("areaname");
		String areaid = request.getParameter("areaid") == null ? "" : request
				.getParameter("areaid");

		String name = request.getParameter("name") == null ? "" : request
				.getParameter("name");
		String role = request.getParameter("role") == null ? "" : request
				.getParameter("role");
		String unitname = request.getParameter("unitname") == null ? ""
				: request.getParameter("unitname");
		String unitid = request.getParameter("unitid") == null ? "" : request
				.getParameter("unitid");
		String username = request.getParameter("username") == null ? ""
				: request.getParameter("username");
		String password = request.getParameter("password") == null ? ""
				: request.getParameter("password");
		String setuptimestr = request.getParameter("setuptime") == null ? ""
				: request.getParameter("setuptime");
		Date setuptime = ft.parse(setuptimestr);
		String changetimestr = request.getParameter("changetime") == null ? ""
				: request.getParameter("changetime");
		Date changetime = ft.parse(changetimestr);

		OMenuinfo selectByPrimaryKey = omenuInfoService
				.selectByPrimaryKey(unitid);

		OMenuinfo selectByPrimaryKey2 = omenuInfoService
				.selectByPrimaryKey(selectByPrimaryKey.getPID());

		System.out.println("department==" + departmentinfo.toString());
		long currentTime = System.currentTimeMillis();
		String valueOf = String.valueOf(currentTime);
		PersonInfo personInfo2 = new PersonInfo();
		personInfo2.setChangetime(changetime);
		personInfo2.setDepartmentname(unitname);
		personInfo2.setName(name);
		personInfo2.setPersoncode(valueOf);
		personInfo2.setPersonid(valueOf);
		personInfo2.setRole(role);
		personInfo2.setSetuptime(setuptime);
		// personInfo2.setState(a);
		personInfo2.setUnitname(selectByPrimaryKey2.getName());
		personInfo2.setAreaid(areaid);
		personInfo2.setAreaname(areaname);

		UserInfo user = new UserInfo();
		String id = MyLong.getRandStr(11);
		personInfo2.setUserid(id);
		if (password == null || password.isEmpty()) {
			password = "123456";
		} else {

		}

		password = MD5andKL.MD5(password);

		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		personInfo2.setUsername(username);
		personInfo2.setPassword(password);
		if (unitid != null) {
			user.setUnitid(selectByPrimaryKey2.getId());// 用作存储unitID
		} else {
			user.setUnitid("0");// 0代表最高单位
		}// unitName
		if (unitname != null) {
			user.setUnitname(unitname);// 用作存储setUnitname
		} else {
			user.setUnitname("超级管理员");// 0代表最高单位
		}// unitName

		int insert = userService.insert(user);
		// }

		int num = personService.insert(personInfo2);

		if (num == 1 && insert == 1) {

			jsonObject.put("statusCode", 200);
			jsonObject.put("tite", "人员信息录入完成");
			jsonObject.put("message", "人员信息录入完成");
			return jsonObject.toString();
		} else {

			jsonObject.put("statusCode", 0);
			jsonObject.put("tite", " 注册失败，请重新注册");
			jsonObject.put("message", " 注册失败，请重新注册");
			return jsonObject.toString();
		}

	}

	/**
	 * 获取下级区域
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getAreaByPid", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getAreaByPid(HttpServletRequest request) throws IOException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");

		System.out.println(id);

		if (id == null || id.equals("")) {

			id = "EFFBABADACE";
		}

		/*
		 * if(areaId==null || areaId.equals("")){ areaId="."; }
		 */
		List<AreaRelation> selectAreaRelations = areaService
				.selectAreaRelations(id);
		List<AreaGov> list = new ArrayList<AreaGov>();
		for (AreaRelation areaRelation : selectAreaRelations) {
			String id2 = areaRelation.getId();
			AreaGov selectAreaGovById = areaService.selectAreaGovById(id2);
			list.add(selectAreaGovById);
		}
		System.out.println(list.toString());
		Gson gson = new Gson();
		String json = gson.toJson(list);

		jsonObject.put("rows", json);
		jsonObject.put("total", list.size());
		System.out.println(jsonObject.toString());
		return jsonObject.toString();

	}

	/**
	 * 获取下级蜂巢
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getHive", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getHive(HttpServletRequest request) throws IOException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		System.out.println(id);

		List<AreaCommunity> list = new ArrayList<AreaCommunity>();

		if (id == null || id.equals("")) {
			list = areaService.selectareaCommunities();
			for (AreaCommunity areaCommunity : list) {

				String areaid = areaCommunity.getAreaid();
				AreaRelation selectById = areaService.selectById(areaid);
				String name = selectById.getName();
				areaCommunity.setAreaid(name);
			}

			System.out.println(list.toString());
			Gson gson = new Gson();
			String json = gson.toJson(list);

			jsonObject.put("rows", json);
			jsonObject.put("total", list.size());
			System.out.println(jsonObject.toString());
			return jsonObject.toString();
		} else {

			AreaGov selectAreaGovById = areaService.selectAreaGovById(id);
			if (selectAreaGovById.getAreadescription() == "community"
					|| selectAreaGovById.getAreadescription().equals(
							"community")) {

				AreaRelation selectById = areaService.selectById(id);

				String name = selectById.getName();

				/*
				 * if(areaId==null || areaId.equals("")){ areaId="."; }
				 */
				List<AreaRelation> selectAreaRelations = areaService
						.selectAreaRelations(id);
				for (AreaRelation areaRelation : selectAreaRelations) {
					String id2 = areaRelation.getId();
					AreaCommunity selectByPrimaryKey = areaService
							.selectByPrimaryKey(id2);
					selectByPrimaryKey.setAreaid(name);
					list.add(selectByPrimaryKey);
				}

				System.out.println(list.toString());
				Gson gson = new Gson();
				String json = gson.toJson(list);

				jsonObject.put("rows", json);
				jsonObject.put("total", list.size());
				System.out.println(jsonObject.toString());
				return jsonObject.toString();

			} else {

				jsonObject.put("rows", "");
				jsonObject.put("total", 0);
				System.out.println(jsonObject.toString());
				return jsonObject.toString();
			}
		}

	}

	/**
	 * t添加区域代码
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("addAreaGovInfo")
	public String addAreaGovInfo(HttpServletRequest request)
			throws ParseException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String areacode = request.getParameter("areaCode") == null ? ""
				: request.getParameter("areaCode");
		String areaname = request.getParameter("areaname") == null ? ""
				: request.getParameter("areaname");
		String areaDescription = request.getParameter("areaDescription") == null ? ""
				: request.getParameter("areaDescription");
		if(areaDescription=="province"||areaDescription.equals("province")){
			
		}else if(areaDescription == "city" || areaDescription.equals("city")){
			
		}else if(areaDescription == "county" || areaDescription.equals("county")){			
		
		}else if(areaDescription == "street" || areaDescription.equals("street")){			
			 
		}else if(areaDescription == "community" || areaDescription.equals("community")){			
			 
		}else{
			
			jsonObject.put("statusCode", 0);
			jsonObject.put("tite", "消息提示");
			jsonObject.put("message", "请输入行政级别的指定内容");
			return jsonObject.toString();
		}

		String areaAddress = request.getParameter("areaAddress") == null ? ""
				: request.getParameter("areaAddress");
		String latitude = request.getParameter("latitude") == null ? ""
				: request.getParameter("latitude");
		String lontitude = request.getParameter("lontitude") == null ? ""
				: request.getParameter("lontitude");
		String highLevelId = request.getParameter("highLevelId") == null ? ""
				: request.getParameter("highLevelId");
		String highLevel = request.getParameter("highLevel") == null ? ""
				: request.getParameter("highLevel");
		String areaTypestr = request.getParameter("areaType") == null ? ""
				: request.getParameter("areaType");
		if(areaTypestr=="1" || areaTypestr.equals("1")){
			
		}else  if(areaTypestr == "2" || areaTypestr.equals("2")){
			
		}else{
			
			jsonObject.put("statusCode", 0);
			jsonObject.put("tite", "消息提示");
			jsonObject.put("message", "请输入区域性质的指定内容");
			return jsonObject.toString();
		}
		String areaType;
		if (areaTypestr == "1" || areaTypestr.equals("1")) {
			areaType = "city";
		} else {

			areaType = "country";
		}
		String setuptimestr = request.getParameter("setuptime") == null ? ""
				: request.getParameter("setuptime");
		Date setuptime = ft.parse(setuptimestr);

		String areaId = MD5andKL.KL(MyLong.getRandStr(13));
		AreaGov areaGov = new AreaGov();
		areaId = areaId.replace("@", "A");
		areaGov.setId(areaId);
		areaGov.setAreacode(areacode);
		areaGov.setAreaname(areaname);
		areaGov.setAreadescription(areaDescription);
		areaGov.setAreaaddress(areaAddress);
		areaGov.setAreatype(areaType);
		areaGov.setSetuptime(setuptime);
		areaGov.setLatitude(latitude);
		areaGov.setLontitude(lontitude);

		int saveAreaGov = areaService.saveAreaGov(areaGov);

		String areaRelationId = MD5andKL.KL(MyLong.getRandStr(15));
		AreaRelation areaRelation = new AreaRelation();
		areaRelationId = areaRelationId.replace("@", "A");
		areaRelation.setArearelationid(areaRelationId);
		areaRelation.setId(areaId);
		areaRelation.setOnclick(areaId);
		areaRelation.setName(areaname);
		if (highLevel == null || highLevel.equals("")) {
			areaRelation.setpId("0");
		} else {

			areaRelation.setpId(highLevelId);
		}
		areaRelation.setIsarea("Y");
		areaRelation.setState("closed");
		areaRelation.setIconcls("fa fa-folder");
		int areaRelation2 = areaService.areaRelation(areaRelation);

		if (saveAreaGov == 1 || areaRelation2 == 1) {

			jsonObject.put("statusCode", 200);
			jsonObject.put("tite", "区域信息录入完成");
			jsonObject.put("message", "区域信息录入完成");
			return jsonObject.toString();
		} else {

			jsonObject.put("statusCode", 0);
			jsonObject.put("tite", "区域信息录入失败");
			jsonObject.put("message", "区域信息录入失败");
			return jsonObject.toString();
		}

	}

	/**
	 * t添加小区代码
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("addAreaCommunityInfo")
	public String addAreaCommunityInfo(HttpServletRequest request)
			throws ParseException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String communitycode = request.getParameter("communitycode") == null ? ""
				: request.getParameter("communitycode");
		String communityname = request.getParameter("communityname") == null ? ""
				: request.getParameter("communityname");
		String communityleader = request.getParameter("communityleader") == null ? ""
				: request.getParameter("communityleader");
		String highLevelId = request.getParameter("highLevelId") == null ? ""
				: request.getParameter("highLevelId");
		String highLevel = request.getParameter("highLevel") == null ? ""
				: request.getParameter("highLevel");
		String setuptimestr = request.getParameter("setuptime") == null ? ""
				: request.getParameter("setuptime");
		Date setuptime = ft.parse(setuptimestr);

		String communityId = MD5andKL.MD5(MyLong.getRandStr(13));
		AreaCommunity areaCommunity = new AreaCommunity();
		areaCommunity.setId(communityId);
		areaCommunity.setCommunitycode(communitycode);
		areaCommunity.setCommunityname(communityname);
		areaCommunity.setCommunityleader(communityleader);
		areaCommunity.setAreaid(highLevelId);
		areaCommunity.setSetuptime(setuptime);

		int saveAreaCommunity = areaService.saveAreaCommunity(areaCommunity);

		String areaRelationId = MD5andKL.MD5(MyLong.getRandStr(15));
		AreaRelation areaRelation = new AreaRelation();
		areaRelation.setArearelationid(areaRelationId);
		areaRelation.setId(communityId);
		areaRelation.setName(communityname);
		areaRelation.setpId(highLevelId);
		areaRelation.setOnclick(communityId);
		areaRelation.setIsarea("Y");
		areaRelation.setState("open");
		int areaRelation2 = areaService.areaRelation(areaRelation);

		if (saveAreaCommunity == 1 || areaRelation2 == 1) {

			jsonObject.put("statusCode", 200);
			jsonObject.put("tite", "小区信息录入完成");
			jsonObject.put("message", "小区信息录入完成");
			return jsonObject.toString();
		} else {

			jsonObject.put("statusCode", 0);
			jsonObject.put("tite", "小区信息录入失败");
			jsonObject.put("message", "小区信息录入失败");
			return jsonObject.toString();
		}

	}

	/**
	 * 获取所有角色配置信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getAreaRoleManager", produces = { "application/json;charset=UTF-8" })
	public String getAreaRoleManager(HttpServletRequest request) {

		List<AreaRoleManager> selectAreaRoleManager = areaService
				.selectAreaRoleManager();

		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		Gson gson = new Gson();
		String json = gson.toJson(selectAreaRoleManager);

		jsonObject.put("rows", json);
		jsonObject.put("total", selectAreaRoleManager.size());
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	/**
	 * 获取要编辑的人员的信息
	 */
	@ResponseBody
	@RequestMapping(value = "/geteditpersoninfo", produces = { "application/json;charset=UTF-8" })
	public String geteditpersoninfo(HttpServletRequest request) {

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");

		PersonInfo selectByPrimaryKey = personService
				.selectByPrimaryKey(Integer.valueOf(id));
		String userid = selectByPrimaryKey.getUserid();

		UserInfo selectByPrimaryKey2 = userService.selectByPrimaryKey(userid);

		Gson gson = new Gson();
		String json = gson.toJson(selectByPrimaryKey);

		System.out.println(json.toString());
		return json.toString();
	}

	/**
	 * 保存编辑的人员的信息
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/savepersoninfo", produces = { "application/json;charset=UTF-8" })
	public String savepersoninfo(HttpServletRequest request)
			throws ParseException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		String name = request.getParameter("name") == null ? "" : request
				.getParameter("name");
		String areaname = request.getParameter("areaname") == null ? ""
				: request.getParameter("areaname");
		String role = request.getParameter("role") == null ? "" : request
				.getParameter("role");
		String username = request.getParameter("username") == null ? ""
				: request.getParameter("username");
		String password = request.getParameter("password") == null ? ""
				: request.getParameter("password");
		String changetimestr = request.getParameter("changetime") == null ? ""
				: request.getParameter("changetime");
		Date changetime = ft.parse(changetimestr);
		password = MD5andKL.MD5(password);
		PersonInfo selectByPrimaryKey = personService
				.selectByPrimaryKey(Integer.valueOf(id));
		selectByPrimaryKey.setName(name);
		selectByPrimaryKey.setAreaname(areaname);
		selectByPrimaryKey.setRole(role);
		selectByPrimaryKey.setUsername(username);
		selectByPrimaryKey.setPassword(password);
		selectByPrimaryKey.setChangetime(changetime);
		String userid = selectByPrimaryKey.getUserid();

		int updateByPrimaryKey = personService
				.updateByPrimaryKey(selectByPrimaryKey);

		UserInfo selectByPrimaryKey2 = userService.selectByPrimaryKey(userid);
		selectByPrimaryKey2.setUsername(username);
		selectByPrimaryKey2.setPassword(password);

		int updateByPrimaryKey2 = userService
				.updateByPrimaryKey(selectByPrimaryKey2);

		if (updateByPrimaryKey == 1 && updateByPrimaryKey2 == 1) {

			jsonObject.put("statusCode", 200);
			jsonObject.put("title", "更新成功");
			jsonObject.put("message", "更新成功");
			return jsonObject.toString();
		} else {

			jsonObject.put("statusCode", 0);
			jsonObject.put("title", "更新失败");
			jsonObject.put("message", "更新失败");
			return jsonObject.toString();
		}

	}

	/**
	 * 删除人员
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteperson", produces = { "application/json;charset=UTF-8" })
	public String deleteperson(HttpServletRequest request)
			throws ParseException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");

		String[] split = id.split(",");

		int deleteByPrimaryKey = 0;
		int deleteByPrimaryKey2 = 0;

		for (int i = 0; i < split.length; i++) {
			String replace = split[i].replace("'", "");

			PersonInfo selectByPrimaryKey = personService
					.selectByPrimaryKey(Integer.valueOf(replace));
			String userid = selectByPrimaryKey.getUserid();

			deleteByPrimaryKey = userService.deleteByPrimaryKey(userid);
			deleteByPrimaryKey2 = personService.deleteByPrimaryKey(Integer
					.valueOf(replace));
			if (deleteByPrimaryKey != 1 || deleteByPrimaryKey2 != 1) {

				jsonObject.put("statusCode", 0);
				jsonObject.put("title", "删除失败");
				jsonObject.put("message", "删除失败");
				return jsonObject.toString();
			}
		}
		if (deleteByPrimaryKey == 1 && deleteByPrimaryKey2 == 1) {
			jsonObject.put("statusCode", 200);
			jsonObject.put("title", "删除成功");
			jsonObject.put("message", "删除成功");
			return jsonObject.toString();

		} else {
			jsonObject.put("statusCode", 0);
			jsonObject.put("title", "删除失败");
			jsonObject.put("message", "删除失败");
			return jsonObject.toString();
		}
	}

	/**
	 * 新增人员角色
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/addpersonrole", produces = { "application/json;charset=UTF-8" })
	public String addpersonrole(HttpServletRequest request, HttpSession session)
			throws ParseException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		String rolename = request.getParameter("rolename") == null ? ""
				: request.getParameter("rolename");
		String remark = request.getParameter("remark") == null ? "" : request
				.getParameter("remark");
		String createtime = request.getParameter("createtime") == null ? ""
				: request.getParameter("createtime");

		AreaRoleManager areaRoleManager = new AreaRoleManager();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		areaRoleManager.setId(id);
		areaRoleManager.setRolename(rolename);
		areaRoleManager.setRemark(remark);
		areaRoleManager.setCreatetime(createtime);
		areaRoleManager.setUserid(session.getAttribute("userid").toString());
		areaRoleManager.setUnitid(session.getAttribute("unitid").toString());
		int insert = areaService.insert(areaRoleManager);

		if (insert == 1) {

			jsonObject.put("statusCode", 200);
			jsonObject.put("title", "人员角色配置成功");
			jsonObject.put("message", "人员角色配置成功");
			return jsonObject.toString();
		} else {
			jsonObject.put("statusCode", 0);
			jsonObject.put("title", "人员角色配置失败");
			jsonObject.put("message", "人员角色配置失败");
			return jsonObject.toString();
		}
	}

	/**
	 * 获取要编辑的人员的信息
	 */
	@ResponseBody
	@RequestMapping(value = "/geteditpersonroleinfo", produces = { "application/json;charset=UTF-8" })
	public String geteditpersonroleinfo(HttpServletRequest request) {

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");

		AreaRoleManager selectAreaRole = areaService.selectAreaRole(id);

		Gson gson = new Gson();
		String json = gson.toJson(selectAreaRole);

		System.out.println(json.toString());
		return json.toString();
	}

	/**
	 * 保存编辑的人员角色的信息
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/savepersonroleinfo", produces = { "application/json;charset=UTF-8" })
	public String savepersonroleinfo(HttpServletRequest request)
			throws ParseException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		String rolename = request.getParameter("rolename") == null ? ""
				: request.getParameter("rolename");
		String remark = request.getParameter("remark") == null ? "" : request
				.getParameter("remark");

		AreaRoleManager selectAreaRole = areaService.selectAreaRole(id);
		selectAreaRole.setRemark(remark);
		selectAreaRole.setRolename(rolename);
		int updateAreaRole = areaService.updateAreaRole(selectAreaRole);

		if (updateAreaRole == 1) {

			jsonObject.put("statusCode", 200);
			jsonObject.put("title", "更新成功");
			jsonObject.put("message", "更新成功");
			return jsonObject.toString();
		} else {

			jsonObject.put("statusCode", 0);
			jsonObject.put("title", "更新失败");
			jsonObject.put("message", "更新失败");
			return jsonObject.toString();
		}

	}

	/**
	 * 删除人员角色
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/deletepersonrole", produces = { "application/json;charset=UTF-8" })
	public String deletepersonrole(HttpServletRequest request)
			throws ParseException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");

		String[] split = id.split(",");

		int deleteAreaRole = 0;

		for (int i = 0; i < split.length; i++) {
			String replace = split[i].replace("'", "");

			deleteAreaRole = areaService.deleteAreaRole(replace);

			if (deleteAreaRole != 1) {

				jsonObject.put("statusCode", 0);
				jsonObject.put("title", "删除失败");
				jsonObject.put("message", "删除失败");
				return jsonObject.toString();
			}
		}
		if (deleteAreaRole == 1) {
			jsonObject.put("statusCode", 200);
			jsonObject.put("title", "删除成功");
			jsonObject.put("message", "删除成功");
			return jsonObject.toString();

		} else {
			jsonObject.put("statusCode", 0);
			jsonObject.put("title", "删除失败");
			jsonObject.put("message", "删除失败");
			return jsonObject.toString();
		}
	}

	/**
	 * 删除区域
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteareainfo", produces = { "application/json;charset=UTF-8" })
	public String deleteareainfo(HttpServletRequest request)
			throws ParseException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		String[] split = id.split(",");

		int deleteAreaGov = 0;
		int deleteAreaRelation = 0;

		for (int i = 0; i < split.length; i++) {
			String replace = split[i].replace("'", "");

			deleteAreaGov = areaService.deleteAreaGov(replace);
			deleteAreaRelation = areaService.deleteAreaRelation(replace);

			if (deleteAreaGov != 1 || deleteAreaRelation != 1) {

				jsonObject.put("statusCode", 0);
				jsonObject.put("title", "删除失败");
				jsonObject.put("message", "删除失败");
				return jsonObject.toString();
			}
		}
		if (deleteAreaGov == 1 && deleteAreaRelation == 1) {
			jsonObject.put("statusCode", 200);
			jsonObject.put("title", "删除成功");
			jsonObject.put("message", "删除成功");
			return jsonObject.toString();

		} else {
			jsonObject.put("statusCode", 0);
			jsonObject.put("title", "删除失败");
			jsonObject.put("message", "删除失败");
			return jsonObject.toString();
		}
	}

	/**
	 * 获取要编辑的区域的信息
	 */
	@ResponseBody
	@RequestMapping(value = "/geteditareainfo", produces = { "application/json;charset=UTF-8" })
	public String geteditareainfo(HttpServletRequest request) {

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");

		AreaGov selectAreaGovById = areaService.selectAreaGovById(id);
		Gson gson = new Gson();
		String json = gson.toJson(selectAreaGovById);

		System.out.println(json.toString());
		return json.toString();
	}

	/**
	 * 保存编辑的区域的信息
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/saveeditareainfo", produces = { "application/json;charset=UTF-8" })
	public String saveeditareainfo(HttpServletRequest request)
			throws ParseException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		String areacode = request.getParameter("areacode") == null ? ""
				: request.getParameter("areacode");
		String areaname = request.getParameter("areaname") == null ? ""
				: request.getParameter("areaname");
		String areaaddress = request.getParameter("areaaddress") == null ? ""
				: request.getParameter("areaaddress");
		String areatypestr = request.getParameter("areatype") == null ? ""
				: request.getParameter("areatype");
		String setuptimestr = request.getParameter("setuptime") == null ? ""
				: request.getParameter("setuptime");
		Date changetime = ft.parse(setuptimestr);
		String areatype;
		if (areatypestr == "1" || areatypestr.equals("1")) {
			areatype = "city";
		} else if (areatypestr == "2" || areatypestr.equals("2")) {

			areatype = "country";
		} else {

			areatype = areatypestr;
		}

		AreaGov selectAreaGovById = areaService.selectAreaGovById(id);
		selectAreaGovById.setAreacode(areacode);
		selectAreaGovById.setAreaname(areaname);
		selectAreaGovById.setAreaaddress(areaaddress);
		selectAreaGovById.setAreatype(areatype);
		selectAreaGovById.setChangetime(changetime);

		int updateAreaGov = areaService.updateAreaGov(selectAreaGovById);

		AreaRelation selectById = areaService.selectById(id);
		selectById.setName(areaname);
		int updateAreaRelation = areaService.updateAreaRelation(selectById);

		if (updateAreaGov == 1 && updateAreaRelation == 1) {

			jsonObject.put("statusCode", 200);
			jsonObject.put("title", "更新成功");
			jsonObject.put("message", "更新成功");
			return jsonObject.toString();
		} else {

			jsonObject.put("statusCode", 0);
			jsonObject.put("title", "更新失败");
			jsonObject.put("message", "更新失败");
			return jsonObject.toString();
		}
	}

	/**
	 * 删除蜂巢
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/deletehive", produces = { "application/json;charset=UTF-8" })
	public String deletehive(HttpServletRequest request) throws ParseException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		String[] split = id.split(",");

		int deleteAreaCommunity = 0;
		int deleteAreaRelation = 0;

		for (int i = 0; i < split.length; i++) {
			String replace = split[i].replace("'", "");

			deleteAreaCommunity = areaService.deleteAreaCommunity(replace);
			deleteAreaRelation = areaService.deleteAreaRelation(replace);

			if (deleteAreaCommunity != 1 || deleteAreaRelation != 1) {

				jsonObject.put("statusCode", 0);
				jsonObject.put("title", "删除失败");
				jsonObject.put("message", "删除失败");
				return jsonObject.toString();
			}
		}
		if (deleteAreaCommunity == 1 && deleteAreaRelation == 1) {
			jsonObject.put("statusCode", 200);
			jsonObject.put("title", "删除成功");
			jsonObject.put("message", "删除成功");
			return jsonObject.toString();

		} else {
			jsonObject.put("statusCode", 0);
			jsonObject.put("title", "删除失败");
			jsonObject.put("message", "删除失败");
			return jsonObject.toString();
		}
	}

	/**
	 * 删除单位
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteunitinfo", produces = { "application/json;charset=UTF-8" })
	public String deleteunitinfo(HttpServletRequest request)
			throws ParseException {
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		String[] split = id.split(",");
		int deleteByPrimaryKey = 0;
		for (int i = 0; i < split.length; i++) {
			String replace = split[i].replace("'", "");

			deleteByPrimaryKey = unitService.deleteByPrimaryKey(replace);

			if (deleteByPrimaryKey != 1) {

				jsonObject.put("statusCode", 0);
				jsonObject.put("title", "删除失败");
				jsonObject.put("message", "删除失败");
				return jsonObject.toString();
			} else {

				jsonObject.put("statusCode", 200);
				jsonObject.put("title", "删除成功");
				jsonObject.put("message", "删除成功");
				return jsonObject.toString();
			}
		}

		if (deleteByPrimaryKey == 1) {
			jsonObject.put("statusCode", 200);
			jsonObject.put("title", "删除成功");
			jsonObject.put("message", "删除成功");
			return jsonObject.toString();

		} else {
			jsonObject.put("statusCode", 0);
			jsonObject.put("title", "删除失败");
			jsonObject.put("message", "删除失败");
			return jsonObject.toString();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/getListone", produces = { "application/json;charset=UTF-8" })
	public String getListone(HttpServletRequest request) throws ParseException {

		List<AreaRelation> selectAreaRelations = areaService
				.selectAreaRelations("0");
		List<Object> data = new ArrayList<Object>();// 最大一层
		for (AreaRelation areaRelation : selectAreaRelations) {

			Map<String, Object> menumap1 = new HashMap<String, Object>();// 一级目录
			// 北京市
			menumap1.put("id", areaRelation.getId());
			menumap1.put("pid", areaRelation.getpId());
			menumap1.put("text", areaRelation.getName());
			menumap1.put("state", areaRelation.getState());
			menumap1.put("iconCls", areaRelation.getIconcls());
			menumap1.put("url", areaRelation.getOnclick());
			
			data.add(menumap1);
		}

		Gson gson = new Gson();
		String json = gson.toJson(data);

		System.out.println(json.toString());
		return json.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getListByPid", produces = { "application/json;charset=UTF-8" })
	public String getListByPid(HttpServletRequest request)
			throws ParseException {

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");

		List<AreaRelation> selectAreaRelations = areaService
				.selectAreaRelations(id);

		List<Object> data = new ArrayList<Object>();// 最大一层
		for (AreaRelation areaRelation : selectAreaRelations) {

			Map<String, Object> menumap1 = new HashMap<String, Object>();// 一级目录
			// 北京市
			menumap1.put("id", areaRelation.getId());
			menumap1.put("pid", areaRelation.getpId());
			menumap1.put("text", areaRelation.getName());
			menumap1.put("state", areaRelation.getState());
			menumap1.put("iconCls", areaRelation.getIconcls());
			menumap1.put("url", areaRelation.getOnclick());
			
			data.add(menumap1);
		}

		
		Gson gson = new Gson();
		String json = gson.toJson(data);

		System.out.println(json.toString());
		return json.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getFatherIds", produces = { "application/json;charset=UTF-8" })
	public String getFatherIds(HttpServletRequest request)
			throws ParseException {

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");

		List<AreaRelation> selectAreaRelations = areaService
				.selectAreaRelations(id);
		List<Object> data = new ArrayList<Object>();// 最大一层
		for (AreaRelation areaRelation : selectAreaRelations) {

			Map<String, Object> menumap1 = new HashMap<String, Object>();// 一级目录
			// 北京市
			menumap1.put("id", areaRelation.getId());
			menumap1.put("pid", areaRelation.getpId());
			menumap1.put("text", areaRelation.getName());
			menumap1.put("state", areaRelation.getState());
			menumap1.put("iconCls", areaRelation.getIconcls());
			menumap1.put("url", areaRelation.getOnclick());
			
			data.add(menumap1);
		}

		Gson gson = new Gson();
		String json = gson.toJson(data);

		System.out.println(json.toString());
		return json.toString();

	}
	
	/**
	 * 获取要编辑的部门的信息
	 */
	@ResponseBody
	@RequestMapping(value = "/geteditdepartmentinfo", produces = { "application/json;charset=UTF-8" })
	public String geteditdepartmentinfo(HttpServletRequest request) {

		String id = request.getParameter("id") == null ? "" : request
				.getParameter("id");

		DepartmentInfo selectByPrimaryKey = departmentService.selectByDepartmentCode(id);

		String unitcode = selectByPrimaryKey.getUnitcode();
		
		UnitInfo selectByPrimaryKey2 = unitService.selectByPrimaryKey(unitcode);
		
		String unitname = selectByPrimaryKey2.getUnitname();
		
		selectByPrimaryKey.setUnitcode(unitname);
		
		Gson gson = new Gson();
		String json = gson.toJson(selectByPrimaryKey);

		System.out.println(json.toString());
		return json.toString();
	}

	
	/**
	 * 获取所有组织机构
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="loadusertree")
	 public void loadUserTree(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
	   
		Object userid = session.getAttribute("userid");
		System.out.println("userid:"+userid);
		
		List<OMenuinfo> user_tree_list = omenuInfoService.selectOMenuInfoList();
		String userid2 = userid.toString();
		
		if(userid2.equals("1")){
			
			System.out.println(user_tree_list);
			System.out.println("string1==="+user_tree_list.toString());
			//利用Json插件将Array转换成Json格式
			String string = JSON.toJSON(user_tree_list).toString();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(string);
			System.out.println(string);
		}else{
			
			UserInfo selectByPrimaryKey = userService.selectByPrimaryKey(userid2);
			System.out.println("userinfo:"+selectByPrimaryKey.toString());
			String unitname = selectByPrimaryKey.getUnitname();
			List<OMenuinfo> unitname_tree = new ArrayList<OMenuinfo>();
			for (OMenuinfo oMenuinfo : user_tree_list) {
				
				if (oMenuinfo.getName().equals(unitname)) {
					unitname_tree.add(oMenuinfo);
					String unitid = oMenuinfo.getId();
					
					for (OMenuinfo oMenuinfo2 : user_tree_list) {
						
						
						if(oMenuinfo2.getPID().equals(unitid)){
							unitname_tree.add(oMenuinfo2);
							
						}
						
					}
					
				}
				
			}
			
			System.out.println(unitname_tree);
			System.out.println("string1==="+unitname_tree.toString());
			//利用Json插件将Array转换成Json格式
			String string = JSON.toJSON(unitname_tree).toString();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(string);
			System.out.println(string);
			
		}
	  }
	
	/**
	 * 保存人员信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertPersonnelInfo",method=RequestMethod.POST)
	public  Map<String, String>  insertPersonnelInfo(@RequestBody String personinfo){//添加到数据库表departmentmanage
		
		JSONObject json = JSONObject.parseObject(personinfo);
		System.out.println(personinfo.toString());
		String mangerId = MyLong.getRandStr(11);
		Map<String, String> map = new HashMap<String, String>();
		if(json!=null ){
			PersonInfo personInfo2 = new PersonInfo();
			personInfo2.setChangetime(json.getDate("changetime"));
			personInfo2.setDepartmentname(json.getString("departmentname"));
			personInfo2.setName(json.getString("name"));
			personInfo2.setPersoncode(json.getString("personcode"));
			personInfo2.setPersonid(mangerId);
			personInfo2.setPid(json.getInteger("pid"));
			personInfo2.setRole(json.getString("role"));
			personInfo2.setSetuptime(json.getDate("setuptime"));
			//personInfo2.setState(a);
			personInfo2.setUnitname(json.getString("unitname"));
			personInfo2.setAreaid(json.getString("areaid"));
			personInfo2.setAreaname(json.getString("areaname"));
			

				UserInfo user = new UserInfo();
				String id =  MyLong.getRandStr(11);
				personInfo2.setUserid(id);
				String username = json.getString("username");
				String password = "";
				if(json.getString("password")== null  || json.getString("password").isEmpty()){
					password = MD5andKL.MD5(json.getString("password")+"123456");
				}else{
					password = MD5andKL.MD5(json.getString("username")+json.getString("password"));
				}
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				personInfo2.setUsername(username);
				personInfo2.setPassword(password);
				if(json.getString("unitid")!=null){
					user.setUnitid(json.getString("unitid"));//用作存储unitID
				}else{
					user.setUnitid("0");//0代表最高单位
				}//unitName
				if(json.getString("unitName")!=null){
					user.setUnitname(json.getString("unitName"));//用作存储setUnitname
				}else{
					user.setUnitname("超级管理员");//0代表最高单位
				}//unitName
				
				userService.insert(user);
//			}
			
			int num = personService.insert(personInfo2);
			map.put("isSuccess", num+"");
		}else{
			try {
				throw new  Exception("数据不能为空");
			} catch (Exception e) {
				e.printStackTrace();
		}}
		return map;
	}
	
	

}
