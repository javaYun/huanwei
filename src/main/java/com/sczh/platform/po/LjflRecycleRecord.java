package com.sczh.platform.po;

import java.util.Date;

public class LjflRecycleRecord {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private Date divisiontime;

    private Date fConfirmTime;

    private String fMemo;

    private String fRecycleStaffId;

    private String fScoreType;

    private Double fTotalScoreValue;

    private Double fUnitValue;

    private String fRecycleObjectId;

    private String fStaffDetailId;

    private Double latitude;

    private Double latitudedone;

    private Double longitude;

    private Double longitudedone;

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

    public Date getfConfirmTime() {
        return fConfirmTime;
    }

    public void setfConfirmTime(Date fConfirmTime) {
        this.fConfirmTime = fConfirmTime;
    }

    public String getfMemo() {
        return fMemo;
    }

    public void setfMemo(String fMemo) {
        this.fMemo = fMemo == null ? null : fMemo.trim();
    }

    public String getfRecycleStaffId() {
        return fRecycleStaffId;
    }

    public void setfRecycleStaffId(String fRecycleStaffId) {
        this.fRecycleStaffId = fRecycleStaffId == null ? null : fRecycleStaffId.trim();
    }

    public String getfScoreType() {
        return fScoreType;
    }

    public void setfScoreType(String fScoreType) {
        this.fScoreType = fScoreType == null ? null : fScoreType.trim();
    }

    public Double getfTotalScoreValue() {
        return fTotalScoreValue;
    }

    public void setfTotalScoreValue(Double fTotalScoreValue) {
        this.fTotalScoreValue = fTotalScoreValue;
    }

    public Double getfUnitValue() {
        return fUnitValue;
    }

    public void setfUnitValue(Double fUnitValue) {
        this.fUnitValue = fUnitValue;
    }

    public String getfRecycleObjectId() {
        return fRecycleObjectId;
    }

    public void setfRecycleObjectId(String fRecycleObjectId) {
        this.fRecycleObjectId = fRecycleObjectId == null ? null : fRecycleObjectId.trim();
    }

    public String getfStaffDetailId() {
        return fStaffDetailId;
    }

    public void setfStaffDetailId(String fStaffDetailId) {
        this.fStaffDetailId = fStaffDetailId == null ? null : fStaffDetailId.trim();
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLatitudedone() {
        return latitudedone;
    }

    public void setLatitudedone(Double latitudedone) {
        this.latitudedone = latitudedone;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitudedone() {
        return longitudedone;
    }

    public void setLongitudedone(Double longitudedone) {
        this.longitudedone = longitudedone;
    }
}