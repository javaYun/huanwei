package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflStaffStatistic;

public interface LjflStaffStatisticMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflStaffStatistic record);

    int insertSelective(LjflStaffStatistic record);

    LjflStaffStatistic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflStaffStatistic record);

    int updateByPrimaryKey(LjflStaffStatistic record);

	List<LjflStaffStatistic> selectStaffStaticListByPamaters(Map<String, String> map);

	List<LjflStaffStatistic> selectStaffStaticListByPamatersNoGroupBy(Map<String, String> map);
}