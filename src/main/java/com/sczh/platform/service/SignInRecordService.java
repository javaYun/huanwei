package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.SignInRecord;

public interface SignInRecordService {

	 int deleteByPrimaryKey(String id);

	    int insert(SignInRecord record);

	    int insertSelective(SignInRecord record);

	    SignInRecord selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(SignInRecord record);

	    int updateByPrimaryKey(SignInRecord record);
	
	    List<SignInRecord> selectListSignInList(@Param("startSize")String startSize,@Param("endSize")String endSize,@Param("time")String time);
	    
	    List<SignInRecord> selectListSignInListTotal(@Param("time")String time);
	    
}
