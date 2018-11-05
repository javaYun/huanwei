package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.UnitInfo;

public interface UnitInfoMapper {
   
	int deleteByPrimaryKey(String unitcode);

    int insert(UnitInfo record);

    int insertSelective(UnitInfo record);

    UnitInfo selectByPrimaryKey(String unitcode);

    int updateByPrimaryKeySelective(UnitInfo record);

    int updateByPrimaryKey(UnitInfo record);
    
    List<UnitInfo> selectUnitInfoList();

    List<UnitInfo> selectByAreaId(@Param("areaId")String areaId);

	List<UnitInfo> selectUnitListByAreaId(Map<String, String> map);
    
	List<UnitInfo> selectByArea(@Param("areaid")String areaId);
}