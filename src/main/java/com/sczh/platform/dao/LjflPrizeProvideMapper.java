package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflPrizeProvide;
import com.sczh.platform.po.LjflPrizeProvideRecord;

public interface LjflPrizeProvideMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflPrizeProvide record);

    int insertSelective(LjflPrizeProvide record);

    LjflPrizeProvide selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflPrizeProvide record);

    int updateByPrimaryKey(LjflPrizeProvide record);

    List<LjflPrizeProvide> selectPrizeProvideInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize,@Param("name")String name,@Param("code")String code,@Param("starttime")String starttime,@Param("endtime")String endtime);
    
    List<LjflPrizeProvide> selectPrizeProvideInfoListTotal(@Param("name")String name,@Param("code")String code,@Param("starttime")String starttime,@Param("endtime")String endtime);
    List<LjflPrizeProvide> selectPrizeProvideInfoListTotal1();
    
    List<LjflPrizeProvide> selecScoreByDateTotal1(Map<String, String> map);
    
    int selecScoreByDateTotalSize1(Map<String, String> map);
    
    LjflPrizeProvide selectByPrizeId(String prizeid);
    
}