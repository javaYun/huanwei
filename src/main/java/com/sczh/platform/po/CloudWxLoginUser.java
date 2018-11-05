package com.sczh.platform.po;

import java.util.Date;

public class CloudWxLoginUser {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private String fBindmanid;

    private String fPassword;

    private String fUsername;

    private String fUserstatus;

    private String fUsertype;

    private String fOriginalPassword;

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

    public String getfBindmanid() {
        return fBindmanid;
    }

    public void setfBindmanid(String fBindmanid) {
        this.fBindmanid = fBindmanid == null ? null : fBindmanid.trim();
    }

    public String getfPassword() {
        return fPassword;
    }

    public void setfPassword(String fPassword) {
        this.fPassword = fPassword == null ? null : fPassword.trim();
    }

    public String getfUsername() {
        return fUsername;
    }

    public void setfUsername(String fUsername) {
        this.fUsername = fUsername == null ? null : fUsername.trim();
    }

    public String getfUserstatus() {
        return fUserstatus;
    }

    public void setfUserstatus(String fUserstatus) {
        this.fUserstatus = fUserstatus == null ? null : fUserstatus.trim();
    }

    public String getfUsertype() {
        return fUsertype;
    }

    public void setfUsertype(String fUsertype) {
        this.fUsertype = fUsertype == null ? null : fUsertype.trim();
    }

    public String getfOriginalPassword() {
        return fOriginalPassword;
    }

    public void setfOriginalPassword(String fOriginalPassword) {
        this.fOriginalPassword = fOriginalPassword == null ? null : fOriginalPassword.trim();
    }
}