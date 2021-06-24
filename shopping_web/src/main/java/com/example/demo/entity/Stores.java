package com.example.demo.entity;

public class Stores {
    private Integer storeId;

    private String storeName;

    private String storePhone;

    private String storeBank;

    private String storeCardJpg;

    private String storeManageJpg;

    private String storeBan;

    private Integer storeLevel;

    private String storePwd;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone == null ? null : storePhone.trim();
    }

    public String getStoreBank() {
        return storeBank;
    }

    public void setStoreBank(String storeBank) {
        this.storeBank = storeBank == null ? null : storeBank.trim();
    }

    public String getStoreCardJpg() {
        return storeCardJpg;
    }

    public void setStoreCardJpg(String storeCardJpg) {
        this.storeCardJpg = storeCardJpg == null ? null : storeCardJpg.trim();
    }

    public String getStoreManageJpg() {
        return storeManageJpg;
    }

    public void setStoreManageJpg(String storeManageJpg) {
        this.storeManageJpg = storeManageJpg == null ? null : storeManageJpg.trim();
    }

    public String getStoreBan() {
        return storeBan;
    }

    public void setStoreBan(String storeBan) {
        this.storeBan = storeBan == null ? null : storeBan.trim();
    }

    public Integer getStoreLevel() {
        return storeLevel;
    }

    public void setStoreLevel(Integer storeLevel) {
        this.storeLevel = storeLevel;
    }

    public String getStorePwd() {
        return storePwd;
    }

    public void setStorePwd(String storePwd) {
        this.storePwd = storePwd == null ? null : storePwd.trim();
    }
}