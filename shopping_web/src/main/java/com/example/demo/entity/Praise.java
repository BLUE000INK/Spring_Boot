package com.example.demo.entity;

public class Praise {
    private Integer praiseId;

    private String praiseUserName;

    private String praiseStoreName;

    private String praiseInfo;

    public Integer getPraiseId() {
        return praiseId;
    }

    public void setPraiseId(Integer praiseId) {
        this.praiseId = praiseId;
    }

    public String getPraiseUserName() {
        return praiseUserName;
    }

    public void setPraiseUserName(String praiseUserName) {
        this.praiseUserName = praiseUserName == null ? null : praiseUserName.trim();
    }

    public String getPraiseStoreName() {
        return praiseStoreName;
    }

    public void setPraiseStoreName(String praiseStoreName) {
        this.praiseStoreName = praiseStoreName == null ? null : praiseStoreName.trim();
    }

    public String getPraiseInfo() {
        return praiseInfo;
    }

    public void setPraiseInfo(String praiseInfo) {
        this.praiseInfo = praiseInfo == null ? null : praiseInfo.trim();
    }
}