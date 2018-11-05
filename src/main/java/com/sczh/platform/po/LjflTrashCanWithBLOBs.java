package com.sczh.platform.po;

public class LjflTrashCanWithBLOBs extends LjflTrashCan {
    private String fMemo;

    private String fPhotoIds;

    private String fTriggerCondition;

    public String getfMemo() {
        return fMemo;
    }

    public void setfMemo(String fMemo) {
        this.fMemo = fMemo == null ? null : fMemo.trim();
    }

    public String getfPhotoIds() {
        return fPhotoIds;
    }

    public void setfPhotoIds(String fPhotoIds) {
        this.fPhotoIds = fPhotoIds == null ? null : fPhotoIds.trim();
    }

    public String getfTriggerCondition() {
        return fTriggerCondition;
    }

    public void setfTriggerCondition(String fTriggerCondition) {
        this.fTriggerCondition = fTriggerCondition == null ? null : fTriggerCondition.trim();
    }
}