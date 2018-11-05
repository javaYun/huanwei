package com.sczh.platform.dao;

import com.sczh.platform.po.AreaManager;

public interface AreaManagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(AreaManager record);

    int insertSelective(AreaManager record);

    AreaManager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AreaManager record);

    int updateByPrimaryKey(AreaManager record);
}