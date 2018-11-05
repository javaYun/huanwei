package com.sczh.platform.Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sczh.platform.po.ContractInfo;
import com.sczh.platform.po.DivisionTable;
import com.sczh.platform.po.LjflPrize;
import com.sczh.platform.po.LjflPrizeProvide;
import com.sczh.platform.po.LjflPrizeProvideRecord;
import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.LjflType;
import com.sczh.platform.po.Picture;
import com.sczh.platform.po.Model.LjflPrizePageDomain;
import com.sczh.platform.service.LjflPrizeService;
import com.sczh.platform.service.LjflScoreService;
import com.yunlao.util.FileUploadUtil;

@Controller
@RequestMapping("ljflprize")
public class LjflPrizeController {

	private static String id = null;
	@Autowired
	private LjflPrizeService ljflprizeService;

	@Autowired
	private LjflScoreService ljflScoreService;

	/*@RequestMapping("ljflPrizePage")
	public ModelAndView ljflPrizePage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView(
				"ljflscore/prizemanage/ljflPrizeIssuePage");
		mav.addObject("userId", userId);
		return mav;
	}*/

	@ResponseBody
	@RequestMapping("getPrizeData")
	public LjflPrizePageDomain getPrizeData(
			@RequestBody LjflPrizePageDomain prizePageDomain) {

		System.out.println("进来啦");

		LjflPrizePageDomain ljflPrizePageDomain = new LjflPrizePageDomain();
		ljflPrizePageDomain = prizePageDomain;

		String name = prizePageDomain.getName();
		String code = prizePageDomain.getCode();
		String startTime = prizePageDomain.getStartTime();
		String endtime = prizePageDomain.getEndtime();

		if (name == null || name == "") {
			name = ".";

		}
		if (code == null || code == "") {

			code = ".";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String st = null;
		if (startTime != null || startTime != "") {
			try {
				Date parse = sdf.parse(startTime);
				st = sdf.format(parse);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String et = null;

		if (endtime != null || endtime != "") {
			try {
				Date parse = sdf.parse(endtime);
				et = sdf.format(parse);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int startSize = ljflPrizePageDomain.getPageSize()
				* (ljflPrizePageDomain.getCurrentPage() - 1);

		List<LjflPrizeProvide> selectPrizeProvideInfoList = ljflprizeService
				.selectPrizeProvideInfoList(startSize + "",
						prizePageDomain.getPageSize() + "", name, code, st, et);
for (LjflPrizeProvide ljflPrizeProvide : selectPrizeProvideInfoList) {
	
	String getfPrizeId = ljflPrizeProvide.getfPrizeId();
	LjflPrize selectByPrimaryKey1 = ljflprizeService.selectByPrimaryKey1(getfPrizeId);
	
	String getfName = selectByPrimaryKey1.getfName();
	ljflPrizeProvide.setfPrizeId(getfName);
	
}
		ljflPrizePageDomain.setContent(selectPrizeProvideInfoList);
		ljflPrizePageDomain.setTotalNum(ljflprizeService
				.selectPrizeProvideInfoListTotal(name, code, st, et).size());

		System.out.println(ljflPrizePageDomain);

		return ljflPrizePageDomain;
	}

	/**
	 * 进入新增奖品发放页面
	 *//*
	@RequestMapping("addPrizeProvide")
	public ModelAndView addPrizeProvide(HttpSession session) {

		ModelAndView mav = new ModelAndView(
				"ljflscore/prizemanage/ljfladdPrizeProvidePage");
		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);

		List<LjflPrize> selectByBeenDeleted = ljflprizeService
				.selectByBeenDeleted();

		mav.addObject("prize", selectByBeenDeleted);

		return mav;

	}*/

	/**
	 * 新增奖品发放
	 * 
	 * @param jsonString
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insertprizeprovideInfo")
	public Map<String, String> insertprizeprovideInfo(
			@RequestBody String jsonString) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsonString);
		System.out.println(parseObject.toString());

		LjflPrizeProvide ljflPrizeProvide = new LjflPrizeProvide();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		ljflPrizeProvide.setId(id);
		Date date = new Date();

		ljflPrizeProvide.setCreatetime(date);
		ljflPrizeProvide.setLastchangetime(date);
		ljflPrizeProvide.setStatus(0);
		ljflPrizeProvide.setBeendeleted(0);
		ljflPrizeProvide.setfCode(parseObject.getString("code"));
		ljflPrizeProvide.setTenantid("4eaf9423eb0043648e502134586b088a");
		ljflPrizeProvide.setfMemo(parseObject.getString("memo"));
		ljflPrizeProvide.setfProviderNum(parseObject.getInteger("providerNum"));
		ljflPrizeProvide.setfScoreNum(parseObject.getInteger("scoreNum"));
		ljflPrizeProvide.setfTakeNum(0);
		Date date2 = parseObject.getDate("validityDateStr");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String st = sdf.format(date2);
		Date date3 = null;
		try {
			date3 = sdf.parse(st);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ljflPrizeProvide.setfValidityDate(date3);
		String prizename = parseObject.getString("prizename");
		List<LjflPrize> selectljflprizebyname = ljflprizeService.selectljflprizebyname(prizename);
		String prizeId = selectljflprizebyname.get(0).getId();
		ljflPrizeProvide.setfName(parseObject.getString("name"));
		
		ljflPrizeProvide.setfPrizeId(prizeId);
		ljflPrizeProvide.setfOrderindex(parseObject.getInteger("orderIndex"));

		int insert = ljflprizeService.insert(ljflPrizeProvide);

		System.out.println(insert);

		if (insert == 1) {

			map.put("message", "添加成功");
		} else {

			map.put("message", "添加失败");
		}

		return map;

	}

	/**
	 * 进入查看奖品发放页面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("lookPrizeProvideData")
	public LjflPrizeProvide lookPrizeProvideData(
			@RequestBody String ids) {

		JSONObject parseObject = JSONObject.parseObject(ids);
		
		String id = parseObject.getString("id");
		
		System.out.println("num==" + id);

		System.out.println(id);

		LjflPrizeProvide selectByPrimaryKey = ljflprizeService
				.selectByPrimaryKey(id);

		System.out.println(selectByPrimaryKey.toString());

		String getfPrizeId = selectByPrimaryKey.getfPrizeId();

		LjflPrize selectByPrimaryKey1 = ljflprizeService
				.selectByPrimaryKey1(getfPrizeId);

		String getfName = selectByPrimaryKey1.getfName();

		selectByPrimaryKey.setfPrizeId(getfName);
		
		System.out.println(selectByPrimaryKey.toString());
		
		return selectByPrimaryKey;

	}

	/**
	 * 修改奖品发放信息
	 *//*
	@RequestMapping("updatePrizeProvideData")
	public ModelAndView updatePrizeProvideData(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView(
				"ljflscore/prizemanage/ljflupdatePrizeProvidePage");
		id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		System.out.println("num==" + id);
		LjflPrizeProvide selectByPrimaryKey = ljflprizeService
				.selectByPrimaryKey(id);

		System.out.println(selectByPrimaryKey.toString());

		String getfPrizeId = selectByPrimaryKey.getfPrizeId();

		LjflPrize selectByPrimaryKey1 = ljflprizeService
				.selectByPrimaryKey1(getfPrizeId);

		List<LjflPrize> selectByBeenDeleted = ljflprizeService
				.selectByBeenDeleted();

		mav.addObject("prize", selectByBeenDeleted);

		mav.addObject("prize2", selectByPrimaryKey1);
		mav.addObject("prizeinfo", selectByPrimaryKey);

		return mav;
	}
*/
	@ResponseBody
	@RequestMapping("updatePrizeProvideInfo")
	public Map<String, String> updateactivityInfo(@RequestBody String jsonString) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsonString);
		System.out.println(parseObject.toString());

		String id = parseObject.getString("id");
		LjflPrizeProvide selectByPrimaryKey = ljflprizeService
				.selectByPrimaryKey(id);
		Date date = new Date();
		selectByPrimaryKey.setLastchangetime(date);
		selectByPrimaryKey.setfCode(parseObject.getString("code"));
		selectByPrimaryKey.setfMemo(parseObject.getString("memo"));
		selectByPrimaryKey.setfName(parseObject.getString("name"));
		selectByPrimaryKey.setfProviderNum(parseObject
				.getInteger("providerNum"));
		selectByPrimaryKey.setfScoreNum(parseObject.getInteger("scoreNum"));
		Date date2 = parseObject.getDate("validityDateStr");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String st = sdf.format(date2);
		Date date3 = null;
		try {
			date3 = sdf.parse(st);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectByPrimaryKey.setfValidityDate(date3);
		
		String prizename = parseObject.getString("prizename");
		
		
		List<LjflPrize> selectljflprizebyname = ljflprizeService.selectljflprizebyname(prizename);
		String prizeId = selectljflprizebyname.get(0).getId();
		
		selectByPrimaryKey.setfPrizeId(prizeId);
		
		selectByPrimaryKey.setfOrderindex(parseObject.getInteger("orderIndex"));

		int updateByPrimaryKey = ljflprizeService
				.updateByPrimaryKey(selectByPrimaryKey);

		if (updateByPrimaryKey == 1) {

			map.put("message", "修改成功");
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
	@RequestMapping("deletePrizeProvideData")
	public Map<String, String> deletePrizeProvideData(
			@RequestBody String jsonString) {

		System.out.println("jinll ");
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsonString);
		String id = parseObject.getString("id");

		Date date = new Date();
		LjflPrizeProvide selectByPrimaryKey = ljflprizeService
				.selectByPrimaryKey(id);
		selectByPrimaryKey.setBeendeleted(1);
		selectByPrimaryKey.setDeletedtime(date);

		int updateByPrimaryKey = ljflprizeService
				.updateByPrimaryKey(selectByPrimaryKey);

		if (updateByPrimaryKey == 1) {

			map.put("message", "删除成功");
		} else {

			map.put("message", "删除失败");
		}

		return map;

	}

	/**
	 * 奖品发放记录页面
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("ljflPrizeRecordPage")
	public ModelAndView ljflPrizeRecordPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView(
				"/ljflscore/prizemanage/ljflPrizeRecordPage");

		// 奖品发放名称
		List<LjflPrizeProvide> selectPrizeProvideInfoListTotal = ljflprizeService
				.selectPrizeProvideInfoListTotal1();

		System.out.println(selectPrizeProvideInfoListTotal.size());

		// 奖品
		List<LjflPrize> selectByBeenDeleted = ljflprizeService
				.selectByBeenDeleted();

		mav.addObject("prize", selectByBeenDeleted);
		mav.addObject("prizeProvide", selectPrizeProvideInfoListTotal);
		mav.addObject("userId", userId);
		return mav;
	}*/

	/**
	 * 获取奖品发放记录数据
	 */
	@ResponseBody
	@RequestMapping("getPrizeRecordData")
	public LjflPrizePageDomain getPrizeRecordData(
			@RequestBody LjflPrizePageDomain prizePageDomain) {

		LjflPrizePageDomain ljflPrizePageDomain = new LjflPrizePageDomain();
		ljflPrizePageDomain = prizePageDomain;
		System.out.println(prizePageDomain.toString());

		// 居民
		if (prizePageDomain.getStaffDetailId() == null
				|| prizePageDomain.getStaffDetailId().isEmpty()) {// 居民id
			prizePageDomain.setStaffDetailId(null);
		}
		// 开始时间
		if (prizePageDomain.getStartTime() == null
				|| prizePageDomain.getStartTime().isEmpty()) {
			prizePageDomain.setStartTime(null);
		}
		// 结束时间
		if (prizePageDomain.getEndtime() == null
				|| prizePageDomain.getEndtime().isEmpty()) {
			prizePageDomain.setEndtime(null);
		}
		// 奖品发放
		if (prizePageDomain.getPrizeProvideId() == null
				|| prizePageDomain.getPrizeProvideId().isEmpty()) {
			prizePageDomain.setPrizeProvideId(null);
		}
		// 奖品
		if (prizePageDomain.getPrizeId() == null
				|| prizePageDomain.getPrizeId().isEmpty()) {
			prizePageDomain.setPrizeId(null);
		}

		// 发放状态
		if (prizePageDomain.getProvideStatus() == null
				|| prizePageDomain.getProvideStatus().isEmpty()) {
			prizePageDomain.setProvideStatus(null);
		}

		int size = 0;// 查询的所有条数
		List<LjflPrizeProvideRecord> ljflPrizeProvideRecordList = new ArrayList<LjflPrizeProvideRecord>();

		/**
		 * 初始化数据 没有起始时间
		 */
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		String chaxunTime = dateNowStr;// 查询时间
		// String startTime = "2018-01-02 00:00:00";
		// String endTime = "2018-01-02 23:59:59";
		String startTime = dateNowStr.substring(0, 10) + " 00:00:00";
		String endTime = dateNowStr.substring(0, 10) + " 23:59:59";
		int pageIsSize = prizePageDomain.getPageSize();

		if (prizePageDomain.getStartTime() == null) {
			String tableName = "";
			try {
				tableName = LjflScoreController.getTableNameString(
						"ljfl_prize_provide_record", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			prizePageDomain.setStartTime(startTime);
			prizePageDomain.setEndtime(endTime);
			prizePageDomain.setTablename(tableName);
			size = ljflprizeService.selecScoreByDateTotalSize(prizePageDomain);
			prizePageDomain
					.setCurrentPage((prizePageDomain.getCurrentPage() - 1)
							* prizePageDomain.getPageSize());

			int endNum = prizePageDomain.getCurrentPage()
					* prizePageDomain.getPageSize();

			if (size <= endNum) {
				prizePageDomain.setEndNum(prizePageDomain.getPageSize()
						- (endNum - size));
			} else {
				prizePageDomain.setEndNum(prizePageDomain.getPageSize());
			}
			ljflPrizeProvideRecordList = ljflprizeService
					.selecScoreByDateTotal(prizePageDomain);
		} else {

			/**
			 * 1.先计算有多少个表，将所有的表的size查出来
			 */
			String startDate = prizePageDomain.getStartTime();
			String endDate = prizePageDomain.getEndtime();
			// int currentNum =
			// (pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize();//此次获取的第一个数据
			// int endNum =
			// pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();//此次获取的可能性最后一个数据
			//

			List<String> list = new ArrayList<String>();
			try {
				list = LjflScoreController.getTableName(
						"ljfl_prize_provide_record", startDate, endDate);
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

				prizePageDomain.setTablename(str);
				int datesize = ljflprizeService
						.selecScoreByDateTotalSize(prizePageDomain);
				division.setSize(datesize);
				tableList.add(division);
				size = size + datesize;// 一共有多少条满足的数据
			}
			/**
			 * 获取数据
			 */
			int currentForMiniNum = (prizePageDomain.getCurrentPage() - 1)
					* prizePageDomain.getPageSize();// 当前循环的最小size
			// int currentMaxNum =
			// (pageModelDomain.getCurrentPage())*pageModelDomain.getPageSize()<=size?(pageModelDomain.getCurrentPage())*pageModelDomain.getPageSize():size;//当前循环的最大size
			int upTotalSize = 0;// 上一个表累加后所包含的最大数据
			int currentTableSize = 0;// 当前表所包含的最大数据
			int pageSize = prizePageDomain.getPageSize();
			int residuePageSize = pageSize;// 剩余的page
			int pageneedSize = pageSize;
			boolean isNextTable = false;
			boolean istheNextTable = false;
			for (int i = 0; i < tableList.size(); i++) {
				prizePageDomain.setPageSize(pageSize);
				if (ljflPrizeProvideRecordList.size() >= pageneedSize) {
					break;
				}
				List<LjflPrizeProvideRecord> forScoreRecordList = new ArrayList<LjflPrizeProvideRecord>();
				DivisionTable divisionTable = tableList.get(i);
				prizePageDomain.setTablename(divisionTable.getTableName());
				upTotalSize = currentTableSize;
				currentTableSize = currentTableSize + divisionTable.getSize();
				if (currentTableSize <= currentForMiniNum) {// 如果当前表的累加数据，小于当前最小值，则跳过
					continue;
				}
				if (divisionTable.getSize() == 0) {
					continue;
				}
				int currenrRealNum = currentForMiniNum - upTotalSize;
				if (currenrRealNum <= 0) {
					currenrRealNum = 0;
				}
				int currentPageSize = 0;
				if (residuePageSize < (currentTableSize - currentForMiniNum)) {
					currentPageSize = residuePageSize;
				} else {
					currentPageSize = pageneedSize
							- ljflPrizeProvideRecordList.size();
					if (currentPageSize > (divisionTable.getSize() - currenrRealNum)) {
						currentPageSize = (divisionTable.getSize() - currenrRealNum);
					}
					residuePageSize = pageSize - currentPageSize;
					isNextTable = true;
				}
				if (istheNextTable) {
					prizePageDomain.setCurrentPage(0);
				} else {
					prizePageDomain.setCurrentPage(currenrRealNum);
				}
				prizePageDomain.setPageSize(currentPageSize);

				forScoreRecordList = ljflprizeService
						.selecScoreByDateTotal(prizePageDomain);
				ljflPrizeProvideRecordList.addAll(forScoreRecordList);
				if (isNextTable) {
					istheNextTable = true;
					prizePageDomain.setPageSize(residuePageSize);
				}

			}

		}

		int num = 0;
		List<LjflPrizeProvideRecord> list = new ArrayList<LjflPrizeProvideRecord>();
		for (LjflPrizeProvideRecord ljflScoreRecord : ljflPrizeProvideRecordList) {
			num++;
			if (num > pageIsSize) {
				break;
			}

			System.out.println(ljflScoreRecord);
			LjflStaffDetail staff = ljflScoreService
					.selectStaffDetailById(ljflScoreRecord.getStaffdetailid());
			ljflScoreRecord.setStaffdetailid(staff.getName());
			LjflPrizeProvide selectByPrimaryKey = ljflprizeService
					.selectByPrimaryKey(ljflScoreRecord.getPrizeprovideid());
			ljflScoreRecord.setPrizeprovideid(selectByPrimaryKey.getfName());

			LjflPrize selectByPrimaryKey1 = ljflprizeService
					.selectByPrimaryKey1(ljflScoreRecord.getPrizeid());
			ljflScoreRecord.setPrizeid(selectByPrimaryKey1.getfName());

			list.add(ljflScoreRecord);

		}

		ljflPrizePageDomain.setTotalNum(size);
		ljflPrizePageDomain.setContent1(list);

		System.out.println(ljflPrizePageDomain);

		return ljflPrizePageDomain;
	}

	/*@RequestMapping("ljflPrizeListPage")
	public ModelAndView ljflPrizeListPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView(
				"ljflscore/prizemanage/ljflPrizeListPage");
		mav.addObject("userId", userId);
		return mav;
	}*/

	@ResponseBody
	@RequestMapping("getPrizeListData")
	public LjflPrizePageDomain getPrizeListData(
			@RequestBody LjflPrizePageDomain prizePageDomain) {

		LjflPrizePageDomain ljflPrizePageDomain = new LjflPrizePageDomain();
		ljflPrizePageDomain = prizePageDomain;

		int startSize = ljflPrizePageDomain.getPageSize()
				* (ljflPrizePageDomain.getCurrentPage() - 1);

		String code = prizePageDomain.getCode();
		String name = prizePageDomain.getName();
		if (code == "" || code == null) {

			code = ".";
		}
		if (name == "" || name == null) {

			name = ".";
		}

		System.out.println(code);
		System.out.println(name);
		List<LjflPrize> selectPrizeInfoList = ljflprizeService
				.selectPrizeInfoList1(startSize + "",
						prizePageDomain.getPageSize() + "", code, name);

		ljflPrizePageDomain.setContent2(selectPrizeInfoList);
		ljflPrizePageDomain.setTotalNum(ljflprizeService
				.selectPrizeInfoListTotal1(code, name).size());

		System.out.println("输出" + ljflPrizePageDomain);
		return ljflPrizePageDomain;
	}

	/**
	 * 进入新增奖品页面
	 *//*
	@RequestMapping("addPrizeList")
	public ModelAndView addPrizeList(HttpSession session) {

		ModelAndView mav = new ModelAndView(
				"ljflscore/prizemanage/ljfladdPrizeListPage");
		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);

		return mav;

	}*/

	/**
	 * 新增奖品发放
	 * 
	 * @param jsonString
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "insertprizeListInfo", method = RequestMethod.POST)
	public Map<String, String> insertprizeListInfo(
			@RequestBody String jsonString, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsonString);
		System.out.println(parseObject.toString());

		LjflPrize ljflPrize = new LjflPrize();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		ljflPrize.setId(id);
		ljflPrize.setfCode(parseObject.getString("code"));
		ljflPrize.setfName(parseObject.getString("name"));
		ljflPrize.setfPrizeTypeName(parseObject.getString("prizeTypeName"));
		ljflPrize.setfPrizeTypeId(parseObject.getString("prizeTypeId"));
		ljflPrize.setfOrderindex(parseObject.getInteger("orderIndex"));
		ljflPrize.setfOnePrice(parseObject.getDouble("onePrice"));
		Date date = new Date();
		ljflPrize.setCreatetime(date);
		ljflPrize.setLastchangetime(date);
		ljflPrize.setStatus(0);
		ljflPrize.setBeendeleted(0);
		ljflPrize.setTenantid("4eaf9423eb0043648e502134586b088a");
		String string = parseObject.getString("photo");

		
		
		// 上传后图片所在路径
		String resultPath = request.getServletContext().getRealPath(
				"/WEB-INF/upload");
		// 文件输出流
		FileOutputStream out = null;
		File f = new File(string);
		try {
			if (!f.isFile()) {

				throw new Exception("不是图片文件");
			}

			if (f != null && f.exists()) {
				// 读入文件
				BufferedImage image = ImageIO.read(f);
				if (image != null) {

					BufferedImage tag = new BufferedImage(image.getWidth(),
							image.getHeight(), BufferedImage.TYPE_INT_RGB);

					tag.getGraphics().drawImage(image, 0, 0, image.getWidth(),
							image.getHeight(), null);

					/**
					 * 以下为生成图片上传后在服务器上的新路径
					 */
					int lastlength = string.lastIndexOf(".");
					Date date1 = new Date(System.currentTimeMillis());
					String strDate = new SimpleDateFormat("yyyyMMddhhmmss")
							.format(date1);
					int random = (int) (Math.random() * 99);
					String imageName = strDate + random;
					String fileType = string.substring(lastlength);

					/**
					 * 进行图片的绘制
					 */
					/*out = new FileOutputStream(resultPath);
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
					JPEGEncodeParam param = encoder
							.getDefaultJPEGEncodeParam(tag);
					param.setQuality(0.95f, true);
					encoder.setJPEGEncodeParam(param);
					encoder.encode(tag);
					tag = null;*/
				}
			}
			f = null;

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

		/*	out.close();
			out = null;*/
		}

		
		
		
		
		
		
		
		
		
		int insert = ljflprizeService.insert(ljflPrize);

		if (insert == 1) {

			map.put("message", "添加成功");
		} else {

			map.put("message", "添加失败");
		}

		return map;

	}

	public static MultipartFile getMulFileByPath(String picPath) {
		FileItem fileItem = createFileItem(picPath);
		MultipartFile mfile = new CommonsMultipartFile(fileItem);
		return mfile;
	}

	public static FileItem createFileItem(String filePath) {
		FileItemFactory factory = new DiskFileItemFactory(16, null);
		File newfile = new File(filePath);
		String name = newfile.getName();

		String textFieldName = "textField";
		FileItem item = factory.createItem(textFieldName, "text/plain", true,
				name);

		int bytesRead = 0;

		byte[] buffer = new byte[8192];

		try {
			FileInputStream fis = new FileInputStream(newfile);
			OutputStream os = item.getOutputStream();
			while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return item;
	}

	/**
	 * 进入查看奖品发放页面
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("lookPrizeListData")
	public LjflPrize lookPrizeListData(@RequestBody String ids) {

		JSONObject parseObject = JSONObject.parseObject(ids);
		
		String id = parseObject.getString("id");
		
		System.out.println("num==" + id);

		System.out.println(id);

		LjflPrize selectByPrimaryKey2 = ljflprizeService
				.selectByPrimaryKey2(id);

		System.out.println(selectByPrimaryKey2.toString());


		return selectByPrimaryKey2;

	}

	/**
	 * 修改奖品发放信息
	 *//*
	@RequestMapping("updatePrizeListData")
	public ModelAndView updatePrizeListData(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView(
				"ljflscore/prizemanage/ljflupdatePrizeListPage");
		id = request.getParameter("id") == null ? "" : request
				.getParameter("id");
		System.out.println("num==" + id);

		LjflPrize selectByPrimaryKey12 = ljflprizeService
				.selectByPrimaryKey1(id);

		System.out.println(selectByPrimaryKey12.toString());

		mav.addObject("prize", selectByPrimaryKey12);

		return mav;
	}*/

	/**
	 * 删除奖品
	 * 
	 * @param jsonString
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deletePrizeListData")
	public Map<String, String> deletePrizeListData(
			@RequestBody String jsonString) {

		System.out.println("jinll ");
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsonString);
		String id = parseObject.getString("id");

		Date date = new Date();

		LjflPrize selectByPrimaryKey1 = ljflprizeService
				.selectByPrimaryKey1(id);

		selectByPrimaryKey1.setBeendeleted(1);
		selectByPrimaryKey1.setDeletedtime(date);

		int updateByPrimaryKey = ljflprizeService
				.updateByPrimaryKey(selectByPrimaryKey1);

		if (updateByPrimaryKey == 1) {

			map.put("message", "删除成功");
		} else {

			map.put("message", "删除失败");
		}

		return map;

	}

	@ResponseBody
	@RequestMapping("updatePrizeListInfo")
	public Map<String, String> updatePrizeListInfo(
			@RequestBody String jsonString) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject parseObject = JSONObject.parseObject(jsonString);
		System.out.println(parseObject.toString());
		String id = parseObject.getString("id");
		LjflPrize selectByPrimaryKey = ljflprizeService.selectByPrimaryKey1(id);
		Date date = new Date();
		selectByPrimaryKey.setLastchangetime(date);
		selectByPrimaryKey.setfCode(parseObject.getString("code"));
		selectByPrimaryKey.setfName(parseObject.getString("name"));
		selectByPrimaryKey.setfOrderindex(parseObject.getInteger("orderIndex"));
		selectByPrimaryKey.setfOnePrice(parseObject.getDouble("onePrize"));
		selectByPrimaryKey.setfPrizeTypeName(parseObject
				.getString("prizeTypeName"));
		selectByPrimaryKey
				.setfPrizeTypeId(parseObject.getString("prizeTypeId"));

		int updateByPrimaryKey = ljflprizeService
				.updateByPrimaryKey(selectByPrimaryKey);

		if (updateByPrimaryKey == 1) {

			map.put("message", "修改成功");
		} else {

			map.put("message", "修改失败");
		}

		return map;
	}
	

	/**
	 * 获取奖品
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getprize", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getprize(HttpServletRequest request) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		List<LjflPrize> selectByBeenDeleted = ljflprizeService
				.selectByBeenDeleted();

		Gson gson = new Gson();
		String json = gson.toJson(selectByBeenDeleted);
		System.out.println(json);
		return json;
	}
	/**
	 * 获取奖品
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getprizeprovide", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getprizeprovide(HttpServletRequest request) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
	
		List<LjflPrizeProvide> selectPrizeProvideInfoListTotal1 = ljflprizeService.selectPrizeProvideInfoListTotal1();
		
		Gson gson = new Gson();
		String json = gson.toJson(selectPrizeProvideInfoListTotal1);
		System.out.println(json);
		return json;
	}

	
}
