package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;
import com.sczh.platform.po.LjflRecycleType;

public interface LjflRecycleTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflRecycleType record);

    int insertSelective(LjflRecycleType record);

    LjflRecycleType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflRecycleType record);

    int updateByPrimaryKeyWithBLOBs(LjflRecycleType record);

    int updateByPrimaryKey(LjflRecycleType record);
   
    List<LjflRecycleType> selectByTenantId(String tenantId);
    

	List<LjflRecycleType> selectLjflTypeListByParamters(Map<String, String> map);

	int selectRecycleTypeByPamaterSize(Map<String, String> map);

	List<LjflRecycleType> selectRecycleTypeByPamaters(Map<String, String> map);
	
	LjflRecycleType selectrecycletypebyname(String name);
}