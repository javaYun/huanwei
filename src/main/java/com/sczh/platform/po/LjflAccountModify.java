package com.sczh.platform.po;

import java.util.Date;

public class LjflAccountModify {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private Double fAddBalance;

    private Double fAddScore;

    private String fModifyUserId;

    private String fModifyUserName;

    private String fStaffDetailId;

    private String fMemo;

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

    public Double getfAddBalance() {
        return fAddBalance;
    }

    public void setfAddBalance(Double fAddBalance) {
        this.fAddBalance = fAddBalance;
    }

    public Double getfAddScore() {
        return fAddScore;
    }

    public void setfAddScore(Double fAddScore) {
        this.fAddScore = fAddScore;
    }

    public String getfModifyUserId() {
        return fModifyUserId;
    }

    public void setfModifyUserId(String fModifyUserId) {
        this.fModifyUserId = fModifyUserId == null ? null : fModifyUserId.trim();
    }

    public String getfModifyUserName() {
        return fModifyUserName;
    }

    public void setfModifyUserName(String fModifyUserName) {
        this.fModifyUserName = fModifyUserName == null ? null : fModifyUserName.trim();
    }

    public String getfStaffDetailId() {
        return fStaffDetailId;
    }

    public void setfStaffDetailId(String fStaffDetailId) {
        this.fStaffDetailId = fStaffDetailId == null ? null : fStaffDetailId.trim();
    }

    public String getfMemo() {
        return fMemo;
    }

    public void setfMemo(String fMemo) {
        this.fMemo = fMemo == null ? null : fMemo.trim();
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

	@Override
	public String toString() {
		return "LjflAccountModify [id=" + id + ", createtime=" + createtime
				+ ", lastchangetime=" + lastchangetime + ", status=" + status
				+ ", beendeleted=" + beendeleted + ", deletedtime="
				+ deletedtime + ", businesssystemid=" + businesssystemid
				+ ", tenantid=" + tenantid + ", fAddBalance=" + fAddBalance
				+ ", fAddScore=" + fAddScore + ", fModifyUserId="
				+ fModifyUserId + ", fModifyUserName=" + fModifyUserName
				+ ", fStaffDetailId=" + fStaffDetailId + ", fMemo=" + fMemo
				+ ", auditid=" + auditid + "]";
	}
    
    
}