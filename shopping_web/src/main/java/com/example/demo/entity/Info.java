package com.example.demo.entity;

public class Info {
    private Integer infoId;

    private String infoUserName;

    private String infoStoreName;

    private Integer infoGoodId;

    private Integer infoGoodNumber;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getInfoUserName() {
        return infoUserName;
    }

    public void setInfoUserName(String infoUserName) {
        this.infoUserName = infoUserName == null ? null : infoUserName.trim();
    }

    public String getInfoStoreName() {
        return infoStoreName;
    }

    public void setInfoStoreName(String infoStoreName) {
        this.infoStoreName = infoStoreName == null ? null : infoStoreName.trim();
    }

    public Integer getInfoGoodId() {
        return infoGoodId;
    }

    public void setInfoGoodId(Integer infoGoodId) {
        this.infoGoodId = infoGoodId;
    }

    public Integer getInfoGoodNumber() {
        return infoGoodNumber;
    }

    public void setInfoGoodNumber(Integer infoGoodNumber) {
        this.infoGoodNumber = infoGoodNumber;
    }
}