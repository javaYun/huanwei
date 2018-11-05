package com.sczh.platform.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sczh.platform.po.KitchenInfo;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.po.Model.KitchenInfoPageDomain;
import com.sczh.platform.service.KitchenInfoService;
import com.sczh.platform.service.UserService;
import com.yunlao.util.MD5andKL;
import com.yunlao.util.MyLong;

@Controller
@RequestMapping(value = "garbage")
public class GarbageController {

	private static String cardno = null;
	@Autowired
	private KitchenInfoService kitcheninfoService;

	@Autowired
	private UserService userService;

	/*@ResponseBody
	// @ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/joinkitchenPage")
	public ModelAndView joinkitPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("garbage/joinkitchenPage");
		return mav;
	}*/

	/**
	 * 保存餐馆信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savekitchenInfo")
	public Map<String, String> savekitcheninfo(@RequestBody String data) {

		JSONObject jsondata = JSONObject.parseObject(data);

		System.out.println("json数据：" + jsondata);
		Map<String, String> map = new HashMap<String, String>();
		if (jsondata != null) {

			KitchenInfo kitchenInfo = new KitchenInfo();
			String id = MyLong.getRand().toString();
			kitchenInfo.setId(id);
			kitchenInfo.setCardno(jsondata.getString("cardno"));
			kitchenInfo.setName(jsondata.getString("name"));
			kitchenInfo.setPhone(jsondata.getString("phone"));
			kitchenInfo.setAddress(jsondata.getString("address"));
			kitchenInfo.setUpdatetime(jsondata.getString("updatetime"));
			kitchenInfo.setIntegral(jsondata.getString("integral"));
			kitchenInfo.setMoney(jsondata.getString("money"));
			kitchenInfo.setPrincipal(jsondata.getString("principal"));
			kitchenInfo.setPrincipalphone(jsondata.getString("principalphone"));
			kitchenInfo.setDeviceno(jsondata.getString("deviceno"));

			kitcheninfoService.insert(kitchenInfo);

			/**
			 * 判断是否对其授权
			 */
			boolean loginRole = jsondata.getBooleanValue("loginRole");
			if (loginRole) {
				UserInfo user = new UserInfo();
				String username = jsondata.getString("name");
				String password = MD5andKL.MD5(jsondata.getString("name")
						+ "123456");
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				if (jsondata.getString("cardno") != null) {
					user.setUnitid(jsondata.getString("cardno"));// 用作存储unitID
				} else {
					user.setUnitid("0");// 0代表最高单位
				}// unitName
				if (jsondata.getString("name") != null) {
					user.setUnitname(jsondata.getString("name"));// 用作存储setUnitname
				} else {
					user.setUnitname("超级管理员");// 0代表最高单位
				}// unitName

				userService.insert(user);
			}

			map.put("message", "信息录入成功");

		} else {

			try {
				throw new Exception("数据不能为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	/*@RequestMapping(value = "joinkitchenInfo")
	public ModelAndView joinkitchenInfo() {
		ModelAndView mav = new ModelAndView("garbage/joinKitchenInfo");
		return mav;
	}*/

	/**
	 * 获取餐馆的信息
	 * 
	 * @param kitpageDomain
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getKitchenInfo")
	public KitchenInfoPageDomain getKitchenInfo(
			@RequestBody KitchenInfoPageDomain kitpageDomain,
			HttpSession session) {

		KitchenInfoPageDomain kitchenInfoPageDomain = new KitchenInfoPageDomain();
		kitchenInfoPageDomain = kitpageDomain;

		Object userid = session.getAttribute("userid");
		String userid2 = userid.toString();

		System.out.println("userid:" + userid2);
		if (userid2.equals("1")) {
			int startSize = kitpageDomain.getPageSize()
					* (kitpageDomain.getCurrentPage() - 1);

			List<KitchenInfo> selectKitchenInfoList = kitcheninfoService
					.selectKitchenInfoList(startSize + "",
							kitpageDomain.getPageSize() + "");

			kitchenInfoPageDomain.setContent(selectKitchenInfoList);
			kitchenInfoPageDomain.setTotalNum(kitcheninfoService
					.selectKitchenInfoListTotal().size());
		} else {

			UserInfo selectByPrimaryKey = userService
					.selectByPrimaryKey(userid2);
			System.out.println("userinfo:" + selectByPrimaryKey.toString());
			String kitchencardno = selectByPrimaryKey.getUnitid();
			KitchenInfo selectByCardno = kitcheninfoService
					.selectByCardno(kitchencardno);
			List<KitchenInfo> kitcheninfoList = new ArrayList<KitchenInfo>();
			if (selectByCardno != null) {
				kitcheninfoList.add(selectByCardno);
				
			} else {
			}
			
			kitchenInfoPageDomain.setContent(kitcheninfoList);
			kitchenInfoPageDomain.setTotalNum(kitcheninfoList.size());
		}
		return kitchenInfoPageDomain;
	}

	/**
	 * 刷卡更新餐馆积分余额
	 */
	@ResponseBody
	@RequestMapping(value = "/updatekitcheninfo")
	public Map<String, String> updatekitcheninfo(@RequestBody String jsondate) {

		System.out.println(jsondate);

		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsondate);
		String cardno = parseObject.getString("cardno");
		String weight = parseObject.getString("weight");

		int parseInt = Integer.parseInt(weight);

		KitchenInfo selectByCardno = kitcheninfoService.selectByCardno(cardno);
		System.out.println(selectByCardno);

		return map;
	}

	/**
	 * 删除餐馆数据
	 * 
	 * @param jsondate
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deletekitcheninfo")
	public Map<String, String> deletekitcheninfo(@RequestBody String jsondate) {

		Map<String, String> map = new HashMap<String, String>();
		JSONObject parseObject = JSONObject.parseObject(jsondate);
		System.out.println(parseObject);
		String cardno = parseObject.getString("cardno");

		System.out.println(cardno);

		KitchenInfo selectByCardno = kitcheninfoService.selectByCardno(cardno);

		System.out.println(selectByCardno);
		String id = selectByCardno.getId();
		kitcheninfoService.deleteByPrimaryKey(id);
		map.put("message", "删除成功");
		return map;
	}

	/**
	 * 修改餐馆数据
	 * 
	 * @param jsondate
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editkitcheninfo")
	public KitchenInfo editkitcheninfo(@RequestBody String jsondate) {

		JSONObject parseObject = JSONObject.parseObject(jsondate);
		System.out.println(parseObject);
		cardno = parseObject.getString("cardno");

		System.out.println(cardno);

		KitchenInfo selectByCardno = kitcheninfoService.selectByCardno(cardno);

		return selectByCardno;
	}

	/**
	 * 保存修改后餐馆数据
	 * 
	 * @param jsondate
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatekitchenInfo2")
	public Map<String, String> updatekitchenInfo2(@RequestBody String jsondate) {

		Map<String, String> map = new HashMap<String, String>();
		JSONObject parseObject = JSONObject.parseObject(jsondate);

		KitchenInfo selectByCardno = kitcheninfoService.selectByCardno(cardno);

		selectByCardno.setName(parseObject.getString("name"));
		selectByCardno.setPhone(parseObject.getString("phone"));
		selectByCardno.setAddress(parseObject.getString("address"));
		selectByCardno.setPrincipal(parseObject.getString("principal"));
		selectByCardno.setPrincipalphone(parseObject
				.getString("principalphone"));
		selectByCardno.setIntegral(parseObject.getString("integral"));
		selectByCardno.setMoney(parseObject.getString("money"));

		kitcheninfoService.updateByPrimaryKey(selectByCardno);
		map.put("message", "修改成功");
		return map;
	}
}
