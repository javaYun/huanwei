package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflPrizeProvide;
import com.sczh.platform.po.LjflPrizeProvideRecord;

public interface LjflPrizeProvideRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflPrizeProvideRecord record);

    int insertSelective(LjflPrizeProvideRecord record);

    LjflPrizeProvideRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflPrizeProvideRecord record);

    int updateByPrimaryKey(LjflPrizeProvideRecord record);
    

    /**
      * 根据动态数据库查询当前的数据库条数
      * @param pageModelDomain
      * @return
      */
 	int selecScoreByDateTotalSize(Map<String, String> map);
 	/**
 	 * 根据动态表获取数据
 	 * @param map
 	 * @return
 	 */
 	List<LjflPrizeProvideRecord> selecScoreByDateTotal(Map<String, String> map);
 	/**
 	 * 根据动态数据库查询当前的数据库条数
 	 * @param pageModelDomain
 	 * @return
 	 */
 	int selecScoreByDateTotalSize1(Map<String, String> map);
 	/**
 	 * 根据动态表获取数据
 	 * @param map
 	 * @return
 	 */
 	List<LjflPrizeProvide> selecScoreByDateTotal1(Map<String, String> map);
 	
 	int insertmap(Map<String, Object> map);
 	
	 int deletebyid(@Param("id")String id,@Param("tablename") String tablename);
	 
	 LjflPrizeProvideRecord selectBycdKey(@Param("tablename") String tablename,@Param("cdkey") String cdkey);
}