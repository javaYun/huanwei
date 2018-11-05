package com.sczh.platform.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.Et100;

public interface Et100Mapper {
    int deleteByPrimaryKey(String id);

    int insert(Et100 record);

    int insertSelective(Et100 record);

    Et100 selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Et100 record);

    int updateByPrimaryKey(Et100 record);
    /**	
     * 获取List<Et100>数据 无条件
     */
    List<Et100> getEt100ListTotal();
    /**	
     * 获取List<Et100>数据 无条件
     */	
    List<Et100> getEt100ListTotalByTimeAndSourceDId(Et100 record);
    /**
     * 根据时间范围获取该周期内的GPS数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<HashMap<String, Object>> getEt100ListByTime(@Param("startTime")String startTime,@Param("endTime")String endTime);
    
    /**	
     * 获取List<Et100>数据根据设备号   sourceDeviceId
     */
    List<Et100> getEt100ListTotalBySourcedeviceid(String sourcedeviceid);
    public abstract List<HashMap<String, Object>> getEt100ListByTimeAndsourceDeviceID(@Param("startTime") String paramString1, @Param("endTime") String paramString2, @Param("sourcedeviceid") String paramString3);  
}