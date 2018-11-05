package com.sczh.platform.po;

import java.util.Date;

public class LjflWorkPoint {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private String fCode;

    private String fCoverId;

    private Double fLatitude;

    private Double fLatitudeDone;

    private Double fLongitude;

    private Double fLongitudeDone;

    private String fManagerStaffId;

    private String fName;

    private Integer fOrderIndex;

    private String fPhotoIds;

    private Integer fServerPersonNum;

    private String fTransferStationId;

    private Integer fTrashCanNum;

    private String fHousesId;

    private String fWorkPointType;

    private String fRubbishTypeId;

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

    public String getfCoverId() {
        return fCoverId;
    }

    public void setfCoverId(String fCoverId) {
        this.fCoverId = fCoverId == null ? null : fCoverId.trim();
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

    public String getfManagerStaffId() {
        return fManagerStaffId;
    }

    public void setfManagerStaffId(String fManagerStaffId) {
        this.fManagerStaffId = fManagerStaffId == null ? null : fManagerStaffId.trim();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public Integer getfOrderIndex() {
        return fOrderIndex;
    }

    public void setfOrderIndex(Integer fOrderIndex) {
        this.fOrderIndex = fOrderIndex;
    }

    public String getfPhotoIds() {
        return fPhotoIds;
    }

    public void setfPhotoIds(String fPhotoIds) {
        this.fPhotoIds = fPhotoIds == null ? null : fPhotoIds.trim();
    }

    public Integer getfServerPersonNum() {
        return fServerPersonNum;
    }

    public void setfServerPersonNum(Integer fServerPersonNum) {
        this.fServerPersonNum = fServerPersonNum;
    }

    public String getfTransferStationId() {
        return fTransferStationId;
    }

    public void setfTransferStationId(String fTransferStationId) {
        this.fTransferStationId = fTransferStationId == null ? null : fTransferStationId.trim();
    }

    public Integer getfTrashCanNum() {
        return fTrashCanNum;
    }

    public void setfTrashCanNum(Integer fTrashCanNum) {
        this.fTrashCanNum = fTrashCanNum;
    }

    public String getfHousesId() {
        return fHousesId;
    }

    public void setfHousesId(String fHousesId) {
        this.fHousesId = fHousesId == null ? null : fHousesId.trim();
    }

    public String getfWorkPointType() {
        return fWorkPointType;
    }

    public void setfWorkPointType(String fWorkPointType) {
        this.fWorkPointType = fWorkPointType == null ? null : fWorkPointType.trim();
    }

    public String getfRubbishTypeId() {
        return fRubbishTypeId;
    }

    public void setfRubbishTypeId(String fRubbishTypeId) {
        this.fRubbishTypeId = fRubbishTypeId == null ? null : fRubbishTypeId.trim();
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
}