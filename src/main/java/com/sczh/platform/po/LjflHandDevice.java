package com.sczh.platform.po;

import java.util.Date;

public class LjflHandDevice {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String auditid;

    private String businesssystemid;

    private String tenantid;

    private String address;

    private String code;

    private Date lastlogintime;

    private String lastloginuserid;

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

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getLastloginuserid() {
        return lastloginuserid;
    }

    public void setLastloginuserid(String lastloginuserid) {
        this.lastloginuserid = lastloginuserid == null ? null : lastloginuserid.trim();
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

	@Override
	public String toString() {
		return "LjflHandDevice [id=" + id + ", createtime=" + createtime
				+ ", lastchangetime=" + lastchangetime + ", status=" + status
				+ ", beendeleted=" + beendeleted + ", deletedtime="
				+ deletedtime + ", auditid=" + auditid + ", businesssystemid="
				+ businesssystemid + ", tenantid=" + tenantid + ", address="
				+ address + ", code=" + code + ", lastlogintime="
				+ lastlogintime + ", lastloginuserid=" + lastloginuserid
				+ ", latitude=" + latitude + ", latitudedone=" + latitudedone
				+ ", longitude=" + longitude + ", longitudedone="
				+ longitudedone + "]";
	}
    
    
    
}