package com.sczh.platform.po;

import java.util.Date;

public class LjflTrashCan {
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

    private String fFrequencyId;

    private String fLjflTypeId;

    private String fWorkPointId;

    private String fClearStaffId;

    private String auditid;

    private String fClearStaffName;

    private Double fHeight;

    private String fSpillOverCode;

    private Double fSpillOverValue;

    private Double fVolume;

    private Double fWeight;

    private String fLastSpillOverCode;

    private Double fLastSpillOverValue;
    

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

    public String getfFrequencyId() {
        return fFrequencyId;
    }

    public void setfFrequencyId(String fFrequencyId) {
        this.fFrequencyId = fFrequencyId == null ? null : fFrequencyId.trim();
    }

    public String getfLjflTypeId() {
        return fLjflTypeId;
    }

    public void setfLjflTypeId(String fLjflTypeId) {
        this.fLjflTypeId = fLjflTypeId == null ? null : fLjflTypeId.trim();
    }

    public String getfWorkPointId() {
        return fWorkPointId;
    }

    public void setfWorkPointId(String fWorkPointId) {
        this.fWorkPointId = fWorkPointId == null ? null : fWorkPointId.trim();
    }

    public String getfClearStaffId() {
        return fClearStaffId;
    }

    public void setfClearStaffId(String fClearStaffId) {
        this.fClearStaffId = fClearStaffId == null ? null : fClearStaffId.trim();
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    public String getfClearStaffName() {
        return fClearStaffName;
    }

    public void setfClearStaffName(String fClearStaffName) {
        this.fClearStaffName = fClearStaffName == null ? null : fClearStaffName.trim();
    }

    public Double getfHeight() {
        return fHeight;
    }

    public void setfHeight(Double fHeight) {
        this.fHeight = fHeight;
    }

    public String getfSpillOverCode() {
        return fSpillOverCode;
    }

    public void setfSpillOverCode(String fSpillOverCode) {
        this.fSpillOverCode = fSpillOverCode == null ? null : fSpillOverCode.trim();
    }

    public Double getfSpillOverValue() {
        return fSpillOverValue;
    }

    public void setfSpillOverValue(Double fSpillOverValue) {
        this.fSpillOverValue = fSpillOverValue;
    }

    public Double getfVolume() {
        return fVolume;
    }

    public void setfVolume(Double fVolume) {
        this.fVolume = fVolume;
    }

    public Double getfWeight() {
        return fWeight;
    }

    public void setfWeight(Double fWeight) {
        this.fWeight = fWeight;
    }

    public String getfLastSpillOverCode() {
        return fLastSpillOverCode;
    }

    public void setfLastSpillOverCode(String fLastSpillOverCode) {
        this.fLastSpillOverCode = fLastSpillOverCode == null ? null : fLastSpillOverCode.trim();
    }

    public Double getfLastSpillOverValue() {
        return fLastSpillOverValue;
    }

    public void setfLastSpillOverValue(Double fLastSpillOverValue) {
        this.fLastSpillOverValue = fLastSpillOverValue;
    }
}