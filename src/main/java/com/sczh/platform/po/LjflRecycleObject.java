package com.sczh.platform.po;

import java.util.Date;

public class LjflRecycleObject {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private String fCode;

    private String fName;

    private String fScoreType;

    private Double fScoreTypeValue;

    private String fRecycleTypeId;

    private Integer fOrderIndex;

    private String auditid;

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

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode == null ? null : fCode.trim();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public String getfScoreType() {
        return fScoreType;
    }

    public void setfScoreType(String fScoreType) {
        this.fScoreType = fScoreType == null ? null : fScoreType.trim();
    }

    public Double getfScoreTypeValue() {
        return fScoreTypeValue;
    }

    public void setfScoreTypeValue(Double fScoreTypeValue) {
        this.fScoreTypeValue = fScoreTypeValue;
    }

    public String getfRecycleTypeId() {
        return fRecycleTypeId;
    }

    public void setfRecycleTypeId(String fRecycleTypeId) {
        this.fRecycleTypeId = fRecycleTypeId == null ? null : fRecycleTypeId.trim();
    }

    public Integer getfOrderIndex() {
        return fOrderIndex;
    }

    public void setfOrderIndex(Integer fOrderIndex) {
        this.fOrderIndex = fOrderIndex;
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    public String getfMemo() {
        return fMemo;
    }

    public void setfMemo(String fMemo) {
        this.fMemo = fMemo == null ? null : fMemo.trim();
    }

	@Override
	public String toString() {
		return "LjflRecycleObject [id=" + id + ", createtime=" + createtime
				+ ", lastchangetime=" + lastchangetime + ", status=" + status
				+ ", beendeleted=" + beendeleted + ", deletedtime="
				+ deletedtime + ", businesssystemid=" + businesssystemid
				+ ", tenantid=" + tenantid + ", fCode=" + fCode + ", fName="
				+ fName + ", fScoreType=" + fScoreType + ", fScoreTypeValue="
				+ fScoreTypeValue + ", fRecycleTypeId=" + fRecycleTypeId
				+ ", fOrderIndex=" + fOrderIndex + ", auditid=" + auditid
				+ ", fMemo=" + fMemo + "]";
	}
    
    
}