package com.sczh.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.CloudStaffMapper;
import com.sczh.platform.dao.LjflCollectRecordMapper;
import com.sczh.platform.dao.LjflDispatchTaskMapper;
import com.sczh.platform.dao.LjflPutRecordMapper;
import com.sczh.platform.dao.LjflQuestionMapper;
import com.sczh.platform.dao.LjflRecycleAppointmentMapper;
import com.sczh.platform.dao.LjflRecycleObjectMapper;
import com.sczh.platform.dao.LjflRecycleRecordMapper;
import com.sczh.platform.dao.LjflRecycleTypeMapper;
import com.sczh.platform.dao.LjflResponseTimeConfigMapper;
import com.sczh.platform.dao.LjflSpillOverConfigMapper;
import com.sczh.platform.dao.LjflStaffDetailMapper;
import com.sczh.platform.dao.LjflStaffStatisticMapper;
import com.sczh.platform.dao.LjflSwipCardRecordMapper;
import com.sczh.platform.dao.LjflTrashCanMapper;
import com.sczh.platform.dao.LjflTypeMapper;
import com.sczh.platform.dao.LjflWorkPointMapper;
import com.sczh.platform.po.CloudStaff;
import com.sczh.platform.po.LjflCollectRecord;
import com.sczh.platform.po.LjflDispatchTask;
import com.sczh.platform.po.LjflPutRecord;
import com.sczh.platform.po.LjflPutRecord1;
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
import com.sczh.platform.po.LjflType;
import com.sczh.platform.po.LjflWorkPoint;
import com.sczh.platform.po.Model.PageModelDomain;
import com.sczh.platform.service.LjflPutRecordService;
@Service
public class LjflPutRecordServiceImpl implements LjflPutRecordService {
	@Autowired private LjflPutRecordMapper ljflPutRecordMapper;
	@Autowired private LjflRecycleTypeMapper ljflRecycleTypeMapper;
	@Autowired private LjflRecycleObjectMapper ljflRecycleObjectMapper;
	@Autowired private LjflRecycleRecordMapper ljflRecycleRecordMapper;
	@Autowired private LjflQuestionMapper ljflQuestionMapper;
	@Autowired private CloudStaffMapper cloudStaffMapper;
	@Autowired private LjflWorkPointMapper ljflWorkPointMapper;
	@Autowired private LjflStaffStatisticMapper ljflStaffStatisticMapper;
	@Autowired private LjflRecycleAppointmentMapper ljflRecycleAppointmentMapper;
	@Autowired private LjflTrashCanMapper ljflTrashCanMapper;
	@Autowired private LjflTypeMapper ljflTypeMapper;
	@Autowired private LjflSpillOverConfigMapper ljflSpillOverConfigMapper;
	@Autowired private LjflResponseTimeConfigMapper ljflResponseTimeConfigMapper;
	@Autowired private LjflCollectRecordMapper ljflCollectRecordMapper;
	@Autowired private LjflSwipCardRecordMapper ljflSwipCardRecordMapper;
	@Autowired private LjflDispatchTaskMapper ljflDispatchTaskMapper;
	@Autowired private LjflStaffDetailMapper ljflStaffDetailMapper;
 	@Override
	public int selectHouseHoldTotalSize(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		if(pageModelDomain.getfName() == null || pageModelDomain.getfName().isEmpty()){
			map.put("doorname", null);
		}else{
			map.put("doorname", pageModelDomain.getfName());
		}
		map.put("beendeleted", "0");
		
		return ljflStaffDetailMapper.selectHouseHoldTotalSize(map);
	}

	@Override
	public List<LjflStaffDetail> selectHouseHoldData(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		if(pageModelDomain.getfName() == null || pageModelDomain.getfName().isEmpty()){
			map.put("doorname", null);
		}else{
			map.put("doorname", pageModelDomain.getfName());
		}
		if(pageModelDomain.getEndNum() ==0){
			map.put("endNum",null);
		}else{
			map.put("endNum",pageModelDomain.getEndNum()+"");
		}
		
		map.put("currentNum",pageModelDomain.getCurrentNum()+"");
		map.put("beendeleted", "0");
		
		return ljflStaffDetailMapper.selectHouseHoldData(map);
	}

	@Override
	public int selecPutRecordByDateTotalSize(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", pageModelDomain.getTableName());
		map.put("staffDetailId", pageModelDomain.getStaffDetailId());
		map.put("startTime", pageModelDomain.getStartTime());
		map.put("endTime", pageModelDomain.getEndTime());
		map.put("fHouseholdId", pageModelDomain.getfHouseholdId());
		
		return ljflPutRecordMapper.selecPutRecordByDateTotalSize(map);
	}

	@Override
	public List<LjflPutRecord> selecPutRecordByDateTotal(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", pageModelDomain.getTableName());
		map.put("staffDetailId", pageModelDomain.getStaffDetailId());
		map.put("startTime", pageModelDomain.getStartTime());
		map.put("endTime", pageModelDomain.getEndTime());
		map.put("fHouseholdId", pageModelDomain.getfHouseholdId());
		if(pageModelDomain.getPageSize()==0){
			map.put("currentNum",null);
			map.put("endNum", null);
		}else{
			map.put("currentNum",pageModelDomain.getCurrentNum()+"");
			map.put("endNum", pageModelDomain.getPageSize()+"");
		}
		return ljflPutRecordMapper.selecPutRecordByDateTotal(map);
	}

	@Override
	public List<LjflRecycleType> selectLjflTypeListByParamters(int num) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("beendeleted", num+"");
		return ljflRecycleTypeMapper.selectLjflTypeListByParamters(map);
	}

	@Override
	public List<LjflRecycleObject> selectLjflObjectListByParamters(int num) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("beendeleted", num+"");
		return ljflRecycleObjectMapper.selectLjflObjectListByParamters(map);
	}

	@Override
	public int selecRecycycleRecordByDateTotalSize(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", pageModelDomain.getTableName());
		map.put("fRecycleObjectId", pageModelDomain.getfRecycleObjectId());
		map.put("startTime", pageModelDomain.getStartTime());
		map.put("endTime", pageModelDomain.getEndTime());
		map.put("staffDetailId", pageModelDomain.getStaffDetailId());
		map.put("fScoreType", pageModelDomain.getfScoreType());
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		return ljflRecycleRecordMapper.selecRecycycleRecordByDateTotalSize(map);
	}

	@Override
	public List<LjflRecycleRecord> selecRecycleRecordByDateTotal(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
	
		map.put("tableName", pageModelDomain.getTableName());
		map.put("fRecycleObjectId", pageModelDomain.getfRecycleObjectId());
		if(pageModelDomain.getPageSize()==0){
			map.put("currentNum",null);
			map.put("endNum", null);
		}else{
			map.put("currentNum",pageModelDomain.getCurrentNum()+"");
			map.put("endNum", pageModelDomain.getPageSize()+"");
		}
		map.put("startTime", pageModelDomain.getStartTime());
		map.put("endTime", pageModelDomain.getEndTime());
		map.put("staffDetailId", pageModelDomain.getStaffDetailId());
		map.put("fScoreType", pageModelDomain.getfScoreType());
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		return ljflRecycleRecordMapper.selecRecycleRecordByDateTotal(map);
	}

	@Override
	public LjflRecycleObject selectRecycleObjectById(String getfRecycleObjectId) {
		return ljflRecycleObjectMapper.selectByPrimaryKey(getfRecycleObjectId);
	}

	@Override
	public int selectRecycleObjectByPamaterSize(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		
		map.put("fRecycleTypeId", pageModelDomain.getfRecycleTypeId());
		map.put("fName", pageModelDomain.getfName());
		map.put("fCode", pageModelDomain.getfCode());
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		return ljflRecycleObjectMapper.selectRecycleObjectByPamaterSize(map);
	}

	@Override
	public List<LjflRecycleObject> selectRecycleObjectByPamaters(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		
	
		if(pageModelDomain.getPageSize()==0){
			map.put("currentNum",null);
			map.put("endNum", null);
		}else{
			map.put("currentNum",pageModelDomain.getCurrentNum()+"");
			map.put("endNum", pageModelDomain.getPageSize()+"");
		}
		map.put("fRecycleTypeId", pageModelDomain.getfRecycleTypeId());
		map.put("fName", pageModelDomain.getfName());
		map.put("fCode", pageModelDomain.getfCode());
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		return ljflRecycleObjectMapper.selectLjflObjectListByParamters(map);
	}

	@Override
	public LjflRecycleType selectLjflRecycleTypeById(String id) {
		return ljflRecycleTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertRecycleObject(LjflRecycleObject object) {
		return ljflRecycleObjectMapper.insert(object);
	}

	@Override
	public int updateRecycleObject(LjflRecycleObject object) {
		return ljflRecycleObjectMapper.updateByPrimaryKey(object);
	}

	@Override
	public void deleteRecycleObjectById(String id) {
		ljflRecycleObjectMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int selectRecycleTypeByPamaterSize(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		
		

		map.put("fName", pageModelDomain.getfName());
		map.put("fCode", pageModelDomain.getfCode());
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		return ljflRecycleTypeMapper.selectRecycleTypeByPamaterSize(map);
	}

	@Override
	public List<LjflRecycleType> selectRecycleTypeByPamaters(PageModelDomain pageModelDomain) {
	Map<String, String> map = new HashMap<String,String>();
		
		
		if(pageModelDomain.getPageSize()==0){
			map.put("currentNum",null);
			map.put("endNum", null);
		}else{
			map.put("currentNum",pageModelDomain.getCurrentNum()+"");
			map.put("endNum", pageModelDomain.getPageSize()+"");
		}
		map.put("fName", pageModelDomain.getfName());
		map.put("fCode", pageModelDomain.getfCode());
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		return ljflRecycleTypeMapper.selectRecycleTypeByPamaters(map);
	}

	@Override
	public int insertRecycletype(LjflRecycleType type) {
		return ljflRecycleTypeMapper.insert(type);
	}

	@Override
	public int updateRecycleType(LjflRecycleType type) {
		return ljflRecycleTypeMapper.updateByPrimaryKey(type);
	}

	@Override
	public void deleteRecycleTypeById(String string) {
		ljflRecycleTypeMapper.deleteByPrimaryKey(string);		
	}

	@Override
	public List<LjflQuestion> selecLjflQuestionByDateTotal(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();

		if(pageModelDomain.getPageSize()==0){
			map.put("currentNum",null);
			map.put("endNum", null);
		}else{
			map.put("currentNum",pageModelDomain.getCurrentNum()+"");
			map.put("endNum", pageModelDomain.getPageSize()+"");
		}
		map.put("tableName", pageModelDomain.getTableName());
		map.put("startTime", pageModelDomain.getStartTime());
		map.put("endTime", pageModelDomain.getEndTime());
		map.put("fHandlingStatus", pageModelDomain.getfHandlingStatus());
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		return ljflQuestionMapper.selecLjflQuestionByDateTotal(map);
	}

	@Override
	public int selecLjflQuestionByDateTotalSize(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", pageModelDomain.getTableName());
		map.put("startTime", pageModelDomain.getStartTime());
		map.put("endTime", pageModelDomain.getEndTime());
		map.put("fHandlingStatus", pageModelDomain.getfHandlingStatus());
		map.put("beendeleted", pageModelDomain.getBeendeleted());
		return ljflQuestionMapper.selecLjflQuestionByDateTotalSize(map);
	}

	@Override
	public CloudStaff selectCloudStaffById(String id) {
		return cloudStaffMapper.selectByPrimaryKey(id);
	}
	@Override
	public LjflWorkPoint selectLjflWorkPointById(String id) {
		return ljflWorkPointMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<LjflWorkPoint> selectLjflWorkPointListByPamaters(Map<String, String> map) {
		return ljflWorkPointMapper.selectLjflWorkPointListByPamaters(map);
	}
	@Override
	public int selectCloudStaffSize(Map<String, String> map) {
	
		
		
		return cloudStaffMapper.selectCloudStaffSize(map);
	}

	@Override
	public List<CloudStaff> selectCloudStaffData(Map<String, String> map){

		
		return cloudStaffMapper.selectCloudStaffData(map);
	}

	@Override
	public int insertLjflQuestion(Map<String, String> map) {
		return ljflQuestionMapper.insertLjflQuestion(map);
	}

	@Override
	public LjflQuestion selectLjflQuestionByPamaters(Map<String, String> map) {
		return ljflQuestionMapper.selectLjflQuestionByPamaters(map);
	}

	@Override
	public int updateLjflQuestion(Map<String, String> map) {
		return ljflQuestionMapper.updateLjflQuestion(map);
	}

	@Override
	public int deleterLjflQuestion(Map<String, String> map) {
		return ljflQuestionMapper.deleterLjflQuestion(map);
	}

	@Override
	public List<LjflStaffStatistic> selectStaffStaticListByPamaters(Map<String, String> map) {
		return ljflStaffStatisticMapper.selectStaffStaticListByPamaters(map);
	}

	@Override
	public List<LjflStaffStatistic> selectStaffStaticListByPamatersNoGroupBy(Map<String, String> map) {
		return ljflStaffStatisticMapper.selectStaffStaticListByPamatersNoGroupBy(map);
	}

	@Override
	public int selectRecycleAppointSizeByPamater(Map<String, Object> map) {
		return ljflRecycleAppointmentMapper.selectRecycleAppointSizeByPamater(map);
	}

	@Override
	public List<LjflRecycleAppointment> selectRecycleAppointByPamaters(Map<String, Object> map) {
		return ljflRecycleAppointmentMapper.selectRecycleAppointByPamaters(map);
	}

	@Override
	public LjflRecycleAppointment getLjflRecycleAppointmentById(String id) {
		return ljflRecycleAppointmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateAppointmentByPrimaryKey(LjflRecycleAppointment appointment) {
		return ljflRecycleAppointmentMapper.updateByPrimaryKey(appointment);
	}

	@Override
	public int selectLjflTrashCanSizeByPamater(Map<String, Object> map) {
		return ljflTrashCanMapper.selectLjflTrashCanSizeByPamater(map);
	}

	@Override
	public List<LjflTrashCan> selectLjflTrashCanByPamaters(Map<String, Object> map) {
		return ljflTrashCanMapper.selectLjflTrashCanByPamaters(map);
	}

	@Override
	public LjflType selectljflTypeById(String getfLjflTypeId) {
		return ljflTypeMapper.selectByPrimaryKey(getfLjflTypeId);
	}

	@Override
	public List<LjflType> selectLjflTypeListByMap(Map<String, String> map) {
		return ljflTypeMapper.selectLjflTypeListByParamters(map);
	}

	@Override
	public int insertLjflTrashCan(LjflTrashCanWithBLOBs trashCan) {
		return ljflTrashCanMapper.insert(trashCan);
	}

	@Override
	public LjflTrashCanWithBLOBs selectLjflTrashCanById(String id) {
		return ljflTrashCanMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateLjflTrashCan(LjflTrashCanWithBLOBs trashCan) {
		return ljflTrashCanMapper.updateByPrimaryKey(trashCan);
	}

	@Override
	public int selectLjflSpliOverConfigSizeByPamater(Map<String, Object> map) {
		return ljflSpillOverConfigMapper.selectLjflSpliOverConfigSizeByPamater(map);
	}

	@Override
	public List<LjflSpillOverConfig> selectLjflSpillOverConfigByPamaters(Map<String, Object> map) {
		return ljflSpillOverConfigMapper.selectLjflSpillOverConfigByPamaters(map);
	}

	@Override
	public int insertLjflSpillOverConfig(LjflSpillOverConfig spliOverConfig) {
		return ljflSpillOverConfigMapper.insert(spliOverConfig);
	}

	@Override
	public LjflSpillOverConfig selectLjflSpillOverConfigById(String id) {
		return ljflSpillOverConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updatespliOverConfig(LjflSpillOverConfig spliOverConfig) {
		return ljflSpillOverConfigMapper.updateByPrimaryKey(spliOverConfig);
	}

	@Override
	public LjflSpillOverConfig selectspliOverConfigById(String id) {
		return ljflSpillOverConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public int selectResponseTimeConfigSizeByPamater(Map<String, Object> map) {
		return ljflResponseTimeConfigMapper.selectResponseTimeConfigSizeByPamater(map);
	}

	@Override
	public List<LjflResponseTimeConfig> selectResponseTimeConfigByPamaters(Map<String, Object> map) {
		return ljflResponseTimeConfigMapper.selectResponseTimeConfigByPamaters(map);
	}

	@Override
	public int insertLjflResponseTimeConfig(LjflResponseTimeConfig responseTimeConfig) {
		return ljflResponseTimeConfigMapper.insert(responseTimeConfig);
	}

	@Override
	public LjflResponseTimeConfig selectLjflResponseTimeConfigById(String id) {
		return ljflResponseTimeConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateResponseTimeConfig(LjflResponseTimeConfig responseTimeConfig) {
		return ljflResponseTimeConfigMapper.updateByPrimaryKey(responseTimeConfig);
	}

	@Override
	public int selecCollectRecordTotalSize(PageModelDomain pageModelDomain) {
		return ljflCollectRecordMapper.selecCollectRecordTotalSize(pageModelDomain);
	}

	@Override
	public List<LjflCollectRecord> selecCollectRecordByDateTotal(PageModelDomain pageModelDomain) {
		return ljflCollectRecordMapper.selecCollectRecordByDateTotal(pageModelDomain);
	}

	@Override
	public int selecSwipCardRecordTotalSize(PageModelDomain pageModelDomain) {
		return ljflSwipCardRecordMapper.selecSwipCardRecordTotalSize(pageModelDomain);
	}

	@Override
	public List<LjflSwipCardRecord> selecSwipCardRecordByDateTotal(PageModelDomain pageModelDomain) {
		return ljflSwipCardRecordMapper.selecSwipCardRecordByDateTotal(pageModelDomain);
	}

	@Override
	public int selectDispatchTaskSizeByPamater(Map<String, String> map) {
		return ljflDispatchTaskMapper.selectDispatchTaskSizeByPamater(map);
	}

	@Override
	public List<LjflDispatchTask> selectDispatchTaskByPamaters(Map<String, String> map) {
		return ljflDispatchTaskMapper.selectDispatchTaskByPamaters(map);
	}

	@Override
	public int selecDispatchTaskHistorySize(Map<String, String> map) {
		return ljflDispatchTaskMapper.selecDispatchTaskHistorySize(map);
	}

	@Override
	public List<LjflDispatchTask> selecDispatchTaskHistoryTotal(Map<String, String> map) {
		return ljflDispatchTaskMapper.selecDispatchTaskHistoryTotal(map);
	}

	@Override
	public int insert(LjflPutRecord1 record) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tablename",record.getTablename());
		map.put("id",record.getId());
		map.put("createtime",record.getCreatetime());
		
		System.out.println("map."+record.getCreatetime());
		
		map.put("lastchangetime",record.getLastchangetime());
		map.put("status",record.getStatus());
		map.put("beendeleted",record.getBeendeleted());
		map.put("deletedtime",record.getDeletedtime());
		map.put("businesssystemid",record.getBusinesssystemid());
		map.put("tenantid",record.getTenantid());
		map.put("divisiontime",record.getDivisiontime());
		map.put("fConfirmLatitude",record.getfConfirmLatitude());
		map.put("fConfirmLatitudeDone",record.getfConfirmLatitudeDone());
		map.put("fConfirmLongitude",record.getfConfirmLongitude());
		map.put("fConfirmLongitudeDone",record.getfConfirmLongitudeDone());
		map.put("fConfirmStatus",record.getfConfirmStatus());
		map.put("fConfirmUserId",record.getfConfirmUserId());
		map.put("fQuality",record.getfQuality());
		map.put("fReadStatus",record.getfReadStatus());
		map.put("fSwingTime",record.getfSwingTime());
		map.put("fWeight",record.getfWeight());
		map.put("fConfirmRubbishTypeId",record.getfConfirmRubbishTypeId());
		map.put("fHouseholdId",record.getfHouseholdId());
		map.put("fStaffDetailId",record.getfStaffDetailId());
		map.put("fWorkPointId",record.getfWorkPointId());
		
		return ljflPutRecordMapper.insert(map);
	}

	@Override
	public LjflRecycleObject selectRecycleObjectbyname(String name) {
		return ljflRecycleObjectMapper.selectRecycleObjectbyname(name);
	}

	@Override
	public LjflRecycleType selectrecycletypebyname(String name) {
		return ljflRecycleTypeMapper.selectrecycletypebyname(name);
	}

	@Override
	public LjflType selectByname(String name) {
		return ljflTypeMapper.selectByname(name);
	}

}
