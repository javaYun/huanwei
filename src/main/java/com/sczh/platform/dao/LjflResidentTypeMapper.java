package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflResidentType;

public interface LjflResidentTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflResidentType record);

    int insertSelective(LjflResidentType record);

    LjflResidentType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflResidentType record);

    int updateByPrimaryKey(LjflResidentType record);

	int selectLjflResidentTypeByParamters(Map<String, Object> map);

	List<LjflResidentType> selectLjflResidentTypeByPamaters(Map<String, Object> map);
	List<LjflResidentType> selectLjflResidentType();

	List<LjflResidentType> selectresidenttypeList(String name);
}