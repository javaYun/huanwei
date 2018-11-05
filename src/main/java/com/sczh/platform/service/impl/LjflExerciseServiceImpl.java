package com.sczh.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.LjflExerciseMapper;
import com.sczh.platform.po.LjflExercise;
import com.sczh.platform.service.LjflExerciseService;

@Service
public class LjflExerciseServiceImpl implements LjflExerciseService{

	@Autowired
	public LjflExerciseMapper ljflexercisemapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ljflexercisemapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LjflExercise record) {
		// TODO Auto-generated method stub
		return ljflexercisemapper.insert(record);
	}

	@Override
	public int insertSelective(LjflExercise record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LjflExercise selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ljflexercisemapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LjflExercise record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(LjflExercise record) {
		// TODO Auto-generated method stub
		return ljflexercisemapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LjflExercise> selectLjflExerciseInfoList(String startSize,
			String endSize) {
		// TODO Auto-generated method stub
		return ljflexercisemapper.selectLjflExerciseInfoList(startSize, endSize);
	}

	@Override
	public List<LjflExercise> selectLjflExerciseInfoListTotal(String name) {
		// TODO Auto-generated method stub
		return ljflexercisemapper.selectLjflExerciseInfoListTotal(name);
	}

	@Override
	public List<LjflExercise> selectLjflExerciseInfoListTotal1() {
		// TODO Auto-generated method stub
		return ljflexercisemapper.selectLjflExerciseInfoListTotal1();
	}

	
}
