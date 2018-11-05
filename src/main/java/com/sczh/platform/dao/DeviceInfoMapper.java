package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.DeviceInfo;

public interface DeviceInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(DeviceInfo record);

    int insertSelective(DeviceInfo record);

    DeviceInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DeviceInfo record);

    int updateByPrimaryKey(DeviceInfo record);
    
    /**	
     * 获取List<DeviceInfo>数据 分页
     */
    List<DeviceInfo> getDeviceInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
    /**	
     * 获取List<DeviceInfo>数据 无条件
     */
    List<DeviceInfo> getDeviceInfoListTotal();
    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    int deleteDeviceInfoByPrimaryKeys(Map<String, Object> params);
    
	List<DeviceInfo> getDeviceInfoListByUnit(@Param("startSize")String startSize,@Param("endSize")String endSize, @Param("unitId")String unitId);

	List<DeviceInfo> getDeviceInfoListTotalByUnit(@Param("unitId")String unitId);
	
	List<DeviceInfo> getDeviceInfoListTotalByUnitAndDeviceType(@Param("unitId")String unitId,@Param("devicetype")String devicetype);
	
	List<DeviceInfo> getDeviceInfoListTotalByDeviceType(String devicetype);
}