package com.sczh.platform.dao;

import java.util.List;

import com.sczh.platform.po.LjflScheduleJobConfig;

public interface LjflScheduleJobConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflScheduleJobConfig record);

    int insertSelective(LjflScheduleJobConfig record);

    LjflScheduleJobConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflScheduleJobConfig record);

    int updateByPrimaryKey(LjflScheduleJobConfig record);
    
    LjflScheduleJobConfig selectByTenantCode(String tenantCode);
}