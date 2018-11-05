package com.sczh.platform.Controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONObject;
import com.sczh.platform.po.AreaRelation;
import com.sczh.platform.po.Depts;
import com.sczh.platform.po.OMenuinfo;
import com.sczh.platform.po.UnitInfo;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.service.AreaService;
import com.sczh.platform.service.DeptsService;
import com.sczh.platform.service.OMenuInfoService;
import com.sczh.platform.service.UnitService;
import com.sczh.platform.service.UserService;
import com.yunlao.util.MD5andKL;
import com.yunlao.util.MyLong;

@Controller
@RequestMapping("depts")
public class DeptsController {
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AreaService areaServiceImpl;
	
	@Autowired 
	private OMenuInfoService omenuinfoService;
	
	@Autowired
	private DeptsService deptsService;
	
	/**
	 * 单位注册
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public Map<String, String> register(@RequestBody Depts registerDepts,HttpSession session){
		Map<String, String> map = new HashMap<String,String>();
		String deptName = registerDepts.getDeptName();
		String describe = registerDepts.getDescribe();
		String variety = registerDepts.getVariety();
		String license = registerDepts.getLicense();
		Depts depts = new Depts(deptName,describe,variety,license);
		
		deptsService.insertRegisterDepts(depts);
		
		System.out.println("--deptName:--"+deptName+"--describe:--"+describe);
		map.put("result", "yes");
		return map;
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
		//if(userId.equals("1")){//Admin下展示所有数据
			AreaRelation areaRelation  = new AreaRelation();
			areaRelation.setIsarea("Y");
			list = areaServiceImpl.selectAreaRelationAndIsArea(areaRelation);
		//}else{
			/**
			 * 根据userId查询所在的areaId
			 */
		//}
		return list;
	}
	/**
	 * 保存单位信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertUnitsInfo",method=RequestMethod.POST)
	public  Map<String, String>  insertUnitsInfo(@RequestBody String unitinfo){//添加到数据库表departmentmanage
		String unitcode = ""+(int)((Math.random()*9+1)*10000000); 
		JSONObject json = JSONObject.parseObject(unitinfo);
		System.out.println("sjhs"+json);
		Map<String, String> map = new HashMap<String, String>();
		if(json!=null ){
			//String id= MyLong.getRand().toString();
			//departmentManage.setId(id);
			System.out.println("json==="+json);
			
			UnitInfo selectByPrimaryKey = unitService.selectByPrimaryKey(unitcode);
			System.out.println("结果："+selectByPrimaryKey);
			if(selectByPrimaryKey!=null){
				
				//map.put("isSuccess", "该单位号已注册");
				map.put("isSuccess", "注册失败，请重新注册");
			}else{
				UnitInfo unitInfo2 = new UnitInfo();
				unitInfo2.setAddress(json.getString("address"));
				unitInfo2.setChangetime(json.getDate("changetime"));
				unitInfo2.setCorporate(json.getString("corporate"));
				unitInfo2.setPhone(json.getString("phone"));
				unitInfo2.setSetuptime(json.getDate("setuptime"));
				unitInfo2.setState(json.getString("state"));
				unitInfo2.setUnitadmin(json.getString("unitadmin"));
				unitInfo2.setUnitcode(unitcode);
				unitInfo2.setUnitdepict(json.getString("unitdepict"));
				unitInfo2.setUnitname(json.getString("unitname"));
				unitInfo2.setUnittype(json.getString("unittype"));
				unitInfo2.setAreaid(json.getString("areaid"));
				unitInfo2.setAreaname(json.getString("areaname"));
				System.out.println(unitInfo2);
				int num;
				try {
					num = unitService.insert(unitInfo2);
					
					System.out.println("num==="+num); 
					OMenuinfo oMenuinfo = new OMenuinfo();
					oMenuinfo.setName(json.getString("unitname"));
					oMenuinfo.setId(unitcode);
					oMenuinfo.setPID("0");
					
					try {
						int insert = omenuinfoService.insert(oMenuinfo);
						
						UserInfo user = new UserInfo();
						String id =  MyLong.getRandStr(11);
						String username = unitInfo2.getUnitname();
						//String password = MD5andKL.MD5("123456");
						String password = MD5andKL.MD5("123456");
						user.setId(id);
						user.setUsername(username);
						user.setPassword(password);
						user.setUnitname(json.getString("unitname"));
						user.setUnitid(unitcode);
						//user.setStr4(username);
						//user.setUnitid("0");//0代表最高单位
						//user.setUnitname("超级管理员");//0代表最高单位
						try {
							int insert2 = userService.insert(user);
							
							if(num==1&& insert==1&&insert2==1){
								
								
								map.put("isSuccess", "单位信息录入完成，用户名为单位名称，初始密码为123456。");
							}
						} catch (Exception e) {
							
							map.put("isSuccess", "注册失败");												
						}
					} catch (Exception e) {
						
						map.put("isSuccess", "注册失败");						
					}
					
					
				} catch (Exception e) {
					
					map.put("isSuccess", "注册失败");
				}
			}
		}else{
			try {
				throw new  Exception("数据不能为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
