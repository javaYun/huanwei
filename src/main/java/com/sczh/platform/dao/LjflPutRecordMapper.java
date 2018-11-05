package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflPutRecord;

public interface LjflPutRecordMapper {
	int deleteByPrimaryKey(String id);

	int insert(@Param("putrecord")LjflPutRecord record,@Param("tablename")String tablename);

	int insertSelective(LjflPutRecord record);

	LjflPutRecord selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(LjflPutRecord record);

	int updateByPrimaryKey(LjflPutRecord record);

	int selecPutRecordByDateTotalSize(Map<String, String> map);

	List<LjflPutRecord> selecPutRecordByDateTotal(Map<String, String> map);
	
	int insert(Map<String, String> map);
}