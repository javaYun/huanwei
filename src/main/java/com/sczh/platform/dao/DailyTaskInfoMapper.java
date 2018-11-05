package com.sczh.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.DailyTaskInfo;

public interface DailyTaskInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DailyTaskInfo record);

    int insertSelective(DailyTaskInfo record);

    DailyTaskInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DailyTaskInfo record);

    int updateByPrimaryKey(DailyTaskInfo record);
    
    List<DailyTaskInfo> selectListDailyTaskList(@Param("startSize")String startSize,@Param("endSize")String endSize);
    
    List<DailyTaskInfo> selectListDailyTaskListTotal();
}