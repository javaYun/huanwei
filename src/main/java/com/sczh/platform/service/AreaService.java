package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.AreaCommunity;
import com.sczh.platform.po.AreaGov;
import com.sczh.platform.po.AreaManager;
import com.sczh.platform.po.AreaRelation;
import com.sczh.platform.po.AreaRoleManager;

/**
 * 区域管理service
 * @author yunluo
 *
 */
public interface AreaService {

	int saveAreaGov(AreaGov areaGov);

	int areaRelation(AreaRelation areaRelation);

	List<AreaRelation> selectAreaRelationAndIsArea(AreaRelation record);

	int saveAreaCommunity(AreaCommunity areaCommunity);

	void saveAreaManager(AreaManager areaManager);
	
	AreaRelation selectAreaRelation(String name);

	List<AreaRelation> selectAreaRelations(@Param("pId")String pId);
	
	List<AreaGov> selectaraegovinfos(String areadescription);
	
	AreaRelation selectById(String id);
	
	List<AreaCommunity> selectareaCommunities();
	
	AreaCommunity selectByPrimaryKey(String id);
	AreaGov selectAreaGovById(String id);
	
	List<AreaRoleManager> selectAreaRoleManager();
	
	int insert(AreaRoleManager areaRoleManager);
	
	AreaRoleManager selectAreaRole(String id);
	
	int updateAreaRole(AreaRoleManager areaRoleManager);
	
	int deleteAreaRole(String id);
	
	int deleteAreaGov(String id);
	
	int deleteAreaRelation (String id);
	
	int deleteAreaCommunity(String id);
	
	int updateAreaGov(AreaGov areagov);
	
	int updateAreaRelation(AreaRelation areaRelation);
	
	int updateAreaCommunity(AreaCommunity areaCommunity);

	List<AreaCommunity> selectAreaCommunitsByName(String areaName);
	
}
