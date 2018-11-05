package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflDispatchTask;

public interface LjflDispatchTaskMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflDispatchTask record);

    int insertSelective(LjflDispatchTask record);

    LjflDispatchTask selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflDispatchTask record);

    int updateByPrimaryKeyWithBLOBs(LjflDispatchTask record);

    int updateByPrimaryKey(LjflDispatchTask record);

	int selectDispatchTaskSizeByPamater(Map<String, String> map);

	List<LjflDispatchTask> selectDispatchTaskByPamaters(Map<String, String> map);

	int selecDispatchTaskHistorySize(Map<String, String> map);

	List<LjflDispatchTask> selecDispatchTaskHistoryTotal(Map<String, String> map);
}