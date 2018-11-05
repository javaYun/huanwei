package com.sczh.platform.po;

import java.util.Date;

public class LjflQuestion {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private Date divisiontime;

    private String fDescription;

    private Double fLatitude;

    private Double fLatitudeDone;

    private Double fLongitude;

    private Double fLongitudeDone;

    private String fPhotoIds;

    private String fStaffId;

    private Date fUploadTime;

    private String fHousesId;

    private String fWorkPointId;

    private String fHandlingStatus;

    private String fHandlingSuggestion;

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

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription == null ? null : fDescription.trim();
    }

    public Double getfLatitude() {
        return fLatitude;
    }

    public void setfLatitude(Double fLatitude) {
        this.fLatitude = fLatitude;
    }

    public Double getfLatitudeDone() {
        return fLatitudeDone;
    }

    public void setfLatitudeDone(Double fLatitudeDone) {
        this.fLatitudeDone = fLatitudeDone;
    }

    public Double getfLongitude() {
        return fLongitude;
    }

    public void setfLongitude(Double fLongitude) {
        this.fLongitude = fLongitude;
    }

    public Double getfLongitudeDone() {
        return fLongitudeDone;
    }

    public void setfLongitudeDone(Double fLongitudeDone) {
        this.fLongitudeDone = fLongitudeDone;
    }

    public String getfPhotoIds() {
        return fPhotoIds;
    }

    public void setfPhotoIds(String fPhotoIds) {
        this.fPhotoIds = fPhotoIds == null ? null : fPhotoIds.trim();
    }

    public String getfStaffId() {
        return fStaffId;
    }

    public void setfStaffId(String fStaffId) {
        this.fStaffId = fStaffId == null ? null : fStaffId.trim();
    }

    public Date getfUploadTime() {
        return fUploadTime;
    }

    public void setfUploadTime(Date fUploadTime) {
        this.fUploadTime = fUploadTime;
    }

    public String getfHousesId() {
        return fHousesId;
    }

    public void setfHousesId(String fHousesId) {
        this.fHousesId = fHousesId == null ? null : fHousesId.trim();
    }

    public String getfWorkPointId() {
        return fWorkPointId;
    }

    public void setfWorkPointId(String fWorkPointId) {
        this.fWorkPointId = fWorkPointId == null ? null : fWorkPointId.trim();
    }

    public String getfHandlingStatus() {
        return fHandlingStatus;
    }

    public void setfHandlingStatus(String fHandlingStatus) {
        this.fHandlingStatus = fHandlingStatus == null ? null : fHandlingStatus.trim();
    }

    public String getfHandlingSuggestion() {
        return fHandlingSuggestion;
    }

    public void setfHandlingSuggestion(String fHandlingSuggestion) {
        this.fHandlingSuggestion = fHandlingSuggestion == null ? null : fHandlingSuggestion.trim();
    }
}