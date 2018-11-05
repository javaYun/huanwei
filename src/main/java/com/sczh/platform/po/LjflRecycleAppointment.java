package com.sczh.platform.po;

import java.util.Date;
/**
 * 预约回收(ljfl_recycle_appointment)
 * @author yunluo
 *
 */
public class LjflRecycleAppointment {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private String fAddress;

    private Date fAppointmentDate;
	/**
	 * 状态 RecycleAppointmentStatusEnum<br/>
	 * WAITCONFIRM("waitConfirm", "待确认"), REJECTED("rejected", "已拒绝"), CONFIRMED("confirmed", "已确认");
	 */
    private String fAppointmentStatus;

    private Date fEndDate;

    private String fRecycleObjectName;

    private Date fStartDate;

    private String fTel;

    private String fStaffDetailId;

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

    public String getfAddress() {
        return fAddress;
    }

    public void setfAddress(String fAddress) {
        this.fAddress = fAddress == null ? null : fAddress.trim();
    }

    public Date getfAppointmentDate() {
        return fAppointmentDate;
    }

    public void setfAppointmentDate(Date fAppointmentDate) {
        this.fAppointmentDate = fAppointmentDate;
    }

    public String getfAppointmentStatus() {
        return fAppointmentStatus;
    }

    public void setfAppointmentStatus(String fAppointmentStatus) {
        this.fAppointmentStatus = fAppointmentStatus == null ? null : fAppointmentStatus.trim();
    }

    public Date getfEndDate() {
        return fEndDate;
    }

    public void setfEndDate(Date fEndDate) {
        this.fEndDate = fEndDate;
    }

    public String getfRecycleObjectName() {
        return fRecycleObjectName;
    }

    public void setfRecycleObjectName(String fRecycleObjectName) {
        this.fRecycleObjectName = fRecycleObjectName == null ? null : fRecycleObjectName.trim();
    }

    public Date getfStartDate() {
        return fStartDate;
    }

    public void setfStartDate(Date fStartDate) {
        this.fStartDate = fStartDate;
    }

    public String getfTel() {
        return fTel;
    }

    public void setfTel(String fTel) {
        this.fTel = fTel == null ? null : fTel.trim();
    }

    public String getfStaffDetailId() {
        return fStaffDetailId;
    }

    public void setfStaffDetailId(String fStaffDetailId) {
        this.fStaffDetailId = fStaffDetailId == null ? null : fStaffDetailId.trim();
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }
}