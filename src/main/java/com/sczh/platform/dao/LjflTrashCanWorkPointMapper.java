package com.sczh.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflTrashCanWorkPoint;

public interface LjflTrashCanWorkPointMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LjflTrashCanWorkPoint record);

    int insertSelective(LjflTrashCanWorkPoint record);

    LjflTrashCanWorkPoint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LjflTrashCanWorkPoint record);

    int updateByPrimaryKey(LjflTrashCanWorkPoint record);
    
    List<LjflTrashCanWorkPoint> selectLjflTrashCanWorkPointList();
    
    LjflTrashCanWorkPoint selectLjflTrashCanWorkPointList1(String trashcanid);
    
    
    List<LjflTrashCanWorkPoint> selectLjflTrashCanWorkPointInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
    
    List<LjflTrashCanWorkPoint> selectLjflTrashCanWorkPointInfoListTotal();
}