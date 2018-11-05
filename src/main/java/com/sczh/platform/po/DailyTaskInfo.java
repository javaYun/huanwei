package com.sczh.platform.po;

import java.util.Date;

public class DailyTaskInfo {
    private Integer id;

    private String sponsor;

    private Date starttime;

    private Date endtime;

    private String taskname;

    private String urgency;

    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor == null ? null : sponsor.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname == null ? null : taskname.trim();
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency == null ? null : urgency.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

	@Override
	public String toString() {
		return "DailyTaskInfo [id=" + id + ", sponsor=" + sponsor
				+ ", starttime=" + starttime + ", endtime=" + endtime
				+ ", taskname=" + taskname + ", urgency=" + urgency + ", code="
				+ code + "]";
	}
    
    
    
}