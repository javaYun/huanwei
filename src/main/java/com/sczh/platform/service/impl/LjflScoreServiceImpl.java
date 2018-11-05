package com.sczh.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.LjflScoreDetailMapper;
import com.sczh.platform.dao.LjflScoreRecordMapper;
import com.sczh.platform.dao.LjflStaffDetailMapper;
import com.sczh.platform.dao.LjflTypeMapper;
import com.sczh.platform.po.LjflScoreDetail;
import com.sczh.platform.po.LjflScoreRecord;
import com.sczh.platform.po.LjflScoreRecord1;
import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.LjflType;
import com.sczh.platform.po.Model.PageModelDomain;
import com.sczh.platform.service.LjflScoreService;
@Service
public class LjflScoreServiceImpl implements LjflScoreService {

	@Autowired
	private LjflStaffDetailMapper ljflStaffDetailMapper;
	@Autowired
	private LjflScoreRecordMapper ljflScoreRecordMapper;
	@Autowired
	private LjflScoreDetailMapper ljflScoreDetailMapper;
	@Autowired
	private LjflTypeMapper  ljflTypeMapper;
	@Override
	public List<LjflStaffDetail> selectStaffDetailList(PageModelDomain pageModelDomain) {
		pageModelDomain.setBeendeleted("0");
		return ljflStaffDetailMapper.selectStaffDetailList(pageModelDomain);
	}
	@Override
	public int selecStaffDetailtTotalSize(PageModelDomain pageModelDomain) {
		pageModelDomain.setBeendeleted("0");
		return ljflStaffDetailMapper.selecStaffDetailtTotalSize(pageModelDomain);
	}
	@Override
	public int selecScoreByDateTotalSize(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", pageModelDomain.getTableName());
		map.put("staffDetailId", pageModelDomain.getStaffDetailId());
		map.put("startTime", pageModelDomain.getStartTime());
		map.put("endTime", pageModelDomain.getEndTime());
		map.put("f_add_type", pageModelDomain.getF_add_type());
		return ljflScoreRecordMapper.selecScoreByDateTotalSize(map);
	}
	@Override
	public List<LjflScoreRecord> selecScoreByDateTotal(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", pageModelDomain.getTableName());
		map.put("staffDetailId", pageModelDomain.getStaffDetailId());
		map.put("startTime", pageModelDomain.getStartTime());
		map.put("endTime", pageModelDomain.getEndTime());
		if(pageModelDomain.getPageSize()==0){
			map.put("currentNum",null);
			map.put("endNum", null);
		}else{
			map.put("currentNum",pageModelDomain.getCurrentNum()+"");
			map.put("endNum", pageModelDomain.getPageSize()+"");
		}
		
		map.put("f_add_type", pageModelDomain.getF_add_type());
		return ljflScoreRecordMapper.selecScoreByDateTotal(map);
	}
	@Override
	public LjflStaffDetail selectStaffDetailById(String StaffDetailId) {
		return ljflStaffDetailMapper.selectByPrimaryKey(StaffDetailId);
	}
	@Override
	public int selecScoreDetailByDateTotalSize(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", pageModelDomain.getTableName());
		map.put("staffDetailId", pageModelDomain.getStaffDetailId());
		map.put("startTime", pageModelDomain.getStartTime());
		map.put("endTime", pageModelDomain.getEndTime());
		map.put("ljflExerciseId", pageModelDomain.getLjflExerciseId());
		return ljflScoreDetailMapper.selecScoreDetailByDateTotalSize(map);
	}
	@Override
	public List<LjflScoreDetail> selecScoreDetailByDateTotal(PageModelDomain pageModelDomain) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("tableName", pageModelDomain.getTableName());
		map.put("staffDetailId", pageModelDomain.getStaffDetailId());
		map.put("startTime", pageModelDomain.getStartTime());
		map.put("endTime", pageModelDomain.getEndTime());
		if(pageModelDomain.getPageSize()==0){
			map.put("currentNum",null);
			map.put("endNum", null);
		}else{
			map.put("currentNum",pageModelDomain.getCurrentNum()+"");
			map.put("endNum", pageModelDomain.getPageSize()+"");
		}
		
		map.put("ljflExerciseId", pageModelDomain.getLjflExerciseId());
		return ljflScoreDetailMapper.selecScoreDetailByDateTotal(map);
	}
	@Override
	public LjflType selectLjflTypeById(String getfConfirmRubbishTypeId) {
		return ljflTypeMapper.selectByPrimaryKey(getfConfirmRubbishTypeId);
	}
	@Override
	public int insert(LjflScoreRecord1 record) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tablename", record.getTablename());
		map.put("id", record.getId());
		map.put("createtime", record.getCreatetime());
		map.put("lastchangetime", record.getLastchangetime());
		map.put("status", record.getStatus());
		map.put("beendeleted", record.getBeendeleted());
		map.put("deletedtime", record.getDeletedtime());
		map.put("businesssystemid", record.getBusinesssystemid());
		map.put("tenantid", record.getTenantid());
		map.put("fAddObjectId", record.getfAddObjectId());
		map.put("fAddScore", record.getfAddScore());
		map.put("fAddScoreInTheory", record.getfAddScoreInTheory());
		map.put("fAddType", record.getfAddType());
		map.put("fScoreBalance", record.getfScoreBalance());
		map.put("fScoreTime", record.getfScoreTime());
		map.put("fStaffDetailId", record.getfStaffDetailId());
		map.put("divisiontime", record.getDivisiontime());
		map.put("fScoreTypeId", record.getfScoreTypeId());

		return ljflScoreRecordMapper.insertljflscorerecord(map);
	}
	@Override
	public List<LjflScoreRecord> selectBydate(String tablename,
			String todayTime, String staffDetailId) {
		return ljflScoreRecordMapper.selectBydate(tablename, todayTime, staffDetailId);
	}
	@Override
	public int deleteByPrimaryKey(String id) {
		return ljflScoreRecordMapper.deleteByPrimaryKey(id);
	}

}
