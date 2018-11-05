package com.sczh.platform.po;

import java.util.Date;

public class AreaCommunity {
    private String id;

    private String communitycode;

    private String areaid;

    private String areacode;

    private String communityname;

    private String communitydescribe;

    private String communityleader;

    private String communitystatus;

    private Date setuptime;

    private Date changetime;

    private String parentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCommunitycode() {
        return communitycode;
    }

    public void setCommunitycode(String communitycode) {
        this.communitycode = communitycode == null ? null : communitycode.trim();
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public String getCommunityname() {
        return communityname;
    }

    public void setCommunityname(String communityname) {
        this.communityname = communityname == null ? null : communityname.trim();
    }

    public String getCommunitydescribe() {
        return communitydescribe;
    }

    public void setCommunitydescribe(String communitydescribe) {
        this.communitydescribe = communitydescribe == null ? null : communitydescribe.trim();
    }

    public String getCommunityleader() {
        return communityleader;
    }

    public void setCommunityleader(String communityleader) {
        this.communityleader = communityleader == null ? null : communityleader.trim();
    }

    public String getCommunitystatus() {
        return communitystatus;
    }

    public void setCommunitystatus(String communitystatus) {
        this.communitystatus = communitystatus == null ? null : communitystatus.trim();
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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }
}