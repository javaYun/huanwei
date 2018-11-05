package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflSmartCard;

public interface LjflSmartCardMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflSmartCard record);

    int insertSelective(LjflSmartCard record);

    LjflSmartCard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflSmartCard record);

    int updateByPrimaryKeyWithBLOBs(LjflSmartCard record);

    int updateByPrimaryKey(LjflSmartCard record);
    
    LjflSmartCard selectByCcode(String ccode);
    
    LjflSmartCard selectByfBindId(String fBindId);
	int selectLjflSmartCardByParamters(Map<String, Object> map);

	List<LjflSmartCard> selectLjflSmartCardByPamaters(Map<String, Object> map);
	
	List<LjflSmartCard> selectljflsmartcard();
}