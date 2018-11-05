package com.sczh.platform.dao;

import com.sczh.platform.po.Point;

public interface PointMapper {
    int deleteByPrimaryKey(String id);

    int insert(Point record);

    int insertSelective(Point record);

    Point selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Point record);

    int updateByPrimaryKey(Point record);
}