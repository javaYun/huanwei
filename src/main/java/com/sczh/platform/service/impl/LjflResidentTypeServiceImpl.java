package com.sczh.platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.LjflResidentTypeMapper;
import com.sczh.platform.dao.LjflSmartCardMapper;
import com.sczh.platform.po.LjflResidentType;
import com.sczh.platform.po.LjflSmartCard;
import com.sczh.platform.service.LjflResidentTypeService;
@Service
public class LjflResidentTypeServiceImpl implements LjflResidentTypeService {
	
	@Autowired
	private LjflResidentTypeMapper  ljflResidentTypeMapper;
	@Autowired
	private LjflSmartCardMapper ljflSmartCardMapper;
	@Override
	public int selectLjflResidentTypeByParamters(Map<String, Object> map) {
		return ljflResidentTypeMapper.selectLjflResidentTypeByParamters(map);
	}
	@Override
	public List<LjflResidentType> selectLjflResidentTypeByPamaters(Map<String, Object> map) {
		return ljflResidentTypeMapper.selectLjflResidentTypeByPamaters(map);
	}
	@Override
	public int insertResidentType(LjflResidentType type) {
		return ljflResidentTypeMapper.insert(type);
	}
	@Override
	public int updateResidentType(LjflResidentType type) {
		return ljflResidentTypeMapper.updateByPrimaryKey(type);
	}
	@Override
	public LjflResidentType selectResidentTypById(String id) {
		return ljflResidentTypeMapper.selectByPrimaryKey(id);
	}
	@Override
	public int selectLjflSmartCardByParamters(Map<String, Object> map) {
		return ljflSmartCardMapper.selectLjflSmartCardByParamters(map);
	}
	@Override
	public List<LjflSmartCard> selectLjflSmartCardByPamaters(Map<String, Object> map) {
		return ljflSmartCardMapper.selectLjflSmartCardByPamaters(map);
	}
	@Override
	public int insertLjflSmartCard(LjflSmartCard card) {
		return ljflSmartCardMapper.insert(card);
	}
	@Override
	public int updateLjflSmartCard(LjflSmartCard card) {
		return ljflSmartCardMapper.updateByPrimaryKey(card);
	}
	@Override
	public LjflSmartCard selectLjflSmartCardById(String id) {
		return ljflSmartCardMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<LjflResidentType> selectLjflResidentType() {
		return ljflResidentTypeMapper.selectLjflResidentType();
	}
	@Override
	public List<LjflResidentType> selectresidenttypeList(String name) {
		return ljflResidentTypeMapper.selectresidenttypeList(name);
	}

}
