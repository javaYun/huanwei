package com.sczh.platform.po;

import java.util.Date;

public class LjflPrize {
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

    private Double fOnePrice;

    private String fPrizeTypeId;

    private String fPhotoUrl;

    private String fPicture;

    private String fPrizeTypeName;

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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public Double getfOnePrice() {
        return fOnePrice;
    }

    public void setfOnePrice(Double fOnePrice) {
        this.fOnePrice = fOnePrice;
    }

    public String getfPrizeTypeId() {
        return fPrizeTypeId;
    }

    public void setfPrizeTypeId(String fPrizeTypeId) {
        this.fPrizeTypeId = fPrizeTypeId == null ? null : fPrizeTypeId.trim();
    }

    public String getfPhotoUrl() {
        return fPhotoUrl;
    }

    public void setfPhotoUrl(String fPhotoUrl) {
        this.fPhotoUrl = fPhotoUrl == null ? null : fPhotoUrl.trim();
    }

    public String getfPicture() {
        return fPicture;
    }

    public void setfPicture(String fPicture) {
        this.fPicture = fPicture == null ? null : fPicture.trim();
    }

    public String getfPrizeTypeName() {
        return fPrizeTypeName;
    }

    public void setfPrizeTypeName(String fPrizeTypeName) {
        this.fPrizeTypeName = fPrizeTypeName == null ? null : fPrizeTypeName.trim();
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
		return "LjflPrize [id=" + id + ", createtime=" + createtime
				+ ", lastchangetime=" + lastchangetime + ", status=" + status
				+ ", beendeleted=" + beendeleted + ", deletedtime="
				+ deletedtime + ", businesssystemid=" + businesssystemid
				+ ", tenantid=" + tenantid + ", fCode=" + fCode + ", fName="
				+ fName + ", fOnePrice=" + fOnePrice + ", fPrizeTypeId="
				+ fPrizeTypeId + ", fPhotoUrl=" + fPhotoUrl + ", fPicture="
				+ fPicture + ", fPrizeTypeName=" + fPrizeTypeName
				+ ", fOrderindex=" + fOrderindex + ", auditid=" + auditid + "]";
	}
    
    
}