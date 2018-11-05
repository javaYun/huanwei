package com.sczh.platform.dao;

import java.util.List;

import com.sczh.platform.po.LjflCollectRecord;
import com.sczh.platform.po.Model.PageModelDomain;

public interface LjflCollectRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflCollectRecord record);

    int insertSelective(LjflCollectRecord record);

    LjflCollectRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflCollectRecord record);

    int updateByPrimaryKey(LjflCollectRecord record);

	int selecCollectRecordTotalSize(PageModelDomain pageModelDomain);

	List<LjflCollectRecord> selecCollectRecordByDateTotal(PageModelDomain pageModelDomain);
}