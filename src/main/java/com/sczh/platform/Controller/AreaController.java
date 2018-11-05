package com.sczh.platform.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sczh.platform.po.AreaGov;
import com.sczh.platform.po.AreaManager;
import com.sczh.platform.po.AreaRelation;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.po.Model.AreaGovModel;
import com.sczh.platform.service.AreaService;
import com.sczh.platform.service.UserService;
import com.yunlao.util.MD5andKL;
import com.yunlao.util.MyLong;
import com.yunlao.util.SimpleDateUtil;

@Controller
@RequestMapping("area")
public class AreaController {
	@Autowired
	private AreaService areaServiceImpl;
	
	@Autowired
	private UserService userService;
	/**
	 * 进入区域管理页面
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/joinAreaPage")
	public ModelAndView joinUnitsPlan(HttpSession session){
		ModelAndView mav = new ModelAndView("area/joinAreaPage");
		String userId =  (String) session.getAttribute("userid");
		mav.addObject("userId", userId);
		return mav;
	}
	/**
	 * 获取所有组织机构
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="loadAreaTree")
	 public List<AreaRelation> loadAreaTree(HttpSession session) throws IOException{
		List<AreaRelation> list = new ArrayList<AreaRelation>();
		String userId =  (String) session.getAttribute("userid");
		if(userId.equals("1")){//Admin下展示所有数据
			AreaRelation areaRelation  = new AreaRelation();
			areaRelation.setIsarea("Y");
			list = areaServiceImpl.selectAreaRelationAndIsArea(areaRelation);
		}else{
			/**
			 * 根据userId查询所在的areaId
			 */
		}
	
		return list;
	  }
	/**
	 * t添加区域性代码
	 */
	@ResponseBody
	@RequestMapping("addAreaGovInfo")
	public Map<String, String> addAreaGovInfo(@RequestBody AreaGovModel areaGovModel){
		Map<String, String> map = new HashMap<String,String>();
		/**
		 * 保存区域性信息
		 * 将上下级单位关系保存到关联表
		 */
		String areaId = MD5andKL.KL(MyLong.getRandStr(13));
		AreaGov areaGov = deal(areaGovModel);
		areaGov.setId(areaId);
		
		areaServiceImpl.saveAreaGov(areaGov);
		
		String areaRelationId = MD5andKL.KL(MyLong.getRandStr(15));
		AreaRelation areaRelation = new AreaRelation();
		areaRelation.setArearelationid(areaRelationId);
		areaRelation.setId(areaId);
		areaRelation.setName(areaGov.getAreaname());
		areaRelation.setOnclick(areaId);
		areaRelation.setpId(areaGovModel.getHighLevelId());
		areaRelation.setIsarea("Y");
		areaServiceImpl.areaRelation(areaRelation);
		map.put("result", "success");
		return map;
	}
	/**
	 * model 数据整合
	 * @param m
	 * @return
	 */
	private AreaGov deal(AreaGovModel m) {
		AreaGov a = new AreaGov();
		a.setAreaaddress(m.getAreaaddress());
		a.setAreacode(m.getAreacode());a.setAreaname(m.getAreaname());a.setAreatype(m.getAreatype());
		a.setAreadescription(m.getAreadescription());
		Date time = null;
		if(m.getSetuptime()!=null){
			time = SimpleDateUtil.getDateByString(m.getSetuptime());
		}
	
		a.setSetuptime(time);
		a.setLatitude(m.getLatitude());a.setLontitude(m.getLontitude());
		return a;
	}
	/**
	 * 进入蜂巢管理页面
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/joinSocietyArea")
	public ModelAndView joinSocietyArea(HttpSession session){
		ModelAndView mav = new ModelAndView("area/joinSocietyArea");
		String userId =  (String) session.getAttribute("userid");
		mav.addObject("userId", userId);
		return mav;
	}
	/**
	 * t添加社区信息
	 */
	@ResponseBody
	@RequestMapping("addAreaCommunityInfo")
	public Map<String, String> addAreaCommunityInfo(@RequestBody AreaCommunity areaCommunity){
		Map<String, String> map = new HashMap<String,String>();
		/**
		 * 保存区域性信息
		 * 将上下级单位关系保存到关联表
		 */
		String communityId = MD5andKL.MD5(MyLong.getRandStr(13));
		Date time = new Date();
		if(areaCommunity.getParentid() != null){
			time = SimpleDateUtil.getDateByString(areaCommunity.getParentid() );
		}
	
		areaCommunity.setSetuptime(time);
		
		areaCommunity.setId(communityId);
		areaServiceImpl.saveAreaCommunity(areaCommunity);
		
		String areaRelationId = MD5andKL.MD5(MyLong.getRandStr(15));
		AreaRelation areaRelation = new AreaRelation();
		areaRelation.setArearelationid(areaRelationId);
		areaRelation.setId(communityId);
		areaRelation.setIsarea("Y");
		areaRelation.setName(areaCommunity.getCommunityname());
		areaRelation.setOnclick(communityId);
		areaRelation.setpId(areaCommunity.getAreaid());
		areaServiceImpl.areaRelation(areaRelation);
		map.put("result", "success");
		return map;
	}
	/**
	 * 进入区域系统管理员页面
	 * @param request
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/joinAreaManager")
	public ModelAndView joinAreaManager(HttpSession session){
		ModelAndView mav = new ModelAndView("area/joinAreaManager");
		String userId =  (String) session.getAttribute("userid");
		mav.addObject("userId", userId);
		return mav;
	}
	/**
	 * t添加社区信息
	 */
	@ResponseBody
	@RequestMapping("addAreaManager")
	public Map<String, String> addAreaManager(@RequestBody AreaManager areaManager){
		String userId = MD5andKL.MD5(MyLong.getRandStr(8));
		areaManager.setUserid(userId);
		Map<String, String> map = new HashMap<String,String>();
		/**
		 * 保存区域性信息
		 * 将上下级单位关系保存到关联表
		 */
		String mangerId = MD5andKL.MD5(MyLong.getRandStr(13));
		Date time = new Date();
		if(areaManager.getStatus() != null){
			time = SimpleDateUtil.getDateByString(areaManager.getStatus() );
		}
	
		areaManager.setSetuptime(time);
		if(areaManager.getStr1() != null){
			time = SimpleDateUtil.getDateByString(areaManager.getStr1() );
			areaManager.setChangetime(time);
		}
		String password = "";
		if(areaManager.getPassword() == null  || areaManager.getPassword().isEmpty()){
			password = MD5andKL.MD5(areaManager.getUsername()+"123456");
		}else{
			password = MD5andKL.MD5(areaManager.getUsername()+areaManager.getPassword());
		}
		areaManager.setPassword(password);
		areaManager.setId(mangerId);
		areaServiceImpl.saveAreaManager(areaManager);
		
		UserInfo user = new UserInfo();
		user.setId(userId);
		user.setUsername(areaManager.getUsername());
		user.setPassword(areaManager.getPassword());
		if(areaManager.getAreaid()!=null){
			user.setUnitid(areaManager.getAreaid());//用作存储unitID
		}else{
			user.setUnitid("0");//0代表最高单位
		}//unitName
	
		
		userService.insert(user);
	
		map.put("result", "success");
		return map;
	}
	/**
	 * 判断是否为需要的区域
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/isNeedArea",method = RequestMethod.POST)
	public Map<String, String> isNeedArea(@RequestBody String areaGovid,HttpSession session){
		
		JSONObject parseObject = JSONObject.parseObject(areaGovid);
		
		String id = parseObject.getString("id");
		AreaGov areaGov = areaServiceImpl.selectAreaGovById(id);
		
		Map<String, String> map = new HashMap<String,String>();
		if("street".equals(areaGov.getAreadescription())){
			map.put("result", "yes");
		}else{
			map.put("result", "no");
		}
		
		return map;
	}
}
