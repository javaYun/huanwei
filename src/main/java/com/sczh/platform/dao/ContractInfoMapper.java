package com.sczh.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.ContractInfo;

public interface ContractInfoMapper {
	
	ContractInfo selectByPrimaryKey(String id);
    
	int deleteByPrimaryKey(String id);

    int insert(ContractInfo record);

    int insertSelective(ContractInfo record);

    int updateByPrimaryKeySelective(ContractInfo record);

    int updateByPrimaryKey(ContractInfo record);
    
    
    //查询所有合同
    List<ContractInfo> selectContractInfos(
    		@Param("startSize")String startSize,
    		@Param("endSize")String endSize
    		);
    //查询当前用户所上传的所有合同
    List<ContractInfo> selectContractInfosss(
    		@Param("startSize")String startSize,
    		@Param("endSize")String endSize,
    		@Param("uploadname")String uploadname
    		);
    //查询所有Admin用户上传的报表
    List<ContractInfo> selectReportformsTotal(
    		@Param("startSize")String startSize,
    		@Param("endSize")String endSize,
    		@Param("name")String name
    		);
    //查询所有除模板外的报表
    List<ContractInfo> selectReportforms(
    		@Param("startSize")String startSize,
    		@Param("endSize")String endSize,
    		@Param("name")String name
    		);
    //查询当前用户所上传的报表
    List<ContractInfo> selectReportformsUploadname(
    		@Param("startSize")String startSize,
    		@Param("endSize")String endSize,
    		@Param("uploadname")String uploadname
    		);
   
    
    List<ContractInfo> selectContractInfosTotal(String name);
}