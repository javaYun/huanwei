package com.sczh.platform.dao;

import java.util.List;

import com.sczh.platform.po.Tub01;

public interface Tub01Mapper {
    int deleteByPrimaryKey(String fuleId);

    int insert(Tub01 record);

    int insertSelective(Tub01 record);

    Tub01 selectByPrimaryKey(String fuleId);

    int updateByPrimaryKeySelective(Tub01 record);

    int updateByPrimaryKey(Tub01 record);
    
    /**	
     * 获取List<Tub01>数据 无条件
     */
    List<Tub01> getTub01ListTotal();
}