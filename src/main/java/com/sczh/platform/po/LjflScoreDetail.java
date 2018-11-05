package com.sczh.platform.po;

import java.util.Date;

public class LjflScoreDetail {
    private String id;

    private Date createtime;

    private Date lastchangetime;

    private Integer status;

    private Integer beendeleted;

    private Date deletedtime;

    private String businesssystemid;

    private String tenantid;

    private String memo;

    private Integer score;

    private Date takeparttime;

    private String ljflexerciseid;

    private String staffdetailid;

    private Date divisiontime;

    private String operatemanid;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getTakeparttime() {
        return takeparttime;
    }

    public void setTakeparttime(Date takeparttime) {
        this.takeparttime = takeparttime;
    }

    public String getLjflexerciseid() {
        return ljflexerciseid;
    }

    public void setLjflexerciseid(String ljflexerciseid) {
        this.ljflexerciseid = ljflexerciseid == null ? null : ljflexerciseid.trim();
    }

    public String getStaffdetailid() {
        return staffdetailid;
    }

    public void setStaffdetailid(String staffdetailid) {
        this.staffdetailid = staffdetailid == null ? null : staffdetailid.trim();
    }

    public Date getDivisiontime() {
        return divisiontime;
    }

    public void setDivisiontime(Date divisiontime) {
        this.divisiontime = divisiontime;
    }

    public String getOperatemanid() {
        return operatemanid;
    }

    public void setOperatemanid(String operatemanid) {
        this.operatemanid = operatemanid == null ? null : operatemanid.trim();
    }
}