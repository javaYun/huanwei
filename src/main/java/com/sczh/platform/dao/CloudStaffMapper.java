package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.CloudStaff;

public interface CloudStaffMapper {
    int deleteByPrimaryKey(String id);

    int insert(CloudStaff record);

    int insertSelective(CloudStaff record);

    CloudStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CloudStaff record);

    int updateByPrimaryKey(CloudStaff record);

	List<CloudStaff> selectCloudStaffData(Map<String, String> map);

	int selectCloudStaffSize(Map<String, String> map);
}