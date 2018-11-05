package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflRecycleRecord;

public interface LjflRecycleRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflRecycleRecord record);

    int insertSelective(LjflRecycleRecord record);

    LjflRecycleRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflRecycleRecord record);

    int updateByPrimaryKey(LjflRecycleRecord record);
    
    int insertRecycleRecoed(Map<String, String> map);
    

	int selecRecycycleRecordByDateTotalSize(Map<String, String> map);

	List<LjflRecycleRecord> selecRecycleRecordByDateTotal(Map<String, String> map);
}