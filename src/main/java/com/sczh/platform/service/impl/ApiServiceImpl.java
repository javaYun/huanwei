package com.sczh.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.LjflHandDeviceMapper;
import com.sczh.platform.dao.LjflRecycleObjectMapper;
import com.sczh.platform.dao.LjflRecycleRecordMapper;
import com.sczh.platform.dao.LjflRecycleTypeMapper;
import com.sczh.platform.dao.LjflScheduleJobConfigMapper;
import com.sczh.platform.dao.LjflSmartCardMapper;
import com.sczh.platform.dao.LjflTypeMapper;
import com.sczh.platform.po.LjflHandDevice;
import com.sczh.platform.po.LjflRecycleObject;
import com.sczh.platform.po.LjflRecycleRecord1;
import com.sczh.platform.po.LjflRecycleType;
import com.sczh.platform.po.LjflScheduleJobConfig;
import com.sczh.platform.po.LjflSmartCard;
import com.sczh.platform.po.LjflType;
import com.sczh.platform.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService{

	
	@Autowired
	private LjflSmartCardMapper ljflSmartCardMapper;
	
	@Autowired
	private LjflTypeMapper ljflTypeMapper;
	
	@Autowired
	private LjflRecycleObjectMapper ljflRecycleObjectMapper;
	
	@Autowired
	private LjflScheduleJobConfigMapper ljflScheduleJobConfigMapper;
	
	@Autowired
	private LjflRecycleTypeMapper ljflRecycleTypeMapper;
	
	@Autowired
	private LjflRecycleRecordMapper ljflRecycleRecordMapper;
	
	@Autowired
	private LjflHandDeviceMapper ljflHandDeviceMapper;
	
	@Override
	public LjflSmartCard selectByCcode(String ccode) {
		return ljflSmartCardMapper.selectByCcode(ccode);
	}

	@Override
	public LjflType selectBycode(String code) {
		return ljflTypeMapper.selectBycode(code);
	}

	@Override
	public LjflSmartCard selectByfBindId(String fBindId) {
		return ljflSmartCardMapper.selectByfBindId(fBindId);
	}

	@Override
	public LjflRecycleObject selectRecycleObject(String id) {
		return ljflRecycleObjectMapper.selectRecycleObject(id);
	}

	@Override
	public LjflScheduleJobConfig selectByTenantCode(String tenantCode) {
		return ljflScheduleJobConfigMapper.selectByTenantCode(tenantCode);
	}

	@Override
	public List<LjflRecycleType> selectByTenantId(String tenantId) {
		return ljflRecycleTypeMapper.selectByTenantId(tenantId);
	}

	@Override
	public List<LjflRecycleObject> selectObjectByTenantId(String tenantId) {
		return ljflRecycleObjectMapper.selectObjectByTenantId(tenantId);
	}

	@Override
	public int insertRecycleRecord(LjflRecycleRecord1 record) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tablename",record.getTablename());
		map.put("id",record.getId());
		map.put("createtime",record.getCreatetime());
		map.put("lastchangetime",record.getLastchangetime());
		map.put("status",record.getStatus());
		map.put("beendeleted",record.getBeendeleted());
		map.put("deletedtime",record.getDeletedtime());
		map.put("businesssystemid",record.getBusinesssystemid());
		map.put("tenantid",record.getTenantid());
		map.put("divisiontime",record.getDivisiontime());
		map.put("fConfirmTime",record.getfConfirmTime());
		map.put("fMemo",record.getfMemo());
		map.put("fRecycleStaffId",record.getfRecycleStaffId());
		map.put("fScoreType",record.getfScoreType());
		map.put("fTotalScoreValue",record.getfTotalScoreValue());
		map.put("fUnitValue",record.getfUnitValue());
		map.put("fRecycleObjectId",record.getfRecycleObjectId());
		map.put("fStaffDetailId",record.getfStaffDetailId());
		map.put("latitude",record.getLatitude());
		map.put("latitudedone",record.getLatitudedone());
		map.put("longitude",record.getLongitude());
		map.put("longitudedone",record.getLongitudedone());
		
		return ljflRecycleRecordMapper.insertRecycleRecoed(map);
	}

	@Override
	public LjflHandDevice selectByHandDevice(String code) {
		return ljflHandDeviceMapper.selectByHandDevice(code);
	}

	@Override
	public int insertHandDevice(LjflHandDevice record) {
		return ljflHandDeviceMapper.insert(record);
	}

	@Override
	public int updateHandDevice(LjflHandDevice record) {
		// TODO Auto-generated method stub
		return ljflHandDeviceMapper.updateByPrimaryKey(record);
	}

	
}
