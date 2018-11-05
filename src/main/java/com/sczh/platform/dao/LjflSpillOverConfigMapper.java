package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflSpillOverConfig;

public interface LjflSpillOverConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflSpillOverConfig record);

    int insertSelective(LjflSpillOverConfig record);

    LjflSpillOverConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflSpillOverConfig record);

    int updateByPrimaryKey(LjflSpillOverConfig record);

	int selectLjflSpliOverConfigSizeByPamater(Map<String, Object> map);

	List<LjflSpillOverConfig> selectLjflSpillOverConfigByPamaters(Map<String, Object> map);
}