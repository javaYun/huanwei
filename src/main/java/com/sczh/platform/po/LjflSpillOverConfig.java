package com.sczh.platform.po;

import java.util.Date;

public class LjflSpillOverConfig {
    private String id;

    private Date createtime;

    private Date lastchangetime;
	
    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private String fColourValue;

    private Double fEndValue;
    /**
	 * 满溢状态编码（枚举SpillOverEnum：正常normal,轻微满溢slight,严重满溢serious）
	 */
    private String fSpillOverCode;

    private Double fStartValue;

    private String auditid;

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

    public String getfColourValue() {
        return fColourValue;
    }

    public void setfColourValue(String fColourValue) {
        this.fColourValue = fColourValue == null ? null : fColourValue.trim();
    }

    public Double getfEndValue() {
        return fEndValue;
    }

    public void setfEndValue(Double fEndValue) {
        this.fEndValue = fEndValue;
    }

    public String getfSpillOverCode() {
        return fSpillOverCode;
    }

    public void setfSpillOverCode(String fSpillOverCode) {
        this.fSpillOverCode = fSpillOverCode == null ? null : fSpillOverCode.trim();
    }

    public Double getfStartValue() {
        return fStartValue;
    }

    public void setfStartValue(Double fStartValue) {
        this.fStartValue = fStartValue;
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }
}