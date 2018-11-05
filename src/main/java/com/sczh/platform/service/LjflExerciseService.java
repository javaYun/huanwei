package com.sczh.platform.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.sczh.platform.po.LjflExercise;

public interface LjflExerciseService {

	int deleteByPrimaryKey(String id);

	int insert(LjflExercise record);

	int insertSelective(LjflExercise record);

	LjflExercise selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(LjflExercise record);

	int updateByPrimaryKey(LjflExercise record);

	List<LjflExercise> selectLjflExerciseInfoList(
			@Param("startSize") String startSize,
			@Param("endSize") String endSize);

	List<LjflExercise> selectLjflExerciseInfoListTotal(@Param("name") String name);
	List<LjflExercise> selectLjflExerciseInfoListTotal1();
}
