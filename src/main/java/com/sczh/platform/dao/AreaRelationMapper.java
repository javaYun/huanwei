package com.sczh.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.AreaRelation;

public interface AreaRelationMapper {
    int deleteByPrimaryKey(String arearelationid);

    int insert(AreaRelation record);

    int insertSelective(AreaRelation record);

    AreaRelation selectByPrimaryKey(String arearelationid);

    int updateByPrimaryKeySelective(AreaRelation record);

    int updateByPrimaryKey(AreaRelation record);
    
    /**
     * 查询所有数据
     * @return
     */
	List<AreaRelation> selectAreaRelationAndIsArea(AreaRelation record);
	
	AreaRelation selectAreaRelation(String name);
	
	AreaRelation selectById(String id);
	
	List<AreaRelation> selectAreaRelations(@Param("pId")String pId);
	
	 int deleteAreaRelation(String id);
}