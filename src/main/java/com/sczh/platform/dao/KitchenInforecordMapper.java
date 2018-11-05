package com.sczh.platform.dao;

import java.util.List;



import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.KitchenInforecord;

public interface KitchenInforecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(KitchenInforecord record);

    int insertSelective(KitchenInforecord record);

    KitchenInforecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(KitchenInforecord record);

    int updateByPrimaryKey(KitchenInforecord record);
    
    List<KitchenInforecord> kitchenrecordinfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
 
    List<KitchenInforecord> kitchenrecordinfoListTotal();
}