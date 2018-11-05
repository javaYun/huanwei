package com.sczh.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflPrize;

public interface LjflPrizeMapper {
	int deleteByPrimaryKey(String id);

	int insert(LjflPrize record);

	int insertSelective(LjflPrize record);

	LjflPrize selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(LjflPrize record);

	int updateByPrimaryKey(LjflPrize record);

	List<LjflPrize> selectByBeenDeleted();

	List<LjflPrize> selectPrizeInfoList(@Param("startSize") String startSize,
			@Param("endSize") String endSize);

	List<LjflPrize> selectPrizeInfoListTotal();

	List<LjflPrize> selectPrizeInfoList1(@Param("startSize") String startSize,
			@Param("endSize") String endSize,@Param("code") String code,
			@Param("name") String name);

	List<LjflPrize> selectPrizeInfoListTotal1(@Param("code") String code,
			@Param("name") String name);
	
	List<LjflPrize> selectljflprizebyname(String name);
	
}