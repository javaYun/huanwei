package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflScoreDetail;

public interface LjflScoreDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflScoreDetail record);

    int insertSelective(LjflScoreDetail record);

    LjflScoreDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflScoreDetail record);

    int updateByPrimaryKey(LjflScoreDetail record);
    
    /**
   * 分页查询现场活动记录
   * @param map
   * @return
   */
	int selecScoreDetailByDateTotalSize(Map<String, String> map);

	List<LjflScoreDetail> selecScoreDetailByDateTotal(Map<String, String> map);
}