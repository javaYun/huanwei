package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.DriverInfo;

public interface DriverInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(DriverInfo record);

    int insertSelective(DriverInfo record);

    DriverInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DriverInfo record);

    int updateByPrimaryKey(DriverInfo record);
    
    /**	
     * 获取List<CarInfo>数据 无条件
     */
    List<DriverInfo> getDriverInfoListTotal();
    
    /**	
     * 获取List<CarInfo>数据 分页
     */
    List<DriverInfo> getDriverInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    int deleteDriverInfoByPrimaryKeys(Map<String, Object> params);
    
	List<DriverInfo> getDriverInfoListByUnit(@Param("startSize")String startSize,@Param("endSize")String endSize, @Param("unitid")String unitId);

	List<DriverInfo> getDriverInfoListTotalByUnit(@Param("unitid")String unitId);
}