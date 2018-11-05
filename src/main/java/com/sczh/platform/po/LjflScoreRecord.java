package com.sczh.platform.po;

import java.util.Date;

public class LjflScoreRecord {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private String fAddObjectId;

    private Double fAddScore;

    private Double fAddScoreInTheory;

    private String fAddType;

    private Double fScoreBalance;

    private Date fScoreTime;

    private String fStaffDetailId;

    private Date divisiontime;

    private String auditid;

    private String fScoreTypeId;

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

    public String getfAddObjectId() {
        return fAddObjectId;
    }

    public void setfAddObjectId(String fAddObjectId) {
        this.fAddObjectId = fAddObjectId == null ? null : fAddObjectId.trim();
    }

    public Double getfAddScore() {
        return fAddScore;
    }

    public void setfAddScore(Double fAddScore) {
        this.fAddScore = fAddScore;
    }

    public Double getfAddScoreInTheory() {
        return fAddScoreInTheory;
    }

    public void setfAddScoreInTheory(Double fAddScoreInTheory) {
        this.fAddScoreInTheory = fAddScoreInTheory;
    }

    public String getfAddType() {
        return fAddType;
    }

    public void setfAddType(String fAddType) {
        this.fAddType = fAddType == null ? null : fAddType.trim();
    }

    public Double getfScoreBalance() {
        return fScoreBalance;
    }

    public void setfScoreBalance(Double fScoreBalance) {
        this.fScoreBalance = fScoreBalance;
    }

    public Date getfScoreTime() {
        return fScoreTime;
    }

    public void setfScoreTime(Date fScoreTime) {
        this.fScoreTime = fScoreTime;
    }

    public String getfStaffDetailId() {
        return fStaffDetailId;
    }

    public void setfStaffDetailId(String fStaffDetailId) {
        this.fStaffDetailId = fStaffDetailId == null ? null : fStaffDetailId.trim();
    }

    public Date getDivisiontime() {
        return divisiontime;
    }

    public void setDivisiontime(Date divisiontime) {
        this.divisiontime = divisiontime;
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    public String getfScoreTypeId() {
        return fScoreTypeId;
    }

    public void setfScoreTypeId(String fScoreTypeId) {
        this.fScoreTypeId = fScoreTypeId == null ? null : fScoreTypeId.trim();
    }
}