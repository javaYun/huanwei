package com.sczh.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.PersonInfo;

public interface PersonInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonInfo record);

    int insertSelective(PersonInfo record);

    PersonInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonInfo record);

    int updateByPrimaryKey(PersonInfo record);
    
    List<PersonInfo> selectPersonInfoList();
	
	List<PersonInfo> selectPersonInfoList1(@Param("unitname")String unitname,@Param("departmentname")String departmentname,@Param("startSize")String startSize,@Param("endSize")String endSize);
	
	List<PersonInfo> selectPersonInfoList2(@Param("startSize")String startSize,@Param("endSize")String endSize);
    
    List<PersonInfo> selectPersonInfoListTotal(@Param("unitname")String unitname,@Param("departmentname")String departmentname);
	
    PersonInfo selectByUserId(String userid);
    
    List<PersonInfo> selectByUnit(@Param("unitname") String unitname);
	
	List<PersonInfo> selectByUnitandDepartment(@Param("unitname")String unitname,@Param("departmentname")String departmentname);
    
}