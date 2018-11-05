package com.sczh.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflAccountModify;
import com.sczh.platform.po.LjflStaffDetail;

public interface LjflAccountModifyMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflAccountModify record);

    int insertSelective(LjflAccountModify record);

    LjflAccountModify selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflAccountModify record);

    int updateByPrimaryKey(LjflAccountModify record);
    
    List<LjflAccountModify> selectAccoountModityInfoList(@Param("modifyusername")String modifyusername);
    List<LjflAccountModify> selectAccoountModityInfoList1();
    List<LjflAccountModify> selectAccoountModityInfoListTotal(@Param("modifyusername")String modifyusername);
    List<LjflAccountModify> selectAccoountModityInfoList2(@Param("fstaffdetailid")String fstaffdetailid);
}