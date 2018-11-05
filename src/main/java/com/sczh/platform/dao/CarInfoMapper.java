package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.CarInfo;

public interface CarInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(CarInfo record);

    int insertSelective(CarInfo record);

    CarInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CarInfo record);

    int updateByPrimaryKey(CarInfo record);
    /**	
     * 获取List<CarInfo>数据 无条件
     */
    List<CarInfo> getCarInfoListTotal();
    
    /**	
     * 获取List<CarInfo>数据 分页
     */
    List<CarInfo> getCarInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    int deleteByPrimaryKeys(Map<String, Object> params);
    
    /**
     * 根据unitID获取carInfo的list集合
     * @param startSize
     * @param endSize
     * @param unitId
     * @return
     */
  	List<CarInfo> getCarInfoListByUnit(@Param("startSize")String startSize,@Param("endSize")String endSize,@Param("unitid") String unitid);
  	/**
	 * @param unitId 单位id
	 * @return
	 */
  	List<CarInfo> getCarInfoListTotalByUnit(@Param("unitid")String unitid);


}