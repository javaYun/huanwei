package com.sczh.platform.dao;

import com.sczh.platform.po.CloudWxLoginUser;

public interface CloudWxLoginUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(CloudWxLoginUser record);

    int insertSelective(CloudWxLoginUser record);

    CloudWxLoginUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CloudWxLoginUser record);

    int updateByPrimaryKey(CloudWxLoginUser record);
    
	 CloudWxLoginUser selectBystaffId(String id);

    
}