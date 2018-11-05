package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.DepartmentInfo;

public interface DepartmentService {

	int deleteByPrimaryKey(String departmentcode);

	int insert(DepartmentInfo record);

	int insertSelective(DepartmentInfo record);

	DepartmentInfo selectByPrimaryKey(String departmentcode);

	int updateByPrimaryKeySelective(DepartmentInfo record);

	int updateByPrimaryKey(DepartmentInfo record);
	
    List<DepartmentInfo> selectDepartmentinfoList();

    List<DepartmentInfo> selectByUnitCode(@Param("unitcode") String unitcode);
    
    DepartmentInfo selectByDepartmentCode(String departmentcode);

}
