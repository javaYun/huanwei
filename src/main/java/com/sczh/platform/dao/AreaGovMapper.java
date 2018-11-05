package com.sczh.platform.dao;

import java.util.List;

import com.sczh.platform.po.AreaGov;

public interface AreaGovMapper {
    int deleteByPrimaryKey(String id);

    int insert(AreaGov record);

    int insertSelective(AreaGov record);

    AreaGov selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AreaGov record);

    int updateByPrimaryKey(AreaGov record);
    
    List<AreaGov> selectaraegovinfos(String areadescription);
    
}