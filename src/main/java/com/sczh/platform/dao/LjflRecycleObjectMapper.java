package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflRecycleObject;

public interface LjflRecycleObjectMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflRecycleObject record);

    int insertSelective(LjflRecycleObject record);

    LjflRecycleObject selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflRecycleObject record);

    int updateByPrimaryKeyWithBLOBs(LjflRecycleObject record);

    int updateByPrimaryKey(LjflRecycleObject record);
    
    List<LjflRecycleObject> selectObjectByTenantId(String tenantId);
    
	List<LjflRecycleObject> selectLjflObjectListByParamters(Map<String, String> map);

	int selectRecycleObjectByPamaterSize(Map<String, String> map);
	
	LjflRecycleObject selectRecycleObject(String id);
    
	LjflRecycleObject selectRecycleObjectbyname(String name);
}