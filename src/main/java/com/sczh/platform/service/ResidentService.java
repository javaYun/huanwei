package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflStaffDetail;

public interface ResidentService {

	List<LjflStaffDetail> selectStaffDetailData(@Param("name") String name,
			@Param("housesId") String housesId,
			@Param("communityId") String communityId,
			@Param("phone") String phone, @Param("startSize") String startSize,
			@Param("endSize") String endSize);
	
	List<LjflStaffDetail> selectStaffDetailDataTotal(@Param("name") String name,
			@Param("housesId") String housesId,
			@Param("communityId") String communityId,
			@Param("phone") String phone);
	
}
