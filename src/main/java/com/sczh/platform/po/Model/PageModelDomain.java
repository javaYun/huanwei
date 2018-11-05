package com.sczh.platform.po.Model;

import java.util.List;

import com.sczh.platform.po.AreaRoleManager;
import com.sczh.platform.po.CloudStaff;
import com.sczh.platform.po.ContractInfo;
import com.sczh.platform.po.LjflCollectRecord;
import com.sczh.platform.po.LjflDispatchTask;
import com.sczh.platform.po.LjflDispatchTaskBlobs;
import com.sczh.platform.po.LjflHandDevice;
import com.sczh.platform.po.LjflPutRecord;
import com.sczh.platform.po.LjflQuestion;
import com.sczh.platform.po.LjflRecycleAppointment;
import com.sczh.platform.po.LjflRecycleObject;
import com.sczh.platform.po.LjflRecycleRecord;
import com.sczh.platform.po.LjflRecycleType;
import com.sczh.platform.po.LjflResidentType;
import com.sczh.platform.po.LjflResponseTimeConfig;
import com.sczh.platform.po.LjflScoreDetail;
import com.sczh.platform.po.LjflScoreRecord;
import com.sczh.platform.po.LjflSmartCard;
import com.sczh.platform.po.LjflSpillOverConfig;
import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.LjflStaffStatistic;
import com.sczh.platform.po.LjflSwipCardRecord;
import com.sczh.platform.po.LjflTrashCan;
import com.sczh.platform.po.LjflWorkPoint;
import com.sczh.platform.po.UnitInfo;

public class PageModelDomain {
	
	private String chaxunTime;
	private String startTime;
	private String endTime;
	private String staffName;
	private String pageNo;
	private int pageSize;//每页显示多少数据
	private int currentPage;
	private int  pageTotalNum;//总共有多少页
	private int pageTotalSize;//总共有多少条数据
	private int cishubiaozhun;//次数标准
	private int weightNum;//重量标准
	private List<WorkPointRate> workRates;

	/**
	 * 源头排放登记系统 ljfl_staff_detail 的id
	 */
	private String staffDetailId ;
	/**
	 * staffDetail 源头排放登记 居民信息表
	 */
	private List<LjflStaffDetail> staffDetailList ;
	
	private String f_add_type ;
	private int currentNum;
	private int endNum;
	private List<LjflScoreRecord> scoreRecordList;
	
	private String areaId;
	private List<UnitInfo> unitInfoList;
	
	private String userid;
	private List<AreaRoleManager> areaRolerManagerList;
	private String unitId;
	
	
	private List<ContractInfo> contractInfo;
	
	private List<LjflHandDevice> handdeviceinfo;
	
	private String code;//设备号
	
	private String name;//姓名
	
	
	/**
	 * Ljfl_HouseHold表
	 */
	private String fName;
	private String  fHouseholdId;
	private List<LjflPutRecord> ljflPutRecordList;
	/**
	 * 查询结果
	 * yes
	 * no
	 */
	private String result;
	
	/**
	 * 回收记录
	 * @return
	 */
	/**
	 * 是否删除
	 */
	private String beendeleted;
	/**
	 * 回收物品id
	 */
	private String fRecycleObjectId;
	/**
	 * 回收的积分类型
	 */
	private String fScoreType;
	
	private List<LjflRecycleRecord>  ljflRecycleRecordList;
	
	/**
	 * 回收物品 编码   回收类型id  
	 */
	private String fCode;
	private String fRecycleTypeId;
	private List<LjflRecycleObject> ljflRecycleObjectList;
	/**
	 * 回收类型
	 */
	private List<LjflRecycleType> ljflRecycleTypelist;
	
	/**
	 * 问题上报
	 * @return
	 */
	private String  fHandlingStatus;//处理状态
	
	private List<LjflQuestion> ljflQuestionList;
	
	private String questionId;
	
	private List<CloudStaff> cloudStaffList;
	/**
	 * 垃圾桶站
	 */
	private List<LjflWorkPoint> ljflWorkPointList;
	/**
	 * houseId
	 */
	private String fHousesId;
	
	private String tenantid;
	
	private String orgName;
	
	/**
	 * 工作人员统计
	 * @return
	 */
	private String  staffid;
	
	private List<LjflStaffStatistic> ljflStaffStatisticList;
	
	private List<LjflStaffStaticModel> ljflStaffStaticModelList;
	
	/**
	 * 预约回收
	 * @return
	 */
	private String fAppointmentStatus;//回收状态
	/**
	 * 预约回收列表 
	 */
	private List<LjflRecycleAppointment> recycleAppointmentList;
	/**
	 * 垃圾桶
	 * @return
	 */
	private List<LjflTrashCan> ljflTrashCanList;
	/**
	 * 满溢状态配置
	 * @return
	 */
	private List<LjflSpillOverConfig> ljflSpillOverConfigList;
	
	/**
	 * 满溢状态响应时间配置
	 * @return
	 */
	private List<LjflResponseTimeConfig> ljflResponseTimeConfigList;
	
	/**
	 * 收集记录
	 * @return
	 */
	private List<LjflCollectRecord> ljflCollectRecordList;
	
	private String fQymanId;//清运人员id
	
	private String fLjflTypeId;//垃圾类型id
	
	private String houseSelected;// 小区id
	
	/**
	 * 清运人员刷卡记录
	 * @return
	 */
	private List<LjflSwipCardRecord> ljflSwipCardRecordList;
	
	/**
	 * 当前调度任务
	 * @return
	 */
	private List<LjflDispatchTask> ljflDispatchTask ;
	private List<LjflDispatchTaskBlobs> ljflDispatchTaskBlobs ;
	
	/**
	 * 分类箱id
	 */
	private String fWorkPointId; 
	/**
	 * 任务状态
	 */
	private String fTaskStatus;
	
	
	
	/**
	 * 居民类型
	 * @return
	 */
	private List<LjflResidentType>  ljflResidentTypeList;
	/*
	 * 智能卡
	 */
	private  List<LjflSmartCard> ljflSmartCardList;
	
	private String fCCode;//卡号
	
	private String fBindName;//绑定居民姓名
	
	private String fBindType;//绑定居民类型
	
	/**
	 * 居民信息
	 * @return
	 */
	private String housesId;
	private String communityId;
	private String phone;
	
	public List<LjflSmartCard> getLjflSmartCardList() {
		return ljflSmartCardList;
	}
	public void setLjflSmartCardList(List<LjflSmartCard> ljflSmartCardList) {
		this.ljflSmartCardList = ljflSmartCardList;
	}
	public String getfCCode() {
		return fCCode;
	}
	public void setfCCode(String fCCode) {
		this.fCCode = fCCode;
	}
	public String getfBindName() {
		return fBindName;
	}
	public void setfBindName(String fBindName) {
		this.fBindName = fBindName;
	}
	public String getfBindType() {
		return fBindType;
	}
	public void setfBindType(String fBindType) {
		this.fBindType = fBindType;
	}
	public List<LjflResidentType> getLjflResidentTypeList() {
		return ljflResidentTypeList;
	}
	public void setLjflResidentTypeList(List<LjflResidentType> ljflResidentTypeList) {
		this.ljflResidentTypeList = ljflResidentTypeList;
	}
	public List<LjflDispatchTaskBlobs> getLjflDispatchTaskBlobs() {
		return ljflDispatchTaskBlobs;
	}
	public void setLjflDispatchTaskBlobs(List<LjflDispatchTaskBlobs> ljflDispatchTaskBlobs) {
		this.ljflDispatchTaskBlobs = ljflDispatchTaskBlobs;
	}
	public List<LjflDispatchTask> getLjflDispatchTask() {
		return ljflDispatchTask;
	}
	public void setLjflDispatchTask(List<LjflDispatchTask> ljflDispatchTask) {
		this.ljflDispatchTask = ljflDispatchTask;
	}
	public String getfWorkPointId() {
		return fWorkPointId;
	}
	public void setfWorkPointId(String fWorkPointId) {
		this.fWorkPointId = fWorkPointId;
	}
	public String getfTaskStatus() {
		return fTaskStatus;
	}
	public void setfTaskStatus(String fTaskStatus) {
		this.fTaskStatus = fTaskStatus;
	}
	public List<LjflSwipCardRecord> getLjflSwipCardRecordList() {
		return ljflSwipCardRecordList;
	}
	public void setLjflSwipCardRecordList(List<LjflSwipCardRecord> ljflSwipCardRecordList) {
		this.ljflSwipCardRecordList = ljflSwipCardRecordList;
	}
	public String getfLjflTypeId() {
		return fLjflTypeId;
	}
	public void setfLjflTypeId(String fLjflTypeId) {
		this.fLjflTypeId = fLjflTypeId;
	}
	public String getHouseSelected() {
		return houseSelected;
	}
	public void setHouseSelected(String houseSelected) {
		this.houseSelected = houseSelected;
	}
	public String getfQymanId() {
		return fQymanId;
	}
	public void setfQymanId(String fQymanId) {
		this.fQymanId = fQymanId;
	}
	public List<LjflCollectRecord> getLjflCollectRecordList() {
		return ljflCollectRecordList;
	}
	public void setLjflCollectRecordList(List<LjflCollectRecord> ljflCollectRecordList) {
		this.ljflCollectRecordList = ljflCollectRecordList;
	}
	public List<LjflResponseTimeConfig> getLjflResponseTimeConfigList() {
		return ljflResponseTimeConfigList;
	}
	public void setLjflResponseTimeConfigList(List<LjflResponseTimeConfig> ljflResponseTimeConfigList) {
		this.ljflResponseTimeConfigList = ljflResponseTimeConfigList;
	}
	public List<LjflSpillOverConfig> getLjflSpillOverConfigList() {
		return ljflSpillOverConfigList;
	}
	public void setLjflSpillOverConfigList(List<LjflSpillOverConfig> ljflSpillOverConfigList) {
		this.ljflSpillOverConfigList = ljflSpillOverConfigList;
	}
	public List<LjflTrashCan> getLjflTrashCanList() {
		return ljflTrashCanList;
	}
	public void setLjflTrashCanList(List<LjflTrashCan> ljflTrashCanList) {
		this.ljflTrashCanList = ljflTrashCanList;
	}
	public String getfAppointmentStatus() {
		return fAppointmentStatus;
	}
	public void setfAppointmentStatus(String fAppointmentStatus) {
		this.fAppointmentStatus = fAppointmentStatus;
	}

	public List<LjflRecycleAppointment> getRecycleAppointmentList() {
		return recycleAppointmentList;
	}
	public void setRecycleAppointmentList(List<LjflRecycleAppointment> recycleAppointmentList) {
		this.recycleAppointmentList = recycleAppointmentList;
	}
	public List<LjflStaffStaticModel> getLjflStaffStaticModelList() {
		return ljflStaffStaticModelList;
	}
	public void setLjflStaffStaticModelList(List<LjflStaffStaticModel> ljflStaffStaticModelList) {
		this.ljflStaffStaticModelList = ljflStaffStaticModelList;
	}
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public List<LjflStaffStatistic> getLjflStaffStatisticList() {
		return ljflStaffStatisticList;
	}
	public void setLjflStaffStatisticList(List<LjflStaffStatistic> ljflStaffStatisticList) {
		this.ljflStaffStatisticList = ljflStaffStatisticList;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getTenantid() {
		return tenantid;
	}
	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}
	public List<LjflWorkPoint> getLjflWorkPointList() {
		return ljflWorkPointList;
	}
	public void setLjflWorkPointList(List<LjflWorkPoint> ljflWorkPointList) {
		this.ljflWorkPointList = ljflWorkPointList;
	}
	public String getfHousesId() {
		return fHousesId;
	}
	public void setfHousesId(String fHousesId) {
		this.fHousesId = fHousesId;
	}
	public String getfHandlingStatus() {
		return fHandlingStatus;
	}
	public void setfHandlingStatus(String fHandlingStatus) {
		this.fHandlingStatus = fHandlingStatus;
	}
	public List<LjflQuestion> getLjflQuestionList() {
		return ljflQuestionList;
	}
	public void setLjflQuestionList(List<LjflQuestion> ljflQuestionList) {
		this.ljflQuestionList = ljflQuestionList;
	}
	public List<CloudStaff> getCloudStaffList() {
		return cloudStaffList;
	}
	public void setCloudStaffList(List<CloudStaff> cloudStaffList) {
		this.cloudStaffList = cloudStaffList;
	}
	public List<LjflRecycleType> getLjflRecycleTypelist() {
		return ljflRecycleTypelist;
	}
	public void setLjflRecycleTypelist(List<LjflRecycleType> ljflRecycleTypelist) {
		this.ljflRecycleTypelist = ljflRecycleTypelist;
	}
	public String getfCode() {
		return fCode;
	}
	public void setfCode(String fCode) {
		this.fCode = fCode;
	}
	public String getfRecycleTypeId() {
		return fRecycleTypeId;
	}
	public void setfRecycleTypeId(String fRecycleTypeId) {
		this.fRecycleTypeId = fRecycleTypeId;
	}
	public List<LjflRecycleObject> getLjflRecycleObjectList() {
		return ljflRecycleObjectList;
	}
	public void setLjflRecycleObjectList(List<LjflRecycleObject> ljflRecycleObjectList) {
		this.ljflRecycleObjectList = ljflRecycleObjectList;
	}
	public List<LjflRecycleRecord> getLjflRecycleRecordList() {
		return ljflRecycleRecordList;
	}
	public void setLjflRecycleRecordList(List<LjflRecycleRecord> ljflRecycleRecordList) {
		this.ljflRecycleRecordList = ljflRecycleRecordList;
	}
	public String getBeendeleted() {
		return beendeleted;
	}
	public void setBeendeleted(String beendeleted) {
		this.beendeleted = beendeleted;
	}
	public String getfRecycleObjectId() {
		return fRecycleObjectId;
	}
	public void setfRecycleObjectId(String fRecycleObjectId) {
		this.fRecycleObjectId = fRecycleObjectId;
	}
	public String getfScoreType() {
		return fScoreType;
	}
	public void setfScoreType(String fScoreType) {
		this.fScoreType = fScoreType;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getfHouseholdId() {
		return fHouseholdId;
	}
	public void setfHouseholdId(String fHouseholdId) {
		this.fHouseholdId = fHouseholdId;
	}
	public List<LjflPutRecord> getLjflPutRecordList() {
		return ljflPutRecordList;
	}
	public void setLjflPutRecordList(List<LjflPutRecord> ljflPutRecordList) {
		this.ljflPutRecordList = ljflPutRecordList;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<AreaRoleManager> getAreaRolerManagerList() {
		return areaRolerManagerList;
	}
	public void setAreaRolerManagerList(List<AreaRoleManager> areaRolerManagerList) {
		this.areaRolerManagerList = areaRolerManagerList;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public List<UnitInfo> getUnitInfoList() {
		return unitInfoList;
	}
	public void setUnitInfoList(List<UnitInfo> unitInfoList) {
		this.unitInfoList = unitInfoList;
	}
	/**
	 * 动态数据库名
	 */
	private String tableName;
	/**
	 * 现场活动类型id
	 */
	private String ljflExerciseId;
	/**
	 * 投放积分明细表 积分兑换 活动类型
	 */
	private List<LjflScoreDetail> scoreDetailList;
	
	public String getLjflExerciseId() {
		return ljflExerciseId;
	}
	public void setLjflExerciseId(String ljflExerciseId) {
		this.ljflExerciseId = ljflExerciseId;
	}
	public List<LjflScoreDetail> getScoreDetailList() {
		return scoreDetailList;
	}
	public void setScoreDetailList(List<LjflScoreDetail> scoreDetailList) {
		this.scoreDetailList = scoreDetailList;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<LjflScoreRecord> getScoreRecordList() {
		return scoreRecordList;
	}
	public void setScoreRecordList(List<LjflScoreRecord> scoreRecordList) {
		this.scoreRecordList = scoreRecordList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getChaxunTime() {
		return chaxunTime;
	}
	public void setChaxunTime(String chaxunTime) {
		this.chaxunTime = chaxunTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageTotalNum() {
		return pageTotalNum;
	}
	public void setPageTotalNum(int pageTotalNum) {
		this.pageTotalNum = pageTotalNum;
	}
	public int getPageTotalSize() {
		return pageTotalSize;
	}
	public void setPageTotalSize(int pageTotalSize) {
		this.pageTotalSize = pageTotalSize;
	}
	public int getCishubiaozhun() {
		return cishubiaozhun;
	}
	public void setCishubiaozhun(int cishubiaozhun) {
		this.cishubiaozhun = cishubiaozhun;
	}
	public int getWeightNum() {
		return weightNum;
	}
	public void setWeightNum(int weightNum) {
		this.weightNum = weightNum;
	}
	public List<WorkPointRate> getWorkRates() {
		return workRates;
	}
	public void setWorkRates(List<WorkPointRate> workRates) {
		this.workRates = workRates;
	}
	

	
	
	public String getF_add_type() {
		return f_add_type;
	}
	public void setF_add_type(String f_add_type) {
		this.f_add_type = f_add_type;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public List<LjflStaffDetail> getStaffDetailList() {
		return staffDetailList;
	}
	public void setStaffDetailList(List<LjflStaffDetail> staffDetailList) {
		this.staffDetailList = staffDetailList;
	}
	public String getStaffDetailId() {
		return staffDetailId;
	}
	public void setStaffDetailId(String staffDetailId) {
		this.staffDetailId = staffDetailId;
	}
	public List<ContractInfo> getContractInfo() {
		return contractInfo;
	}
	public void setContractInfo(List<ContractInfo> contractInfo) {
		this.contractInfo = contractInfo;
	}
	
	
	
	public List<LjflHandDevice> getHanddeviceinfo() {
		return handdeviceinfo;
	}
	public void setHanddeviceinfo(List<LjflHandDevice> handdeviceinfo) {
		this.handdeviceinfo = handdeviceinfo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getHousesId() {
		return housesId;
	}
	public void setHousesId(String housesId) {
		this.housesId = housesId;
	}
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "PageModelDomain [chaxunTime=" + chaxunTime + ", startTime="
				+ startTime + ", endTime=" + endTime + ", staffName="
				+ staffName + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", currentPage=" + currentPage + ", pageTotalNum="
				+ pageTotalNum + ", pageTotalSize=" + pageTotalSize
				+ ", cishubiaozhun=" + cishubiaozhun + ", weightNum="
				+ weightNum + ", workRates=" + workRates + ", staffDetailId="
				+ staffDetailId + ", staffDetailList=" + staffDetailList
				+ ", f_add_type=" + f_add_type + ", currentNum=" + currentNum
				+ ", endNum=" + endNum + ", scoreRecordList=" + scoreRecordList
				+ ", areaId=" + areaId + ", unitInfoList=" + unitInfoList
				+ ", userid=" + userid + ", areaRolerManagerList="
				+ areaRolerManagerList + ", unitId=" + unitId
				+ ", contractInfo=" + contractInfo + ", handdeviceinfo="
				+ handdeviceinfo + ", code=" + code + ", name=" + name
				+ ", fName=" + fName + ", fHouseholdId=" + fHouseholdId + ", ljflPutRecordList="
				+ ljflPutRecordList + ", result=" + result + ", beendeleted="
				+ beendeleted + ", fRecycleObjectId=" + fRecycleObjectId
				+ ", fScoreType=" + fScoreType + ", ljflRecycleRecordList="
				+ ljflRecycleRecordList + ", fCode=" + fCode
				+ ", fRecycleTypeId=" + fRecycleTypeId
				+ ", ljflRecycleObjectList=" + ljflRecycleObjectList
				+ ", ljflRecycleTypelist=" + ljflRecycleTypelist
				+ ", fHandlingStatus=" + fHandlingStatus
				+ ", ljflQuestionList=" + ljflQuestionList + ", questionId="
				+ questionId + ", cloudStaffList=" + cloudStaffList
				+ ", ljflWorkPointList=" + ljflWorkPointList + ", fHousesId="
				+ fHousesId + ", tenantid=" + tenantid + ", orgName=" + orgName
				+ ", staffid=" + staffid + ", ljflStaffStatisticList="
				+ ljflStaffStatisticList + ", ljflStaffStaticModelList="
				+ ljflStaffStaticModelList + ", fAppointmentStatus="
				+ fAppointmentStatus + ", recycleAppointmentList="
				+ recycleAppointmentList + ", ljflTrashCanList="
				+ ljflTrashCanList + ", ljflSpillOverConfigList="
				+ ljflSpillOverConfigList + ", ljflResponseTimeConfigList="
				+ ljflResponseTimeConfigList + ", ljflCollectRecordList="
				+ ljflCollectRecordList + ", fQymanId=" + fQymanId
				+ ", fLjflTypeId=" + fLjflTypeId + ", houseSelected="
				+ houseSelected + ", ljflSwipCardRecordList="
				+ ljflSwipCardRecordList + ", ljflDispatchTask="
				+ ljflDispatchTask + ", ljflDispatchTaskBlobs="
				+ ljflDispatchTaskBlobs + ", fWorkPointId=" + fWorkPointId
				+ ", fTaskStatus=" + fTaskStatus + ", ljflResidentTypeList="
				+ ljflResidentTypeList + ", ljflSmartCardList="
				+ ljflSmartCardList + ", fCCode=" + fCCode + ", fBindName="
				+ fBindName + ", fBindType=" + fBindType + ", housesId="
				+ housesId + ", communityId=" + communityId + ", phone="
				+ phone + ", tableName=" + tableName + ", ljflExerciseId="
				+ ljflExerciseId + ", scoreDetailList=" + scoreDetailList + "]";
	}
	
	
	
	
}

