package com.sczh.platform.po;

import java.util.Date;

public class LjflPrizeProvideRecord1 {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private Date divisiontime;

    private String cdkey;

    private Date providedate;

    private String providemanid;

    private Integer providenum;

    private Integer providestatus;

    private Date receivedate;

    private Double spendscore;

    private String prizeid;

    private String prizeprovideid;

    private String staffdetailid;

    private String tablename;
    
    public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

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

    public String getCdkey() {
        return cdkey;
    }

    public void setCdkey(String cdkey) {
        this.cdkey = cdkey == null ? null : cdkey.trim();
    }

    public Date getProvidedate() {
        return providedate;
    }

    public void setProvidedate(Date providedate) {
        this.providedate = providedate;
    }

    public String getProvidemanid() {
        return providemanid;
    }

    public void setProvidemanid(String providemanid) {
        this.providemanid = providemanid == null ? null : providemanid.trim();
    }

    public Integer getProvidenum() {
        return providenum;
    }

    public void setProvidenum(Integer providenum) {
        this.providenum = providenum;
    }

    public Integer getProvidestatus() {
        return providestatus;
    }

    public void setProvidestatus(Integer providestatus) {
        this.providestatus = providestatus;
    }

    public Date getReceivedate() {
        return receivedate;
    }

    public void setReceivedate(Date receivedate) {
        this.receivedate = receivedate;
    }

    public Double getSpendscore() {
        return spendscore;
    }

    public void setSpendscore(Double spendscore) {
        this.spendscore = spendscore;
    }

    public String getPrizeid() {
        return prizeid;
    }

    public void setPrizeid(String prizeid) {
        this.prizeid = prizeid == null ? null : prizeid.trim();
    }

    public String getPrizeprovideid() {
        return prizeprovideid;
    }

    public void setPrizeprovideid(String prizeprovideid) {
        this.prizeprovideid = prizeprovideid == null ? null : prizeprovideid.trim();
    }

    public String getStaffdetailid() {
        return staffdetailid;
    }

    public void setStaffdetailid(String staffdetailid) {
        this.staffdetailid = staffdetailid == null ? null : staffdetailid.trim();
    }

	@Override
	public String toString() {
		return "LjflPrizeProvideRecord [id=" + id + ", createtime="
				+ createtime + ", lastchangetime=" + lastchangetime
				+ ", status=" + status + ", beendeleted=" + beendeleted
				+ ", deletedtime=" + deletedtime + ", businesssystemid="
				+ businesssystemid + ", tenantid=" + tenantid
				+ ", divisiontime=" + divisiontime + ", cdkey=" + cdkey
				+ ", providedate=" + providedate + ", providemanid="
				+ providemanid + ", providenum=" + providenum
				+ ", providestatus=" + providestatus + ", receivedate="
				+ receivedate + ", spendscore=" + spendscore + ", prizeid="
				+ prizeid + ", prizeprovideid=" + prizeprovideid
				+ ", staffdetailid=" + staffdetailid + "]";
	}
    
    
}