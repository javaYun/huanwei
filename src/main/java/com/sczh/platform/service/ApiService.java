package com.sczh.platform.service;

import java.util.List;

import com.sczh.platform.po.LjflHandDevice;
import com.sczh.platform.po.LjflRecycleObject;
import com.sczh.platform.po.LjflRecycleRecord1;
import com.sczh.platform.po.LjflRecycleType;
import com.sczh.platform.po.LjflScheduleJobConfig;
import com.sczh.platform.po.LjflSmartCard;
import com.sczh.platform.po.LjflType;

public interface ApiService {

	LjflSmartCard selectByCcode(String ccode);
	LjflType selectBycode(String code);
	LjflSmartCard selectByfBindId(String fBindId);
	LjflRecycleObject selectRecycleObject(String id);
	LjflScheduleJobConfig selectByTenantCode(String tenantCode);
	List<LjflRecycleObject> selectObjectByTenantId(String tenantId);
	List<LjflRecycleType> selectByTenantId(String tenantId);
	
	int insertRecycleRecord(LjflRecycleRecord1 record);
	 LjflHandDevice selectByHandDevice(String code);
	 int insertHandDevice(LjflHandDevice record);
	 int updateHandDevice(LjflHandDevice record);
}
