package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.UnitInfo;

public interface UnitService {

	int deleteByPrimaryKey(String unitcode);

    int insert(UnitInfo record);

    int insertSelective(UnitInfo record);

    UnitInfo selectByPrimaryKey(String unitcode);

    int updateByPrimaryKeySelective(UnitInfo record);

    int updateByPrimaryKey(UnitInfo record);
    
    List<UnitInfo> selectUnitInfoList();

	List<UnitInfo> selectByAreaId(@Param("areaId")String areaId);

	List<UnitInfo> selectByArea(@Param("areaid")String areaId);
}
