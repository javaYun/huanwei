package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.AreaRoleManager;

public interface AreaRoleManagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(AreaRoleManager record);

    int insertSelective(AreaRoleManager record);

    AreaRoleManager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AreaRoleManager record);

    int updateByPrimaryKey(AreaRoleManager record);
    /**
     * 求根据判断条件求size
     * @param map
     * @return
     */

	int selectAreaRoleManagerByUnitId(Map<String, String> map);

	List<AreaRoleManager> selectAreaRoleManagerListByUserId(Map<String, String> map);

	List<AreaRoleManager> selectAreaRoleManager();
}