package com.sczh.platform.dao;

import com.sczh.platform.po.LjflType;
import java.util.List;
import java.util.Map;

public interface LjflTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflType record);

    int insertSelective(LjflType record);

    LjflType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflType record);

    int updateByPrimaryKeyWithBLOBs(LjflType record);

    int updateByPrimaryKey(LjflType record);
    
    LjflType selectBycode(String code);
    
    List<LjflType> selectLjflTypeListByParamters(Map<String, String> map);
    
    LjflType selectByname(String name);
    
}