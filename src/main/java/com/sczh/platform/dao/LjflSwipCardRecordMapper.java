package com.sczh.platform.dao;

import java.util.List;

import com.sczh.platform.po.LjflSwipCardRecord;
import com.sczh.platform.po.Model.PageModelDomain;

public interface LjflSwipCardRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflSwipCardRecord record);

    int insertSelective(LjflSwipCardRecord record);

    LjflSwipCardRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflSwipCardRecord record);

    int updateByPrimaryKey(LjflSwipCardRecord record);

	int selecSwipCardRecordTotalSize(PageModelDomain pageModelDomain);

	List<LjflSwipCardRecord> selecSwipCardRecordByDateTotal(PageModelDomain pageModelDomain);
}