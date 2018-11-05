package com.sczh.platform.dao;

import java.util.List;

import com.sczh.platform.po.AreaCommunity;

public interface AreaCommunityMapper {
    int deleteByPrimaryKey(String id);

    int insert(AreaCommunity record);

    int insertSelective(AreaCommunity record);

    AreaCommunity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AreaCommunity record);

    int updateByPrimaryKey(AreaCommunity record);
    
	List<AreaCommunity> selectareaCommunities();
	
	List<AreaCommunity> selectAreaCommunitsByName(String areaName);

}