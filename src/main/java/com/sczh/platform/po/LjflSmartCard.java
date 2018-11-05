package com.sczh.platform.po;

import java.util.Date;

public class LjflSmartCard {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private String fBindId;

    private String fBindName;

    private Date fBindTime;

    private Integer fBindType;

    private String fCCode;

    private String fCode;

    private String auditid;

    private String userid;

    private String unitid;

    private String str1;

    private String fMemo;

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

    public String getfBindId() {
        return fBindId;
    }

    public void setfBindId(String fBindId) {
        this.fBindId = fBindId == null ? null : fBindId.trim();
    }

    public String getfBindName() {
        return fBindName;
    }

    public void setfBindName(String fBindName) {
        this.fBindName = fBindName == null ? null : fBindName.trim();
    }

    public Date getfBindTime() {
        return fBindTime;
    }

    public void setfBindTime(Date fBindTime) {
        this.fBindTime = fBindTime;
    }

    public Integer getfBindType() {
        return fBindType;
    }

    public void setfBindType(Integer fBindType) {
        this.fBindType = fBindType;
    }

    public String getfCCode() {
        return fCCode;
    }

    public void setfCCode(String fCCode) {
        this.fCCode = fCCode == null ? null : fCCode.trim();
    }

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode == null ? null : fCode.trim();
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1 == null ? null : str1.trim();
    }

    public String getfMemo() {
        return fMemo;
    }

    public void setfMemo(String fMemo) {
        this.fMemo = fMemo == null ? null : fMemo.trim();
    }
}