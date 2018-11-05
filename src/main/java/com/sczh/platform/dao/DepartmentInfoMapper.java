package com.sczh.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.DepartmentInfo;

public interface DepartmentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DepartmentInfo record);

    int insertSelective(DepartmentInfo record);

    DepartmentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DepartmentInfo record);

    int updateByPrimaryKey(DepartmentInfo record);
    
    List<DepartmentInfo> selectDepartmentinfoList();
    
    List<DepartmentInfo> selectByUnitCode(@Param("unitcode") String unitcode);

    DepartmentInfo selectByDepartmentCode(String departmentcode);
}