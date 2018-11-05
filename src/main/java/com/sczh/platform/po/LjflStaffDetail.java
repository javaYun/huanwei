package com.sczh.platform.po;

import java.util.Arrays;
import java.util.Date;

public class LjflStaffDetail {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private Integer age;

    private String credentialnum;

    private Double fCurrentBalance;

    private Double fCurrentScore;

    private String educationid;

    private String gender;

    private Integer fLiveNum;

    private String maritalstatusid;

    private String name;

    private String phone;

    private String picture;

    private String fServeStatus;

    private Integer fStaffType;

    private String tdbimgid;

    private String tdbstr;

    private Double fTotalBalance;

    private Double fTotalScore;

    private String fHouseholdId;

    private String fResidentTypeId;

    private String fAddress;

    private String auditid;

    private Date fLastAddScoreTime;

    private Date fLastConsumeScoreTime;

    private String doorname;

    private Integer znum;

    private Integer rnum;

    private byte[] fDepartmentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastchangetime() {
        return lastchangetime;
    }

    public void setLastchangetime(Date lastchangetime) {
        this.lastchangetime = lastchangetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBeendeleted() {
        return beendeleted;
    }

    public void setBeendeleted(Integer beendeleted) {
        this.beendeleted = beendeleted;
    }

    public Date getDeletedtime() {
        return deletedtime;
    }

    public void setDeletedtime(Date deletedtime) {
        this.deletedtime = deletedtime;
    }

    public String getBusinesssystemid() {
        return businesssystemid;
    }

    public void setBusinesssystemid(String businesssystemid) {
        this.businesssystemid = businesssystemid == null ? null : businesssystemid.trim();
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid == null ? null : tenantid.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCredentialnum() {
        return credentialnum;
    }

    public void setCredentialnum(String credentialnum) {
        this.credentialnum = credentialnum == null ? null : credentialnum.trim();
    }

    public Double getfCurrentBalance() {
        return fCurrentBalance;
    }

    public void setfCurrentBalance(Double fCurrentBalance) {
        this.fCurrentBalance = fCurrentBalance;
    }

    public Double getfCurrentScore() {
        return fCurrentScore;
    }

    public void setfCurrentScore(Double fCurrentScore) {
        this.fCurrentScore = fCurrentScore;
    }

    public String getEducationid() {
        return educationid;
    }

    public void setEducationid(String educationid) {
        this.educationid = educationid == null ? null : educationid.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Integer getfLiveNum() {
        return fLiveNum;
    }

    public void setfLiveNum(Integer fLiveNum) {
        this.fLiveNum = fLiveNum;
    }

    public String getMaritalstatusid() {
        return maritalstatusid;
    }

    public void setMaritalstatusid(String maritalstatusid) {
        this.maritalstatusid = maritalstatusid == null ? null : maritalstatusid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getfServeStatus() {
        return fServeStatus;
    }

    public void setfServeStatus(String fServeStatus) {
        this.fServeStatus = fServeStatus == null ? null : fServeStatus.trim();
    }

    public Integer getfStaffType() {
        return fStaffType;
    }

    public void setfStaffType(Integer fStaffType) {
        this.fStaffType = fStaffType;
    }

    public String getTdbimgid() {
        return tdbimgid;
    }

    public void setTdbimgid(String tdbimgid) {
        this.tdbimgid = tdbimgid == null ? null : tdbimgid.trim();
    }

    public String getTdbstr() {
        return tdbstr;
    }

    public void setTdbstr(String tdbstr) {
        this.tdbstr = tdbstr == null ? null : tdbstr.trim();
    }

    public Double getfTotalBalance() {
        return fTotalBalance;
    }

    public void setfTotalBalance(Double fTotalBalance) {
        this.fTotalBalance = fTotalBalance;
    }

    public Double getfTotalScore() {
        return fTotalScore;
    }

    public void setfTotalScore(Double fTotalScore) {
        this.fTotalScore = fTotalScore;
    }

    public String getfHouseholdId() {
        return fHouseholdId;
    }

    public void setfHouseholdId(String fHouseholdId) {
        this.fHouseholdId = fHouseholdId == null ? null : fHouseholdId.trim();
    }

    public String getfResidentTypeId() {
        return fResidentTypeId;
    }

    public void setfResidentTypeId(String fResidentTypeId) {
        this.fResidentTypeId = fResidentTypeId == null ? null : fResidentTypeId.trim();
    }

    public String getfAddress() {
        return fAddress;
    }

    public void setfAddress(String fAddress) {
        this.fAddress = fAddress == null ? null : fAddress.trim();
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    public Date getfLastAddScoreTime() {
        return fLastAddScoreTime;
    }

    public void setfLastAddScoreTime(Date fLastAddScoreTime) {
        this.fLastAddScoreTime = fLastAddScoreTime;
    }

    public Date getfLastConsumeScoreTime() {
        return fLastConsumeScoreTime;
    }

    public void setfLastConsumeScoreTime(Date fLastConsumeScoreTime) {
        this.fLastConsumeScoreTime = fLastConsumeScoreTime;
    }

    public String getDoorname() {
        return doorname;
    }

    public void setDoorname(String doorname) {
        this.doorname = doorname == null ? null : doorname.trim();
    }

    public Integer getZnum() {
        return znum;
    }

    public void setZnum(Integer znum) {
        this.znum = znum;
    }

    public Integer getRnum() {
        return rnum;
    }

    public void setRnum(Integer rnum) {
        this.rnum = rnum;
    }

    public byte[] getfDepartmentId() {
        return fDepartmentId;
    }

    public void setfDepartmentId(byte[] fDepartmentId) {
        this.fDepartmentId = fDepartmentId;
    }

	@Override
	public String toString() {
		return "LjflStaffDetail [id=" + id + ", createtime=" + createtime
				+ ", lastchangetime=" + lastchangetime + ", status=" + status
				+ ", beendeleted=" + beendeleted + ", deletedtime="
				+ deletedtime + ", businesssystemid=" + businesssystemid
				+ ", tenantid=" + tenantid + ", age=" + age
				+ ", credentialnum=" + credentialnum + ", fCurrentBalance="
				+ fCurrentBalance + ", fCurrentScore=" + fCurrentScore
				+ ", educationid=" + educationid + ", gender=" + gender
				+ ", fLiveNum=" + fLiveNum + ", maritalstatusid="
				+ maritalstatusid + ", name=" + name + ", phone=" + phone
				+ ", picture=" + picture + ", fServeStatus=" + fServeStatus
				+ ", fStaffType=" + fStaffType + ", tdbimgid=" + tdbimgid
				+ ", tdbstr=" + tdbstr + ", fTotalBalance=" + fTotalBalance
				+ ", fTotalScore=" + fTotalScore + ", fHouseholdId="
				+ fHouseholdId + ", fResidentTypeId=" + fResidentTypeId
				+ ", fAddress=" + fAddress + ", auditid=" + auditid
				+ ", fLastAddScoreTime=" + fLastAddScoreTime
				+ ", fLastConsumeScoreTime=" + fLastConsumeScoreTime
				+ ", doorname=" + doorname + ", znum=" + znum + ", rnum="
				+ rnum + ", fDepartmentId=" + Arrays.toString(fDepartmentId)
				+ "]";
	}
    
    
    
}