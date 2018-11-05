package com.sczh.platform.service;

import java.util.List;
import java.util.Map;

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

public interface LjflPutRecordService {
	/**
	 * 获取所有居户的数量
	 * @param pageModelDomain
	 * @return
	 */
	int selectHouseHoldTotalSize(PageModelDomain pageModelDomain);
	/**
	 * 获取所有居户
	 * @param pageModelDomain
	 * @return
	 */
	List<LjflStaffDetail> selectHouseHoldData(PageModelDomain pageModelDomain);
	
	int selecPutRecordByDateTotalSize(PageModelDomain pageModelDomain);
	
	List<LjflPutRecord> selecPutRecordByDateTotal(PageModelDomain pageModelDomain);
	/**
	 * 根据判断条件获取recycleType数据
	 * @param num
	 * @return
	 */
	List<LjflRecycleType> selectLjflTypeListByParamters(int num);
	/**
	 * 根据判断条件获取recycleObject数据
	 * @param num
	 * @return
	 */
	List<LjflRecycleObject> selectLjflObjectListByParamters(int num);
	/**
	 * 查询size
	 * @param pageModelDomain
	 * @return
	 */
	int selecRecycycleRecordByDateTotalSize(PageModelDomain pageModelDomain);
	
	List<LjflRecycleRecord> selecRecycleRecordByDateTotal(PageModelDomain pageModelDomain);
	
	LjflRecycleObject selectRecycleObjectById(String getfRecycleObjectId);
	
	int selectRecycleObjectByPamaterSize(PageModelDomain pageModelDomain);
	
	List<LjflRecycleObject> selectRecycleObjectByPamaters(PageModelDomain pageModelDomain);
	
	LjflRecycleType selectLjflRecycleTypeById(String getfRecycleTypeId);
	
	int insertRecycleObject(LjflRecycleObject object);
	
	int updateRecycleObject(LjflRecycleObject object);
	
	void deleteRecycleObjectById(String string);
	
	int selectRecycleTypeByPamaterSize(PageModelDomain pageModelDomain);
	
	List<LjflRecycleType> selectRecycleTypeByPamaters(PageModelDomain pageModelDomain);
	
	int insertRecycletype(LjflRecycleType type);
	
	int updateRecycleType(LjflRecycleType type);
	
	void deleteRecycleTypeById(String string);
	
	List<LjflQuestion> selecLjflQuestionByDateTotal(PageModelDomain pageModelDomain);
	
	int selecLjflQuestionByDateTotalSize(PageModelDomain pageModelDomain);
	
	CloudStaff selectCloudStaffById(String getfStaffId);
	
	LjflWorkPoint selectLjflWorkPointById(String getfWorkPointId);
	
	List<LjflWorkPoint> selectLjflWorkPointListByPamaters(Map<String, String> map);
	
	int selectCloudStaffSize(Map<String, String> map);
	
	List<CloudStaff> selectCloudStaffData(Map<String, String> map);
	/**
	 * 保存动态问题上报表
	 * @param map
	 * @return
	 */
	int insertLjflQuestion(Map<String, String> map);
	
	LjflQuestion selectLjflQuestionByPamaters(Map<String, String> map);
	
	int updateLjflQuestion(Map<String, String> map);
	
	int deleterLjflQuestion(Map<String, String> map);
	
	List<LjflStaffStatistic> selectStaffStaticListByPamaters(Map<String, String> map);
	
	List<LjflStaffStatistic> selectStaffStaticListByPamatersNoGroupBy(Map<String, String> map);
	
	int selectRecycleAppointSizeByPamater(Map<String, Object> map);
	
	List<LjflRecycleAppointment> selectRecycleAppointByPamaters(Map<String, Object> map);
	
	LjflRecycleAppointment getLjflRecycleAppointmentById(String id);
	
	int updateAppointmentByPrimaryKey(LjflRecycleAppointment appointment);
	
	int selectLjflTrashCanSizeByPamater(Map<String, Object> map);
	
	List<LjflTrashCan> selectLjflTrashCanByPamaters(Map<String, Object> map);
	
	LjflType selectljflTypeById(String getfLjflTypeId);
	
	List<LjflType> selectLjflTypeListByMap(Map<String, String> map);
	
	int insertLjflTrashCan(LjflTrashCanWithBLOBs trashCan);
	
	LjflTrashCanWithBLOBs selectLjflTrashCanById(String id);
	
	int updateLjflTrashCan(LjflTrashCanWithBLOBs trashCan);
	
	int selectLjflSpliOverConfigSizeByPamater(Map<String, Object> map);
	
	List<LjflSpillOverConfig> selectLjflSpillOverConfigByPamaters(Map<String, Object> map);
	
	int insertLjflSpillOverConfig(LjflSpillOverConfig spliOverConfig);
	
	LjflSpillOverConfig selectLjflSpillOverConfigById(String id);
	
	int updatespliOverConfig(LjflSpillOverConfig spliOverConfig);
	
	LjflSpillOverConfig selectspliOverConfigById(String id);
	
	int selectResponseTimeConfigSizeByPamater(Map<String, Object> map);
	
	List<LjflResponseTimeConfig> selectResponseTimeConfigByPamaters(Map<String, Object> map);
	
	int insertLjflResponseTimeConfig(LjflResponseTimeConfig responseTimeConfig);
	
	LjflResponseTimeConfig selectLjflResponseTimeConfigById(String id);
	
	int updateResponseTimeConfig(LjflResponseTimeConfig responseTimeConfig);
	
	int selecCollectRecordTotalSize(PageModelDomain pageModelDomain);
	
	List<LjflCollectRecord> selecCollectRecordByDateTotal(PageModelDomain pageModelDomain);
	
	int selecSwipCardRecordTotalSize(PageModelDomain pageModelDomain);
	
	List<LjflSwipCardRecord> selecSwipCardRecordByDateTotal(PageModelDomain pageModelDomain);
	
	int selectDispatchTaskSizeByPamater(Map<String, String> map);
	
	List<LjflDispatchTask> selectDispatchTaskByPamaters(Map<String, String> map);
	
	int selecDispatchTaskHistorySize(Map<String, String> map);
	
	List<LjflDispatchTask> selecDispatchTaskHistoryTotal(Map<String, String> map);
	
	int insert(LjflPutRecord1 ljflPutRecord);

	LjflRecycleObject selectRecycleObjectbyname(String name);

	LjflRecycleType selectrecycletypebyname(String name);

    LjflType selectByname(String name);

}
