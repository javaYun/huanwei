package com.sczh.platform.po;

import java.util.Date;

public class LjflStaffStatistic {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String auditid;

    private String businesssystemid;

    private String tenantid;

    private Integer persontime;

    private String rubbishtypeid;

    private Integer rubbishtypeindex;

    private String rubbishtypename;

    private String staffid;

    private String staffname;

    private Integer stafforderindex;

    private Date statisticdate;

    private Integer systemindex;

    private String systemtype;

    private String unit;

    private Double value;

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

    public Integer getPersontime() {
        return persontime;
    }

    public void setPersontime(Integer persontime) {
        this.persontime = persontime;
    }

    public String getRubbishtypeid() {
        return rubbishtypeid;
    }

    public void setRubbishtypeid(String rubbishtypeid) {
        this.rubbishtypeid = rubbishtypeid == null ? null : rubbishtypeid.trim();
    }

    public Integer getRubbishtypeindex() {
        return rubbishtypeindex;
    }

    public void setRubbishtypeindex(Integer rubbishtypeindex) {
        this.rubbishtypeindex = rubbishtypeindex;
    }

    public String getRubbishtypename() {
        return rubbishtypename;
    }

    public void setRubbishtypename(String rubbishtypename) {
        this.rubbishtypename = rubbishtypename == null ? null : rubbishtypename.trim();
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname == null ? null : staffname.trim();
    }

    public Integer getStafforderindex() {
        return stafforderindex;
    }

    public void setStafforderindex(Integer stafforderindex) {
        this.stafforderindex = stafforderindex;
    }

    public Date getStatisticdate() {
        return statisticdate;
    }

    public void setStatisticdate(Date statisticdate) {
        this.statisticdate = statisticdate;
    }

    public Integer getSystemindex() {
        return systemindex;
    }

    public void setSystemindex(Integer systemindex) {
        this.systemindex = systemindex;
    }

    public String getSystemtype() {
        return systemtype;
    }

    public void setSystemtype(String systemtype) {
        this.systemtype = systemtype == null ? null : systemtype.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}