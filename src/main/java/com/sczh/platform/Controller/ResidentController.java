package com.sczh.platform.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sczh.platform.po.AreaCommunity;
import com.sczh.platform.po.AreaRelation;
import com.sczh.platform.po.CloudWxLoginUser;
import com.sczh.platform.po.LjflPrizeProvide;
import com.sczh.platform.po.LjflResidentType;
import com.sczh.platform.po.LjflSmartCard;
import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.po.Model.PageModelDomain;
import com.sczh.platform.service.ApiService;
import com.sczh.platform.service.AreaService;
import com.sczh.platform.service.LjflResidentTypeService;
import com.sczh.platform.service.LjflStaffDetailService;
import com.sczh.platform.service.ResidentService;
import com.sczh.platform.service.UserService;
import com.yunlao.util.MD5Util;
import com.yunlao.util.QRCodeUtil;
import com.yunlao.util.excel.ExcelUtil;

@Controller
@RequestMapping("resident")
public class ResidentController {

	@Autowired
	private ResidentService residentService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private ApiService ApiService;
	@Autowired
	private LjflStaffDetailService ljflStaffDetailService;
	@Autowired
	private UserService UserService;
	@Autowired
	private LjflResidentTypeService LjflResidentTypeService;

	public static String id = null;

	@RequestMapping("joinResidentPage")
	public ModelAndView joinResidentPage() {

		ModelAndView mav = new ModelAndView("resident/joinResidentPage");

		List<AreaCommunity> selectareaCommunities = areaService
				.selectareaCommunities();

		System.out
				.println("==========================" + selectareaCommunities);

		mav.addObject("area", selectareaCommunities);

		return mav;
	}

	@ResponseBody
	@RequestMapping("getResidentData")
	public PageModelDomain getResidentData(
			@RequestBody PageModelDomain modelDomain) {

		PageModelDomain pageModelDomain = new PageModelDomain();
		String name = modelDomain.getName();// 姓名
		String housesId = modelDomain.getHousesId();// 蜂巢
		System.out.println(housesId);
		String communityId = modelDomain.getCommunityId();// 所属社区
		String phone = modelDomain.getPhone();// 手机号
		if (name == null || name.isEmpty()) {
			name = null;
		}
		if (housesId == null || housesId.isEmpty()) {

			housesId = null;
		}else{
			
			List<AreaCommunity> selectAreaCommunitsByName = areaService.selectAreaCommunitsByName(housesId);
			housesId = selectAreaCommunitsByName.get(0).getId();
		}
		if (communityId == null || communityId.isEmpty()) {

			communityId = null;
		}
		if (phone == null || phone.isEmpty()) {

			phone = null;
		}

		int startSize = modelDomain.getPageSize()
				* (modelDomain.getCurrentPage() - 1);
		List<LjflStaffDetail> selectStaffDetailData = residentService
				.selectStaffDetailData(name, housesId, communityId, phone,
						startSize + "", modelDomain.getPageSize() + "");
		for (LjflStaffDetail ljflStaffDetail : selectStaffDetailData) {

			String getfHouseholdId = ljflStaffDetail.getfHouseholdId();
			
			// 蜂巢
			AreaCommunity selectByPrimaryKey2 = areaService
					.selectByPrimaryKey(getfHouseholdId);
			String getfName2 = selectByPrimaryKey2.getCommunityname();
			ljflStaffDetail.setfHouseholdId(getfName2);
			
			String getfResidentTypeId = ljflStaffDetail.getfResidentTypeId();
			
			LjflResidentType selectResidentTypById = LjflResidentTypeService.selectResidentTypById(getfResidentTypeId);
			if(selectResidentTypById==null){
				
				ljflStaffDetail.setfResidentTypeId("");
			}else{
				String getfName = selectResidentTypById.getfName();
				ljflStaffDetail.setfResidentTypeId(getfName);
			}
			
		}

		pageModelDomain.setStaffDetailList(selectStaffDetailData);
		pageModelDomain.setPageTotalSize(residentService
				.selectStaffDetailDataTotal(name, housesId, communityId, phone)
				.size());

		System.out.println(pageModelDomain.toString());
		return pageModelDomain;
	}

	@ResponseBody
	@RequestMapping("lookresidentData")
	public LjflStaffDetail lookresidentData(@RequestBody String ids) {
		
		JSONObject parseObject = JSONObject.parseObject(ids);
		
		String id = parseObject.getString("id");
		
		System.out.println("num==" + id);
		LjflStaffDetail ljflStaffDetail = ljflStaffDetailService
				.selectByPrimaryKey(id);

		
		// 蜂巢
		String getfHouseholdId = ljflStaffDetail.getfHouseholdId();
		AreaCommunity selectByPrimaryKey2 = areaService
				.selectByPrimaryKey(getfHouseholdId);
		String getfName2 = selectByPrimaryKey2.getCommunityname();
		ljflStaffDetail.setfHouseholdId(getfName2);
		// 居民类型
		String getfResidentTypeId = ljflStaffDetail.getfResidentTypeId();
		if(getfResidentTypeId==null || getfResidentTypeId.isEmpty()){
			
			ljflStaffDetail.setfResidentTypeId("");
		}else{
			LjflResidentType selectResidentTypById = LjflResidentTypeService
				.selectResidentTypById(getfResidentTypeId);
			ljflStaffDetail.setfResidentTypeId(selectResidentTypById.getfName());
		}
		
		return ljflStaffDetail;
	}

	@RequestMapping("updateresidentData")
	public ModelAndView updateresidentData(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		System.out.println("num==" + id);

		LjflStaffDetail ljflStaffDetail = ljflStaffDetailService
				.selectByPrimaryKey(id);

		// 蜂巢
		String getfHouseholdId = ljflStaffDetail.getfHouseholdId();
		AreaCommunity selectByPrimaryKey = areaService.selectByPrimaryKey(getfHouseholdId);
		String getfName2 = selectByPrimaryKey.getCommunityname();
		ljflStaffDetail.setfHouseholdId(getfName2);
		// 居民类型
		String getfResidentTypeId = ljflStaffDetail.getfResidentTypeId();
		if(getfResidentTypeId==null || getfResidentTypeId.isEmpty()){
			
			ljflStaffDetail.setfResidentTypeId("");
		}else{
			LjflResidentType selectResidentTypById = LjflResidentTypeService
				.selectResidentTypById(getfResidentTypeId);
			ljflStaffDetail.setfResidentTypeId(selectResidentTypById.getfName());
		}
		
		List<AreaCommunity> selectareaCommunities = areaService
				.selectareaCommunities();
		mav.addObject("area", selectareaCommunities);

		List<LjflResidentType> selectLjflResidentType = LjflResidentTypeService.selectLjflResidentType();
		
		mav.addObject("ljflstaffdetail", ljflStaffDetail);
		mav.addObject("ResidentType", selectLjflResidentType);
		mav.setViewName("resident/updateresidentpage");
		return mav;
	}

	@ResponseBody
	@RequestMapping("updateresidentInfo")
	public Map<String, String> updateresidentInfo(@RequestBody String jsondata) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsondata);

		// 姓名
		String name = parseObject.getString("name");
		// 幢数
		String znum1 = parseObject.getString("znum");
		int znum;
		if (znum1 == null || znum1.equals("")) {
			znum = 0;
		} else {

			znum = Integer.parseInt(znum1);
		}
		// 性别
		String gender = parseObject.getString("gender");
		// 累计积分
		String totalScore = parseObject.getString("totalScore");
		if (totalScore == null || totalScore.equals("")) {
			totalScore = "0";
		}
		// 人员类型
		String staffType1 = parseObject.getString("staffType");
		int staffType;
		if (staffType1 == null || staffType1.equals("")) {
			staffType = 0;
		} else {

			staffType = Integer.parseInt(staffType1);
		}
		// 身份证号
		String credentialNum = parseObject.getString("credentialNum");
		// 年龄
		String age1 = parseObject.getString("age");
		int age;
		if (age1 == null || age1.equals("")) {
			age = 0;
		} else {

			age = Integer.parseInt(age1);
		}
		// 家庭常住人口数
		String liveNum1 = parseObject.getString("liveNum");
		int liveNum;
		if (liveNum1 == null || liveNum1.equals("")) {
			liveNum = 0;
		} else {

			liveNum = Integer.parseInt(liveNum1);
		}
		// 蜂巢id
		String housesId = parseObject.getString("housesId");
		List<AreaCommunity> selectAreaCommunitsByName = areaService.selectAreaCommunitsByName(housesId);
		String id = selectAreaCommunitsByName.get(0).getId();
		
		AreaRelation selectById = areaService.selectById(id);
		// 蜂巢name
		String name2 = selectById.getName();
		// 社区id
		String getpId = selectById.getpId();
		AreaRelation selectById2 = areaService.selectById(getpId);
		// 社区name
		String name3 = selectById2.getName();
		// 街道id
		String getpId2 = selectById2.getpId();
		AreaRelation selectById3 = areaService.selectById(getpId2);
		// 街道name
		String name4 = selectById3.getName();
		// 区id
		String getpId3 = selectById3.getpId();
		AreaRelation selectById4 = areaService.selectById(getpId3);
		// 区name
		String name5 = selectById4.getName();

		// 室数
		String rnum1 = parseObject.getString("rnum");
		int rnum;
		if (rnum1 == null || rnum1.equals("")) {
			rnum = 0;
		} else {

			rnum = Integer.parseInt(rnum1);
		}
		// 居民分类
		
		String residentTypename= parseObject.getString("residentTypeId");
		List<LjflResidentType> selectresidenttypeList = LjflResidentTypeService.selectresidenttypeList(residentTypename);
		
		String residentTypeId = selectresidenttypeList.get(0).getId();
		
		
		// 当前积分
		String currentScore = parseObject.getString("currentScore");
		if (currentScore == null || currentScore.equals("")) {
			currentScore = "0";
		}
		// 人员服务状态
		String serveStatus = parseObject.getString("serveStatus");
		// 婚姻状态
		String maritalStatusId = parseObject.getString("maritalStatusId");
		// 手机号码
		String phone = parseObject.getString("phone");

		String id2 = parseObject.getString("id");
		
		LjflStaffDetail ljflStaffDetail = ljflStaffDetailService
				.selectByPrimaryKey(id2);

		Date date = new Date();

		ljflStaffDetail.setCreatetime(date);
		ljflStaffDetail.setLastchangetime(date);
		ljflStaffDetail.setStatus(0);
		ljflStaffDetail.setBeendeleted(0);
		ljflStaffDetail.setTenantid("4eaf9423eb0043648e502134586b088a");
		ljflStaffDetail.setAge(age);
		ljflStaffDetail.setCredentialnum(credentialNum);
		ljflStaffDetail.setfCurrentBalance(Double.valueOf(currentScore));
		ljflStaffDetail.setfCurrentScore(Double.valueOf(currentScore));
		ljflStaffDetail.setGender(gender);
		ljflStaffDetail.setfLiveNum(liveNum);
		ljflStaffDetail.setMaritalstatusid(maritalStatusId);
		ljflStaffDetail.setName(name);
		ljflStaffDetail.setPhone(phone);
		ljflStaffDetail.setfServeStatus(serveStatus);
		ljflStaffDetail.setfStaffType(staffType);
		ljflStaffDetail.setfTotalBalance(Double.valueOf(totalScore));
		ljflStaffDetail.setfTotalScore(Double.valueOf(totalScore));
		ljflStaffDetail.setfResidentTypeId(residentTypeId);
		ljflStaffDetail.setfAddress(name5 + name4+name3+name2+znum + "幢" + rnum
				+ "室");
		ljflStaffDetail.setDoorname(znum+"幢"+rnum+"室");
		
		ljflStaffDetail.setfHouseholdId(id);
		
		int insert = ljflStaffDetailService.updateByPrimaryKey(ljflStaffDetail);
		if (insert == 1) {

			map.put("message", "修改成功");
			return map;
		} else {

			map.put("message", "修改失败");
			return map;
		}

	}

	@ResponseBody
	@RequestMapping("deleteresidentData")
	public Map<String, String> deleteresidentData(@RequestBody String jsondata) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsondata);
		String id = parseObject.getString("id");
		LjflStaffDetail selectByPrimaryKey = ljflStaffDetailService
				.selectByPrimaryKey(id);
		selectByPrimaryKey.setBeendeleted(1);
		int updateByPrimaryKey = ljflStaffDetailService
				.updateByPrimaryKey(selectByPrimaryKey);
		if (updateByPrimaryKey == 1) {

			map.put("message", "删除成功");
		} else {

			map.put("message", "删除成功");
		}

		return map;
	}

	/*@RequestMapping("addresident")
	public ModelAndView addresident() {

		ModelAndView mav = new ModelAndView();

		// 蜂巢
		List<AreaCommunity> selectareaCommunities = areaService.selectareaCommunities();
		mav.addObject("area", selectareaCommunities);
		List<LjflResidentType> selectLjflResidentType = LjflResidentTypeService.selectLjflResidentType();
		
		mav.addObject("ResidentType", selectLjflResidentType);
		mav.setViewName("resident/addresidentpage");
		return mav;
	}*/

	@ResponseBody
	@RequestMapping("addresidentInfo")
	public Map<String, String> addresidentInfo(@RequestBody String jsondata) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsondata);

		// 姓名
		String name = parseObject.getString("name");
		// 幢数
		String znum1 = parseObject.getString("znum");
		int znum;
		if (znum1.equals("") || znum1 == null) {
			znum = 0;
		} else {
			znum = Integer.parseInt(znum1);

		}
		// 性别
		String gender = parseObject.getString("gender");
		// 累计积分
		String totalScore = parseObject.getString("totalScore");

		if (totalScore == null || totalScore.equals("")) {

			totalScore = "0";
		}

		// 人员类型
		String staffType1 = parseObject.getString("staffType");
		int staffType;
		if (staffType1 == null || staffType1.equals("")) {

			staffType = 0;
		} else {

			staffType = Integer.parseInt(staffType1);
		}
		// 身份证号
		String credentialNum = parseObject.getString("credentialNum");
		// 年龄
		String age1 = parseObject.getString("age");
		int age;
		if (age1 == null || age1.equals("")) {

			age = 0;
		} else {

			age = Integer.parseInt(age1);
		}
		// 家庭常住人口数
		String liveNum1 = parseObject.getString("liveNum");
		int liveNum;
		if (liveNum1 == null || liveNum1.equals("")) {

			liveNum = 0;
		} else {

			liveNum = Integer.parseInt(liveNum1);
		}
		// 蜂巢id
		String housesId = parseObject.getString("housesId");
		List<AreaCommunity> selectAreaCommunitsByName = areaService.selectAreaCommunitsByName(housesId);
		String id = selectAreaCommunitsByName.get(0).getId();
		
		AreaRelation selectById = areaService.selectById(id);
		// 蜂巢name
		String name2 = selectById.getName();
		// 社区id
		String getpId = selectById.getpId();
		AreaRelation selectById2 = areaService.selectById(getpId);
		// 社区name
		String name3 = selectById2.getName();
		// 街道id
		String getpId2 = selectById2.getpId();
		AreaRelation selectById3 = areaService.selectById(getpId2);
		// 街道name
		String name4 = selectById3.getName();
		// 区id
		String getpId3 = selectById3.getpId();
		AreaRelation selectById4 = areaService.selectById(getpId3);
		// 区name
		String name5 = selectById4.getName();

		// 室数
		String rnum1 = parseObject.getString("rnum");
		int rnum;
		if (rnum1 == null || rnum1.equals("")) {
			rnum = 0;
		} else {

			rnum = Integer.parseInt(rnum1);
		}
		// 居民分类
		String residentTypename= parseObject.getString("residentTypeId");
		List<LjflResidentType> selectresidenttypeList = LjflResidentTypeService.selectresidenttypeList(residentTypename);
		
		String residentTypeId = selectresidenttypeList.get(0).getId();
		
		// 当前积分
		String currentScore = parseObject.getString("currentScore");
		if (currentScore == null || currentScore.equals("")) {
			currentScore = "0";
		}
		// 人员服务状态
		String serveStatus = parseObject.getString("serveStatus");
		// 婚姻状态
		String maritalStatusId = parseObject.getString("maritalStatusId");
		// 手机号码
		String phone = parseObject.getString("phone");


		Date date = new Date();
		
			LjflStaffDetail ljflStaffDetail = new LjflStaffDetail();
			String id2 = UUID.randomUUID().toString().replaceAll("-", "");
			ljflStaffDetail.setId(id2);
			ljflStaffDetail.setCreatetime(date);
			ljflStaffDetail.setLastchangetime(date);
			ljflStaffDetail.setStatus(0);
			ljflStaffDetail.setBeendeleted(0);
			ljflStaffDetail.setTenantid("4eaf9423eb0043648e502134586b088a");
			ljflStaffDetail.setAge(age);
			ljflStaffDetail.setCredentialnum(credentialNum);
			ljflStaffDetail.setfCurrentBalance(Double.valueOf(currentScore));
			ljflStaffDetail.setfCurrentScore(Double.valueOf(currentScore));
			ljflStaffDetail.setGender(gender);
			ljflStaffDetail.setfLiveNum(liveNum);
			ljflStaffDetail.setMaritalstatusid(maritalStatusId);
			ljflStaffDetail.setName(name);
			ljflStaffDetail.setPhone(phone);
			ljflStaffDetail.setfServeStatus(serveStatus);
			ljflStaffDetail.setfStaffType(staffType);
			ljflStaffDetail.setfTotalBalance(Double.valueOf(totalScore));
			ljflStaffDetail.setfTotalScore(Double.valueOf(totalScore));
			ljflStaffDetail.setfHouseholdId(id);
			ljflStaffDetail.setfResidentTypeId(residentTypeId);
			ljflStaffDetail.setfAddress(name5+name4+name3+name2 + znum + "幢"
					+ rnum + "室");
			ljflStaffDetail.setDoorname(znum + "幢"+ rnum + "室");
			ljflStaffDetail.setRnum(rnum);
			ljflStaffDetail.setZnum(znum);
			
			int insert = ljflStaffDetailService.insert(ljflStaffDetail);
			if (insert == 1) {

				map.put("message", "新增成功");
				return map;
			} else {

				map.put("message", "添加失败");
				return map;
			}


	}

	@ResponseBody
	@RequestMapping("resetPassword")
	public Map<String, String> resetPassword(@RequestBody String jsondata) {

		Map<String, String> map = new HashMap<String, String>();
		JSONObject parseObject = JSONObject.parseObject(jsondata);
		String id = parseObject.getString("id");

		System.out.println("id" + id);
		CloudWxLoginUser selectBystaffId = UserService.selectBystaffId(id);
		System.out.println(selectBystaffId);
		if (selectBystaffId == null) {

			map.put("message", "该用户暂未开通微信登录");
			return map;
		} else {

			LjflSmartCard selectByfBindId = ApiService.selectByfBindId(id);
			if (selectByfBindId == null) {

				map.put("message", "该用户没有绑卡");
				return map;
			} else {
				String getfCCode = selectByfBindId.getfCCode();
				selectBystaffId.setfOriginalPassword(getfCCode
						.substring(getfCCode.length() - 6));
				selectBystaffId.setfPassword(MD5Util.GetMD5Code(getfCCode
						.substring(getfCCode.length() - 6)));

				int updateByPrimaryKey = UserService
						.updateByPrimaryKey(selectBystaffId);
				if (updateByPrimaryKey == 1) {

					map.put("message", "重置成功");
					return map;
				} else {

					map.put("message", "重置失败");
					return map;
				}
			}
		}
	}

	@ResponseBody
	@RequestMapping("generatecode")
	public Map<String, String> generatecode(@RequestBody String jsondata,
			HttpServletRequest req) {

		Map<String, String> map = new HashMap<String, String>();
		JSONObject parseObject = JSONObject.parseObject(jsondata);
		String contextPath = req.getSession().getServletContext()
				.getRealPath("/img");
		String id = parseObject.getString("id");
		String path = contextPath + "/code/" + id + ".jpg";
		System.out.println(path);
		QRCodeUtil.zxingCodeCreate(id, 300, 300, path, "jpg");
		map.put("message", "生成成功");
		System.out.println(map.toString());
		return map;
	}
	
	 
	 /* @RequestMapping({"exportStaffDetailPage"})
	  public ModelAndView exportStaffDetailPage(HttpSession session)
	  {
	    String userId = session.getAttribute("userid").toString();
	    System.out.println(userId);
	    ModelAndView mav = new ModelAndView("resident/exportStaffDetailPage");
	    mav.addObject("userId", userId);
	    return mav;
	  }*/
	  
	  @RequestMapping(value={"/exportExcel"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public ModelAndView upload(HttpServletRequest request, @RequestParam("file") MultipartFile file)
	    throws Exception
	  {
	    ModelAndView mav = new ModelAndView("message/success");
	    
	    String fileNameType = file.getOriginalFilename();
	    
	    ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel2007(file, fileNameType);
	    System.out.println(result.size());
	    for (ArrayList<Object> arrayList : result)
	    {
	      LjflStaffDetail staffDetail = new LjflStaffDetail();
	      ArrayList<Object> list = arrayList;
	      String id = UUID.randomUUID().toString();
	      String str0 = list.get(0).toString();
	      String staffName = str0;
	      if ((isDouble(staffName)) && 
	        (!isInteger(staffName))) {
	        staffName = staffName.substring(0, staffName.length() - 3);
	      }
	      String str1 = list.get(1).toString();
	      int znum = 0;
	      if ((isDouble(str1)) && 
	        (!isInteger(str1)))
	      {
	        str1 = str1.substring(0, str1.length() - 3);
	        znum = Integer.parseInt(str1);
	      }
	      String str2 = list.get(2).toString();
	      int rnum = 0;
	      if ((isDouble(str2)) && 
	        (!isInteger(str2)))
	      {
	        str2 = str2.substring(0, str2.length() - 3);
	        rnum = Integer.parseInt(str2);
	      }
	      Date creteTime = new Date();
	      String str3 = list.get(3).toString();
	      String areaName = str3;
	      if ((isDouble(areaName)) && 
	        (!isInteger(areaName))) {
	        areaName = areaName.substring(0, areaName.length() - 3);
	      }
	      
	      List<AreaCommunity> areaList = this.areaService.selectAreaCommunitsByName(areaName);
	      if (areaList.size() == 0)
	      {
	        mav = new ModelAndView("message/error");
	        mav.addObject("areaName", areaName);
	        return mav;
	      }
	      staffDetail.setId(id);
	      staffDetail.setName(staffName);
	      staffDetail.setZnum(Integer.valueOf(znum));
	      staffDetail.setRnum(Integer.valueOf(rnum));
	      staffDetail.setCreatetime(creteTime);
	      staffDetail.setLastchangetime(creteTime);
	      staffDetail.setStatus(0);
	      staffDetail.setTenantid("4eaf9423eb0043648e502134586b088a");
	      staffDetail.setDoorname(znum + "幢" + rnum + "室");
	      staffDetail.setfHouseholdId(((AreaCommunity)areaList.get(0)).getId());
	      staffDetail.setfStaffType(Integer.valueOf(0));
	      staffDetail.setBeendeleted(Integer.valueOf(0));
	      staffDetail.setfResidentTypeId("db001db60d8042c5ab14b192d712e3ef");
	      staffDetail.setPhone("001");
	      staffDetail.setfCurrentBalance(Double.valueOf(0.0D));
	      staffDetail.setfCurrentScore(Double.valueOf(0.0D));
	      staffDetail.setfTotalScore(Double.valueOf(0.0D));
	      staffDetail.setfServeStatus("0");
	      staffDetail.setGender("0");
	      ljflStaffDetailService.insert(staffDetail);
	    }
	    return mav;
	  }
	  
	  @RequestMapping(value={"/delete"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public ModelAndView delete(HttpServletRequest request, @RequestParam("file1") MultipartFile file)
	    throws Exception
	  {
	    ModelAndView mav = new ModelAndView("/delete");
	    try
	    {
	      String fileNameType = file.getOriginalFilename();
	      
	      ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel2007(file, fileNameType);
	      System.out.println(result.size());
	      for (ArrayList<Object> arrayList : result)
	      {
	        ArrayList<Object> list = arrayList;
	        String str0 = list.get(0).toString();
	        String cardNo = str0;
	        if ((isDouble(str0)) && (!isInteger(str0))) {
	          cardNo = str0.substring(0, str0.length() - 3);
	        }
	      }
	    }
	    catch (Exception localException) {}
	    return mav;
	  }
	  
	  public static boolean isNumeric(String str)
	  {
	    int i = str.length();
	    do
	    {
	      if (!Character.isDigit(str.charAt(i))) {
	        return false;
	      }
	      i--;
	    } while (i >= 0);
	    return true;
	  }
	  
	  private static boolean isInteger(String str)
	  {
	    if ((str == null) || ("".equals(str))) {
	      return false;
	    }
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	    return pattern.matcher(str).matches();
	  }
	  
	  private static boolean isDouble(String str)
	  {
	    if ((str == null) || ("".equals(str))) {
	      return false;
	    }
	    Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
	    return pattern.matcher(str).matches();
	  }
	  
	
	/*  *//**
	   * 进入批量绑定只能卡页面
	   * @return
	   *//*
	  @RequestMapping(value={"/expotSMARTExcelPage"})
	  public ModelAndView expotSMARTExcelPage(){
		  ModelAndView mav = new ModelAndView("resident/expotSMARTExcelPage");
		  return mav;
	  }*/
	  @RequestMapping(value={"/exportSmartExcel"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public ModelAndView exportSmartExcel(HttpServletRequest request, @RequestParam("file") MultipartFile file,HttpSession session)
	    throws Exception
	  {
	    ModelAndView mav = new ModelAndView("message/success");
	    
	    String fileNameType = file.getOriginalFilename();
	    
	    ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel2007(file, fileNameType);
	    System.out.println(result.size());
	    for (ArrayList<Object> arrayList : result)
	    {
	      ArrayList<Object> list = arrayList;
	      String id = UUID.randomUUID().toString();
	      String str0 = list.get(0).toString();
	      String carNo = str0;
	      if ((isDouble(carNo)) && 
	        (!isInteger(carNo))) {
	    	  carNo = carNo.substring(0, carNo.length() - 3);
	      }
	      String str1 = list.get(1).toString();
	      /**
	       * 居民姓名
	       */
	      String staffName = str1;
	      if ((isDouble(staffName)) && 
	        (!isInteger(staffName))) {
	        staffName = staffName.substring(0, staffName.length() - 3);
	      }
	      String userId = session.getAttribute("userid").toString();
	      Map<String, Object> map = new HashMap<String, Object>();
		  map.put("f_c_code", carNo);
	      List<LjflSmartCard> smartList = LjflResidentTypeService.selectLjflSmartCardByPamaters(map);
	      if(smartList.size()==0){
	    	  List<LjflStaffDetail> staffNameList = ljflStaffDetailService.selectByName(staffName);
		      
		      if(staffNameList.size() == 0){
			        mav = new ModelAndView("message/staffError");
			        mav.addObject("staffName", carNo+","+staffName);
			        return mav;
		      }else{
		    	  LjflSmartCard smartCard = new LjflSmartCard();
		    	  smartCard.setId(id);
		    	  smartCard.setfBindName(staffNameList.get(0).getName());
		    	  smartCard.setfBindId(staffNameList.get(0).getId());
		    	  smartCard.setUserid(userId);
		    	  UserInfo user = UserService.selectByPrimaryKey(userId);
		    	  smartCard.setUnitid(user.getUnitid());
		    	  smartCard.setfCCode(carNo);
		    	  smartCard.setTenantid("4eaf9423eb0043648e502134586b088a");
		    	  Date date = new Date();
		    	  smartCard.setCreatetime(date);
		    	  smartCard.setBeendeleted(0);
		    	  smartCard.setStatus(0);
		    	  smartCard.setfBindType(0);
		    	  LjflResidentTypeService.insertLjflSmartCard(smartCard);
		    	  
		      }
	      }else{
	    	  	mav = new ModelAndView("message/smartError");
		        mav.addObject("staffName", carNo+","+staffName);
		        return mav;
	      }
	    

	    }
	    return mav;
	  }
	/*  *//**
	   * 进入批量精准导入数据页面
	   *//*
	  @RequestMapping({"updateStaffDetailInfoPage"})
	  public ModelAndView updateStaffDetailInfoPage(HttpSession session)
	  {
	    String userId = session.getAttribute("userid").toString();
	    System.out.println(userId);
	    ModelAndView mav = new ModelAndView("resident/updateStaffDetailInfoPage");
	    mav.addObject("userId", userId);
	    return mav;
	  }
	  */
	  //上传文件会自动绑定到MultipartFile中
	    @RequestMapping(value="/exportExcelUpdateStaffDetailList",method=RequestMethod.POST)
	    public ModelAndView exportExcelUpdateStaffDetailList(HttpServletRequest request,
	           @RequestParam("file") MultipartFile file) throws Exception {
	    		ModelAndView mav = new ModelAndView("message/success");
	 
	    		String fileNameType =  file.getOriginalFilename();//部署流程名

	           	ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel2007(file,fileNameType);
	           	System.out.println(result.size());
	           	for (ArrayList<Object> arrayList : result) {
	           		ArrayList<Object> list = arrayList;
	           		if(list.size() < 1){
	           			return mav;
	           		}
	           		String str0 = list.get(0).toString();//居民姓名
	           		String staffName = str0;
	           		if(isDouble(staffName)){
	           			if(!isInteger(staffName)){
	           				staffName = staffName.substring(0, staffName.length()-3);
	           			}
	           			
	           		}
	           		String realName = list.get(1).toString();//真实姓名
	           		if(isDouble(realName)){
	           			if(!isInteger(realName)){
	           				realName = realName.substring(0, realName.length()-3);
	           			}
	           			
	           		}
	           		String str3 = list.get(2).toString();
	           		String areaName = str3;//蜂巢
	           		if(isDouble(areaName)){
	           			if(!isInteger(areaName)){
	           				areaName = areaName.substring(0, areaName.length()-3);
	           			}
	           		}
	           		String str1 = list.get(3).toString();
	           		int znum = 0;//幢数
	           		if(isDouble(str1)){
	           			if(!isInteger(str1)){
	           				str1 = str1.substring(0, str1.length()-3);
	           				znum =  Integer.parseInt(str1);
	           			}
	           			
	           		}
	           		String str2 = list.get(4).toString();
	           		int rnum = 0;//室数
	           		if(isDouble(str2)){
	           			if(!isInteger(str2)){
	           				str2 = str2.substring(0, str2.length()-3);
	           				rnum =  Integer.parseInt(str2);
	           			}
	           			
	           		}
	           		String sex = "";// 性别
	           		try {
	           			sex  = list.get(5).toString();

	               		if(isDouble(sex)){
	               			if(!isInteger(sex)){
	               				sex = sex.substring(0, sex.length()-3);
	               			}
	               			
	               		}
	               		if("女".equals(sex)){
	               			sex = "0";
	               		}else{
	               			sex= "1";
	               		}
					} catch (Exception e) {
						sex = "1";
					}
	           		String mobilePhone ="";//联系方式
	           		try {
	           			mobilePhone  = list.get(6).toString();
	               		if(isDouble(mobilePhone)){
	               			if(!isInteger(mobilePhone)){
	               				mobilePhone = mobilePhone.substring(0, mobilePhone.length()-3);
	               			}
	               		}
					} catch (Exception e) {
						mobilePhone = "";
					}
	           		List<LjflStaffDetail> staffList =  ljflStaffDetailService.selectByName(staffName);
	           		
	           		if(staffList.size()  == 0){
	           			mav = new ModelAndView("message/updateStaffErrorByStaffName");
	           			mav.addObject("staffName", staffName);//系统当前没有此居民
	           			return mav;
	           		}else{
	           			for (LjflStaffDetail staff : staffList) {
	    					 List<AreaCommunity> areaList = this.areaService.selectAreaCommunitsByName(areaName);
	    					 if(areaList.size() ==0){//查询有没有当前蜂巢
	    						  mav = new ModelAndView("message/error");
	    					      mav.addObject("areaName", areaName);
	    					      return mav;
	    					 }else{
	    						 staff.setfHouseholdId(areaList.get(0).getId());
	    					 }
	    			
	    			
	    					 staff.setRnum(rnum);
	    					 staff.setZnum(znum);
	    					staff.setName(realName);
	    					staff.setGender(sex);
	    					staff.setPhone(mobilePhone);
	    					ljflStaffDetailService.updateByPrimaryKeySelective(staff);
	    					
	    					
	    				}
	           		}
	           		
	           		
	           		
	   
	           		
	           

	    		}

	     return mav;

	    }
	    // 生成UUID
	    public static String uuid() {
	        StringBuilder sb = new StringBuilder();
	        String uid = UUID.randomUUID().toString().replaceAll("-", "");
	        int length = uid.length();
	        // 将16进制数转换成64进制数
	        // 2的4次方转化成2的6次方的数据
	        // bcb da5 f41 172 4bc 28b 920 b1c 5f4 422 6e
	        // 3位转2位
	        int i = 0;
	        for (; i < length; i = i + 3) {
	            // 16进制数转化成10进制数
	            int end = i + 3;
	            if (end >= length) {
	                end = length;
	            }
	            int t = Integer.parseInt(uid.substring(i, end), 16);
	            // 10进制数转化成64进制数
	            sb.append(chars[t % 64]);// 第二个数据
	            sb.append(chars[t / 64]);// 第一个数据
	        }
	        return sb.toString();
	    }
	    private static final String[] chars = { // <br>
	            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", // <br>
	            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", // <br>
	            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", // <br>
	            "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", // <br>
	            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", // <br>
	            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", // <br>
	            "Y", "Z", "a1", "a2" // <br>
	    };
	    // 统计UUID的长度变化范围
	    public static void main(String[] args) throws InterruptedException {
	    	System.out.println(uuid().length());
	       
	    }

		/**
		 * 获取居民分类
		 * @param request
		 * @return
		 * @throws IOException
		 */
		@ResponseBody
		@RequestMapping(value = "/getresidenttype", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
		public String getresidenttype(HttpServletRequest request) throws IOException {
			
			request.setCharacterEncoding("UTF-8");
		
			List<LjflResidentType> selectLjflResidentType = LjflResidentTypeService.selectLjflResidentType();
			
			Gson gson = new Gson();
			String json = gson.toJson(selectLjflResidentType);
			System.out.println(json);
			return json;
		}

		
	  
}
