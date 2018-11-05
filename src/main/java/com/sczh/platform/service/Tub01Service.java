package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.Et100;
import com.sczh.platform.po.KitchenInfo;
import com.sczh.platform.po.KitchenInforecord;
import com.sczh.platform.po.Point;
import com.sczh.platform.po.Tub01;
import com.sczh.platform.po.WorkPoint;
import com.sun.jmx.snmp.Timestamp;

public interface Tub01Service {//油耗service

	  int deleteByPrimaryKey(String fuleId);

	    int insert(Tub01 record);

	    int insertSelective(Tub01 record);

	    Tub01 selectByPrimaryKey(String fuleId);

	    int updateByPrimaryKeySelective(Tub01 record);

	    int updateByPrimaryKey(Tub01 record);
	    /**
	     * 根据key获取数据
	     * @param key
	     * @param startNum
	     * @param endNum
	     * @return
	     */
	    List<String> getList(String key,int startNum, int endNum);
	    /**	
	     * 获取List<Tub01>数据 无条件
	     */
	    List<Tub01> getTub01ListTotal();
	    
	    /**
	     * 插入数据到et100
	     */
	    int insertEt100Recode(Et100 record);
	    
	    /**	
	     * 获取List<Et100>数据 无条件
	     */
	    List<Et100> getEt100ListTotal();
	    /**	
	     * 获取List<Et100>数据 无条件
	     */
	    List<Et100> getEt100ListTotalByTimeAndSourceDId(Et100 record);
	    /**
	     * 保存坐标数据信息
	     * @param record
	     * @return
	     */
	    int insertPoint(Point record);

	    /**
	     * 获取坐标信息数据
	     * @param id
	     * @return
	     */
	    Point selectPointByPrimaryKey(String id);
	    /**
	     * 修改point坐标信息
	     * @param record
	     * @return
	     */
	    int updatePointByPrimaryKeySelective(Point record);
	    
	    /**
	     * 设计垃圾站点经纬度范围
	     * @param point
	     * @return
	     */
	    int insertWorkPointData(WorkPoint point);
	    /**
	     * 根据ID 获取workPoint
	     * @param id
	     * @return
	     */
	    WorkPoint selectWorkPointByPrimaryKey(String id);
	    
	    WorkPoint selectBypointName(String pointName);
	    /**	
	     * 获取List<WorkPoint>数据 分页
	     */
	    List<WorkPoint> getWorkPointList(@Param("startSize")String startSize,@Param("endSize")String endSize);
	    /**
	     * 根据时间范围获取该周期内的GPS数据
	     * @param startTime
	     * @param endTime
	     * @return
	     */
	    List<Et100> getEt100ListByTime(@Param("startTime")String startTime,@Param("endTime")String endTime);
	    /**	
	     * 获取List<WorkPoint>数据 分页
	     */
	    List<WorkPoint> getWorkPointList();
	    /**	
	     * 获取List<Et100>数据根据设备号   sourceDeviceId
	     */
	    List<Et100> getEt100ListTotalBySourcedeviceid(String sourcedeviceid);
	    /**
	     * 根据id获取Et100
	     * @param id
	     * @return
	     */
	    Et100 getEt00ByPrimaryKey(String id);

		List<WorkPoint> getWorkPointListByUnit(String unitId);

		KitchenInforecord getKitchenInforecordId(String id);

		void insertKitchenInforecord(KitchenInforecord record);

		KitchenInfo getKitchenInfoByCarNo(String cardno);

		 List<KitchenInforecord> kitchenrecordinfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
		 
		    List<KitchenInforecord> kitchenrecordinfoListTotal();
		
		void updatekitchenInfo(KitchenInfo kitchenInfo);
		 public abstract List<Et100> getEt100sBetweenTimeAndSouceDeviceId(String paramString1, String paramString2, String paramString3);

}
