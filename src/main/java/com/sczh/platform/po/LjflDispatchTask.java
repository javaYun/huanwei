package com.sczh.platform.po;

import java.util.Date;

public class LjflDispatchTask {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private Date begintime;

    private String clearmanid;

    private String clearmanname;

    private String fCode;

    private Date endtime;

    private Integer responsemin;

    private String taskstatus;

    private String fWorkpoint;

    private String auditid;

    private Date divisiontime;

    private Date fBeginTime;

    private String fClearManId;

    private String fClearManName;

    private Date fEndTime;

    private Date fMsgPushTime;

    private Integer fResponseMin;

    private String fSpillOverTypeCode;

    private String fSpillOverTypeId;

    private String fSpillOverTypeName;

    private Double fSpillOverValue;

    private String fTaskStatus;

    private Double fWeight;

    private String fRubbishTypeId;

    private String fWorkPointId;

    private byte[] rubbishtype;

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

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public String getClearmanid() {
        return clearmanid;
    }

    public void setClearmanid(String clearmanid) {
        this.clearmanid = clearmanid == null ? null : clearmanid.trim();
    }

    public String getClearmanname() {
        return clearmanname;
    }

    public void setClearmanname(String clearmanname) {
        this.clearmanname = clearmanname == null ? null : clearmanname.trim();
    }

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode == null ? null : fCode.trim();
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getResponsemin() {
        return responsemin;
    }

    public void setResponsemin(Integer responsemin) {
        this.responsemin = responsemin;
    }

    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus == null ? null : taskstatus.trim();
    }

    public String getfWorkpoint() {
        return fWorkpoint;
    }

    public void setfWorkpoint(String fWorkpoint) {
        this.fWorkpoint = fWorkpoint == null ? null : fWorkpoint.trim();
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    public Date getDivisiontime() {
        return divisiontime;
    }

    public void setDivisiontime(Date divisiontime) {
        this.divisiontime = divisiontime;
    }

    public Date getfBeginTime() {
        return fBeginTime;
    }

    public void setfBeginTime(Date fBeginTime) {
        this.fBeginTime = fBeginTime;
    }

    public String getfClearManId() {
        return fClearManId;
    }

    public void setfClearManId(String fClearManId) {
        this.fClearManId = fClearManId == null ? null : fClearManId.trim();
    }

    public String getfClearManName() {
        return fClearManName;
    }

    public void setfClearManName(String fClearManName) {
        this.fClearManName = fClearManName == null ? null : fClearManName.trim();
    }

    public Date getfEndTime() {
        return fEndTime;
    }

    public void setfEndTime(Date fEndTime) {
        this.fEndTime = fEndTime;
    }

    public Date getfMsgPushTime() {
        return fMsgPushTime;
    }

    public void setfMsgPushTime(Date fMsgPushTime) {
        this.fMsgPushTime = fMsgPushTime;
    }

    public Integer getfResponseMin() {
        return fResponseMin;
    }

    public void setfResponseMin(Integer fResponseMin) {
        this.fResponseMin = fResponseMin;
    }

    public String getfSpillOverTypeCode() {
        return fSpillOverTypeCode;
    }

    public void setfSpillOverTypeCode(String fSpillOverTypeCode) {
        this.fSpillOverTypeCode = fSpillOverTypeCode == null ? null : fSpillOverTypeCode.trim();
    }

    public String getfSpillOverTypeId() {
        return fSpillOverTypeId;
    }

    public void setfSpillOverTypeId(String fSpillOverTypeId) {
        this.fSpillOverTypeId = fSpillOverTypeId == null ? null : fSpillOverTypeId.trim();
    }

    public String getfSpillOverTypeName() {
        return fSpillOverTypeName;
    }

    public void setfSpillOverTypeName(String fSpillOverTypeName) {
        this.fSpillOverTypeName = fSpillOverTypeName == null ? null : fSpillOverTypeName.trim();
    }

    public Double getfSpillOverValue() {
        return fSpillOverValue;
    }

    public void setfSpillOverValue(Double fSpillOverValue) {
        this.fSpillOverValue = fSpillOverValue;
    }

    public String getfTaskStatus() {
        return fTaskStatus;
    }

    public void setfTaskStatus(String fTaskStatus) {
        this.fTaskStatus = fTaskStatus == null ? null : fTaskStatus.trim();
    }

    public Double getfWeight() {
        return fWeight;
    }

    public void setfWeight(Double fWeight) {
        this.fWeight = fWeight;
    }

    public String getfRubbishTypeId() {
        return fRubbishTypeId;
    }

    public void setfRubbishTypeId(String fRubbishTypeId) {
        this.fRubbishTypeId = fRubbishTypeId == null ? null : fRubbishTypeId.trim();
    }

    public String getfWorkPointId() {
        return fWorkPointId;
    }

    public void setfWorkPointId(String fWorkPointId) {
        this.fWorkPointId = fWorkPointId == null ? null : fWorkPointId.trim();
    }

    public byte[] getRubbishtype() {
        return rubbishtype;
    }

    public void setRubbishtype(byte[] rubbishtype) {
        this.rubbishtype = rubbishtype;
    }
}