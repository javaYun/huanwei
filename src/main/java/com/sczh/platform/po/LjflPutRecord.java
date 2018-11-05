package com.sczh.platform.po;

import java.util.Date;

public class LjflPutRecord {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private Date divisiontime;

    private Double fConfirmLatitude;

    private Double fConfirmLatitudeDone;

    private Double fConfirmLongitude;

    private Double fConfirmLongitudeDone;

    private Integer fConfirmStatus;

    private String fConfirmUserId;

    private Integer fQuality;

    private Integer fReadStatus;

    private Date fSwingTime;

    private Double fWeight;

    private String fConfirmRubbishTypeId;

    private String fHouseholdId;

    private String fStaffDetailId;

    private String fWorkPointId;

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

    public Date getDivisiontime() {
        return divisiontime;
    }

    public void setDivisiontime(Date divisiontime) {
        this.divisiontime = divisiontime;
    }

    public Double getfConfirmLatitude() {
        return fConfirmLatitude;
    }

    public void setfConfirmLatitude(Double fConfirmLatitude) {
        this.fConfirmLatitude = fConfirmLatitude;
    }

    public Double getfConfirmLatitudeDone() {
        return fConfirmLatitudeDone;
    }

    public void setfConfirmLatitudeDone(Double fConfirmLatitudeDone) {
        this.fConfirmLatitudeDone = fConfirmLatitudeDone;
    }

    public Double getfConfirmLongitude() {
        return fConfirmLongitude;
    }

    public void setfConfirmLongitude(Double fConfirmLongitude) {
        this.fConfirmLongitude = fConfirmLongitude;
    }

    public Double getfConfirmLongitudeDone() {
        return fConfirmLongitudeDone;
    }

    public void setfConfirmLongitudeDone(Double fConfirmLongitudeDone) {
        this.fConfirmLongitudeDone = fConfirmLongitudeDone;
    }

    public Integer getfConfirmStatus() {
        return fConfirmStatus;
    }

    public void setfConfirmStatus(Integer fConfirmStatus) {
        this.fConfirmStatus = fConfirmStatus;
    }

    public String getfConfirmUserId() {
        return fConfirmUserId;
    }

    public void setfConfirmUserId(String fConfirmUserId) {
        this.fConfirmUserId = fConfirmUserId == null ? null : fConfirmUserId.trim();
    }

    public Integer getfQuality() {
        return fQuality;
    }

    public void setfQuality(Integer fQuality) {
        this.fQuality = fQuality;
    }

    public Integer getfReadStatus() {
        return fReadStatus;
    }

    public void setfReadStatus(Integer fReadStatus) {
        this.fReadStatus = fReadStatus;
    }

    public Date getfSwingTime() {
        return fSwingTime;
    }

    public void setfSwingTime(Date fSwingTime) {
        this.fSwingTime = fSwingTime;
    }

    public Double getfWeight() {
        return fWeight;
    }

    public void setfWeight(Double fWeight) {
        this.fWeight = fWeight;
    }

    public String getfConfirmRubbishTypeId() {
        return fConfirmRubbishTypeId;
    }

    public void setfConfirmRubbishTypeId(String fConfirmRubbishTypeId) {
        this.fConfirmRubbishTypeId = fConfirmRubbishTypeId == null ? null : fConfirmRubbishTypeId.trim();
    }

    public String getfHouseholdId() {
        return fHouseholdId;
    }

    public void setfHouseholdId(String fHouseholdId) {
        this.fHouseholdId = fHouseholdId == null ? null : fHouseholdId.trim();
    }

    public String getfStaffDetailId() {
        return fStaffDetailId;
    }

    public void setfStaffDetailId(String fStaffDetailId) {
        this.fStaffDetailId = fStaffDetailId == null ? null : fStaffDetailId.trim();
    }

    public String getfWorkPointId() {
        return fWorkPointId;
    }

    public void setfWorkPointId(String fWorkPointId) {
        this.fWorkPointId = fWorkPointId == null ? null : fWorkPointId.trim();
    }
}