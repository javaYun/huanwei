package com.sczh.platform.Controller;

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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sczh.platform.po.AreaCommunity;
import com.sczh.platform.po.DivisionTable;
import com.sczh.platform.po.LjflAccountModify;
import com.sczh.platform.po.LjflExercise;
import com.sczh.platform.po.LjflScoreDetail;
import com.sczh.platform.po.LjflScoreRecord;
import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.SignInRecord;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.po.Model.ExportExcelFile;
import com.sczh.platform.po.Model.LjflActivityModel;
import com.sczh.platform.po.Model.LjflExercisePageDomain;
import com.sczh.platform.po.Model.LjflScoreAmendModel;
import com.sczh.platform.po.Model.LjflScoreAmendPageDomain;
import com.sczh.platform.po.Model.PageModelDomain;
import com.sczh.platform.po.Model.SignInPageDomain;
import com.sczh.platform.service.AreaService;
import com.sczh.platform.service.LjflAccountModifyService;
import com.sczh.platform.service.LjflExerciseService;
import com.sczh.platform.service.LjflScoreService;
import com.sczh.platform.service.LjflStaffDetailService;
import com.sczh.platform.service.UnitService;
import com.sczh.platform.service.UserService;
import com.yunlao.util.ExcelUtil;

@Controller
@RequestMapping("ljflScore")
public class LjflScoreController {

	@Autowired
	private LjflScoreService ljflScoreService;
	
	@Autowired
	private LjflExerciseService ljflExerciseService;

	@Autowired
	private LjflAccountModifyService ljflaccountmodifyService;

	@Autowired
	private LjflStaffDetailService ljflstaffdetailService;
	
	@Autowired
	private AreaService areaService;
	@Autowired
	private UserService userService;
	

	public static String id = null;

	/**
	 * 进入现场活动页面
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("ljflLocaleActivityPage")
	public ModelAndView ljflLocaleActivityPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("ljflscore/ljflLocaleActivityPage");

		List<AreaCommunity> selectareaCommunities = areaService
				.selectareaCommunities();

		System.out
				.println("==========================" + selectareaCommunities);

		System.out.println(selectareaCommunities);
		mav.addObject("housesname", selectareaCommunities);
		mav.addObject("userId", userId);
		return mav;
	}*/

	/**
	 * 进入积分修改页面
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("ljflScoreAmendPage")
	public ModelAndView ljflScoreAmendPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("ljflscore/ljflScoreAmendPage");
		mav.addObject("userId", userId);
		return mav;
	}*/

	/**
	 * 查询现场活动
	 * 
	 * @param exercisePageDomain
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getactivityData")
	public LjflExercisePageDomain getactivityData(
			@RequestBody LjflExercisePageDomain exercisePageDomain) {
		/**
		 * 1.没有任何筛选条件 2.只有活动名称 3.只有所属小区 4.只有活动开始时间 5.只有活动结束时间
		 */
		List<LjflActivityModel> ljflActivity = new ArrayList<LjflActivityModel>();
		List<LjflExercise> selectLjflExerciseInfoListTotal;
		// 获取条件活动名称
		String activityname = exercisePageDomain.getName();
		System.out.println(activityname);
		if (activityname == null || activityname == "") {
			// 根据活动名称筛选
			// 获取ljflexercise数据
			selectLjflExerciseInfoListTotal = ljflExerciseService
					.selectLjflExerciseInfoListTotal1();
		} else {

			selectLjflExerciseInfoListTotal = ljflExerciseService
					.selectLjflExerciseInfoListTotal(activityname);
		}

		// 获得条件小区
		String ljflHousesname = exercisePageDomain.getLjflHousesId();
		
		
		
		if (ljflHousesname != "") {
			List<AreaCommunity> selectAreaCommunitsByName = areaService.selectAreaCommunitsByName(ljflHousesname);
			String id = selectAreaCommunitsByName.get(0).getId();
			System.out.println("dgs:" + selectLjflExerciseInfoListTotal);

			Iterator<LjflExercise> iterator = selectLjflExerciseInfoListTotal
					.iterator();

			while (iterator.hasNext()) {

				LjflExercise ljflExercise = iterator.next();
				System.out.println(ljflExercise);
				// 获取fljflhousesid
				String fLjflhousesId = ljflExercise.getfLjflhousesId();
				
				if (fLjflhousesId.equals(id)) {

				} else {
					iterator.remove();
				}

			}
		} else {

		}

		// 获得条件开始时间
		String startTime = exercisePageDomain.getStartTime();
		// 获得条件结束时间
		String endtime = exercisePageDomain.getEndtime();

		// 判断是否有时间删选条件
		if (startTime != "" && endtime != "") {
			Date starttime1 = null;
			Date endtime1 = null;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				starttime1 = sdf.parse(startTime);
				endtime1 = sdf.parse(endtime);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

			long stime;
			long timeing;
			long etime;
			if (starttime1 == null) {
				stime = 0;
			} else {
				stime = starttime1.getTime();
			}
			if (endtime1 == null) {
				etime = 0;
			} else {
				etime = endtime1.getTime();
			}

			Iterator<LjflExercise> iterator = selectLjflExerciseInfoListTotal
					.iterator();

			while (iterator.hasNext()) {

				LjflExercise ljflExercise = iterator.next();

				// 获得开始时间
				Date starttime = ljflExercise.getStarttime();

				if (starttime != null) {

					timeing = starttime.getTime();
				} else {
					timeing = 0;
				}

				if (stime <= timeing && timeing <= etime) {

				} else {
					iterator.remove();
				}

			}

		}

		for (LjflExercise ljflExercise : selectLjflExerciseInfoListTotal) {

			// 获取fljflhousesid
			String fLjflhousesId = ljflExercise.getfLjflhousesId();
			
			AreaCommunity selectByPrimaryKey = areaService.selectByPrimaryKey(fLjflhousesId);
			
			String fName = selectByPrimaryKey.getCommunityname();
			
			// 获得id
			String id = ljflExercise.getId();
			// 获得活动名称
			String name = ljflExercise.getName();
			// 获得刷卡人数
			Integer peoplenum = ljflExercise.getPeoplenum();
			// 获得活动类型
			String exercisetypename = ljflExercise.getExercisetypename();
			// 获得活动可获得的积分
			Integer minscore = ljflExercise.getMinscore();
			// 获得开始时间
			Date starttime = ljflExercise.getStarttime();
			// 获得结束时间
			Date endtime2 = ljflExercise.getEndtime();

			LjflActivityModel ljflActivityModel = new LjflActivityModel();

			ljflActivityModel.setId(id);
			ljflActivityModel.setName(name);
			ljflActivityModel.setPeoplenum(peoplenum);
			ljflActivityModel.setFhousesname(fName);
			ljflActivityModel.setActivitytypename(exercisetypename);
			ljflActivityModel.setMinscore(minscore);
			ljflActivityModel.setStarttime(starttime);
			ljflActivityModel.setEndtime(endtime2);

			ljflActivity.add(ljflActivityModel);
		}

		LjflExercisePageDomain ljflExercisePageDomain = new LjflExercisePageDomain();

		ljflExercisePageDomain = exercisePageDomain;

		int startSize = exercisePageDomain.getPageSize()
				* (exercisePageDomain.getCurrentPage() - 1);
		List<LjflActivityModel> subList = null;
		if (startSize + 5 > ljflActivity.size()) {

			subList = ljflActivity.subList(startSize, ljflActivity.size());
		} else {

			subList = ljflActivity.subList(startSize, startSize + 5);
		}

		ljflExercisePageDomain.setContent(subList);

		ljflExercisePageDomain.setTotalNum(ljflActivity.size());

		System.out.println(ljflExercisePageDomain);

		return exercisePageDomain;
	}

	/**
	 * 进入新增现场活动页面
	 *//*
	@RequestMapping("addacitivity")
	public ModelAndView addactivity(HttpSession session) {

		ModelAndView mav = new ModelAndView("ljflscore/ljfladdactivityPage");
		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);

		List<AreaCommunity> selectareaCommunities = areaService
				.selectareaCommunities();

		System.out
				.println("==========================" + selectareaCommunities);

		System.out.println(selectareaCommunities);

		mav.addObject("housesname", selectareaCommunities);

		return mav;

	}*/

	/**
	 * 新增现场活动
	 * 
	 * @param jsonString
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insertactivityInfo")
	public Map<String, String> insertactivityInfo(@RequestBody String jsonString) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsonString);

		System.out.println(parseObject.toString());
		LjflExercise ljflExercise = new LjflExercise();

		String id = UUID.randomUUID().toString().replaceAll("-", "");
		ljflExercise.setId(id);
		Date date = new Date();
		System.out.println(date);
		ljflExercise.setCreatetime(date);
		ljflExercise.setLastchangetime(date);
		ljflExercise.setStatus(0);
		ljflExercise.setBeendeleted(0);
		ljflExercise.setTenantid("4eaf9423eb0043648e502134586b088a");
		ljflExercise.setEndtime(parseObject.getDate("endtimestr"));
		ljflExercise.setMinscore(parseObject.getInteger("score"));
		ljflExercise.setStarttime(parseObject.getDate("starttimestr"));
		ljflExercise.setName(parseObject.getString("name"));
		ljflExercise.setExercisetypename(parseObject
				.getString("exerciseTypeName"));
		ljflExercise.setExercisetypeid(parseObject.getString("exerciseTypeId"));
		System.out.println(parseObject.getString("exerciseTypeId"));
		ljflExercise.setPeoplenum(0);
		// 获取输入的小区名称
		String housesnameid = parseObject.getString("ljflHousesId");
		List<AreaCommunity> selectAreaCommunitsByName = areaService.selectAreaCommunitsByName(housesnameid);
		String housesholdid = selectAreaCommunitsByName.get(0).getId();
		
		ljflExercise.setfLjflhousesId(housesholdid);
		ljflExercise.setType(parseObject.getInteger("type"));

		int insert = ljflExerciseService.insert(ljflExercise);

		if (insert == 1) {
			map.put("message", "活动信息添加成功");
		} else {

			map.put("message", "信息添加失败");
		}

		return map;
	}

	/**
	 * 进入查看活动页面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("lookactivityData")
	public LjflActivityModel lookactivityData(@RequestBody String ids) {

		JSONObject json = JSONObject.parseObject(ids);
		String id = json.getString("id");

		LjflExercise ljflExercise = ljflExerciseService.selectByPrimaryKey(id);

		// 获取fljflhousesid
		String fLjflhousesId = ljflExercise.getfLjflhousesId();
		AreaCommunity selectByPrimaryKey = areaService.selectByPrimaryKey(fLjflhousesId);
		// 获取小区名称
		String fName = selectByPrimaryKey.getCommunityname();
		// 获得活动名称
		String name = ljflExercise.getName();
		// 获得刷卡人数
		Integer peoplenum = ljflExercise.getPeoplenum();
		// 获得活动类型
		String exercisetypename = ljflExercise.getExercisetypename();
		// 获得活动可获得的积分
		Integer minscore = ljflExercise.getMinscore();
		// 获得开始时间
		Date starttime = ljflExercise.getStarttime();
		// 获得结束时间
		Date endtime2 = ljflExercise.getEndtime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String st =sdf.format(starttime);
		String et = sdf.format(endtime2);
		
		System.out.println("starttime=="+st);
		System.out.println("endtime=="+et);
		
		// 获取活动类型
		Integer type = ljflExercise.getType();
		//String typename;
		/*if (type == 1) {

			typename = "区间分值";
		} else {

			typename = "固定分值";
		}*/

		LjflActivityModel ljflActivityModel = new LjflActivityModel();
		ljflActivityModel.setId(id);
		ljflActivityModel.setName(name);
		ljflActivityModel.setPeoplenum(peoplenum);
		ljflActivityModel.setFhousesname(fName);
		ljflActivityModel.setActivitytypename(exercisetypename);
		ljflActivityModel.setMinscore(minscore);
		ljflActivityModel.setSt(st);
		ljflActivityModel.setEt(et);
		ljflActivityModel.setScoretype(type.toString());

		System.out.println(ljflActivityModel.toString());
		
		return ljflActivityModel;

	}

	/**
	 * 进入修改活动页面
	 * 
	 * @param request
	 * @return
	 *//*
	@RequestMapping("updateactivityData")
	public ModelAndView updateactivityData(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("ljflscore/ljflupdateactivityPage");
		id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		System.out.println("num==" + id);

		LjflExercise ljflExercise = ljflExerciseService.selectByPrimaryKey(id);

		// 获取fljflhousesid
		String fLjflhousesId = ljflExercise.getfLjflhousesId();
		AreaCommunity selectByPrimaryKey = areaService.selectByPrimaryKey(fLjflhousesId);
		// 获取小区名称
		String fName = selectByPrimaryKey.getCommunityname();
		// 获得活动名称
		String name = ljflExercise.getName();
		// 获得刷卡人数
		Integer peoplenum = ljflExercise.getPeoplenum();
		// 获得活动类型
		String exercisetypename = ljflExercise.getExercisetypename();
		// 获得活动可获得的积分
		Integer minscore = ljflExercise.getMinscore();
		// 获得开始时间
		Date starttime = ljflExercise.getStarttime();
		// 获得结束时间
		Date endtime2 = ljflExercise.getEndtime();
		// 获取活动类型
		Integer type = ljflExercise.getType();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String st =sdf.format(starttime);
		String et = sdf.format(endtime2);
		
		LjflActivityModel ljflActivityModel = new LjflActivityModel();
		ljflActivityModel.setId(id);
		ljflActivityModel.setName(name);
		ljflActivityModel.setPeoplenum(peoplenum);
		ljflActivityModel.setFhousesname(fName);
		ljflActivityModel.setActivitytypename(exercisetypename);
		ljflActivityModel.setMinscore(minscore);
		ljflActivityModel.setSt(st);
		ljflActivityModel.setEt(et);
		ljflActivityModel.setScoretype(type.toString());

		List<AreaCommunity> selectareaCommunities = areaService
				.selectareaCommunities();

		System.out
				.println("==========================" + selectareaCommunities);

		System.out.println(selectareaCommunities);

		mav.addObject("housesname", selectareaCommunities);

		mav.addObject("activityinfo", ljflActivityModel);

		return mav;

	}
*/
	/**
	 * 保存修改后的现场活动
	 * 
	 * @param jsonString
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateactivityInfo")
	public Map<String, String> updateactivityInfo(@RequestBody String jsonString) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsonString);

		System.out.println(parseObject.toString());
		String id = parseObject.getString("id");
		LjflExercise ljflExercise = ljflExerciseService.selectByPrimaryKey(id);
		Date date = new Date();
		System.out.println(date);
		ljflExercise.setLastchangetime(date);
		ljflExercise.setEndtime(parseObject.getDate("endtimestr"));
		ljflExercise.setMinscore(parseObject.getInteger("score"));
		ljflExercise.setStarttime(parseObject.getDate("starttimestr"));
		ljflExercise.setName(parseObject.getString("name"));
		ljflExercise.setExercisetypename(parseObject
				.getString("exerciseTypeName"));

		// 获取输入的小区名称
		String housesnameid = parseObject.getString("ljflHousesId");
		List<AreaCommunity> selectAreaCommunitsByName = areaService.selectAreaCommunitsByName(housesnameid);
		String householdid = selectAreaCommunitsByName.get(0).getId();
		

		ljflExercise.setfLjflhousesId(householdid);
		ljflExercise.setType(parseObject.getInteger("type"));

		int updateByPrimaryKey = ljflExerciseService
				.updateByPrimaryKey(ljflExercise);

		if (updateByPrimaryKey == 1) {
			map.put("message", "修改信息成功");
		} else {

			map.put("message", "修改失败");
		}

		return map;
	}

	/**
	 * 删除活动
	 * 
	 * @param jsonString
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteactivityData")
	public Map<String, String> deleteactivityData(@RequestBody String jsonString) {

		System.out.println("jinll ");
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsonString);
		String id = parseObject.getString("id");

		System.out.println(id);
		
		LjflExercise selectByPrimaryKey = ljflExerciseService.selectByPrimaryKey(id);
		
		selectByPrimaryKey.setBeendeleted(1);
		
		int updateByPrimaryKey = ljflExerciseService.updateByPrimaryKey(selectByPrimaryKey);
		
		//int deleteByPrimaryKey = ljflExerciseService.deleteByPrimaryKey(id);

		if (updateByPrimaryKey == 1) {
		map.put("message", "删除活动成功");
		} else {

			map.put("message", "删除失败");
		}

		return map;

	}

	/**
	 * 获取居民的积分修改信息
	 * 
	 * @param scoreAmendPageDomain
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getscoreData")
	public LjflScoreAmendPageDomain getscoreData(
			@RequestBody LjflScoreAmendPageDomain scoreAmendPageDomain) {

		System.out.println("分页："+scoreAmendPageDomain);
		
		// 获取操作人
		String modifyUserName = scoreAmendPageDomain.getModifyUserName();

		List<LjflScoreAmendModel> list = new ArrayList<LjflScoreAmendModel>();

		List<LjflAccountModify> selectAccoountModityInfoList1;
		if (modifyUserName == null || modifyUserName == "") {

			selectAccoountModityInfoList1 = ljflaccountmodifyService
					.selectAccoountModityInfoList1();
		} else {

			selectAccoountModityInfoList1 = ljflaccountmodifyService
					.selectAccoountModityInfoList(modifyUserName);
		}

		// 获取所属人员
		String name = scoreAmendPageDomain.getName();
		if (name == null || name .equals("")) {
			
		}else{
			
			LjflStaffDetail ljflStaffDetail = ljflstaffdetailService.selectByPrimaryKey(name);
			
			
		
				// 获取人员id
				String id2 = ljflStaffDetail.getId();
				// 获取该人员的所有积分记录
				selectAccoountModityInfoList1 = ljflaccountmodifyService
						.selectAccoountModityInfoList2(id2);
			
		}
		System.out.println(selectAccoountModityInfoList1);

		for (LjflAccountModify ljflAccountModify : selectAccoountModityInfoList1) {

			String id2 = ljflAccountModify.getId();
			String getfStaffDetailId = ljflAccountModify.getfStaffDetailId();
			LjflStaffDetail selectByPrimaryKey = ljflstaffdetailService.selectByPrimaryKey(getfStaffDetailId);
			
			String name2;
			try {
				name2 = selectByPrimaryKey.getName();
			} catch (Exception e) {
				
				name2="";
				
			}
			
			Double getfAddScore = ljflAccountModify.getfAddScore();
			String getfModifyUserName = ljflAccountModify.getfModifyUserName();
			String getfMemo = ljflAccountModify.getfMemo();
			
			LjflScoreAmendModel ljflScoreAmendModel = new LjflScoreAmendModel();
			ljflScoreAmendModel.setId(id2);
			ljflScoreAmendModel.setName(name2);
			ljflScoreAmendModel.setfAddScore(getfAddScore);
			ljflScoreAmendModel.setfModifyUserName(getfModifyUserName);
			ljflScoreAmendModel.setfMemo(getfMemo);
			
			list.add(ljflScoreAmendModel);
		}
		
		
		LjflScoreAmendPageDomain ljflScoreAmendPageDomain = new LjflScoreAmendPageDomain();

		ljflScoreAmendPageDomain = scoreAmendPageDomain;

		int startSize = scoreAmendPageDomain.getPageSize()
				* (scoreAmendPageDomain.getCurrentPage() - 1);
		List<LjflScoreAmendModel> subList = null;
		if (startSize + 10 > list.size()) {

			subList = list.subList(startSize, list.size());
		} else {

			subList = list.subList(startSize, startSize + 10);
		}

		ljflScoreAmendPageDomain.setContent(subList);

		ljflScoreAmendPageDomain.setTotalNum(list.size());

		System.out.println(ljflScoreAmendPageDomain);
		

		return ljflScoreAmendPageDomain;
	}

	/**
	 * 进入查看积分信息页面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("lookscoreinfoData")
	public LjflAccountModify lookscoreinfoData(@RequestBody String ids) {


		JSONObject parseObject = JSONObject.parseObject(ids);
		String id = parseObject.getString("id");

	
		
		LjflAccountModify selectByPrimaryKey3 = ljflaccountmodifyService
				.selectByPrimaryKey(id);
		String getfStaffDetailId = selectByPrimaryKey3.getfStaffDetailId();
		LjflStaffDetail selectByPrimaryKey = ljflstaffdetailService.selectByPrimaryKey(getfStaffDetailId);
		String name = selectByPrimaryKey.getName();
		
		selectByPrimaryKey3.setfStaffDetailId(name);
		
		System.out.println(selectByPrimaryKey3.toString());

		return selectByPrimaryKey3;

	}
	
	/**
	 *进入积分增减页面 
	 * @return
	 *//*
	@RequestMapping("addscoreaccountPage")
	public ModelAndView addscoreaccountPage(){
		
		ModelAndView mav = new ModelAndView("ljflscore/ljfladdscoreaccountPage");
		//ModelAndView mav = new ModelAndView("ljflscore/ljflScoreDatePage");
		
		
		return mav;
	}*/
	
	
	/**
	 * 获取居民总数据
	 *//*
	@ResponseBody
	@RequestMapping(value = "/getStaffDetailData", method = RequestMethod.POST)
	public PageModelDomain getStaffDetailData(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
		if(pageModelDomain.getStaffName() ==null || pageModelDomain.getStaffName().isEmpty()){
			pageModelDomain.setStaffName(null);
		}
		int size  = ljflstaffdetailService.selecStaffDetailtTotalSize(pageModelDomain);
		page.setPageTotalNum(size);
		pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
	
		int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
		
		if(size <= endNum){
			pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
		}else{
			pageModelDomain.setEndNum(pageModelDomain.getPageSize());
		}
		List<LjflStaffDetail> staffDetailList = ljflstaffdetailService.selectStaffDetailList(pageModelDomain);

		page.setStaffDetailList(staffDetailList);
		return page;
		
	}*/
	
	
	@ResponseBody
	@RequestMapping("insertscoreaccountInfo")
	public Map<String, String> insertscoreaccountInfo(@RequestBody String jsondate,HttpSession session){
		
		Map<String, String> map = new HashMap<String, String>();
		
		JSONObject parseObject = JSONObject.parseObject(jsondate);
		
		
		String staffName = parseObject.getString("staffName");
		String memo = parseObject.getString("memo");
		String addBalance = parseObject.getString("addBalance");
		String staffDetailId = parseObject.getString("staffDetailId");
		System.out.println(staffDetailId);
		System.out.println(staffName);
		System.out.println(memo);
		
		Double addBalance1 = Double.valueOf(addBalance);		
		Date date = new Date();
		System.out.println(addBalance1);
		
		
		String userid = session.getAttribute("userid").toString();
		UserInfo selectByPrimaryKey2 = userService.selectByPrimaryKey(userid);
		
		LjflAccountModify ljflAccountModify = new LjflAccountModify();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		ljflAccountModify.setCreatetime(date);
		ljflAccountModify.setLastchangetime(date);
		ljflAccountModify.setBeendeleted(0);
		ljflAccountModify.setStatus(0);
		ljflAccountModify.setId(id);
		ljflAccountModify.setfStaffDetailId(staffDetailId);
		ljflAccountModify.setfMemo(memo);
		ljflAccountModify.setfAddScore(addBalance1);
		ljflAccountModify.setTenantid("4eaf9423eb0043648e502134586b088a");
		ljflAccountModify.setfModifyUserName(selectByPrimaryKey2.getUsername());
		ljflAccountModify.setfModifyUserId(userid);
		
		int insert = ljflaccountmodifyService.insert(ljflAccountModify);
		
		if(insert==1){
			
			LjflStaffDetail selectByPrimaryKey = ljflstaffdetailService.selectByPrimaryKey(staffDetailId);
			Double getfCurrentScore = selectByPrimaryKey.getfCurrentScore();
			BigDecimal b1 = new BigDecimal(addBalance1);
			  BigDecimal b2 = new BigDecimal(getfCurrentScore);
			  double doubleValue = b2.add(b1).doubleValue();
			  selectByPrimaryKey.setfCurrentScore(doubleValue);
			  Double getfTotalScore = selectByPrimaryKey.getfTotalScore();
			  BigDecimal b3 = new BigDecimal(getfTotalScore);
			  double doubleValue2 = b3.add(b1).doubleValue();
			  selectByPrimaryKey.setfTotalScore(doubleValue2);
			  
			  int updateByPrimaryKey = ljflstaffdetailService.updateByPrimaryKey(selectByPrimaryKey);
			  
			  if(updateByPrimaryKey==1){
				  
				  map.put("message","添加成功");
			  }else{
				  
				  map.put("message", "添加失败");				  
			  }
			  
		}else{
			
			map.put("message", "添加失败");
		}
		
		return map;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("ljflScoreDatePage")
	public ModelAndView ljflScoreDatePage(HttpSession session){
		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("ljflscore/ljflScoreDatePage");
		mav.addObject("userId", userId);
		return mav;
		
	}
/*	@RequestMapping("ljflScoreDetailDatePage")
	public ModelAndView ljflScoreDetailDatePage(HttpSession session){
		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("ljflscore/ljflScoreDetailDatePage");
		mav.addObject("userId", userId);
		return mav;
		
	}*/
	/**
	 * 获取居民总数据
	 */
	@ResponseBody
	@RequestMapping(value = "/getStaffDetailData", method = RequestMethod.POST)
	public PageModelDomain getStaffDetailData(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
		if(pageModelDomain.getStaffName() ==null || pageModelDomain.getStaffName().isEmpty()){
			pageModelDomain.setStaffName(null);
		}
		int size  = ljflScoreService.selecStaffDetailtTotalSize(pageModelDomain);
		page.setPageTotalNum(size);
		pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
	
		int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
		
		if(size <= endNum){
			pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
		}else{
			pageModelDomain.setEndNum(pageModelDomain.getPageSize());
		}
		List<LjflStaffDetail> staffDetailList = ljflScoreService.selectStaffDetailList(pageModelDomain);

		page.setStaffDetailList(staffDetailList);
		return page;
		
	}
	/**
	 * 查询居民日积分
	 */
	@ResponseBody
	@RequestMapping(value = "/queryScoreRecord", method = RequestMethod.POST)
	public PageModelDomain queryScoreRecord(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
		if(pageModelDomain.getStaffDetailId() ==null || pageModelDomain.getStaffDetailId().isEmpty()){//居民id
			pageModelDomain.setStaffDetailId(null);
		}
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getF_add_type() ==null || pageModelDomain.getF_add_type().isEmpty()){
			pageModelDomain.setF_add_type(null);
		}
		int size = 0;//查询的所有条数
		List<LjflScoreRecord> ljflScoreRecordlist = new ArrayList<LjflScoreRecord>();
		/**
		 * 初始化数据
		 *没有起始时间
		 */
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		String chaxunTime = dateNowStr;//查询时间
//		String startTime = "2018-01-02 00:00:00";
//		String endTime = "2018-01-02 23:59:59";
		String startTime = dateNowStr.substring(0, 10)+" 00:00:00";
		String endTime = dateNowStr.substring(0, 10)+" 23:59:59";
		int pageIsSize = pageModelDomain.getPageSize();
		if(pageModelDomain.getStartTime() == null){
			String tableName = "";
			try {
				tableName = getTableNameString("ljfl_score_record", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflScoreService.selecScoreByDateTotalSize(pageModelDomain);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			ljflScoreRecordlist = ljflScoreService.selecScoreByDateTotal(pageModelDomain);
		}else{
			/**
			 * 1.先计算有多少个表，将所有的表的size查出来
			 */
			String startDate = pageModelDomain.getStartTime();
			String endDate = pageModelDomain.getEndTime();
//			int currentNum =  (pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize();//此次获取的第一个数据
//			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();//此次获取的可能性最后一个数据
//			
			
			List<String> list = new ArrayList<String>();
			try {
				list = getTableName("ljfl_score_record",startDate,endDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<DivisionTable> tableList = new ArrayList<DivisionTable>();
			
			/**
			 * 遍历每个表有多少数据
			 */
			for (String str : list) {
				DivisionTable division = new DivisionTable();
				division.setTableName(str);
				
				pageModelDomain.setTableName(str);
				int datesize =ljflScoreService.selecScoreByDateTotalSize(pageModelDomain);
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			/**
			 * 获取数据
			 */
			int currentForMiniNum =(pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize();//当前循环的最小size
//			int currentMaxNum = (pageModelDomain.getCurrentPage())*pageModelDomain.getPageSize()<=size?(pageModelDomain.getCurrentPage())*pageModelDomain.getPageSize():size;//当前循环的最大size
			int upTotalSize = 0;//上一个表累加后所包含的最大数据
			int currentTableSize = 0;//当前表所包含的最大数据
			int pageSize = pageModelDomain.getPageSize();
			int residuePageSize = pageSize;//剩余的page
			int pageneedSize = pageSize;
			boolean isNextTable = false;
			boolean istheNextTable = false;
			for (int i = 0; i < tableList.size(); i++) {
				pageModelDomain.setPageSize(pageSize);
				if(ljflScoreRecordlist.size()>=pageneedSize){
					break;
				}
				List<LjflScoreRecord> forScoreRecordList = new ArrayList<LjflScoreRecord>();
				DivisionTable divisionTable = tableList.get(i);
				pageModelDomain.setTableName(divisionTable.getTableName());
				upTotalSize = currentTableSize;
				currentTableSize = currentTableSize+divisionTable.getSize();
				if(currentTableSize <=currentForMiniNum){//如果当前表的累加数据，小于当前最小值，则跳过
					continue;
				}
				if(divisionTable.getSize() == 0){
					continue;
				}
				int currenrRealNum = currentForMiniNum- upTotalSize;
				if(currenrRealNum<= 0 ){
					currenrRealNum = 0 ;
				}
				int currentPageSize = 0 ;
				if(residuePageSize<(currentTableSize - currentForMiniNum )){
					currentPageSize =residuePageSize;
				}else{
					currentPageSize = pageneedSize - ljflScoreRecordlist.size();
					if(currentPageSize>(divisionTable.getSize()-currenrRealNum)){
						currentPageSize = (divisionTable.getSize()-currenrRealNum);
					}
					residuePageSize = pageSize - currentPageSize;
					isNextTable = true;
				}
				if(istheNextTable){
					pageModelDomain.setCurrentNum(0);
				}else{
					pageModelDomain.setCurrentNum(currenrRealNum);
				}
				pageModelDomain.setPageSize(currentPageSize);
				
				forScoreRecordList = ljflScoreService.selecScoreByDateTotal(pageModelDomain);
				ljflScoreRecordlist.addAll(forScoreRecordList);
				if(isNextTable){
					istheNextTable = true;
					pageModelDomain.setPageSize(residuePageSize);
				}

			}
			
		}
		int num = 0;
		List<LjflScoreRecord> list = new ArrayList<LjflScoreRecord>();
		for (LjflScoreRecord ljflScoreRecord : ljflScoreRecordlist) {
			num++;
			if(num>pageIsSize){
				break;
			}
			LjflStaffDetail staff = ljflScoreService.selectStaffDetailById(ljflScoreRecord.getfStaffDetailId());
			ljflScoreRecord.setfStaffDetailId(staff.getName());
			list.add(ljflScoreRecord);
		}
		page.setPageTotalNum(size);
		page.setScoreRecordList(ljflScoreRecordlist);
		return page;
		
	}
	/**
	 * 导出居民需要的积分
	 */
	
	public PageModelDomain getLjflScoreExportExcel(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
		if(pageModelDomain.getStaffDetailId() ==null || pageModelDomain.getStaffDetailId().isEmpty()){//居民id
			pageModelDomain.setStaffDetailId(null);
		}
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getF_add_type() ==null || pageModelDomain.getF_add_type().isEmpty()){
			pageModelDomain.setF_add_type(null);
		}
		int size = 0;//查询的所有条数
		List<LjflScoreRecord> ljflScoreRecordlist = new ArrayList<LjflScoreRecord>();
		/**
		 * 初始化数据
		 *没有起始时间
		 */
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		String chaxunTime = dateNowStr;//查询时间
//		String startTime = "2018-01-02 00:00:00";
//		String endTime = "2018-01-02 23:59:59";
		String startTime = dateNowStr.substring(0, 10)+" 00:00:00";
		String endTime = dateNowStr.substring(0, 10)+" 23:59:59";
		if(pageModelDomain.getStartTime() == null){
			String tableName = "";
			try {
				tableName = getTableNameString("ljfl_score_record", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflScoreService.selecScoreByDateTotalSize(pageModelDomain);

			ljflScoreRecordlist = ljflScoreService.selecScoreByDateTotal(pageModelDomain);
		}else{
			/**
			 * 1.先计算有多少个表，将所有的表的size查出来
			 */
			String startDate = pageModelDomain.getStartTime();
			String endDate = pageModelDomain.getEndTime();
//			int currentNum =  (pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize();//此次获取的第一个数据
//			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();//此次获取的可能性最后一个数据
//			
			
			List<String> list = new ArrayList<String>();
			try {
				list = getTableName("ljfl_score_record",startDate,endDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<DivisionTable> tableList = new ArrayList<DivisionTable>();
			
			/**
			 * 遍历每个表有多少数据
			 */
			for (String str : list) {
				DivisionTable division = new DivisionTable();
				division.setTableName(str);
				
				pageModelDomain.setTableName(str);
				int datesize =ljflScoreService.selecScoreByDateTotalSize(pageModelDomain);
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			/**
			 * 获取数据
			 */
			for (int i = 0; i < tableList.size(); i++) {
				DivisionTable divisionTable = tableList.get(i);
				
				List<LjflScoreRecord> forScoreRecordList = new ArrayList<LjflScoreRecord>();
			
				pageModelDomain.setTableName(divisionTable.getTableName());
					
				forScoreRecordList = ljflScoreService.selecScoreByDateTotal(pageModelDomain);
				ljflScoreRecordlist.addAll(forScoreRecordList);
			}
			
		}
		List<LjflScoreRecord> list = new ArrayList<LjflScoreRecord>();
		for (LjflScoreRecord ljflScoreRecord : ljflScoreRecordlist) {
			if(ljflScoreRecord.getfAddType().equals("swingCard")){
				ljflScoreRecord.setfAddType("垃圾投放");
			}
			if(ljflScoreRecord.getfAddType().equals("activity")){
				ljflScoreRecord.setfAddType("活动");
			}
			if(ljflScoreRecord.getfAddType().equals("recycle")){
				ljflScoreRecord.setfAddType("回收");
			}
			if(ljflScoreRecord.getfAddType().equals("accountmodify")){
				ljflScoreRecord.setfAddType("积分调整");
			}
			if(ljflScoreRecord.getfAddType().equals("prizeProvide")){
				ljflScoreRecord.setfAddType("奖品发放");
			}
			Date time = ljflScoreRecord.getfScoreTime();
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			ljflScoreRecord.setfAddObjectId(dateFormater.format(time));
			LjflStaffDetail staff = ljflScoreService.selectStaffDetailById(ljflScoreRecord.getfStaffDetailId());
			ljflScoreRecord.setfStaffDetailId(staff.getName());
			list.add(ljflScoreRecord);
		}
		page.setPageTotalNum(size);
		page.setScoreRecordList(ljflScoreRecordlist);
		return page;
		
	}
	/**
	 * 导出excel数据文件(居民时间段投放记录接口查询)
	 * 
	 * @param pageModel
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
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String staffDetailId = request.getParameter("staffDetailId");
		String f_add_type = request.getParameter("f_add_type");
		pageModel.setStartTime(startTime);
		pageModel.setEndTime(endTime);
	

		pageModel.setStaffDetailId(staffDetailId);
		pageModel.setF_add_type(f_add_type);
		PageModelDomain page = getLjflScoreExportExcel(pageModel);
		/**
		 * 获取到数据
		 */
		List<LjflScoreRecord> scoreRecordList = page.getScoreRecordList();

		// 填充Objects数据
		// List<Object> Objects=createData(request);
		List<Map<String, Object>> list = createExcelRecord(scoreRecordList);
		String columnNames[] = { "id", "居民", "增加或减少的积分", "积分时间", "来源类型", "应该增加或减少的积分" };// 列名
		String keys[] = { "id", "staffName", "fAddScore", "fScoreTime", "fAddType",
				"fAddScoreInTheory" };// map中的key
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
		String fileName = "居民时间段投放记录表";
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
	
	private List<Map<String, Object>> createExcelRecord(List<LjflScoreRecord> scoreRecordList) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "居民时间段投放记录表");
		listmap.add(map);
		LjflScoreRecord scoreRecord = null;
		for (int j = 0; j < scoreRecordList.size(); j++) {
			scoreRecord = scoreRecordList.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("id", scoreRecord.getId());
			mapValue.put("staffName", scoreRecord.getfStaffDetailId());
			mapValue.put("fAddScore", scoreRecord.getfAddScore());
			mapValue.put("fScoreTime", scoreRecord.getfAddObjectId());
			mapValue.put("fAddType", scoreRecord.getfAddType());
			mapValue.put("fAddScoreInTheory",scoreRecord.getfAddScoreInTheory());
			listmap.add(mapValue);
		}
		return listmap;
		
	}

	/*
	 * 获取动态数据库表名集合
	 */
	public static List<String> getTableName(String tableName, String startTime, String endTime) throws Exception {
		List<String> list = new ArrayList<String>();
		Date d1 = new SimpleDateFormat("yyyy-MM").parse(startTime.substring(0, 7));// 定义起始日期
		Date d2 = new SimpleDateFormat("yyyy-MM").parse(endTime.substring(0, 7));// 定义结束日期
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d2);
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		d2 = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		Calendar dd = Calendar.getInstance();// 定义日期实例
		dd.setTime(d1);// 设置日期起始时间
		while (dd.getTime().before(d2)) {// 判断是否到结束日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String str = sdf.format(dd.getTime());
			list.add(tableName + str);

			dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1搜索

		}
		return list;
	}
	/*
	 * 获取动态数据库表名
	 */
	public static String getTableNameString(String tableName, String endTime) throws Exception {

		Date d2 = new SimpleDateFormat("yyyy-MM").parse(endTime.substring(0, 7));// 定义日期

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d2);
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		d2 = calendar.getTime(); // 这个时间就是日期往后推一天的结果

		Calendar dd = Calendar.getInstance();// 定义日期实例

		dd.setTime(d2);// 设置日期起始时间

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

		String str = sdf.format(dd.getTime());

		tableName = tableName + str;

		return tableName;
	}
	/**
	 * 查询现场活动记录
	 */
	@ResponseBody
	@RequestMapping(value = "/queryScoreDetail", method = RequestMethod.POST)
	public PageModelDomain queryScoreDetail(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
		if(pageModelDomain.getStaffDetailId() ==null || pageModelDomain.getStaffDetailId().isEmpty()){//居民id
			pageModelDomain.setStaffDetailId(null);
		}
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getLjflExerciseId() == null || pageModelDomain.getLjflExerciseId().isEmpty()){
			pageModelDomain.setLjflExerciseId(null);
		}
		int size = 0;//查询的所有条数
		List<LjflScoreDetail> ljflScoreDetaillist = new ArrayList<LjflScoreDetail>();
		/**
		 * 初始化数据
		 *没有起始时间
		 */
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		String chaxunTime = dateNowStr;//查询时间
		String startTime = "2018-01-02 00:00:00";
		String endTime = "2018-01-02 23:59:59";
//		String startTime = dateNowStr.substring(0, 10)+" 00:00:00";
//		String endTime = dateNowStr.substring(0, 10)+" 23:59:59";
		int pageisSize  = pageModelDomain.getPageSize();
		if(pageModelDomain.getStartTime() == null){
			String tableName = "";
			try {
				tableName = getTableNameString("ljfl_score_detail", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflScoreService.selecScoreDetailByDateTotalSize(pageModelDomain);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			ljflScoreDetaillist = ljflScoreService.selecScoreDetailByDateTotal(pageModelDomain);
		}else{
			/**
			 * 1.先计算有多少个表，将所有的表的size查出来
			 */
			String startDate = pageModelDomain.getStartTime();
			String endDate = pageModelDomain.getEndTime();
//			int currentNum =  (pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize();//此次获取的第一个数据
//			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();//此次获取的可能性最后一个数据
//			
//			
			List<String> list = new ArrayList<String>();
			try {
				list = getTableName("ljfl_score_detail",startDate,endDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<DivisionTable> tableList = new ArrayList<DivisionTable>();
			
			/**
			 * 遍历每个表有多少数据
			 */
			for (String str : list) {
				DivisionTable division = new DivisionTable();
				division.setTableName(str);
				
				pageModelDomain.setTableName(str);
				int datesize =ljflScoreService.selecScoreDetailByDateTotalSize(pageModelDomain);
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			/**
			 * 获取数据
			 */
			
			int currentForMiniNum =(pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize();//当前循环的最小size
//			int currentMaxNum = (pageModelDomain.getCurrentPage())*pageModelDomain.getPageSize()<=size?(pageModelDomain.getCurrentPage())*pageModelDomain.getPageSize():size;//当前循环的最大size
			int upTotalSize = 0;//上一个表累加后所包含的最大数据
			int currentTableSize = 0;//当前表所包含的最大数据
			int pageSize = pageModelDomain.getPageSize();
			int residuePageSize = pageSize;//剩余的page
			int pageneedSize = pageSize;
			boolean isNextTable = false;
			boolean istheNextTable = false;
			for (int i = 0; i < tableList.size(); i++) {
				pageModelDomain.setPageSize(pageSize);
				if(ljflScoreDetaillist.size()>=pageneedSize){
					break;
				}
				List<LjflScoreDetail> forScoreDetailList = new ArrayList<LjflScoreDetail>();
				DivisionTable divisionTable = tableList.get(i);
				pageModelDomain.setTableName(divisionTable.getTableName());
				upTotalSize = currentTableSize;
				currentTableSize = currentTableSize+divisionTable.getSize();
				if(currentTableSize <=currentForMiniNum){//如果当前表的累加数据，小于当前最小值，则跳过
					continue;
				}
				if(divisionTable.getSize() == 0){
					continue;
				}
				int currenrRealNum = currentForMiniNum- upTotalSize;
				if(currenrRealNum<= 0 ){
					currenrRealNum = 0 ;
				}
				int currentPageSize = 0 ;
				if(residuePageSize<(currentTableSize - currentForMiniNum )){
					currentPageSize =residuePageSize;
				}else{
					currentPageSize = pageneedSize - ljflScoreDetaillist.size();
					if(currentPageSize>(divisionTable.getSize()-currenrRealNum)){
						currentPageSize = (divisionTable.getSize()-currenrRealNum);
					}
					residuePageSize = pageSize - currentPageSize;
					isNextTable = true;
				}
				if(istheNextTable){
					pageModelDomain.setCurrentNum(0);
				}else{
					pageModelDomain.setCurrentNum(currenrRealNum);
				}
				pageModelDomain.setPageSize(currentPageSize);
				
				forScoreDetailList = ljflScoreService.selecScoreDetailByDateTotal(pageModelDomain);//直接获取当前数据库表的数据
				ljflScoreDetaillist.addAll(forScoreDetailList);
				if(isNextTable){
					istheNextTable = true;
					pageModelDomain.setPageSize(residuePageSize);
				}

			}
			
		}
		List<LjflScoreDetail> list = new ArrayList<LjflScoreDetail>();
		int num = 0;
		for (LjflScoreDetail ljflScoreDetail : ljflScoreDetaillist) {
			num++;
			if(num>pageisSize){
				break;
			}
			LjflStaffDetail staff = ljflScoreService.selectStaffDetailById(ljflScoreDetail.getStaffdetailid());
			LjflExercise exercise = ljflExerciseService.selectByPrimaryKey(ljflScoreDetail.getLjflexerciseid());
			ljflScoreDetail.setStaffdetailid(staff.getName());
			ljflScoreDetail.setLjflexerciseid(exercise.getName());
			list.add(ljflScoreDetail);
		}
		page.setPageTotalNum(size);
		page.setScoreDetailList(list);
		return page;
		
	}
	/**
	 * 查询现场活动
	 * 
	 * @param exercisePageDomain
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("getactivityData")
	public LjflExercisePageDomain getactivityData(
			@RequestBody LjflExercisePageDomain exercisePageDomain) {

		System.out.println("进啊咧咧");
		*//**
		 * 1.没有任何筛选条件 2.只有活动名称 3.只有所属小区 4.只有活动开始时间 5.只有活动结束时间
		 *//*
		List<LjflActivityModel> ljflActivity = new ArrayList<LjflActivityModel>();
		List<LjflExercise> selectLjflExerciseInfoListTotal;
		// 获取条件活动名称
		String activityname = exercisePageDomain.getName();
		System.out.println(activityname);
		if (activityname ==null || activityname == "") {
			// 根据活动名称筛选
			// 获取ljflexercise数据
			selectLjflExerciseInfoListTotal = ljflExerciseService
					.selectLjflExerciseInfoListTotal1();
		} else {

			selectLjflExerciseInfoListTotal = ljflExerciseService
					.selectLjflExerciseInfoListTotal(activityname);
		}

		// 获得条件小区
		String ljflHousesname = exercisePageDomain.getLjflHousesId();
		if (ljflHousesname != "") {
			System.out.println("dgs:" + selectLjflExerciseInfoListTotal);

			Iterator<LjflExercise> iterator = selectLjflExerciseInfoListTotal
					.iterator();

			while (iterator.hasNext()) {

				LjflExercise ljflExercise = iterator.next();
				System.out.println(ljflExercise);
				// 获取fljflhousesid
				String fLjflhousesId = ljflExercise.getfLjflhousesId();
				// 获取对应的ljflhouses的数据
				LjflHouses selectByPrimaryKey = ljflHousesService
						.selectByPrimaryKey(fLjflhousesId);
				// 获取ljflbaseid
				String fLjflBaseId = selectByPrimaryKey.getfLjflBaseId();
				// 获取对应的ljflbase数据
				LjflBase selectByPrimaryKey2 = ljflBaseService
						.selectByPrimaryKey(fLjflBaseId);
				// 获取小区名称
				String fName = selectByPrimaryKey2.getfName();
				if (fName.equals(ljflHousesname)) {

				} else {
					iterator.remove();
				}

			}
		} else {

		}

		// 获得条件开始时间
		String startTime = exercisePageDomain.getStartTime();
		// 获得条件结束时间
		String endtime = exercisePageDomain.getEndtime();

		// 判断是否有时间删选条件
		if (startTime != "" && endtime != "") {
			Date starttime1 = null;
			Date endtime1 = null;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				starttime1 = sdf.parse(startTime);
				endtime1 = sdf.parse(endtime);

			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}

			long stime;
			long timeing;
			long etime;
			if (starttime1 == null) {
				stime = 0;
			} else {
				stime = starttime1.getTime();
			}
			if (endtime1 == null) {
				etime = 0;
			} else {
				etime = endtime1.getTime();
			}

			Iterator<LjflExercise> iterator = selectLjflExerciseInfoListTotal
					.iterator();

			while (iterator.hasNext()) {

				LjflExercise ljflExercise = iterator.next();

				// 获得开始时间
				Date starttime = ljflExercise.getStarttime();

				if (starttime != null) {

					timeing = starttime.getTime();
				} else {
					timeing = 0;
				}

				if (stime <= timeing && timeing <= etime) {

				} else {
					iterator.remove();
				}

			}

		}

		for (LjflExercise ljflExercise : selectLjflExerciseInfoListTotal) {

			// 获取fljflhousesid
			String fLjflhousesId = ljflExercise.getfLjflhousesId();
			// 获取对应的ljflhouses的数据
			LjflHouses selectByPrimaryKey = ljflHousesService
					.selectByPrimaryKey(fLjflhousesId);
			// 获取ljflbaseid
			String fLjflBaseId = selectByPrimaryKey.getfLjflBaseId();
			// 获取对应的ljflbase数据
			LjflBase selectByPrimaryKey2 = ljflBaseService
					.selectByPrimaryKey(fLjflBaseId);
			// 获取小区名称
			String fName = selectByPrimaryKey2.getfName();
			// 获得id
			String id = ljflExercise.getId();
			// 获得活动名称
			String name = ljflExercise.getName();
			// 获得刷卡人数
			Integer peoplenum = ljflExercise.getPeoplenum();
			// 获得活动类型
			String exercisetypename = ljflExercise.getExercisetypename();
			// 获得活动可获得的积分
			Integer minscore = ljflExercise.getMinscore();
			// 获得开始时间
			Date starttime = ljflExercise.getStarttime();
			// 获得结束时间
			Date endtime2 = ljflExercise.getEndtime();

			LjflActivityModel ljflActivityModel = new LjflActivityModel();

			ljflActivityModel.setId(id);
			ljflActivityModel.setName(name);
			ljflActivityModel.setPeoplenum(peoplenum);
			ljflActivityModel.setFhousesname(fName);
			ljflActivityModel.setActivitytypename(exercisetypename);
			ljflActivityModel.setMinscore(minscore);
			ljflActivityModel.setStarttime(starttime);
			ljflActivityModel.setEndtime(endtime2);

			ljflActivity.add(ljflActivityModel);
		}

		LjflExercisePageDomain ljflExercisePageDomain = new LjflExercisePageDomain();

		ljflExercisePageDomain = exercisePageDomain;

		int startSize = exercisePageDomain.getPageSize()
				* (exercisePageDomain.getCurrentPage() - 1);
		List<LjflActivityModel> subList = null;
		if (startSize + 5 > ljflActivity.size()) {

			subList = ljflActivity.subList(startSize, ljflActivity.size());
		} else {

			subList = ljflActivity.subList(startSize, startSize + 5);
		}

		ljflExercisePageDomain.setContent(subList);

		ljflExercisePageDomain.setTotalNum(ljflActivity.size());

		System.out.println(ljflExercisePageDomain);

		return exercisePageDomain;
	}*/
	/**
	 * 导出excel数据文件(活动积分查询)
	 * 
	 * @param pageModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportScoreDetailExcel")
	public String exportScoreDetailExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		PageModelDomain pageModel = new PageModelDomain();
		/**
		 * 封装PageModelDomain
		 */
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String staffDetailId = request.getParameter("staffDetailId");
		String ljflExerciseId = request.getParameter("ljflExerciseId");
		pageModel.setStartTime(startTime);
		pageModel.setEndTime(endTime);
	

		pageModel.setStaffDetailId(staffDetailId);
		pageModel.setLjflExerciseId(ljflExerciseId);
		//PageModelDomain page = getLjflScoreExportExcel(pageModel);
		PageModelDomain page = getLjflScoreDetailExportExcel(pageModel);
		/**
		 * 获取到数据
		 */
		List<LjflScoreDetail> scoreDetailList = page.getScoreDetailList();

		// 填充Objects数据
		// List<Object> Objects=createData(request);
		List<Map<String, Object>> list = createExcelDetail(scoreDetailList);
		String columnNames[] = { "id", "居民姓名", "所属活动", "积分分值", "积分时间" };// 列名
		String keys[] = { "id", "staffName", "ljflexerciseid", "score", "createtime"};// map中的key
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
		String fileName = "活动积分表";
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
	private List<Map<String, Object>> createExcelDetail(List<LjflScoreDetail> scoreDetailList) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "活动积分表");
		listmap.add(map);
		LjflScoreDetail scoreDetail = null;
		for (int j = 0; j < scoreDetailList.size(); j++) {
			scoreDetail = scoreDetailList.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("id", scoreDetail.getId());
			mapValue.put("staffName", scoreDetail.getStaffdetailid());
			mapValue.put("ljflexerciseid", scoreDetail.getLjflexerciseid());
			mapValue.put("score",scoreDetail.getScore());
			mapValue.put("createtime", scoreDetail.getMemo());
			listmap.add(mapValue);
		}
		return listmap;
	}
	private PageModelDomain getLjflScoreDetailExportExcel(PageModelDomain pageModelDomain) {
		PageModelDomain page = new PageModelDomain();
		if(pageModelDomain.getStaffDetailId() ==null || pageModelDomain.getStaffDetailId().isEmpty()){//居民id
			pageModelDomain.setStaffDetailId(null);
		}
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getLjflExerciseId() ==null || pageModelDomain.getLjflExerciseId().isEmpty()){
			pageModelDomain.setLjflExerciseId(null);
		}
		int size = 0;//查询的所有条数
		List<LjflScoreDetail> ljflScoreDetaillist = new ArrayList<LjflScoreDetail>();
		/**
		 * 初始化数据
		 *没有起始时间
		 */
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		String chaxunTime = dateNowStr;//查询时间

		String startTime = dateNowStr.substring(0, 10)+" 00:00:00";
		String endTime = dateNowStr.substring(0, 10)+" 23:59:59";
		if(pageModelDomain.getStartTime() == null){
			String tableName = "";
			try {
				tableName = getTableNameString("ljfl_score_detail", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflScoreService.selecScoreByDateTotalSize(pageModelDomain);

			ljflScoreDetaillist = ljflScoreService.selecScoreDetailByDateTotal(pageModelDomain);
		}else{
			/**
			 * 1.先计算有多少个表，将所有的表的size查出来
			 */
			String startDate = pageModelDomain.getStartTime();
			String endDate = pageModelDomain.getEndTime();
//			int currentNum =  (pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize();//此次获取的第一个数据
//			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();//此次获取的可能性最后一个数据
//			
			
			List<String> list = new ArrayList<String>();
			try {
				list = getTableName("ljfl_score_detail",startDate,endDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<DivisionTable> tableList = new ArrayList<DivisionTable>();
			
			/**
			 * 遍历每个表有多少数据
			 */
			for (String str : list) {
				DivisionTable division = new DivisionTable();
				division.setTableName(str);
				
				pageModelDomain.setTableName(str);
				int datesize =ljflScoreService.selecScoreDetailByDateTotalSize(pageModelDomain);
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			/**
			 * 获取数据
			 */
			for (int i = 0; i < tableList.size(); i++) {
				DivisionTable divisionTable = tableList.get(i);
				
				List<LjflScoreDetail> forScoreDetailList = new ArrayList<LjflScoreDetail>();
			
				pageModelDomain.setTableName(divisionTable.getTableName());
					
				forScoreDetailList = ljflScoreService.selecScoreDetailByDateTotal(pageModelDomain);
				ljflScoreDetaillist.addAll(forScoreDetailList);
			}
			
		}
		List<LjflScoreDetail> list = new ArrayList<LjflScoreDetail>();
		for (LjflScoreDetail ljflScoreDetail : ljflScoreDetaillist) {
			LjflExercise exercise = ljflExerciseService.selectByPrimaryKey(ljflScoreDetail.getLjflexerciseid());
			ljflScoreDetail.setLjflexerciseid(exercise.getName());
			Date time = ljflScoreDetail.getCreatetime();
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			ljflScoreDetail.setMemo(dateFormater.format(time));
			LjflStaffDetail staff = ljflScoreService.selectStaffDetailById(ljflScoreDetail.getStaffdetailid());
			ljflScoreDetail.setStaffdetailid(staff.getName());
			list.add(ljflScoreDetail);
		}
		page.setPageTotalNum(size);
		page.setScoreDetailList(list);
		return page;
	}
	

}
