package com.sczh.platform.dao;

import com.sczh.platform.po.WorkPlan;

public interface WorkPlanMapper {
    int deleteByPrimaryKey(String id);

    int insert(WorkPlan record);

    int insertSelective(WorkPlan record);

    WorkPlan selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkPlan record);

    int updateByPrimaryKey(WorkPlan record);
}