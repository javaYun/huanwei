package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflWorkPoint;

public interface LjflWorkPointMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflWorkPoint record);

    int insertSelective(LjflWorkPoint record);

    LjflWorkPoint selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflWorkPoint record);

    int updateByPrimaryKeyWithBLOBs(LjflWorkPoint record);

    int updateByPrimaryKey(LjflWorkPoint record);

	List<LjflWorkPoint> selectLjflWorkPointListByPamaters(Map<String, String> map);
}