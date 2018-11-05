package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.AreaCommunityMapper;
import com.sczh.platform.dao.AreaGovMapper;
import com.sczh.platform.dao.AreaManagerMapper;
import com.sczh.platform.dao.AreaRelationMapper;
import com.sczh.platform.dao.AreaRoleManagerMapper;
import com.sczh.platform.po.AreaCommunity;
import com.sczh.platform.po.AreaGov;
import com.sczh.platform.po.AreaManager;
import com.sczh.platform.po.AreaRelation;
import com.sczh.platform.po.AreaRoleManager;
import com.sczh.platform.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaGovMapper areaGovMapper;
	@Autowired
	private AreaRelationMapper areaRelationMapper;
	@Autowired
	private AreaCommunityMapper areaCommunityMapper;
	@Autowired
	private AreaManagerMapper areaManagerMapper;
	
	@Autowired
	private AreaRoleManagerMapper areaRoleManagerMapper;
	
	@Override
	public int saveAreaGov(AreaGov areaGov) {
		return areaGovMapper.insert(areaGov);
	}
	@Override
	public int areaRelation(AreaRelation areaRelation) {
		return areaRelationMapper.insert(areaRelation);		
	}
	@Override
	public List<AreaRelation> selectAreaRelationAndIsArea(AreaRelation record) {
		return areaRelationMapper.selectAreaRelationAndIsArea(record);
	}
	@Override
	public int saveAreaCommunity(AreaCommunity areaCommunity) {
		return  areaCommunityMapper.insert(areaCommunity);		
	}
	@Override
	public void saveAreaManager(AreaManager areaManager) {
		areaManagerMapper.insert(areaManager);
	}
	@Override
	public AreaRelation selectAreaRelation(String name) {
		return areaRelationMapper.selectAreaRelation(name);
	}
	@Override
	public List<AreaRelation> selectAreaRelations(String pId) {
		return areaRelationMapper.selectAreaRelations(pId);
	}
	@Override
	public List<AreaGov> selectaraegovinfos(String areadescription) {
		return areaGovMapper.selectaraegovinfos(areadescription);
	}
	@Override
	public AreaRelation selectById(String id) {
		return areaRelationMapper.selectById(id);
	}
	@Override
	public List<AreaCommunity> selectareaCommunities() {
		return areaCommunityMapper.selectareaCommunities();
	}
	@Override
	public AreaCommunity selectByPrimaryKey(String id) {
		return areaCommunityMapper.selectByPrimaryKey(id);
	}
	@Override
	public AreaGov selectAreaGovById(String id) {
		return areaGovMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<AreaRoleManager> selectAreaRoleManager() {
		return areaRoleManagerMapper.selectAreaRoleManager();
	}
	@Override
	public int insert(AreaRoleManager areaRoleManager) {
		return areaRoleManagerMapper.insert(areaRoleManager);
	}
	@Override
	public AreaRoleManager selectAreaRole(String id) {
		return areaRoleManagerMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateAreaRole(AreaRoleManager areaRoleManager) {
		return areaRoleManagerMapper.updateByPrimaryKey(areaRoleManager);
	}
	@Override
	public int deleteAreaRole(String id) {
		return areaRoleManagerMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int deleteAreaGov(String id) {
		return areaGovMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int deleteAreaRelation(String id) {
		return areaRelationMapper.deleteAreaRelation(id);
	}
	@Override
	public int deleteAreaCommunity(String id) {
		return areaCommunityMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int updateAreaGov(AreaGov areagov) {
		return areaGovMapper.updateByPrimaryKey(areagov);
	}
	@Override
	public int updateAreaRelation(AreaRelation areaRelation) {
		return areaRelationMapper.updateByPrimaryKey(areaRelation);
	}
	@Override
	public int updateAreaCommunity(AreaCommunity areaCommunity) {
		return areaCommunityMapper.updateByPrimaryKey(areaCommunity);
	}
	@Override
	public List<AreaCommunity> selectAreaCommunitsByName(String areaName) {
		return areaCommunityMapper.selectAreaCommunitsByName(areaName);
	}
	
}
