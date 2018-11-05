package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflScoreRecord;

public interface LjflScoreRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflScoreRecord record);

    int insertSelective(LjflScoreRecord record);

    LjflScoreRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflScoreRecord record);

    int updateByPrimaryKey(LjflScoreRecord record);
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
	List<LjflScoreRecord> selecScoreByDateTotal(Map<String, String> map);
	
	int insertljflscorerecord(Map<String, Object> map);
	
	List<LjflScoreRecord> selectBydate(@Param("tablename") String tablename,@Param("todayTime") String todayTime,@Param("staffDetailId")String staffDetailId);
	
}