package com.sczh.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.WorkPoint;

public interface WorkPointMapper {
    int deleteByPrimaryKey(String id);

    int insert(WorkPoint record);

    int insertSelective(WorkPoint record);

    WorkPoint selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkPoint record);

    int updateByPrimaryKey(WorkPoint record);
    /**
     * 根据站点名称获取站点电子围栏数据
     * @param pointName
     * @return
     */
    WorkPoint selectBypointName(String pointName);
    /**	
     * 获取List<WorkPoint>数据 分页
     */
    List<WorkPoint> getWorkPointListNoPrameter();
    
    /**	
     * 获取List<WorkPoint>数据 分页
     */
    List<WorkPoint> getWorkPointList(@Param("startSize")String startSize,@Param("endSize")String endSize);
    /**
     * 
     * @param unitId
     * @return
     */
    List<WorkPoint> getWorkPointListByUnit(@Param("unitId")String unitId);
    
}