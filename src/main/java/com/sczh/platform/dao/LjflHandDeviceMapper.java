package com.sczh.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflHandDevice;

public interface LjflHandDeviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflHandDevice record);

    int insertSelective(LjflHandDevice record);

    LjflHandDevice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflHandDevice record);

    int updateByPrimaryKey(LjflHandDevice record);
    
    List<LjflHandDevice> selecthanddeviceinfos(@Param("startSize")String startSize,@Param("endSize")String endSize,@Param("code")String code,@Param("name")String name);
	 
    List<LjflHandDevice> selecthanddeviceinfostotal(@Param("code")String code,@Param("name")String name);
    
    LjflHandDevice selectByHandDevice(String code);
}