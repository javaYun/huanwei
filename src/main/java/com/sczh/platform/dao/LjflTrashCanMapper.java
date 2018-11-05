package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflTrashCan;
import com.sczh.platform.po.LjflTrashCanWithBLOBs;

public interface LjflTrashCanMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflTrashCanWithBLOBs record);

    int insertSelective(LjflTrashCanWithBLOBs record);

    LjflTrashCanWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflTrashCanWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LjflTrashCanWithBLOBs record);

    int updateByPrimaryKey(LjflTrashCan record);

	int selectLjflTrashCanSizeByPamater(Map<String, Object> map);

	List<LjflTrashCan> selectLjflTrashCanByPamaters(Map<String, Object> map);
}