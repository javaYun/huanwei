package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflResponseTimeConfig;

public interface LjflResponseTimeConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflResponseTimeConfig record);

    int insertSelective(LjflResponseTimeConfig record);

    LjflResponseTimeConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflResponseTimeConfig record);

    int updateByPrimaryKey(LjflResponseTimeConfig record);

	int selectResponseTimeConfigSizeByPamater(Map<String, Object> map);

	List<LjflResponseTimeConfig> selectResponseTimeConfigByPamaters(Map<String, Object> map);
}