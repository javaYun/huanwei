package com.sczh.platform.Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

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
import com.google.gson.Gson;
import com.sczh.platform.dao.LjflRecycleObjectMapper;
import com.sczh.platform.po.AreaCommunity;
import com.sczh.platform.po.AreaGov;
import com.sczh.platform.po.AreaRelation;
import com.sczh.platform.po.CarInfo;
import com.sczh.platform.po.CloudStaff;
import com.sczh.platform.po.DivisionTable;
import com.sczh.platform.po.LjflCollectRecord;
import com.sczh.platform.po.LjflDispatchTask;
import com.sczh.platform.po.LjflDispatchTaskBlobs;
import com.sczh.platform.po.LjflPutRecord;
import com.sczh.platform.po.LjflQuestion;
import com.sczh.platform.po.LjflRecycleAppointment;
import com.sczh.platform.po.LjflRecycleObject;
import com.sczh.platform.po.LjflRecycleRecord;
import com.sczh.platform.po.LjflRecycleType;
import com.sczh.platform.po.LjflResponseTimeConfig;
import com.sczh.platform.po.LjflSpillOverConfig;
import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.LjflStaffStatistic;
import com.sczh.platform.po.LjflSwipCardRecord;
import com.sczh.platform.po.LjflTrashCan;
import com.sczh.platform.po.LjflTrashCanWithBLOBs;
import com.sczh.platform.po.LjflTrashCanWorkPoint;
import com.sczh.platform.po.LjflType;
import com.sczh.platform.po.LjflWorkPoint;
import com.sczh.platform.po.PersonInfo;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.po.Model.ExportExcelFile;
import com.sczh.platform.po.Model.LjflStaffStaticModel;
import com.sczh.platform.po.Model.PageModelDomain;
import com.sczh.platform.service.AreaService;
import com.sczh.platform.service.CdsService;
import com.sczh.platform.service.LjflPutRecordService;
import com.sczh.platform.service.LjflScoreService;
import com.sczh.platform.service.LjflTrashCanWorkPointService;
import com.sczh.platform.service.PersonService;
import com.sczh.platform.service.UserService;
import com.yunlao.util.ExcelUtil;
import com.yunlao.util.MyLong;
import com.yunlao.util.TableUtil;
@Controller
@RequestMapping("putRecord")
public class LjflPutRecordController {
	
	@Autowired
	private LjflPutRecordService ljflPutRecordService;
	@Autowired
	private LjflScoreService ljflScoreService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private PersonService personService;
	@Autowired
	private CdsService cdsService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private LjflTrashCanWorkPointService ljflTrashCanWorkPointService;
	/**
	 * 进入投放记录页面
	 * 
	 * @param session
	 * @return
	 */
	/*@RequestMapping("joinTouFangRecorPage")
	public ModelAndView joinTouFangRecorPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("putRecord/joinTouFangRecorPage");
		mav.addObject("userId", userId);
		return mav;
	}*/
	/**
	 * 获取所有居户信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getHouseHoldData", method = RequestMethod.POST)
	public PageModelDomain getHouseHoldData(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
		if(pageModelDomain.getStaffName() ==null || pageModelDomain.getStaffName().isEmpty()){
			pageModelDomain.setStaffName(null);
		}
		int size  = ljflPutRecordService.selectHouseHoldTotalSize(pageModelDomain);
		page.setPageTotalSize(size);
		pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
	
		int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
		
		if(size <= endNum){
			pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
		}else{
			pageModelDomain.setEndNum(pageModelDomain.getPageSize());
		}
		List<LjflStaffDetail> ljflStaffDetailList = ljflPutRecordService.selectHouseHoldData(pageModelDomain);
		List<LjflStaffDetail> list = new ArrayList<LjflStaffDetail>();
		for (LjflStaffDetail l : ljflStaffDetailList) {
			AreaCommunity areaCommunity = areaService.selectByPrimaryKey(l.getfHouseholdId());
			if(areaCommunity != null ){
				l.setfHouseholdId(areaCommunity.getCommunityname());
				list.add(l);
			}
		}
	

		page.setStaffDetailList(list);
		return page;
		
	}
	
	/**
	 * 根据相关条件获取投放信息
	 * 分页查询
	 * 条件一 居民id  所属户id  时间段  都可以为空
	 */
	@ResponseBody
	@RequestMapping(value = "/queryTotalPutRecordDatas", method = RequestMethod.POST)
	public PageModelDomain queryTotalPutRecordDatas(@RequestBody PageModelDomain pageModelDomain){
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
		if(pageModelDomain.getfHouseholdId() ==null || pageModelDomain.getfHouseholdId().isEmpty()){
			pageModelDomain.setfHouseholdId(null);
		}
		int size = 0;//查询的所有条数
		List<LjflPutRecord> ljflPutRecordlist = new ArrayList<LjflPutRecord>();
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
				tableName = TableUtil.getTableNameString("ljfl_put_record", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflPutRecordService.selecPutRecordByDateTotalSize(pageModelDomain);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			ljflPutRecordlist = ljflPutRecordService.selecPutRecordByDateTotal(pageModelDomain);
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
				list = TableUtil.getTableName("ljfl_put_record",startDate,endDate);
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
				int datesize =ljflPutRecordService.selecPutRecordByDateTotalSize((pageModelDomain));
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			if(size > 1024*63){
				page.setResult("no");
				return page;
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
				if(ljflPutRecordlist.size()>=pageneedSize){
					break;
				}
				List<LjflPutRecord> forputRecordList = new ArrayList<LjflPutRecord>();
				DivisionTable divisionTable = tableList.get(i);
				pageModelDomain.setTableName(divisionTable.getTableName());
				upTotalSize = currentTableSize;
				currentTableSize = currentTableSize+divisionTable.getSize();
				if(currentTableSize <= currentForMiniNum){//如果当前表的累加数据，小于当前最小值，则跳过
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
					currentPageSize = pageneedSize - ljflPutRecordlist.size();
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
				
				forputRecordList = ljflPutRecordService.selecPutRecordByDateTotal(pageModelDomain);
				ljflPutRecordlist.addAll(forputRecordList);
				if(isNextTable){
					istheNextTable = true;
					pageModelDomain.setPageSize(residuePageSize);
				}

			}
			
		}
		int num = 0;
		List<LjflPutRecord> list = new ArrayList<LjflPutRecord>();
		for (LjflPutRecord ljflputRecord : ljflPutRecordlist) {
			num++;
			if(num>pageIsSize){
				break;
			}
			LjflStaffDetail staff = ljflScoreService.selectStaffDetailById(ljflputRecord.getfStaffDetailId());
			LjflType ljflType = ljflScoreService.selectLjflTypeById(ljflputRecord.getfConfirmRubbishTypeId());
			ljflputRecord.setfConfirmRubbishTypeId(ljflType.getfName());
			ljflputRecord.setfHouseholdId(staff.getDoorname());
			ljflputRecord.setfStaffDetailId(staff.getName());
			list.add(ljflputRecord);
		}
		page.setPageTotalNum(size);
		page.setLjflPutRecordList(list);
		return page;
		
	}
	/**
	 * "startTime":$("#startTime").val(),
	"endTime":$("#endTime").val(),
	"staffDetailId":$("#staffDetailId").val(),
	"fHouseholdId":$("#ljflHouseHoldId").val(),
	 */
	/**
	 * 导出excel数据文件(投放记录查询)
	 * 
	 * @param pageModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportPutRecordExcel")
	public String exportScoreDetailExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		PageModelDomain pageModel = new PageModelDomain();
		/**
		 * 封装PageModelDomain
		 */
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String staffDetailId = request.getParameter("staffDetailId");
		String fHouseholdId = request.getParameter("fHouseholdId");
		pageModel.setStartTime(startTime);
		pageModel.setEndTime(endTime);
	

		pageModel.setStaffDetailId(staffDetailId);
		pageModel.setfHouseholdId(fHouseholdId);
		//PageModelDomain page = getLjflScoreExportExcel(pageModel);
		PageModelDomain page = getPutRecordExportExcel(pageModel);
		/**
		 * 获取到数据
		 */
		List<LjflPutRecord> putRecordList = page.getLjflPutRecordList();

		// 填充Objects数据
		// List<Object> Objects=createData(request);
		List<Map<String, Object>> list = createExcelDetail(putRecordList);
		String columnNames[] = { "id", "居民姓名", "所属户", "投放时间", "垃圾类型", "重量" };// 列名
		String keys[] = { "id", "fStaffDetailId", "fHouseholdId", "createtime", "fConfirmRubbishTypeId", "fWeight"};// map中的key
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
		String fileName = "投放记录表";
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
	private PageModelDomain getPutRecordExportExcel(PageModelDomain pageModelDomain) {
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
		if(pageModelDomain.getfHouseholdId() == null || pageModelDomain.getfHouseholdId().isEmpty()){
			pageModelDomain.setfHouseholdId(null);
		}
		int size = 0;//查询的所有条数
		List<LjflPutRecord> ljflPutRecordlist = new ArrayList<LjflPutRecord>();
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
				tableName = TableUtil.getTableNameString("ljfl_put_record", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflPutRecordService.selecPutRecordByDateTotalSize(pageModelDomain);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			ljflPutRecordlist = ljflPutRecordService.selecPutRecordByDateTotal(pageModelDomain);
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
				list = TableUtil.getTableName("ljfl_put_record",startDate,endDate);
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
				int datesize =ljflPutRecordService.selecPutRecordByDateTotalSize((pageModelDomain));
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			for (int i = 0; i < tableList.size(); i++) {
				DivisionTable divisionTable = tableList.get(i);
				
				List<LjflPutRecord> forPutRecordList = new ArrayList<LjflPutRecord>();
			
				pageModelDomain.setTableName(divisionTable.getTableName());
					
				forPutRecordList = ljflPutRecordService.selecPutRecordByDateTotal(pageModelDomain);
				ljflPutRecordlist.addAll(forPutRecordList);
			}
		}
		List<LjflPutRecord> list = new ArrayList<LjflPutRecord>();
		for (LjflPutRecord ljflputRecord : ljflPutRecordlist) {
			
			LjflStaffDetail staff = ljflScoreService.selectStaffDetailById(ljflputRecord.getfStaffDetailId());
			LjflType ljflType = ljflScoreService.selectLjflTypeById(ljflputRecord.getfConfirmRubbishTypeId());
			ljflputRecord.setfConfirmRubbishTypeId(ljflType.getfName());
			ljflputRecord.setfHouseholdId(staff.getDoorname());
			ljflputRecord.setfStaffDetailId(staff.getName());
			list.add(ljflputRecord);
		}
		page.setPageTotalNum(size);
		page.setLjflPutRecordList(list);
		return page;
	}
	private List<Map<String, Object>> createExcelDetail(List<LjflPutRecord> putRecordList) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "活动积分表");
		listmap.add(map);
		LjflPutRecord putRecord = null;
		for (int j = 0; j < putRecordList.size(); j++) {
			putRecord = putRecordList.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("id", putRecord.getId());
			mapValue.put("fStaffDetailId", putRecord.getfStaffDetailId());
			mapValue.put("fHouseholdId", putRecord.getfHouseholdId());
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			mapValue.put("createtime",dateFormater.format(putRecord.getCreatetime()));
			mapValue.put("fConfirmRubbishTypeId", putRecord.getfConfirmRubbishTypeId());
			mapValue.put("fWeight", putRecord.getfWeight());
			listmap.add(mapValue);
		}
		return listmap;
	}
	/**
	 * 进入回收记录页面
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("joinRecyleRecodePage")
	public ModelAndView joinRecyleRecodePage(HttpSession session) {
		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("putRecord/joinRecyleRecodePage");
		mav.addObject("userId", userId);
		List<LjflRecycleObject> ljflRecyObjectList = ljflPutRecordService.selectLjflObjectListByParamters(0);
		mav.addObject("ljflRecyObjectList", ljflRecyObjectList);
		return mav;
	}*/
	/**
	 * 根据相关条件获取回收记录数据
	 * beendeleted
	 * 分页查询
	 * 条件一 居民id  所属户id  时间段  都可以为空
	 */
	@ResponseBody
	@RequestMapping(value = "/queryTotalRecycleRecordDatas", method = RequestMethod.POST)
	public PageModelDomain queryTotalRecycleRecordDatas(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
		if(pageModelDomain.getStaffDetailId() ==null || pageModelDomain.getStaffDetailId().isEmpty()){//居民id
			pageModelDomain.setStaffDetailId(null);
		}
		if(pageModelDomain.getfRecycleObjectId() ==null || pageModelDomain.getfRecycleObjectId().isEmpty()||"-1".equals(pageModelDomain.getfRecycleObjectId())){//居民id
			pageModelDomain.setfRecycleObjectId(null);
		}else{
			String getfRecycleObjectId = pageModelDomain.getfRecycleObjectId();
			LjflRecycleObject selectRecycleObjectbyname = ljflPutRecordService.selectRecycleObjectbyname(getfRecycleObjectId);
			String id = selectRecycleObjectbyname.getId();
			System.out.println("id========"+id);
			pageModelDomain.setfRecycleObjectId(id);
		}
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getfScoreType() ==null || pageModelDomain.getfScoreType().isEmpty()||"-1".equals(pageModelDomain.getfScoreType())){
			pageModelDomain.setfScoreType(null);
		}
		
		
		
		int size = 0;//查询的所有条数
		List<LjflRecycleRecord> ljflRecycleRecordlist = new ArrayList<LjflRecycleRecord>();
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
				tableName = TableUtil.getTableNameString("ljfl_recycle_record", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflPutRecordService.selecRecycycleRecordByDateTotalSize(pageModelDomain);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			if(size<=0){
				return page;
			}
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			ljflRecycleRecordlist = ljflPutRecordService.selecRecycleRecordByDateTotal(pageModelDomain);
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
				list = TableUtil.getTableName("ljfl_recycle_record",startDate,endDate);
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
				int datesize =ljflPutRecordService.selecRecycycleRecordByDateTotalSize((pageModelDomain));
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			if(size > 1024*63){
				page.setResult("no");
				return page;
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
				if(ljflRecycleRecordlist.size()>=pageneedSize){
					break;
				}
				List<LjflRecycleRecord> forRecycleRecordList = new ArrayList<LjflRecycleRecord>();
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
					currentPageSize = pageneedSize - ljflRecycleRecordlist.size();
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
				
				forRecycleRecordList = ljflPutRecordService.selecRecycleRecordByDateTotal(pageModelDomain);
				ljflRecycleRecordlist.addAll(forRecycleRecordList);
				if(isNextTable){
					istheNextTable = true;
					pageModelDomain.setPageSize(residuePageSize);
				}

			}
			
		}
		int num = 0;
		List<LjflRecycleRecord> list = new ArrayList<LjflRecycleRecord>();
		for (LjflRecycleRecord ljflRecycleRecord : ljflRecycleRecordlist) {
			num++;
			if(num>pageIsSize){
				break;
			}
			LjflStaffDetail staff = ljflScoreService.selectStaffDetailById(ljflRecycleRecord.getfStaffDetailId());
			LjflRecycleObject object = ljflPutRecordService.selectRecycleObjectById(ljflRecycleRecord.getfRecycleObjectId());
			ljflRecycleRecord.setfStaffDetailId(staff.getName());
			ljflRecycleRecord.setfRecycleObjectId(object.getfName());
			if("0".equals(ljflRecycleRecord.getfScoreType())){
				ljflRecycleRecord.setfScoreType("数量");
			}else if("1".equals(ljflRecycleRecord.getfScoreType())){
				ljflRecycleRecord.setfScoreType("重量");
			}else if("2".equals(ljflRecycleRecord.getfScoreType())){
				ljflRecycleRecord.setfScoreType("估量");
			}
			list.add(ljflRecycleRecord);
		}
		page.setPageTotalNum(size);
		page.setLjflRecycleRecordList(list);
		return page;
		
	}
	
	/**
	 * "startTime":$("#startTime").val(),
	"endTime":$("#endTime").val(),
	"staffDetailId":$("#staffDetailId").val(),
	"fHouseholdId":$("#ljflHouseHoldId").val(),
	 */
	/**
	 * 导出excel数据文件(投放记录查询)
	 * 
	 * @param pageModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportRecycleRecordExcel")
	public String exportRecycleRecordExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		PageModelDomain pageModel = new PageModelDomain();
		/**
		 * 封装PageModelDomain
		 */
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String staffDetailId = request.getParameter("staffDetailId");
		String beendeleted = request.getParameter("beendeleted");
		String fRecycleObjectId = request.getParameter("fRecycleObjectId");
		LjflRecycleObject selectRecycleObjectbyname = ljflPutRecordService.selectRecycleObjectbyname(fRecycleObjectId);
		String RecycleObjectId = selectRecycleObjectbyname.getId();
		String fScoreType = request.getParameter("fScoreType");
		pageModel.setStartTime(startTime);
		pageModel.setEndTime(endTime);
	

		pageModel.setStaffDetailId(staffDetailId);
		pageModel.setBeendeleted(beendeleted);
		pageModel.setfRecycleObjectId(RecycleObjectId);
		pageModel.setfScoreType(fScoreType);
		//PageModelDomain page = getLjflScoreExportExcel(pageModel);
		PageModelDomain page = getRecycleRecordExportExcel(pageModel);
		/**
		 * 获取到数据
		 */
		List<LjflRecycleRecord> recycleRecordList = page.getLjflRecycleRecordList();

		// 填充Objects数据
		// List<Object> Objects=createData(request);
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(d);
		String fileName = dateNowStr+"回收记录表";
		List<Map<String, Object>> list = createRecycleDetail(recycleRecordList,fileName);
		String columnNames[] = { "id", "物品名称", "所属居民", "积分方式", "单位数值", "本次总积分" , "确认时间"};// 列名
		String keys[] = { "id", "fRecycleObjectId", "fStaffDetailId", "fScoreType", "fUnitValue", "fTotalScoreValue", "createtime"};// map中的key
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
	private List<Map<String, Object>> createRecycleDetail(List<LjflRecycleRecord> recycleRecordList, String fileName) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("sheetName", fileName);
		listmap.add(map);
		LjflRecycleRecord Record = null;
		for (int j = 0; j < recycleRecordList.size(); j++) {
			Record = recycleRecordList.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("id", Record.getId());
			mapValue.put("fRecycleObjectId", Record.getfStaffDetailId());
			mapValue.put("fStaffDetailId", Record.getfStaffDetailId());
			mapValue.put("fScoreType", Record.getfScoreType());
			mapValue.put("fUnitValue", Record.getfUnitValue());
			mapValue.put("fTotalScoreValue", Record.getfTotalScoreValue());
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			mapValue.put("createtime",dateFormater.format(Record.getCreatetime()));
			listmap.add(mapValue);
		}
		return listmap;
	}
	private PageModelDomain getRecycleRecordExportExcel(PageModelDomain pageModelDomain) {
		PageModelDomain page = new PageModelDomain();
		if(pageModelDomain.getStaffDetailId() ==null || pageModelDomain.getStaffDetailId().isEmpty()){//居民id
			pageModelDomain.setStaffDetailId(null);
		}
		if(pageModelDomain.getfRecycleObjectId() ==null || pageModelDomain.getfRecycleObjectId().isEmpty()||"-1".equals(pageModelDomain.getfRecycleObjectId())){//居民id
			pageModelDomain.setfRecycleObjectId(null);
		}
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getfScoreType() ==null || pageModelDomain.getfScoreType().isEmpty()||"-1".equals(pageModelDomain.getfScoreType())){
			pageModelDomain.setfScoreType(null);
		}
		int size = 0;//查询的所有条数
		List<LjflRecycleRecord> ljflRecycleRecordlist = new ArrayList<LjflRecycleRecord>();
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
				tableName = TableUtil.getTableNameString("ljfl_recycle_record", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflPutRecordService.selecRecycycleRecordByDateTotalSize(pageModelDomain);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			if(size<=0){
				return page;
			}
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			ljflRecycleRecordlist = ljflPutRecordService.selecRecycleRecordByDateTotal(pageModelDomain);
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
				list = TableUtil.getTableName("ljfl_recycle_record",startDate,endDate);
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
				int datesize =ljflPutRecordService.selecRecycycleRecordByDateTotalSize((pageModelDomain));
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			if(size > 1024*63){
				page.setResult("no");
				return page;
			}

			for (int i = 0; i < tableList.size(); i++) {
				DivisionTable divisionTable = tableList.get(i);

				List<LjflRecycleRecord> forRecycleRecordList = new ArrayList<LjflRecycleRecord>();
			
				pageModelDomain.setTableName(divisionTable.getTableName());
					
				forRecycleRecordList = ljflPutRecordService.selecRecycleRecordByDateTotal(pageModelDomain);
				ljflRecycleRecordlist.addAll(forRecycleRecordList);
			}
				
		}

		List<LjflRecycleRecord> list = new ArrayList<LjflRecycleRecord>();
		for (LjflRecycleRecord ljflRecycleRecord : ljflRecycleRecordlist) {
		
			LjflStaffDetail staff = ljflScoreService.selectStaffDetailById(ljflRecycleRecord.getfStaffDetailId());
			LjflRecycleObject object = ljflPutRecordService.selectRecycleObjectById(ljflRecycleRecord.getfRecycleObjectId());
			ljflRecycleRecord.setfStaffDetailId(staff.getName());
			ljflRecycleRecord.setfRecycleObjectId(object.getfName());
			if("0".equals(ljflRecycleRecord.getfScoreType())){
				ljflRecycleRecord.setfScoreType("数量");
			}else if("1".equals(ljflRecycleRecord.getfScoreType())){
				ljflRecycleRecord.setfScoreType("重量");
			}else if("2".equals(ljflRecycleRecord.getfScoreType())){
				ljflRecycleRecord.setfScoreType("估量");
			}
			list.add(ljflRecycleRecord);
		}
		page.setPageTotalNum(size);
		page.setLjflRecycleRecordList(list);
		return page;
	}

	/**
	 * 进入回收物品页面
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("joinRecyleThingPage")
	public ModelAndView joinRecyleThingPage(HttpSession session) {
		String userId = session.getAttribute("userid").toString();
		ModelAndView mav = new ModelAndView("putRecord/joinRecyleThingPage");
		mav.addObject("userId", userId);
		List<LjflRecycleType> list = ljflPutRecordService.selectLjflTypeListByParamters(0);
		mav.addObject("LjflRecycleTypeList", list);
		return mav;
	}*/
	
	/**
	 * 根据相关条件获取投放信息
	 * 分页查询
	 * 条件一 居民id  所属户id  时间段  都可以为空
	 */
	@ResponseBody
	@RequestMapping(value = "/queryRecycleObject", method = RequestMethod.POST)
	public PageModelDomain queryRecycleObject(@RequestBody PageModelDomain pageModelDomain){
		if(pageModelDomain.getfCode() ==null || pageModelDomain.getfCode().isEmpty()){//居民id
			pageModelDomain.setfCode(null);
		}
		if(pageModelDomain.getfName() ==null || pageModelDomain.getfName().isEmpty()){
			pageModelDomain.setfName(null);
		}
		if(pageModelDomain.getfRecycleTypeId() ==null || pageModelDomain.getfRecycleTypeId().isEmpty()||"-1".equals(pageModelDomain.getfRecycleTypeId())){
			pageModelDomain.setfRecycleTypeId(null);
		}else{
			
			String getfRecycleTypeId = pageModelDomain.getfRecycleTypeId();
			LjflRecycleType selectRecycletypebyname = ljflPutRecordService.selectrecycletypebyname(getfRecycleTypeId);
			String id = selectRecycletypebyname.getId();
			pageModelDomain.setfRecycleTypeId(id);
		}
		int size = ljflPutRecordService.selectRecycleObjectByPamaterSize(pageModelDomain);//总的数据
		pageModelDomain.setPageTotalNum(size);
		int currentNum =  (pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize();//此次获取的第一个数据
		int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();//此次获取的可能性最后一个数据
		int pageSize = pageModelDomain.getPageSize();
		if(endNum>=size && currentNum<size){//当最后一条不满足的时候
			pageSize = size-currentNum;
		}
		pageModelDomain.setCurrentNum(currentNum);
		pageModelDomain.setPageSize(pageSize);
		List<LjflRecycleObject> list = ljflPutRecordService.selectRecycleObjectByPamaters(pageModelDomain);
		List<LjflRecycleObject> list1 = new ArrayList<LjflRecycleObject>();
		for (LjflRecycleObject l : list) {
			LjflRecycleType type = null;
			try {
				type = ljflPutRecordService.selectLjflRecycleTypeById(l.getfRecycleTypeId());
				l.setfRecycleTypeId(type.getfName());
			} catch (Exception e) {
				
				l.setfRecycleTypeId("");
			}
			list1.add(l);
		}
		pageModelDomain.setLjflRecycleObjectList(list1);
		return pageModelDomain;
	}
	/**
	 * 保存回收物品数据
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertRecycleObject",method=RequestMethod.POST)
	public  Map<String, String>  insertRecycleObject(@RequestBody LjflRecycleObject object){//添加到数据库表departmentmanage
		Map<String, String> map = new HashMap<String,String>();
		String id = MyLong.getRandStr(11);
		
		String getfRecycleTypeId = object.getfRecycleTypeId();
		if (getfRecycleTypeId==null||getfRecycleTypeId.equals("")) {
			
		}else{
			LjflRecycleType selectRecycletypebyname = ljflPutRecordService.selectrecycletypebyname(getfRecycleTypeId);
			String id2 = selectRecycletypebyname.getId();
			object.setfRecycleTypeId(id2);
		}
		
		object.setId(id);
		object.setTenantid("4eaf9423eb0043648e502134586b088a");
		object.setBeendeleted(0);
		int i =  ljflPutRecordService.insertRecycleObject(object);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 获取回收物品信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getRecycleObjectById",method=RequestMethod.POST)
	public  LjflRecycleObject  getRecycleObjectById(@RequestBody String ids){//添加到数据库表departmentmanage
		JSONObject json = JSONObject.parseObject(ids);
		String id = json.getString("id");
		LjflRecycleObject l = ljflPutRecordService.selectRecycleObjectById(id);
		return l;
	}
	/**
	 * 更新回收物品数据
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateRecycleObject",method=RequestMethod.POST)
	public  Map<String, String>  updateRecycleObject(@RequestBody LjflRecycleObject object){//添加到数据库表departmentmanage
		Map<String, String> map = new HashMap<String,String>();

		String getfRecycleTypeId = object.getfRecycleTypeId();
		System.out.println(getfRecycleTypeId);
		if (getfRecycleTypeId==null||getfRecycleTypeId.equals("")) {
			
		}else{
			LjflRecycleType selectRecycleTypebyname = ljflPutRecordService.selectrecycletypebyname(getfRecycleTypeId);
			String id2 = selectRecycleTypebyname.getId();
			object.setfRecycleTypeId(id2);
		}
		
		
		object.setTenantid("4eaf9423eb0043648e502134586b088a");
		object.setBeendeleted(0);
		int i =  ljflPutRecordService.updateRecycleObject(object);
		if(i==1){
			map.put("result", "更新成功");
			
		}else{
			
			map.put("result", "更新失败");
		}
		return map;
	}
	/**
	 * 删除回收物品数据
	 * @param carInfo
	 * @return
	 */
	/**
	 * 删除角色信息，可以批量删除
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteRecycleData", method = RequestMethod.POST)
	public Map<String, String> deleteRecycleData(@RequestBody LjflRecycleObject object){
		Map<String, String> map =new HashMap<String,String>();
		String str = "Y";
		try {
			String ids = object.getId();
			String[] id = ids.split(",");
			for (String string : id) {
				ljflPutRecordService.deleteRecycleObjectById(string);
			}
			map.put("result", str);
		} catch (Exception e) {
			str = "N";
			map.put("result", str);
		}
		
		return map;
		
	}
	/**
	 * 进入回收物品类型页面
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("joinRecyleTypePage")
	public ModelAndView joinRecyleTypePage(HttpSession session) {
		String userId = session.getAttribute("userid").toString();
		ModelAndView mav = new ModelAndView("putRecord/joinRecyleTypePage");
		mav.addObject("userId", userId);
	
		return mav;
	}*/
	/**
	*获取回收类型数据
	 */
	@ResponseBody
	@RequestMapping(value = "/queryRecycleType", method = RequestMethod.POST)
	public PageModelDomain queryRecycleType(@RequestBody PageModelDomain pageModelDomain){
		if(pageModelDomain.getfCode() ==null || pageModelDomain.getfCode().isEmpty()){//居民id
			pageModelDomain.setfCode(null);
		}
		if(pageModelDomain.getfName() ==null || pageModelDomain.getfName().isEmpty()){
			pageModelDomain.setfName(null);
		}
		int size = ljflPutRecordService.selectRecycleTypeByPamaterSize(pageModelDomain);//总的数据
		pageModelDomain.setPageTotalNum(size);
		int currentNum =  (pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize();//此次获取的第一个数据
		int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();//此次获取的可能性最后一个数据
		int pageSize = pageModelDomain.getPageSize();
		if(endNum>=size && currentNum<size){//当最后一条不满足的时候
			pageSize = size-currentNum;
		}
		pageModelDomain.setCurrentNum(currentNum);
		pageModelDomain.setPageSize(pageSize);
		List<LjflRecycleType> list = ljflPutRecordService.selectRecycleTypeByPamaters(pageModelDomain);
//		List<LjflRecycleObject> list1 = new ArrayList<LjflRecycleObject>();
//		for (LjflRecycleObject l : list) {
//			LjflRecycleType type= ljflPutRecordService.selectLjflRecycleTypeById(l.getfRecycleTypeId());
//			l.setfRecycleTypeId(type.getfName());
//			list1.add(l);
//		}
		pageModelDomain.setLjflRecycleTypelist(list);
		return pageModelDomain;
	}
	/**
	 * 保存回收类型数据
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertRecycleType",method=RequestMethod.POST)
	public  Map<String, String>  insertRecycleType(@RequestBody LjflRecycleType type){//添加到数据库表departmentmanage
		Map<String, String> map = new HashMap<String,String>();
		String id = MyLong.getRandStr(11);
		type.setId(id);
		type.setTenantid("4eaf9423eb0043648e502134586b088a");
		type.setBeendeleted(0);
		Date d = new Date();
		type.setCreatetime(d);
		int i =  ljflPutRecordService.insertRecycletype(type);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 获取回收类型信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getRecycleTypeById",method=RequestMethod.POST)
	public  LjflRecycleType  getRecycleTypeById(@RequestBody String ids){//添加到数据库表departmentmanage
		JSONObject json = JSONObject.parseObject(ids);
		String id = json.getString("id");
		LjflRecycleType l = ljflPutRecordService.selectLjflRecycleTypeById(id);
		return l;
	}
	/**
	 * 更新回收类型数据
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateRecycleType",method=RequestMethod.POST)
	public  Map<String, String>  updateRecycleType(@RequestBody LjflRecycleType type){//添加到数据库表departmentmanage
		Map<String, String> map = new HashMap<String,String>();
		LjflRecycleType l = ljflPutRecordService.selectLjflRecycleTypeById(type.getId());
		type.setTenantid("4eaf9423eb0043648e502134586b088a");
		type.setBeendeleted(0);
		type.setCreatetime(l.getCreatetime());
		int i =  ljflPutRecordService.updateRecycleType(type);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 删除回收物品类型数据
	 * @param carInfo
	 * @return
	 */
	/**
	 * 删除角色信息，可以批量删除
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteRecycleTypeData", method = RequestMethod.POST)
	public Map<String, String> deleteRecycleTypeData(@RequestBody LjflRecycleType type){
		Map<String, String> map =new HashMap<String,String>();
		String str = "Y";
		try {
			String ids = type.getId();
			String[] id = ids.split(",");
			for (String string : id) {
				ljflPutRecordService.deleteRecycleTypeById(string);
			}
			map.put("result", str);
		} catch (Exception e) {
			str = "N";
			map.put("result", str);
		}
		
		return map;
		
	}
	
	
	/**
	 * 进入问题上报页面
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("joinQuesttionPage")
	public ModelAndView joinQuesttionPage(HttpSession session) {
		String userId = session.getAttribute("userid").toString();
		ModelAndView mav = new ModelAndView("putRecord/joinQuesttionPage");
		mav.addObject("userId", userId);
		Map<String, String> map = new HashMap<String,String>();
		map.put("beenDeleteId", "0");
		map.put("tenantid", "4eaf9423eb0043648e502134586b088a");
		List<AreaCommunity> areaList = areaService.selectareaCommunities();
//		List<LjflHouses> houseList = ljflPutRecordService.selectHouseListByParamters(map);
//		List<LjflHouses> houses = new ArrayList<LjflHouses>();
//		for (LjflHouses ljflHouses : houseList) {
//			LjflBase base = ljflPutRecordService.selectLjflBaseById(ljflHouses.getfLjflBaseId());
//			ljflHouses.setfLjflBaseId(base.getfName());
//			houses.add(ljflHouses);
//		}
		mav.addObject("areaList", areaList);
//		mav.addObject("houses", houses);
	
		return mav;
	}*/
	
	/**
	 * 根据相关条件获取投放信息
	 * 分页查询
	 * 条件一 居民id  所属户id  时间段  都可以为空
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLjflQuestion", method = RequestMethod.POST)
	public PageModelDomain queryLjflQuestion(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
	
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getfHandlingStatus() ==null || pageModelDomain.getfHandlingStatus().isEmpty()||("-1").equals( pageModelDomain.getfHandlingStatus())){
			pageModelDomain.setfHandlingStatus(null);
		}
		int size = 0;//查询的所有条数
		List<LjflQuestion> ljflQuestionlist = new ArrayList<LjflQuestion>();
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
		int pageIsSize = pageModelDomain.getPageSize();
		if(pageModelDomain.getStartTime() == null){
			String tableName = "";
			try {
				tableName = TableUtil.getTableNameString("ljfl_question", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflPutRecordService.selecPutRecordByDateTotalSize(pageModelDomain);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			ljflQuestionlist = ljflPutRecordService.selecLjflQuestionByDateTotal(pageModelDomain);
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
				list = TableUtil.getTableName("ljfl_question",startDate,endDate);
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
				int datesize =ljflPutRecordService.selecLjflQuestionByDateTotalSize((pageModelDomain));
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			if(size > 1024*63){
				page.setResult("no");
				return page;
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
				if(ljflQuestionlist.size()>=pageneedSize){
					break;
				}
				List<LjflQuestion> forQuestionList = new ArrayList<LjflQuestion>();
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
					currentPageSize = pageneedSize - ljflQuestionlist.size();
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
				
				forQuestionList = ljflPutRecordService.selecLjflQuestionByDateTotal(pageModelDomain);
				ljflQuestionlist.addAll(forQuestionList);
				if(isNextTable){
					istheNextTable = true;
					pageModelDomain.setPageSize(residuePageSize);
				}

			}
			
		}
		int num = 0;
		List<LjflQuestion> list = new ArrayList<LjflQuestion>();
		for (LjflQuestion ljflQuestion : ljflQuestionlist) {
			num++;
			if(num>pageIsSize){
				break;
			}
			CloudStaff staff = ljflPutRecordService.selectCloudStaffById(ljflQuestion.getfStaffId());
			ljflQuestion.setfStaffId(staff.getName());
			LjflWorkPoint workPoint = ljflPutRecordService.selectLjflWorkPointById(ljflQuestion.getfWorkPointId());
			ljflQuestion.setfWorkPointId(workPoint.getfName());
//			LjflHouses house = ljflPutRecordService.selectLjflHouseById(ljflQuestion.getfHousesId());
//			LjflBase base = ljflPutRecordService.selectLjflBaseById(house.getfLjflBaseId());
			AreaCommunity areaCommunity =  areaService.selectByPrimaryKey(ljflQuestion.getfHousesId());
			ljflQuestion.setfHousesId(areaCommunity.getCommunityname());
			if("suspending".equals(ljflQuestion.getfHandlingStatus())){
				ljflQuestion.setfHandlingStatus("未处理");
			}else{
				ljflQuestion.setfHandlingStatus("已处理");
			}
			
			list.add(ljflQuestion);
		}
		page.setPageTotalNum(size);
		page.setLjflQuestionList(list);
		return page;
		
	}
	
	/**
	 * 根据相关条件获取桶站信息
	 * 分页查询
	 * beenDeleteId    fHouseid
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLjflWorkPointByPamaraters", method = RequestMethod.POST)
	public PageModelDomain queryLjflWorkPointByPamaraters(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
	
		if(pageModelDomain.getBeendeleted() ==null || pageModelDomain.getBeendeleted().isEmpty()){
			pageModelDomain.setBeendeleted(null);;
		}
		if(pageModelDomain.getfHousesId() ==null || pageModelDomain.getfHousesId().isEmpty()){
			pageModelDomain.setfHousesId(null);
		}
		Map<String, String> map = new HashMap<String,String>();
		map.put("beenDeleteId", pageModelDomain.getBeendeleted());
		map.put("tenantid", pageModelDomain.getTenantid());
		map.put("fHousesId", pageModelDomain.getfHousesId());
		List<LjflWorkPoint> list = ljflPutRecordService.selectLjflWorkPointListByPamaters(map);
		page.setLjflWorkPointList(list);
		return page;
		
	}
	
	/**
	 * 获取所有管理员信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getCloudStaffData", method = RequestMethod.POST)
	public PageModelDomain getCloudStaffData(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
		Map<String, String> map = new HashMap<String,String>();
		map.put("orgName",pageModelDomain.getOrgName());
		map.put("beenDeleteId", pageModelDomain.getBeendeleted());
		map.put("tenantid",pageModelDomain.getTenantid());
		map.put("beenDeleteId", pageModelDomain.getBeendeleted());
		int size  = ljflPutRecordService.selectCloudStaffSize(map);
		page.setPageTotalSize(size);
		pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
	
		int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
		
		if(size <= endNum){
			pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
		}else{
			pageModelDomain.setEndNum(pageModelDomain.getPageSize());
		}
		if(pageModelDomain.getEndNum() ==0){
			map.put("endNum",null);
		}else{
			map.put("endNum",pageModelDomain.getEndNum()+"");
		}

		map.put("currentNum",pageModelDomain.getCurrentNum()+"");
		
		List<CloudStaff> ljflCloudStaffList = ljflPutRecordService.selectCloudStaffData(map);
	

		page.setCloudStaffList(ljflCloudStaffList);
		return page;
		
	}
	/**
	 * 问题上报数据保存
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertLjflQuesttion",method=RequestMethod.POST)
	public  Map<String, String>  insertLjflQuesttion(@RequestBody LjflQuestion question){//添加到数据库表departmentmanage
		Map<String, String> map = new HashMap<String,String>();
		String id = MyLong.getRandStr(11);
		question.setId(id);
		String createTime = question.getfHandlingStatus();
		String tableName = "";
		try {
			tableName = TableUtil.getTableNameString("ljfl_question", createTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("id", id);
		map.put("beendeleted", question.getBeendeleted()+"");
		map.put("tenantid",question.getTenantid());
		map.put("tableName",tableName);
		map.put("fHousesId", question.getfHousesId());
		map.put("fWorkPointId",question.getfWorkPointId());
		map.put("fStaffId",question.getfStaffId());
		map.put("createtime", createTime);
		map.put("fHandlingStatus", "suspending");
	
		if(question.getfPhotoIds() == null || question.getfPhotoIds().isEmpty()){
			map.put("fPhotoIds",null);
		}else{
			map.put("fPhotoIds",question.getfPhotoIds());
		}
		if(question.getfLatitudeDone() == null || (question.getfLatitudeDone()+"").isEmpty()){
			map.put("fLatitudeDone",null);
		}else{
			map.put("fLatitudeDone",question.getfLatitudeDone()+"");
		}
		if(question.getfLongitudeDone() == null || (question.getfLongitudeDone()+"").isEmpty()){
			map.put("fLongitudeDone",null);
		}else{
			map.put("fLongitudeDone",question.getfLongitudeDone()+"");
		}

		int i =  ljflPutRecordService.insertLjflQuestion(map);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 获取问题上报信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getQuestionByPamaters",method=RequestMethod.POST)
	public  LjflQuestion  getQuestionByPamaters(@RequestBody PageModelDomain model){//添加到数据库表departmentmanage
	
		String chaxunTime =  model.getChaxunTime();
		String tableName = "";
		try {
			tableName = TableUtil.getTableNameString("ljfl_question", chaxunTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", tableName);
		map.put("id", model.getQuestionId());
		LjflQuestion ljflQuestion = ljflPutRecordService.selectLjflQuestionByPamaters(map);
		CloudStaff staff = ljflPutRecordService.selectCloudStaffById(ljflQuestion.getfStaffId());
		ljflQuestion.setTenantid(staff.getName());
		return ljflQuestion;
	}
	/**
	 * 更新问题上报信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateLjflQuestion",method=RequestMethod.POST)
	public  Map<String, String>  updateLjflQuestion(@RequestBody  LjflQuestion question){//添加到数据库表departmentmanage
	
		Map<String, String> map = new HashMap<String,String>();
		String createTime = question.getBusinesssystemid();
		String tableName = "";
		try {
			tableName = TableUtil.getTableNameString("ljfl_question", createTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("id", question.getId());
		map.put("beendeleted", question.getBeendeleted()+"");
		map.put("tenantid",question.getTenantid());
		map.put("tableName",tableName);
		map.put("fHousesId", question.getfHousesId());
		map.put("fWorkPointId",question.getfWorkPointId());
		map.put("fStaffId",question.getfStaffId());
		map.put("fHandlingStatus", question.getfHandlingStatus());
	
		map.put("fHandlingSuggestion", question.getfHandlingSuggestion());
		map.put("fPhotoIds",question.getfPhotoIds());
		map.put("fLatitudeDone",question.getfLatitudeDone()+"");
		map.put("fLongitudeDone",question.getfLongitudeDone()+"");
		
		int i =  ljflPutRecordService.updateLjflQuestion(map);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 获取问题上报信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/deleteLjflQuestionData",method=RequestMethod.POST)
	public  Map<String, String>   deleteLjflQuestionData(@RequestBody PageModelDomain model){//添加到数据库表departmentmanage
	
		String chaxunTime =  model.getChaxunTime();
		String tableName = "";
		try {
			tableName = TableUtil.getTableNameString("ljfl_question", chaxunTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", tableName);
		map.put("id", model.getQuestionId());
		int result = ljflPutRecordService.deleterLjflQuestion(map);
		map.put("result", result+"");
		return map;
	}
	
	/**
	 * 进入工作人员统计页面
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("joinWorkerTotalPage")
	public ModelAndView joinWorkerTotalPage(HttpSession session) {
		String userId = session.getAttribute("userid").toString();
		ModelAndView mav = new ModelAndView("putRecord/joinWorkerTotalPage");
		mav.addObject("userId", userId);
		return mav;
	}*/
	
	/**
	*获取回收类型数据
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLjflStaffStatisticData", method = RequestMethod.POST)
	public PageModelDomain queryLjflStaffStatisticData(@RequestBody PageModelDomain pageModelDomain){
		if(pageModelDomain.getStartTime()==null || pageModelDomain.getStartTime().isEmpty()){//居民id
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getStaffid() ==null || pageModelDomain.getStaffid().isEmpty()){
			pageModelDomain.setStaffid(null);
		}
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		String startTime = dateNowStr.substring(0, 10);
		if(pageModelDomain.getStartTime() !=null){
			startTime = pageModelDomain.getStartTime().substring(0, 10);
		}
		Map<String, String> map = new HashMap<String,String>();
		map.put("staffid", pageModelDomain.getStaffid());
		map.put("startTime", startTime);
		map.put("value", 0+"");
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		List<LjflStaffStatistic> staffStatisticList = ljflPutRecordService.selectStaffStaticListByPamaters(map);
		List<LjflStaffStaticModel> modelList = new ArrayList<LjflStaffStaticModel>();
		int num = 0;
		for (LjflStaffStatistic statistic : staffStatisticList) {
			LjflStaffStaticModel model = new LjflStaffStaticModel();
			num++;
			model.setXuhao(num);
			model.setId(statistic.getId());
			model.setStaffId(statistic.getStaffid());
			model.setStaffName(statistic.getStaffname());
			map.put("staffid", statistic.getStaffid());
			List<LjflStaffStatistic> realData = ljflPutRecordService.selectStaffStaticListByPamatersNoGroupBy(map);
			
			for (LjflStaffStatistic l : realData) {
				model.setPersontime(model.getPersontime()+l.getPersontime());
				if("餐厨".equals(l.getRubbishtypename())){
					model.setCanchu(Double.parseDouble(df.format(model.getCanchu()+l.getValue())));
				}
				if("厨余".equals(l.getRubbishtypename()) && ("投放系统".equals(l.getSystemtype())|| "回收系统".equals(l.getSystemtype()))){
					model.setChuyuzhongliang(Double.parseDouble(df.format(model.getChuyuzhongliang()+l.getValue())));
				}
				if("厨余".equals(l.getRubbishtypename()) && ("垃圾运输".equals(l.getSystemtype()))){
					model.setChuyu(Double.parseDouble(df.format(model.getChuyu()+l.getValue())));
				}
				if("废品".equals(l.getRubbishtypename())){
					model.setFeipin(Double.parseDouble(df.format(model.getFeipin()+l.getValue())));
				}
				if("大件2".equals(l.getRubbishtypename())){
					model.setDajian2(Double.parseDouble(df.format(model.getDajian2()+l.getValue())));
				}
				if("建筑".equals(l.getRubbishtypename())){
					model.setJianzhu(Double.parseDouble(df.format(model.getJianzhu()+l.getValue())));
				}
				if("废旧金属".equals(l.getRubbishtypename())){
					model.setFeijiujinshu(Double.parseDouble(df.format(model.getFeijiujinshu()+l.getValue())));
				}
				if("有害".equals(l.getRubbishtypename())){
					model.setYouhai(Double.parseDouble(df.format(model.getYouhai()+l.getValue())));
				}
				if("废旧资料".equals(l.getRubbishtypename())){
					model.setFeijiusiliao(Double.parseDouble(df.format(model.getFeijiusiliao()+l.getValue())));
				}
				if("园林".equals(l.getRubbishtypename())){
					model.setYuanlin(Double.parseDouble(df.format(model.getYuanlin()+l.getValue())));
				}
				if("其他".equals(l.getRubbishtypename())){
					model.setQita(Double.parseDouble(df.format(model.getQita()+l.getValue())));
				}
			}
			modelList.add(model);
			Collections.sort(modelList);
		}
		pageModelDomain.setLjflStaffStaticModelList(modelList);
		return pageModelDomain;
	}
	
	/**
	 * 导出excel数据文件(工作人员统计)
	 * 
	 * @param pageModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportLjflStaffStatistic")
	public String exportLjflStaffStatistic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		PageModelDomain pageModel = new PageModelDomain();
		/**
		 * 封装PageModelDomain
		 */
		String startTime = request.getParameter("startTime");
		String beendeleted = request.getParameter("beendeleted");
		String staffDetailId = request.getParameter("staffid");
		pageModel.setStartTime(startTime);
		pageModel.setBeendeleted(beendeleted);
		pageModel.setStaffid(staffDetailId);
		//PageModelDomain page = getLjflScoreExportExcel(pageModel);
		PageModelDomain page = queryLjflStaffStatisticData(pageModel);
		/**
		 * 获取到数据
		 */
		List<LjflStaffStaticModel> StaticModeldList = page.getLjflStaffStaticModelList();

		// 填充Objects数据
		// List<Object> Objects=createData(request);
		List<Map<String, Object>> list = createExcelStaticModeldList(StaticModeldList);
		String columnNames[] = { "序号", "姓名", "厨余人次", "厨余重量", "废品", "大件2", "建筑", "废旧金属", "有害", "废旧资料", "厨余","餐厨", "园林", "其他" };// 列名
		String keys[] = { "xuhao", "staffName", "persontime", "chuyuzhongliang", "feipin", "dajian2", "jianzhu", "feijiujinshu", "youhai", "feijiusiliao", "chuyu", "canchu", "yuanlin", "qita"};// map中的key
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
		String fileName = "工作人员统计表";
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
	
	private List<Map<String, Object>> createExcelStaticModeldList(List<LjflStaffStaticModel> StaticModeldList) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "活动积分表");
		listmap.add(map);
		LjflStaffStaticModel model = null;
		for (int j = 0; j < StaticModeldList.size(); j++) {
			model = StaticModeldList.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("xuhao", model.getXuhao());
			mapValue.put("persontime", model.getPersontime());
			mapValue.put("chuyuzhongliang", model.getChuyuzhongliang());
			mapValue.put("feipin", model.getFeipin());
			mapValue.put("dajian2", model.getDajian2());
			mapValue.put("jianzhu", model.getJianzhu());
			mapValue.put("feijiujinshu", model.getFeijiujinshu());
			mapValue.put("youhai", model.getYouhai());
			
			mapValue.put("feijiusiliao", model.getFeijiusiliao());
			mapValue.put("chuyu", model.getChuyu());
			mapValue.put("canchu", model.getCanchu());
			mapValue.put("yuanlin", model.getCanchu());
			mapValue.put("qita", model.getQita());

			listmap.add(mapValue);
		}
		return listmap;
	}
	/**
	 * 进入预约回收页面
	 * 
	 * @param session
	 * @return
	 */
	/*@RequestMapping("joinYuyueHuiShouPage")
	public ModelAndView joinYuyueHuiShouPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("putRecord/joinYuyueHuiShouPage");
		mav.addObject("userId", userId);
		return mav;
	}*/
	/**
	 * 根据相关条件获取预约回收信息
	 * 分页查询
	 */
	@ResponseBody
	@RequestMapping(value = "/queryRecycleAppoinmentByPamaters", method = RequestMethod.POST)
	public PageModelDomain queryRecycleAppoinmentByPamaters(@RequestBody PageModelDomain pageModelDomain){
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getfAppointmentStatus() ==null || pageModelDomain.getfAppointmentStatus().isEmpty()||"-1".equals(pageModelDomain.getfAppointmentStatus())){
			pageModelDomain.setfAppointmentStatus(null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		map.put("startTime", pageModelDomain.getStartTime());
		map.put("endTime", pageModelDomain.getEndTime());
		map.put("fAppointmentStatus", pageModelDomain.getfAppointmentStatus());
	
		int size = ljflPutRecordService.selectRecycleAppointSizeByPamater(map);//总的数据
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
		map.put("endNum", pageModelDomain.getPageSize());
		List<LjflRecycleAppointment> list = ljflPutRecordService.selectRecycleAppointByPamaters(map);
		List<LjflRecycleAppointment> list1 = new ArrayList<LjflRecycleAppointment>();
		for (LjflRecycleAppointment l : list) {
			LjflStaffDetail staff = ljflScoreService.selectStaffDetailById(l.getfStaffDetailId());
			l.setfStaffDetailId(staff.getName());
			list1.add(l);
		}
		pageModelDomain.setRecycleAppointmentList(list1);
		return pageModelDomain;
	}
	/**
	 * 修改预约回收信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateLjflRecycleAppointmentById",method=RequestMethod.POST)
	public  Map<String, String>   updateLjflRecycleAppointmentById(@RequestBody LjflRecycleAppointment appoint){//添加到数据库表departmentmanage
		Map<String, String> map = new HashMap<String, String>();
		LjflRecycleAppointment appointment = ljflPutRecordService.getLjflRecycleAppointmentById(appoint.getId());
		appointment.setfAppointmentStatus( appoint.getfAppointmentStatus());
		int result = ljflPutRecordService.updateAppointmentByPrimaryKey(appointment);
		map.put("result", result+"");
		return map;
		
	}
	/**
	 * 进入满溢状态 -- 垃圾桶菜单页面
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("joinTrashCanPage")
	public ModelAndView joinTrashCanPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("putRecord/joinTrashCanPage");
		mav.addObject("userId", userId);
		Map<String, String> map = new HashMap<String,String>();
		map.put("beenDeleteId", "0");
		map.put("tenantid", "4eaf9423eb0043648e502134586b088a");
		List<LjflWorkPoint> list = ljflPutRecordService.selectLjflWorkPointListByPamaters(map);
		mav.addObject("ljflWorkPointList", list);
		List<LjflType> listType = ljflPutRecordService.selectLjflTypeListByMap(map);
		mav.addObject("listType", listType);
		return mav;
	}*/
	/**
	 * 根据相关条件获取预约回收信息
	 * 分页查询
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLjflTrashCan", method = RequestMethod.POST)
	public PageModelDomain queryLjflTrashCan(@RequestBody PageModelDomain pageModelDomain){
		if(pageModelDomain.getfCode() ==null || pageModelDomain.getfCode().isEmpty()){
			pageModelDomain.setfCode(null);
		}
		if(pageModelDomain.getfName() ==null || pageModelDomain.getfName().isEmpty()){
			pageModelDomain.setfName(null);
		}
	
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("tenantid", pageModelDomain.getTenantid());
		map.put("fName", pageModelDomain.getfName());
		map.put("fCode", pageModelDomain.getfCode());
		map.put("beendeleted", pageModelDomain.getBeendeleted());
	
		int size = ljflPutRecordService.selectLjflTrashCanSizeByPamater(map);//总的数据
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
		map.put("endNum", pageModelDomain.getPageSize());

		List<LjflTrashCan> list = ljflPutRecordService.selectLjflTrashCanByPamaters(map);
		List<LjflTrashCan> list1 = new ArrayList<LjflTrashCan>();
		for (LjflTrashCan l : list) {
			LjflWorkPoint workPoint = ljflPutRecordService.selectLjflWorkPointById(l.getfWorkPointId());
			l.setfWorkPointId(workPoint.getfName());
			LjflType type= ljflPutRecordService.selectljflTypeById(l.getfLjflTypeId());
			l.setfLjflTypeId(type.getfName());
			list1.add(l);
		}
		pageModelDomain.setLjflTrashCanList(list1);
		return pageModelDomain;
	}
	/**
	 * 保存垃圾桶信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertLjflTrashCan",method=RequestMethod.POST)
	public  Map<String, String>  insertLjflTrashCan(@RequestBody LjflTrashCanWithBLOBs trashCan){//添加到数据库表departmentmanage
		Map<String, String> map = new HashMap<String,String>();
		String id = MyLong.getRandStr(11);
		trashCan.setId(id);
	
		
		int i =  ljflPutRecordService.insertLjflTrashCan(trashCan);
		
		map.put("result", i+"");
		return map;
	}
	
	/**
	 * 获取满溢 -- 垃圾桶信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getLjflTrashCanByPamaters",method=RequestMethod.POST)
	public  LjflTrashCanWithBLOBs  getLjflTrashCanByPamaters(@RequestBody LjflTrashCanWithBLOBs trashCan){//添加到数据库表departmentmanage
		LjflTrashCanWithBLOBs trash = ljflPutRecordService.selectLjflTrashCanById(trashCan.getId());
		CloudStaff staff = ljflPutRecordService.selectCloudStaffById(trash.getfClearStaffId());
		trash.setTenantid(staff.getName());
		return trash;
	}
	/**
	 * 更新问题上报信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateLjflTrashCan",method=RequestMethod.POST)
	public  Map<String, String>  updateLjflTrashCan(@RequestBody  LjflTrashCanWithBLOBs trashCan){//添加到数据库表departmentmanage
	
		Map<String, String> map = new HashMap<String,String>();
		int i =  ljflPutRecordService.updateLjflTrashCan(trashCan);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 更新垃圾桶信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/deleteLjflTrashCan",method=RequestMethod.POST)
	public  Map<String, String>  deleteLjflTrashCan(@RequestBody  LjflTrashCanWithBLOBs trashCan){//添加到数据库表departmentmanage
	
		Map<String, String> map = new HashMap<String,String>();
		LjflTrashCanWithBLOBs t = ljflPutRecordService.selectLjflTrashCanById(trashCan.getId());
		t.setBeendeleted(trashCan.getBeendeleted());
		int i =  ljflPutRecordService.updateLjflTrashCan(t);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 进入满溢状态 -- 垃圾桶菜单页面
	 * 
	 * @param session
	 * @return
	 */
	/*@RequestMapping("joinTrashCanStatusPage")
	public ModelAndView joinTrashCanStatusPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("putRecord/joinTrashCanStatusPage");
		mav.addObject("userId", userId);
//		Map<String, String> map = new HashMap<String,String>();
//		map.put("beenDeleteId", "0");
//		map.put("tenantid", "4eaf9423eb0043648e502134586b088a");
//		List<LjflWorkPoint> list = ljflPutRecordService.selectLjflWorkPointListByPamaters(map);
//		mav.addObject("ljflWorkPointList", list);
//		List<LjflType> listType = ljflPutRecordService.selectLjflTypeListByMap(map);
//		mav.addObject("listType", listType);
		return mav;
	}*/
	/**
	 * 获取满溢状态配置信息
	 * 分页查询
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLjflSpliOverConfig", method = RequestMethod.POST)
	public PageModelDomain queryLjflSpliOverConfig(@RequestBody PageModelDomain pageModelDomain){
		if(pageModelDomain.getfCode() ==null || pageModelDomain.getfCode().isEmpty()){
			pageModelDomain.setfCode(null);
		}
		if(pageModelDomain.getfName() ==null || pageModelDomain.getfName().isEmpty()){
			pageModelDomain.setfName(null);
		}
	
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("tenantid", pageModelDomain.getTenantid());
	
		map.put("beendeleted", pageModelDomain.getBeendeleted());
	
		int size = ljflPutRecordService.selectLjflSpliOverConfigSizeByPamater(map);//总的数据
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
		

		List<LjflSpillOverConfig> list = ljflPutRecordService.selectLjflSpillOverConfigByPamaters(map);
//		List<LjflTrashCan> list1 = new ArrayList<LjflTrashCan>();
//		for (LjflTrashCan l : list) {
//			LjflWorkPoint workPoint = ljflPutRecordService.selectLjflWorkPointById(l.getfWorkPointId());
//			l.setfWorkPointId(workPoint.getfName());
//			LjflType type= ljflPutRecordService.selectljflTypeById(l.getfLjflTypeId());
//			l.setfLjflTypeId(type.getfName());
//			list1.add(l);
//		}
		pageModelDomain.setLjflSpillOverConfigList(list);
		return pageModelDomain;
	}
	/**
	 * 保存满溢状态配置信息
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertLjflSpliOverConfig",method=RequestMethod.POST)
	public  Map<String, String>  insertLjflSpliOverConfig(@RequestBody LjflSpillOverConfig spliOverConfig){//添加到数据库表departmentmanage
		Map<String, String> map = new HashMap<String,String>();
		String id = MyLong.getRandStr(11);
		spliOverConfig.setId(id);
	
		
		int i =  ljflPutRecordService.insertLjflSpillOverConfig(spliOverConfig);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 获取满溢 -- 满溢状态配置信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getLjflSpliOverConfig",method=RequestMethod.POST)
	public  LjflSpillOverConfig  getLjflSpliOverConfig(@RequestBody LjflSpillOverConfig spliOverConfig){//添加到数据库表departmentmanage
		spliOverConfig = ljflPutRecordService.selectLjflSpillOverConfigById(spliOverConfig.getId());
		return spliOverConfig;
	}
	/**
	 * 更新满溢状态配置信息
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateLjflSpliOverConfig",method=RequestMethod.POST)
	public  Map<String, String>  updateLjflSpliOverConfig(@RequestBody  LjflSpillOverConfig spliOverConfig){//添加到数据库表departmentmanage
	
		Map<String, String> map = new HashMap<String,String>();
		int i =  ljflPutRecordService.updatespliOverConfig(spliOverConfig);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 更新垃圾桶信息
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/deleteLjflSpliOverConfig",method=RequestMethod.POST)
	public  Map<String, String>  deleteLjflSpliOverConfig(@RequestBody  LjflSpillOverConfig spliOverConfig){//添加到数据库表departmentmanage
	
		Map<String, String> map = new HashMap<String,String>();
		LjflSpillOverConfig t = ljflPutRecordService.selectspliOverConfigById(spliOverConfig.getId());
		t.setBeendeleted(spliOverConfig.getBeendeleted());
		int i =  ljflPutRecordService.updatespliOverConfig(t);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 进入满溢状态响应时间配置
	 * 
	 * @param session
	 * @return
	 */
/*	@RequestMapping("joinTimePage")
	public ModelAndView joinTimePage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("putRecord/joinTimePage");
		mav.addObject("userId", userId);
//		Map<String, String> map = new HashMap<String,String>();
//		map.put("beenDeleteId", "0");
//		map.put("tenantid", "4eaf9423eb0043648e502134586b088a");
//		List<LjflWorkPoint> list = ljflPutRecordService.selectLjflWorkPointListByPamaters(map);
//		mav.addObject("ljflWorkPointList", list);
//		List<LjflType> listType = ljflPutRecordService.selectLjflTypeListByMap(map);
//		mav.addObject("listType", listType);
		return mav;
	}	*/
	/**
	 * 进入满溢状态响应时间配置
	 * 
	 * 分页查询
	 */
	@ResponseBody
	@RequestMapping(value = "/queryResponseTimeConfig", method = RequestMethod.POST)
	public PageModelDomain queryResponseTimeConfig(@RequestBody PageModelDomain pageModelDomain){
		if(pageModelDomain.getfCode() ==null || pageModelDomain.getfCode().isEmpty()){
			pageModelDomain.setfCode(null);
		}
		if(pageModelDomain.getfName() ==null || pageModelDomain.getfName().isEmpty()){
			pageModelDomain.setfName(null);
		}
	
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("tenantid", pageModelDomain.getTenantid());
	
		map.put("beendeleted", pageModelDomain.getBeendeleted());
	
		int size = ljflPutRecordService.selectResponseTimeConfigSizeByPamater(map);//总的数据
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
		

		List<LjflResponseTimeConfig> list = ljflPutRecordService.selectResponseTimeConfigByPamaters(map);

		pageModelDomain.setLjflResponseTimeConfigList(list);
		return pageModelDomain;
	}
	/**
	 * 满溢任务响应时间信息配置
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/insertLjflResponseTimeConfig",method=RequestMethod.POST)
	public  Map<String, String>  insertLjflResponseTimeConfig(@RequestBody LjflResponseTimeConfig ResponseTimeConfig){//添加到数据库表departmentmanage
		Map<String, String> map = new HashMap<String,String>();
		String id = MyLong.getRandStr(11);
		ResponseTimeConfig.setId(id);
	
		
		int i =  ljflPutRecordService.insertLjflResponseTimeConfig(ResponseTimeConfig);
		
		map.put("result", i+"");
		return map;
	}
	
	/**
	 * 获取满溢 --满溢任务响应时间信息配置
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/getLjflResponseTimeConfig",method=RequestMethod.POST)
	public  LjflResponseTimeConfig  getLjflResponseTimeConfig(@RequestBody LjflResponseTimeConfig responseTimeConfig){//添加到数据库表departmentmanage
		responseTimeConfig = ljflPutRecordService.selectLjflResponseTimeConfigById(responseTimeConfig.getId());
		return responseTimeConfig;
	}
	/**
	 * 更新满溢任务响应时间信息配置
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/updateLjflResponseTimeConfig",method=RequestMethod.POST)
	public  Map<String, String>  updateLjflResponseTimeConfig(@RequestBody  LjflResponseTimeConfig responseTimeConfig){//添加到数据库表departmentmanage
	
		Map<String, String> map = new HashMap<String,String>();
		int i =  ljflPutRecordService.updateResponseTimeConfig(responseTimeConfig);
		
		map.put("result", i+"");
		return map;
	}
	/**
	 * 更新满溢任务响应时间信息配置
	 * @param carInfo
	 * @return
	 */
	@ResponseBody //@ResponseBody代表数据以json串的方式传递数据
	@RequestMapping(value = "/deleteLjflResponseTimeConfig",method=RequestMethod.POST)
	public  Map<String, String>  deleteLjflResponseTimeConfig(@RequestBody  LjflResponseTimeConfig responseTimeConfig){//添加到数据库表departmentmanage
	
		Map<String, String> map = new HashMap<String,String>();
		LjflResponseTimeConfig t = ljflPutRecordService.selectLjflResponseTimeConfigById(responseTimeConfig.getId());
		t.setBeendeleted(responseTimeConfig.getBeendeleted());
		int i =  ljflPutRecordService.updateResponseTimeConfig(t);
		
		map.put("result", i+"");
		return map;
	}
	
	
	/**
	 * 进入满溢状态   -- 收集记录
	 * 
	 * @param session
	 * @return
	 *//*
	@RequestMapping("joinShouJiRecordPage")
	public ModelAndView joinShouJiRecordPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("putRecord/joinShouJiRecordPage");
		mav.addObject("userId", userId);
		Map<String, String> map = new HashMap<String,String>();
		map.put("beenDeleteId", "0");
		map.put("tenantid", "4eaf9423eb0043648e502134586b088a");
		List<AreaCommunity> selectareaCommunities = areaService
				.selectareaCommunities();

		System.out
				.println("==========================" + selectareaCommunities);

		System.out.println(selectareaCommunities);
		
		mav.addObject("houses", selectareaCommunities);
		List<LjflType> listType = ljflPutRecordService.selectLjflTypeListByMap(map);
		mav.addObject("listType", listType);
		return mav;
	}	*/
	/**
	 * 根据相关条件收集信息
	 * 分页查询
	 * 条件一 居民id  所属户id  时间段  都可以为空
	 */
	@ResponseBody
	@RequestMapping(value = "/queryTotalCollectRecordDatas", method = RequestMethod.POST)
	public PageModelDomain queryTotalCollectRecordDatas(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
	
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getfLjflTypeId() ==null || pageModelDomain.getfLjflTypeId().isEmpty()|| "-1".equals(pageModelDomain.getfLjflTypeId())){//编码
			pageModelDomain.setfLjflTypeId(null);
		}else{
			
			String getfLjflTypeId = pageModelDomain.getfLjflTypeId();
			LjflType selectByname = ljflPutRecordService.selectByname(getfLjflTypeId);
			String id = selectByname.getId();
			pageModelDomain.setfLjflTypeId(id);
			
		}
		if(pageModelDomain.getHouseSelected() ==null || pageModelDomain.getHouseSelected().isEmpty() || "-1".equals(pageModelDomain.getHouseSelected())){//名称
			pageModelDomain.setHouseSelected(null);
		}else{
			
			String houseSelected = pageModelDomain.getHouseSelected();
			List<AreaCommunity> selectAreaCommunitsByName = areaService.selectAreaCommunitsByName(houseSelected);
			String id = selectAreaCommunitsByName.get(0).getId();
			
			pageModelDomain.setHouseSelected(id);
			
		}
//		if(pageModelDomain.getfQymanId() ==null || pageModelDomain.getfQymanId().isEmpty()){//编码
//			pageModelDomain.setfCode(null);
//		}
//		if(pageModelDomain.getfName() ==null || pageModelDomain.getfName().isEmpty()){//名称
//			pageModelDomain.setfName(null);
//		}
	
		int size = 0;//查询的所有条数
		List<LjflCollectRecord> ljflCollectlist = new ArrayList<LjflCollectRecord>();
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
				tableName = TableUtil.getTableNameString("ljfl_collect_record", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflPutRecordService.selecCollectRecordTotalSize(pageModelDomain);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			ljflCollectlist = ljflPutRecordService.selecCollectRecordByDateTotal(pageModelDomain);
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
				list = TableUtil.getTableName("ljfl_collect_record",startDate,endDate);
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
				int datesize =ljflPutRecordService.selecCollectRecordTotalSize((pageModelDomain));
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			if(size > 1024*63){
				page.setResult("no");
				return page;
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
				if(ljflCollectlist.size()>=pageneedSize){
					break;
				}
				List<LjflCollectRecord> forCollectRecordList = new ArrayList<LjflCollectRecord>();
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
					currentPageSize = pageneedSize - ljflCollectlist.size();
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
				
				forCollectRecordList = ljflPutRecordService.selecCollectRecordByDateTotal(pageModelDomain);
				ljflCollectlist.addAll(forCollectRecordList);
				if(isNextTable){
					istheNextTable = true;
					pageModelDomain.setPageSize(residuePageSize);
				}

			}
			
		}
		List<LjflCollectRecord> list = new ArrayList<LjflCollectRecord>();
		for (LjflCollectRecord l : ljflCollectlist) {
		
		LjflTrashCanWithBLOBs trashCan = ljflPutRecordService.selectLjflTrashCanById(l.getfTrashCanId());
		l.setfTrashCanId(trashCan.getfName());
		list.add(l);
		}
		page.setPageTotalNum(size);
		page.setLjflCollectRecordList(list);
		return page;
		
	}
	
	/**
	 * 导出excel数据文件(收集记录查询)
	 * 
	 * @param pageModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportLjflCollectExcel")
	public String exportLjflCollectExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		PageModelDomain pageModel = new PageModelDomain();
		/**
		 * 封装PageModelDomain
		 */
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String beendeleted = request.getParameter("beendeleted");
		String tenantid = request.getParameter("tenantid");
		String fQymanId = request.getParameter("fQymanId");
		String fCode = request.getParameter("fCode");
		String fName = request.getParameter("fName");
		String fLjflTypeId = request.getParameter("fLjflTypeId");
		String houseSelected = request.getParameter("houseSelected");
		pageModel.setStartTime(startTime);
		pageModel.setEndTime(endTime);
	

		pageModel.setBeendeleted(beendeleted);
		pageModel.setTenantid(tenantid);
		pageModel.setfQymanId(fQymanId);
		pageModel.setfCode(fCode);
		pageModel.setfName(fName);
		pageModel.setfLjflTypeId(fLjflTypeId);
		pageModel.setHouseSelected(houseSelected);
		//PageModelDomain page = getLjflScoreExportExcel(pageModel);
		PageModelDomain page = getLjflCollectExportExcel(pageModel);
		/**
		 * 获取到数据
		 */
		List<LjflCollectRecord> collectRecord = page.getLjflCollectRecordList();

		// 填充Objects数据
		// List<Object> Objects=createData(request);
		List<Map<String, Object>> list = createLjflCollectExcelDetail(collectRecord);
		String columnNames[] = { "id", "编码", "名称", "小区名", "垃圾类型", "重量(kg)","收运时间" , "清运人员","垃圾桶"};// 列名
		String keys[] = { "id", "fCode", "fName", "houseSelected", "fLjflTypeId", "fWeight", "startTime", "fQymanId", "trashCanId"};// map中的key
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
		String fileName = "收运记录表";
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
	private PageModelDomain getLjflCollectExportExcel(PageModelDomain pageModelDomain) {
		PageModelDomain page = new PageModelDomain();
		
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getfLjflTypeId() ==null || pageModelDomain.getfLjflTypeId().isEmpty()|| "-1".equals(pageModelDomain.getfLjflTypeId())){//编码
			pageModelDomain.setfLjflTypeId(null);
		}
		if(pageModelDomain.getHouseSelected() ==null || pageModelDomain.getHouseSelected().isEmpty() || "-1".equals(pageModelDomain.getHouseSelected())){//名称
			pageModelDomain.setHouseSelected(null);
		}
//		if(pageModelDomain.getfQymanId() ==null || pageModelDomain.getfQymanId().isEmpty()){//编码
//			pageModelDomain.setfCode(null);
//		}
//		if(pageModelDomain.getfName() ==null || pageModelDomain.getfName().isEmpty()){//名称
//			pageModelDomain.setfName(null);
//		}
	
		int size = 0;//查询的所有条数
		List<LjflCollectRecord> ljflCollectlist = new ArrayList<LjflCollectRecord>();
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
				tableName = TableUtil.getTableNameString("ljfl_collect_record", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflPutRecordService.selecCollectRecordTotalSize(pageModelDomain);
			
			

			ljflCollectlist = ljflPutRecordService.selecCollectRecordByDateTotal(pageModelDomain);
		}else{
			/**
			 * 1.先计算有多少个表，将所有的表的size查出来
			 */
			String startDate = pageModelDomain.getStartTime();
			String endDate = pageModelDomain.getEndTime();
//			
			
			List<String> list = new ArrayList<String>();
			try {
				list = TableUtil.getTableName("ljfl_collect_record",startDate,endDate);
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
				int datesize =ljflPutRecordService.selecCollectRecordTotalSize((pageModelDomain));
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			if(size > 1024*63){
				page.setResult("no");
				return page;
			}
			/**
			 * 获取数据
			 */

			for (int i = 0; i < tableList.size(); i++) {
			
				List<LjflCollectRecord> forCollectRecordList = new ArrayList<LjflCollectRecord>();
				DivisionTable divisionTable = tableList.get(i);
				pageModelDomain.setTableName(divisionTable.getTableName());
				
				forCollectRecordList = ljflPutRecordService.selecCollectRecordByDateTotal(pageModelDomain);
				ljflCollectlist.addAll(forCollectRecordList);
				

			}
			
		}
		List<LjflCollectRecord> list = new ArrayList<LjflCollectRecord>();
		for (LjflCollectRecord l : ljflCollectlist) {
		try {
			LjflTrashCanWithBLOBs trashCan = ljflPutRecordService.selectLjflTrashCanById(l.getfTrashCanId());
			l.setfTrashCanId(trashCan.getfName());
			list.add(l);
		} catch (Exception e) {
		}
		
		}
		page.setPageTotalNum(size);
		page.setLjflCollectRecordList(list);
		return page;
	}
	private List<Map<String, Object>> createLjflCollectExcelDetail(List<LjflCollectRecord> collectList) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "收运记录表");
		listmap.add(map);
		LjflCollectRecord record = null;
		for (int j = 0; j < collectList.size(); j++) {
			record = collectList.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("id", record.getId());
			mapValue.put("fCode", record.getfWorkPointCode());
			mapValue.put("fName", record.getfWorkPointName());
			mapValue.put("houseSelected",record.getfHousesName());
			mapValue.put("fLjflTypeId", record.getfRubbishTypeName());
			mapValue.put("fWeight", record.getfWeight());
			mapValue.put("startTime",record.getfCollectTime());
			mapValue.put("fQymanId", record.getfQymanName());
			mapValue.put("trashCanId", record.getfTrashCanId());
			listmap.add(mapValue);
		}
		return listmap;
	}
	/**
	 * 进入满溢状态   -- 收集记录
	 * 
	 * @param session
	 * @return
	 */
	/*@RequestMapping("joinWorkerCardRecordPage")
	public ModelAndView joinWorkerCardRecordPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("putRecord/joinWorkerCardRecordPage");
		mav.addObject("userId", userId);
		Map<String, String> map = new HashMap<String,String>();
		map.put("beenDeleteId", "0");
		map.put("tenantid", "4eaf9423eb0043648e502134586b088a");
		List<AreaCommunity> selectareaCommunities = areaService
				.selectareaCommunities();

		System.out
				.println("==========================" + selectareaCommunities);

		System.out.println(selectareaCommunities);
		
		mav.addObject("houses", selectareaCommunities);
		List<LjflType> listType = ljflPutRecordService.selectLjflTypeListByMap(map);
		mav.addObject("listType", listType);
		return mav;
	}	*/
	
	/**
	 * 根据相关条件收集信息
	 * 分页查询
	 * 条件一 居民id  所属户id  时间段  都可以为空
	 */
	@ResponseBody
	@RequestMapping(value = "/querySwipCardRecordDatas", method = RequestMethod.POST)
	public PageModelDomain querySwipCardRecordDatas(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
	
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getHouseSelected() ==null || pageModelDomain.getHouseSelected().isEmpty() || "-1".equals(pageModelDomain.getHouseSelected())){//名称
			pageModelDomain.setHouseSelected(null);
		}else{
			
			String houseSelected = pageModelDomain.getHouseSelected();
			List<AreaCommunity> selectAreaCommunitsByName = areaService.selectAreaCommunitsByName(houseSelected);
			String id = selectAreaCommunitsByName.get(0).getId();
			
			pageModelDomain.setHouseSelected(id);
		}

		int size = 0;//查询的所有条数
		List<LjflSwipCardRecord> ljflSwipCardlist = new ArrayList<LjflSwipCardRecord>();
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
				tableName = TableUtil.getTableNameString("ljfl_swip_card_record", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflPutRecordService.selecSwipCardRecordTotalSize(pageModelDomain);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			ljflSwipCardlist = ljflPutRecordService.selecSwipCardRecordByDateTotal(pageModelDomain);
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
				list = TableUtil.getTableName("ljfl_swip_card_record",startDate,endDate);
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
				int datesize =ljflPutRecordService.selecSwipCardRecordTotalSize((pageModelDomain));
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			if(size > 1024*63){
				page.setResult("no");
				return page;
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
				if(ljflSwipCardlist.size()>=pageneedSize){
					break;
				}
				List<LjflSwipCardRecord> forSwipCardList = new ArrayList<LjflSwipCardRecord>();
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
					currentPageSize = pageneedSize - ljflSwipCardlist.size();
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
				
				forSwipCardList = ljflPutRecordService.selecSwipCardRecordByDateTotal(pageModelDomain);
				ljflSwipCardlist.addAll(forSwipCardList);
				if(isNextTable){
					istheNextTable = true;
					pageModelDomain.setPageSize(residuePageSize);
				}

			}
			
		}
//		List<LjflSwipCardRecord> list = new ArrayList<LjflSwipCardRecord>();
//		for (LjflSwipCardRecord l : ljflSwipCardlist) {
//		
//		LjflTrashCanWithBLOBs trashCan = ljflPutRecordService.selectLjflTrashCanById(l.getfTrashCanId());
//		l.setfTrashCanId(trashCan.getfName());
//		list.add(l);
//		}
		page.setPageTotalNum(size);
		page.setLjflSwipCardRecordList(ljflSwipCardlist);
		return page;
		
	}
	
	/**
	 * 导出excel数据文件(收集记录查询)
	 * 
	 * @param pageModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportSwipCardRecordExcel")
	public String exportSwipCardRecordExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		PageModelDomain pageModel = new PageModelDomain();
		/**
		 * 封装PageModelDomain
		 */
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String beendeleted = request.getParameter("beendeleted");
		String tenantid = request.getParameter("tenantid");
		String fQymanId = request.getParameter("fQymanId");
		String fCode = request.getParameter("fCode");
		String fName = request.getParameter("fName");
		String houseSelected = request.getParameter("houseSelected");
		pageModel.setStartTime(startTime);
		pageModel.setEndTime(endTime);
	

		pageModel.setBeendeleted(beendeleted);
		pageModel.setTenantid(tenantid);
		pageModel.setfQymanId(fQymanId);
		pageModel.setfCode(fCode);
		pageModel.setfName(fName);
		pageModel.setHouseSelected(houseSelected);
		//PageModelDomain page = getLjflScoreExportExcel(pageModel);
		PageModelDomain page = getSwipCardExportExcel(pageModel);
		/**
		 * 获取到数据
		 */
		List<LjflSwipCardRecord> swipCardRecord = page.getLjflSwipCardRecordList();

		// 填充Objects数据
		// List<Object> Objects=createData(request);
		List<Map<String, Object>> list = createSwipCardRecordExcelDetail(swipCardRecord);
		String columnNames[] = { "id", "编码", "名称", "小区名","清运运时间" , "清运人员"};// 列名
		String keys[] = { "id", "fCode", "fName", "houseSelected", "startTime", "fQymanId"};// map中的key
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
		String fileName = "清运记录表";
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
	private PageModelDomain getSwipCardExportExcel(PageModelDomain pageModelDomain) {
		PageModelDomain page = new PageModelDomain();
		
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getHouseSelected() ==null || pageModelDomain.getHouseSelected().isEmpty() || "-1".equals(pageModelDomain.getHouseSelected())){//名称
			pageModelDomain.setHouseSelected(null);
		}

		int size = 0;//查询的所有条数
		List<LjflSwipCardRecord> ljflSwipCardlist = new ArrayList<LjflSwipCardRecord>();
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
				tableName = TableUtil.getTableNameString("ljfl_swip_card_record", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			size  = ljflPutRecordService.selecSwipCardRecordTotalSize(pageModelDomain);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			ljflSwipCardlist = ljflPutRecordService.selecSwipCardRecordByDateTotal(pageModelDomain);
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
				list = TableUtil.getTableName("ljfl_swip_card_record",startDate,endDate);
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
				int datesize =ljflPutRecordService.selecSwipCardRecordTotalSize((pageModelDomain));
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			if(size > 1024*63){
				page.setResult("no");
				return page;
			}
			/**
			 * 获取数据
			 */

	
			for (int i = 0; i < tableList.size(); i++) {
			
				List<LjflSwipCardRecord> forSwipCardList = new ArrayList<LjflSwipCardRecord>();
				DivisionTable divisionTable = tableList.get(i);
				pageModelDomain.setTableName(divisionTable.getTableName());
				forSwipCardList = ljflPutRecordService.selecSwipCardRecordByDateTotal(pageModelDomain);
				ljflSwipCardlist.addAll(forSwipCardList);
			

			}
			
		}

		page.setPageTotalNum(size);
		page.setLjflSwipCardRecordList(ljflSwipCardlist);
		return page;
		
		
	}
	private List<Map<String, Object>> createSwipCardRecordExcelDetail(List<LjflSwipCardRecord> swingScord) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "清运记录表");
		listmap.add(map);
		LjflSwipCardRecord record = null;
		for (int j = 0; j < swingScord.size(); j++) {
			record = swingScord.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("id", record.getId());
			mapValue.put("fCode", record.getfWorkPointCode());
			mapValue.put("fName", record.getfWorkPointName());
//			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			mapValue.put("houseSelected",record.getfHousesName());
			mapValue.put("startTime",record.getfSwipCardTime());
			mapValue.put("fQymanId",record.getfQyManName());
			listmap.add(mapValue);
		}
		return listmap;
	}
	
	
	/**
	 * 进入满溢状态   --当前调度任务
	 * 
	 * @param session
	 * @return
	 */
	/*@RequestMapping("joinCurrentPatchTaskPage")
	public ModelAndView joinCurrentPatchTaskPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("putRecord/joinCurrentPatchTaskPage");
		mav.addObject("userId", userId);
		Map<String, String> map = new HashMap<String,String>();
		map.put("beenDeleteId", "0");
		map.put("tenantid", "4eaf9423eb0043648e502134586b088a");
		List<AreaCommunity> selectareaCommunities = areaService
				.selectareaCommunities();

		System.out
				.println("==========================" + selectareaCommunities);

		System.out.println(selectareaCommunities);
		
		mav.addObject("houses", selectareaCommunities);
		List<LjflType> listType = ljflPutRecordService.selectLjflTypeListByMap(map);
		mav.addObject("listType", listType);

		List<LjflWorkPoint> workPointS = ljflPutRecordService.selectLjflWorkPointListByPamaters(map);
		mav.addObject("workPointS", workPointS);
		return mav;
	}	*/
	/**
	 * 根据相关条件  当前调度任务信息
	 * 分页查询
	 */
	@ResponseBody
	@RequestMapping(value = "/queryTotalDispatchTaskDatas", method = RequestMethod.POST)
	public PageModelDomain queryTotalDispatchTaskDatas(@RequestBody PageModelDomain page){
	
		if(page.getfQymanId() != null || page.getfQymanId().isEmpty() ){
			page.setfQymanId(null);
		}
		if(page.getfLjflTypeId() != null || page.getfLjflTypeId().isEmpty() || ("-1").equals(page.getfLjflTypeId())){
			page.setfLjflTypeId(null);
		}else{
			
			String getfLjflTypeId = page.getfLjflTypeId();
			LjflType selectByname = ljflPutRecordService.selectByname(getfLjflTypeId);
			String id = selectByname.getId();
			page.setfLjflTypeId(id);
			
		}		
		if(page.getHouseSelected() != null || page.getHouseSelected().isEmpty() || ("-1").equals(page.getHouseSelected())){
			page.setHouseSelected(null);
		}else{
			
			String houseSelected = page.getHouseSelected();
			List<AreaCommunity> selectAreaCommunitsByName = areaService.selectAreaCommunitsByName(houseSelected);
			String id = selectAreaCommunitsByName.get(0).getId();
			
			page.setHouseSelected(id);
		}
		if(page.getfTaskStatus() != null || page.getfTaskStatus().isEmpty() || ("-1").equals(page.getfTaskStatus())){
			page.setfTaskStatus(null);
		}
		if(page.getfWorkPointId() != null || page.getfWorkPointId().isEmpty() || ("-1").equals(page.getfWorkPointId())){
			page.setfWorkPointId(null);
		}
		String fWorkPointId = "";
		if(page.getHouseSelected() !=null){
			Map<String, String> map1 = new HashMap<String, String>();
			
			map1.put("fHousesId", page.getHouseSelected());
			List<LjflWorkPoint> workPoints = ljflPutRecordService.selectLjflWorkPointListByPamaters(map1);
			
			if(workPoints.size()>0){
				fWorkPointId = workPoints.get(0).getId();
			}
			
		}
	
		Map<String, String> map = new HashMap<String, String>();
		map.put("beendeleted", page.getBeendeleted());
		map.put("tenantid", page.getTenantid());
		map.put("fQymanId", page.getfQymanId());
		map.put("fLjflTypeId", page.getfLjflTypeId());
		
		
		map.put("fTaskStatus", page.getfTaskStatus());
		if(!fWorkPointId.isEmpty()){
			map.put("fWorkPointId",fWorkPointId);
		}else{
			map.put("fWorkPointId", page.getfWorkPointId());
		}
	
		int size = ljflPutRecordService.selectDispatchTaskSizeByPamater(map);//总的数据
		page.setPageTotalNum(size);
		int currentNum =  (page.getCurrentPage()-1)*page.getPageSize();//此次获取的第一个数据
		int endNum = page.getCurrentPage()*page.getPageSize();//此次获取的可能性最后一个数据
		int pageSize = page.getPageSize();
		if(endNum>=size && currentNum<size){//当最后一条不满足的时候
			pageSize = size-currentNum;
		}
		page.setCurrentNum(currentNum);
		page.setPageSize(pageSize);
		map.put("currentNum", page.getCurrentNum()+"");
		map.put("endNum", page.getPageSize()+"");
		List<LjflDispatchTask> list = ljflPutRecordService.selectDispatchTaskByPamaters(map);
		List<LjflDispatchTaskBlobs> list1 = new ArrayList<LjflDispatchTaskBlobs>();
		for (LjflDispatchTask l : list) {
			LjflDispatchTaskBlobs b = new LjflDispatchTaskBlobs();
			LjflWorkPoint w = ljflPutRecordService.selectLjflWorkPointById(l.getfWorkPointId());
			b.setId(l.getId());
			b.setfCode(l.getfCode());
			b.setfSpillOverTypeName(l.getfSpillOverTypeName());
			b.setfWeight(l.getfWeight());
			b.setBegintime(l.getfBeginTime());
			b.setfClearManName(l.getfClearManName());
			if("current_delay".equals(l.getfTaskStatus())){
				b.setfTaskStatus("延时");
			}else{
				b.setfTaskStatus("进行中");
			}
			b.setWorkPointName(w.getfName());
			b.setfTrashCanNum(w.getfTrashCanNum()+"");
			b.setLon(w.getfLongitudeDone()+"");
			b.setLat(w.getfLatitudeDone()+"");
			LjflType type= ljflPutRecordService.selectljflTypeById(l.getfRubbishTypeId());
			b.setfRubbishTypeName(type.getfName());
			AreaCommunity selectByPrimaryKey = areaService.selectByPrimaryKey(w.getfHousesId());
			b.setHouseName(selectByPrimaryKey.getCommunityname());
			list1.add(b);
			
		}
	
		page.setPageTotalNum(size);
		page.setLjflDispatchTaskBlobs(list1);
		return page;
	}
	
	/**
	 * 导出excel数据文件(当前调度任务)
	 * 
	 * @param pageModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportLjflDispatchTask")
	public String exportLjflDispatchTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		PageModelDomain pageModel = new PageModelDomain();
		/**
		 * 封装PageModelDomain
		 */
	
		String beendeleted = request.getParameter("beendeleted");
		String tenantid = request.getParameter("tenantid");
		String fQymanId = request.getParameter("fQymanId");
		String fTaskStatus = request.getParameter("fTaskStatus");
		String fWorkPointId = request.getParameter("fWorkPointId");
		String fLjflTypeId = request.getParameter("fLjflTypeId");
		String houseSelected = request.getParameter("houseSelected");

	

		pageModel.setBeendeleted(beendeleted);
		pageModel.setTenantid(tenantid);
		pageModel.setfQymanId(fQymanId);
		pageModel.setfTaskStatus(fTaskStatus);
		pageModel.setfWorkPointId(fWorkPointId);
		pageModel.setfLjflTypeId(fLjflTypeId);
		pageModel.setHouseSelected(houseSelected);
		//PageModelDomain page = getLjflScoreExportExcel(pageModel);
		PageModelDomain page = getSLjflDispatchTaskExportExcel(pageModel);
		/**
		 * 获取到数据
		 */
		List<LjflDispatchTaskBlobs> dispatchTask = page.getLjflDispatchTaskBlobs();

		// 填充Objects数据
		// List<Object> Objects=createData(request);
		List<Map<String, Object>> list = createLjflSwipCardExcelDetail(dispatchTask);
		String columnNames[] = { "id", "任务编码", "分类箱名称", "小区名","垃圾类型" , "垃圾桶数", "满溢程度", "重量(kg)", "开始时间","清运人员" , "任务状态","经度" , "纬度"};// 列名
		String keys[] = { "id", "fCode", "workPointName", "houseName", "fRubbishTypeName", "fTrashCanNum", "fSpillOverTypeName", "fWeight", "begintime", "fClearManName", "fTaskStatus", "lon", "lat"};// map中的key
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
		String fileName = "当前调度任务表";
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
	private PageModelDomain getSLjflDispatchTaskExportExcel(PageModelDomain page) {
		if(page.getfQymanId() != null || page.getfQymanId().isEmpty() ){
			page.setfQymanId(null);
		}
		if(page.getfLjflTypeId() != null || page.getfLjflTypeId().isEmpty() || ("-1").equals(page.getfLjflTypeId())){
			page.setfLjflTypeId(null);
		}
		if(page.getHouseSelected() != null || page.getHouseSelected().isEmpty() || ("-1").equals(page.getHouseSelected())){
			page.setHouseSelected(null);
		}
		if(page.getfTaskStatus() != null || page.getfTaskStatus().isEmpty() || ("-1").equals(page.getfTaskStatus())){
			page.setfTaskStatus(null);
		}
		if(page.getfWorkPointId() != null || page.getfWorkPointId().isEmpty() || ("-1").equals(page.getfWorkPointId())){
			page.setfWorkPointId(null);
		}
		String fWorkPointId = "";
		if(page.getHouseSelected() !=null){
			Map<String, String> map1 = new HashMap<String, String>();
			
			map1.put("fHousesId", page.getHouseSelected());
			List<LjflWorkPoint> workPoints = ljflPutRecordService.selectLjflWorkPointListByPamaters(map1);
			
			if(workPoints.size()>0){
				fWorkPointId = workPoints.get(0).getId();
			}
			
		}
	
		Map<String, String> map = new HashMap<String, String>();
		map.put("beendeleted", page.getBeendeleted());
		map.put("tenantid", page.getTenantid());
		map.put("fQymanId", page.getfQymanId());
		map.put("fLjflTypeId", page.getfLjflTypeId());
		
		
		map.put("fTaskStatus", page.getfTaskStatus());
		if(!fWorkPointId.isEmpty()){
			map.put("fWorkPointId",fWorkPointId);
		}else{
			map.put("fWorkPointId", page.getfWorkPointId());
		}
	
		int size = ljflPutRecordService.selectDispatchTaskSizeByPamater(map);//总的数据
		List<LjflDispatchTask> list = ljflPutRecordService.selectDispatchTaskByPamaters(map);
		List<LjflDispatchTaskBlobs> list1 = new ArrayList<LjflDispatchTaskBlobs>();
		for (LjflDispatchTask l : list) {
			LjflDispatchTaskBlobs b = new LjflDispatchTaskBlobs();
			LjflWorkPoint w = ljflPutRecordService.selectLjflWorkPointById(l.getfWorkPointId());
			b.setId(l.getId());
			b.setfCode(l.getfCode());
			b.setfSpillOverTypeName(l.getfSpillOverTypeName());
			b.setfWeight(l.getfWeight());
			b.setBegintime(l.getfBeginTime());
			b.setfClearManName(l.getfClearManName());
			if("current_delay".equals(l.getfTaskStatus())){
				b.setfTaskStatus("延时");
			}else{
				b.setfTaskStatus("进行中");
			}
			b.setWorkPointName(w.getfName());
			b.setfTrashCanNum(w.getfTrashCanNum()+"");
			b.setLon(w.getfLongitudeDone()+"");
			b.setLat(w.getfLatitudeDone()+"");
			LjflType type= ljflPutRecordService.selectljflTypeById(l.getfRubbishTypeId());
			b.setfRubbishTypeName(type.getfName());
			AreaCommunity selectByPrimaryKey = areaService.selectByPrimaryKey(w.getfHousesId());
			b.setHouseName(selectByPrimaryKey.getCommunityname());
			list1.add(b);
			
		}
	
		page.setPageTotalNum(size);
		page.setLjflDispatchTaskBlobs(list1);
		return page;
		
		
	}
	private List<Map<String, Object>> createLjflSwipCardExcelDetail(List<LjflDispatchTaskBlobs> dispatchTask) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "当前调度任务表");
		listmap.add(map);
		LjflDispatchTaskBlobs record = null;
		for (int j = 0; j < dispatchTask.size(); j++) {
			record = dispatchTask.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("id", record.getId());
			mapValue.put("fCode", record.getfCode());
			mapValue.put("workPointName", record.getWorkPointName());
			mapValue.put("houseName", record.getHouseName());
			mapValue.put("fRubbishTypeName", record.getfRubbishTypeName());
			mapValue.put("fTrashCanNum", record.getfTrashCanNum());
			mapValue.put("fSpillOverTypeName", record.getfSpillOverTypeName());
			mapValue.put("fWeight", record.getfWeight());
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			mapValue.put("begintime",dateFormater.format(record.getBegintime()));
			
//			mapValue.put("begintime",record.getfBeginTime());
			mapValue.put("fClearManName", record.getfClearManName());
			mapValue.put("fTaskStatus", record.getfTaskStatus());
			mapValue.put("lon", record.getLon());
			mapValue.put("lat", record.getLat());
		
			listmap.add(mapValue);
		}
		return listmap;
	}
	
	/**
	 * 进入满溢状态   --历史调度任务
	 * 
	 * @param session
	 * @return
	 */
	/*@RequestMapping("joinHistoryPatchTaskPage")
	public ModelAndView joinHistoryPatchTaskPage(HttpSession session) {

		String userId = session.getAttribute("userid").toString();
		System.out.println(userId);
		ModelAndView mav = new ModelAndView("putRecord/joinHistoryPatchTaskPage");
		mav.addObject("userId", userId);
		Map<String, String> map = new HashMap<String,String>();
		map.put("beenDeleteId", "0");
		map.put("tenantid", "4eaf9423eb0043648e502134586b088a");
		List<AreaCommunity> selectareaCommunities = areaService
				.selectareaCommunities();

		System.out
				.println("==========================" + selectareaCommunities);

		System.out.println(selectareaCommunities);

		
		mav.addObject("houses", selectareaCommunities);
		List<LjflType> listType = ljflPutRecordService.selectLjflTypeListByMap(map);
		mav.addObject("listType", listType);

		List<LjflWorkPoint> workPointS = ljflPutRecordService.selectLjflWorkPointListByPamaters(map);
		mav.addObject("workPointS", workPointS);
		return mav;
	}	*/
	/**
	 * 根据相关条件 -- 历史调度任务
	 * 分页查询
	 * 条件一 居民id  所属户id  时间段  都可以为空
	 */
	@ResponseBody
	@RequestMapping(value = "/queryTotalDispatchTaskHistoryDatas", method = RequestMethod.POST)
	public PageModelDomain queryTotalDispatchTaskHistoryDatas(@RequestBody PageModelDomain pageModelDomain){
		PageModelDomain page = new PageModelDomain();
	
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getfQymanId() == null || pageModelDomain.getfQymanId().isEmpty() ){
			pageModelDomain.setfQymanId(null);
		}
		if(pageModelDomain.getfLjflTypeId() == null || pageModelDomain.getfLjflTypeId().isEmpty() || ("-1").equals(pageModelDomain.getfLjflTypeId())){
			pageModelDomain.setfLjflTypeId(null);
		}else{
			
			String getfLjflTypeId = pageModelDomain.getfLjflTypeId();
			LjflType selectByname = ljflPutRecordService.selectByname(getfLjflTypeId);
			String id = selectByname.getId();
			pageModelDomain.setfLjflTypeId(id);
		}
		if(pageModelDomain.getHouseSelected() == null || pageModelDomain.getHouseSelected().isEmpty() || ("-1").equals(pageModelDomain.getHouseSelected())){
			pageModelDomain.setHouseSelected(null);
		}else{
			
			String houseSelected = pageModelDomain.getHouseSelected();
			List<AreaCommunity> selectAreaCommunitsByName = areaService.selectAreaCommunitsByName(houseSelected);
			String id = selectAreaCommunitsByName.get(0).getId();
			
			pageModelDomain.setHouseSelected(id);
		}		
		if(pageModelDomain.getfTaskStatus() == null || pageModelDomain.getfTaskStatus().isEmpty() || ("-1").equals(pageModelDomain.getfTaskStatus())){
			pageModelDomain.setfTaskStatus(null);
		}
		if(pageModelDomain.getfWorkPointId() == null || pageModelDomain.getfWorkPointId().isEmpty() || ("-1").equals(pageModelDomain.getfWorkPointId())){
			pageModelDomain.setfWorkPointId(null);
		}

		int size = 0;//查询的所有条数
		List<LjflDispatchTask> ljflDispatchTasklist = new ArrayList<LjflDispatchTask>();
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
		String fWorkPointId = "";
		if(page.getHouseSelected() !=null){
			Map<String, String> map1 = new HashMap<String, String>();
			
			map1.put("fHousesId", page.getHouseSelected());
			List<LjflWorkPoint> workPoints = ljflPutRecordService.selectLjflWorkPointListByPamaters(map1);
			
			if(workPoints.size()>0){
				fWorkPointId = workPoints.get(0).getId();
			}
			
		}
	
		Map<String, String> map = new HashMap<String, String>();
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		map.put("tenantid", pageModelDomain.getTenantid());
		map.put("fQymanId", pageModelDomain.getfQymanId());
		map.put("fLjflTypeId", pageModelDomain.getfLjflTypeId());
		map.put("fTaskStatus", pageModelDomain.getfTaskStatus());
		if(!fWorkPointId.isEmpty()){
			map.put("fWorkPointId",fWorkPointId);
		}else{
			map.put("fWorkPointId", pageModelDomain.getfWorkPointId());
		}
	
		if(pageModelDomain.getStartTime() == null){
			String tableName = "";
			try {
				tableName = TableUtil.getTableNameString("ljfl_dispatch_task", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			map.put("startTime", pageModelDomain.getStartTime());
			map.put("endTime", pageModelDomain.getEndTime());
			map.put("tableName", pageModelDomain.getTableName());
			size  = ljflPutRecordService.selecDispatchTaskHistorySize(map);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			if(pageModelDomain.getPageSize()==0){
				map.put("currentNum",null);
				map.put("endNum", null);
			}else{
				map.put("currentNum",pageModelDomain.getCurrentNum()+"");
				map.put("endNum", pageModelDomain.getPageSize()+"");
			}
			ljflDispatchTasklist = ljflPutRecordService.selecDispatchTaskHistoryTotal(map);
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
				list = TableUtil.getTableName("ljfl_dispatch_task",startDate,endDate);
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
				map.put("startTime", pageModelDomain.getStartTime());
				map.put("endTime", pageModelDomain.getEndTime());
				map.put("tableName", pageModelDomain.getTableName());
				int datesize =ljflPutRecordService.selecDispatchTaskHistorySize((map));
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			if(size > 1024*63){
				page.setResult("no");
				return page;
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
				if(ljflDispatchTasklist.size()>=pageneedSize){
					break;
				}
				List<LjflDispatchTask> forDispatchTaskList = new ArrayList<LjflDispatchTask>();
				DivisionTable divisionTable = tableList.get(i);
				pageModelDomain.setTableName(divisionTable.getTableName());
				map.put("tableName",divisionTable.getTableName());
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
					currentPageSize = pageneedSize - ljflDispatchTasklist.size();
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
				if(pageModelDomain.getPageSize()==0){
					map.put("currentNum",null);
					map.put("endNum", null);
				}else{
					map.put("currentNum",pageModelDomain.getCurrentNum()+"");
					map.put("endNum", pageModelDomain.getPageSize()+"");
				}
				forDispatchTaskList = ljflPutRecordService.selecDispatchTaskHistoryTotal(map);
				ljflDispatchTasklist.addAll(forDispatchTaskList);
				if(isNextTable){
					istheNextTable = true;
					pageModelDomain.setPageSize(residuePageSize);
				}

			}
			
		}

		List<LjflDispatchTaskBlobs> list1 = new ArrayList<LjflDispatchTaskBlobs>();
		for (LjflDispatchTask l : ljflDispatchTasklist) {
			LjflDispatchTaskBlobs b = new LjflDispatchTaskBlobs();
			LjflWorkPoint w = ljflPutRecordService.selectLjflWorkPointById(l.getfWorkPointId());
			b.setId(l.getId());
			b.setfCode(l.getfCode());
			b.setfSpillOverTypeName(l.getfSpillOverTypeName());
			b.setfWeight(l.getfWeight());
			b.setBegintime(l.getfBeginTime());
			b.setfClearManName(l.getfClearManName());
			if("current_delay".equals(l.getfTaskStatus())){
				b.setfTaskStatus("延时");
			}else{
				b.setfTaskStatus("进行中");
			}
			b.setWorkPointName(w.getfName());
			b.setfTrashCanNum(w.getfTrashCanNum()+"");
			b.setLon(w.getfLongitudeDone()+"");
			b.setLat(w.getfLatitudeDone()+"");
			LjflType type= ljflPutRecordService.selectljflTypeById(l.getfRubbishTypeId());
			b.setfRubbishTypeName(type.getfName());
			AreaCommunity selectByPrimaryKey = areaService.selectByPrimaryKey(w.getfHousesId());
			b.setHouseName(selectByPrimaryKey.getCommunityname());
			list1.add(b);
			
		}
	
		page.setPageTotalNum(size);
		page.setLjflDispatchTaskBlobs(list1);
		return page;
		
	}
	
	/**
	 * 导出excel数据文件(历史调度任务)
	 * 
	 * @param pageModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportLjflDispatchHistoryTask")
	public String exportLjflDispatchHistoryTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		PageModelDomain pageModel = new PageModelDomain();
		/**
		 * 封装PageModelDomain
		 */
	
		String beendeleted = request.getParameter("beendeleted");
		String tenantid = request.getParameter("tenantid");
		String fQymanId = request.getParameter("fQymanId");
		String fTaskStatus = request.getParameter("fTaskStatus");
		String fWorkPointId = request.getParameter("fWorkPointId");
		String fLjflTypeId = request.getParameter("fLjflTypeId");
		String houseSelected = request.getParameter("houseSelected");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");

	
		pageModel.setStartTime(startTime);
		pageModel.setEndTime(endTime);
		pageModel.setBeendeleted(beendeleted);
		pageModel.setTenantid(tenantid);
		pageModel.setfQymanId(fQymanId);
		pageModel.setfTaskStatus(fTaskStatus);
		pageModel.setfWorkPointId(fWorkPointId);
		pageModel.setfLjflTypeId(fLjflTypeId);
		pageModel.setHouseSelected(houseSelected);
		//PageModelDomain page = getLjflScoreExportExcel(pageModel);
		PageModelDomain page = getSLjflDispatchTaskHistoryExportExcel(pageModel);
		/**
		 * 获取到数据
		 */
		List<LjflDispatchTaskBlobs> dispatchTask = page.getLjflDispatchTaskBlobs();

		// 填充Objects数据
		// List<Object> Objects=createData(request);
		List<Map<String, Object>> list = createLjflDispatchTaskHisToryBlobs(dispatchTask);
		String columnNames[] = { "id", "任务编码", "分类箱名称", "小区名","垃圾类型" , "垃圾桶数", "满溢程度", "重量(kg)", "开始时间","清运人员" , "任务状态","经度" , "纬度"};// 列名
		String keys[] = { "id", "fCode", "workPointName", "houseName", "fRubbishTypeName", "fTrashCanNum", "fSpillOverTypeName", "fWeight", "begintime", "fClearManName", "fTaskStatus", "lon", "lat"};// map中的key
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
		String fileName = "历史调度任务表";
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
	private PageModelDomain getSLjflDispatchTaskHistoryExportExcel(PageModelDomain pageModelDomain) {
		PageModelDomain page = new PageModelDomain();
		
		if(pageModelDomain.getStartTime() ==null || pageModelDomain.getStartTime().isEmpty()){
			pageModelDomain.setStartTime(null);
		}
		if(pageModelDomain.getEndTime() ==null || pageModelDomain.getEndTime().isEmpty()){
			pageModelDomain.setEndTime(null);
		}
		if(pageModelDomain.getfQymanId() == null || pageModelDomain.getfQymanId().isEmpty() ){
			pageModelDomain.setfQymanId(null);
		}
		if(pageModelDomain.getfLjflTypeId() == null || pageModelDomain.getfLjflTypeId().isEmpty() || ("-1").equals(pageModelDomain.getfLjflTypeId())){
			pageModelDomain.setfLjflTypeId(null);
		}
		if(pageModelDomain.getHouseSelected() == null || pageModelDomain.getHouseSelected().isEmpty() || ("-1").equals(pageModelDomain.getHouseSelected())){
			pageModelDomain.setHouseSelected(null);
		}
		if(pageModelDomain.getfTaskStatus() == null || pageModelDomain.getfTaskStatus().isEmpty() || ("-1").equals(pageModelDomain.getfTaskStatus())){
			pageModelDomain.setfTaskStatus(null);
		}
		if(pageModelDomain.getfWorkPointId() == null || pageModelDomain.getfWorkPointId().isEmpty() || ("-1").equals(pageModelDomain.getfWorkPointId())){
			pageModelDomain.setfWorkPointId(null);
		}

		int size = 0;//查询的所有条数
		List<LjflDispatchTask> ljflDispatchTasklist = new ArrayList<LjflDispatchTask>();
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
		String fWorkPointId = "";
		if(page.getHouseSelected() !=null){
			Map<String, String> map1 = new HashMap<String, String>();
			
			map1.put("fHousesId", page.getHouseSelected());
			List<LjflWorkPoint> workPoints = ljflPutRecordService.selectLjflWorkPointListByPamaters(map1);
			
			if(workPoints.size()>0){
				fWorkPointId = workPoints.get(0).getId();
			}
			
		}
	
		Map<String, String> map = new HashMap<String, String>();
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		map.put("tenantid", pageModelDomain.getTenantid());
		map.put("fQymanId", pageModelDomain.getfQymanId());
		map.put("fLjflTypeId", pageModelDomain.getfLjflTypeId());
		map.put("fTaskStatus", pageModelDomain.getfTaskStatus());
		if(!fWorkPointId.isEmpty()){
			map.put("fWorkPointId",fWorkPointId);
		}else{
			map.put("fWorkPointId", pageModelDomain.getfWorkPointId());
		}
	
		if(pageModelDomain.getStartTime() == null){
			String tableName = "";
			try {
				tableName = TableUtil.getTableNameString("ljfl_dispatch_task", chaxunTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageModelDomain.setStartTime(startTime);
			pageModelDomain.setEndTime(endTime);
			pageModelDomain.setTableName(tableName);
			map.put("startTime", pageModelDomain.getStartTime());
			map.put("endTime", pageModelDomain.getEndTime());
			map.put("tableName", pageModelDomain.getTableName());
			size  = ljflPutRecordService.selecDispatchTaskHistorySize(map);
			pageModelDomain.setCurrentNum((pageModelDomain.getCurrentPage()-1)*pageModelDomain.getPageSize());
			
			int endNum = pageModelDomain.getCurrentPage()*pageModelDomain.getPageSize();
			
			if(size <= endNum){
				pageModelDomain.setEndNum(pageModelDomain.getPageSize()-(endNum-size));
			}else{
				pageModelDomain.setEndNum(pageModelDomain.getPageSize());
			}
			if(pageModelDomain.getPageSize()==0){
				map.put("currentNum",null);
				map.put("endNum", null);
			}else{
				map.put("currentNum",pageModelDomain.getCurrentNum()+"");
				map.put("endNum", pageModelDomain.getPageSize()+"");
			}
			ljflDispatchTasklist = ljflPutRecordService.selecDispatchTaskHistoryTotal(map);
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
				list = TableUtil.getTableName("ljfl_dispatch_task",startDate,endDate);
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
				map.put("startTime", pageModelDomain.getStartTime());
				map.put("endTime", pageModelDomain.getEndTime());
				map.put("tableName", pageModelDomain.getTableName());
				int datesize =ljflPutRecordService.selecDispatchTaskHistorySize((map));
				division.setSize(datesize);
				tableList.add(division);
				size = size+datesize;//一共有多少条满足的数据
			}
			if(size > 1024*63){
				page.setResult("no");
				return page;
			}
			/**
			 * 获取数据
			 */

			for (int i = 0; i < tableList.size(); i++) {
		
				List<LjflDispatchTask> forDispatchTaskList = new ArrayList<LjflDispatchTask>();
				DivisionTable divisionTable = tableList.get(i);
				map.put("tableName",divisionTable.getTableName());

				forDispatchTaskList = ljflPutRecordService.selecDispatchTaskHistoryTotal(map);
				ljflDispatchTasklist.addAll(forDispatchTaskList);
	
			}
			
		}


		List<LjflDispatchTaskBlobs> list1 = new ArrayList<LjflDispatchTaskBlobs>();
		for (LjflDispatchTask l : ljflDispatchTasklist) {
			LjflDispatchTaskBlobs b = new LjflDispatchTaskBlobs();
			LjflWorkPoint w = ljflPutRecordService.selectLjflWorkPointById(l.getfWorkPointId());
			b.setId(l.getId());
			b.setfCode(l.getfCode());
			b.setfSpillOverTypeName(l.getfSpillOverTypeName());
			b.setfWeight(l.getfWeight());
			b.setBegintime(l.getfBeginTime());
			b.setfClearManName(l.getfClearManName());
			if("current_delay".equals(l.getfTaskStatus())){
				b.setfTaskStatus("延时");
			}else{
				b.setfTaskStatus("进行中");
			}
			b.setWorkPointName(w.getfName());
			b.setfTrashCanNum(w.getfTrashCanNum()+"");
			b.setLon(w.getfLongitudeDone()+"");
			b.setLat(w.getfLatitudeDone()+"");
			LjflType type= ljflPutRecordService.selectljflTypeById(l.getfRubbishTypeId());
			b.setfRubbishTypeName(type.getfName());
			AreaCommunity selectByPrimaryKey = areaService.selectByPrimaryKey(w.getfHousesId());
			b.setHouseName(selectByPrimaryKey.getCommunityname());
			list1.add(b);
			
		}
	
		page.setPageTotalNum(size);
		page.setLjflDispatchTaskBlobs(list1);
		return page;
		
		
	}
	private List<Map<String, Object>> createLjflDispatchTaskHisToryBlobs(List<LjflDispatchTaskBlobs> dispatchTask) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "历史调度任务表");
		listmap.add(map);
		LjflDispatchTaskBlobs record = null;
		for (int j = 0; j < dispatchTask.size(); j++) {
			record = dispatchTask.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("id", record.getId());
			mapValue.put("fCode", record.getfCode());
			mapValue.put("workPointName", record.getWorkPointName());
			mapValue.put("houseName", record.getHouseName());
			mapValue.put("fRubbishTypeName", record.getfRubbishTypeName());
			mapValue.put("fTrashCanNum", record.getfTrashCanNum());
			mapValue.put("fSpillOverTypeName", record.getfSpillOverTypeName());
			mapValue.put("fWeight", record.getfWeight());
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			mapValue.put("begintime",dateFormater.format(record.getBegintime()));
			
//			mapValue.put("begintime",record.getfBeginTime());
			mapValue.put("fClearManName", record.getfClearManName());
			mapValue.put("fTaskStatus", record.getfTaskStatus());
			mapValue.put("lon", record.getLon());
			mapValue.put("lat", record.getLat());
		
			listmap.add(mapValue);
		}
		return listmap;
	}
	
	/**
	 * 进入垃圾分类一张图
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("joinGarbageSortMapPage")
	public ModelAndView joinGarbageSortMapPage(HttpSession session) {
		AreaGov areaGov = new AreaGov();
		ModelAndView mav = new ModelAndView("putRecord/joinGarbageSortMapPage");
		try {
			String userId = session.getAttribute("userid").toString();
			
			mav.addObject("userId", userId);
			PersonInfo  personInfo =  personService.selectByUserId(userId);
			if(personInfo != null ){
				areaGov = areaService.selectAreaGovById(personInfo.getAreaid());
				mav.addObject("areaGov", areaGov);
			}else{
				areaGov.setLatitude("39.914365");
				areaGov.setLontitude("116.404049");
				mav.addObject("areaGov", areaGov);
			}
			UserInfo userInfo = userService.selectByPrimaryKey(userId) ;
			
			List<CarInfo> carInfos = cdsService.getCarInfoListTotal(userInfo.getUnitid());
			mav.addObject("carInfos", carInfos);
			List<LjflTrashCanWorkPoint> trashCanList = ljflTrashCanWorkPointService.selectLjflTrashCanWorkPointInfoListTotal();
			
			List<LjflTrashCanWorkPoint> list = new ArrayList<LjflTrashCanWorkPoint>();
			for (LjflTrashCanWorkPoint l : trashCanList) {
				if(l.getUserid()!=null && userService.selectByPrimaryKey(l.getUserid())!=null){
					if(userInfo.getUnitid().equals(userService.selectByPrimaryKey(l.getUserid()).getUnitid())){
						list.add(l);
					};
				}
				
				
			}
			mav.addObject("trashCanList", list);
		} catch (Exception e) {
			mav.addObject("areaGov", areaGov);
		}
		return mav;
	}
	
	/**
	 * 获取回收物品
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getrecyclename", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getrecyclename(HttpServletRequest request) throws IOException {

		request.setCharacterEncoding("UTF-8");

		List<LjflRecycleObject> ljflRecyObjectList = ljflPutRecordService.selectLjflObjectListByParamters(0);

		Gson gson = new Gson();
		String json = gson.toJson(ljflRecyObjectList);
		System.out.println(json);
		return json;
	}
	/**
	 * 获取回收类型
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getrecycletype", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getrecycletype(HttpServletRequest request) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		List<LjflRecycleType> ljflRecyTypeList = ljflPutRecordService.selectLjflTypeListByParamters(0);
		
		Gson gson = new Gson();
		String json = gson.toJson(ljflRecyTypeList);
		System.out.println(json);
		return json;
	}
	/**
	 * 获取垃圾类型
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/gettrashtype", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String gettrashtype(HttpServletRequest request) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("beenDeleteId", "0");
		map.put("tenantid", "4eaf9423eb0043648e502134586b088a");
		List<LjflType> listType = ljflPutRecordService.selectLjflTypeListByMap(map);
		Gson gson = new Gson();
		String json = gson.toJson(listType);
		System.out.println(json);
		return json;
	}

}