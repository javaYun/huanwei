package com.sczh.platform.po;

import java.util.Date;

public class LjflPrizeProvide {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private String fCode;

    private String fMemo;

    private String fName;

    private Integer fProviderNum;

    private Integer fScoreNum;

    private Integer fTakeNum;

    private Date fValidityDate;

    private String fPrizeId;

    private String fReceiveCode;

    private Integer fOrderindex;

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

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode == null ? null : fCode.trim();
    }

    public String getfMemo() {
        return fMemo;
    }

    public void setfMemo(String fMemo) {
        this.fMemo = fMemo == null ? null : fMemo.trim();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public Integer getfProviderNum() {
        return fProviderNum;
    }

    public void setfProviderNum(Integer fProviderNum) {
        this.fProviderNum = fProviderNum;
    }

    public Integer getfScoreNum() {
        return fScoreNum;
    }

    public void setfScoreNum(Integer fScoreNum) {
        this.fScoreNum = fScoreNum;
    }

    public Integer getfTakeNum() {
        return fTakeNum;
    }

    public void setfTakeNum(Integer fTakeNum) {
        this.fTakeNum = fTakeNum;
    }

    public Date getfValidityDate() {
        return fValidityDate;
    }

    public void setfValidityDate(Date fValidityDate) {
        this.fValidityDate = fValidityDate;
    }

    public String getfPrizeId() {
        return fPrizeId;
    }

    public void setfPrizeId(String fPrizeId) {
        this.fPrizeId = fPrizeId == null ? null : fPrizeId.trim();
    }

    public String getfReceiveCode() {
        return fReceiveCode;
    }

    public void setfReceiveCode(String fReceiveCode) {
        this.fReceiveCode = fReceiveCode == null ? null : fReceiveCode.trim();
    }

    public Integer getfOrderindex() {
        return fOrderindex;
    }

    public void setfOrderindex(Integer fOrderindex) {
        this.fOrderindex = fOrderindex;
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

	@Override
	public String toString() {
		return "LjflPrizeProvide [id=" + id + ", createtime=" + createtime
				+ ", lastchangetime=" + lastchangetime + ", status=" + status
				+ ", beendeleted=" + beendeleted + ", deletedtime="
				+ deletedtime + ", businesssystemid=" + businesssystemid
				+ ", tenantid=" + tenantid + ", fCode=" + fCode + ", fMemo="
				+ fMemo + ", fName=" + fName + ", fProviderNum=" + fProviderNum
				+ ", fScoreNum=" + fScoreNum + ", fTakeNum=" + fTakeNum
				+ ", fValidityDate=" + fValidityDate + ", fPrizeId=" + fPrizeId
				+ ", fReceiveCode=" + fReceiveCode + ", fOrderindex="
				+ fOrderindex + ", auditid=" + auditid + "]";
	}
    
    
}