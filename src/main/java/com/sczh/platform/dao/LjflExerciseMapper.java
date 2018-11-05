package com.sczh.platform.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflExercise;

public interface LjflExerciseMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflExercise record);

    int insertSelective(LjflExercise record);

    LjflExercise selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflExercise record);

    int updateByPrimaryKey(LjflExercise record);
    
    List<LjflExercise> selectLjflExerciseInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
    
    List<LjflExercise> selectLjflExerciseInfoListTotal(@Param("name") String name);
    List<LjflExercise> selectLjflExerciseInfoListTotal1();
}