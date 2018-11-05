package com.sczh.platform.po;

import java.util.Date;

public class DepartmentInfo {
    private Integer id;

    private String departmentcode;

    private String unitcode;

    private String departmentname;

    private String departmentdescribe;

    private String departmentleader;

    private String state;

    private Date setuptime;

    private Date changetime;

    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode == null ? null : departmentcode.trim();
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode == null ? null : unitcode.trim();
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname == null ? null : departmentname.trim();
    }

    public String getDepartmentdescribe() {
        return departmentdescribe;
    }

    public void setDepartmentdescribe(String departmentdescribe) {
        this.departmentdescribe = departmentdescribe == null ? null : departmentdescribe.trim();
    }

    public String getDepartmentleader() {
        return departmentleader;
    }

    public void setDepartmentleader(String departmentleader) {
        this.departmentleader = departmentleader == null ? null : departmentleader.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getSetuptime() {
        return setuptime;
    }

    public void setSetuptime(Date setuptime) {
        this.setuptime = setuptime;
    }

    public Date getChangetime() {
        return changetime;
    }

    public void setChangetime(Date changetime) {
        this.changetime = changetime;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}