package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.DailyTaskInfo;
import com.sczh.platform.po.LjflClean;

public interface LjflcleanService {

	int deleteByPrimaryKey(Integer id);

    int insert(LjflClean record);

    int insertSelective(LjflClean record);

    LjflClean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LjflClean record);

    int updateByPrimaryKey(LjflClean record);
    
    int deleteLjflClean();
    
    List<LjflClean> selectLjflCleanInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize,@Param("time")String time);
        
    List<LjflClean> selectLjflCleanInfoListTotal(@Param("time")String time);
    
}
