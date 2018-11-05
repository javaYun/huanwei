package com.sczh.platform.po;

import java.util.Date;

public class AreaGov {
    private String id;

    private String areacode;

    private String areaname;

    private String areadescription;

    private String areaaddress;

    private String areaperson;

    private String areaphone;

    private String areaadmin;

    private Date setuptime;

    private String state;

    private Date changetime;

    private String latitude;

    private String lontitude;

    private String areatype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getAreadescription() {
        return areadescription;
    }

    public void setAreadescription(String areadescription) {
        this.areadescription = areadescription == null ? null : areadescription.trim();
    }

    public String getAreaaddress() {
        return areaaddress;
    }

    public void setAreaaddress(String areaaddress) {
        this.areaaddress = areaaddress == null ? null : areaaddress.trim();
    }

    public String getAreaperson() {
        return areaperson;
    }

    public void setAreaperson(String areaperson) {
        this.areaperson = areaperson == null ? null : areaperson.trim();
    }

    public String getAreaphone() {
        return areaphone;
    }

    public void setAreaphone(String areaphone) {
        this.areaphone = areaphone == null ? null : areaphone.trim();
    }

    public String getAreaadmin() {
        return areaadmin;
    }

    public void setAreaadmin(String areaadmin) {
        this.areaadmin = areaadmin == null ? null : areaadmin.trim();
    }

    public Date getSetuptime() {
        return setuptime;
    }

    public void setSetuptime(Date setuptime) {
        this.setuptime = setuptime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getChangetime() {
        return changetime;
    }

    public void setChangetime(Date changetime) {
        this.changetime = changetime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLontitude() {
        return lontitude;
    }

    public void setLontitude(String lontitude) {
        this.lontitude = lontitude == null ? null : lontitude.trim();
    }

    public String getAreatype() {
        return areatype;
    }

    public void setAreatype(String areatype) {
        this.areatype = areatype == null ? null : areatype.trim();
    }
}