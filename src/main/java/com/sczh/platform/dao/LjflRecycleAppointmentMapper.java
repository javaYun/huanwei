package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflRecycleAppointment;

public interface LjflRecycleAppointmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflRecycleAppointment record);

    int insertSelective(LjflRecycleAppointment record);

    LjflRecycleAppointment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflRecycleAppointment record);

    int updateByPrimaryKey(LjflRecycleAppointment record);

	int selectRecycleAppointSizeByPamater(Map<String, Object> map);

	List<LjflRecycleAppointment> selectRecycleAppointByPamaters(Map<String, Object> map);
}