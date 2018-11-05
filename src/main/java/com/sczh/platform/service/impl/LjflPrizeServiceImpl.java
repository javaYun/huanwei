package com.sczh.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.LjflPrizeMapper;
import com.sczh.platform.dao.LjflPrizeProvideMapper;
import com.sczh.platform.dao.LjflPrizeProvideRecordMapper;
import com.sczh.platform.po.LjflPrize;
import com.sczh.platform.po.LjflPrizeProvide;
import com.sczh.platform.po.LjflPrizeProvideRecord;
import com.sczh.platform.po.LjflPrizeProvideRecord1;
import com.sczh.platform.po.Model.LjflPrizePageDomain;
import com.sczh.platform.service.LjflPrizeService;

@Service
public class LjflPrizeServiceImpl implements LjflPrizeService{

	@Autowired
	private LjflPrizeProvideMapper ljflprizeProvidemapper;
	
	@Autowired
	private LjflPrizeMapper ljflprizemapper;
	
	@Autowired
	private LjflPrizeProvideRecordMapper ljflprizeproviderecordmapper;
	
	
	@Override
	public List<LjflPrize> selectByBeenDeleted() {
		return ljflprizemapper.selectByBeenDeleted();
	}

	@Override
	public int insert(LjflPrizeProvide record) {
		return ljflprizeProvidemapper.insert(record);
	}

	@Override
	public LjflPrizeProvide selectByPrimaryKey(String id) {
		return ljflprizeProvidemapper.selectByPrimaryKey(id);
	}

	@Override
	public LjflPrize selectByPrimaryKey1(String id) {
		return ljflprizemapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(LjflPrizeProvide record) {
		return ljflprizeProvidemapper.updateByPrimaryKey(record);
	}

	@Override
	public int selecScoreByDateTotalSize(LjflPrizePageDomain ljflPrizePageDomain) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", ljflPrizePageDomain.getTablename());
		map.put("staffDetailId", ljflPrizePageDomain.getStaffDetailId());
		map.put("startTime", ljflPrizePageDomain.getStartTime());
		map.put("endTime", ljflPrizePageDomain.getEndtime());
		map.put("prizeProvideId", ljflPrizePageDomain.getPrizeProvideId());
		map.put("prizeId", ljflPrizePageDomain.getPrizeId());
		map.put("provideStatus", ljflPrizePageDomain.getProvideStatus());
		
		return ljflprizeproviderecordmapper.selecScoreByDateTotalSize(map);
	}

	@Override
	public List<LjflPrizeProvideRecord> selecScoreByDateTotal(
			LjflPrizePageDomain ljflPrizePageDomain) {
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", ljflPrizePageDomain.getTablename());
		map.put("staffDetailId", ljflPrizePageDomain.getStaffDetailId());
		map.put("startTime", ljflPrizePageDomain.getStartTime());
		map.put("endTime", ljflPrizePageDomain.getEndtime());
		map.put("prizeProvideId", ljflPrizePageDomain.getPrizeProvideId());
		map.put("prizeId", ljflPrizePageDomain.getPrizeId());
		map.put("provideStatus", ljflPrizePageDomain.getProvideStatus());
		if(ljflPrizePageDomain.getPageSize()==0){
			map.put("currentNum",null);
			map.put("endNum", null);
		}else{
			map.put("currentNum",ljflPrizePageDomain.getCurrentPage()+"");
			map.put("endNum", ljflPrizePageDomain.getPageSize()+"");
			
	
		}
		return ljflprizeproviderecordmapper.selecScoreByDateTotal(map);
	}

	@Override
	public List<LjflPrize> selectPrizeInfoList(String startSize, String endSize) {
		return ljflprizemapper.selectPrizeInfoList(startSize, endSize);
	}

	@Override
	public List<LjflPrize> selectPrizeInfoListTotal() {
		return ljflprizemapper.selectPrizeInfoListTotal();
	}

	@Override
	public List<LjflPrize> selectPrizeInfoList1(String startSize,
			String endSize, String code, String name) {
		return ljflprizemapper.selectPrizeInfoList1(startSize, endSize, code, name);
	}

	@Override
	public List<LjflPrize> selectPrizeInfoListTotal1(String code, String name) {
		return ljflprizemapper.selectPrizeInfoListTotal1(code, name);
	}

	@Override
	public int insert(LjflPrize record) {
		return ljflprizemapper.insert(record);
	}

	@Override
	public LjflPrize selectByPrimaryKey2(String id) {
		return ljflprizemapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(LjflPrize record) {
		return ljflprizemapper.updateByPrimaryKey(record);
	}
	
	@Override
	public List<LjflPrizeProvide> selecScoreByDateTotal1(
			LjflPrizePageDomain ljflPrizePageDomain) {
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("code", ljflPrizePageDomain.getCode());
		map.put("name", ljflPrizePageDomain.getName());
		map.put("startTime", ljflPrizePageDomain.getStartTime());
		map.put("endTime", ljflPrizePageDomain.getEndtime());
	
		return ljflprizeproviderecordmapper.selecScoreByDateTotal1(map);
	}

	@Override
	public int selecScoreByDateTotalSize1(
			LjflPrizePageDomain ljflPrizePageDomain) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("code", ljflPrizePageDomain.getCode());
		map.put("name", ljflPrizePageDomain.getName());
		map.put("startTime", ljflPrizePageDomain.getStartTime());
		map.put("endTime", ljflPrizePageDomain.getEndtime());
	
		return ljflprizeproviderecordmapper.selecScoreByDateTotalSize1(map);
	}

	@Override
	public List<LjflPrizeProvide> selectPrizeProvideInfoList(String startSize,
			String endSize, String name, String code, String starttime,
			String endtime) {
		
		return ljflprizeProvidemapper.selectPrizeProvideInfoList(startSize, endSize, name, code, starttime, endtime);
	}

	@Override
	public List<LjflPrizeProvide> selectPrizeProvideInfoListTotal(String name,
			String code, String starttime, String endtime) {
		return ljflprizeProvidemapper.selectPrizeProvideInfoListTotal(name, code, starttime, endtime);
	}

	@Override
	public List<LjflPrizeProvide> selectPrizeProvideInfoListTotal1() {
		return ljflprizeProvidemapper.selectPrizeProvideInfoListTotal1();
	}

	@Override
	public LjflPrizeProvide selectByPrizeId(String prizeid) {
		return ljflprizeProvidemapper.selectByPrizeId(prizeid);
	}
	
	@Override
	public int insert(LjflPrizeProvideRecord1 record) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", record.getId());
		map.put("createtime", record.getCreatetime());
		map.put("lastchangetime", record.getLastchangetime());
		map.put("status", record.getStatus());
		map.put("beendeleted", record.getBeendeleted());
		map.put("deletedtime", record.getDeletedtime());
		map.put("businesssystemid", record.getBusinesssystemid());
		map.put("tenantid", record.getTenantid());
		map.put("divisiontime", record.getDivisiontime());
		map.put("cdkey", record.getCdkey());
		map.put("providedate", record.getProvidedate());
		map.put("providemanid", record.getProvidemanid());
		map.put("providenum", record.getProvidenum());
		map.put("providestatus", record.getProvidestatus());
		map.put("receivedate", record.getReceivedate());
		map.put("spendscore", record.getSpendscore());
		map.put("prizeid", record.getPrizeid());
		map.put("prizeprovideid", record.getPrizeprovideid());
		map.put("staffdetailid", record.getStaffdetailid());
		map.put("tablename", record.getTablename());

		return ljflprizeproviderecordmapper.insertmap(map);
	}

	@Override
	public int deletebyid(String id, String tablename) {
		return ljflprizeproviderecordmapper.deletebyid(id, tablename);
	}

	@Override
	public LjflPrizeProvideRecord selectBycdKey(String tablename, String cdkey) {
		return ljflprizeproviderecordmapper.selectBycdKey(tablename, cdkey);
	}

	@Override
	public int updateByPrimaryKey(LjflPrizeProvideRecord record) {
		return ljflprizeproviderecordmapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LjflPrize> selectljflprizebyname(String name) {
		return ljflprizemapper.selectljflprizebyname(name);
	}
	
}
