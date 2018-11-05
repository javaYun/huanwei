package com.sczh.platform.po;

import java.util.Date;

public class LjflExercise {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private Date endtime;

    private String exercisetypeid;

    private Integer maxscore;

    private Integer minscore;

    private String name;

    private Date starttime;

    private Integer type;

    private String fLjflhousesId;

    private String exercisetypename;

    private Integer peoplenum;

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

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getExercisetypeid() {
        return exercisetypeid;
    }

    public void setExercisetypeid(String exercisetypeid) {
        this.exercisetypeid = exercisetypeid == null ? null : exercisetypeid.trim();
    }

    public Integer getMaxscore() {
        return maxscore;
    }

    public void setMaxscore(Integer maxscore) {
        this.maxscore = maxscore;
    }

    public Integer getMinscore() {
        return minscore;
    }

    public void setMinscore(Integer minscore) {
        this.minscore = minscore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getfLjflhousesId() {
        return fLjflhousesId;
    }

    public void setfLjflhousesId(String fLjflhousesId) {
        this.fLjflhousesId = fLjflhousesId == null ? null : fLjflhousesId.trim();
    }

    public String getExercisetypename() {
        return exercisetypename;
    }

    public void setExercisetypename(String exercisetypename) {
        this.exercisetypename = exercisetypename == null ? null : exercisetypename.trim();
    }

    public Integer getPeoplenum() {
        return peoplenum;
    }

    public void setPeoplenum(Integer peoplenum) {
        this.peoplenum = peoplenum;
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

	@Override
	public String toString() {
		return "LjflExercise [id=" + id + ", createtime=" + createtime
				+ ", lastchangetime=" + lastchangetime + ", status=" + status
				+ ", beendeleted=" + beendeleted + ", deletedtime="
				+ deletedtime + ", businesssystemid=" + businesssystemid
				+ ", tenantid=" + tenantid + ", endtime=" + endtime
				+ ", exercisetypeid=" + exercisetypeid + ", maxscore="
				+ maxscore + ", minscore=" + minscore + ", name=" + name
				+ ", starttime=" + starttime + ", type=" + type
				+ ", fLjflhousesId=" + fLjflhousesId + ", exercisetypename="
				+ exercisetypename + ", peoplenum=" + peoplenum + ", auditid="
				+ auditid + "]";
	}
    
   
    
}