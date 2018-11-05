package com.sczh.platform.service;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflResidentType;
import com.sczh.platform.po.LjflSmartCard;

public interface LjflResidentTypeService {

	int selectLjflResidentTypeByParamters(Map<String, Object> map);

	List<LjflResidentType> selectLjflResidentTypeByPamaters(Map<String, Object> map);

	int insertResidentType(LjflResidentType type);

	int updateResidentType(LjflResidentType type);

	LjflResidentType selectResidentTypById(String id);

	int selectLjflSmartCardByParamters(Map<String, Object> map);

	List<LjflSmartCard> selectLjflSmartCardByPamaters(Map<String, Object> map);

	int insertLjflSmartCard(LjflSmartCard card);

	int updateLjflSmartCard(LjflSmartCard card);

	LjflSmartCard selectLjflSmartCardById(String id);
	
	List<LjflResidentType> selectLjflResidentType();

	List<LjflResidentType> selectresidenttypeList(String name);

	
}
