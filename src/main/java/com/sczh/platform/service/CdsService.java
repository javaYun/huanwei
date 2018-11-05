package com.sczh.platform.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.CarInfo;
import com.sczh.platform.po.DriverInfo;

public interface CdsService {
	
	int insertCarInfo(CarInfo record);
	
	 /**	
     * 获取List<CarInfo>数据 无条件
     */
    List<CarInfo> getCarInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
    

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    int deleteByPrimaryKeys(@Param("ids")String ids);
    /**
     * 根据id获取车辆信息
     * @param id
     * @return
     */
    CarInfo selectCarInfoByPrimaryKey(String id);
    /**
     * 根据id修改车辆信息
     * @param record
     * @return
     */
    int updateCarInfoByPrimaryKey(CarInfo record);
    
    /**	
     * 获取List<CarInfo>数据 无条件
     */
    List<CarInfo> getCarInfoListTotal();
    /**
     * 保存驾驶员信息
     * @param record
     * @return
     */
    int insertDriverInfo(DriverInfo record);
    
    /**	
     * 获取List<DriverInfo>数据 无条件
     */
    List<DriverInfo> getDriverInfoListTotal();
    
    /**	
     * 获取List<DriverInfo>数据 分页
     */
    List<DriverInfo> getDriverInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
    /**
     * 批量删除数据DriverInfo
     * @param ids
     * @return
     */
    int deleteDriverInfoByPrimaryKeys(Map<String, Object> params);
    /**
     * 根据id获取DriverInfo
     * @param id
     * @return
     */
    DriverInfo selectDriverInfoByPrimaryKey(String id);
    /**
     * 修改DriverInfo信息
     * @param record
     * @return
     */
    int updateDriverInfoByPrimaryKey(DriverInfo record);
  /**
   * 根据unitID获取carInfo的list集合
   * @param startSize
   * @param endSize
   * @param unitId
   * @return
   */
	List<CarInfo> getCarInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize, String unitId);
	/**
	 * @param unitId 单位id
	 * @return
	 */
	 List<CarInfo> getCarInfoListTotal(String unitId);

	List<DriverInfo> getDriverInfoListByUnit(@Param("startSize")String startSize,@Param("endSize")String endSize, String unitId);

	List<DriverInfo> getDriverInfoListTotalByUnit(String unitId);
}
