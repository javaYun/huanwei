package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.AreaRoleManager;
import com.sczh.platform.po.CloudWxLoginUser;
import com.sczh.platform.po.ContractInfo;
import com.sczh.platform.po.LjflHandDevice;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.po.Model.PageModelDomain;
public interface UserService {

	int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKeyWithBLOBs(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    UserInfo selectByUserNameAndPassword(@Param("username") String username,@Param("password") String password);

    List<UserInfo> selectListUserInfos();
    
    
    
    
    
    ContractInfo selectByPrimaryKeyfile(String id);
    
    int deleteByPrimaryKeyfile(String id);

	int insert(ContractInfo record);

	int insertSelective(ContractInfo record);
    
    int updateByPrimaryKeySelective(ContractInfo record);
    
    int updateByPrimaryKey(ContractInfo record);
    
    List<ContractInfo> selectContractInfos(
    		@Param("startSize") String startSize,
    		@Param("endSize") String endSize
    		);
    List<ContractInfo> selectContractInfosss(
    		@Param("startSize") String startSize,
    		@Param("endSize") String endSize,
    		String uploadname
    		);
    List<ContractInfo> selectReportformsTotal(
    		@Param("startSize")String startSize,
    		@Param("endSize")String endSize,
    		String name
    		);
    List<ContractInfo> selectReportforms(
    		@Param("startSize")String startSize,
    		@Param("endSize")String endSize,
    		String name
    		);
    List<ContractInfo> selectReportformsUploadname(
    		@Param("startSize") String startSize,
    		@Param("endSize") String endSize,
    		String uploadname
    		);
    
    List<ContractInfo> selectContractInfosTotal(String name);

	 CloudWxLoginUser selectBystaffId(String id);

	    int updateByPrimaryKey(CloudWxLoginUser record);
		List<AreaRoleManager> selectAreaRoleManager();

		//手持端设备管理
		List<LjflHandDevice> selecthanddeviceinfos(
				@Param("startSize")String startSize,
				@Param("endSize")String endSize,
				@Param("code")String code,
				@Param("name")String name);
		 
	    List<LjflHandDevice> selecthanddeviceinfostotal(@Param("code")String code,@Param("name")String name);
	    
	    int deleteById(String id);
	    
	    void insertRoleManagerInfo(AreaRoleManager role);

		List<AreaRoleManager> selectAreaRoleManagerListByUserId(PageModelDomain pageModelDomain);

		int selectAreaRoleManagerByUnitId(String unitId);

		void deleteAreaRoleManageInfo(String id);
}
